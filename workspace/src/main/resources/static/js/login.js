const genres = document.getElementsByClassName("genre_pop");
const genre_btns = document.getElementsByClassName("genre_button");

for (let i = 0; i < genre_btns.length; i++) {
  genre_btns[i].addEventListener("click", function () {
    if (genres[i].style.display === "block") {
      genres[i].style.display = "none";
    } else {
      genres[i].style.display = "block";
    }
  });
}


//설정 창 열고 닫기 이벤트--------------------
const closeItems = document.querySelectorAll(".close");
const settingspage = document.querySelectorAll(".l_options");
const settingsItems= document.querySelectorAll(".login_button");

settingsItems.forEach((closeItem) => {
  closeItem.addEventListener("click", function () {
    // 각 해당 세팅 페이지에 "active" 클래스 추가
    settingspage.forEach((settingspage) => {
      settingspage.classList.add("active");
    });
  });
});


closeItems.forEach((closeItem) => {
  closeItem.addEventListener("click", function () {
    // 각 해당 세팅 페이지에서 "active" 클래스 제거
    settingspage.forEach((settingspage) => {
      settingspage.classList.remove("active");
    });
  });
});