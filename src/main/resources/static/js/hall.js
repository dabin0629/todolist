document.addEventListener("DOMContentLoaded", function () {
    const sayingList = document.getElementById("saying-list");

    // 서버에서 명언 리스트 가져오기
    fetch('/hall/api/sayings')
        .then(response => {
            if (!response.ok) {
                throw new Error('서버로부터의 응답이 올바르지 않습니다.');
            }
            return response.json();
        })
        .then(data => {
            renderSayings(data);
        })
        .catch(error => console.error('Error:', error));

    // 명언 리스트 출력 함수
    function renderSayings(sayings) {
        sayingList.innerHTML = ""; // 기존 리스트 초기화
        sayings.forEach(saying => {
            addSayingToList(saying);
        });
    }

    // 명언을 리스트에 추가하는 함수
    function addSayingToList(saying) {
        const listItem = document.createElement("li");
        listItem.textContent = `"${saying.quote}" - ${saying.author}`;
        sayingList.appendChild(listItem);
    }

    // 새 명언 추가
    document.getElementById("add-saying-btn").addEventListener("click", function () {
        const newSaying = document.getElementById("new-saying").value.trim();
        if (newSaying) {
            // 새로운 명언 추가 요청 보내기
            fetch('/hall/api/sayings/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ quote: newSaying })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("서버로부터의 응답이 올바르지 않습니다.");
                }
                return response.json();
            })
            .then(data => {
                alert("명언이 성공적으로 추가되었습니다!");
                // 새로 추가된 명언을 리스트에 추가
                addSayingToList(data);
                document.getElementById("new-saying").value = ""; // 입력 필드 초기화
            })
            .catch(error => console.error('Error:', error));
        } else {
            alert("명언을 입력하세요!");
        }
    });
});
