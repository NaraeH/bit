/*
3단계: 역순으로 데이터를 꺼내주는 Iterator 만들기
=> ReverseIterator
*/
package java01.test53.step05;

public class EvenIterator extends Iterator{
	int cursor;

	public EvenIterator(){}

	@Override
	public boolean hasNext() {
		if (cursor < list.length) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String next() {
		String value = list[cursor];
		cursor += 2;
		return value;
	}
}

