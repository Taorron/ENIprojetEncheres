package bll;

import java.util.List;

import bo.ArticleVendu;
import dal.ArticleDAO;
import dal.DAOFactory;

public class ArticleManager {

	private ArticleDAO articleDao = DAOFactory.getArticleDAO();
	private static ArticleManager instance;
	
	//Singleton
	public static ArticleManager getInstance() throws BLLException{
		
		if(instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	public void setInfo(List<ArticleVendu> articles){
		UserManager userManager = new UserManager();
		for (ArticleVendu articleVendu : articles) {
			//TODO : Set Category, Retrait, Acheteur si il existe
			articleVendu.setVendeur(userManager.getUtilisateurById(articleVendu.getVendeur().getNoUtilisateur()));
		}
	}
}
