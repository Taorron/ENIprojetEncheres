package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
				Timestamp ts = rs.getTimestamp("date_enchere");
				Date date=new Date(ts.getTime());  
				result.add(new Enchere(
						date,
						rs.getInt("montant_enchere"), 
						new Utilisateur(rs.getInt("no_utilisateur"),
								null,null,null,null,null,null,null,null,null,0,false, 
								new ArrayList<ArticleVendu>(), new ArrayList<ArticleVendu>(),
								new ArrayList<Enchere>()
								), 
						new ArticleVendu(rs.getInt("no_article"), 
								null, null, null, null, 0, 0, null, null, null, null, null)
						));
			}
			
			System.out.println("success selectUtilisateur");
		} catch (SQLException e) {
			throw new DALException("erreur dans selectById : " + e.toString());
		} 
		
		return result;
	}

	@Override
	public void insert(Enchere enchere) throws Exception {
		
		Connection cnx = null;
		
		Date dateEnchere = enchere.getDateEnchère();
		Timestamp ts=new Timestamp(dateEnchere.getTime());  
		
		try {
			cnx = JdbcTools.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(INSERT_ENCHERE);
			
			rqt.setInt(1,enchere.getUtilisateur().getNoUtilisateur());
			rqt.setInt(2, enchere.getArticleVendu().getNoArticle());
			rqt.setTimestamp(3, ts);
			rqt.setInt(4, enchere.getMontantEnchere());
			
			rqt.executeUpdate();
			System.out.println("insert Bid");
		} catch (SQLException e) {
			//propager une exception personnalisée
			throw new Exception("Problème d'ajout d'un enchere en base. Cause : " + e.getMessage());
		}
	}

	@Override
	public void update(Enchere enchere) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int noArticle) throws DALException {
		try(Connection connexion = JdbcTools.getConnection();
				PreparedStatement rqt = connexion.prepareStatement(DELETE_ENCHERE);){

			rqt.setInt(1,noArticle);
			
			rqt.executeUpdate();
			System.out.println("Success delete article id : " + noArticle);
		} catch (SQLException e) {
			//propager une exception personnalisée
			throw new DALException("erreur delete, enchere id : " + noArticle + e.getMessage());
		}
		
	}
}
