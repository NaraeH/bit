package com.bit;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Score {
	static ArrayList<Score> dataList = new ArrayList<Score>();
	static String[] inputList = new String[4];
	static Scanner in = new Scanner(System.in);
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

	public static boolean menu() throws Exception{
		try {
			String str = null;

			System.out.println("명령> ");
			str = in.nextLine();

			inputList = (str.split(" "));

			switch (inputList[0].toLowerCase()) {
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
			
		} catch (IndexOutOfBoundsException e) {
			System.out.print("입력이 잘못 되었습니다. => ");
			System.out.println(e.getMessage());
			return true;
		} catch (NumberFormatException e){
			System.out.println("잘못된 형식의 데이터입니다.");
			return true;
		}
	}

	public static void help() {
		System.out.println("help");
		System.out.println("view 인덱스");
		System.out.println("add 이름 국어 영어 수학");
		System.out.println("delete 인덱스");
		System.out.println("update 인덱스");
		System.out.println("exit");

	}

	public static void list() {
		if (dataList.isEmpty()) {
			System.out.println("출력할 데이터가 존재하지 않습니다.");
		} else {
			for (int i = 0; i <= (dataList.size() - 1); i++) {// 돌아가며 리스트 전부 출력
				System.out.print(i + " ");
				System.out.print(dataList.get(i).name + " ");
				System.out.print(dataList.get(i).kor + " ");
				System.out.print(dataList.get(i).eng + " ");
				System.out.print(dataList.get(i).math + "\n");
			}
		}
	}

	public static void add(String name, int kor, int eng, int math) {
		Score score = new Score(name, kor, eng, math);

		dataList.add(score);
		System.out.println("저장되었습니다.");

	}

	public static void delete(int index) {
		System.out.println(dataList.get(index).name + "의 성적을 삭제하시겠습니까? (y/n)");

		switch (in.nextLine().toLowerCase()) {
		case "y":
			dataList.remove(index);
			System.out.println(index + "번째 데이터를 삭제하였습니다.");
			break;
		case "n":
			System.out.println("삭제를 취소하였습니다");
			break;
		default:
			System.out.println("유효하지 않은 입력입니다");
		}
	}

	public static void update(int index) {
		String name, kor, eng, math, selector;

		Score sData = dataList.get(index);

		System.out.println("이름(" + sData.name + ")? ");
		name = in.nextLine();

		System.out.println("국어(" + sData.kor + ")? ");
		kor = in.nextLine();

		System.out.println("영어(" + sData.eng + ")? ");
		eng = in.nextLine();

		System.out.println("수학(" + sData.math + ")? ");
		math = in.nextLine();

		System.out.println("정말 변경하시겠습니까?(y / n): ");
		selector = in.nextLine();

		switch (selector.toLowerCase()) {
		case "y":
			sData.name = (name.equals("")) ? sData.name : name;
			sData.kor = (kor.equals("")) ? sData.kor : Integer.parseInt(kor);
			sData.eng = (eng.equals("")) ? sData.eng : Integer.parseInt(eng);
			sData.math = (math.equals("")) ? sData.math : Integer
					.parseInt(math);
			System.out.println("변경하였습니다");
			break;
		case "n":
			System.out.println("변경이 취소되었습니다");
			break;
		}

	}

	public static void view(int index) {
		int sum = 0;
		float avg = 0.0f;

		sum = dataList.get(index).kor + dataList.get(index).eng	+ dataList.get(index).math;
		avg = sum / 3.0f;

		System.out.println("인덱스: " + index);
		System.out.println("이름: " + dataList.get(index).name);
		System.out.println("국어: " + dataList.get(index).kor);
		System.out.println("영어: " + dataList.get(index).eng);
		System.out.println("수학: " + dataList.get(index).math);
		System.out.println("합계: " + sum);
		System.out.println("평균: " + avg);
	}

	public static void exit() throws Exception {
		FileOutputStream out = new FileOutputStream("score.txt");
		DataOutputStream out2 = new DataOutputStream(out);

		for (int i = 0; i < dataList.size(); i++) {
			out2.writeUTF(dataList.get(i).name);
			out2.writeInt(dataList.get(i).kor);
			out2.writeInt(dataList.get(i).eng);
			out2.writeInt(dataList.get(i).math);
		}
		System.out.println("파일이 성공적으로 저장되었습니다.");

		out2.close();
		out.close();
	}
}
