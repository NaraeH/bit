/*
 <왜 총액이 1000000인데, 그 이상찾을 수 있을까?>
 각각의 ATM은 각각의 stack을 갖고있다.
 
 <stack 메모리 구조>
  지점      잔 액        money
-----------------------------
  강남: 1000000     100   
  양재: 1000000     100        
  종로: 1000000     100
  부산: 1000000     100
  강릉: 1000000     100
  
  1) 강남
     => 500 원찾다가 CPU 사용권 뺏낌 balance = 999500
  2) 양재
     => 100원 찾다가 CPU 사용권 뺏낌 balance = 999900 
  3) 다시 강남
     => 100원 찾다가 CPU 사용권 뺏낌 balance는 999500로 바뀐다  => 문제점 발생 
  
  => 각각이 1000000을 갖고 있으므로 총액이 1000000이 되어야 하는 것은 아니다. 
 */
package java002.test12;

public class Test04 {
	static class Account{
		long balance;
		
		public Account(int balance){
			this.balance = balance;
		}
		
		//단순히 시간 지체하기 위한 것.
		public long delay(){
			long l= 10L;
			double b = 3.15;
			b = b / l;
			b += System.currentTimeMillis();
			return (long)(b / 34.56);
		}
		
		//여러 thread가 동시에 접근했을 떄 문제가 발생하는 코드 블럭
		//=> critical section(critical region)
		//=> 개선 코드 Test05.java
		public long withdraw(long money){
			long temp = this.balance;
			
			temp -= money;
			
			delay();
			delay();
			delay();
			
			if(temp >= 0){
				this.balance = temp;
				return money;
			}else{
				return 0;
			}
		}
	}
	
	static class ATM extends Thread{
		Account account;
		
		public ATM(String name, Account account){
			this.setName(name);
			this.account = account;
		}
		
		@Override
		public void run() {
			long sum = 0;
			for(int i = 0; i < 10000; i++){
				if(account.withdraw(100) != 0){
					sum += 100;
				}else{
					break;
				}
			}
			System.out.println(this.getName() + " ===> " + sum + "원 찾았습니다.");
		}
	}
	public static void main(String[] args) {
		Account account = new Account(1000000);
		
		ATM a1 = new ATM("강남", account);
		ATM a2 = new ATM("양재", account);
		ATM a3 = new ATM("종로", account);
		ATM a4 = new ATM("부산", account);
		ATM a5 = new ATM("강릉", account);
		
		a1.start();
		a2.start();
		a3.start();
		a4.start();
		a5.start();
		
	}

}
