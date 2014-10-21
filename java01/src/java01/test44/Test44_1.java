package java01.test44;

public class Test44_1 {

	public static void main(String[] args) {
		//Unit u1 = new Unit(); //error => abstract이므로
		Tank t1 = new Tank();
		//t1.move(t1.NORTH);  //warning : The static field Mover.NORTH should be accessed in a static way
		                      //static으로 선언됬으므로 그 해당 클래스로 접근하여라
		t1.move(Mover.NORTH);
		
		Vulture v1 = new Vulture();
		v1.move(Mover.SOUTH);
		
		Solider s1 = new Solider();
		s1.move(Mover.EAST);

	}

}
