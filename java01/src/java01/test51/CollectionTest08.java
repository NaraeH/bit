package java01.test51;

class MyStack {
	Object[] list = new Object[100];
	int top;

	public void push(Object value) {
		if (top < 100) {
			list[top++] = value;
		}
	}

	// 마지막에 입력한 값을 꺼낸다. 목록에서 제거됨.
	public Object pop() {
		if (top > 0) {
			Object temp = list[top - 1];
			list[--top] = null;
			return temp;
		} else {
			return null;
		}
	}
}

class MyQueue {
	class Bucket {
		Object value;
		Bucket next;
	}

	Bucket start;
	Bucket end;

	public MyQueue() {
		start = new Bucket();
		end = start;
	}

	public void add(Object value) {

		end.value = value;
		end.next = new Bucket();

		end = end.next;
	}

	// 첫 번째 입력 값을 꺼낸다. 목록에서 제거됨.
	public Object poll() {
		if (start.value != null) {
			Bucket temp = start;
			start = start.next;

			return temp.value;
		} else {
			return null;
		}
	}
}

public class CollectionTest08 {

	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.push("0000");
		stack.push("1111");
		stack.push("2222");
		stack.push("3333");

		System.out.println("----stack push 확인-----");
		for (int i = 0; i < 4; i++) {
			System.out.println(stack.list[i]);
		}

		System.out.println("-----stack pop 확인------");
		for (int i = 0; i < 5; i++) {
			System.out.println(stack.pop());
		}
		// for
		/*
		 * 예상 출력 결과 3333 2222 1111 0000
		 */

		System.out.println("------현재의 stack 출력-----");
		for (int i = 0; i < 4; i++) {
			System.out.println(stack.list[i]);
		}

		MyQueue queue = new MyQueue();
		queue.add("AAAA");
		queue.add("BBBB");
		queue.add("CCCC");
		queue.add("DDDD");

		System.out.println("------queue 출력-----");
		for (int i = 0; i < 6; i++) {
			System.out.println(queue.poll());
		}// for
		/*
		 * 예상 출력 결과 AAAA BBBB CCCC DDDD
		 */
	}

}
