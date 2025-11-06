package utilities;

public class WrongCommandException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public WrongCommandException(String mess) {
		super(mess);
	}
}
