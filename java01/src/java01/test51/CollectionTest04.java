package java01.test51;

class MyLinkedList1 {

	class Bucket {

		Object value; // 값(인스턴스주소)을 저장하기 위한 변수 선언
		Bucket next; // 다음 버킷의 주소 저장: 링크저장
	}

	Bucket start; // 처음 시작되는 버킷
	Bucket end; // 마지막 끝나는 버킷
	int size;

	public MyLinkedList1() {
		start = new Bucket(); // 처음 시작 위해 새로운 Bucket생성
		end = start; // 처음 생성되었을 시는 시작과 끝은 같다
	}

	public int size() {
		return this.size;
	}

	public int add(Object value) {
		end.value = value; // 현재의 마지막(즉 비어있는 버킷)에 값을 넣어라.
		end.next = new Bucket(); // end.next = 새로운 빈 버킷의 주소를 가리킴 => add를 한다면 그
									// 다음번의 add를 하기 위한 Bucket(공간)을 하나 생성해라
		end = end.next; // end = 그것의 주소(즉 주소)

		return ++size; // 현재의 사이즈 리턴
	}

	public Object getList(int index) {
		Bucket cursor = start;
		if ((index < 0) || (index >= this.size)) {
			return null;
		}

		for (int i = 1; i <= index; i++) {
			cursor = cursor.next;
		}
		return cursor.value;
	}
	/*
	 * public int remove(int index) { // start.next;
	 * 
	 * }
	 * 
	 * public int insert(int pos, String value) {
	 * 
	 * }
	 * 
	 * 
	 * public Object get(int pos) { }
	 */
}

public class CollectionTest04 {

	public static void printArray(MyLinkedList1 list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.getList(i));
		}
	}

	public static void main(String[] args) {
		MyLinkedList1 arr = new MyLinkedList1();

		System.out.println("현재크기: " + arr.add("00000"));
		System.out.println("현재크기: " + arr.add("11111"));
		System.out.println("현재크기: " + arr.add("22222"));
		System.out.println("현재크기: " + arr.add("33333"));
		System.out.println("현재크기: " + arr.add("44444"));
		System.out.println("현재크기: " + arr.add("55555"));
		System.out.println("현재크기: " + arr.add("66666"));

		printArray(arr);

	}
}
