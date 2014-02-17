package hei.devweb.controllers;

import hei.devweb.metier.AjaxNewsletterManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class AjaxNewsletterServlet extends HttpServlet{
	
	private static final long serialVersionUID = 5274442345250866770L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	                       throws ServletException, IOException {	    
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/404.jsp");
		view.forward(request, response);
		//On n'autorise pas l'affichage de la page
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	                      throws ServletException, IOException {
	    String email = request.getParameter("email");
	    String result = AjaxNewsletterManager.getInstance().addMailInNewsletter(email);
	    Gson gson = new Gson();
	    response.setContentType("application/json"); 
	    response.setCharacterEncoding("utf-8");
	    
	    String json = gson.toJson(result);	    
	    response.getWriter().write(json);
	}


}
