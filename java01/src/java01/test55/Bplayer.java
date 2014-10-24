package java01.test55;

public class Bplayer implements Gamer{
	private int count;

	@Override
	public String who() {
		return "B";
	}

	@Override
	public void init() {
		
	}

	@Override
	public String play() {
		count++;
		if(count  == 1){
			return Gamer.PAPER;
		}else {
			if(count % 3 == 0){
				return Gamer.ROCK;
			}else{
				return Gamer.SCISSORS;
			}
		}
	}

	@Override
	public void sendResult(int result) {
		if (result == 1) {
			System.out.println("B: 이김");
		} else if (result == -1) {
			System.out.println("B: 짐");
		}else{
			System.out.println("B:비김");
		}
	}

}
