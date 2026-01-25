document.addEventListener("DOMContentLoaded", function () {
    // 오늘 날짜 삽입
    const today = new Date().toISOString().split("T")[0];
    document.getElementById("current-date").textContent = `오늘의 날짜: ${today}`;

    // 네비게이션 함수
    window.navigateTo = function (page) {
        window.location.href = page;
    };
});
