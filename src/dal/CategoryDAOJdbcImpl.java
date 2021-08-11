package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bo.Categorie;

public class CategoryDAOJdbcImpl implements CategoryDAO {

	@Override
	public List<Categorie> select(Integer no_categorie, String libelle) throws DALException {
		int i = 0;
		List<Categorie> result = new ArrayList<>();
		
		Map<Integer, Object> mapping = new HashMap<>();
		
		//Adaptation de la requête en fonction des paramètres
		
		StringBuilder query = new StringBuilder(SELECT_CATEGORY);
		
		if(no_categorie != null) {
			i++;
			mapping.put(i, no_categorie);
			query.append("AND no_categorie = ? ");
		}
		
		if(libelle != null) {
			i++;
			mapping.put(i, libelle);
			query.append("AND libelle = ? ");
		}
		
		System.out.println(query);
		
		try(Connection connexion = JdbcTools.getConnection(); 
				PreparedStatement rqt = connexion.prepareStatement(query.toString());) 
		{
			for(int j = 1; j <= mapping.size(); j++) 
			{
				if(mapping.get(j) instanceof Integer) {
					rqt.setInt(j, (Integer)mapping.get(j));
					
				} else if(mapping.get(j) instanceof String) {
					rqt.setString(j, (String)mapping.get(j));
				}
			}
			
			ResultSet rs = rqt.executeQuery();
			while(rs.next())
			{
				result.add(new Categorie(
						rs.getInt("no_categorie"), rs.getString("libelle")
						));
			}
			
			System.out.println("success selectCategory");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException("erreur dans select category : " + e.toString());
		}
		
		return result;
	}

	@Override
	public Categorie insert(Categorie categ) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Categorie categ) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int no_categorie) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
