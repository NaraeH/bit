package com.bit;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

  public static void main(String[] args) throws Exception {

		try {
			FileInputStream in = new FileInputStream("score.txt");
			DataInputStream in2 = new DataInputStream(in);

			for (int i = 0; i < in2.available(); i++) {
				Score inScore = new Score(in2.readUTF(), in2.readInt(),	in2.readInt(), in2.readInt());
				Score.dataList.add(inScore);
			}

			in2.close();
			in.close();

		} catch (FileNotFoundException e) {
			//System.out.println("현재 존재하는 파일이 없습니다.");
		} finally {
			boolean flag = true;

			while (flag) {
				flag = Score.menu();
			}
			Score.in.close();
		}
	}
}
