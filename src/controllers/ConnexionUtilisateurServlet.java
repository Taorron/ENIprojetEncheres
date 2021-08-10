package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
import dal.DALException;
import dal.InterfaceEnchereDAO;

/**
 * Servlet implementation class ConnexionUtilisateurServlet
 */
@WebServlet("/ConnexionUtilisateurServlet")
public class ConnexionUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final InterfaceEnchereDAO InterfaceEnchereDAO = null;
       
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
		InterfaceEnchereDAO interfaceEnchereDAO = InterfaceEnchereDAO;
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
			List<Utilisateur> selectUtilisateur = interfaceEnchereDAO.selectUtilisateur(null, null, null, email, null, null, null, null, mdp, null, null);
			Utilisateur utilisateur = selectUtilisateur.get(0);
			if (utilisateur!=null) 
			{
				
				Utilisateur utilisateur1= new Utilisateur(utilisateur.getNoUtilisateur(), utilisateur.getPseudo(), utilisateur.getNom(), utilisateur.getPrenom(),
						utilisateur.getEmail(), utilisateur.getTelephone(),	utilisateur.getRue(), utilisateur.getCodePostal(), utilisateur.getVille(), 
						utilisateur.getMotDePasse(), utilisateur.getCredit(), utilisateur.isAdministrateur(), utilisateur.getArticleVendu(),
						utilisateur.getArticleAchete(), utilisateur.getEnchere());
				
			}
			
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
