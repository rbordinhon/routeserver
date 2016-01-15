package routeservice.exception;

public class BussinessException extends Exception{

	/**
	 * @author Rodrigo Bordinhon
	 */
	private static final long serialVersionUID = 5681947514561852813L;

	public BussinessException() {
		super();
		
	}

	public BussinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public BussinessException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BussinessException(String message) {
		super(message);
		
	}

	public BussinessException(Throwable cause) {
		super(cause);
		
	}

}
