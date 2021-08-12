package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.BLLException;
import bll.UserManager;
import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
import dal.DALException;


/**
 * Servlet implementation class ConnexionUtilisateurServlet
 */
@WebServlet("/ConnexionUtilisateurServlet")
public class ConnexionUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnexionUtilisateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String identifiant=request.getParameter("identifiant");
		
		String mdp = request.getParameter("mdp");
	
	
		try {
			
			UserManager manager = UserManager.getInstance();
			Utilisateur utilisateur=manager.connect(identifiant, mdp);
			RequestDispatcher rd=null;
			if (utilisateur!=null) 
			{
				
				HttpSession session=request.getSession();
				session.setAttribute("user", utilisateur);
				
				Cookie cookie = new Cookie("identifiant", identifiant);
				Cookie cookieMDP = new Cookie("mdp", mdp);
				response.addCookie(cookie);
				response.addCookie(cookieMDP);
				

				
				rd = request.getRequestDispatcher("/WEB-INF/premierePageDeCoUtilisateur.jsp");
			}
			else
			{
				rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
				request.setAttribute("erreur","Mot de passe et/ou identifiant introuvable");
				
			}
			rd.forward(request, response);
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
