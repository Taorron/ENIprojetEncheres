package bo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dal.JdbcTools;
import dal.DALException;
import dal.EnchereDAO;

public class ClassTest {

	public static EnchereDAO dao = new EnchereDAO();

	public static void main (String[] args) throws Exception {
		
		//Test stringBuilder
		StringBuilder testSb = new StringBuilder("bla");
		testSb.append("b");
		System.out.println(testSb.toString());
		
		
		//Test DAO SelectUtilisateur
		List<Utilisateur> test1 = dao.selectUtilisateur(null, null, null, null, "Taorron@gmail.com", null, null, null, null, "123456", null, null);
		for (Utilisateur utilisateur : test1) {
			System.out.println(utilisateur.getNom());
		}
		
		//Test DAO InsertUtilisateur
//		Utilisateur newUtil = new Utilisateur
//				(0, "bla", "bli", "blou", "bla@bla.fr", "0123456789", "10 rue du grand dieu", "00001",
//						"Supreme","mdptropcool", 999, false, new ArrayList<ArticleVendu>(), 
//				new ArrayList<ArticleVendu>(), new ArrayList<Enchere>());
//				
//		Utilisateur returnUtil = dao.insertUtilisateur(newUtil);
		
		//Test updateUtilisateur
//		Utilisateur newUtil = new Utilisateur
//				(3, "da", "di", "dou", "bla@bla.fr", "0123456789", "1 rue du petit dieu", "00002",
//						"PetitSupreme","mdptropcool", 9, false, new ArrayList<ArticleVendu>(), 
//				new ArrayList<ArticleVendu>(), new ArrayList<Enchere>());
//				
//		dao.updateUtilisateur(newUtil);;
//		
	}
	
	
}
