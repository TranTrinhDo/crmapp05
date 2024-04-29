$(document).ready(function() {
    $(".btn-update").click(function() {
        var id = $(this).attr("id-user");
        var firstname = $("#firstname").val();
        var lastname = $("#lastname").val();
        var email = $("#email").val();
        var password = $("#password").val();
        var idRole = $("#idRole option:selected").val();
        var fullname = $("#fullname").val();
        var phone = $("#phone").val();
        
        if (fullname == "" || phone == "" || firstname == "" || lastname == "" || email == "" || password == "" || idRole == -1) {
            alert("Vui lòng nhập đầy đủ thông tin");
            return;
        }
        
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/crmapp05/api/user/update",
            data: {id: id, firstname: firstname, lastname: lastname, email: email, password: password, idRole: idRole, fullname: fullname, phone: phone }
        }).done(function(result) {
            if (result.data) {
                alert("Cập nhật thành công");
                window.location.href = 'http://localhost:8080/crmapp05/users';
            } else {
                alert("Cập nhật thất bại");
            }
        }).fail(function(jqXHR, textStatus, errorThrown) {
            alert("Đã xảy ra lỗi: " + errorThrown);
        });
    });

    $(".btn-return").click(function() {
        window.location.href = 'http://localhost:8080/crmapp05/users';
    });
});