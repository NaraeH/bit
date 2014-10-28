package com.bit;

import java.util.ArrayList;
import java.util.Scanner;

public class Score {
	static ArrayList dataList = new ArrayList(); //수정
	static String[] inputList = new String[4]; //수정
	String name;
	int kor;
	int eng;
	int math;

	Score(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public static boolean menu() {

		try {
			Scanner in = new Scanner(System.in);
			String str = null;

			System.out.println("명령> ");
			str = in.nextLine();

			inputList = (str.split(" "));

			switch (inputList[0]) {
			case "help":
				help();
				return true;
			case "list":
				list();
				return true;
			case "add":
				add(inputList[1], Integer.parseInt(inputList[2]),
						Integer.parseInt(inputList[3]),
						Integer.parseInt(inputList[4]));
				return true;
			case "delete":
				delete(Integer.parseInt(inputList[1]));
				return true;
			case "update":
				update(Integer.parseInt(inputList[1]));
				return true;
			case "view":
				view(Integer.parseInt(inputList[1]));
				return true;
			case "exit":
				exit();
				return false;
			default:
				System.out.println("잘못된 명령어 입니다.");
				return true;
			}
		} catch (Exception e) {
			System.out.println("입력이 바르지 않습니다.");
			System.out.println(e.getStackTrace());
			return true;
		}
	}

	public static void help() {
		//메뉴 출력만 => 나래
		System.out.println("help입니다");
		System.out.println("help");
		System.out.println("view 인덱스");
		System.out.println("add 이름 국어 영어 수학");
		System.out.println("delete 인덱스");
		System.out.println("update 인덱스");
		System.out.println("exit");

	}

	public static void list() {
		System.out.println("list입니다");
		//list 전부 출력 => 지영

	}

	public static void add(String name, int kor, int eng, int math) {
		// list.length-1 데이터 추가 => 나래
		if ((kor >= 0) && (eng >= 0) && (math >= 0)) {
			int length = dataList.size() > 0 ? dataList.size() - 1 : 0;
			
			Score score = new Score(name, kor, eng, math);
			
			dataList.add(length, score);

			System.out.println("저장되었습니다.");
		}else {
			System.out.println("잘못된 입력입니다. 점수는 정수만 입력가능합니다.");
			return;
		}

	}

	public static void delete(int index) {
		System.out.println("delete" + index);
		//해당 인덱스의 데이터 삭제하고 뒤에데이터 인덱스 앞으로 땡겨오기 => 지영

 	}

	public static void update(int index) {
		Scanner upScanner = new Scanner(System.in);
		String name, kor, eng, math, selector;
		System.out.println("update" + index);
		System.out.println(dataList.size());
		
		//해당 인덱스의 값 수정 => 나래
		if(index < dataList.size()){
			Score sData = (Score)dataList.get(index);
			
			System.out.println("이름(" + sData.name + ")? ");
			if((name = upScanner.nextLine()) != null){}
			
			System.out.println("국어(" + sData.kor + ")? ");
			if((kor = upScanner.nextLine()) != null){}
			
			System.out.println("영어(" + sData.eng + ")? ");
			if((eng = upScanner.nextLine()) != null){}
			
			System.out.println("수학(" + sData.math + ")? ");
			if((math = upScanner.nextLine()) != null){}
			
			System.out.println("정말 변경하시겠습니까?(y / n): ");
			selector = upScanner.nextLine();
			
			switch(selector){
			case "y": case "Y":
				sData.name = name;
				sData.kor = Integer.parseInt(kor);
				sData.eng = Integer.parseInt(eng);
				sData.math = Integer.parseInt(math);
				System.out.println("변경하였습니다");
				break;
			case "n": case "N":
				System.out.println("변경이 취소되었습니다");
				break;
			}
			
		}else{
			System.out.println("찾으려는 항목의 값이 잘못되었습니다.");
		}

	}

	public static void view(int index) {
		System.out.println("view" + index);
		//해당 인덱스 값 보기 => 지영

	}

	public static void exit() {
		System.out.println("exit입니다"); 
		//같이 => 파일에 넣기
	}

}
