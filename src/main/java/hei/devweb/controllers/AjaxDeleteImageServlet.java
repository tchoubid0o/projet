package hei.devweb.controllers;

import hei.devweb.metier.ImagesManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxDeleteImageServlet extends HttpServlet{
	
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
		Integer idUser = (Integer) request.getSession().getAttribute("idUser");
		ImagesManager.getInstance().deleteImage(request.getParameter("cleeImg"), idUser);
		request.setAttribute("cleeImg", request.getParameter("cleeImg") );
	}
}
