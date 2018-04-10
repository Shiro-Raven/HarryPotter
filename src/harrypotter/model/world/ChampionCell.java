package harrypotter.model.world;

import harrypotter.model.character.Champion;

public class ChampionCell extends Cell {

	private Champion champ;

	public ChampionCell(Champion champ) {
		this.champ = champ;
	}

	public Champion getChamp() {
		return champ;
	}
}
