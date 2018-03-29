<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="resources/css/table.css">
<link rel="stylesheet" href="resources/css/jquery-ui.css">
<script src="resources/js/jquery-ui.js"></script>
<script src="resources/js/datepicker-ko.js"></script>
<div id="table-background">
	
	<div id="table-main">
		<div class="table-title"> ※ 문서등록</div>
		<div class="table-view">
			<table id="table">
				<tbody>
					<tr style="height:8%">
						<td class="table-left">철제목</td>
						<td class="table-right">
							<input type="text" style="width: 95%">	
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">문서제목</td>
						<td class="table-right">
							<input type="text" style="width: 95%">
						</td>
					</tr>
					<tr style="height:12%">
						<td class="table-left">문서요약</td>
						<td class="table-right">
							<textarea style="height: 85%;resize: none; width: 95%; overflow-y:scroll " rows="3"></textarea>
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">작성일자</td>
						<td class="table-right">
							<input type="text" id="writeDate" style="width: 40%;">
							<input type="button" class="layer-btn" value="달력" id="datePicker">
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">기타항목</td>
						<td class="table-right">
							기타 1: <input type="text" style="width: 80%">
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">문서보관위치</td>
						<td class="table-right">
							<input type="text" style="width: 95%">
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
							<input type="button" value="등록">
							<input type="button" value="초기화">
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</div>

<script type="text/javascript">
console.log('누를때마다 호출');

var form_Data = new FormData();

$('.layer-btn').click(function(){
	layer_popup('#layer2');
	$('#layer-conts').empty();
    var id = $(this).attr('id');
    
    console.log(id);
	
    if(id == 'datePicker'){
        console.log('달력');
    	$('#layer-conts').datepicker({
    		onSelect:function(dateText, inst) {
    			$('#writeDate').val(dateText);
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
		
		console.log(fileObj);
		
		form_Data.append('files[]', fileObj);
	}

});

$('#fileDelete').click(function() {
	
	console.log($('#fileListBox option:selected').text());
	
	for (var key in form_Data) {
		console.log(key);
	}
	
	//form_Data.delete('');
});



function sendData() {
	$.ajax({
        url: '/uploadDocument',
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