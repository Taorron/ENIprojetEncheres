package controllers;

import java.io.IOException;

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
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/CreateUser.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pseudo = request.getParameter("inputNickname");
		String name = request.getParameter("inputName");
		String firstName = request.getParameter("inputFirstname");
		String email = request.getParameter("inputMail");
		String phone = request.getParameter("inputPhone");
		String street = request.getParameter("inputAddress");
		String zip = request.getParameter("inputZip");
		String city = request.getParameter("inputCity");
		String pwd = request.getParameter("inputPassword");
		String confirmPwd = request.getParameter("inputConfirmPassword");

		
		UserManager userManager = new UserManager();
		boolean checkInfo=true;
		
			try {
				checkInfo = userManager.checkCredentialNewUser(pseudo, name, firstName, email, phone, street, zip, city, pwd, confirmPwd);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			if (checkInfo) 
			{
				Utilisateur newUser = userManager.createUser(pseudo, name, firstName, email, phone, street, zip, city, pwd);
				if (newUser != null) {
					System.out.println("Utilisateur crée id : " + newUser.getNoUtilisateur());
				} else {
					System.out.println("Une erreur est survenu à la creation de compte");
				}
			}
			else
			{
				System.out.println("Erreur données non conforme");
			}
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/connexion.jsp");
			rd.forward(request, response);
	}

}
