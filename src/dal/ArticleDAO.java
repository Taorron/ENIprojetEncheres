package dal;

import java.util.Date;
import java.util.List;

import bo.ArticleVendu;


public interface ArticleDAO {
	
	static final String SELECT_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article is not null ";
	static final String INSERT_ARTICLE ="INSERT INTO ARTICLES_VENDUS "
			+ "(nom_article, [description], date_debut_encheres, date_fin_encheres, prix_initial, "
			+ "prix_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?)";
	
	static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS "
			+ "SET nom_article = ?, [description] = ?, date_debut_encheres = ?,"
			+ " date_fin_encheres = ?, prix_initial = ?, prix_vente = ?,"
			+ " no_utilisateur = ?, no_categorie = ?"
			+ " WHERE no_article = ?";
	
	static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS "
			+ "WHERE no_article = ?";
	
	public List<ArticleVendu> select(Integer noArticle, String nomArticle, String description,
			Date dateDebutEncheres, Date dateFinEncheres, Integer miseAPrix, Integer prixVente,
			Integer categorie, Integer vendeur) throws DALException;

	public ArticleVendu insert(ArticleVendu article) throws Exception;

	public void update(ArticleVendu article) throws DALException;

	public void delete(int noArticle) throws DALException;
}
