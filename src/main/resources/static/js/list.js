document.addEventListener("DOMContentLoaded", function () {
    const taskList = document.getElementById("recommended-list");

    // 서버에서 추천된 리스트 가져오기
    fetch('/api/todolist')
        .then(response => response.ok ? response.json() : Promise.reject('서버 오류'))
        .then(data => {
            localStorage.setItem("recommendedTasks", JSON.stringify(data));
            renderTasks(data);
        })
        .catch(error => console.error('Error:', error));

    // 추천된 리스트 출력 함수
    function renderTasks(recommendedTasks) {
        taskList.innerHTML = "";
        recommendedTasks.forEach(task => {
            const listItem = document.createElement("li");
            listItem.className = "task-item";

            // 리스트 항목 텍스트
            const taskText = document.createElement("span");
            taskText.textContent = task.work;
            taskText.className = "task-text";

            // 진행률 버튼 그룹 생성
            const progressWrapper = document.createElement("div");
            progressWrapper.className = "progress-wrapper";
            const progressValues = ["20%", "50%", "70%", "100%"];

            progressValues.forEach(progressValue => {
                const progressButton = document.createElement("button");
                progressButton.textContent = progressValue;
                progressButton.className = "progress-btn";

                // 현재 진행률에 해당하는 버튼에 selected 클래스 추가
                if (task.progress === progressValue) {
                    progressButton.classList.add("selected");
                }

                // 버튼 클릭 이벤트 추가
                progressButton.addEventListener("click", () => {
                    updateProgress(task.id, progressValue);
                    highlightSelectedButton(progressWrapper, progressValue);
                });

                progressWrapper.appendChild(progressButton);
            });

            // 수정 및 삭제 버튼 추가
            const editButton = document.createElement("button");
            editButton.textContent = "수정";
            editButton.className = "edit-btn";
            editButton.addEventListener("click", () => editTask(task.id, task.work));

            const deleteButton = document.createElement("button");
            deleteButton.textContent = "삭제";
            deleteButton.className = "delete-btn";
            deleteButton.addEventListener("click", () => deleteTask(task.id));

            listItem.appendChild(taskText);
            listItem.appendChild(progressWrapper);
            listItem.appendChild(editButton);
            listItem.appendChild(deleteButton);
            taskList.appendChild(listItem);
        });
    }

    // 진행률 버튼 강조 표시 함수
    function highlightSelectedButton(progressWrapper, selectedProgress) {
        const buttons = progressWrapper.querySelectorAll(".progress-btn");
        buttons.forEach(button => {
            if (button.textContent === selectedProgress) {
                button.classList.add("selected");
            } else {
                button.classList.remove("selected");
            }
        });
    }

    // 진행률 업데이트 함수
    function updateProgress(id, progress) {
        const url = `/api/todolist/progress/${id}?progress=${encodeURIComponent(progress)}`;

        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
        })
        .then(response => response.ok ? response.text() : Promise.reject(`서버 오류: ${response.status}`))
        .then(message => {
            alert(message); // 서버로부터 받은 메시지 표시

            // 로컬 스토리지 업데이트 및 화면 반영
            const recommendedTasks = JSON.parse(localStorage.getItem("recommendedTasks")) || [];
            const taskIndex = recommendedTasks.findIndex(task => task.id === id);
            if (taskIndex > -1) {
                recommendedTasks[taskIndex].progress = progress;
                localStorage.setItem("recommendedTasks", JSON.stringify(recommendedTasks));
                renderTasks(recommendedTasks);
            }
        })
        .catch(error => console.error('Error:', error));
    }

    // 할 일 수정 함수
    function editTask(id, currentWork) {
        const updatedTask = prompt("수정할 내용을 입력하세요:", currentWork);
        if (updatedTask) {
            fetch(`/api/todolist/edit/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ work: updatedTask })
            })
            .then(response => response.ok ? response.json() : Promise.reject('서버 오류'))
            .then(data => {
                const recommendedTasks = JSON.parse(localStorage.getItem("recommendedTasks")) || [];
                const taskIndex = recommendedTasks.findIndex(task => task.id === id);
                if (taskIndex > -1) {
                    recommendedTasks[taskIndex].work = updatedTask;
                    localStorage.setItem("recommendedTasks", JSON.stringify(recommendedTasks));
                    renderTasks(recommendedTasks);
                }
            })
            .catch(error => console.error('Error:', error));
        }
    }

    // 할 일 삭제 함수
    function deleteTask(id) {
        fetch(`/api/todolist/delete/${id}`, {
            method: 'DELETE'
        })
        .then(response => response.ok ? response.text() : Promise.reject('서버 오류'))
        .then(() => {
            let recommendedTasks = JSON.parse(localStorage.getItem("recommendedTasks")) || [];
            recommendedTasks = recommendedTasks.filter(task => task.id !== id);
            localStorage.setItem("recommendedTasks", JSON.stringify(recommendedTasks));
            renderTasks(recommendedTasks);
        })
        .catch(error => console.error('Error:', error));
    }

    // 새 할 일 추가
    document.getElementById("add-task-btn").addEventListener("click", function () {
        const newTask = document.getElementById("new-task").value;
        if (newTask) {
            fetch('/api/todolist/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ work: newTask, progress: "0%" })
            })
            .then(response => response.ok ? response.json() : Promise.reject('서버 오류'))
            .then(data => {
                const recommendedTasks = JSON.parse(localStorage.getItem("recommendedTasks")) || [];
                recommendedTasks.push(data);
                localStorage.setItem("recommendedTasks", JSON.stringify(recommendedTasks));
                renderTasks(recommendedTasks);
                document.getElementById("new-task").value = "";
            })
            .catch(error => console.error('Error:', error));
        }
    });
});
