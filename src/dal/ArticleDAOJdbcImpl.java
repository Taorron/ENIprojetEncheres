package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bo.ArticleVendu;
import bo.Category;
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
						new Category(rs.getInt("no_categorie"),""),
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
	public ArticleVendu insert(ArticleVendu article) throws Exception {
		ArticleVendu result = article;
		Connection cnx = null;
		
		LocalDate dateDebutEnchere = article.getDateDebutEncheres().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		LocalDate dateFinEnchere = article.getDateFinEncheres().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		try {
			cnx = JdbcTools.getConnection();
			PreparedStatement rqt = cnx.prepareStatement(INSERT_ARTICLE,PreparedStatement.RETURN_GENERATED_KEYS);

			rqt.setString(1, article.getNomArticle());
			rqt.setString(2, article.getDescription());
			rqt.setDate(3, java.sql.Date.valueOf(dateDebutEnchere));
			rqt.setDate(4, java.sql.Date.valueOf(dateFinEnchere));
			rqt.setInt(5, article.getMiseAPrix());
			rqt.setInt(6, article.getPrixVente());
			rqt.setInt(7, article.getVendeur().getNoUtilisateur());
			rqt.setInt(8, article.getCategorie().getNoCategorie());
			
			rqt.executeUpdate();
			ResultSet rs = rqt.getGeneratedKeys();
			if (rs.next()) {
				result.setNoArticle((rs.getInt(1)));
			}
			
			System.out.println("success insert Article new id : "+result.getNoArticle() );			
			
		}catch (SQLException e) {
			cnx.rollback();
			//propager une exception personnalisée
			throw new Exception("Problème d'ajout d'un Article en base. Cause : " + e.getMessage());
		}
		return result;
	}

	@Override
	public void update(ArticleVendu article) throws DALException {
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		
		//Gestion typage date debut
		String dateDebutformat = formater.format(article.getDateDebutEncheres());
		Date DateDebut = null;
		try {
			DateDebut = new SimpleDateFormat("yyyy-MM-dd").parse(dateDebutformat);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Instant instantDateDebut = DateDebut.toInstant();
		ZonedDateTime atZoneDateDebut = instantDateDebut.atZone(ZoneId.systemDefault());
		LocalDate localDateDebut = atZoneDateDebut.toLocalDate();
		LocalDate dateDebutEnchere = localDateDebut;
		
		//Gestion typage date fin
		String dateFinformat = formater.format(article.getDateFinEncheres());
		Date DateFin = null;
		try {
			DateFin = new SimpleDateFormat("yyyy-MM-dd").parse(dateFinformat);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Instant instantDateFin = DateFin.toInstant();
		ZonedDateTime atZoneDateFin = instantDateFin.atZone(ZoneId.systemDefault());
		LocalDate localDateFin = atZoneDateFin.toLocalDate();
		LocalDate dateFinEnchere = localDateFin;
		
		
		try(Connection connexion = JdbcTools.getConnection();
				PreparedStatement prpStmt = connexion.prepareStatement(UPDATE_ARTICLE);){
			
//			System.out.println( article.getNomArticle() );
//			System.out.println(article.getDescription());
//			System.out.println(dateDebutEnchere);
//			System.out.println(dateFinEnchere);
//			System.out.println(article.getMiseAPrix());
//			System.out.println(article.getPrixVente());
//			System.out.println(article.getVendeur().getNoUtilisateur());
//			System.out.println(article.getCategorie().getNoCategorie());
//			System.out.println(article.getNoArticle());
			
			prpStmt.setString(1,article.getNomArticle());
			prpStmt.setString(2,article.getDescription());
			prpStmt.setDate(3,java.sql.Date.valueOf(dateDebutEnchere));
			prpStmt.setDate(4,java.sql.Date.valueOf(dateFinEnchere));
			prpStmt.setInt(5, article.getMiseAPrix());
			prpStmt.setInt(6, article.getPrixVente());
			prpStmt.setInt(7, article.getVendeur().getNoUtilisateur());
			prpStmt.setInt(8, article.getCategorie().getNoCategorie());
			prpStmt.setInt(9, article.getNoArticle());
			
			prpStmt.executeUpdate();
			
			System.out.println("Success update article id : " + article.getNoArticle());
			
		}catch (SQLException e) {
			throw new DALException("erreur update, article id : " + article.getNoArticle() + e.getMessage());
		}	
	}

	@Override
	public void delete(int noArticle) throws DALException {
		
		try(Connection connexion = JdbcTools.getConnection();
				PreparedStatement prpStmt = connexion.prepareStatement(DELETE_ARTICLE);){
			
			prpStmt.setInt(1, noArticle);
			
			prpStmt.executeUpdate();
			System.out.println("Success delete article id : " + noArticle);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("erreur delete, article id : " + noArticle + e.getMessage());
		}
	}
}
