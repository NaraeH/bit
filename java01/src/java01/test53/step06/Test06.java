package java01.test53.step06;

public class Test06 {
	
	public static void main(String[] args) throws Exception{
		String iteratorClassName = System.getProperty("iterator");
		
		Class clazz = Class.forName(iteratorClassName); //class는 예약어 이므로 clazz로!
		Iterator iterator = (Iterator)clazz.newInstance(); 
		iterator.setList(args);
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
	}

}
