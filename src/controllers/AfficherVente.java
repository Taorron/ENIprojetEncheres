package controllers;

import java.io.IOException;
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
import bo.Utilisateur;

/**
 * Servlet implementation class AfficherVente
 */
@WebServlet("/AfficherVente")
public class AfficherVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String IdVente = request.getParameter("IdVente");
		ArticleManager articleManager = new ArticleManager();
		ArticleVendu article = articleManager.getArticleById(Integer.parseInt(IdVente), true);
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur)session.getAttribute("user");
		request.setAttribute("article", article);
		request.setAttribute("date", new Date());
		boolean ifModif=articleManager.ifModif(user, article);
		RequestDispatcher rd=null;
		if (ifModif) 
		{
			CategoryManager categoryManager = new CategoryManager();
			List<Category> categories = categoryManager.select(null, null);
			request.setAttribute("categories", categories);
			rd = request.getRequestDispatcher("WEB-INF/nouvelleVente.jsp");
		}
		else
		{
			rd = request.getRequestDispatcher("WEB-INF/afficherArticle.jsp");
			
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
