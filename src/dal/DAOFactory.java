package dal;

public class DAOFactory {
	
	public static UserDAO getUserDAO() {
		return new UserDAOJdbcImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
	
	public static BidDAO getBidDAO() {
		return new BidDAOJdbcImpl();
	}
	
	public static CategoryDAO getCategoryDAO() {
		return new CategoryDAOJdbcImpl();
	}
	
	public static WithdrawDAO getWithdrawDAO() {
		return new WithdrawDAOJdbcImpl();
	}
}
