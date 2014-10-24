/*
3단계: 역순으로 데이터를 꺼내주는 Iterator 만들기
=> ReverseIterator
*/
package java01.test53.step03;

public class Iterator {
	String[] list;
	int cursor;

	public Iterator(String[] list) {
		this.list = list;
	}

	public boolean hasNext() {
		if (cursor < list.length) {
			return true;
		} else {
			return false;
		}
	}

	public String next() {
		return list[cursor++];
	}
}

