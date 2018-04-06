<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/table2.css">
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
					<input type="text" style="width: 95%" id="bindTitle">	
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">문서제목</td>
				<td class="table-right">
					<input type="text" style="width: 95%" id="documentTitle">
				</td>
			</tr>
			<tr style="height:12%">
				<td class="table-left">문서요약</td>
				<td class="table-right">
					<textarea style="height: 85%;resize: none; width: 95%; overflow-y:scroll " rows="3" id="documentDescrption"></textarea>
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">작성일자</td>
				<td class="table-right">
					<input type="text" id="registerDate" style="width: 40%;" readonly="readonly">
					<input type="button" class="layer-btn" value="달력" id="datePicker">
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">문서함</td>
				<td class="table-right">
					<input type="text" style="width: 40%;" readonly="readonly" id="documentFolder">
					<input type="text" style="display: none;" id="documentFolderId">
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
					<select id="fileListBox" size="6" style="width: 90%"></select> <input type="button" value="삭제" id="fileDelete">
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

<script src="resources/js/jquery-ui.js"></script>
<script src="resources/js/datepicker-ko.js"></script>
<script type="text/javascript">
console.log('누를때마다 호출');

var form_Data = new FormData();
var file_arr = new Array();

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

</script>
