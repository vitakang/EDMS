function login() {
//	var formData = new FormData(document.getElementById("formData"));
	var formData = $("#formData").serializeArray();
	$.ajax({
		type : "POST",
		url : "login",
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		data : formData,
//		processData : false,
		success : function(data) {
			console.log(data);
			if (data == 'S') {
				var userId = formData[0].value;
				var form = document.createElement('form');
				var userIdElement = document.createElement("input");
				userIdElement.setAttribute("name", "userId");
				userIdElement.setAttribute("value", userId);
				userIdElement.setAttribute("type", "hidden");
				form.appendChild(userIdElement);
				form.setAttribute("action", "/jcone/main");
				form.setAttribute("method", "POST");
//				form.action = "/jcone/main";
//				form.method = "POST";
				document.body.appendChild(form);
				form.submit();
			}
		},
		error : function(e) {
			alert(e);
		}
	});
}