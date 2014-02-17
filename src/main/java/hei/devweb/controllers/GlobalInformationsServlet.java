package hei.devweb.controllers;

import hei.devweb.metier.GlobalInformationsManager;
import hei.devweb.metier.ImagesManager;
import hei.devweb.model.Images;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class GlobalInformationsServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6724419434412485659L;

	public abstract void getRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	public abstract void postRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//On récupère le nom de la page pour pouvoir activer les liens du header
		String uri = request.getRequestURI();
		String pageName = uri.substring(uri.lastIndexOf("/")+1);	
		request.setAttribute("pageName", pageName);
		
		Integer nbImages = GlobalInformationsManager.getInstance().countImagesDataBase();
		Integer nbUsers = GlobalInformationsManager.getInstance().countUsersDataBase();
		Integer nbVotes = GlobalInformationsManager.getInstance().countVotesDataBase();
		
		request.setAttribute("nbImages", nbImages);
		request.setAttribute("nbUsers", nbUsers);
		request.setAttribute("nbVotes", nbVotes);
		
		//Pour le menu de gauche images récentes pour toutes les catégories (idCategorie = 0)
		List<Images> imagesn = ImagesManager.getInstance().listerAndSortImages("newest", 0);
		request.setAttribute("imagesn", imagesn);
		
		ArrayList<String> categories = GlobalInformationsManager.getInstance().returnAllCategorie();
		request.setAttribute("categories", categories);
		
		getRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Integer nbImages = GlobalInformationsManager.getInstance().countImagesDataBase();
		Integer nbUsers = GlobalInformationsManager.getInstance().countUsersDataBase();
		Integer nbVotes = GlobalInformationsManager.getInstance().countVotesDataBase();
		
		request.setAttribute("nbImages", nbImages);
		request.setAttribute("nbUsers", nbUsers);
		request.setAttribute("nbVotes", nbVotes);
		
		//Pour le menu de gauche images récentes pour toutes les catégories (idCategorie = 0)
		List<Images> imagesn = ImagesManager.getInstance().listerAndSortImages("newest", 0);
		request.setAttribute("imagesn", imagesn);
		
		ArrayList<String> categories = GlobalInformationsManager.getInstance().returnAllCategorie();
		request.setAttribute("categories", categories);
		
		postRequest(request, response);
	}

}
