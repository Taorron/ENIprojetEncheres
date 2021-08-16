package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.UserManager;
import bo.Utilisateur;

/**
 * Servlet implementation class VoirProfilServlet
 */
@WebServlet("/VoirProfilServlet")
public class VoirProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoirProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userParameter = request.getParameter("sellerId");
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		boolean canEditProfil = false;
		Utilisateur seller = null;		
		
		if (userParameter != null) {
			Integer id = Integer.parseInt(request.getParameter("sellerId"));
			UserManager um = new UserManager();
			seller = um.getUserById(id);
			//Si user connecté passe par la methode get pour voir son profil
			//il peut le modifier
			if(user != null) {
				canEditProfil = (id == user.getNoUtilisateur()) ? true : false;
			}
			
		} else {
			seller = user;
			canEditProfil = true;
		}
		
		request.setAttribute("canEdit", canEditProfil);
		request.setAttribute("seller", seller);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/profil.jsp");
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
