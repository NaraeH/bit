package java01.test40;

import java.util.Date;

public class Car {
	
	private String  maker;
	protected String model;
	private int cc = 800;
	private boolean diesel;
	private Date releaseDate;
	private int capacity = 5;
	
	public Car(){
		this.maker = "미정";
		this.model = "미정";
	};	
	
	public Car(String model, String maker, int cc ){
		this.model = model;
		this.maker = maker;
		this.cc = cc;
	}

	public String getMaker() { return maker; }
	public String getModel() { return model; }
	public int getCc() { return cc;	}
	public boolean isDiesel() {	return diesel; }
	public Date getReleaseDate() { return releaseDate; }
	public int getCapacity() { return capacity;	}
	
}
