package bo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import bll.ArticleManager;
import dal.UserDAO;
import dal.WithdrawDAO;
import dal.ArticleDAO;
import dal.BidDAO;
import dal.CategoryDAO;
import dal.DAOFactory;

public class ClassTest {

	private static UserDAO dao = DAOFactory.getUserDAO();
	private static CategoryDAO categoryDao = DAOFactory.getCategoryDAO();
	private static WithdrawDAO withdrawDao = DAOFactory.getWithdrawDAO();
	private static ArticleDAO articleDao = DAOFactory.getArticleDAO();
	private static BidDAO bidDao = DAOFactory.getBidDAO();
	
	static ArticleManager articleManager = new ArticleManager();
	
	public static void main (String[] args) throws Exception {
		
		//Test stringBuilder
//		StringBuilder testSb = new StringBuilder("bla");
//		testSb.append("b");
//		System.out.println(testSb.toString());
		
		
		//Test DAO SelectUtilisateur
//		List<Utilisateur> test1 = dao.select(1, "",null, null, null, null, null, null, null, null, 0, null);
//		for (Utilisateur utilisateur : test1) {
//			System.out.println(utilisateur.getNoUtilisateur());
//		}
		
		//Test DAO InsertUtilisateur
//		Utilisateur newUtil = new Utilisateur
//				(0, "bla", "bli", "blou", "bla@bla.fr", "0123456789", "10 rue du grand dieu", "00001",
//						"Supreme","mdptropcool", 999, false, new ArrayList<ArticleVendu>(), 
//				new ArrayList<ArticleVendu>(), new ArrayList<Enchere>());
//				
//		Utilisateur returnUtil = dao.insert(newUtil);
		
		//Test updateUtilisateur
//		Utilisateur newUtil1 = new Utilisateur
//				(3, "da", "di", "dou", "bla@bla.fr", "0123456789", "1 rue du petit dieu", "00002",
//						"PetitSupreme","mdptropcool", 9, false, new ArrayList<ArticleVendu>(), 
//				new ArrayList<ArticleVendu>(), new ArrayList<Enchere>());
//				
//		dao.update(newUtil1);
		
		//TestCategory
//		List<Categorie> listCateg = categoryDao.select(null, null);
//		
//		for(Categorie categ : listCateg) {
//			System.out.println(categ.getLibelle());
//		}
		
		//TestListRetrait
//		List<Retrait> listRetrait = withdrawDao.select(null, null, null, null);
//		
//		for(Retrait retrait : listRetrait) {
//			System.out.println(retrait.getRue() + " " + retrait.getVille());
//		}
		
		//TestListArticle
//		List<ArticleVendu> listArticle = articleDao.select(null, null, null,
//				null, null, null, null, null, null);
//		
//		for (ArticleVendu articleVendu : listArticle) {
//			System.out.println(articleVendu.getNoArticle() + " " + articleVendu.getNomArticle());
//		}
		
		//TestSetInfo ArticleManager
//		List<ArticleVendu> listArticle = articleManager.getArticles();
		
		//Test Insert Article
		Instant now = Instant.now();
		Instant yesterday = now.minus(1, ChronoUnit.DAYS);
//		
		ArticleVendu testArticle = new ArticleVendu(
				8,
				"LebelArticle",
				"Magnifique Description",
				 Date.from(yesterday),
				 Date.from(now),
				 5,
				 0,
				 new Category(1,"BelArticle"),
				 new Utilisateur(6, "",null, null, null, null, null, null, null, null, 0, false)
				);
//		
//		ArticleVendu testRetour = articleDao.insert(testArticle);
//		System.out.println(testRetour.getNoArticle() + " " + testRetour.getNomArticle());
		
		//
//		
//		bidDao.insert(new Enchere
//				(Date.from(yesterday),
//				40,
//				new Utilisateur(4, "",null, null, null, null, null, null, null, null, 0, false),
//				new ArticleVendu(
//						4,
//						"LebelArticle",
//						"Magnifique Description",
//						 Date.from(yesterday),
//						 Date.from(now),
//						 5,
//						 0,
//						 new Category(1,"BelArticle"),
//						 new Utilisateur(1, "",null, null, null, null, null, null, null, null, 0, false)
//						)
//				));
		
		articleDao.update(testArticle);
	
	}
	
	
}
