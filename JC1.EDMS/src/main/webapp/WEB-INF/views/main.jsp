<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JC1 EDMS</title>
<link rel="stylesheet" href="resources/css/main.css">
</head>
<body>
	<div id="header">
	    <div id="logo">
	 
		</div>
		<div id="menu">
			<div class="menu-row">
				<div class="menu-col" style="text-align: center;">
					<button onclick="documentInsert(this)" class="dropbtn">문서등록</button>
				</div>
				<div class="menu-col" style="text-align: center;">
					<button onclick="myFunction()" class="dropbtn">문서검색</button>
					<!-- <div id="myDropdown" class="dropdown-content">
						<a href="#">Link 1</a>
						<a href="#">Link 2</a>
						<a href="#">Link 3</a>
					</div> -->
				</div>
				<div class="menu-col" style="text-align: center;">
					<button onclick="myFunction()" class="dropbtn">팀문서함</button>
				</div>
				<div class="menu-col" style="text-align: center;">
					<button onclick="myFunction()" class="dropbtn">즐겨찾기</button>
				</div>
				<div class="menu-col" style="text-align: center;">
					<button onclick="myFunction()" class="dropbtn">개인문서</button>
				</div>
				<div class="menu-col" style="text-align: center;">
					<button onclick="myFunction()" class="dropbtn">로그아웃</button>
				</div>
			</div>
		</div>
	</div>
	<div id="main">
		<div id="left_Bar">
			<div id="left-menu" >
				<div style="height: 35px;">
					<div class="dd" style="color: white;">사용자</div>
					<div class="dd" style="color: white;">누구</div>
					<div class="dd" onclick="settingContent()" style="color: white;">설정</div>
				</div>
				<div style="height:50px;">
					<ul class="tabs">
						<li class="tab-link current" data-tab="tab-1" style="color: white;">회사</li>
						<li class="tab-link" data-tab="tab-2" style="color: white;">소속팀</li>
						<li class="tab-link" data-tab="tab-3" style="color: white;">기타</li>
					</ul>
				</div>
				<div id="tab-1" class="tab-content current">
					<ul id="treeMenu" style="display: none;">
						<li>
							<button type="button" class="open"></button> <a class="open box-color">first</a>
							<ul id="subMenu1">
								<li>
									<button type="button" class="open"></button> <a class="open box-color">first-first</a>
									<ul id="subMenu1-1">
										<li><a onclick="documentContent(this)" class="box-color">first-first-first</a></li>
										<li><a onclick="documentContent(this)" class="box-color">first-first-second</a></li>
									</ul>
								</li>
								<li><a href="#" class="box-color">first-second</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<div id="tab-2" class="tab-content">
					todo 
				</div>
				<div id="tab-3" class="tab-content">
					todo
				</div>
			</div>
		</div>
		<div id="right_Content">
			<div>전자문서 관리 시스템</div>
		</div>
	</div>
	<div id="footer"></div>
	
<div class="dim-layer">
    <div class="dimBg"></div>
    <div id="layer2" class="pop-layer">
        <div class="pop-container">
            <div class="pop-conts">
				<div id="layer-conts"></div>
                <div class="btn-r">
                    <a href="#" class="btn-layerClose">Close</a>
                </div>
                <!--// content-->
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script type="text/javascript">

var opener = $("a.open");
var nested = $("a.open").parent().find("li");
var nestedCont = $("li > ul > li").parent();
var that;

var tree = {
	init : function() {
		nestedCont.hide();
		$("li:last-child").addClass("end");
		$("a.open").each(function() {
			$(opener).click(function(target) {
				tree.click(this);
			});
			return false;
		})
	},
	click : function(_tar) {
		that = _tar;
		$(that).next().show();
		$(that).prev().toggleClass("close");
		$(that).toggleClass("close");
		if (!$(that).hasClass("close")) {
			$(that).next().hide();
		}
	}
}
tree.init();

function documentContent(obj) {
	var arr = {title:obj.text,sub:'sub'};
	var url = 'listDocument';
	console.log(arr);
	console.log(url);
	changeContent(url,arr);
}

function viewDetail(idx,title) {
	var url = 'viewDetail';
	var arr = {idx:idx,title:title};
	changeContent(url,arr);
}

function documentInsert(obj) {
	var url = 'insertDocument';
	changeContent(url);
}

function settingContent() {
	var url = 'setting';
	changeContent(url,null);
}

function changeContent(url,arr) {
	console.log(arr);
	console.log(url);
    $('#right_Content').load(url,arr);
}

function myFunction() {
    //document.getElementById("myDropdown").classList.toggle("show");
}

$(document).ready(function() {

	$('ul.tabs li').click(function() {
		var tab_id = $(this).attr('data-tab');

		$('ul.tabs li').removeClass('current');
		$('.tab-content').removeClass('current');

		$(this).addClass('current');
		$("#" + tab_id).addClass('current');
	})

})
    
function layer_popup(el){

    var $el = $(el);        //레이어의 id를 $el 변수에 저장
    var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

    isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

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
        isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
        return false;
    });

    $('.layer .dimBg').click(function(){
        $('.dim-layer').fadeOut();
        return false;
    });

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
</script>
</body>
</html>

