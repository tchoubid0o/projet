package hei.devweb.controllers;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FaqServlet extends GlobalInformationsServlet {
	private static final long serialVersionUID = 1L;

	public void getRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/faq.jsp");
		view.forward(request, response);
	}

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
