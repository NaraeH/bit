package java002.test07.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassFinder {
	ArrayList<String> list = new ArrayList<String>();
	String basePackage;
	
	public ClassFinder(String basePackage){
		this.basePackage = basePackage;
	}
	
	// ArrayList는 List interface로 구현된 것.
	// 왜 List를 리턴할까? => LinkedList, ArrayList .. 등 List 밑의 어떤 것을 리턴하더라도 가능!! =>
	// 좀 더 확장성이 좋음
	public List<String> getClassList() {
		return list;
	}

	public void find(String path) {
		try {
			File file = new File(path);
			
			if (file.isFile()) {
				if(file.getName().endsWith(".class")) {
					String className = path.substring(6).replace("/", ".").replace("\\", ".").replaceAll("\\.class", "");
					
					if(className.startsWith(basePackage)){
						list.add(className);
						//System.out.println(className);
					}
				}
				return;

			} else if (file.isDirectory()) {
				String[] fileNames = file.list();
				
				for (String fileName : fileNames) {
					find(path + "/" + fileName);
				}
			}
		} catch (Exception e) {

		}
	}
	
	

/*	//test용
	public static void main(String[] args) {
		ClassFinder f = new ClassFinder("java002.test07");
		f.find("./bin");
		
		List<String> classNames = f.getClassList();
		
		for(String className:classNames){
			System.out.println(className);
		}
		
	}*/
}
