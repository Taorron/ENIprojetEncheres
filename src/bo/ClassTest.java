package bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dal.JdbcTools;
import dal.DALException;

public class ClassTest {

	private static String SQL_SELECT_ALL = "SELECT * FROM UTILISATEURS";
	
	private static String SQL_SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	

	public static void main (String[] args) throws DALException {
		try {
			test();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
	public static void test() throws DALException{
		
		try(Connection connexion = JdbcTools.getConnection(); 
				PreparedStatement rqt = connexion.prepareStatement(SQL_SELECT_BY_ID);) 
		{

			rqt.setInt(1, 1);
			ResultSet rs = rqt.executeQuery();
			Utilisateur util = null;
			if (rs.next()) {
				util = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
						rs.getString("telephone"),  rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
						rs.getBoolean("administrateur"), new ArrayList<ArticleVendu>(), new ArrayList<ArticleVendu>(), new ArrayList<Enchere>());
			}
			
			System.out.println(util.getNoUtilisateur());
			
		} catch (SQLException e) {
			throw new DALException("erreur dans selectById : " + e.toString());
		} 
	}
	
	
	
}
