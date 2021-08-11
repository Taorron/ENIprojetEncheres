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
import bo.Enchere;
import bo.Utilisateur;

public class BidDAOJdbcImpl implements BidDAO {
	
	@Override
	public List<Enchere> select(Integer noUtil, Integer noArticle, Date dateEnchere, Integer montant)
			throws DALException {
		int i = 0;
		List<Enchere> result = new ArrayList<>();
		
		Map<Integer, Object> mapping = new HashMap<>();
		
		//Adaptation de la requête en fonction des paramètres
		
		StringBuilder query = new StringBuilder(SELECT_ENCHERE);
		
		if(noUtil != null) {
			i++;
			mapping.put(i, noUtil);
			query.append("AND no_utilisateur = ? ");
		}
		
		if(noArticle != null) {
			i++;
			mapping.put(i, noArticle);
			query.append("AND no_article = ? ");
		}
		
		if(dateEnchere != null) {
			i++;
			mapping.put(i, dateEnchere);
			query.append("AND date_enchere = ? ");
		}
		
		if(montant != null) {
			i++;
			mapping.put(i, montant);
			query.append("AND montant_enchere = ? ");
		}			
				
		System.out.println(query);
		
		try(Connection connexion = JdbcTools.getConnection(); 
				PreparedStatement rqt = connexion.prepareStatement(query.toString());) 
		{
			for(int j = 1; j <= mapping.size(); j++) 
			{
				if(mapping.get(j) instanceof Integer) {
					rqt.setInt(j, (Integer)mapping.get(j));
					
				} else if(mapping.get(j) instanceof Date) {
					rqt.setDate(j, (java.sql.Date)mapping.get(j));
				}
			}
			
			ResultSet rs = rqt.executeQuery();
			while(rs.next())
			{
				result.add(new Enchere(
						rs.getDate("date_enchere").toString(),
						rs.getInt("montant_enchere"), 
						new Utilisateur(rs.getInt("no_utilisateur"),
								null,null,null,null,null,null,null,null,null,0,false, 
								new ArrayList<ArticleVendu>(), new ArrayList<ArticleVendu>(),
								new ArrayList<Enchere>()
								), 
						new ArticleVendu(rs.getInt("no_article"), 
								null, null, null, null, 0, 0, null, null, null, null, null, 
								new ArrayList<Enchere>())
						));
			}
			
			System.out.println("success selectUtilisateur");
		} catch (SQLException e) {
			throw new DALException("erreur dans selectById : " + e.toString());
		} 
		
		return result;
	}

	@Override
	public Enchere insert(Enchere enchere) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Enchere enchere) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int noUtil, int noArticle) throws DALException {
		// TODO Auto-generated method stub
		
	}
}
