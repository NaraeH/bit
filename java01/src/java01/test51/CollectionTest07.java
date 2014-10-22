/*<Iterator 설계 비법>
 - 보관소에서 데이터를 꺼낼 때 사용하는 설계 방식
 - 목적:
    배열에서 꺼내든, 링크드리스트에서 꺼내든, 스택에서 꺼내든 저장소에 구조에 상관없이 동일한 interface(메서드)를 사용하여 데이터를 꺼내게 한다.
*/
package java01.test51;

class MyLinkedList4 {

	class Bucket {

		Object value; // 값(인스턴스주소)을 저장하기 위한 변수 선언
		Bucket next; // 다음 버킷의 주소 저장: 링크저장
	}

	Bucket start; // 처음 시작되는 버킷
	Bucket end; // 마지막 끝나는 버킷
	int size;

	public MyLinkedList4() {
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

	public int insert(int index, Object value) {
		Bucket before = start;
		Bucket newBucket = new Bucket();
		Bucket temp = new Bucket();

		if (index >= 0 && index <= size) {
			newBucket.value = value;
			
			for (int i = 1; i < index - 1; i++) {
				before = before.next;
			}
			
			if (index == 0) {
				newBucket.next = start;
				start = newBucket;
			} else if (index <= size) {
				temp.next = (before.next).next;
				(before.next).next = newBucket;
				newBucket.next = temp.next;

			} else {

				before.next = newBucket;
				end = newBucket;
			}
			size++;
			return 0;
		} else {

			return -1;
		}
	}

	public int remove(int index) { // start.next;
		Bucket before = start;

		if (index >= 0 && index < size) {
			for (int i = 1; i < index - 1; i++) {
				before = before.next;
			}
			if (index == 0) {
				start = before.next;
			} else if (index < size - 1) {
				before.next = (before.next).next;
			} else {
				before.next = (before.next).next; //end는 항상 빈 Bucket
			}
			size--;
			return 0;
		} else {
			return -1;
		}
	}
}

public class CollectionTest07 {

	public static void printArray(MyLinkedList4 list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.getList(i));
		}
	}

	public static void main(String[] args) {
		MyLinkedList4 arr = new MyLinkedList4();

		arr.add("00000");
		arr.add("11111");
		arr.add("22222");
		arr.add("33333");
		arr.add("44444");
		arr.add("55555");
		arr.add("66666");
		arr.add("77777");
		
		printArray(arr);

		System.out.println("--------10번에 입력------");
		arr.insert(-10, "-10에 입력"); // insert안됨

		System.out.println("-------30번에 입력------");
		arr.insert(30, "30에 입력"); // insert안됨

		System.out.println("-------0번에 입력------");
		arr.insert(0, "!!!!!!!"); // insert
		printArray(arr);

		System.out.println("-------9번에 입력------");
		arr.insert(9, "@@@@@@@"); // insert
		printArray(arr);
		 
		System.out.println("-------4번에 입력------");
		arr.insert(4, "#######"); // insert
		printArray(arr);

		System.out.println("-30번삭제:-------------------------");
		arr.remove(-30);// remove 안됨 
		printArray(arr);

		System.out.println("30번삭제:-------------------------"); // remove 안됨
		arr.remove(30);// remove 안됨 
		printArray(arr);

		System.out.println("0번삭제:-------------------------"); // remove
		arr.remove(0);// remove 안됨 
		printArray(arr);

		System.out.println("10번삭제:-------------------------"); // remove
		arr.remove(10);// remove 안됨 
		printArray(arr);

		System.out.println("4번삭제:-------------------------"); // remove
		arr.remove(4);// remove 안됨 
		printArray(arr);
	}
}
