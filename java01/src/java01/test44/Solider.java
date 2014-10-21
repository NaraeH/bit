package java01.test44;

public class Solider extends Mover{
	int attack;
	int level;
	
	//overriding: Mover로부터 상속받은 move() method를 서버크래스의 역할에 맞게 재정의하는 것.
	//문법: 재정의 하려는 클래스와 같은 시그너처(signature: C언어에서는 function prototype)를 갖는 메서드를 만들어라.
	//parameter 변수의 이름은 달라도 상관없다. => 프로그램 실행에 영양을 미치지 않는다.
	//signature: method의 이름, return type, parameter type을 말함
	//단 공개범위는 축소되어서는 안된다.
	
	@Override //super class의 method를 재정의하는지 검사하라.
	public void move(int dir) { //만약 파라미터가 없다면 Overload가 되어버림 => 파라미터가 다르다면 overload이므로
		System.out.println("뚜벅... 뚜벅... 걷는다.");

	}
	
	

	

}
