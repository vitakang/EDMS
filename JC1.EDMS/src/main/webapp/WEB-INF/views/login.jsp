<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>JC1 EDMS</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<link rel="stylesheet" href="/jcone/resources/css/bootstrap.min.css">
<script src="/jcone/resources/js/jquery.min.js"></script>
<script src="/jcone/resources/js/login.js?ver=1.0"></script>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<style>
body {
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: -1px;
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.account-wall {
	margin-top: 80px;
	padding: 40px 0px 20px 0px;
	background-color: #ffffff;
	box-shadow: 0 2px 10px 0 rgba(0, 0, 0, 0.16);
}

.login-title {
	color: #555;
	font-size: 22px;
	font-weight: 400;
	display: block;
}

.profile-img {
	width: 96px;
	height: 96px;
	margin: 0 auto 10px;
	display: block;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
}

.select-img {
	border-radius: 50%;
	display: block;
	height: 75px;
	margin: 0 30px 10px;
	width: 75px;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
}

.select-name {
	display: block;
	margin: 30px 10px 10px;
}

.logo-img {
	width: 96px;
	height: 96px;
	margin: 0 auto 10px;
	display: block;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
}
</style>
<body >
	<!------ Include the above in your HEAD tag ---------->
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4" style="margin: 0px auto; float: none;">
				<div class="account-wall">
					<div id="my-tab-content" class="tab-content">
						<div class="tab-pane active" id="login">
							<img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120" alt="">
							<form class="form-signin" id="formData">
								<input type="text" class="form-control" placeholder="Email" required autofocus name="email" value="test">
								<input type="password" class="form-control" placeholder="Password" required name="password" value="test">
								<input type="button" class="btn btn-lg btn-default btn-block" value="Login" onclick="login();"/>
							</form>
							<!-- <div id="tabs" data-tabs="tabs">
								<p class="text-center">
									<a href="#register" data-toggle="tab">Need an Account?</a>
								</p>
								<p class="text-center">
									<a href="#select" data-toggle="tab">Select Account</a>
								</p>
							</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<script>
	var a = '${param.page}';
</script>