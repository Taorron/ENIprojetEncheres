package dal;

import java.util.List;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Retrait;
import bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	@Override
	public List<ArticleVendu> select(Integer noArticle, String nomArticle, String description, String dateDebutEncheres,
			String dateFinEncheres, Integer miseAPrix, Integer prixVente, String etatVente, Categorie categorie,
			Retrait retrait, Utilisateur vendeur, Utilisateur acheteur) throws DALException {
		// TODO Auto-generated method stub
		return null;
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
