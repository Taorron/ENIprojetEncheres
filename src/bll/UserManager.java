package bll;

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
	
	
}
