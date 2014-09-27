"use strict";

changeState("create");

// 버튼과 input의 보이는지 여부
function changeState(state) {

	var stateMap = {
		create : "none",
		detail : "none"
	}

	stateMap[state] = '';

	var detailClass = document.querySelectorAll('.detail');
	var createClass = document.querySelectorAll('.create');

	for (var i = 0; i < detailClass.length; i++) {
		$(detailClass[i]).css("display", stateMap.detail);
	}

	for (var i = 0; i < createClass.length; i++) {
		$(createClass[i]).css("dispaly", stateMap.create);
	}

}

// 게시글을 저장하는 객체 생성자
function Board(titleValue, contentValue, writerValue, passwordValue) {
	this.title = titleValue;
	this.content = contentValue;
	this.writer = writerValue;
	this.password = passwordValue;
	this.date = new Date();
	this.count = 0;
}

// 입력폼 초기화
function resetForm() {
	$("#btnCancel").click();
}

function refreshBoardList() {
	var boardTable = $("#boardTable");

	var tbody = boardTable.lastElementChild;

	for (var i = tbody.children.length - 1; i > 0; i--) {
		// console.log(tbody.children[i]);
		tbody.removeChild(tbody.children[i]);
	}

	for ( var i in boardList) {
		$("<tr>").append($("<td>").html(i))
		.append($("<td>").append($('<a>')
				.attr('bno', new String(i))
				.attr('href', '#')
				.click(loadBoardDetail)
				.html(boardList[i].title)))
		.append($("<td>")
				.text(boardList[i].writer))
		.append($("<td>")
				.html($.toYYYYMMDD(boardList[i].date)))
		.append($("<td>")
				.html(boardList[i].count))
		.appendTo(tbody);
	}
}

function loadBoardDetail(event) {

	// 원래하던기능 정지 (그 함수가 끝나고 하고자 했던 기능 하지 말아라)
	event.preventDefault();
	var board = boardList[this.getAttribute('bno')];

	changeState("detail");

	$("#no").val(this.getAttribute('bno'));
	$("#title").val(board.title);
	$("#content").val(board.content);
	$("#writer").val(board.writer);
	$("#password").val(board.password);
	$("#date").val($.toYYYYMMDD(board.date));
}

var boardList = [];

$("#btnAdd").click(function(event) {

	var board = new Board($("#title").value, $("#content").value,
			$("#writer").value, $("#password").value);

	boardList.push(board);
	resetForm();
	refreshBoardList();
});

$('#btnCancel').click(function(event) {
	changeState("create");
});
