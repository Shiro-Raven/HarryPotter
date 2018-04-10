package harrypotter.model.magic;

public class Potion extends Collectible {

	private int amount;

	public Potion(String name, int amount) {

		super(name);
		this.amount = amount;

	}

	public int getAmount() {
		return amount;
	}

}
