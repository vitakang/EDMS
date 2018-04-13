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
					<input type="text" style="width: 95%" id="bindTitle" value="${documentBean.BIND_TITLE}">	
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">문서제목</td>
				<td class="table-right">
					<input type="text" style="width: 95%" id="documentTitle" value="${documentBean.DOCUMENT_TITLE}">
				</td>
			</tr>
			<tr style="height:12%">
				<td class="table-left">문서요약</td>
				<td class="table-right">
					<textarea style="height: 85%;resize: none; width: 95%; overflow-y:scroll " rows="3" id="documentDescrption">${documentBean.DOCUMENT_DESCRIPTION}</textarea>
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">작성일자</td>
				<td class="table-right">
					<input type="text" id="registerDate" style="width: 40%;" readonly="readonly" value="${documentBean.REGISTER_DATE}">
					<input type="button" class="layer-btn" value="달력" id="datePicker">
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">문서함</td>
				<td class="table-right">
					<input type="text" style="width: 40%;" readonly="readonly" id="documentFolder" value="${documentBean.FOLDER_NAME}">
					<input type="text" style="display: none;" id="documentFolderId" value="${documentBean.FOLDER_ID}">
					<input type="button" class="layer-btn" value="찾아보기" id="documentBox">
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">보안등급</td>
				<td class="table-right">
					<span>
						<input type="radio" name="securityLevel" value="전체공개">
						<label class="radio-label">전체공개</label>
					</span>
					<span>
						<input type="radio" name="securityLevel" value="부분공개">
						<label class="radio-label">부분공개</label>
					</span>
					<span>
						<input type="radio" name="securityLevel" value="팀공개">
						<label class="radio-label">팀공개</label>
					</span>
					<span>
						<input type="radio" name="securityLevel" value="비공개">
						<label class="radio-label">비공개</label>
					</span>
				</td>
			</tr>
			<tr style="height:24%">
				<td class="table-left">첨부파일</td>
				<td class="table-right">
					<input type="file" id="selectedFile" multiple="multiple"> <input type="button" value="첨부" id="attachFile"> <br/><br/>
					<select id="fileListBox" size="6" style="width: 90%">
						<c:forEach var="list" items="${fileList}">
							<option value="${list.DOCUMENT_FILE_ID }">${list.ORIGINAL_FILE_NAME }</option>
						</c:forEach>
					</select> <input type="button" value="삭제" id="fileDelete">
				</td>
			</tr>
			<tr style="height:8%">
				<td colspan="2" style="text-align: right; width: 100%">
					<input type="button" value="등록" onclick="sendData()">
					<input type="button" value="초기화" onclick="dataClear()">
				</td>
			</tr>
		</tbody>
	</table>
</div>
		</div>
	</div>
	<div class="dim-layer" id="layerDiv">
    <div class="dimBg"></div>
    <div id="layer" class="pop-layer">
        <div class="pop-container">
            <div class="pop-conts">
				<div id="layer-conts">
					<ul id="treeMenu">
						<li>
							<button type="button" class="holer"></button> <a class="open box-color" style="color: black;">JCONE</a>
							<ul id="subMenu1">
								<c:forEach var="gList" items="${groupList}">
									<li class="forderPut group_${gList.group_id}">
										<button type="button" class="open"></button> <a class="open box-color" style="color: black;">${gList.group_name}</a>
										<ul id="subMenu1-1">
										<c:forEach var="gFolderList" items="${groupInFolderList}">
											<c:if test="${gFolderList.parent_folder_id ==  gList.group_id}">
													<li class="forderPut"><a onclick="setDocumentFolderValue(this)" class="box-color" style="color: black;" id="${gFolderList.folder_id }">${gFolderList.folder_name }</a></li>
											</c:if>
										</c:forEach>
										</ul>
									</li>
								</c:forEach>
							</ul>
						</li>
					</ul>
				</div>
                <div class="btn-r">
                    <a href="#" class="btn-layerClose">Close</a>
                </div>
                <!--// content-->
            </div>
        </div>
    </div>
</div>	
<div class="dim-layer" id="layer2Div">
    <div class="dimBg"></div>
    <div id="layer2" class="pop-layer">
        <div class="pop-container">
            <div class="pop-conts">
				<div id="layer-conts2"></div>
                <div class="btn-r">
                    <a href="#" class="btn-layerClose">Close</a>
                </div>
                <!--// content-->
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
var form_Data = new FormData();
var file_arr = new Array();
var vs = '${documentBean.VERSION}';
var di = '${documentBean.DOCUMENT_ID}';

$('#datePicker').click(function(){
	layer_popup('#layer2');
    var id = $(this).attr('id');
    
    console.log(id);
	
    console.log('달력');
	$('#layer-conts2').datepicker({
		onSelect:function(dateText, inst) {
			$('#registerDate').val(dateText);
        	console.log(dateText);
            $('#layer2Div').fadeOut();
    	}
	});
	
});

function dataClear() {
	
	$('#bindTitle').val('');
	$('#documentTitle').val('');
	$('#documentDescrption').val('');
	$('#registerDate').val('');
	$('#documentFolderId').val('');
	$('#documentFolder').val('');
	$('#selectedFile').val('');
	$('#fileListBox option').remove();
	$('input[name=securityLevel]:checked').prop('checked',false);
	
}

$('#documentBox').click(function(){
	layer_popup('#layer');
	
    console.log('문서함');
	
});

$('#attachFile').click(function() {
	
	var name = $('#selectedFile')[0];
	var fileList = $('#fileListBox')[0];
	
	for(var i = 0; i < name.files.length; i++){
		
		var fileObj = name.files[i];
		var fileName = fileObj.name;
		var option = document.createElement("option");
		option.text = fileName;
		fileList.add(option);
		
		file_arr.push(fileObj);
	}

});

$('#fileDelete').click(function() {
	
	var selectedText = $('#fileListBox option:selected').text();
	$('#fileListBox option:selected').remove();

	for(var key in file_arr){
		if(file_arr[key].name == selectedText){
			file_arr.splice(key,1);
		}
	}
	
});

function sendData() {
	
	var fileSize = file_arr.length;
	
	var bTitle = $('#bindTitle').val();
	var dTitle = $('#documentTitle').val();
	var dDescrption = $('#documentDescrption').val();
	var rDate = $('#registerDate').val();
	var checkedVal = '';
	var fId = $('#documentFolderId').val();
	
	$("input[name=securityLevel]:checked").each(function() {
		  checkedVal = $(this).val();
	});

	if(bTitle == ''){
		alert('철제목을 입력해주세요');
		return;
	}

	if(dTitle == ''){
		alert('문서제목을 입력해주세요');
		return;
	}

	if(dDescrption == ''){
		alert('문서요약을 입력해주세요');
		return;
	}

	if(rDate == ''){
		alert('작성일자을 선택해주세요');
		return;
	}
	
	if(fId == ''){
		alert('문서함을 선택해주세요');
		return;
	}

	if(checkedVal == ''){
		alert('보안등급을 선택해주세요');
		return;
	}
	
	if(fileSize > 0){
		for(var i = 0; i < fileSize; i++){
			form_Data.append('multiPartFiles',file_arr[i]);
		}
		
		
		form_Data.append('BIND_TITLE', $('#bindTitle').val());
		form_Data.append('DOCUMENT_TITLE', $('#documentTitle').val());
		form_Data.append('DOCUMENT_DESCRIPTION', $('#documentDescrption').val());
		form_Data.append('REGISTER_DATE', $('#registerDate').val());
		form_Data.append('SECURITY_GRADE', checkedVal);
		form_Data.append('FOLDER_ID', $('#documentFolderId').val());
		form_Data.append('DOCUMENT_ID', di);
		form_Data.append('VERSION', vs);
		form_Data.append('updateType', 'edit');
		
		$.ajax({
	        url: '/jcone/uploadDocument',
	        enctype: 'multipart/form-data',
	        processData: false, 
	        contentType: false,
	        cache: false,
	        data: form_Data,
	        type: 'POST',
	        success: function(result){
	        	console.log(result);
	        	if(result == 'success'){
		            alert("업로드 성공!!");
		            movePage('/jcone/main');
	        	}else{
	        		alert(result);
		        	form_Data = new FormData();
	        	}
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
	}else{
		alert('파일을 선택해주세요');
	}
}

$('.radio-label').click(function() {
	var radioBtn = $(this).parents().children("input[type=radio]");
	var checked = radioBtn.prop("checked");

 	if(!checked){
		radioBtn.prop("checked", true);
	}
});


$(document).ready(function() {
	var gradeTxt = '${documentBean.SECURITY_GRADE}';
	
	$("input[name=securityLevel]").each(function() {
		var txt = $(this).val();
		
		if(txt == gradeTxt){
			$(this).prop("checked", true);
		}
	});
})


</script>
<script src="resources/js/common.js"></script>
</body>
</html>