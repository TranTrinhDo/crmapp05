$(document).ready(function() {
	$(".btn-delete").click(function() {
		id = $(this).attr("id-role");
		row = $(this).closest("tr");
		if (confirm("Bạn có chắc chắn muốn xóa quyền này không?")) {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/crmapp05/api/role/delete?id=" + id
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