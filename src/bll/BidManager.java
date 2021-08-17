package bll;

import java.util.ArrayList;
import java.util.List;

import bo.Enchere;
import dal.BidDAO;
import dal.DALException;
import dal.DAOFactory;

public class BidManager {
	private static BidDAO BidDao = DAOFactory.getBidDAO();
	private static BidManager instance;
	
	public static BidManager getInstance() throws BLLException{
		if(instance == null) {
			instance = new BidManager();
		}
		return instance;
	}
	
	public List<Enchere> getEncheres(){
		
		List<Enchere> result = new ArrayList<Enchere>();
		
		try {
			result = BidDao.select(null, null, null, null);
			setInfo(result);
		} catch(DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Enchere> getEncheresByArticle(int no_article){
		List<Enchere> result = new ArrayList<Enchere>();
		
		try {
			result = BidDao.select(null, no_article, null, null);
			setInfo(result);
		} catch(DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public List<Enchere> getEncheresByUtilisateur(int no_utilisateur){
		List<Enchere> result = new ArrayList<Enchere>();
		
		try {
			result = BidDao.select(no_utilisateur, null, null, null);
		} catch(DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	private void setInfo(List<Enchere> result) {
		UserManager userManager = new UserManager();
		ArticleManager articleManager = new ArticleManager();
		for (Enchere enchere : result) {
			enchere.setUtilisateur(userManager.getUserById(enchere.getUtilisateur().getNoUtilisateur()));
			enchere.setArticleVendu(articleManager.getArticleById(enchere.getArticleVendu().getNoArticle(), false));
		}
	}
}
