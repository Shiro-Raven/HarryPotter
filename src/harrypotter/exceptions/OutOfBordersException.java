package harrypotter.exceptions;

@SuppressWarnings("serial")
public class OutOfBordersException extends InvalidActionException {

	public OutOfBordersException() {

	}

	public OutOfBordersException(String message) {
		super(message);
	}
}
