package bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bo.ArticleVendu;
import bo.Enchere;
import bo.EtatVente;
import bo.Utilisateur;
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
	
	public List<ArticleVendu> getArticlesBidding(){
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		
		List<ArticleVendu> allArticles = getArticles();
		
		for (ArticleVendu articleVendu : allArticles) {
			if(articleVendu.getEtatVente() == EtatVente.ENCOURS) {
				result.add(articleVendu);
			}
		}
		
		return result;
		
	}
	
	public ArticleVendu getArticleById(int no_article) {
		ArticleVendu result = null;		
		try {
			
			List<ArticleVendu> list = articleDao.select(no_article, null, null, null, null, null, null, null, null);
			if(!list.isEmpty()) {
				result = list.get(0);
			}
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
		}
		
		return result;
	}
	
	public void setInfo(List<ArticleVendu> articles){
		UserManager userManager = new UserManager();
		CategoryManager categoryManager = new CategoryManager();
		WithdrawManager withdrawManager = new WithdrawManager();
		BidManager bidManager = new BidManager();
		for (ArticleVendu articleVendu : articles) {
			int no_article = articleVendu.getNoArticle();
			articleVendu.setCategorie(categoryManager.getCategoryById(articleVendu.getCategorie().getNoCategorie()));
			articleVendu.setVendeur(userManager.getUserById(articleVendu.getVendeur().getNoUtilisateur()));
			articleVendu.setRetrait(withdrawManager.getRetraitByArticleId(no_article));
			articleVendu.setEnchere((ArrayList<Enchere>)(bidManager.getEncheresByArticle(no_article)));
			
			findBuyerAndSetSellPrice(articleVendu);
			System.out.println("Fin setInfo");
			
		}
	}
	
	public void findBuyerAndSetSellPrice(ArticleVendu article) {
		Utilisateur buyer = null;
		int sellPrice = article.getPrixVente();
		if(article.getEtatVente() == EtatVente.TERMINER) {
			
			Date lastDate = null;
			for(Enchere enchere : article.getEnchere()) {
				if(lastDate == null || enchere.getDateEnchère().after(lastDate)) {
					lastDate = enchere.getDateEnchère();
					buyer = enchere.getUtilisateur();
					sellPrice = enchere.getMontantEnchere();
				}
			}
		}
		
		article.setAcheteur(buyer);
		article.setPrixVente(sellPrice);
	}
	
	
}
