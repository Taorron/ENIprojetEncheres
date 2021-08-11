package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Enchere;
import bo.Retrait;
import bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	@Override
	public List<ArticleVendu> select(Integer noArticle, String nomArticle, String description,
			Date dateDebutEncheres, Date dateFinEncheres, Integer miseAPrix, Integer prixVente,
			Integer categorie, Integer vendeur) throws DALException 
	{
		int i = 0;
		List<ArticleVendu> result = new ArrayList<>();
		
		Map<Integer, Object> mapping = new HashMap<>();
		
		//Adaptation de la requête en fonction des paramètres
		
		StringBuilder query = new StringBuilder(SELECT_ARTICLE);
		
		if(noArticle != null) {
			i++;
			mapping.put(i, noArticle);
			query.append("AND no_article = ? ");
		}
		
		if(nomArticle != null) {
			i++;
			mapping.put(i, nomArticle);
			query.append("AND nom_article = ? ");
		}
		
		if(description != null) {
			i++;
			mapping.put(i, description);
			query.append("AND description = ? ");
		}
		
		if(dateDebutEncheres != null) {
			i++;
			mapping.put(i, dateDebutEncheres);
			query.append("AND date_debut_encheres = ? ");
		}			
		
		if(dateFinEncheres != null) {
			i++;
			mapping.put(i, dateFinEncheres);
			query.append("AND date_fin_encheres = ? ");
		}
					
		if(miseAPrix != null) {
			i++;
			mapping.put(i, miseAPrix);
			query.append("AND prix_initial = ? ");
		}			
		
		if(prixVente != null) {
			i++;
			mapping.put(i, prixVente);
			query.append("AND prix_vente = ? ");
		}
						
		
		if(categorie != null) {
			i++;
			mapping.put(i, categorie);
			query.append("AND no_categorie = ? ");
		}
			
		
		if(vendeur != null) {
			i++;
			mapping.put(i, vendeur);
			query.append("AND no_utilisateur = ? ");
		}
		
		System.out.println(query);
		
		try(Connection connexion = JdbcTools.getConnection(); 
				PreparedStatement rqt = connexion.prepareStatement(query.toString());) 
		{
			for(int j = 1; j <= mapping.size(); j++) 
			{
				if(mapping.get(j) instanceof Date) {
					rqt.setDate(j, (java.sql.Date) mapping.get(j));
					
				} else if(mapping.get(j) instanceof String) {
					rqt.setString(j, (String)mapping.get(j));
					
				} else if(mapping.get(j) instanceof Integer) {
					rqt.setInt(j, (Integer)mapping.get(j));	
				}
			}
			
			ResultSet rs = rqt.executeQuery();
			while(rs.next())
			{
				result.add(new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						new Categorie(rs.getInt("no_categorie"),""),
						new Utilisateur(rs.getInt("no_utilisateur"),"","","","","","","","","",0,false)
						));
			}
			
			System.out.println("success select Article");
		} catch (SQLException e) {
			throw new DALException("erreur dans select utilisateur : " + e.toString());
		} 
		
		return result;
	}

	@Override
	public ArticleVendu insert(ArticleVendu article) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ArticleVendu article) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int noArticle) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
