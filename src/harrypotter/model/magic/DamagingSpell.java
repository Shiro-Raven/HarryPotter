package harrypotter.model.magic;

public class DamagingSpell extends Spell {

	private int damageAmount;

	public DamagingSpell(String name, int cost, int coolDown, int damageAmount) {

		super(name, cost, coolDown);
		this.damageAmount = damageAmount;

	}

	public int getDamageAmount() {
		return damageAmount;
	}

}
