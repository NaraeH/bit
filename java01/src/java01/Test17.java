/*Quiz1
프로그램 아규먼트로 나이를 입력받아 청소년, 청년 등의 여부를 출력하시오.
*/
package java01;

public class Test17 {

	public static void main(String[] args) {
		
		int age = Integer.parseInt(args[0]);
		
		if(age >= 65){
			System.out.println("노인입니다");
		}else if(age >= 50){
			System.out.println("중년입니다");
		}else if(age >= 40){
			System.out.println("장년입니다");
		}else if(age >= 18){
			System.out.println("청년입니다");
		}else{
			System.out.println("청소년입니다.");
		}

	}

}
