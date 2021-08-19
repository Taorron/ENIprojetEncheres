package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.ArticleManager;
import bo.Utilisateur;
import dal.DALException;

/**
 * Servlet implementation class RetraitArticleServlet
 */
@WebServlet("/RetraitArticleServlet")
public class RetraitArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetraitArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		
		String idArticle = request.getParameter("idArticle");
		HttpSession session=request.getSession();
		Object attribute = session.getAttribute("user");
		Utilisateur user = (attribute != null) ? (Utilisateur) attribute : null;
		
		ArticleManager articleManager = new ArticleManager();
		
		boolean result;
		try {
			result = articleManager.retraitArticle(user, idArticle);
		} catch (DALException e) {
			result = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result) {
			//Redirection home servlet avec un ptit message qui dit que c'est cool
			request.setAttribute("success", "Vous avez vendu votre article et vous avez été crédité !");
			rd = request.getRequestDispatcher("/IndexServlet");
		}else{
			//Pop-up erreur
			request.setAttribute("error", "Une erreur est survenue");
			rd = request.getRequestDispatcher("/AfficherVente");
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
