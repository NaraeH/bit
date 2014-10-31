package java002.test09.commands;

import java.util.ArrayList;
import java.util.HashMap;

import java002.test09.Command;
import java002.test09.Score;
import java002.test09.ScoreDao;
import java002.test09.annotation.Component;
@Component("view")
public class ViewCommand implements Command{
	
	// 총괄 관리자로부터 의존 객체를 주입받고 싶으면 setter method를 준비하라.
	ScoreDao scoreDao;

	// 이 메서드는 Test01이 호출한다.
	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Override
	public String getCommandInfo() {
		return "view";
	}

	@Override
	public void service(HashMap<String, Object> params) throws Exception {
		//ScoreDao scoreDao = (ScoreDao)params.get("scoreDao");
		
		ArrayList<String> options = (ArrayList<String>)params.get("options");
		int index = Integer.parseInt(options.get(0));
		
		 Score score  = scoreDao.getData(index);
		    
		    if(score == null){
		    	System.out.println("해당 인덱스의 성적정보를 찾을 수 없습니다.");
		    	return;
		    }
		    
		    System.out.println("인덱스: " + index);
		    System.out.println("이름: " + score.getName());
		    System.out.println("국어: " + score.getKor());
		    System.out.println("영어: " + score.getEng());
		    System.out.println("수학: " + score.getMath());
		    System.out.println("총점: " + score.getSum());
		    System.out.println("평균: " + score.getAverage());
		
	}


}
