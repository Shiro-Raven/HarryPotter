package harrypotter.model.magic;

public abstract class Spell {

	private String name;
	private int cost;
	private int defaultCooldown;
	private int coolDown;

	public Spell(String name, int cost, int defaultCoolDown) {

		this.name = name;
		this.cost = cost;
		this.defaultCooldown = defaultCoolDown;

	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int cooldown) {
		this.coolDown = cooldown;
	}

	public int getDefaultCooldown() {
		return defaultCooldown;
	}

}
