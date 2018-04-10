package harrypotter.model.tournament;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.WallCell;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ThirdTask extends Task {

	public ThirdTask(ArrayList<Champion> champions) throws IOException {

		super(champions);
		generateMap();

		setCurrentChamp(getChampions().get(0));

	}

	public void generateMap() throws IOException {

		initializeAllEmpty();
		readMap("task3map.csv");
		allocatePotions();

	}

	private void readMap(String filePath) throws IOException {

		Cell[][] map = getMap();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();

		int i = 0;
		while (line != null) {

			String[] content = line.split(",");

			for (int j = 0; j < content.length; j++) {

				char cellType = content[j].charAt(0);

				switch (cellType) {
				case '0':

					map[i][j] = new EmptyCell();
					break;

				case '5':

					map[i][j] = new WallCell();
					break;

				case '6':

					int hp = (int) ((Math.random() * 101) + 200);
					map[i][j] = new ObstacleCell(new PhysicalObstacle(hp));
					break;

				case '7':

					map[i][j] = new CupCell();
					break;

				case '1':
				case '2':
				case '3':
				case '4':

					int c = Character.getNumericValue(cellType);
					if (c <= getChampions().size()) {

						map[i][j] = new ChampionCell(getChampions().get(c - 1));
						((Wizard) getChampions().get(c - 1))
								.setLocation(new Point(i, j));

					}

					break;

				default:

					break;

				}

			}

			i++;
			line = br.readLine();

		}

		br.close();

	}

	public void moveForward() throws IOException, OutOfBordersException,
			InvalidTargetCellException {

		Wizard current = (Wizard) getCurrentChamp();

		Point location = current.getLocation();

		Point newLocation = new Point(location.x - 1, location.y);
		if (newLocation.x < 0) {
			throw new OutOfBordersException(
					"Cannot move beyond the front border.");
		}

		Cell next = getMap()[newLocation.x][newLocation.y];

		if (next instanceof CupCell) {

			getMap()[location.x][location.y] = new EmptyCell();
			getMap()[newLocation.x][newLocation.y] = new EmptyCell();
			current.setLocation(new Point(newLocation.x, newLocation.y));

			getChampions().remove(current);

			if (getListener() != null)
				getListener().onFinishingThirdTask((Champion) current);

		} else {
			super.moveForward();
		}
	}

	public void moveBackward() throws IOException, OutOfBordersException,
			InvalidTargetCellException {

		Wizard current = (Wizard) getCurrentChamp();

		Point location = current.getLocation();

		Point newLocation = new Point(location.x + 1, location.y);
		if (newLocation.x > 9) {
			throw new OutOfBordersException(
					"Cannot move beyond the back border.");
		}

		Cell next = getMap()[newLocation.x][newLocation.y];

		if (next instanceof CupCell) {

			getMap()[location.x][location.y] = new EmptyCell();
			getMap()[newLocation.x][newLocation.y] = new EmptyCell();
			current.setLocation(new Point(newLocation.x, newLocation.y));

			getChampions().remove(current);

			if (getListener() != null)
				getListener().onFinishingThirdTask((Champion) current);

		} else {
			super.moveBackward();
		}
	}

	public void moveLeft() throws IOException, OutOfBordersException,
			InvalidTargetCellException {

		Wizard current = (Wizard) getCurrentChamp();

		Point location = current.getLocation();

		Point newLocation = new Point(location.x, location.y - 1);
		if (newLocation.y < 0) {
			throw new OutOfBordersException(
					"Cannot move beyond the left border.");
		}

		Cell next = getMap()[newLocation.x][newLocation.y];

		if (next instanceof CupCell) {

			getMap()[location.x][location.y] = new EmptyCell();
			getMap()[newLocation.x][newLocation.y] = new EmptyCell();
			current.setLocation(new Point(newLocation.x, newLocation.y));

			getChampions().remove(current);

			if (getListener() != null)
				getListener().onFinishingThirdTask((Champion) current);

		} else {
			super.moveLeft();
		}
	}

	public void moveRight() throws IOException, OutOfBordersException,
			InvalidTargetCellException {

		Wizard current = (Wizard) getCurrentChamp();

		Point location = current.getLocation();

		Point newLocation = new Point(location.x, location.y + 1);
		if (newLocation.y > 9) {
			throw new OutOfBordersException(
					"Cannot move beyond the right border.");
		}

		Cell next = getMap()[newLocation.x][newLocation.y];

		if (next instanceof CupCell) {

			getMap()[location.x][location.y] = new EmptyCell();
			getMap()[newLocation.x][newLocation.y] = new EmptyCell();
			current.setLocation(new Point(newLocation.x, newLocation.y));

			getChampions().remove(current);

			if (getListener() != null)
				getListener().onFinishingThirdTask((Champion) current);

		} else {
			super.moveRight();
		}
	}

	public void onSlytherinTrait(Direction d) throws IOException,
			InCooldownException, OutOfBordersException,
			InvalidTargetCellException {

		Wizard current = (Wizard) getCurrentChamp();
		super.onSlytherinTrait(d);
		current.setTraitCooldown(10);

	}

	public void onHufflepuffTrait() throws InCooldownException {

		Wizard current = (Wizard) getCurrentChamp();
		super.onHufflepuffTrait();
		current.setTraitCooldown(0);
		cont.loadCurrent();

	}

	public Object onRavenclawTrait() throws InCooldownException {

		Wizard current = (Wizard) getCurrentChamp();

		if (current.getTraitCooldown() > 0) {
			throw new InCooldownException(current.getTraitCooldown());
		}

		ArrayList<Direction> result = new ArrayList<Direction>();

		int x = 0;
		int y = 0;
		for (int i = 0; i < getMap().length; i++) {
			for (int j = 0; j < getMap()[i].length; j++) {

				Cell c = getMap()[i][j];

				if (c instanceof CupCell) {

					x = i;
					y = j;
					break;

				}
			}
		}

		int currentX = current.getLocation().x;
		int currentY = current.getLocation().y;

		if (y > currentY)
			result.add(Direction.RIGHT);
		else if (y < currentY)
			result.add(Direction.LEFT);

		if (x > currentX)
			result.add(Direction.BACKWARD);
		else if (x < currentX)
			result.add(Direction.FORWARD);

		setTraitActivated(true);

		current.setTraitCooldown(7);
		cont.loadCurrent();
		String out = "Go ";
		for(Direction d : result)
		{
			out += d.toString() + " ";
		}
		
		JOptionPane.showMessageDialog(null, out);
		return result;

	}
}
