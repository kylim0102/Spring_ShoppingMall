function su() {
    var name = document.getElementById("name").value;
    var f_rrn = document.getElementById("f_rrn").value;
    var b_rrn = document.getElementById("b_rrn").value;
    var f_phone = document.getElementById("f_phone").value;
    var m_phone = document.getElementById("m_phone").value;
    var e_phone = document.getElementById("e_phone").value;
    var id = document.getElementById("id").value;
    var password = document.getElementById("password").value;
    var re_password = document.getElementById("re_password").value;
    var mail = document.getElementById("mail").value;
    var web = document.getElementById("web").value;

     if (name == "") {
        alert("이름을 입력하세요!");
        document.getElementById("name").focus();
        return false;
    }

     if (f_rrn == "") {
        alert("주민등록번호를 입력하세요!");
        document.getElementById("f_rrn").focus();
        return false;
    }

    if (b_rrn == "") {
        alert("주민등록번호를 입력하세요!");
        document.getElementById("b_rrn").focus();
        return false;
    }

    if (f_phone == "") {
        alert("휴대폰 번호를 입력하세요!");
        document.getElementById("f_phone").focus();
        return false;
    }

    if (m_phone == "") {
        alert("휴대폰 번호를 입력하세요!");
        document.getElementById("m_phone").focus();
        return false;
    }

    if (e_phone == "") {
        alert("휴대폰 번호를 입력하세요!");
        document.getElementById("e_phone").focus();
        return false;
    }

    if (id == "") {
        alert("아이디를 입력하세요!");
        document.getElementById("id").focus();
        return false;
    }

    if (password == "") {
        alert("비밀번호를 입력하세요!");
        document.getElementById("password").focus();
        return false;
    }

    if (re_password == "") {
        alert("비밀번호 재확인을 하세요!");
        document.getElementById("re_password").focus();
        return false;
    }

    if (mail == "") {
        alert("이메일을 입력하세요!");
        document.getElementById("mail").focus();
        return false;
    }

    if (web == "") {
        alert("이메일을 입력하세요!");
        document.getElementById("web").focus();
        return false;
    }

    return true;
}