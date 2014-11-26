package groovy01;

public class Test04 {
	public static void main(String[] args) {
		//자바파일에서 groovy 불러다 써서 상관없음
		//둘다 컴파일 되면 .class 이므로
		System.out.println(Calc.plus(10, 50));
		System.out.println(Calc.minus(10, 50));
	}

}
