package java002.test21.spring.exam10;

public class EnginFactory {
	public static Engine createEngine(String type){
		if(type.equals("sedan")){
			return new Engine("현대자동차", 16);
		}else if(type.equals("sport")){
			return new Engine("기아자동차", 18);
		}else if(type.equals("suv")){
			return new Engine("벤츠", 20);
		}else {
			return new Engine("GM", 22);
		}
	}
	
	

}
