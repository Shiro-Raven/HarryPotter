package harrypotter.exceptions;

@SuppressWarnings("serial")
public class NotEnoughIPException extends NotEnoughResourcesException {

	private int requiredIP;
	private int remainingIP;

	public NotEnoughIPException(int requiredIP, int remainingIP) {

		super("The required IP you need to cast this spell is " + requiredIP
				+ ".\nYou need another " + remainingIP
				+ " IP to be able to cast this spell.");

		this.requiredIP = requiredIP;
		this.remainingIP = remainingIP;

	}

	public int getRequiredIP() {
		return requiredIP;
	}

	public int getRemainingIP() {
		return remainingIP;
	}
}
