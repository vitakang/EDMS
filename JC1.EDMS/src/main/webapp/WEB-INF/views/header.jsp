<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
	    <div id="logo" onclick="movePage('/jcone/main')" style="cursor: pointer;">
	 		<div id="logoDiv">EDMS</div>
		</div>
		<div id="menu">
			<div class="menu-row">
			<div class="menu-col" style="text-align: center;" onclick="documentInsert(this)">문서등록<b class="b-img"></b></div>
			<div class="menu-col" style="text-align: center;" onclick="ready_ing()">문서검색<b class="b-img"></b></div>
			<div class="menu-col dropbtn" style="text-align: center;" onclick="myFunction()">팀문서함<b class="b-img"></b>
					<div id="myDropdown" class="dropdown-content">
						<a href="javascript:teamFolder('teamFolderInsert');">팀문서함 등록</a>
						<a href="javascript:teamFolder('teamFolderManager','1');">팀문서함 관리</a>
					</div></div>
			<div class="menu-col" style="text-align: center;" onclick="favoriteList(1);">즐겨찾기<b class="b-img"></b></div>
			<div class="menu-col" style="text-align: center;" onclick="ready_ing()">개인문서<b class="b-img"></b></div>
			<div class="menu-col" style="text-align: center;" onclick="logout()">로그아웃<b class="b-img"></b></div>
				<!-- <div class="menu-col" style="text-align: center;">
					<button onclick="documentInsert(this)" class="dropbtn">문서등록</button>
				</div>
				<div class="menu-col" style="text-align: center;">
					<button class="dropbtn" onclick="ready_ing()">문서검색</button>
				</div>
				<div class="menu-col" style="text-align: center;">
					<button onclick="myFunction()" class="dropbtn">팀문서함</button>
					<div id="myDropdown" class="dropdown-content">
						<a href="javascript:teamFolder('teamFolderInsert');">팀문서함 등록</a>
						<a href="javascript:teamFolder('teamFolderManager','1');">팀문서함 관리</a>
					</div>
				</div>
				<div class="menu-col" style="text-align: center;">
					<button class="dropbtn" onclick="favoriteList(1);">즐겨찾기</button>
				</div>
				<div class="menu-col" style="text-align: center;">
					<button class="dropbtn" onclick="ready_ing()">개인문서</button>
				</div>
				<div class="menu-col" style="text-align: center;">
					<button class="dropbtn" onclick="logout()">로그아웃</button>
				</div> -->
			</div>
		</div>
	</div>