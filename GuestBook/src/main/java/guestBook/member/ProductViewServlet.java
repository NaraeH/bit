package guestBook.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet("/member/view")
public class ProductViewServlet extends GenericServlet{
	private static final long serialVersionUID = 1L;
	
	SqlSessionFactory sqlSessionFactory = null;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));

		/*ProductDao productDao = (ProductDao) this.getServletContext().getAttribute("productDao");
		Product product = productDao.selectOne(no);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		
		//다른 서블릿을 실행 => 실행 후 제어권이 되돌아 온다.
		RequestDispatcher rd = request.getRequestDispatcher("/common/header");
		rd.include(request, response); 

		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class='container'>");
		out.println("<h1>제품정보</h1>");
		
		//placeholder: 입력 전에 미리 들어가 있는 값, 누르면 없어지고
		out.println("<form class='form-horizontal' role='form' " + "action='update' method='post'>");
		out.println(" <div class='form-group'>");
		out.println("   <label for='no' class='col-sm-2 control-label'>#</label>");
		out.println("   <div class='col-sm-10'>");
		out.println("    <input type='text' class='form-control' readonly id='no' name='no' value='"
				+ product.getNo() + "'>");
		out.println("    </div>");
		out.println("  </div>");
		  
		out.println("  <div class='form-group'>");
		out.println("    <label for='name' class='col-sm-2 control-label'>제품</label>");
		out.println("    <div class='col-sm-10'>");
		out.println("      <input type='text' class='form-control' id='name' name='name' value='" + product.getName() + "'>");
		out.println("    </div>");
		out.println(" </div>");
		
		out.println("  <div class='form-group'>");
		out.println("    <label for='qty' class='col-sm-2 control-label'>수량</label>");
		out.println("    <div class='col-sm-10'>");
		out.println("      <input type='text' class='form-control' id='qty' name='qty' value='" + product.getQuantity() + "'>");
		out.println("    </div>");
		out.println("  </div>");
		
		out.println("  <div class='form-group'>");
		out.println("    <label for='mkno' class='col-sm-2 control-label'>제조사</label>");
		out.println("    <div class='col-sm-10'>");
		out.println("    <input type='text' class='form-control' id='mkno' name='mkno' value='"	+ product.getMakerNo() + "'>");
		out.println("    </div>");
		out.println(" </div>");
		
		out.println("<div class='form-group'>");
		out.println("  <button id='btnUpdate' type='submit' class='btn btn-primary'>변경</button>");
		out.println("  <button id='btnDelete' type='button' class='btn btn-primary'>삭제</button>");
		out.println("  <button id='btnCancel' type='button' class='btn btn-primary'>취소</button>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");

		out.println("<script src='../../js/jquery-1.11.1.js'></script>");
		out.println("<script>");
		out.println("$('#btnCancel').click(function(){");
		out.println("	history.back();");
		out.println("});");

		out.println("$('#btnDelete').click(function(){");
		out.println("	if(window.confirm('삭제하시겠습니까?z')){");
		out.println("		location.href = 'delete?no=" + product.getNo()+ "';");
		out.println("	}");
		out.println("});");
		
		out.println("$('#btnUpdate').click(function(){");
		out.println("	if($('#name').val().length == 0){");
		out.println("		alert('제품명은 필수입력 항목입니다.');");
		out.println("		return false;");
		out.println("	}");
			
		out.println("	if($('#qty').val().length == 0){");
		out.println("		alert('수량은 필수입력 항목입니다.');");
		out.println("return false;");
		out.println("	}");

		out.println("if($('#mkno').val().length == 0){");
		out.println("	alert('제조사 번호는 필수입력 항목입니다.');");
		out.println("	return false;");
		out.println("}");
		out.println("});");
			
		out.println("</script>");
		
		out.println("</body>");
		out.println("</html>");*/

	}
}