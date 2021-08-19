package dal;

import java.util.Date;
import java.util.List;

import bo.Enchere;

public interface BidDAO {
	
	static final String SELECT_ENCHERE = "SELECT * FROM ENCHERES WHERE no_utilisateur is not null AND no_article is not null ";
	
	static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur,no_article,date_enchere,montant_enchere) VALUES (?,?,?,?)";
	
	static final String DELETE_ENCHERE = "DELETE FROM ENCHERES "
			+ "WHERE no_article = ?";
	
	public List<Enchere> select(Integer noUtil, Integer noArticle, Date dateEnchere, Integer montant)
			throws DALException;
	
	public void insert(Enchere enchere) throws Exception;
	
	public void update(Enchere enchere) throws DALException;
	
	public void delete(int noArticle) throws DALException;
}
