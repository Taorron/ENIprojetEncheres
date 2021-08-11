package bll;

import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import dal.ArticleDAO;
import dal.DALException;
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
	
	public List<ArticleVendu> getArticles(){
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		
		try {
			result = articleDao.select(null, null, null, null, null, null, null, null, null);
			setInfo(result);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return result;
	}
	
	public void setInfo(List<ArticleVendu> articles){
		UserManager userManager = new UserManager();
		CategoryManager categoryManager = new CategoryManager();
		for (ArticleVendu articleVendu : articles) {
			//TODO : Set Retrait et Acheteur si il existe
			articleVendu.setCategorie(categoryManager.getCategoryById(articleVendu.getCategorie().getNoCategorie()));
			articleVendu.setVendeur(userManager.getUserById(articleVendu.getVendeur().getNoUtilisateur()));
		}
	}
}
