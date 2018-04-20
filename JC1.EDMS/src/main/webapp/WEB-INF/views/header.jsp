<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
	<div id="first_field" class="first_blank"></div>
    <div id="logo" onclick="movePage('/jcone/main')" style="cursor: pointer;">
 		<div id="logoDiv"><img src="/jcone/resources/images/first_top.png"></div>
	</div>
	<div id="menu">
		<div class="menu-row">
			<div class="menu-col" style="text-align: center;" onclick="documentInsert(this)">
				<div style="float: none !important; text-align: center; height: 80px">
					<img src="/jcone/resources/images/insert.png" style="margin-top: 10px;">
				</div>
				<div style="float: none !important; text-align: center; margin-top: -67px;">문서등록</div>
			</div>
			<div class="menu-col" style="text-align: center;" onclick="ready_ing()">
				<div style="float: none !important; text-align: center; height: 80px">
					<img src="/jcone/resources/images/document-search.png" style="margin-top: 10px;">
				</div>
				<div style="float: none !important; text-align: center; margin-top: -67px;">문서검색</div>
			</div>
			<div class="menu-col" style="text-align: center;" onclick="myFunction()">
				<div style="float: none !important; text-align: center; height: 80px">
					<img class="dropbtn" src="/jcone/resources/images/network.png" style="margin-top: 10px;">
				</div>
				<div class="dropbtn" style="float: none !important; text-align: center; margin-top: -67px;">팀문서함</div>
				<div id="myDropdown" class="dropdown-content">
					<a href="javascript:teamFolder('teamFolderInsert');">팀문서함 등록</a>
					<a href="javascript:teamFolder('teamFolderManager','1');">팀문서함 관리</a>
				</div>
			</div>
			<div class="menu-col" style="text-align: center;" onclick="favoriteList(1);">
				<div style="float: none !important; text-align: center; height: 80px">
					<img src="/jcone/resources/images/file.png" style="margin-top: 10px;">
				</div>
				<div style="float: none !important; text-align: center; margin-top: -67px;">즐겨찾기</div>
			</div>
			<div class="menu-col" style="text-align: center;" onclick="ready_ing()">
				<div style="float: none !important; text-align: center; height: 80px">
					<img src="/jcone/resources/images/resume.png" style="margin-top: 10px;">
				</div>
				<div style="float: none !important; text-align: center; margin-top: -67px;">개인문서</div>
			</div>
			<div class="menu-col" style="text-align: center;" onclick="logout()">
				<div style="float: none !important; text-align: center; height: 80px">
					<img src="/jcone/resources/images/logout.png" style="margin-top: 10px;">
				</div>
				<div style="float: none !important; text-align: center; margin-top: -67px;">로그아웃</div>
			</div>
		</div>
	</div>
</div>