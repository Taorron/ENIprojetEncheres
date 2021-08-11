package dal;

import java.util.Date;
import java.util.List;

import bo.Enchere;

public interface BidDAO {
	
	static final String SELECT_ENCHERE = "SELECT * FROM ENCHERES WHERE no_utilisateur AND no_article is not null ";
	
	public List<Enchere> select(Integer noUtil, Integer noArticle, Date dateEnchere, Integer montant)
			throws DALException;
	
	public Enchere insert(Enchere enchere) throws DALException;
	
	public void update(Enchere enchere) throws DALException;
	
	public void delete(int noUtil, int noArticle) throws DALException;
}
