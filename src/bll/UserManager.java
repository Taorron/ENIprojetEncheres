package bll;



import java.util.List;


import bo.Utilisateur;
import dal.DALException;
import dal.DAOFactory;
import dal.UserDAO;

public class UserManager {

	private UserDAO userDao = DAOFactory.getUserDAO();
	private static UserManager instance;
	
	//Singleton
	public static UserManager getInstance() throws BLLException {
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	public Utilisateur connect(String id, String password) 
	{
		Utilisateur utilisateur=null;
		String email=null;
		String motDePasse=null;
		String pseudo=null;
		
		if (id!=null) 
		{
			if (id.contains("@")) 
			{
				email=id;
			}
			else
			{
				pseudo=id;
			}
		}
		if (password!=null) 
		{
			motDePasse=password;
		}
		try {
			
			List<Utilisateur> selectUtilisateur =userDao.select(null, pseudo, null, null, email, null, null, null, null, motDePasse, null, null);

			if (!selectUtilisateur.isEmpty()) 
			{
				utilisateur = selectUtilisateur.get(0);
			}
			
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return	utilisateur;
		
	}
	
	
}
