package java002.test21.spring.exam12;

import java.util.Date;
import java.util.Map;

public class Tire {
	String maker;
	int width;
	int height;
	int radial;
	Map<String, Object> options;
	Date createDate;
	

	@Override
	public String toString() {
		return "Tire [maker=" + maker + ", width=" + width + ", height="
				+ height + ", radial=" + radial + ", options=" + options
				+ ", createDate=" + createDate + "]";
	}
	
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getRadial() {
		return radial;
	}
	public void setRadial(int radial) {
		this.radial = radial;
	}
	public Map<String, Object> getOptions() {
		return options;
	}
	public void setOptions(Map<String, Object> options) {
		this.options = options;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
