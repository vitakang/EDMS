<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/table2.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div class="table-wrapper">
	<div class="table-title">
				<h2>
					 ※ ${table_title} 문서목록
				</h2>
	</div>
	<div class="table-filter">
		<div class="row">
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
			<!--  <div class="col-sm-3">
				<div class="show-entries">
					<span>Show</span> <select class="form-control">
						<option>5</option>
						<option>10</option>
						<option>15</option>
						<option>20</option>
					</select> <span>entries</span>
				</div>
			</div>
			<div class="col-sm-9">-->
				<!-- <button type="button" class="btn btn-primary">
					<i class="fa fa-search"></i>
				</button>
				<div class="filter-group">
					<label>Name</label> <input type="text" class="form-control">
				</div>
				<div class="filter-group">
					<label>Location</label> <select class="form-control">
						<option>All</option>
						<option>Berlin</option>
						<option>London</option>
						<option>Madrid</option>
						<option>New York</option>
						<option>Paris</option>
					</select>
				</div>
				<div class="filter-group">
					<label>Status</label> <select class="form-control">
						<option>Any</option>
						<option>Delivered</option>
						<option>Shipped</option>
						<option>Pending</option>
						<option>Cancelled</option>
					</select>
				</div>
				<span class="filter-icon"><i class="fa fa-filter"></i></span> -->
			<!-- </div> -->
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
			<tr>
				<td class="listTable1">1</td>
				<td class="listTable2"> Michael Holz</td>
				<td class="listTable3">London</td>
				<td class="listTable4">Jun 15, 2017</td>
				<td class="listTable5"><span class="status text-success">&bull;</span> Delivered</td>
				<td class="listTable6">$254</td>
			</tr>
			<tr>
				<td class="listTable1">2</td>
				<td class="listTable2">Paula Wilson</td>
				<td class="listTable3">Madrid</td>
				<td class="listTable4">Jun 21, 2017</td>
				<td class="listTable5"><span class="status text-info">&bull;</span> Shipped</td>
				<td class="listTable6">$1,260</td>
			</tr>
			<tr>
				<td class="listTable1">3</td>
				<td class="listTable2">Antonio Moreno</td>
				<td class="listTable3">Berlin</td>
				<td class="listTable4">Jul 04, 2017</td>
				<td class="listTable5"><span class="status text-danger">&bull;</span> Cancelled</td>
				<td class="listTable6">$350</td>
			</tr>
			<tr>
				<td class="listTable1">4</td>
				<td class="listTable2">Mary Saveley</td>
				<td class="listTable3">New York</td>
				<td class="listTable4">Jul 16, 2017</td>
				<td class="listTable5"><span class="status text-warning">&bull;</span> Pending</td>
				<td class="listTable6">$1,572</td>
			</tr>
			<tr>
				<td class="listTable1">5</td>
				<td class="listTable2"> Martin Sommer</td>
				<td class="listTable3">Paris</td>
				<td class="listTable4">Aug 04, 2017</td>
				<td class="listTable5"><span class="status text-success">&bull;</span> Delivered</td>
				<td class="listTable6">$580</td>
			</tr>
		</tbody>
	</table>
	<div class="clearfix">
		<div class="hint-text">
			Showing <b>5</b> out of <b>25</b> entries
		</div>
		<ul class="pagination">
<!-- 			<li class="page-item disabled"><a href="#">Previous</a></li>
			<li class="page-item"><a href="#" class="page-link">1</a></li>
			<li class="page-item"><a href="#" class="page-link">2</a></li>
			<li class="page-item"><a href="#" class="page-link">3</a></li>
			<li class="page-item active"><a href="#" class="page-link">4</a></li>
			<li class="page-item"><a href="#" class="page-link">5</a></li>
			<li class="page-item"><a href="#" class="page-link">6</a></li>
			<li class="page-item"><a href="#" class="page-link">7</a></li>
			<li class="page-item"><a href="#" class="page-link">Next</a></li> -->
		</ul>
	</div>
</div>
