$(document).ready(function() {
	$(".btn-update").click(function() {
		id = $(this).attr("id-role");
		var roleName = $("#role-name").val();
		var description = $("#description").val();
		if (roleName == ""|| description =="") {
			alert("Vui lòng nhập đầy đủ thông tin");
			return;
		}
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/crmapp05/api/role/update",
			data: { id: id, roleName: roleName, description: description }
		}).done(function(result) {
            if (result.data) {
                alert("Cập nhật thành công");
                window.location.href = 'http://localhost:8080/crmapp05/roles';
            } else {
                alert("Cập nhật thất bại");
            }
        })
	})


	$(".btn-return").click(function() {
		window.location.href = 'http://localhost:8080/crmapp05/roles';
	});
})