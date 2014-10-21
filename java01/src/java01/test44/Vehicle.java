package java01.test44;

public abstract class Vehicle extends Mover{
	
	int speed;
	int capacity;
	
	@Override
	public void move(int direction) {
		System.out.println("엔진으로 이동한다...");
		
	}
	
	public void test(){
		System.out.println("Test입니다");
	}

}
