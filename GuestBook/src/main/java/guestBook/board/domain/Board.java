package guestBook.board.domain;

import java.io.Serializable;

public class Board implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	
	protected int    no;
	protected String title;
	protected String content;
	protected int    pwd;
	protected String name;
	protected String date;
	protected int    uId;
	
	public Board(){}
	
	public Board(int no, String title, String content, int pwd, String name,
			String date, int uId) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.pwd = pwd;
		this.name = name;
		this.date = date;
		this.uId = uId;
	}

	@Override
	public Board clone() throws CloneNotSupportedException {
		return (Board)super.clone();
	}
	
	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", content=" + content
				+ ", pwd=" + pwd + ", name=" + name + ", date=" + date
				+ ", uId=" + uId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + no;
		result = prime * result + pwd;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + uId;
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
		Board other = (Board) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (no != other.no)
			return false;
		if (pwd != other.pwd)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (uId != other.uId)
			return false;
		return true;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPwd() {
		return pwd;
	}

	public void setPwd(int pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getUId() {
		return uId;
	}

	public void setUId(int uId) {
		this.uId = uId;
	}

}