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
	
	public List<ArticleVendu> getArticlesBidding(Utilisateur user, String resherchName, String resherchCategory, String encheresOuvertes,
			String mesEncheresEnCours, String mesEncheresRemportees, String mesVentesEnCours,
			String ventesNonDebute, String ventesTerminees, String resherchVentes, String resherchAchats) {
		List<ArticleVendu> result = new ArrayList<ArticleVendu>();
		List<ArticleVendu> allArticles = getArticles();
		boolean haveFilter = false;
		
		for (ArticleVendu articleVendu : allArticles) {	
			
			if(resherchName != null) {
				if(!resherchName.isEmpty()) {
					haveFilter = true;
					if(!articleVendu.getNomArticle().contains(resherchName)) {
						continue;
					}
				}
			}
					
			if(resherchCategory != null) {
				if(!resherchCategory.isEmpty()) {
					haveFilter = true;
					if(articleVendu.getCategorie().getNoCategorie() != Integer.parseInt(resherchCategory)) {
						continue;
					}
				}
			}
			
			if(resherchVentes != null) {	
				
				if(mesVentesEnCours != null) {	
					if(!mesVentesEnCours.isEmpty()) {
						haveFilter = true;
						if(articleVendu.getVendeur().getNoUtilisateur() != user.getNoUtilisateur() &&
								articleVendu.getEtatVente() != EtatVente.ENCOURS) {
							continue;
						}
					}
				}
				
				if(ventesNonDebute != null) {	
					if(ventesNonDebute != null) {	
						if(!ventesNonDebute.isEmpty()) {
							haveFilter = true;
							if(articleVendu.getVendeur().getNoUtilisateur() != user.getNoUtilisateur() &&
									articleVendu.getEtatVente() != EtatVente.NONDEBUTER) {
								continue;
							}
						}
					}
				}
				
				if(ventesTerminees != null) {	
					if(ventesTerminees != null) {	
						if(!ventesTerminees.isEmpty()) {
							haveFilter = true;
							if(articleVendu.getVendeur().getNoUtilisateur() != user.getNoUtilisateur() &&
									articleVendu.getEtatVente() != EtatVente.TERMINER) {
								continue;
							}
						}
					}
				}
			}
			
			
			if(resherchAchats != null) {	
				
				if(encheresOuvertes != null) {
					if(!encheresOuvertes.isEmpty()) {
						haveFilter = true;
						if(articleVendu.getEtatVente() != EtatVente.ENCOURS) {
							continue;
						}
					}
				}
						
				if(mesEncheresEnCours != null) {	
					if(!mesEncheresEnCours.isEmpty()) {
						haveFilter = true;
						boolean enchereOnArticle = false;
						for (Enchere enchere : articleVendu.getEnchere()) {
							if(enchere.getUtilisateur().getNoUtilisateur() == user.getNoUtilisateur()) {
								enchereOnArticle = true;
							}
						}
						if(enchereOnArticle == false) {
							continue;
						}
					}
				}
				
				if(mesEncheresRemportees != null) {	
					if(!mesEncheresRemportees.isEmpty()) {	
						haveFilter = true;
						if(articleVendu.getAcheteur().getNoUtilisateur() == user.getNoUtilisateur()) {
							continue;
						}
					}
				}
			}
			
			//Si on a aucun filtre, on souhaite par defaut voir tout les articles avec des enchères en cours
			if(haveFilter) {
				result.add(articleVendu);
			} else {
				if(articleVendu.getEtatVente() == EtatVente.ENCOURS) {
					result.add(articleVendu);
				}
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
