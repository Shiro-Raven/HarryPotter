package harrypotter.model.tournament;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Potion;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.ObstacleCell;

import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class FirstTask extends Task {

	private ArrayList<Point> markedCells;
	private ArrayList<Champion> winners;

	public FirstTask(ArrayList<Champion> champions) throws IOException {

		super(champions);
		Collections.shuffle(champions);
		generateMap();

		setCurrentChamp(getChampions().get(0));
		markedCells = new ArrayList<Point>();
		winners = new ArrayList<Champion>();
		markCells();

	}

	public void generateMap() {

		Cell[][] map = getMap();

		initializeAllEmpty();

		allocateChampions();

		int count = 0;

		while (count < 10) {

			int randomX = (int) (Math.random() * 10);

			int randomY = (int) (Math.random() * 10);

			if (map[randomX][randomY] instanceof EmptyCell
					&& !(randomX == 4 && randomY == 4)) {

				int hp = (int) ((Math.random() * 101) + 200);
				map[randomX][randomY] = new ObstacleCell(new PhysicalObstacle(
						hp));
				count++;

			}
		}

		allocatePotions();

	}

	public void allocatePotions() {

		Cell[][] map = getMap();

		ArrayList<Potion> potions = getPotions();

		int i = 0;
		while (i < 10) {

			int randomX = (int) (Math.random() * 10);
			int randomY = (int) (Math.random() * 10);

			if (map[randomX][randomY] instanceof EmptyCell
					&& !(randomX == 4 && randomY == 4)) {

				int r = (int) (Math.random() * potions.size());
				map[randomX][randomY] = new CollectibleCell(potions.get(r));
				i++;

			}

		}
	}
	
	public void markCells() {

		markedCells = new ArrayList<Point>();

		ArrayList<Point> cells = new ArrayList<Point>();

		Wizard current = (Wizard) getCurrentChamp();
		int currentX = current.getLocation().x;
		int currentY = current.getLocation().y;

		cells.add(current.getLocation());

		if (currentX + 1 <= 9)
			cells.add(new Point(currentX + 1, currentY));
		if (currentX - 1 >= 0)
			cells.add(new Point(currentX - 1, currentY));
		if (currentY - 1 >= 0)
			cells.add(new Point(currentX, currentY - 1));
		if (currentY + 1 <= 9)
			cells.add(new Point(currentX, currentY + 1));

		int i = 0;
		while (i < 2) {
			int r = (int) (Math.random() * cells.size());
			if (!markedCells.contains(cells.get(r))) {
				markedCells.add(cells.get(r));
				i++;
			}
		}
	}

	public void fire() throws IOException {
		//super.cont.fi(markedCells);
		Cell[][] map = getMap();

		for (int i = 0; i < markedCells.size(); i++) {

			Cell cell = map[markedCells.get(i).x][markedCells.get(i).y];

			if (cell instanceof ChampionCell) {

				Wizard current = (Wizard) (((ChampionCell) cell).getChamp());
				int newHp = current.getHp() - 150;
				if (newHp <= 0) {

					current.setHp(0);
					map[markedCells.get(i).x][markedCells.get(i).y] = new EmptyCell();
					cont.loadCurrent();
					getChampions().remove(((ChampionCell) cell).getChamp());
					
					JOptionPane.showMessageDialog(null, "OOPS! You are dead!");
					cont.rempoint(current.getLocation());
//					endTurn();
					
				} else {
					//int old = current.getHp();
					current.setHp(newHp);
					//JOptionPane.showMessageDialog(null, "Dragon damaged your HP by "+ (old-newHp));
					cont.loadCurrent();
					
				}
			}
		}
		
		super.cont.fi(markedCells);

	}

	public void finalizeAction() throws IOException {

		Wizard current = (Wizard) getCurrentChamp();

		if (current.getLocation().x == 4 && current.getLocation().y == 4) {

			winners.add(getCurrentChamp());
			cont.loadCurrent();
			getChampions().remove(getCurrentChamp());
			
			getMap()[4][4] = new EmptyCell();
			JOptionPane.showMessageDialog(null, "You Won!");
			ImageIcon bb = new ImageIcon("egg.png");
			bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(cont.view.mapcells.get(44).getWidth(),cont.view.mapcells.get(44).getHeight(), java.awt.Image.SCALE_SMOOTH));
			cont.view.mapcells.get(44).setIcon(bb);
			
			if (!(current instanceof HufflepuffWizard && isTraitActivated()))
				fire();
			else
				JOptionPane.showMessageDialog(null, "The dragon didn't attack you!" + '\n'+"You are lucky, my friend!");
			
			endTurn();
			cont.loadCurrent();

		} else {

			setAllowedMoves(getAllowedMoves() - 1);
			cont.loadCurrent();
			if (getAllowedMoves() == 0) {				
				if (!(current instanceof HufflepuffWizard && isTraitActivated()))
					fire();
				else
					JOptionPane.showMessageDialog(null, "The dragon didn't attack you!" + '\n'+"You are lucky, my friend!");
				
				if(((Wizard)current).getHp()>0)
					checkForEnemies();
				else{
					endTurn();
					JOptionPane.showMessageDialog(null, "Your turn is over!");
					cont.loadCurrent();
				}

			}
		}

	}

	public void endTurn() throws IOException {

		if (getChampions().size() == 0) {

			if (getListener() != null)
				getListener().onFinishingFirstTask(winners);

		} else {
			
			super.endTurn();
			markCells();

		}

	}

	public void onSlytherinTrait(Direction d) throws IOException,
			InCooldownException, OutOfBordersException,
			InvalidTargetCellException {

		Wizard current = (Wizard) getCurrentChamp();
		super.onSlytherinTrait(d);
		current.setTraitCooldown(6);

	}

	public void onHufflepuffTrait() throws InCooldownException {

		Wizard current = (Wizard) getCurrentChamp();
		super.onHufflepuffTrait();
		current.setTraitCooldown(3);
		cont.loadCurrent();

	}

	public Object onRavenclawTrait() throws InCooldownException {

		Wizard current = (Wizard) getCurrentChamp();
		if (current.getTraitCooldown() > 0) {
			throw new InCooldownException(current.getTraitCooldown());
		
		
		}

		setTraitActivated(true);
		super.cont.fi(markedCells);
		
		
		current.setTraitCooldown(5);
		cont.loadCurrent();
		return markedCells;

	}

	public ArrayList<Point> getMarkedCells() {
		return markedCells;
	}

	public void setMarkedCells(ArrayList<Point> markedCells) {
		this.markedCells = markedCells;
	}

	public ArrayList<Champion> getWinners() {
		return winners;
	}

	public void setWinners(ArrayList<Champion> winners) {
		this.winners = winners;
	}

}
