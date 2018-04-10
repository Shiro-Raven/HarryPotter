package harrypotter.model.world;

public class ObstacleCell extends Cell {

	private Obstacle obstacle;

	public ObstacleCell(Obstacle obstacle) {
		this.obstacle = obstacle;

	}

	public Obstacle getObstacle() {
		return obstacle;
	}

}
