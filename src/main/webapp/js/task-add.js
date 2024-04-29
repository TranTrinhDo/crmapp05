$(document).ready(function() {
	$(".btn-add").click(function() {
		taskName = $("#taskName").val();
		dateEnd = $("#dateEnd").val();
		dateStart = $("#dateStart").val();
		if (taskName == "" || dateEnd == "" || dateStart == "") {
			alert("Vui lòng nhập đầy đủ thông tin");
			return;
		}
	});
})