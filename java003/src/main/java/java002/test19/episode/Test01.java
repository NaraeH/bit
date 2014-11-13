package java002.test19.episode;

public class Test01 {
	
	public static void main(String[] args) {
		//공장 생성이 복잡할 경우
		//빌더 역할을 수행하는 객체를 통해서 공장 객체를 얻는다.
		KimRiceFactory factory = KimRiceFactoryBuilder.build("/comfig/kimrice-config.xml");
		KimRice p = factory.createInstance("멸치");
	}
	public static void main01(String[] args) {
		//객체 생성이 단순한 경우 다음과 같이
		//직접 객체를 생성하면 된다.
		KimRice p = new KimRice();
	}

}
