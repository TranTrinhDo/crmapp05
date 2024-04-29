$(document).ready(function() {
	$(".btn-delete").click(function() {
		id = $(this).attr("id-project")
		row = $(this).closest("tr");
		if (confirm("Bạn có chắc chắn muốn xóa dự án này không?")) {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/crmapp05/api/project/delete?id=" + id
			})
				.done(function(result) {
					if (result.data) {
						row.remove();
					} else {
						alert("Xóa thất bại");
					}
				})
		}
	});
})