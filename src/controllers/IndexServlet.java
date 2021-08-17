package controllers;

import java.io.IOException;
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
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Session attribute
		HttpSession session=request.getSession();
		Object attribute = session.getAttribute("user");
		
		//Requestt parameter
		String disconnect = request.getParameter("disconnect");
		String resherchName = request.getParameter("inputResherch");
		String resherchCategory = request.getParameter("category");
		String encheresOuvertes = request.getParameter("encheresOuvertes");
		String mesEncheresEnCours = request.getParameter("mesEncheresEnCours");
		String mesEncheresRemportees = request.getParameter("mesEncheresRemportees");
		String mesVentesEnCours = request.getParameter("mesVentesEnCours");
		String ventesNonDebute = request.getParameter("ventesNonDebute");
		String ventesTerminees = request.getParameter("ventesTerminees");
		String resherchVentes = request.getParameter("ventes");
		String resherchAchats = request.getParameter("achats");
		
		//Disconnect user
		if (attribute != null && disconnect != null) 
		{
			System.out.println("Deconnection de l'utilisateur id "+((Utilisateur)(attribute)).getNoUtilisateur() );
			session.invalidate();
		}
		
		//Get CategoryList
		CategoryManager categoryManager = new CategoryManager();
		List<Category> categories = categoryManager.select(null, null);
		request.setAttribute("categories", categories);
		
		//Get ArticleList
		ArticleManager articleManager = new ArticleManager();
		
		Utilisateur user = (attribute != null) ? (Utilisateur) attribute : null;

		List<ArticleVendu> articles = articleManager.getArticlesBidding(
				user, resherchName, resherchCategory, encheresOuvertes,mesEncheresEnCours,
				mesEncheresRemportees,mesVentesEnCours,	ventesNonDebute, 
				ventesTerminees, resherchVentes, resherchAchats);
		
		System.out.println("nb enchere en cours : "+articles.size());
		
		request.setAttribute("articles", articles);
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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
