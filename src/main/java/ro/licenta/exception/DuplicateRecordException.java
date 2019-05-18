package ro.licenta.exception;

public class DuplicateRecordException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateRecordException() {
		super();
	}
	
	public DuplicateRecordException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	public DuplicateRecordException(final String message) {
		super(message);
	}
	
	public DuplicateRecordException(final Throwable cause) {
		super(cause);
	}
}
