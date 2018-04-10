package harrypotter.exceptions;

@SuppressWarnings("serial")
public abstract class NotEnoughResourcesException extends
		InvalidActionException {

	public NotEnoughResourcesException() {

	}

	public NotEnoughResourcesException(String message) {
		super(message);
	}

}
