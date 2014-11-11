package java002.test15.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java002.test15.Score;
import java002.test15.ScoreDao;
import java002.test15.annotation.Command;
import java002.test15.annotation.Component;

@Component
public class ScoreCommand {
		ScoreDao scoreDao;
		Scanner scanner;

		public void setScoreDao(ScoreDao scoreDao) {
			this.scoreDao = scoreDao;
		}
		public void setScanner(Scanner scanner) {
			this.scanner = scanner;
		}

		@Command("add")
		public void addTest(HashMap<String, Object> params) throws Exception {
			ArrayList<String> options = (ArrayList<String>)params.get("options");
			
		    Score score = new Score(options.get(0), 
		            Integer.parseInt(options.get(1)), 
		            Integer.parseInt(options.get(2)), 
		            Integer.parseInt(options.get(3)));
		        
		        scoreDao.add(score);
		        System.out.println("저장하였습니다.");
		}
		

		@Command("delete")
		public void deleteTest(HashMap<String, Object> params) throws Exception {
			
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
		
		@Command("list")
		public void listTest(HashMap<String, Object> params) throws Exception {
			//이 메서드가 호출되기 전에 ScoreDao 의존 객체가 저장 될 것이기 때문에 다음 코드는 제거한다.
			//ScoreDao scoreDao = (ScoreDao) params.get("scoreDao");

			int index = 0;
			for (Score score : scoreDao.getList()) {
				System.out.printf("%-3d %-10s %3d %3d %3d\n", index,
						score.getName(), score.getKor(), score.getEng(),
						score.getMath());
				index++;
			}
		}
		
		@Command("update")
		public void updateTest(HashMap<String, Object> params) throws Exception {

			ArrayList<String> options = (ArrayList<String>)params.get("options");
			int index = Integer.parseInt(options.get(0));
			
		    Score score = scoreDao.getData(index);
		    if(score == null){
		    	System.out.println("해당 인덱스의 데이터를 찾을 수 없습니다.");
		    	return;
		    }
		    
		    Score tempScore = score.clone();
		    
		    String text = null;
		    System.out.printf("이름(%s):", score.getName());
		    text = scanner.nextLine();
		    if (text.length() > 0)
		      tempScore.setName(text);
		    
		    System.out.printf("국어(%d):", score.getKor());
		    text = scanner.nextLine();
		    if (text.length() > 0)
		      tempScore.setKor(Integer.parseInt(text));
		    
		    System.out.printf("영어(%d):", score.getEng());
		    text = scanner.nextLine();
		    if (text.length() > 0)
		      tempScore.setEng(Integer.parseInt(text)); 
		    
		    System.out.printf("수학(%d):", score.getMath());
		    text = scanner.nextLine();
		    if (text.length() > 0)
		      tempScore.setMath(Integer.parseInt(text));
		    
		    System.out.print("정말 변경하시겠습니까?(y/n)");
		    if (scanner.nextLine().equalsIgnoreCase("y")) {
		      scoreDao.change(index, tempScore);
		      System.out.println("변경하였습니다.");
		    } else {
		      System.out.println("변경 취소하였습니다.");
		    }
		}
		
		@Command("view")
		public void viewTest(HashMap<String, Object> params) throws Exception {
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
