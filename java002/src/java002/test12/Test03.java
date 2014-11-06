/*
 <sleep>
 */
package java002.test12;

import java.util.Date;

public class Test03 {
	
	public static class MyAlarm implements Runnable{

		@Override
		public void run() {
			int count = 0;
			while (true) {
				if(count++ >10){
					break;
				}
				Date today = new Date();
				System.out.println("\n현재시간: " + today);

				try {
					Thread.currentThread().sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public static void main(String[] args) {
		new Thread(new MyAlarm()).start();
		
		for(int i = 0; i < 10000000; i++){
			double d1 = 3.141592;
			double d2 = 124.2;
			
			if(i % 10000 == 0){
			System.out.print(".");
			}
		}
	}
}
