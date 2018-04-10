function login() {
//	var formData = new FormData(document.getElementById("formData"));
	var formData = $("#formData").serializeArray();
//	var formData = new Object();
//	formData.id = $("input[name=id]").val();
//	formData.password = $("input[name=password]").val();
	
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
				document.body.appendChild(form);
				form.submit();
			}
		},
		error : function(xhr, textStatus, error) {
			if (xhr.status == "901") {
				 location.href = "loginView";
//				goPage("loginView.jcg", {'COMMAND' : 'NOT_LOGIN'});
			}
		},
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("AJAX", "true");
		}
	});
}