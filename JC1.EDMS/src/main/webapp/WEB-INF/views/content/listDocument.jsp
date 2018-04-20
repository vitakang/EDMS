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
				<img src="/jcone/resources/images/home.png" style="margin-top: 12px;"> > ${folderName}
			</div>
		</div>
		<div style="height: 30px;">&nbsp;</div>
		<%@ include file="../leftBar.jsp"%>
		<div id="right_Content">
<div class="table-wrapper">
	<div class="table-title">
		<h2>
			 ※ ${folderName} 문서목록
		</h2>
	</div>
	<div class="table-filter">
		<div class="table-search-list" id="search-title" style="text-align: center;line-height: 2">
			SEARCH
		</div>
		<div class="table-search-list" id="search-select">
			<span style="vertical-align: middle;">
				<select id="searchOp">
					<option value="">등록구분</option>
					<option value="bindTitle">철제목</option>
					<option value="documentTitle">문서제목</option>
					<option value="userId">작성자</option>
					<!-- <option value="">작성일자</option> -->
				</select>
			</span>
		</div>
		<div class="table-search-list" id="search-input">
			<input type="text" id="searchTxt" placeholder="검색어를 입력해주세요" style="width: 100%; vertical-align: middle;">
		</div>
		<div class="table-search-list" id="search-button">
			<input type="button" value="검색" style="vertical-align: middle;" onclick="searchDocument()">
		</div>
	</div>
	<table class="table table-striped table-hover">
		<thead>
			<tr style="text-align: center;">
				<th class="listTable1"><input type="checkbox" id="allCheck"></th>
				<th class="listTable2">등록구분</th>
				<th class="listTable3">철제목</th>
				<th class="listTable4">문서제목</th>
				<th class="listTable5">작성자</th>
				<th class="listTable6">작성일자</th>
			</tr>
		</thead>
		<tbody>
		
			<c:if test="${fn:length(d_list) < 10}">
				<c:forEach var="list" items="${d_list }">
					<tr style="text-align: center;">
						<td class="listTable1 listCheck" id="${list.DOCUMENT_ID}"><input type="checkbox" name="document-chk"></td>
						<td class="listTable2">${list.GROUP_NAME}</td>
						<td class="listTable3">${list.BIND_TITLE}</td>
						<td class="listTable4" style="cursor: pointer; " onclick="viewDetail('${list.DOCUMENT_ID}','${folderName}','${folderId}','${nowPage}')">${list.DOCUMENT_TITLE}</td>
						<td class="listTable5">${list.USER_ID}</td>
						<td class="listTable6">${list.REGISTER_DATE}</td>
					</tr>	
				</c:forEach>
				<c:forEach begin="0" end="${10 - fn:length(d_list)}">
					<tr style="text-align: center;">
						<td class="listTable1" ></td>
						<td class="listTable2"></td>
						<td class="listTable3"></td>
						<td class="listTable4"></td>
						<td class="listTable5"></td>
						<td class="listTable6"></td>
					</tr>	
				</c:forEach>
				
			</c:if>
			<c:if test="${fn:length(d_list) >= 10}">
				<c:forEach var="list" items="${d_list }">
					<tr style="text-align: center;">
						<td class="listTable1 listCheck" id="${list.DOCUMENT_ID}"><input type="checkbox" name="document-chk"></td>
						<td class="listTable2">${list.GROUP_NAME}</td>
						<td class="listTable3">${list.BIND_TITLE}</td>
						<td class="listTable4" style="cursor: pointer; " onclick="viewDetail('${list.DOCUMENT_ID}','${folderName}','${folderId}','${nowPage}')">${list.DOCUMENT_TITLE}</td>
						<td class="listTable5">${list.USER_ID}</td>
						<td class="listTable6">${list.REGISTER_DATE}</td>
					</tr>	
				</c:forEach>
			</c:if>
			<tr>
				<td colspan="2" style="text-align: left;">검색건수 :  ${maxDocument}</td>
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
				<td colspan="6" style="text-align: right;"><input type="button" value="즐겨찾기등록" onclick="popupFavorite('insert')"></td>
			</tr>
		</tbody>
	</table>
</div>

		</div>
	</div>
<script type="text/javascript">

var listNowPage = ${nowPage};
var listMaxPage = ${maxPage};
var listFolderName = '${folderName}';
var listFolderId = '${folderId}';

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
	var arr = {FOLDER_NAME:listFolderName, FOLDER_ID:listFolderId, page:page, searchText:searchText, searchType:searchType};
	var url = 'listDocument';
	changeContent(url,arr);
}

function searchDocument() {
	var txt = $('#searchTxt').val();
	var selectVal = $("#searchOp option:selected").val();
	
	loadListPage('1', txt, selectVal);
}

</script>
<script src="resources/js/common.js"></script>
</body>
</html>

