package bo;

import java.util.List;

import bll.ArticleManager;
import dal.UserDAO;
import dal.WithdrawDAO;
import dal.ArticleDAO;
import dal.CategoryDAO;
import dal.DAOFactory;

public class ClassTest {

	private static UserDAO dao = DAOFactory.getUserDAO();
	private static CategoryDAO categoryDao = DAOFactory.getCategoryDAO();
	private static WithdrawDAO withdrawDao = DAOFactory.getWithdrawDAO();
	private static ArticleDAO articleDao = DAOFactory.getArticleDAO();
	
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
		ArticleManager articleManager = new ArticleManager();
		List<ArticleVendu> listArticle = articleManager.getArticles();
		
	}
	
	
}
