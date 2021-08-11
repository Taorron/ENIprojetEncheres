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
	
	public Category getCategoryById(Integer no_category) {
		Category categ = null;
		try {
			List<Category> categories = category.select(no_category, null);
			if(!categories.isEmpty()) {
				categ = categories.get(0);
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categ;
	}
}
