package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
import dal.DALException;
import dal.EnchereDAO;
import dal.InterfaceEnchereDAO;

/**
 * Servlet implementation class ConnexionUtilisateurServlet
 */
@WebServlet("/ConnexionUtilisateurServlet")
public class ConnexionUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final InterfaceEnchereDAO InterfaceEnchereDAO = null;
	EnchereDAO enchereDAO = new EnchereDAO();
       
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
		String email=null;
		String motDePasse=null;
		String pseudo=null;
		
		if (identifiant!=null) 
		{
			if (identifiant.contains("@")) 
			{
				email=identifiant;
			}
			else
			{
				pseudo=identifiant;
			}
		}
		if (mdp!=null) 
		{
			motDePasse=mdp;
		}
		try {
			List<Utilisateur> selectUtilisateur =enchereDAO.selectUtilisateur(null, null, null, null, "Taorron@gmail.com", null, null, null, null, "123456", null, null);
//			List<Utilisateur> selectUtilisateur = enchereDAO.selectUtilisateur(null, pseudo, null, null, email, null, null, null, null, motDePasse, null, null);
			Utilisateur utilisateur = selectUtilisateur.get(0);
			HttpSession session=request.getSession();
					
			session.setAttribute("user", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/premierePageDeCoUtilisateur.jsp");
			rd.forward(request, response);
			
		} catch (DALException e) {
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
