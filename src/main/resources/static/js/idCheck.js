$('#id').on("change", function(){
    const id = $("#id").val();
    const checkResult = document.getElementById("id-check-result");
    $.ajax({
        type: "POST",
        url: "/idCheck",
        dataType: "text",
        data: {
            "id": id
        },
       success: function (result) {
           if (result == 0) { // 중복된 아이디일 때
               checkResult.innerHTML = "중복된 ID입니다.";
               checkResult.style.color = "red";
           } else { // 중복되지 않은 아이디일 때
               checkResult.innerHTML = "사용 가능한 ID입니다.";
               checkResult.style.color = "blue";
           }
       },
        error: function () {
            alert("ajax 실패");
        }
    });
});