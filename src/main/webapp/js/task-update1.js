$(document).ready(function() {
	$(".btn-update").click(function() {
		id = $(this).attr("id-task");
		var taskName = $("#taskName").val();
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var idUser = $("#idUser").val();
		var idStatus = $("#idStatus").val();
		if (taskName == "" || startDate == "" || endDate == "") {
			alert("Vui lòng nhập đầy đủ thông tin");
			return;
		}
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/crmapp05/api/task/update",
			data: { id: id, taskName: taskName, startDate: startDate, endDate: endDate, idUser: idUser, idStatus: idStatus }
		}).done(function(result) {
			if (result.data) {
				alert("Cập nhật thành công");
				window.location.href = 'http://localhost:8080/crmapp05/tasks';
			} else {
				alert("Cập nhật thất bại");
			}
		})
	})
})