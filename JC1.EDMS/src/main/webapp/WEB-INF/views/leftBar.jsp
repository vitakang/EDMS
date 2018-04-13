<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="left_Bar">
	<div id="left-menu">
		<div style="height: 35px; padding-top: 10px">
			<div class="leftMenu-top" style="color: white;">사용자</div>
			<div class="leftMenu-top" style="color: white;">${userId}</div>
			<div class="leftMenu-top" onclick="settingContent()" style="color: white;">
				<img src="resources/images/gear.png">
			</div>
		</div>
		<div style="height: 50px;">
			<ul class="tabs">
				<li class="tab-link current" data-tab="tab-1">회사</li>
				<li class="tab-link" data-tab="tab-2">소속팀</li>
				<li class="tab-link" data-tab="tab-3">기타</li>
			</ul>
		</div>
		<div id="tab-1" class="tab-content current">
			<ul class="treeMenu" style="display: none;">
				<li>
					<button type="button" class="holer"></button>
					<a class="open box-color">JCONE</a>
					<ul id="subMenu1">
						<c:forEach var="gList" items="${groupList}">
							<li class="forderPut group_${gList.group_id}">
								<button type="button" class="open"></button>
								<a class="open box-color">${gList.group_name}</a>
								<ul id="subMenu1-1">
									<c:forEach var="gFolderList" items="${groupInFolderList}">
										<c:if test="${gFolderList.parent_folder_id ==  gList.group_id}">
											<li class="forderPut">
												<a onclick="documentContent(this,'1')" class="box-color" id="${gFolderList.folder_id }">${gFolderList.folder_name }</a>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
		<div id="tab-2" class="tab-content">
			<ul class="treeMenu" style="display: none;">
				<c:forEach var="gList" items="${groupList}">
				<c:if test="${gList.group_id == myGroup}">
				<li>
					<button type="button" class="holer"></button>
					<a class="open box-color">${gList.group_name}</a>
					<ul id="subMenu1">
						<c:forEach var="gFolderList" items="${groupInFolderList}">
							<c:if test="${gFolderList.parent_folder_id ==  gList.group_id}">
								<li class="forderPut">
									<a onclick="documentContent(this,'1')" class="box-color" id="${gFolderList.folder_id }">${gFolderList.folder_name }</a>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</li>
				</c:if> 
				</c:forEach>
			</ul>
		</div>
		<div id="tab-3" class="tab-content">todo</div>
	</div>
</div>
<script>
var globalGroupId = ${myGroup};
</script>
