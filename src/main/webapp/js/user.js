// $<=> jQuery
$(document).ready(function() {
	$(".btn-add").click(function() {
		window.location.href = 'http://localhost:8080/crmapp/add-user'
	})
	//DOM => Document Object Mapper
	//Đăng ký sự kiện click cho thẻ nào đang sử dụng class có tên là btn-xoa
	$('.btn-xoa').click(function() {
		var id = $(this).attr('id-user');
		var row = $(this).closest("tr");
		if (confirm("Bạn có chắc chắn muốn xóa người dùng này không?")) {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/crmapp05/api/user/delete?id=" + id
			})
				.done(function(result) {
					if (result.data) {
						row.remove();
					} else {
						alert("Xóa thất bại");
					}
				})
				.fail(function(jqXHR, textStatus, errorThrown) {
					alert("Đã xảy ra lỗi: " + errorThrown);
				});
		}
	});
	
	$(".btn-detail").click(function(){
		id = $(this).attr("id-user")
		
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/crmapp05/user-details?id="+id,
		}).done(function(data){
			window.location.href ="http://localhost:8080/crmapp05/user-details?id="+id;
		})
	})
})