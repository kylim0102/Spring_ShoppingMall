function lg() {
    var id = document.getElementById("id").value;
    var password = document.getElementById("password").value;

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

    return true;
}

