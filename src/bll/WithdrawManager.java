package bll;

import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Retrait;
import dal.DALException;
import dal.DAOFactory;
import dal.WithdrawDAO;
import dal.WithdrawDAOJdbcImpl;

public class WithdrawManager {
	private WithdrawDAO withdrawDao = DAOFactory.getWithdrawDAO();
	private static WithdrawManager instance;
	
	public static WithdrawManager getInstance() throws BLLException {
		
		if(instance == null) {
			instance = new WithdrawManager();
		}
		return instance;
	}
	
	
	public Retrait getRetraitByArticleId(int id) {
		Retrait retrait = null;
		try {
			List<Retrait> result = withdrawDao.select(null, null, null, id);
			if(!result.isEmpty()) {
				retrait = result.get(0);
			}
		} catch(DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retrait;
	}
	public void insert(Retrait retrait) throws DALException
	{
		withdrawDao.insert(retrait);
	}


	public void update(Retrait retrait) throws DALException {
		withdrawDao.update(retrait);
		// TODO Auto-generated method stub
		
	}
	
}
