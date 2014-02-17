package hei.devweb.controllers;

import hei.devweb.metier.ImagesManager;
import hei.devweb.model.Images;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ModificationImageServlet extends HttpServlet{

	private static final long serialVersionUID = 5321683035708509573L;

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
		Map<String, String> erreursMessage = new HashMap<String, String>();
		Integer idUser = (Integer) request.getSession().getAttribute("idUser");
		String resultat = "";
		String titreImg = "";
		String textImg = "";
		String cleeImg = "";
		String cleePrevImg = "";
		String extPrevImg = "";
		String extImg = "";
		String domainName = "";
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
					if(name.equals("cleePrevImg")){
						cleePrevImg = value;
					}
					if(name.equals("extPrevImg")){
						extPrevImg = value;
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
						
						if(!(cleePrevImg+extPrevImg).equals(itemName)){
							int IndexOf = itemName.indexOf("."); 
							domainName = itemName.substring(IndexOf);
							//On renomme l'image uploadée
							String finalimage = r+domainName;
							
							System.out.println("test1");
						//On récupère l'extension de l'image uploadée
						
						
						cleeImg = String.valueOf(r);
						extImg = domainName;
						
						//à modifier par le chemin d'accès au projet
						File savedFile = new File("C:/Users/Tchou/workspace/miniProjet/src/main/webapp/images/uploads/"+finalimage);
						item.write(savedFile);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}	
			if(cleeImg.equals("")){cleeImg=cleePrevImg;}
			Images image = new Images(null, cleeImg, extImg, titreImg, textImg, null, 0, null, idCategorie);
			resultat = ImagesManager.getInstance().modifierImage(cleePrevImg, image, idUser);
		}
		
		
		 //Stockage des erreurs et du résultat
      	request.setAttribute("erreurs", erreursMessage );
      	request.setAttribute("resultat", resultat );
      	
      	List<Images> images = ImagesManager.getInstance().listerMesImages(idUser);		
		request.setAttribute("images", images);
		request.setAttribute("images_size", images.size());
		
		response.sendRedirect("profil");
	}
}
