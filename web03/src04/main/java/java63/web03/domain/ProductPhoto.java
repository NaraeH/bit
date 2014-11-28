package java63.web03.domain;

import java.io.Serializable;

//Serializable하는 이유: 만약 서버가 1,2,3 3개가 존재하는데 누군가 3서버에서 작업을 하다가 서버가 다운됬다
//그러면 서버3의 작업파일을 자동으로 서버2로 옮기는데 그때 'Serializable'이 필요하다 만약 Serializable이 되지 않았다면, 객체를 옮길 수 없다!!!!
public class ProductPhoto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected int    no;
	protected int    productNo;
	protected String url;
	
	@Override
	public String toString() {
		return "ProductPhoto [no=" + no + ", proudctNo=" + productNo + ", url="
				+ url + "]";
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getproductNo() {
		return productNo;
	}
	public void setproductNo(int proudctNo) {
		this.productNo = proudctNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
}
