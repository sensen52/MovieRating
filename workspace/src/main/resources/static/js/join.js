    $(document).ready(function () {
        // 전체약관동의 체크박스 클릭 시
        $(".agreebox1 input[type='checkbox']").change(function () {
            if ($(this).is(":checked")) {
                // 전체약관동의가 체크된 경우 모든 하위 체크박스 체크
                $(".agreebox2 input[type='checkbox'], .agreebox3 input[type='checkbox']").prop('checked', true);
            } else {
                // 전체약관동의가 해제된 경우 모든 하위 체크박스 해제
                $(".agreebox2 input[type='checkbox'], .agreebox3 input[type='checkbox']").prop('checked', false);
            }
        });

        // 하위 체크박스 중 하나라도 해제될 경우 전체약관동의 체크 해제
        $(".agreebox2 input[type='checkbox'], .agreebox3 input[type='checkbox']").change(function () {
            if (!$(this).is(":checked")) {
                $(".agreebox1 input[type='checkbox']").prop('checked', false);
            }
        });
    });

    $(document).ready(function(){
        // 아이디 중복 검사
        $("input[name='userId']").on('input', function() {
            var userId = $(this).val();

            // 유효성 검사: 아이디가 비어있는지 확인
            if (userId == '') {
                $("#userIdError").text("아이디를 입력하세요.");
                return;
            }

            // 중복 검사: 아이디가 이미 사용중인지 확인
            $.ajax({
                url: '/member/idCheck',
                type: 'POST',
                data: JSON.stringify({ userId: userId }),
                contentType: "application/json",
                success: function(data) {
                    if (data.status === 'fail') {
                        $("#userIdError").text(data.message);
                    } else {
                        $("#userIdError").text(data.message).css("color", "green");;
                    }
                },
                error: function(error) {
                    console.error(error);
                }
            });
        });

        $("form").on("submit", function(event){

            // 유효성 검사
            var isValid = true;

            // 아이디 유효성 검사
            var userId = $("input[name='userId']").val();
            if(userId == ""){
                $("#userIdError").text("아이디를 입력해주세요.");
                isValid = false;
            } else {
                $("#userIdError").text("");
            }
            // 이름 유효성 검사
            var userName = $("input[name='userName']").val();
            if(userName == ""){
                $("#userNameError").text("이름을 입력해주세요.");
                isValid = false;
            } else {
                $("#userNameError").text("");
            }
            // 비밀번호 유효성 검사
            var userPw = $("input[name='userPw']").val();
            if(userPw == ""){
                $("#userPwError").text("비밀번호를 입력해주세요.");
                isValid = false;
            } else {
                $("#userPwError").text("");
            }
            // 비밀번호 확인 유효성 검사
            var userPwCheck = $("input[name='userPwCheck']").val();
            if(userPwCheck != userPw){
                $("#userPwError1").text("비밀번호가 일치하지 않습니다.");
                isValid = false;
            } else {
                $("#userPwError1").text("");
            }
            // 이메일 유효성 검사
            var userEmail = $("input[name='userEmail']").val();
            var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
            if(!emailPattern.test(userEmail)){
                $("#userEmailError").text("유효한 이메일 주소를 입력해주세요.");
                isValid = false;
            } else {
                $("#userEmailError").text("");
            }
            // 생년월일 유효성 검사
            var userBirthday = $("input[name='userBirthday']").val();
            var birthdayPattern = /^\d{4}-\d{2}-\d{2}$/;
            if(!birthdayPattern.test(userBirthday)){
                $("#userBirthdayError").text("유효한 생년월일을 입력해주세요. (예: 1990-01-01)");
                isValid = false;
            } else {
                $("#userBirthdayError").text("");
            }

            if (!isValid) {
                event.preventDefault();
                return;
            }

            if(!$('.agreebox2 input[type="checkbox"]').is(':checked')){
                alert('필수 약관동의가 필요합니다.');
                event.preventDefault();
                return;
            }

            var formData = $(this).serialize();
            $.ajax({
                url: '/member/join',
                type: 'POST',
                data: formData,
                success: function(data) {
                    alert(data.message);
                    if (data.status === 'success') {
                        window.location.href = "/main";
                    }
                },
                error: function(error) {
                    console.error(error);
                }
            });
            event.preventDefault();
        });
    });


    /* 이메일 인증 받기 */
    // 인증 버튼 클릭 시 인증번호 입력란 표시
    document.getElementById('emailVerification').addEventListener('click', function() {
        var userEmail = document.getElementById('userEmail').value;
        if (userEmail.trim() !== '') {
            $.ajax({
                url: '/member/mailConfirm',
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ email: userEmail }),
                success: function(data) {
                    alert("이메일이 발송되었습니다. 확인후 인증번호를 입력해주세요");
                    document.getElementById('verificationCode').style.display = 'block';
                    console.log('인증 코드: ', data);
                },
                error: function(error) {
                    console.error('Error:', error);
                }
            });
        } else {
            alert('이메일을 입력하세요.');
        }
    });


    /* 회원가입 이메일 인증확인 */
    $('#submitVerification').click(function() {
        var inputCode = $('#verificationInput').val();

        $.ajax({
            url: '/member/checkCode',
            type: 'POST',
            data: JSON.stringify({ code: inputCode }),
            contentType: "application/json", // 'Content-Type' 헤더를 'application/json'으로 설정
            success: function(result) {
                if (result) {
                    alert('인증 성공');
                } else {
                    alert('인증 실패');
                }
            }
        });
    });