package java01.test53.step06;

public class OddIterator extends AbstractIterator{
	int cursor;

	public OddIterator(){
		cursor++;
	}

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

