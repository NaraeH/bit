/*
<크리티컬 섹션을 동기화 처리하기>
  여러 쓰레드가 크리티컬 섹션 부분을 실행하더라도 문제없게 만들기
  한번에 하나의 스레드만 접근하게 만듬

- 방법
1) 어떤 thread가 critical 섹션에 진입하면 잠든다(lock)
2) thread가 일을 마치고 나올 때 잠금을 해재한다.
- 문법
 critical section block에 synchronized를 붙인다.
  ex) method: synchronized void m(){}
      object: synchronized(객체) {}
  
  - 뮤텍스(Mutex)    
  => 한번에 "하나"의 thread만이 크리티컬 섹션에 접근하는 방식  
  ==> semaphore(1) =>java에서 제공O => synchronized
  
  - 세마포어(semaphore)
  => 한번에 "몇개"의 thread가 critical section에 접근 가능한지에 대한 thread 개수    
  ====> java에서 제공X => 직접 프로그래밍 필요
  
  -Thread safe(쓰레드에 안전하다)  
  => 여러 thread가 진입하더라도 계산에 영향을 끼치지 않는 코드블럭(변수를 공유하지 않는경우 => 로컬변수만 사용하는 경우)  
  ==> 굳이 동기화 처리를 할 필요가 없다. 
 */
package java002.test12;

public class Test05 {
	static class Account{
		long balance;
		
		public Account(int balance){
			this.balance = balance;
		}
		
		public long delay(){
			long l= 10L;
			double b = 3.15;
			b = b / l;
			b += System.currentTimeMillis();
			return (long)(b / 34.56);
		}
		
		synchronized public long withdraw(long money){
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
