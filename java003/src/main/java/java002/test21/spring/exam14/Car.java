package java002.test21.spring.exam14;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //=> 이름 지정안하면 default로 클래스이름으로 지정된다. 단, 클래스의 첫 알파벳은 소문자로한다.
//@Component("c01")  //에노테이션에 이름 지정하기
public class Car {
	String model;
	int cc;
	
	@Autowired
	Engine engine;  //Engine은 Car의 의존객체
	Set<Tire> tires;
	Map<String, Object> options;
	
	public Car(){}
	
	public Car(String model){
		this.model = model;
	}
	
	public Car(String model, int cc){
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
	@Autowired
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
