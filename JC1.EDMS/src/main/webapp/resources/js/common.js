function goPage(path, params, method) {
	method = method || "post";
	
	var form = document.createElement("form");
	form.setAttribute("method",method);
	form.setAttribute("action",path);
	form.acceptCharset = "utf-8";

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
			if (data == "S") goPage("loginView.jcg");
			else {
				alert("로그아웃 실패" + data);
			}
		},
		error : function(xhr, textStatus, error) {
			if (xhr.status == "901") {
				// location.href = "loginView.jcg?COMMAND=NOT_LOGIN";
				goPage("loginView.jcg", {'COMMAND' : 'NOT_LOGIN'});
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
function viewDetail(documentId,folderName,folderId,page) {
	var url = 'viewDetail';
	var arr = {DOCUMENT_ID:documentId,FOLDER_NAME:folderName,FOLDER_ID:folderId,page:page};
	changeContent(url,arr);
}

function documentInsert(obj) {
	var url = 'insertDocument';
	changeContent(url);
}

function favoriteList(page) {
	var url = "favoriteList";
	changeContent(url, {page : page});
}

function settingContent() {
	var url = 'setting';
	changeContent(url,null);
}

function teamFolder(url,page,searchText,searchType) {
	var groupId = globalGroupId;
	var arr = {GROUP_ID:groupId, page:page, searchText:searchText, searchType:searchType};
	changeContent(url,arr);
}

function changeContent(url,arr) {
	console.log(arr);
	console.log(url);
	goPage(url, arr, "POST");
    /* $('#right_Content').load(url,arr); */
    
//    $.ajax({
//		type : "POST",
//		url : url,
//		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
//		data : arr,
////		processData : false,
//		success : function(data) {
//			var contentEl = document.getElementById("right_Content");
//			contentEl.innerHTML = data;
//		},
//		error : function(xhr, textStatus, error) {
//			if (xhr.status == "901") {
//				 location.href = "loginView";
////				goPage("loginView.jcg", {'COMMAND' : 'NOT_LOGIN'});
//			}
//		},
//		beforeSend : function(xmlHttpRequest) {
//			xmlHttpRequest.setRequestHeader("AJAX", "true");
//		}
//	});
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
	var myGroupId = 'group_' + globalGroupId; 
	
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