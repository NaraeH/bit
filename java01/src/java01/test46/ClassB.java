package java01.test46;

public class ClassB extends ClassA{
	int x = 10;
	
	{
		System.out.println("ClassB의 인스턴스 블럭");
	}
	
	public ClassB(){
		System.out.println("ClassB의 생성자 블럭");
		x = 20;
	}

}
