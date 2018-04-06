function favoriteSave(command) {
// var formData = document.getElementById("favorite-data");
	var formData = $("#favorite-data").serializeArray();
	
	$.ajax({
		url : "addFavorite",
		data : formData,
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		async : false,
		success : function(reponseData) {
			console.log(reponseData);
			if (reponseData == 'S') {
				alert('성공');
				$("a.btn-layerClose").trigger("click");
				if (command == 'update') {
					favoriteList();
				}
			} else {
				alert('실패');
			}
		},
		error : function(e) {
			alert(e);
		}
	});
}

function popupFavorite(command) {
	var btnEl = document.getElementById("favorite-save");
	var layerContsEl = document.getElementById('layer-conts3');
	var formEl = document.createElement("form");
	var divTitle = document.createElement("div");
	var checkElArr = document.getElementsByName('document-chk');
	var checkElArrLength = checkElArr.length;
	var cnt = 0;
	
	if (command == 'insert') {
		btnEl.setAttribute("onclick","favoriteSave('insert')");
	} else if (command == 'update') {
		btnEl.setAttribute("onclick","favoriteSave('update')");
	}
	
	while (layerContsEl.hasChildNodes()) {
		layerContsEl.removeChild(layerContsEl.firstChild);
	}
	
	formEl.setAttribute("id", "favorite-data");
	divTitle.setAttribute("id", "layer-conts3-title");
	divTitle.innerText = "즐겨찾기 추가";
	formEl.appendChild(divTitle);
	layerContsEl.appendChild(formEl);
	
	for (var i = 0; i < checkElArrLength; i++) {
		if (checkElArr[i].checked) {
			makeHtmlElements(checkElArr[i], command);
			cnt++;
		}
	}
	
	if (cnt > 0) layer_popup('#layer3');
	else {
		alert("문서를 선택해주세요.");
	}
}

function makeHtmlElements(chkEl, command) {
	var titleValue = chkEl.offsetParent.nextElementSibling.nextElementSibling.innerText;
	var descriptionValue = titleValue;
	if (command == 'update') {
		titleValue = chkEl.offsetParent.nextElementSibling.innerText;
		descriptionValue = chkEl.offsetParent.nextElementSibling.nextElementSibling.innerText
	}
	var docId = chkEl.offsetParent.id;
	var favoriteFormEl = document.getElementById('favorite-data');
	var tableEl = document.createElement('table');
	var firstTrEl = document.createElement("tr");
	var firstTdEl = document.createElement("td");
	var firstThEl = document.createElement("th");
	var firstInputEl = document.createElement("input");
	var secondTrEl = document.createElement("tr");
	var secondTdEl = document.createElement("td");
	var secondThEl = document.createElement("th");
	var secondInputEl = document.createElement("input");
	var docIdInputEl = document.createElement("input");
	docIdInputEl.setAttribute("type", "hidden");
	docIdInputEl.setAttribute("value", docId);
	docIdInputEl.setAttribute("name", "docId");
	firstInputEl.setAttribute("type", "text");
	firstInputEl.setAttribute("value", titleValue);
	firstInputEl.setAttribute("name", "favoriteName")
	firstInputEl.setAttribute("readonly", "true");
	firstTdEl.appendChild(firstInputEl);
	firstTdEl.appendChild(docIdInputEl);
	firstThEl.innerText = "이름";
	firstTrEl.appendChild(firstThEl);
	firstTrEl.appendChild(firstTdEl);

	secondThEl.innerText = "설명";
	secondInputEl.setAttribute("type", "text");
	secondInputEl.setAttribute("value", descriptionValue);
	secondInputEl.setAttribute("name", "favoriteDescription")
	secondTdEl.appendChild(secondInputEl);
	secondTrEl.appendChild(secondThEl);
	secondTrEl.appendChild(secondTdEl);

	tableEl.appendChild(firstTrEl);
	tableEl.appendChild(secondTrEl);
	favoriteFormEl.appendChild(tableEl);
}

function favoriteDelete(deleteArrStr) {
	$.ajax({
		url : "deleteFavorite",
		data : {
			deleteStr : deleteArrStr
		},
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		async : false,
		success : function (responseData) {
			console.log(responseData);
			if (responseData == 'S'){
				alert("성공");
				favoriteList();
			}
			else alert("실패");
		}, 
		error : function (xhr, status, e) {
			
		}
	});
}

var favorite = {
		popup : function () {
			console.log('popup');
			popupFavorite('update');
		},
		mod : function() {
			console.log('mod');
			favoriteSave('update');
		},
		del : function(){
			console.log('del');
			var checkElArr = document.getElementsByName('document-chk');
			var checkElArrLength = checkElArr.length;
			var checkedEl = new Array(); 
			var arrStr = "";
			
			for (var i = 0, j = 0 ; i < checkElArrLength; i++) {
				if (checkElArr[i].checked) {
//					checkedEl.push(checkElArr[i].offsetParent.id);
//					var obj = new Object();
//					obj.delName = checkElArr[i].offsetParent.id;
//					checkedEl.push(obj);
					if (j > 0 && j != i) arrStr += "|";
					j++;
					arrStr += checkElArr[i].offsetParent.id;
				}
				
			}
			
			favoriteDelete(arrStr);
		},
		init : function () {
			var popupEl = document.getElementById("favorite-mod");
			var delEl = document.getElementById("favorite-del");
			
			popupEl.addEventListener("click", this.popup, false);
			delEl.addEventListener("click", this.del, false);
		}
}
