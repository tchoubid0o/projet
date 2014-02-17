package hei.devweb.controllers;

import hei.devweb.metier.ImagesManager;
import hei.devweb.model.Images;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
maxFileSize=1024*1024*50,          // 50 MB
maxRequestSize=1024*1024*100)      // 100 MB
public class ProfilServlet extends GlobalInformationsServlet{

	private static final long serialVersionUID = -7188152923285024456L;

	
	@Override
	public void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		Integer idUser = (Integer) request.getSession().getAttribute("idUser");
		if(idUser != null){
			List<Images> images = ImagesManager.getInstance().listerMesImages(idUser);		
			request.setAttribute("images", images);
			request.setAttribute("images_size", images.size());
			
			request.setAttribute("erreurs", "");
			
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/profil.jsp");
			view.forward(request, response);
		}
		else{
			request.setAttribute("erreurs", "<div class='erreur_message'>Vous devez être connecté pour uploader des fichiers.</div>");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/profil.jsp");
			view.forward(request, response);
		}
	}

	public void postRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> erreursMessage = new HashMap<String, String>();
		Integer idUser = (Integer) request.getSession().getAttribute("idUser");
		String resultat = "";
		String titreImg = "";
		String textImg = "";
		String cleeImg = "";
		String extImg = "";
		Integer idCategorie = null;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
		} else {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<?> items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			Iterator<?> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString();
					
					if(name.equals("titreImg")){
						titreImg = value;
					}
					if(name.equals("textImg")){
						textImg = value;
					}
					if(name.equals("categorie")){
						idCategorie = Integer.parseInt(value);
					}
				} else {
					try {

						String itemName = item.getName();

						//On génère le nouveau nom de l'image que l'on uploadera
						Random generator = new Random();
						int r = Math.abs(generator.nextInt());

						String reg = "[.*]";
						String replacingtext = "";
						Pattern pattern = Pattern.compile(reg);
						Matcher matcher = pattern.matcher(itemName);
						StringBuffer buffer = new StringBuffer();

						while (matcher.find()) {
							matcher.appendReplacement(buffer, replacingtext);
						}

						//On récupère l'extension de l'image uploadée
						int IndexOf = itemName.indexOf("."); 
						String domainName = itemName.substring(IndexOf);

						//On renomme l'image uploadée
						String finalimage = r+domainName;
						
						cleeImg = String.valueOf(r);
						extImg = domainName;
						
						//à modifier par le chemin d'accès au projet
						File savedFile = new File("C:/Users/Tchou/workspace/miniProjet/src/main/webapp/images/uploads/"+finalimage);
						item.write(savedFile);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Images image = new Images(null, cleeImg, extImg, titreImg, textImg, null, 0, null, idCategorie);
			resultat = ImagesManager.getInstance().ajouterImage(image, idUser);
			
		}
		 //Stockage des erreurs et du résultat
      	request.setAttribute("erreurs", erreursMessage );
      	request.setAttribute("resultat", resultat );
      	
      	List<Images> images = ImagesManager.getInstance().listerMesImages(idUser);		
		request.setAttribute("images", images);
		request.setAttribute("images_size", images.size());
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/pages/profil.jsp");
		view.forward(request, response);
	}

}