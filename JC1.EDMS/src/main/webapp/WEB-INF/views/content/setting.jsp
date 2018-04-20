<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JC1 EDMS</title>

<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/css/jquery-ui.css">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/table2.css">

<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="resources/js/jquery-ui.js"></script>
<script src="resources/js/datepicker-ko.js"></script>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<div id="main">
		<div class="nav">
			<div id="div_navigation" class="div_navigation">
				<img src="/jcone/resources/images/home.png" style="margin-top: 12px;"> > 설정
			</div>
		</div>
		<div style="height: 30px;">&nbsp;</div>
		<%@ include file="../leftBar.jsp"%>
		<div id="right_Content">
<div class="table-wrapper">
	<div class="table-title">
		<h2>
			 ※ 정보변경
		</h2>
	</div>
	<table class="table table-striped table-hover" style="width: 100%; margin-top: 50px">
		<tbody>
			<tr style="height:8%">
				<td class="table-left">새 비밀번호</td>
				<td class="table-right" style="text-align: center;">
					<input type="password" id="newPwd">
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">새 비밀번호 확인</td>
				<td class="table-right" style="text-align: center;">
					<input type="password" id="newPwdCon">
				</td>
			</tr>
			<tr style="height:8%">
				<td colspan="2" style="text-align: right; width: 100%">
					<input type="button" value="초기화" onclick="textClear()">
					<input type="button" value="확인" onclick="changePwd()">
				</td>
			</tr>
		</tbody>
	</table>
</div>
		</div>
	</div>
<script type="text/javascript">
function textClear() {
	$('#newPwd').val('');
	$('#newPwdCon').val('');
}

function changePwd() {
	var t1 = $('#newPwd').val();
	var t2 = $('#newPwdCon').val();
	
	
	if(t1 == ''){
		alert('새로운 비밀번호를 입력해주세요');
		return;
	}else if(t2 == ''){
		alert('확인할 비밀번호를 입력해주세요');
		return;
	}else if(t1 != t2){
		alert('비밀번호가 일치하지 않습니다.');
		return;
	}else{
		
		$.ajax({
		    url: '/jcone/changePwd',
		    contentType: 'application/x-www-form-urlencoded; charset=UTF-8;',
		    data: {newPassword:t1,newPasswordConfirm:t2},
		    dataType:'text',
		    type: 'POST',
		    success: function(result){
		    	if('success' == result){
		    		alert('변경완료');
		    		movePage('/jcone/main');
		    	}else{
		    		alert('fail');
		    	}
		
		    },beforeSend:function(){
		        //(이미지 보여주기 처리)
		        $('.wrap-loading').removeClass('display-none');
		    },complete:function(){
		       // (이미지 감추기 처리)
		        $('.wrap-loading').addClass('display-none');
		    },error:function(e){
		    	alert('오류 발생\n' + e);
		    },timeout:100000 //"응답제한시간 ms"
		
		});
	}
}
</script>
<script src="resources/js/common.js"></script>
</body>
</html>