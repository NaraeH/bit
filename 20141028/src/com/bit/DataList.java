package com.bit;

public class DataList {

	public static void main(String[] args) {
		Boolean flag = null;
		
		while(true){
			
			flag = Score.menu();
			
			if(flag.equals(false)){
				break;
			}
		}

	}

}
