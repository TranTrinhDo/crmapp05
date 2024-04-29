$(document).ready(function() {
	$(".btn-add").click(function() {
		projectName = $("#projectname").val();
		dateEnd = $("#dateEnd").val();
		dateStart = $("#dateStart").val();
		if (projectName == "" || dateEnd == "" || dateStart == "") {
			alert("Vui lòng nhập đầy đủ thông tin");
			return;
		}
	});
})