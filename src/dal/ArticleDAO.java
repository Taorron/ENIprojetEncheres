package dal;

import java.util.Date;
import java.util.List;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Retrait;
import bo.Utilisateur;

public interface ArticleDAO {
	
	static final String SELECT_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE no_article is not null ";
	
	public List<ArticleVendu> select(Integer noArticle, String nomArticle, String description,
			Date dateDebutEncheres, Date dateFinEncheres, Integer miseAPrix, Integer prixVente,
			Integer categorie, Integer vendeur) throws DALException;

	public ArticleVendu insert(ArticleVendu article) throws DALException;

	public void update(ArticleVendu article) throws DALException;

	public void delete(int noArticle) throws DALException;
}
