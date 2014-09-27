//자주 사용하는 함수는 별도의 파일에 분리해둔다.
"use strict";

//document.getElementById => $
function bit(value) {
	var element = null;

	if (value instanceof Element) {
		element = value;
	} else if (value.charAt(0) == "#") {
		element = document.getElementById(value.substring(1)); //#은 들어가면 안되므로 => 0번째 것을 제외한 끝까지
	} else if (value.charAt(0) == "<") {
		element = document.createElement(value.replace(/<|>/g, ''));
	}

	//html을 호출할 때 그 객체가 this 즉, 여기서는 element
	//element.text => 라는 새로운 메소드 만듬
	element.text = function(value) {
		this.textContent = value;
		return this;
	}

	element.html = function(value) {
		this.innerHTML = value;
		return this;
	}

	element.append = function(child) {
		this.appendChild(child);
		return this;
	}

	element.appendTo = function(parent) {
		parent.appendChild(this);
		return this;
	}

	element.attr = function(name, value) {
		this.setAttribute(name, value);
		return this;
	}
	element.click = function(listener) {
		if (listener) {
			this.onclick = listener;
		} else {
			var event = new MouseEvent('click', {
			// "view" : window,
			// "bubbles" : true,
			// "cancelable" : true
			});
			this.dispatchEvent(event);
		}
		return this;
	}

	element.val = function(value) {
		this.value = value;
		return value;
	}

	element.css = function(name, value) {
		this.style[name] = value;
		return value;
	}
	return element;

}
var $ = bit;

//함수도 객체이기 때문에 다른 저장소로 사용될 수 있다.
//bit.toYYYYMMDD: 글로벌함수가 아닌 bit에 소속되어있는 함수
//=> 라이브러리화
//혹시나 함수이름이 다른 변수의 이름과 겹쳐 값을 덮어쓰면서 프로그램에서 오류나는 것을 방지(하나의 프로그램 내에서는 이름만 같다면 같은 변수로 인식하므로)
bit.toYYYYMMDD = function(date) {
	return (date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date
			.getDate());
};
