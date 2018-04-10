package harrypotter.model.world;

public class Merperson extends Obstacle {

	private int damage;

	public Merperson(int hp, int damage) {

		super(hp);
		this.damage = damage;

	}

	public int getDamage() {
		return damage;
	}

}
