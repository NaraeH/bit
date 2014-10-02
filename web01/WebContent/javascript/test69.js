"use strict";

changeState("create");

// 버튼과 input의 보이는지 여부
function changeState(state) {

	var stateMap = {
		create : "none",
		detail : "none"
	}

	stateMap[state] = '';

	$(".detail").css("display", stateMap.detail);
	$(".create").css("display", stateMap.create);

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
	
	$(".dateRow").remove();
	
/*	for (var i = tbody.children.length - 1; i > 0; i--) {
		// console.log(tbody.children[i]);
		tbody.removeChild(tbody.children[i]);
	}*/

	for ( var i in boardList) {
		$("<tr>")
		.attr("class", "dateRow")
		.append($("<td>").html(i))
		.append($("<td>")
				.append($('<a>')
						.attr('bno', new String(i))
						.attr('href', '#')
						.click(loadBoardDetail)
								.html(boardList[i].title))).append(
				$("<td>").text(boardList[i].writer)).append(
				$("<td>").html($.toYYYYMMDD(boardList[i].date))).append(
				$("<td>").html(boardList[i].count))
				.appendTo(boardTable);
	}
}

function loadBoardDetail(event) {

	// 원래하던기능 정지 (그 함수가 끝나고 하고자 했던 기능 하지 말아라)
	event.preventDefault();
	var board = boardList[$(this).attr('bno')];

	changeState("detail");

	$("#no").val($(this).attr('bno'));
	$("#title").val(board.title);
	$("#content").val(board.content);
	$("#writer").val(board.writer);
	$("#password").val(board.password);
	$("#date").val($.toYYYYMMDD(board.date));
}

//boardList의 내부안에 있는 객체는 객체의 값이 아닌 객체들의 주소값이 들어가 있음
var boardList = [];

$("#btnAdd").click(function(event) {

			var board = new Board($("#title").val(), 
					$("#content").val(),
					$("#writer").val(), $("#password").val());

			boardList.push(board);
			resetForm();
			refreshBoardList();
		});

$("#btnDelete").click(function(event) {
	var no = $("#no").val();

	boardList.splice(no, 1); //"no"번째 부터 "1"개를 지워라
	refreshBoardList();
	resetForm();

});

$("#btnChange").click(function(evnet) {

	var no = $("#no").val();
	var board = boardList[no];

	board.title = $("#title").val();
	board.content = $("#content").val();

	refreshBoardList();
});

$('#btnCancel').click(function(event) {
	changeState("create");
});
