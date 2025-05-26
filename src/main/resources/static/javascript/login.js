function showTab(tab) {
    const loginForm = document.getElementById("login-form");
    const signupForm = document.getElementById("signup-form");
    const tabLogin = document.getElementById("tab-login");
    const tabSignup = document.getElementById("tab-signup");

    if (tab === 'login') {
        loginForm.classList.add("active");
        signupForm.classList.remove("active");
        tabLogin.classList.add("active");
        tabSignup.classList.remove("active");
    } else {
        loginForm.classList.remove("active");
        signupForm.classList.add("active");
        tabLogin.classList.remove("active");
        tabSignup.classList.add("active");
    }
}

function login() {
    const id = document.getElementById("login-id").value.trim();
    const pw = document.getElementById("login-pw").value.trim();

    if (id && pw) {
        alert(`어서오세요, ${id}님!`);
        window.location.href = "index.html";
    } else {
        alert("아이디와 비밀번호를 입력해주세요.");
    }
}

function signup() {
    const id = document.getElementById("signup-id").value.trim();
    const email = document.getElementById("signup-email").value.trim();
    const pw = document.getElementById("signup-pw").value.trim();

    if (id && email && pw) {
        alert(`${id}님, 회원가입이 완료되었습니다.`);
        showTab('login');
    } else {
        alert("모든 항목을 입력해주세요.");
    }
}
