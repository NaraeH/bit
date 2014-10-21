package java01.test44;

public class Tank extends Vehicle {

	int range;
	boolean siegeMode;

	@Override
	public void move(int dir) {
		
		if(siegeMode){
			return; //움직일 수 없다.
		}
		int distance = 3;
		
		switch(dir){
		case NORTH: this.y -= distance; break; //북
		case EAST: this.x += distance; break; //동
		case SOUTH: this.y += distance; break; //남
		case WEST: this.x -= distance; break; //서
		default : System.out.println("유효하지 않는 방향입니다.");
		}
		
		System.out.println("끼리릭....컬...척:" + this.x + ", " + this.y);
	}
}
