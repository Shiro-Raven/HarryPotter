package harrypotter.exceptions;

@SuppressWarnings("serial")
public abstract class InvalidActionException extends Exception {

	public InvalidActionException() {

	}

	public InvalidActionException(String message) {
		super(message);
	}
}
