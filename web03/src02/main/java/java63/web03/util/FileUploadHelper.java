package java63.web03.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadHelper {
	public static Map<String, String> parse(HttpServletRequest request) throws Exception{
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		HashMap<String, String> paramMap = new HashMap<>();
		
		String fileuploadRealPath = null;
		File file = null;
		String filename = null;
		int startNo = (int)(Math.random() * 1000);
		ServletContext application = request.getServletContext();
		
		for(FileItem item:items){
			if(item.isFormField()){ //1)일반 폼 데이터
				paramMap.put(item.getFieldName(), item.getString("UTF-8")); //멀티파트는 request.setEncoding 설정이 안먹히므로 직접 UTF-8설정을 해주어야 한다.
			}else{ //2) 바이너리 데이터
				fileuploadRealPath = application.getRealPath("/fileupload");
				
				filename = System.currentTimeMillis() + "_" + (++startNo); //파일이름 겹치지 않도록 처리 => 나중에 DB에 저장할 파일명
				file = new File(fileuploadRealPath + "/" + filename);
				item.write(file);

				paramMap.put(item.getFieldName(), filename);
			}
		}
		return paramMap;
	}

}
