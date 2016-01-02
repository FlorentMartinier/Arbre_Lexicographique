package arbreLexicographique;

public class ModificationImpossibleException extends
		ArbreLexicographiqueException {

	public ModificationImpossibleException() {
	}

	public ModificationImpossibleException(String message) {
		super(message);
	}

	public ModificationImpossibleException(Throwable cause) {
		super(cause);
	}

	public ModificationImpossibleException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModificationImpossibleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
