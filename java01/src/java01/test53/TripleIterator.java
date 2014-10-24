package java01.test53;

import java01.test53.step05.Iterator;

public class TripleIterator extends Iterator{
	int cursor;
	
	@Override
	public boolean hasNext() {
		//상속받은 변수에 접근할 수 없다? 왜?
		// list는 접근범위가 default이기 때문이다. => 즉, default는 같은 클래스멤버이거나  패키지 내에서만 접근가능하다.
		// 해결책! => 자식 클래스도 접근 가능하도록 접근 범위를 조정해야 한다.
		// 어떻게? => 접근 범위를 default에서 protected로 바꾼다
		// 왜?    => protected는 클래스멤버, 패키지멤버, 자식 클래스 접근 가능!(다른 패키지여도)
		if (cursor < list.length) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String next() {
		String value = list[cursor];
		cursor += 3;
		return value;
	}
	
	

}
