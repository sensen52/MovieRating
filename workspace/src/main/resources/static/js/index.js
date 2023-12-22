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

//찜하기 버튼
let steamed = document.querySelector("button.steamed");
let opacity = 1;

function handleClick() {
  if (opacity === 1) {
    opacity = 0.5;
  } else {
    opacity = 1;
    alert("장바구니에 영화가 담겼습니다.");
  }
  steamed.style.opacity = opacity;
}

steamed.addEventListener("click", handleClick);

//리뷰쓰기 나타나는 버튼

let reviewWriteButton = document.querySelector("div.review_intro_button > a");
let reviewInputForm = document.querySelector("div.review_input_form");

function toggleReviewInputForm() {
  let currentDisplay = window
    .getComputedStyle(reviewInputForm)
    .getPropertyValue("opacity");

  if (currentDisplay === "1") {
    reviewInputForm.style.opacity = "0";
    reviewWriteButton.textContent = "리뷰 쓰기";
  } else {
    reviewInputForm.style.opacity = "1";
    reviewWriteButton.textContent = "리뷰 닫기";
  }
}
reviewWriteButton.addEventListener("click", toggleReviewInputForm);

//페이지 클릭 시 색 바뀌는것
const pageButtons = document.querySelectorAll(".pagebtn");

        pageButtons.forEach(button => {
            button.addEventListener("click", function() {
                // 모든 페이지 버튼에서 "active" 클래스를 제거합니다.
                pageButtons.forEach(btn => btn.classList.remove("active"));
                // 현재 클릭된 페이지 버튼에 "active" 클래스를 추가합니다.
                this.classList.add("active");
            });
        });

function confirmLogout() {
    return confirm('정말로 로그아웃 하시겠습니까?');
}