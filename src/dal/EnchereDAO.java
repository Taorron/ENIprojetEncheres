package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import bo.*;

public class EnchereDAO {

	private String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
	private String SELECT_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur is not null ";


	public List<Utilisateur> selectUtilisateur(
			Integer noUtil, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, 
			String MotDePasse, Integer credit, Boolean admin) throws DALException
	{
		int i = 0;
		List<Utilisateur> result = new ArrayList<>();
		
		Map<Integer, Object> mapping = new HashMap<>();
		
		//Adaptation de la requête en fonction des paramètres
		
		StringBuilder query = new StringBuilder(SELECT_UTILISATEUR);
		
		if(noUtil != null) {
			i++;
			mapping.put(i, noUtil);
			query.append("AND no_utilisateur = ? ");
		}
			
		
		if(nom != null) {
			i++;
			mapping.put(i, nom);
			query.append("AND nom = ? ");
		}
			
		
		if(prenom != null) {
			i++;
			mapping.put(i, prenom);
			query.append("AND prenom = ? ");
		}
			
		
		if(email != null) {
			i++;
			mapping.put(i, email);
			query.append("AND email = ? ");
		}
			
		
		if(telephone != null) {
			i++;
			mapping.put(i, telephone);
			query.append("AND telephone = ? ");
		}
			
		
		if(rue != null) {
			i++;
			mapping.put(i, rue);
			query.append("AND rue = ? ");
		}
			
		
		if(codePostal != null) {
			i++;
			mapping.put(i, codePostal);
			query.append("AND code_postal = ? ");
		}
			
		
		if(ville != null) {
			i++;
			mapping.put(i, ville);
			query.append("AND ville = ? ");
		}
			
		
		if(MotDePasse != null) {
			i++;
			mapping.put(i, MotDePasse);
			query.append("AND mot_de_passe = ? ");
		}
			
		
		if(credit != null) {
			i++;
			mapping.put(i, credit);
			query.append("AND credit = ? ");
		}
		
		if(admin != null) {
			i++;
			mapping.put(i, admin);
			query.append("AND administrateur = ? ");
		}
		
		System.out.println(query);
		
		try(Connection connexion = JdbcTools.getConnection(); 
				PreparedStatement rqt = connexion.prepareStatement(query.toString());) 
		{
			for(int j = 1; j <= mapping.size(); j++) 
			{
				if(mapping.get(j) instanceof Boolean) {
					rqt.setBoolean(j, (Boolean)mapping.get(j));
					
				} else if(mapping.get(j) instanceof String) {
					rqt.setString(j, (String)mapping.get(j));
					
				} else if(mapping.get(j) instanceof Integer) {
					rqt.setInt(j, (Integer)mapping.get(j));	
				}
			}
			
			ResultSet rs = rqt.executeQuery();
			while(rs.next())
			{
				result.add(new Utilisateur(
					rs.getInt("no_utilisateur"), rs.getString("pseudo"),
					rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
					rs.getString("telephone"),  rs.getString("rue"), rs.getString("code_postal"),
					rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
					rs.getBoolean("administrateur"), new ArrayList<ArticleVendu>(), 
					new ArrayList<ArticleVendu>(), new ArrayList<Enchere>()));
			}
			
			
		} catch (SQLException e) {
			throw new DALException("erreur dans selectById : " + e.toString());
		} 
		
		return result;
	}	
}


