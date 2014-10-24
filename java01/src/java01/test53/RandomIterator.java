package java01.test53;

import java.util.ArrayList;
import java01.test53.step05.Iterator;

public class RandomIterator extends Iterator{
	ArrayList<String> randomList = new ArrayList<String>();
	
	public RandomIterator(){}

	@Override
	public void setList(String[] list) {
		super.setList(list);
		
		for(String value:list){
			randomList.add(value);
		}
	}

	@Override
	public boolean hasNext() {
		if(randomList.size() > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public String next() {
		/*int index = (int)Math.random() * randomList.size();
		return randomList.remove(index);*/
		
		//실무에서는 쓸데없이 메모리를 낭비하지 않기위해 임시변수를 만들지 않는다.
		return randomList.remove((int)(Math.random() * randomList.size()));
	};
	

}
