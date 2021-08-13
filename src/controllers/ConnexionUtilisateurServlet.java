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
import bll.CategoryManager;
import bll.UserManager;
import bo.ArticleVendu;
import bo.Category;
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
		String seSouvenir = request.getParameter("seSouvenir");
	
	
		try {
			
			UserManager manager = UserManager.getInstance();
			Utilisateur utilisateur=manager.connect(identifiant, mdp);
			RequestDispatcher rd=null;
			if (utilisateur!=null) 
			{
				Cookie cookie;
				Cookie cookieMDP;
				Cookie seSouvenirChecked;
				if (seSouvenir!=null && seSouvenir.equals("on")) 
				{
										
					cookie = new Cookie("identifiant", identifiant);
					cookieMDP = new Cookie("mdp", mdp);
					seSouvenirChecked = new Cookie("seSouvenir", seSouvenir);
					
				}
				else
				{

					cookie = new Cookie("identifiant", "");
					cookieMDP = new Cookie("mdp", "");
					seSouvenirChecked = new Cookie("seSouvenir", "");
					
				}
				response.addCookie(cookie);
				response.addCookie(cookieMDP);
				response.addCookie(seSouvenirChecked);
				
				HttpSession session=request.getSession();
				session.setAttribute("user", utilisateur);

				CategoryManager categoryManager = new CategoryManager();
				List<Category> categories = categoryManager.select(null, null);
				request.setAttribute("categories", categories);
				rd = request.getRequestDispatcher("index.jsp");
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
