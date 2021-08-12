package controllers;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UserManager;
import bo.Utilisateur;
import dal.DALException;

/**
 * Servlet implementation class ValidationModifProfilServlet
 */
@WebServlet("/ValidationModifProfilServlet")
public class ValidationModifProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationModifProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
		
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String mdpActuel = request.getParameter("mdpActuel");
		String nouveauMdp = request.getParameter("nouveauMdp");
		String confirmationNouveauMdp = request.getParameter("confirmationNouveauMdp");
		
		UserManager userManager = new UserManager();
		boolean verificationInfoModification=true;
		
			try {
				verificationInfoModification = userManager.verificationInfoModification(utilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal,ville,mdpActuel, nouveauMdp, confirmationNouveauMdp);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
//		boolean matches = Pattern.matches("[0-9]+", "12345");
		if (!verificationInfoModification) 
		{
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/modifierProfil.jsp");
			rd.forward(request, response);
			System.out.println("erreur");
		}
		else
		{
			try {
				userManager.update(utilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, nouveauMdp, confirmationNouveauMdp);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/profil.jsp");
			rd.forward(request, response);
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
