package java01.test54;

public class Dog implements Unit{

	@Override
	public void run() {
		System.out.println("멍멍 달린다!");
		
	}

	@Override
	public void stop() {
		System.out.println("멍멍 멈췄다 왈왈!");
		
	}

}
