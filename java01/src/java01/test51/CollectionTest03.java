package java01.test51;

class MyArray2 {
	Object[] list = new Object[6];
	int cursor;

	public int add(Object instance) {
		if (cursor < list.length) {
			list[cursor++] = instance;
			return 0;
		} else {
			return -1;
		}
	}

	public int remove(int pos) {
		/*
		 * if ((pos >= 0) && (pos < this.cursor)) { for (int i = pos; i
		 * < this.cursor; i++) { if (i == (this.cursor - 1)) { list[i] = null;
		 * this.cursor--; } else { list[i] = list[i + 1]; } } return 0; } else {
		 * return -1; }
		 */
		if ((pos >= 0) && (pos < this.cursor)) {
			for (int i = pos; i < this.cursor; i++) {
				list[i] = list[i + 1];
			}
			this.cursor--;
			return 0;
		} else {
			System.out.println("유효하지 않은 범위가 들어갔습니다. 범위를 확인해주세요");
			return -1;
		}
	}

	public int insert(int pos, String value) {
		/*
		  //방법1: this.cursor의 값을 받는데 문제가 생김 if (pos >= 0 && pos < list.length)
		  { for (int i = list.length - 1; i >= pos; i--) { if (pos != i) {
		  list[i] = list[i - 1]; } else { list[pos] = value; } } for (int i =
		  0; i < list.length; i++) { if (list[i] != null) { this.cursor = i +
		 1; } }
		  
		 return 0; } else { System.out.println("유효하지 않은 index 범위입니다."); return
		  -1; }
		 */
/*		if (pos >= 0 && pos < list.length) {
			// System.out.println("cursor => " + this.cursor);
			if (this.cursor < pos) {
				list[pos] = value;
				System.out.println("배열 중간에 공간이 비어있습니다. size()를 제대로 실행할 수 없습니다");
				return -1;
			} else {
				for (int i = this.cursor; i > pos; i--) {
					list[i] = list[i - 1];
				}
				list[pos] = value;
				this.cursor = (this.cursor >= list.length - 1) ? list.length - 1: this.cursor + 1;
				return 0;
			}
		} else {
			System.out.println("유효하지 않은 범위가 들어갔습니다. 범위를 확인해주세요");
			return -1;
		}*/
		//배열이 가득차 있다면 더 이상 값 넣는 것 불가능
		//System.out.println(this.cursor);
		if ((this.cursor != list.length) && (pos >= 0 && pos <= this.cursor)) {
			for (int i = this.cursor; i > pos; i--) {
				list[i] = list[i - 1];
			}
			list[pos] = value;
			this.cursor++;
			return 0;
		} else {
			System.out.println("유효하지 않은 범위가 들어갔습니다. 범위를 확인해주세요");
			return -1;
		}
	}

	public int size() {
		return this.cursor;
	}

	public Object get(int pos) {
		return list[pos];
	}
}

public class CollectionTest03 {

	public static void main(String[] args) {

		MyArray2 arr = new MyArray2();

		System.out.println(arr.add("00000"));
		System.out.println(arr.add("11111"));
		System.out.println(arr.add("22222"));
		System.out.println(arr.add("33333"));
		System.out.println(arr.add("4444"));
		//System.out.println(arr.add("55555"));
		//System.out.println(arr.add("666666"));


		for (int i = 0; i < arr.size(); i++) {
			System.out.println("[" + i + "] " + arr.get(i));
		}

		System.out.println("----< remove >----");

		//arr.remove(2);
		for (int i = 0; i < arr.size(); i++) {
			System.out.println("[" + i + "] " + arr.get(i));
		}
		//System.out.println(arr.cursor);

		System.out.println("---------<insert>------------");

		arr.insert(4, "a");
		//arr.insert(5, "b");
		//arr.insert(3, "c");

		//arr.insert(2, "z");
		for (int i = 0; i < arr.size(); i++) {
			System.out.println("[" + i + "] " + arr.get(i));
		}
	}
}
