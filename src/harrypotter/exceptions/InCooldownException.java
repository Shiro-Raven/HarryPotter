package harrypotter.exceptions;

@SuppressWarnings("serial")
public class InCooldownException extends NotEnoughResourcesException {

	private int remainingTurns;

	public InCooldownException(int remainingTurns) {

		super("You must wait " + remainingTurns
				+ " until you can use this spell/trait again.");
		this.remainingTurns = remainingTurns;

	}

	public int getRemainingTurns() {
		return remainingTurns;
	}
}
