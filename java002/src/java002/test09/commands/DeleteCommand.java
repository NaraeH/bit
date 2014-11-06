package java002.test09.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java002.test09.Score;
import java002.test09.ScoreDao;
import java002.test09.annotation.Command;
import java002.test09.annotation.Component;

@Component("delete")
public class DeleteCommand {
	
	// 총괄 관리자로부터 의존 객체를 주입받고 싶으면 setter method를 준비하라.
	ScoreDao scoreDao;
	Scanner scanner;


	// 이 메서드는 Test01이 호출한다.
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}
	
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	@Command
	public void deleteTest(HashMap<String, Object> params) throws Exception {
		//ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
		//Scanner scanner = (Scanner) params.get("scanner");
		
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		int index = Integer.parseInt(options.get(0));
	
		  Score score  = scoreDao.getData(index);
		    
		    if(score == null){
		    	System.out.println("해당 인덱스의 성적정보를 찾을 수 없습니다.");
		    	return;
		    }
	    
	    System.out.print(score.getName() + "의 성적을 삭제하시겠습니까?(y/n)");
	    if (scanner.nextLine().equalsIgnoreCase("y")) {
	      scoreDao.delete(index);
	      System.out.println("삭제하였습니다.");
	    } else {
	      System.out.println("삭제 취소하였습니다.");
	    }
	}

}
