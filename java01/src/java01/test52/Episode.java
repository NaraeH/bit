package java01.test52;

public class Episode {

	static int global;
	int value;
	
	static void m(){
		System.out.println("static m()이 호출되었다");
	}
	
	void m2(){
		System.out.println("static이 없는 m()이 호출되었다");
	}
	
	public static void main(String[] args) {
		Episode p = new Episode();
		
		//static이 붙은 메서드(클래스메서드)도 인스턴스 주소로 호출할 수 없다.
		//그러나, 클래스 매서드에느 this라는 내부 변수가 없기 때문에 인스턴스 주소를 전달할 길이 없다.
		
		p.m();  //p라는 인스턴스를 생성하였으로 호출가능
		m(); //static으로 선언하였으므로 실행가능
		Episode.m();// 클래스의 메서드를 부른다.
		
		//m2(); //error: m2를 찾을 수가 없다. load가 되지 않았으므로
		Episode p2 = new Episode(); //Episode load
		p2.m2(); //Episode가 load되면서 내부의 m2도 load 됬기 때문에 사용 가능
		

	}

}
