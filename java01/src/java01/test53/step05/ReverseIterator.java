package java01.test53.step05;

public class ReverseIterator extends Iterator{
	int cursor;

	public ReverseIterator(){}
	
/*	public ReverseIterator(String[] list) {
		this.list = list;
		this.cursor = list.length - 1; //배열의 끝 인덱스 가르킴.
	}*/
	
	@Override
	public boolean hasNext() {
		if (cursor >= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void setList(String[] list) {
		super.setList(list);  // this.list = list;

		cursor = list.length - 1;
	}

	@Override
	public String next() {
		return list[cursor--];
	}
}
