package dal;

import java.util.List;

import bo.Category;

public interface CategoryDAO {
	
	static final String SELECT_CATEGORY = "SELECT * FROM CATEGORIES WHERE no_categorie is not null ";
		
	public List<Category> select(Integer no_categorie, String libelle) throws DALException;
	
	public Category insert(Category categ) throws DALException;
	
	public void update(Category categ) throws DALException;
	
	public void delete(int no_categorie) throws DALException;
}
