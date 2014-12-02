package java63.web03.domain;

import java.io.Serializable;

public class Maker implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected int    no;
	protected String name;
	protected String homepage;
	protected String tell;
	protected String fax;
	
	@Override
	public String toString() {
		return "Maker [no=" + no + ", name=" + name + ", homepage=" + homepage
				+ ", tell=" + tell + ", fax=" + fax + "]";
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
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	

}
