package java01.test55;

public class Aplayer implements Gamer{
	int count = 1;

	@Override
	public String who() {
		return "A";
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
			if(count % 11 == 0){
				return Gamer.ROCK;
			}else if(count % 13 == 0){
				return Gamer.PAPER;
			}else{
				return Gamer.SCISSORS;
			}
		}
	}

	@Override
	public void sendResult(int result) {
		if (result == 1) {
			System.out.println("A: 이김");
		} else if (result == -1) {
			System.out.println("A: 짐");
		}else{
			System.out.println("A:비김");
		}
	}
}
