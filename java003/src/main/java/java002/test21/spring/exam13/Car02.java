package java002.test21.spring.exam13;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Car02 {
	String model;
	int cc;
	Engine engine;  //Engine은 Car의 의존객체
	Set<Tire> tires;
	Map<String, Object> options;
	
	public Car02(){}
	
	public Car02(String model){
		this.model = model;
	}
	
	public Car02(String model, int cc){
		this.model = model;
		this.cc = cc;
	}


	@Override
	public String toString() {
		return "Car [model=" + model + ", cc=" + cc + ", engine=" + engine
				+ ", tires=" + tires + ", options=" + options + "]";
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	public Engine getEngine() {
		return engine;
	}
	//@Autowired : 주입해라
	//@Qualifier(!!!!): 무엇을? !!!!을 실행해라 
	@Autowired
	@Qualifier("engine02")
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	public Set<Tire> getTires() {
		return tires;
	}
	public void setTires(Set<Tire> tires) {
		this.tires = tires;
	}

	public Map<String, Object> getOptions() {
		return options;
	}

	public void setOptions(Map<String, Object> options) {
		this.options = options;
	}
	
	

}
