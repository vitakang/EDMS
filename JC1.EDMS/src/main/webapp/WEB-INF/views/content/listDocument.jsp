<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<link rel="stylesheet" href="resources/css/table.css">
<div id="table-background">
	
	<div id="table-main">
		<div class="table-title"> ※ ${table_title} 문서목록</div>
		<div class="table-search">
			<div class="table-search-list" id="search-title">검색</div>
			<div class="table-search-list" id="search-select">
				<select>
					<option value="">등록구분</option>
					<option value="">철제목</option>
					<option value="">문서제목</option>
					<option value="">작성일</option>
					<option value="">작성일자</option>
				</select>
			</div>
			<div class="table-search-list" id="search-input">
				<input type="text" placeholder="검색어를 입력해주세요" style="width: 100%">
			</div>
			<div class="table-search-list" id="search-button">
				<input type="button" value="검색">
			</div>
		</div>
		<div class="table-view">
			<table id="table" style="border: 1px solid bla;">
				<tr style="text-align: center;">
					<td class="listTable1"><input type="checkbox" id="allCheck"></td>
					<td class="listTable2">등록구분</td>
					<td class="listTable3">철제목</td>
					<td class="listTable4">문서제목</td>
					<td class="listTable5">작성자</td>
					<td class="listTable6">작성일자</td>
				</tr>
				<c:forEach var="list" items="${d_list }">
					<tr style="text-align: center;">
						<td class="listTable1 listCheck" id="${list.idx}"><input type="checkbox"></td>
						<td class="listTable2">${list.group}</td>
						<td class="listTable3">${list.subTitle}</td>
						<td class="listTable4" style="cursor: pointer; " onclick="viewDetail(${list.idx},'${title}')">${list.title}</td>
						<td class="listTable5">${list.writer}</td>
						<td class="listTable6">${list.writeDate}</td>
					</tr>	
				</c:forEach>
				<tr>
					<td colspan="2" style="text-align: left;">검색건수 :  ${fn:length(d_list)}</td>
					<td colspan="4" style="text-align: center;">1</td>
				</tr>
				<tr>
					<td colspan="6" style="text-align: right;"><input type="button" value="즐겨찾기등록"></td>
				</tr>
			</table>
		</div>
	</div>

</div>

<script type="text/javascript">
console.log('누를때마다 호출');

$('#allCheck').click(function() {
	var checked = $(this).prop("checked");
	var listCnt = $('.listCheck').length;
	//console.log($('.listCheck').length);
	console.log(checked);
	
	//$('.listCheck').prop("checked", $(this).is(':checked'));
	$('#1').prop("checked", checked);
 	
});

</script>