$(document).ready(function() {
	$(".btn-add").click(function() {
		roleName = $("#rolename").val();
		description = $("#description").val();
		if (roleName == "" || description == "") {
			alert("Vui lòng nhập đầy đủ thông tin");
			return;
		}
	});
	$(".btn-return").click(function() {
		window.location.href = 'http://localhost:8080/crmapp05/roles'
	})
})