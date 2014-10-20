package java01.test36;

public class Test36 {

	public static void main(String[] args) {
		
		String name="홍길동";
		int kor = 100;
		int eng = 100;
		int math = 90;
		int sum = 290;
		float average = (float)sum/3;
		
		System.out.println("이름: "+ name);
		System.out.println("국어: "+ kor);
		System.out.println("영어: "+ eng);
		System.out.println("수학: "+ math);
		System.out.println("합계: "+ sum);
		System.out.println("평균: "+ average);
		
		System.out.println("-------------------------");
		
		Score s = new Score();
		
		s.name = "홍길동";
		s.kor = 100;
		s.eng = 100;
		s.math = 90;
		s.sum = s.kor + s.eng + s.math;
		s.everage = s.sum / 4;
		
		System.out.println("이름: "+ name);
		System.out.println("국어: "+ kor);
		System.out.println("영어: "+ eng);
		System.out.println("수학: "+ math);
		System.out.println("합계: "+ sum);
		System.out.println("평균: "+ average);
	}
}
