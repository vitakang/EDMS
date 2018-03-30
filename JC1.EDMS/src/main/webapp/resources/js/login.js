function login() {
//	var formData = new FormData(document.getElementById("formData"));
	var formData = $("#formData").serialize();
	$.ajax({
		type : "POST",
		url : "login",
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		data : formData,
//		processData : false,
		success : function(data) {
			console.log(data);
		},
		error : function(e) {
			alert(e);
		}
	});
}