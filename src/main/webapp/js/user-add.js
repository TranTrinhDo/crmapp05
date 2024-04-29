$(document).ready(function() {
	$(".btn-add").click(function() {
		var fullname = $("#fullname").val();
		var email = $("#email").val();
		var password = $("#password").val();
		var phone = $("#phone").val();

		if (fullname === "" || email === "" || password === "" || phone === "") {
			alert("Vui lòng nhập đầy đủ thông tin");
			return;
		}

	});

	$(".btn-return").click(function() {
		window.location.href = 'http://localhost:8080/crmapp05/users'
	})
})