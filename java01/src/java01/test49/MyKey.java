/*<Object 클래스>
 1) hashCode(): 인스턴스 별로 고유 값을 리턴
 2) equals():같은 인스턴스인지 비교(true/false)
 
 Map에 값을 저장할 때 키로 사용할 클래스라면, 
 반드시 그 클래스는 hashCode()와 equals()를 재정의 해야 한다.
 
 String + 랩퍼클래스(Byte, Short, Integer, Long, Float, Double, ...)
 => 기본적으로 hashCode()와 equals()와 toString() 등을 재정의하였다.
 => hashCode(): 인스턴스의 저장된 값이 같다면 같은 해쉬값을 리턴한다.
 => equals(): 인스턴스에 저장된 값이 같다면 true 리턴
 => toString(): 인스턴스의 내용을 출력하도록 재정의함.
                        즉, "클래스@해시" 를 출력하지 않음.
 => finalize(): 가비지 컬렉터가 비지를 해제하기 전에 호출
  */
package java01.test49;

public class MyKey {
	String password;
	int specialNo;
	int secreatNo;
	
	public MyKey(){}

	public MyKey(String password, int specialNo, int secreateNo) {
		this.password = password;
		this.secreatNo = specialNo;
		this.secreatNo = secreateNo;
	}

	@Override
	public String toString() {
		return "MyKey [pasword=" + password + ", specialNo=" + specialNo
				+ ", secreatNo=" + secreatNo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + secreatNo;
		result = prime * result + specialNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyKey other = (MyKey) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (secreatNo != other.secreatNo)
			return false;
		if (specialNo != other.specialNo)
			return false;
		return true;
	}
	
/*	
 	//source -> Generate equals() and hashCode()...
 	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		
		if(!(obj instanceof MyKey)){
			return false;
		}
		MyKey other = (MyKey)obj;
		if( this.password.equals(other.password)&&
				this.secreatNo == other.secreatNo &&
		this.specialNo == other.specialNo){
			return true;
		}
		}
		*/
		
		

	
}
