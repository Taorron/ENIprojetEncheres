package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.BidManager;
import bo.Utilisateur;

/**
 * Servlet implementation class EncherirServlet
 */
@WebServlet("/EncherirServlet")
public class EncherirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EncherirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("IndexServlet");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Object attribute = session.getAttribute("user");
		Utilisateur user = (attribute != null) ? (Utilisateur) attribute : null;
				
		String articleId = request.getParameter("id");
		String bidPrice = request.getParameter("proposition");
		BidManager bidManager = new BidManager();
		
		String result = null;
		try {
			result = bidManager.insertEnchere(articleId, bidPrice, user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "Une erreur est survenue";
		}
		
		session.setAttribute("user", user);
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("IndexServlet");
		rd.forward(request, response);
	}

}
