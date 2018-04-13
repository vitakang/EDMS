<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JC1 EDMS</title>
<!-- 공통 -->
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/css/jquery-ui.css">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/table2.css">
<link rel="stylesheet" href="resources/css/common.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
#layer-conts3 table tr th, #layer-conts3 table tr td {
	color: #000;
}

#layer-conts3 table {
	width: 100%;
	border: 3px double #ccc;
	padding: 10px;
	margin-bottom: 10px;
	background-color: #f7f7f7;
}

#layer-conts3-title {
	color: #000;
	margin: 0px auto;
	width: 100%;
	text-align: center;
	margin-bottom: 20px;
	font-size: 20px;
	font-weight: 700;
}

#layer-conts3 table input {
	padding: 5px;
}
</style>
</head>
<body>
	<%@ include file="../header.jsp"%>
	<%@ include file="../leftBar.jsp"%>
	<div id="main">
		<div id="right_Content">
			<div class="table-wrapper">
				<div class="table-title">
					<h2>※ 즐겨찾기</h2>
				</div>
				<div class="table-filter">
					<div class="table-search-list" id="search-title">
						<img src="resources/images/search.png" style="vertical-align: middle; height: 100%">
						&nbsp;&nbsp;
						<span style="vertical-align: middle; font-weight: bold;">SEARCH</span>
					</div>
					<div class="table-search-list" id="search-select">
						<span style="vertical-align: middle;">
							<select name="searchType">
								<option value="favoriteName">문서이름</option>
								<option value="favoriteDescription">문서설명</option>
							</select>
						</span>
					</div>
					<div class="table-search-list" id="search-input">
						<input type="text" name="searchText" placeholder="검색어를 입력해주세요" style="width: 100%; vertical-align: middle;">
					</div>
					<div class="table-search-list" id="search-button">
						<input type="button" value="검색" style="vertical-align: middle;" id="search-button-input">
					</div>
				</div>
				<table class="table table-striped table-hover">
					<thead>
						<tr style="text-align: center;">
							<th class="listTable1"><input type="checkbox" id="allCheck" onclick="allCheckMananger()"></th>
							<th class="listTable5">문서이름</th>
							<th class="listTable4">문서설명</th>
							<th class="listTable2">생성자</th>
							<th class="listTable6">생성일자</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="list" items="${favoriteList }">
							<tr style="text-align: center;">
								<td class="listTable1 listCheck" id="${list.OBJECT_ID}">
									<input type="checkbox" name="document-chk">
								</td>
								<td class="listTable5">${list.FAVORITE_NAME}</td>
								<td class="listTable4">${list.FAVORITE_DESCRIPTION}</td>
								<td class="listTable2">${list.USER_NAME}</td>
								<td class="listTable6">
									<fmt:formatDate value="${list.ROW_INPUT_DATE}" pattern="yyyy-MM-dd" />
								</td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(favoriteList) < 10}">
							<c:forEach begin="0" end="${10 - fn:length(favoriteList)}">
								<tr style="text-align: center;">
									<td class="listTable1"></td>
									<td class="listTable5"></td>
									<td class="listTable4"></td>
									<td class="listTable2"></td>
									<td class="listTable6"></td>
								</tr>
							</c:forEach>

						</c:if>
						<tr>
							<td colspan="2" style="text-align: left;">검색건수 : ${fn:length(favoriteList)}</td>
							<td colspan="4" style="text-align: center;">
								<ul class="pagination">
									<li class="page-item disabled">
										<a href="javascript:previousPage();">Previous</a>
									</li>
									<c:forEach begin="${startingPage}" end="${endPage}" var="i">
										<c:if test="${i == nowPage }">
											<li class="page-item active">
												<a class="page-link" style="cursor: pointer;">${i}</a>
											</li>
										</c:if>
										<c:if test="${i != nowPage }">
											<li class="page-item">
												<a href="javascript:changePage(${i});" class="page-link">${i}</a>
											</li>
										</c:if>
									</c:forEach>
									<li class="page-item">
										<a href="javascript:nextPage();" class="page-link">Next</a>
									</li>
								</ul>
							</td>
						</tr>
						<tr>
							<td colspan="6" style="text-align: right;">
								<input type="button" value="수정" id="favorite-mod">
								<input type="button" value="삭제" id="favorite-del">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="footer"></div>
	<div class="dim-layer" id="layer3Div">
		<div class="dimBg"></div>
		<div id="layer3" class="pop-layer">
			<div class="pop-container">
				<div class="pop-conts">
					<div id="layer-conts3" style="min-height: 390px;"></div>
					<div class="btn-r">
						<a href="#" class="btn-layerSave" id="favorite-save">Save</a>
						<a href="#" class="btn-layerClose">Close</a>
					</div>
					<!--// content-->
				</div>
			</div>
		</div>
	</div>
	<div class="wrap-loading display-none">
		<div>
			<img src="resources/images/loading.gif" />
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script src="resources/js/jquery-ui.js"></script>
	<script src="resources/js/favorite.js?ver=1.0"></script>
	<script src="resources/js/common.js"></script>
</body>
<script>
var listNowPage = ${nowPage};
var listMaxPage = ${maxPage};
</script>
</html>

