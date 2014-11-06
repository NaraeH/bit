/*
 < 분리하여 병형 처리하기 >
 특정 명령어 블럭을 thread로 분리하기
 방법1) thread class를 상속받아 만들기
 방법2) Runnable interface를 구현하기
 */
package java002.test12;

public class Test02 {
	
	//방법1: Thread를 상속받아 만들기
	static class MyThread extends Thread{

		//병행으로 수행할 명령어는 run() method에 넣는다.
		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				//주춤하다가 cpu빼기도록
				double d = 3.14; //<- 실행을 주춤하게 하기
				d /= 12.56;      //<- 실행을 더 주춤하게 하기
				System.out.println(" >: " + i);
			}
		}
	}
	
	//방법2: Runnable interface구현하기
	//이 객체는 바로 실행 할 수 없고 Thread 객체를 통해 실행시킨다.
	static class MyRunnable implements Runnable{

		//병행으로 수행할 명령어는 run() method에 넣는다.
		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				//주춤하다가 cpu빼기도록
				double d = 3.14; //<- 실행을 주춤하게 하기
				d /= 12.56;      //<- 실행을 더 주춤하게 하기
				System.out.println(" @@!: " + i);
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 1000; i++){
			//주춤하다가 cpu빼기도록
			double d = 3.14; //<- 실행을 주춤하게 하기
			d /= 12.56;      //<- 실행을 더 주춤하게 하기
			System.out.println("main: " + i);
		}
		
		MyThread t = new MyThread();
		t.start();
		
		Thread t2 = new Thread(new MyRunnable());
		t2.start();
		
		
		
		for(int i = 0; i < 1000; i++){
			System.out.println("main2: " + i);
		}
	}

}
