package java01.test51;

class MyArray {
	Object[] list = new Object[3];
	int index;
	
	public int add(Object instance){
		if(index <list.length){
			list[index++] = instance;
			return 0;
		}else{
			return -1;
		}
	}
	
	public int size(){
		return this.index;
	}
	
	public Object get(int index){
		return list[index];
	}
}

public class CollectionTest02 {

	public static void main(String[] args) {
		
		MyArray arr = new MyArray();
		
		System.out.println(arr.add("정담담한정담"));
		System.out.println(arr.add("정담담"));
		System.out.println(arr.add("정담걸렸다"));
		System.out.println(arr.add("정담담담담"));
		
		int size = arr.index;
		
		for(int i=0; i<size; i++){
			System.out.println("[" + i + "] " + arr.get(i));
		}
	}
}
