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
		<%@ include file="../leftBar.jsp"%>
		<div id="right_Content">
<div class="table-wrapper">
	<div class="table-title">
		<h2>
			 ※ 문서등록
		</h2>
	</div>
	<table class="table table-striped table-hover" style="width: 100%; margin-top: 50px">
		<tbody>
			<tr style="height:8%">
				<td class="table-left">철제목</td>
				<td class="table-right">
					${documentBean.BIND_TITLE}
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">문서제목</td>
				<td class="table-right">
					${documentBean.DOCUMENT_TITLE}
				</td>
			</tr>
			<tr style="height:12%">
				<td class="table-left">문서요약</td>
				<td class="table-right">
					<textarea style="height: 85%;resize: none; width: 95%; overflow-y:scroll " readonly="readonly" rows="3">${documentBean.DOCUMENT_DESCRIPTION}</textarea>
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">작성일자</td>
				<td class="table-right">
					${documentBean.REGISTER_DATE}
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">문서함</td>
				<td class="table-right">
					${documentBean.FOLDER_NAME}
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">보안등급</td>
				<td class="table-right">
					${documentBean.SECURITY_GRADE}
				</td>
			</tr>
			<tr style="height:24%">
				<td class="table-left">첨부파일</td>
				<td class="table-right">
					<select id="fileListBox" size="6" style="width: 90%">
						<c:forEach var="list" items="${fileList}">
							<option value="${list.DOCUMENT_FILE_ID }">${list.DOCUMENT_FILE_ID }</option>
						</c:forEach>
					</select> 
					<input type="button" value="다운로드" onclick="downloadFile()">
				</td>
			</tr>
			<tr style="height:8%">
				<td colspan="2" style="text-align: right; width: 100%">
					<input type="button" value="삭제" onclick="documentDelete()">
					<!-- <input type="button" value="수정"> -->
					<input type="button" value="목록" onclick="documentlist()"><a style="display: none;" id="d-title">${folderName}</a>
				</td>
			</tr>
		</tbody>
	</table>
</div>
		</div>
	</div>
<script type="text/javascript">
console.log('누를때마다 호출');

function documentlist() {
	var nPage = ${nowPage};
	var arr = {FOLDER_NAME:'${folderName}',page:nPage,FOLDER_ID:'${folderId}'};
	var url = 'listDocument';
	console.log(arr);
	console.log(url);
	changeContent(url,arr);
}

function documentDelete() {
	var documentId = '${documentBean.DOCUMENT_ID}';
	
	$.ajax({
        url: '/jcone/documentDelete',
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8;',
        data: {DOCUMENT_ID:documentId},
        type: 'POST',
        success: function(result){
        	
        	console.log(result);

        },beforeSend:function(){
            //(이미지 보여주기 처리)
            $('.wrap-loading').removeClass('display-none');
        },complete:function(){
           // (이미지 감추기 처리)
            $('.wrap-loading').addClass('display-none');
        },error:function(e){
        	alert('오류 발생\n' + e);
        	form_Data = new FormData();
        },timeout:100000 //"응답제한시간 ms"

    });
}

function downloadFile() {
	
	var fileId = $('#fileListBox option:selected').val();
	
	$.ajax({
        url: '/jcone/download',
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8;',
        data: {DOCUMENT_FILE_ID:fileId},
        type: 'POST',
        success: function(result){
        	
        	console.log(result);

        },beforeSend:function(){
            //(이미지 보여주기 처리)
            $('.wrap-loading').removeClass('display-none');
        },complete:function(){
           // (이미지 감추기 처리)
            $('.wrap-loading').addClass('display-none');
        },error:function(e){
        	alert('오류 발생\n' + e);
        	form_Data = new FormData();
        },timeout:100000 //"응답제한시간 ms"

    });
	
}

</script>
<script src="resources/js/common.js"></script>
</body>
</html>