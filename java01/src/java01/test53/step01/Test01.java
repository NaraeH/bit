/*
 1단계: 하드 코딩되어있는 데이터를 순서대로 출력하기
 */
package java01.test53.step01;

public class Test01 {

	public static void main(String[] args) {
		String[] data = { "홍길동", "임꺽정", "안중근", "정다미", "애교머신", "김에벌레" };

		Iterator iterator = new Iterator(data);

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

	}

}
