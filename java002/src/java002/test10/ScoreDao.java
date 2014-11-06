package java002.test10;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScoreDao {
	static ArrayList<Score> list = new ArrayList<Score>();
	String filename;

	public ScoreDao() {
		this.filename = "score.dat";

	}

	public ScoreDao(String filname) {
		this.filename = filename;

	}

	public void load() throws Exception {
		Scanner dataScanner = null;
		try {
			dataScanner = new Scanner(new FileReader(filename));
			String token;

			while (true) {
				try {
					token = dataScanner.nextLine();
					list.add(new Score(token));
				} catch (NoSuchElementException e) {
					break;
				}
			}
			System.out.println("데이터 로딩이 성공하였습니다.");
		} catch (Exception e) {
			list.clear();
			throw e;
		} finally {
			try {
				dataScanner.close();
			} catch (Exception e) {
			}
		}
	}

	public void save() throws Exception {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(filename));

			for (Score score : list) {
				out.write(score.getName() + "," + score.getKor() + ","
						+ score.getEng() + "," + score.getMath() + "\n");
			}
			System.out.println("파일에 저장하였습니다.");
		} catch (IOException e) {
			System.out.println("파일 저장 중 오류발생");
			throw e;
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}

	private boolean isValid(int index) {
		if (index < 0 || index >= list.size()) {
			System.out.println("존재하지 않는 인덱스입니다.");
			return false;
		} else {
			return true;
		}
	}

	public Score getData(int index) {
		if (isValid(index)) {
			return list.get(index);
		} else {
			return null;
		}
	}

	public void change(int index, Score data) {
		list.set(index, data);
	}

	public void delete(int index) {
		list.remove(index);
	}
	
	public List<Score> getList(){
		return list;
	}
	public void add(Score data){
		list.add(data);
	}
}
