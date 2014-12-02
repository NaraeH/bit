package java63.web03.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected int           no;
	protected String        name;
	protected int           quantity;
	protected int           makerNo;
	protected String        photo;
	protected MultipartFile photofile;
	
	@Override
	public String toString() {
		return "Product [no=" + no + ", name=" + name + ", quantity="
				+ quantity + ", makerNo=" + makerNo + ", photo=" + photo
				+ ", photofile=" + photofile + "]";
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMakerNo() {
		return makerNo;
	}

	public void setMakerNo(int makerNo) {
		this.makerNo = makerNo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public MultipartFile getPhotofile() {
		return photofile;
	}

	public void setPhotofile(MultipartFile photofile) {
		this.photofile = photofile;
	}
	
}