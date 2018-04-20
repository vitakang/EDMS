<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JC1 EDMS</title>
<link rel="stylesheet" href="resources/css/main.css">
<link rel="stylesheet" href="resources/css/jquery-ui.css">
<link rel="stylesheet" href="resources/css/common.css">
</head>
<body>
	<%@ include file="header.jsp" %> 
	<div id="main">
		<div class="nav">
			<div id="div_navigation" class="div_navigation">
				<img src="/jcone/resources/images/home.png" style="margin-top: 12px;">
			</div>
		</div>
		<div style="height: 30px;">&nbsp;</div>
		<%@ include file="leftBar.jsp" %>
		<div id="right_Content">
			<div>전자문서 관리 시스템</div>
		</div>
	</div>
	<div id="footer"></div>
	
<div class="dim-layer" id="layerDiv">
    <div class="dimBg"></div>
    <div id="layer" class="pop-layer">
        <div class="pop-container">
            <div class="pop-conts">
				<div id="layer-conts">
					<ul id="treeMenu">
						<li>
							<button type="button" class="holer"></button> <a class="open box-color" style="color: black;">JCONE</a>
							<ul id="subMenu1">
								<c:forEach var="gList" items="${groupList}">
									<li class="forderPut group_${gList.group_id}">
										<button type="button" class="open"></button> <a class="open box-color" style="color: black;">${gList.group_name}</a>
										<ul id="subMenu1-1">
										<c:forEach var="gFolderList" items="${groupInFolderList}">
											<c:if test="${gFolderList.parent_folder_id ==  gList.group_id}">
													<li class="forderPut"><a onclick="setDocumentFolderValue(this)" class="box-color" style="color: black;" id="${gFolderList.folder_id }">${gFolderList.folder_name }</a></li>
											</c:if>
										</c:forEach>
										</ul>
									</li>
								</c:forEach>
							</ul>
						</li>
					</ul>
				</div>
                <div class="btn-r">
                    <a href="#" class="btn-layerClose">Close</a>
                </div>
                <!--// content-->
            </div>
        </div>
    </div>
</div>	
<div class="dim-layer" id="layer2Div">
    <div class="dimBg"></div>
    <div id="layer2" class="pop-layer">
        <div class="pop-container">
            <div class="pop-conts">
				<div id="layer-conts2"></div>
                <div class="btn-r">
                    <a href="#" class="btn-layerClose">Close</a>
                </div>
                <!--// content-->
            </div>
        </div>
    </div>
</div>
<div class="dim-layer" id="layer3Div">
    <div class="dimBg"></div>
    <div id="layer3" class="pop-layer">
        <div class="pop-container">
            <div class="pop-conts">
				<div id="layer-conts3" style="min-height: 390px;">
				</div>
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
    <div><img src="resources/images/loading.gif" /></div>
</div>  

<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="resources/js/jquery-ui.js"></script>
<script src="resources/js/common.js"></script>
<!-- <script type="text/javascript">

function goPage(path, params, method) {
	method = method || "post";
	
	var form = document.createElement("form");
	form.setAttribute("method",method);
	form.setAttribute("action",path);
	
	for(var key in params){
		var hiddenFiled = document.createElement("input");
		hiddenFiled.setAttribute("type","hidden");
		hiddenFiled.setAttribute("name",key);
		hiddenFiled.setAttribute('value',params[key]);
		
		form.appendChild(hiddenFiled);
		
	}
	
	document.body.appendChild(form);
	form.submit();

}

// 로그아웃
function logout() {
	$.ajax({
		type : "POST",
		url : 'logout.jcg',
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		data : {
			"id" : '${id}'
		},
		success : function(data) {
			// alert(data);
			if (data == "S") goPage("login/view.jcg");
			else {
				alert("로그아웃 실패" + data);
			}
		},
		error : function(xhr, textStatus, error) {
			if (xhr.status == "901") {
				// location.href = "loginView.jcg?COMMAND=NOT_LOGIN";
				goPage("login/view.jcg", {'COMMAND' : 'NOT_LOGIN'});
			}
		},
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("AJAX", "true");
		}
	});
}

//문서 목록
function documentContent(obj,page) {
	var arr = {FOLDER_NAME:obj.text,FOLDER_ID:obj.id,page:page};
	var url = 'listDocument';
	console.log(arr);
	console.log(url);
	changeContent(url,arr);
}

function setDocumentFolderValue(obj) {
	var id = obj.id;
	var text = obj.text;
	$('#documentFolder').val(text);
	$('#documentFolderId').val(id);

    $('#layerDiv').fadeOut();
}

// 상세보기
function viewDetail(documentId,folderName) {
	var url = 'viewDetail';
	var arr = {DOCUMENT_ID:documentId,FOLDER_NAME:folderName};
	changeContent(url,arr);
}

function documentInsert(obj) {
	var url = 'insertDocument';
	changeContent(url);
}

function favoriteList() {
	var url = "favoriteList";
	changeContent(url, null);
}

function settingContent() {
	var url = 'setting';
	changeContent(url,null);
}

function teamFolder(url,page,searchText,searchType) {
	var groupId = ${myGroup};
	var arr = {GROUP_ID:groupId, page:page, searchText:searchText, searchType:searchType};
	changeContent(url,arr);
}

function changeContent(url,arr) {
	console.log(arr);
	console.log(url);
    /* $('#right_Content').load(url,arr); */
    
    $.ajax({
		type : "POST",
		url : url,
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		data : arr,
//		processData : false,
		success : function(data) {
			var contentEl = document.getElementById("right_Content");
			contentEl.innerHTML = data;
		},
		error : function(xhr, textStatus, error) {
			if (xhr.status == "901") {
				 location.href = "loginView";
//				goPage("loginView.jcg", {'COMMAND' : 'NOT_LOGIN'});
			}
		},
		beforeSend : function(xmlHttpRequest) {
			xmlHttpRequest.setRequestHeader("AJAX", "true");
		}
	});
}

function movePage(url) {
	location.href = url;
}
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

$(document).ready(function() {

	$('ul.tabs li').click(function() {
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#" + tab_id).addClass('current');
	})
	
	var childObj = $('#subMenu1').children('li');
	var myGroupId = 'group_' + ${myGroup}; 
	
	for(var i = 0; i < childObj.length; i++){
		var cl = childObj[i].className;
		
		if(cl.indexOf(myGroupId) > 0){
			continue;
		}

		childObj[i].children[0].classList.remove('open');
		childObj[i].children[0].classList.add('holer');
		childObj[i].children[0].classList.add('end');

		childObj[i].children[1].classList.remove('open');
		childObj[i].children[1].classList.add('holer');
	}
	

	var opener = $("button.open");
	var opener2 = $("a.open");
	var nested = $("button.open").parent().find("li");
	var nestedCont = $("li.forderPut > ul > li.forderPut").parent();
	var that;
	
	var tree = {
		init : function() {
			console.log(nestedCont);
			nestedCont.hide();
			//$("li:last-child").addClass("end");			
			$("button.open").each(function() {
				$(opener).click(function(target) {
					tree.click(this);
				});
				return false;
			})
			$("a.open").each(function() {
				$(opener2).click(function(target) {
					tree.click(this);
				});
				return false;
			})
		},
		click : function(_tar) {
			that = _tar;
			if($(that)[0].tagName == 'BUTTON'){
				$(that).next().next().show();
			}else if($(that)[0].tagName == 'A'){
				$(that).next().show();
			}
			
			$(that).prev().toggleClass("close");
			$(that).toggleClass("close");
			if (!$(that).hasClass("close")) {

				if($(that)[0].tagName == 'BUTTON'){
					$(that).next().next().hide();
				}else if($(that)[0].tagName == 'A'){
					$(that).next().hide();
				}
			}
		}
	}
	tree.init();

})
    
function layer_popup(el){

    var $el = $(el);        //레이어의 id를 $el 변수에 저장
    var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수
    
    if(el == '#layer'){
    	$('#layer-conts').css('height','400px');
    	$('#layer-conts').css('overflow-y','scroll');
    } else if ( el == "#layer3") {
    	$('#layer3').css('height','500px');
    	$('#layer3').css('overflow-y','scroll');
    }
    

    isDim ? $(el+'Div').fadeIn() : $el.fadeIn();

    var $elWidth = ~~($el.outerWidth()),
        $elHeight = ~~($el.outerHeight()),
        docWidth = $(document).width(),
        docHeight = $(document).height();

    // 화면의 중앙에 레이어를 띄운다.
    if ($elHeight < docHeight || $elWidth < docWidth) {
        $el.css({
            marginTop: -$elHeight /2,
            marginLeft: -$elWidth/2
        })
    } else {
        $el.css({top: 0, left: 0});
    }

    $el.find('a.btn-layerClose').click(function(){
        isDim ? $(el+'Div').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
        return false;
    });

    $('.layer .dimBg').click(function(){
        $(el+'Div').fadeOut();
        return false;
    });

}

function ready_ing() {
	alert('준비중입니다.');
}

//Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

$('#treeMenu').css('display','block');
</script> -->
</body>
</html>

