package dal;

import java.util.List;

import bo.Categorie;

public interface CategoryDAO {
	
	static final String SELECT_CATEGORY = "SELECT * FROM CATEGORIES WHERE no_categorie is not null ";
		
	public List<Categorie> select(Integer no_categorie, String libelle) throws DALException;
	
	public Categorie insert(Categorie categ) throws DALException;
	
	public void update(Categorie categ) throws DALException;
	
	public void delete(int no_categorie) throws DALException;
}
