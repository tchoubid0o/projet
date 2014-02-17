package hei.devweb.controllers;

import hei.devweb.metier.ImagesManager;
import hei.devweb.model.Images;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IndexServlet extends GlobalInformationsServlet{

	private static final long serialVersionUID = 3904379670853846520L;

	@Override
	public void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			String sortChoice = request.getParameter("sortby");
			String stringCategorie = request.getParameter("cat");
			Integer idCategorie = 0;
			
			if(stringCategorie != null){
				idCategorie = Integer.parseInt(stringCategorie);
			}
			
			if(sortChoice == null){
				//Pour la liste entière des images (idCategorie = 0)
				List<Images> images = ImagesManager.getInstance().listerAndSortImages("all", idCategorie);
				request.setAttribute("images", images);
				request.setAttribute("images_size", images.size());
				
				request.setAttribute("cat", idCategorie);
				
	
			}
			else{
				List<Images> images = ImagesManager.getInstance().listerAndSortImages(sortChoice, idCategorie);	
				request.setAttribute("images", images);
				request.setAttribute("images_size", images.size());
				
				request.setAttribute("cat", idCategorie);
			}
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/index.jsp");
		view.forward(request, response);
	}

	@Override
	public void postRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("sendResearch") != null){
			List<Images> images = ImagesManager.getInstance().listerImagesSearched(request.getParameter("sendResearch"));
			request.setAttribute("images", images);
			request.setAttribute("images_size", images.size());
			
			request.setAttribute("cat", 0);
			
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/index.jsp");
			view.forward(request, response);
		}
	}
}
