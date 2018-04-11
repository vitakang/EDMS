<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			 ※ 팀문서함 관리
		</h2>
	</div>
	<div class="table-filter">
		<div class="table-search-list" id="search-title"><img src="resources/images/search.png" style="vertical-align: middle; height: 100%">&nbsp;&nbsp;<span style="vertical-align: middle; font-weight: bold;">SEARCH</span></div>
		<div class="table-search-list" id="search-select">
			<span style="vertical-align: middle;"><select>
				<option value="folderName">문서함이름</option>
				<option value="folderDes">문서함설명</option>
				<option value="userId">생성자</option>
			</select></span>
		</div>
		<div class="table-search-list" id="search-input">
			<input type="text" placeholder="검색어를 입력해주세요" style="width: 100%; vertical-align: middle;">
		</div>
		<div class="table-search-list" id="search-button">
			<input type="button" value="검색" style="vertical-align: middle;">
		</div>
	</div>
	<table class="table table-striped table-hover">
		<thead>
			<tr style="text-align: center;">
				<th class="listTable1"><input type="checkbox" id="allCheck" onclick="allCheckMananger()"></th>
				<th class="listTable2">레벨</th>
				<th class="listTable3">문서함이름</th>
				<th class="listTable4">문서함설명</th>
				<th class="listTable5">생성자</th>
				<th class="listTable6">생성일자</th>
			</tr>
		</thead>
		<tbody>
		
			<c:if test="${fn:length(folderList) < 10}">
				<c:forEach var="list" items="${folderList }">
					<tr style="text-align: center;">
						<td class="listTable1 listCheck"><input type="checkbox" id="${list.FOLDER_ID}"></td>
						<td class="listTable2">${list.FOLDER_LEVEL}</td>
						<td class="listTable3">${list.FOLDER_NAME}</td>
						<td class="listTable4">${list.FOLDER_DESCRIPTION}</td>
						<td class="listTable5">${list.USER_ID}</td>
						<td class="listTable6"><fmt:formatDate value="${list.ROW_INPUT_DATE}" pattern="yyyy-MM-dd"/></td>
					</tr>	
				</c:forEach>
				<c:forEach begin="0" end="${10 - fn:length(d_list)}">
					<tr style="text-align: center;">
						<td class="listTable1"></td>
						<td class="listTable2"></td>
						<td class="listTable3"></td>
						<td class="listTable4"></td>
						<td class="listTable5"></td>
						<td class="listTable6"></td>
					</tr>	
				</c:forEach>
				
			</c:if>
			<c:if test="${fn:length(folderList) > 10}">
				<c:forEach var="list" items="${folderList }">
					<tr style="text-align: center;">
						<td class="listTable1 listCheck"><input type="checkbox" id="${list.FOLDER_ID}"></td>
						<td class="listTable2">${list.FOLDER_LEVEL}</td>
						<td class="listTable3">${list.FOLDER_NAME}</td>
						<td class="listTable4">${list.FOLDER_DESCRIPTION}</td>
						<td class="listTable5">${list.USER_ID}</td>
						<td class="listTable6"><fmt:formatDate value="${list.ROW_INPUT_DATE}" pattern="yyyy-MM-dd"/></td>
					</tr>	
				</c:forEach>
			</c:if>
			<tr>
				<td colspan="2" style="text-align: left;">검색건수 :  ${maxFolder}</td>
				<td colspan="4" style="text-align: center;">
					<ul class="pagination">
						<li class="page-item disabled"><a href="javascript:previousPage();">Previous</a></li>
						<c:forEach begin="${startingPage}" end="${endPage}" var="i">
							<c:if test="${i == nowPage }">
								<li class="page-item active"><a class="page-link" style="cursor: pointer;">${i}</a></li>
							</c:if>
							<c:if test="${i != nowPage }">
								<li class="page-item"><a href="javascript:changePage(${i});" class="page-link">${i}</a></li>
							</c:if>
						</c:forEach>
						<li class="page-item"><a href="javascript:nextPage();" class="page-link">Next</a></li>
					</ul>
				</td>
			</tr>
			<tr>
				<td colspan="6" style="text-align: right;">
					<!-- <input type="button" value="수정"> -->
					<input type="button" value="삭제" onclick="deleteTeamFolder()">
				</td>
			</tr>
		</tbody>
	</table>
</div>
		</div>
	</div>
<script type="text/javascript">

var deleteArr = new Array();
var listNowPage = ${nowPage};
var listMaxPage = ${maxPage};

function previousPage() {
	if(listNowPage < 2){
		alert('이전페이지가 없습니다.');
	}else{
		console.log(listNowPage-1);
		loadListPage(listNowPage-1);
	}
}

function nextPage() {
	if(listNowPage >= listMaxPage){
		alert('다음페이지가 없습니다.');
	}else{
		//console.log(listNowPage+1);
		loadListPage(listNowPage+1);
	}
}

function changePage(p) {
	loadListPage(p);
}

function loadListPage(page,searchText,searchType) {
	var arr = {page:page, searchText:searchText, searchType:searchType};
	var url = 'teamFolderManager';
	changeContent(url,arr);
}

function searchDocument() {
	var txt = $('#searchTxt').val();
	var selectVal = $("#searchOp option:selected").val();
	
	loadListPage('1', txt, selectVal);
}

function allCheckMananger() {
	if($('#allCheck').prop('checked')){
		$('.listCheck').children('input[type=checkbox]').prop('checked',true);
	}else{
		$('.listCheck').children('input[type=checkbox]').prop('checked',false);
	}
}

function deleteTeamFolder() {
	var checked = $('.listCheck').children('input[type=checkbox]:checked');
	
	if(checked.length < 1){
		alert('삭제할 폴더를 체크해 주세요');
	}else{
		for(var i = 0; i < checked.length; i++){
			
			//console.log(checked[i]);
			//console.log(checked[i].id);
			deleteArr.push({'FOLDER_IDS':checked[i].id});
		}
		var json_arr = JSON.stringify(deleteArr);
		
		//console.log(json_arr)
		
		$.ajax({
		    url: '/jcone/teamFolderDelete',
		    contentType: 'application/x-www-form-urlencoded; charset=UTF-8;',
		    data: {jsonData:json_arr},
		    dataType:'text',
	        cache: false,
		    type: 'POST',
		    success: function(result){
		    	if('success' == result){
		    		alert('삭제완료');
		    		teamFolder('teamFolderManager','1');
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
		    	console.log(e);
		    },timeout:100000 //"응답제한시간 ms"
		});
	}
	
}

function folderReset() {
	$('#folderName').val('');
	$('#folderDescription').val('');
}

</script>
<script src="resources/js/common.js"></script>
</body>
</html>
