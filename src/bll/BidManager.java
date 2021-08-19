package bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
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
	
	public void delete(int no_article) throws DALException {
		BidDao.delete(no_article);
	}
	
	public String insertEnchere(String idArticle, String prop, Utilisateur user) throws Exception {

		String result = null;
		if(idArticle != null && user != null && prop != null) {
			
			int no_article = Integer.parseInt(idArticle);
			int proposition = Integer.parseInt(prop);
			ArticleManager articleManager = new ArticleManager();
			ArticleVendu article = articleManager.getArticleById(no_article, true);
			
			//On vérifie qu'on est pas le vendeur et qu'on a les crédits
			if(article.getVendeur().getNoUtilisateur() != user.getNoUtilisateur()
					&& user.getCredit() >= proposition
					) {
				
				
				//On vérifie que l'enchere est plus grande que l'ancien enchéreur
				
				if(article.getPrixVente() < proposition) {
					UserManager userManager = new UserManager();
					
					//On recredit l'ancien enchéreur
					
					Utilisateur lastBuyer = null;
					int sellPrice = 0;
					Date lastDate = null;
					for(Enchere enchere : article.getEnchere()) {
						if(lastDate == null || enchere.getDateEnchère().after(lastDate)) {
							lastDate = enchere.getDateEnchère();
							lastBuyer = enchere.getUtilisateur();
							sellPrice = enchere.getMontantEnchere();
						}
					}
					
					if (lastBuyer != null){
						userManager.AddCredit(lastBuyer, sellPrice);
					}
					
					//On place l'enchere
					Enchere enchere = new Enchere(
							new Date(),
							proposition,
							new Utilisateur(user.getNoUtilisateur(),null,null,null,null,null,null,null,null,null,0,false),
							new ArticleVendu(no_article,null,null,null,null,0,0,null,null,null,null)
							);
					BidDao.insert(enchere);
					
					//On retire les crédits de l'user qui vient de poser l'enchere
					userManager.AddCredit(user, -proposition);
					result ="Enchere placé avec succès";
					
				} else {
					result = "Enchere plus petite que la dernière enchère";
				}
			} else {
				result = "Credit insuffisant";
			}
		} else {
			result = "Erreur : Champ incorrect";
		}
		
		return result;
	}
}
