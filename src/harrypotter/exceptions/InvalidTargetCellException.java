package harrypotter.exceptions;

@SuppressWarnings("serial")
public class InvalidTargetCellException extends InvalidActionException {

	public InvalidTargetCellException() {

	}

	public InvalidTargetCellException(String message) {
		super(message);
	}
}
