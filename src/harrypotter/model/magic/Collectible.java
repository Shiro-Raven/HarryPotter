package harrypotter.model.magic;

public abstract class Collectible {

	private String name;

	public Collectible(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
