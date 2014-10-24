package java01.test53.step05;

public class GeneralIterator extends Iterator{
	int cursor;

	public GeneralIterator(){}
	
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
		return list[cursor++];
	}
}

