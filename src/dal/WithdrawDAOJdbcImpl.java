package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bo.ArticleVendu;
import bo.Retrait;

public class WithdrawDAOJdbcImpl implements WithdrawDAO{

	@Override
	public List<Retrait> select(String rue, String codePostal, String ville, Integer articleVendu) throws DALException {
		int i = 0;
		List<Retrait> result = new ArrayList<>();
		
		Map<Integer, Object> mapping = new HashMap<>();
		
		//Adaptation de la requête en fonction des paramètres
		
		StringBuilder query = new StringBuilder(SELECT_WITHDRAW);
		
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
		
		if(articleVendu != null) {
			i++;
			mapping.put(i, articleVendu);
			query.append("AND no_article = ? ");
		}
		
		System.out.println(query);
		
		try(Connection connexion = JdbcTools.getConnection(); 
				PreparedStatement rqt = connexion.prepareStatement(query.toString());) 
		{
			for(int j = 1; j <= mapping.size(); j++) 
			{
				if(mapping.get(j) instanceof String) {
					rqt.setString(j, (String)mapping.get(j));
					
				} else if(mapping.get(j) instanceof Integer) {
					rqt.setInt(j, (Integer)mapping.get(j));	
				}
			}
			
			ResultSet rs = rqt.executeQuery();
			
			while(rs.next())
			{
				result.add(new Retrait(
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville")
						));
			}
						
			System.out.println("success select withdraw");
		} catch (SQLException e) {
			throw new DALException("erreur dans select withdraw : " + e.toString());
		}
		
		return result;
	}

	@Override
	public void insert(Retrait retrait) throws DALException {

		Connection cnx = null;
		
		try {
			cnx = JdbcTools.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(INSERT_WITHDRAW);
			rqt.setInt(1, retrait.getArticleVendu().getNoArticle());
			rqt.setString(2, retrait.getRue());
			rqt.setString(3, retrait.getCodePostal());
			rqt.setString(4, retrait.getVille());
			
			
			rqt.executeUpdate();
			
			
//			System.out.println("success insert Article new id : "+result.getNoArticle() );			
			
		}catch (SQLException e) {
			throw new DALException("erreur dans select withdraw : " + e.toString());
			//propager une exception personnalisée
			
		}
//		return result;
	
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Retrait retrait) throws DALException {
		// TODO Auto-generated method stub
		Connection cnx = null;
		
		try {
			cnx = JdbcTools.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(UPDATE_WITHDRAW);
			
			rqt.setString(1, retrait.getRue());
			rqt.setString(2, retrait.getCodePostal());
			rqt.setString(3, retrait.getVille());
			rqt.setInt(4, retrait.getArticleVendu().getNoArticle());
			
			
			rqt.executeUpdate();
			
			
//			System.out.println("success insert Article new id : "+result.getNoArticle() );			
			
		}catch (SQLException e) {
			throw new DALException("erreur dans select withdraw : " + e.toString());
			//propager une exception personnalisée
			
		}
		
	}

	@Override
	public void delete(int no_retrait) throws DALException {
		try(Connection connexion = JdbcTools.getConnection();
				PreparedStatement prpStmt = connexion.prepareStatement(DELETE_WITHDRAW);){			
			prpStmt.setInt(1, no_retrait);
			prpStmt.executeUpdate();
			System.out.println("Success delete retrait id : " + no_retrait);
		} catch (SQLException e) {
			throw new DALException("erreur delete, retrait id : " + no_retrait + e.getMessage());
			
		}
		
	}



}
