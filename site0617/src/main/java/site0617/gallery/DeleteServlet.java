package site0617.gallery;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//jsp를 이용해서도, 삭제 업무를 처리할 수 있지만, 공부목적으로 서블릿으로 처리한다
public class DeleteServlet extends HttpServlet{
	ServletContext context; //현재 서블릿이 실행되고 있는 웹 어플리케이션
	
	public void init(ServletConfig config) throws ServletException {
		context=config.getServletContext();
	}
	
	
	//doXXX형 메서드로 처리해보자!!
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기!!
		String gallery_id = request.getParameter("galley_id");
		String filename = request.getParameter("filename");
		
		//삭제 처리(파일 삭제 및 레코드 삭제)
		String path=context.getRealPath("/data");
		File file=new File(path+"/"+filename);
		
		
		
	}
	
	
}
