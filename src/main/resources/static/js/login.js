// 로그인 폼 동작 처리
document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('.login-form').addEventListener('submit', function (event) {
        event.preventDefault();
        const username = event.target.username.value;
        if (username) {
            localStorage.setItem('username', username); // 사용자 이름 저장
            window.location.href = "/main"; // Healist 메인 페이지로 이동 (경로 수정)
        } else {
            alert("아이디를 입력해주세요.");
        }
    });
});
