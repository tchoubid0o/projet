package hei.devweb.controllers;

import hei.devweb.metier.AjaxContactManager;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class AjaxContactServlet extends HttpServlet{
	
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
		String erreursMessage = null;
		
	    String email = request.getParameter("email");
	    String subject = request.getParameter("subject");
	    String message = request.getParameter("message");
	    
	  //Validation pseudo
        try {
            validationEmail(email);
            try {
            	validationNotEmpty(subject);
            	 try {
                 	validationNotEmpty(message);
                    if ( erreursMessage == null ) {
            		    erreursMessage = AjaxContactManager.getInstance().addContactMessage(email,subject,message);
            		    Gson gson = new Gson();
            		    response.setContentType("application/json"); 
            		    response.setCharacterEncoding("utf-8");
            		    
            		    String json = gson.toJson(erreursMessage);
            		    
            		    response.getWriter().write(json);
                    }
                 } catch ( Exception e ) {
                     erreursMessage = e.getMessage();
                 }
            } catch ( Exception e ) {
            	erreursMessage = e.getMessage();
            }
        } catch ( Exception e ) {
        	erreursMessage = e.getMessage();
        }
	}

	private void validationEmail(String user_mail) throws Exception {
		if ( user_mail != null && user_mail.trim().length() != 0 ) {
			if ( !user_mail.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
				throw new Exception( "<div class='erreur_message'>Merci de saisir une adresse mail valide.</div>" );
			}
		} else {
			throw new Exception( "<div class='erreur_message'>Merci de saisir une adresse mail.</div>" );
		}
	}
	private void validationNotEmpty(String maString) throws Exception {
		if ( maString == null || maString.trim().length() == 0 ) {
			throw new Exception( "<div class='erreur_message'>Vous devez remplir tous les champs.</div>" );
		}
	}
}
