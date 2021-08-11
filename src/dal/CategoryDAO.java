package dal;

import java.util.List;

import bo.Categorie;

public interface CategoryDAO {
	
	public List<Categorie> select(Integer no_categorie, String libelle) throws DALException;
	
	public Categorie insert(Categorie categ) throws DALException;
	
	public void update(Categorie categ) throws DALException;
	
	public void delete(int no_categorie) throws DALException;
}
