package bll;

import dal.CategoryDAO;
import dal.DALException;
import dal.DAOFactory;

import java.util.List;

import bo.Category;

public class CategoryManager 
{
	private CategoryDAO category = DAOFactory.getCategoryDAO();
	private static CategoryManager instance;
	//Singleton
	public static CategoryManager getInstance() throws BLLException {
		if(instance == null) {
			instance = new CategoryManager();
		}
		return instance;
	}
	
	public List<Category> select(Integer no_categorie,String libelle)
	{
		List<Category> categories=null;
		try {
			categories=category.select(no_categorie, libelle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}
}
