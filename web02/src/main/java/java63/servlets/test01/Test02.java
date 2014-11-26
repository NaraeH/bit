/*
 < 한글 출력시 깨지는 문제 해결 >
이유: ServletResponse가 준 출력 스트림은 기본적으로 출력할 때 ISO8859-1로 인코딩한다.
문제점: 한글은 ISO8859-1 문자집합에 없기 때문에 ?로 대체되어 출력한다(깨진다).

해결책: ServletResponse로 부터 "출력 스트림을 얻기 전"에 출력할 내용의 타입과 문자집합을 설정한다.
 => response.setContentType("text/plain;charset=UTF-8")
 => response.setCharacterEncoding("UTF-8");
 
 <MIME 타입>
 => 콘텐츠의 타입을 지정
  ex) text/plain
 */
package java63.servlets.test01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/test01/Test02")
public class Test02 extends GenericServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		//0. 인코딩 설정하기
		//방법1.
		//response.setCharacterEncoding("UTF-8");
		//방법2.
		response.setContentType("text/plain;charset=UTF-8");
		
		//1.ServletResponse도구를 사용하여 출력 스트림을 준비한다.
		PrintWriter out = response.getWriter();
		
		//2.출력 스트림을 사용하여 웹 브라우저로 출력한다.
		out.println("된당!!!!");
		
	}
}
