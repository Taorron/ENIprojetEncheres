package dal;

import java.util.List;

import bo.Retrait;

public interface WithdrawDAO {
	
	static final String SELECT_WITHDRAW = "SELECT * FROM RETRAITS WHERE no_article is not null ";
	static final String INSERT_WITHDRAW = "INSERT INTO RETRAITS (no_article,rue,code_postal,ville) value(?,?,?,?)";
	
	
	
	public List<Retrait> select(String rue, String codePostal, String ville, Integer articleVendu) throws DALException; 
	
	public void insert(Retrait retrait) throws DALException;
	
	public void update(Retrait retrait) throws DALException;
	
	public void delete(int no_retrait) throws DALException;
}
