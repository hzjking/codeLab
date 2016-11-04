package hzj.exception;

public class ErrCodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrCodeException(String resultCode){
//		super(resultCode, null, false, false);
		super(resultCode);
	}
}
