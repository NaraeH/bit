/*
 <Value Object>
 class 문법을 사용하여 사용자 정의 데이터 타입 만들기
 
 1) Serializable interface를 구현
    => SerialVersionUID 스태틱 변수 선언
 2) 인스턴스 변수 선언
 
 3) setter/getter 생성
 
 4) 기본 생성자와 파라미터 값을 받는 생성자를 선언
 
 5) 경우에 따라 hashCode(), equals(), override()
 
 6) toString() override
 
 */
package java002.test16;

import java.io.Serializable;

public class Member implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	
	protected String    id;
	protected String    name;
	protected String pw;
	protected String    email;
	protected String    tel;
	protected String    pax;
	protected String    datAddrs;
	protected String    photo;
	protected int    no;
	
	public Member(){}

	public Member(String id, String pw, String email, String tel,
			String det_addrs, String photo, int no) {
		super();
		this.id = id;
		this.pw = pw;
		this.email = email;
		this.tel = tel;
		this.datAddrs = det_addrs;
		this.photo = photo;
		this.no = no;
	}

	@Override
	public Member clone() throws CloneNotSupportedException {
		return (Member)super.clone();
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", email=" + email
				+ ", tel=" + tel + ", det_addrs=" + datAddrs + ", photo="
				+ photo + ", no=" + no + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((datAddrs == null) ? 0 : datAddrs.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + no;
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((pw == null) ? 0 : pw.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
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
		Member other = (Member) obj;
		if (datAddrs == null) {
			if (other.datAddrs != null)
				return false;
		} else if (!datAddrs.equals(other.datAddrs))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (no != other.no)
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (pw == null) {
			if (other.pw != null)
				return false;
		} else if (!pw.equals(other.pw))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDatAddrs() {
		return datAddrs;
	}
	
	public String getPax() {
		return pax;
	}

	public void setPax(String pax) {
		this.pax = pax;
	}

	public void setDatAddrs(String det_addrs) {
		this.datAddrs = det_addrs;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
