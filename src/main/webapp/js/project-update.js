$(document).ready(function() {
	$(".btn-update").click(function() {
		var id = $(this).attr("id-project");
		var projectName = $("#projectName").val();
		var dateStart = $("#dateStart").val();
		var dateEnd = $("#dateEnd").val();
		if (projectName == "" || dateStart == "" || dateEnd == "") {
			alert("Vui lòng nhập đầy đủ thông tin");
			return;
		}
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/crmapp05/api/project/update",
			data: { id: id, projectName: projectName, dateStart: dateStart, dateEnd: dateEnd }
		}).done(function(result) {
			if (result.data) {
				alert("Cập nhật thành công");
				window.location.href = 'http://localhost:8080/crmapp05/projects';
			} else {
				alert("Cập nhật thất bại");
			}
		})
	})
})