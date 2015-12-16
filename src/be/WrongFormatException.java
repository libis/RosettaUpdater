package be;
public class WrongFormatException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongFormatException() {
		super();
	}

	public WrongFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public WrongFormatException(String s) {
		super(s);
	}

	public WrongFormatException(Throwable cause) {
		super(cause);
	}

}
