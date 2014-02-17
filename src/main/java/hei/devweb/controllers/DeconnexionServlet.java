package hei.devweb.controllers;

import hei.devweb.metier.ImagesManager;
import hei.devweb.model.Images;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeconnexionServlet extends GlobalInformationsServlet{

	private static final long serialVersionUID = -8044405890640694131L;

	@Override
	public void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Nécessaire à l'affichage des dernières images publiées
			List<Images> images = ImagesManager.getInstance().listerImages();
			request.setAttribute("images", images);
		// Fin dernière images publiées
				
		request.getSession().removeAttribute("idUser");
		request.getSession().removeAttribute("user_pseudo");
		request.getSession().removeAttribute("user_mail");
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/deconnexion.jsp");
		view.forward(request, response);
	}

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
