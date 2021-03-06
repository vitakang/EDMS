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
				<img src="/jcone/resources/images/home.png" style="margin-top: 12px;"> > 팀문서함 등록
			</div>
		</div>
		<div style="height: 30px;">&nbsp;</div>
		<%@ include file="../leftBar.jsp"%>
		<div id="right_Content">
<div class="table-wrapper">
	<div class="table-title">
		<h2>
			 ※ 팀 문서함 등록
		</h2>
	</div>
	<table class="table table-striped" style="width: 100%; height:40%; margin-top: 50px">
		<tbody>
			<tr style="height:8%">
				<td rowspan="3" style="width: 40%">
					<ul class="treeMenu" style="margin-left: 100px">
						<li>
							<button type="button" class="holer"></button> <a class="open box-color" style="color: black;">${groupName}</a>
							<ul id="subMenu1">
								<c:forEach var="gList" items="${groupFolderList}">
									<li class="forderPut">${gList.FOLDER_NAME}</li>
								</c:forEach>
							</ul>
						</li>
					</ul>
				</td>
				<td style="text-align: center;">상위문서함</td>
				<td style="width: 50%">
					<input type="text" style="width: 300px;" readonly="readonly" value="${groupName}">
				</td>
			</tr>
			<tr style="height:8%">
				<td style="text-align: center;">문서함 이름</td>
				<td style="width: 50%">
					<input type="text" style="width: 300px;" id="folderName">
				</td>
			</tr>
			<tr style="height:8%">
				<td style="text-align: center;">문서함 설명</td>
				<td style="width: 50%">
					<input type="text" style="width: 300px;" id="folderDescription">
				</td>
			</tr>
		</tbody>
	</table>
	<div style="text-align: right;">
		<input type="button" value="등록" onclick="insertFolder()">
		<input type="button" value="초기화" onclick="folderReset()">
	</div>
	
</div>

		</div>
	</div>
<script type="text/javascript">

function folderReset() {
	$('#folderName').val('');
	$('#folderDescription').val('');
}

function insertFolder() {
	console.log('come');
	$.ajax({
	    url: '/jcone/insertFolder',
	    contentType: 'application/x-www-form-urlencoded; charset=UTF-8;',
	    data: {FOLDER_NAME:$('#folderName').val(),FOLDER_DESCRIPTION:$('#folderDescription').val()},
	    type: 'POST',
	    success: function(result){
	    	if('success' == result){
	    		alert('등록완료');
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
</script>
<script src="resources/js/common.js"></script>
</body>
</html>
