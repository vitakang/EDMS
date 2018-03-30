<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/table2.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- <style type="text/css">
	.table-wrapper {
        background: #fff;
        padding: 20px 25px;
        margin: 30px auto;
		border-radius: 3px;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
    }
	.table-wrapper .btn {
		float: right;
		color: #333;
    	background-color: #fff;
		border-radius: 3px;
		border: none;
		outline: none !important;
		margin-left: 10px;
	}
	.table-wrapper .btn:hover {
        color: #333;
		background: #f2f2f2;
	}
	.table-wrapper .btn.btn-primary {
		color: #fff;
		background: #03A9F4;
	}
	.table-wrapper .btn.btn-primary:hover {
		background: #03a3e7;
	}
	.table-title .btn {		
		font-size: 13px;
		border: none;
	}
	.table-title .btn i {
		float: left;
		font-size: 21px;
		margin-right: 5px;
	}
	.table-title .btn span {
		float: left;
		margin-top: 2px;
	}
	.table-title {
		color: #fff;
		background: #4b5366;		
		padding: 16px 25px;
		margin: -20px -25px 10px;
		border-radius: 3px 3px 0 0;
    }
    .table-title h2 {
		margin: 5px 0 0;
		font-size: 24px;
	}
	.show-entries select.form-control {        
        width: 60px;
		margin: 0 5px;
	}
	.table-filter .filter-group {
        float: right;
		margin-left: 15px;
    }
	.table-filter input, .table-filter select {
		height: 34px;
		border-radius: 3px;
		border-color: #ddd;
        box-shadow: none;
	}
	.table-filter {
		padding: 5px 0 15px;
		border-bottom: 1px solid #e9e9e9;
		margin-bottom: 5px;
	}
	.table-filter .btn {
		height: 34px;
	}
	.table-filter label {
		font-weight: normal;
		margin-left: 10px;
	}
	.table-filter select, .table-filter input {
		display: inline-block;
		margin-left: 5px;
	}
	.table-filter input {
		width: 200px;
		display: inline-block;
	}
	.filter-group select.form-control {
		width: 110px;
	}
	.filter-icon {
		float: right;
		margin-top: 7px;
	}
	.filter-icon i {
		font-size: 18px;
		opacity: 0.7;
	}	
    table.table tr th, table.table tr td {
        border-color: #e9e9e9;
		padding: 12px 15px;
		vertical-align: middle;
    }
	table.table tr th:first-child {
		width: 60px;
	}
	table.table tr th:last-child {
		width: 80px;
	}
    table.table-striped tbody tr:nth-of-type(odd) {
    	background-color: #fcfcfc;
	}
	table.table-striped.table-hover tbody tr:hover {
		background: #f5f5f5;
	}
    table.table th i {
        font-size: 13px;
        margin: 0 5px;
        cursor: pointer;
    }	
	table.table td a {
		font-weight: bold;
		color: #566787;
		display: inline-block;
		text-decoration: none;
	}
	table.table td a:hover {
		color: #2196F3;
	}
	table.table td a.view {        
		width: 30px;
		height: 30px;
		color: #2196F3;
		border: 2px solid;
		border-radius: 30px;
		text-align: center;
    }
    table.table td a.view i {
        font-size: 22px;
		margin: 2px 0 0 1px;
    }   
	table.table .avatar {
		border-radius: 50%;
		vertical-align: middle;
		margin-right: 10px;
	}
</style> -->
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
					<input type="text" id="registerDate" style="width: 40%;" >
					<input type="button" class="layer-btn" value="달력" id="datePicker">
				</td>
			</tr>
			<tr style="height:8%">
				<td class="table-left">문서함</td>
				<td class="table-right">
					<input type="text" style="width: 40%;">
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
					<input type="button" value="초기화">
				</td>
			</tr>
		</tbody>
	</table>
</div>

<script type="text/javascript">
console.log('누를때마다 호출');

var form_Data = new FormData();
var file_arr = new Array();

$('.layer-btn').click(function(){
	layer_popup('#layer2');
    var id = $(this).attr('id');
    
    console.log(id);
	
    if(id == 'datePicker'){
        console.log('달력');
    	$('#layer-conts').datepicker({
    		onSelect:function(dateText, inst) {
    			$('#registerDate').val(dateText);
            	console.log(dateText);
                $('.dim-layer').fadeOut();
        	}
    	});	
    }else if(id == 'documentBox'){
        console.log('문서함');
    }
	
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
		//console.log(fileObj);
		
		//form_Data.append('files[]', fileObj);
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
	
	for(var i = 0; i < file_arr.length; i++){
		form_Data.append('multiPartFiles',file_arr[i]);
	}
	
	var checkedVal = '';
	$("input[name=securityLevel]:checked").each(function() {
		  checkedVal = $(this).val();
	});
	
	form_Data.append('BIND_TITLE', $('#bindTitle').val());
	form_Data.append('DOCUMENT_TITLE', $('#documentTitle').val());
	form_Data.append('DOCUMENT_DESCRIPTION', $('#documentDescrption').val());
	form_Data.append('REGISTER_DATE', $('#registerDate').val());
	form_Data.append('SECURITY_GRADE', checkedVal);
	
	$.ajax({
        url: '/jcone/uploadDocument',
        enctype: 'multipart/form-data',
        processData: false, 
        contentType: false,
        cache: false,
        data: form_Data,
        type: 'POST',
        success: function(result){
            alert("업로드 성공!!");
        }
    });
}

$('.radio-label').click(function() {
	var radioBtn = $(this).parents().children("input[type=radio]");
	var checked = radioBtn.prop("checked");

 	if(!checked){
		radioBtn.prop("checked", true);
	}
});

</script>
