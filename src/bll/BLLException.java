package bll;

public class BLLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BLLException() {
		super();
	}
	
	public BLLException(String msg) {
			super(msg);
		}
		
	public BLLException(String msg, Throwable exc) {
		super(msg, exc);
	}
	
	//Méthodes
		@Override
		public String getMessage() {
			StringBuffer sb = new StringBuffer("Couche BLL - ");
			sb.append(super.getMessage());
			
			return sb.toString() ;
		}
}
