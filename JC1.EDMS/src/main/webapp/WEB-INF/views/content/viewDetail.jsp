<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<link rel="stylesheet" href="resources/css/table.css">
<div id="table-background">
	
	<div id="table-main">
		<div class="table-title"> ※ 문서상세정보</div>
		<div class="main-line"></div>
		<div class="table-view">
			<table id="table">
				<tbody>
					<tr style="height:8%">
						<td class="table-left">철제목</td>
						<td class="table-right">
						
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">문서제목</td>
						<td class="table-right">
						
						</td>
					</tr>
					<tr style="height:12%">
						<td class="table-left">문서요약</td>
						<td class="table-right">
							<textarea style="height: 100%"></textarea>
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">작성일자</td>
						<td class="table-right">
						
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">기타항목</td>
						<td class="table-right">
							기타 1: 
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">문서보관위치</td>
						<td class="table-right">
						
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">문서함</td>
						<td class="table-right">
						
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">보안등급</td>
						<td class="table-right">
							<label class="radio-label">전체공개</label>
						</td>
					</tr>
					<tr style="height:24%">
						<td class="table-left">첨부파일</td>
						<td class="table-right">
							<select id="fileListBox" size="6" style="width: 90%"></select> <input type="button" value="보기" id="">
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">관련정보</td>
						<td class="table-right">
							<span>변경이력</span>&nbsp;
							<span>열람정보</span>
						</td>
					</tr>
					<tr style="height:8%">
						<td class="table-left">문서설정</td>
						<td class="table-right">
							<span>권한설정</span>&nbsp;
							<span>공유함등록</span>
						</td>
					</tr>
					<tr style="height:8%">
						<td colspan="2" style="text-align: right; width: 100%">
							<input type="button" value="삭제">
							<input type="button" value="수정">
							<input type="button" value="목록" onclick="documentlist()"><a style="display: none;" id="d-title">${title}</a>
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
	var arr = {title:'${title}',sub:'sub'};
	var url = 'listDocument';
	console.log(arr);
	console.log(url);
	changeContent(url,arr);
}

</script>