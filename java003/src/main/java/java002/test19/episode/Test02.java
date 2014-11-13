package java002.test19.episode;

public class Test02 {
	
	public static void main(String[] args) {
		//객체 생성이 복잡할 경우(속성을 설정할 경우가 많은 경우)
		//객체를 대신 생성해주는 factory객체에게 요구이용
		KimRiceFactory factory = new KimRiceFactory();
		KimRice p = factory.createInstance("멸치");
	}
	public static void main01(String[] args) {
		//객체 생성이 단순한 경우 다음과 같이
		//직접 객체를 생성하면 된다.
		KimRice p = new KimRice();
	}

}
