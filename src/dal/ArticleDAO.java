package dal;

import java.util.List;

import bo.ArticleVendu;
import bo.Category;
import bo.Retrait;
import bo.Utilisateur;

public interface ArticleDAO {
	
	public List<ArticleVendu> select(Integer noArticle, String nomArticle, String description,
			String dateDebutEncheres, String dateFinEncheres, Integer miseAPrix, Integer prixVente, String etatVente,
			Category categorie, Retrait retrait, Utilisateur vendeur, Utilisateur acheteur) throws DALException;

	public ArticleVendu insert(ArticleVendu article) throws DALException;

	public void update(ArticleVendu article) throws DALException;

	public void delete(int noArticle) throws DALException;
}
