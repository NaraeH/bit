package guestBook.member.domain;

import java.io.Serializable;

public class Member implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected String photo;
	
	public Member(){}

	public Member(String name, String photo) {
		super();
		this.name = name;
		this.photo = photo;
	}
	
	@Override
	public Member clone() throws CloneNotSupportedException {
		return (Member)super.clone();
	}
	
	@Override
	public String toString() {
		return "Member [name=" + name + ", photo=" + photo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}