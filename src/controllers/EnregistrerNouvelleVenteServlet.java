package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticleManager;
import bll.CategoryManager;
import bo.ArticleVendu;
import bo.Category;
import bo.Retrait;
import bo.Utilisateur;

/**
 * Servlet implementation class EnregistrerNouvelleVenteServlet
 */
@WebServlet("/EnregistrerNouvelleVenteServlet")
public class EnregistrerNouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrerNouvelleVenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			ArticleManager articleManager = new ArticleManager();
			String nomArticle=request.getParameter("article");
			String description=request.getParameter("description");
			String categorie=request.getParameter("category");
			String miseAPrix=request.getParameter("prix");
			String dateDebutEncheres=request.getParameter("dateDebut");
			String dateFinEncheres=request.getParameter("dateFin");
			String rue=request.getParameter("rue");
			String cp=request.getParameter("cp");
			String ville=request.getParameter("ville");
			
			HttpSession session = request.getSession();
			Utilisateur user = (Utilisateur)session.getAttribute("user");
			
			ArticleVendu article=articleManager.verifArticle(0, nomArticle, description, dateDebutEncheres, dateFinEncheres, 
					 miseAPrix, categorie, user, null, rue, cp, ville);
			
			if (article!=null) 
			{
				articleManager.saveArticle(article);
				RequestDispatcher rd = request.getRequestDispatcher("/IndexServlet");
				rd.forward(request, response);
			}
			else
			{
				CategoryManager categoryManager = new CategoryManager();
				List<Category> categories = categoryManager.select(null, null);
				request.setAttribute("categories", categories);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/nouvelleVente.jsp");
				rd.forward(request, response);
			}
//			ArticleVendu article=new ArticleVendu(0, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, miseAPrix, new Category(categorie, null), new Retrait(rue, cp, ville), user,null);
			
		} catch (Exception e) {
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
