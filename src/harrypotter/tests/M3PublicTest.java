package harrypotter.tests;

import static org.junit.Assert.*;
import harrypotter.exceptions.*;
import harrypotter.model.character.*;
import harrypotter.model.magic.*;
import harrypotter.model.tournament.*;
import harrypotter.model.world.*;

import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class M3PublicTest {

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	private void castDamagingSpellOutOfBordersHelper(Task task,
			Champion currentChamp) throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			int x = 0;
			int y = new Random().nextInt(10);

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			DamagingSpell dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
			((Wizard) task.getCurrentChamp()).getSpells().add(dmg);

			boolean exceptionThrown = false;
			Exception thrown = null;

			try {
				task.castDamagingSpell(dmg, Direction.FORWARD);
			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				thrown = ee;
			}
			task.setCurrentChamp(currentChamp);

			assertTrue(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a damaging spell on a cell out of the borders, OutOfBordersException should be thrown",
					exceptionThrown);

			assertEquals(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a damaging spell on a cell out of the borders, OutOfBordersException should be thrown",
					OutOfBordersException.class, thrown.getClass());

			x = 9;
			y = new Random().nextInt(10);

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
			((Wizard) task.getCurrentChamp()).getSpells().add(dmg);

			exceptionThrown = false;
			thrown = null;

			try {
				task.castDamagingSpell(dmg, Direction.BACKWARD);
			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				thrown = ee;
			}
			task.setCurrentChamp(currentChamp);

			assertTrue(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a damaging spell on a cell out of the borders, OutOfBordersException should be thrown",
					exceptionThrown);

			assertEquals(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a damaging spell on a cell out of the borders, OutOfBordersException should be thrown",
					OutOfBordersException.class, thrown.getClass());

			x = new Random().nextInt(10);
			y = 0;

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
			((Wizard) task.getCurrentChamp()).getSpells().add(dmg);

			exceptionThrown = false;
			thrown = null;
			try {
				task.castDamagingSpell(dmg, Direction.LEFT);
			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				thrown = ee;
			}
			task.setCurrentChamp(currentChamp);

			assertTrue(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a damaging spell on a cell out of the borders, OutOfBordersException should be thrown",
					exceptionThrown);

			assertEquals(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a damaging spell on a cell out of the borders, OutOfBordersException should be thrown",
					OutOfBordersException.class, thrown.getClass());

			x = new Random().nextInt(10);
			y = 9;
			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
			((Wizard) task.getCurrentChamp()).getSpells().add(dmg);

			exceptionThrown = false;
			thrown = null;

			try {
				task.castDamagingSpell(dmg, Direction.RIGHT);
			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				thrown = ee;
			}
			task.setCurrentChamp(currentChamp);

			assertTrue(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a damaging spell on a cell out of the borders, OutOfBordersException should be thrown",
					exceptionThrown);

			assertEquals(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a damaging spell on a cell out of the borders, OutOfBordersException should be thrown",
					OutOfBordersException.class, thrown.getClass());

		}
	}

	private void castRelocatingSpellOnTargetOutOfBordersHelper(Task task,
			Champion currentChamp) throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			int x = 0;
			int y = new Random().nextInt(10);

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));
			RelocatingSpell spell = new RelocatingSpell("rel", 100, 3, 2);
			((Wizard) task.getCurrentChamp()).getSpells().add(spell);

			boolean exceptionThrown = false;
			Exception e = null;
			try {
				task.castRelocatingSpell(spell, Direction.FORWARD,
						Direction.BACKWARD, 2);
			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				e = ee;
			}

			assertTrue(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a relocating spell on a cell out of the borders, OutOfBordersException should be thrown",
					exceptionThrown);

			assertEquals(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a relocating spell on a cell out of the borders, OutOfBordersException should be thrown",
					OutOfBordersException.class, e.getClass());

			x = 9;
			y = new Random().nextInt(10);

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			spell = new RelocatingSpell("rel", 100, 3, 2);
			((Wizard) task.getCurrentChamp()).getSpells().add(spell);

			exceptionThrown = false;
			try {
				task.castRelocatingSpell(spell, Direction.BACKWARD,
						Direction.FORWARD, 2);
			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				e = ee;
			}

			assertTrue(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a relocating spell on a cell out of the borders, OutOfBordersException should be thrown",
					exceptionThrown);

			assertEquals(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a relocating spell on a cell out of the borders, OutOfBordersException should be thrown",
					OutOfBordersException.class, e.getClass());

			x = new Random().nextInt(10);
			y = 0;

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			spell = new RelocatingSpell("rel", 100, 3, 2);
			((Wizard) task.getCurrentChamp()).getSpells().add(spell);

			exceptionThrown = false;

			try {
				task.castRelocatingSpell(spell, Direction.LEFT,
						Direction.RIGHT, 2);
			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				e = ee;
			}

			assertTrue(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a relocating spell on a cell out of the borders, OutOfBordersException should be thrown",
					exceptionThrown);

			assertEquals(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a relocating spell on a cell out of the borders, OutOfBordersException should be thrown",
					OutOfBordersException.class, e.getClass());

			x = new Random().nextInt(10);
			y = 9;
			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			spell = new RelocatingSpell("rel", 100, 3, 2);
			((Wizard) task.getCurrentChamp()).getSpells().add(spell);

			exceptionThrown = false;
			try {
				task.castRelocatingSpell(spell, Direction.RIGHT,
						Direction.LEFT, 2);
			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				e = ee;
			}

			assertTrue(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a relocating spell on a cell out of the borders, OutOfBordersException should be thrown",
					exceptionThrown);

			assertEquals(
					"In the "
							+ task.getClass().getSimpleName()
							+ ", when a champion tries to cast a relocating spell on a cell out of the borders, OutOfBordersException should be thrown",
					OutOfBordersException.class, e.getClass());

		}
	}

	private void damageInvalidTarget(Task task, DamagingSpell s, Direction d,
			Point targetPoint) throws Exception {

		Champion curr = task.getCurrentChamp();
		Class<? extends Cell> targetClass = task.getMap()[targetPoint.x][targetPoint.y]
				.getClass();
		int PrevIp = ((Wizard) task.getCurrentChamp()).getIp();
		try {

			task.castDamagingSpell(s, d);

			fail("If a champion tries to use a damaging spell on "
					+ targetClass.getSimpleName()
					+ ", an exception of type InvalidTargetCellException should be thrown ");

		} catch (InvalidTargetCellException e) {

			assertEquals(
					"If a champion tries to use a damaging spell on "
							+ targetClass.getSimpleName()
							+ ", an exception of type InvalidTargetCellException should be thrown ",
					InvalidTargetCellException.class, e.getClass());

			assertEquals("If a champion tries to use a damaging spell on "
					+ targetClass.getSimpleName()
					+ ", the cell should not be affected ", targetClass,
					task.getMap()[targetPoint.x][targetPoint.y].getClass());

			assertEquals("If a champion tries to use a damaging spell on "
					+ targetClass.getSimpleName()
					+ ", the spell coolDown should not change ", 0,
					s.getCoolDown());

			assertEquals("If a champion tries to use a damaging spell on "
					+ targetClass.getSimpleName()
					+ ", the champion's IP should not change ", PrevIp,
					((Wizard) task.getCurrentChamp()).getIp());

			assertEquals("If a champion tries to use a damaging spell on "
					+ targetClass.getSimpleName()
					+ ", the allowedMoves should not change ", 1,
					task.getAllowedMoves());

			assertEquals("If a champion tries to use a damaging spell on "
					+ targetClass.getSimpleName()
					+ ", the currentChamp should not change ", curr,
					task.getCurrentChamp());

		}

	}

	private Cell getCellClone(Cell cell, Champion champion) {

		if (cell instanceof CupCell)
			return new CupCell();
		else if (cell instanceof TreasureCell)
			return new TreasureCell(champion);
		else if (cell instanceof ChampionCell)
			return new ChampionCell(champion);
		else if (cell instanceof WallCell)
			return new WallCell();
		else if (cell instanceof ObstacleCell)
			return new ObstacleCell(new PhysicalObstacle(((ObstacleCell) cell)
					.getObstacle().getHp()));
		else if (cell instanceof CollectibleCell)
			return new CollectibleCell(new Potion("Senzo", 100));

		return new EmptyCell();

	}

	private Spell getSpellClone(Spell spell) {
		Spell clone = null;
		if (spell instanceof DamagingSpell)
			clone = new DamagingSpell(spell.getName(), spell.getCost(),
					spell.getDefaultCooldown(),
					((DamagingSpell) spell).getDamageAmount());
		else if (spell instanceof RelocatingSpell)
			clone = new RelocatingSpell(spell.getName(), spell.getCost(),
					spell.getDefaultCooldown(),
					((RelocatingSpell) spell).getRange());
		else if (spell instanceof HealingSpell)
			clone = new HealingSpell(spell.getName(), spell.getCost(),
					spell.getDefaultCooldown(),
					((HealingSpell) spell).getHealingAmount());
		clone.setCoolDown(spell.getCoolDown());
		return clone;
	}

	private Task getTaskClone(Task task) throws Exception {
		Task clone = null;
		int length = task.getChampions().size();
		ArrayList<Champion> temp = new ArrayList<Champion>();
		for (int i = 0; i < length; i++) {
			temp.add((Champion) getWizardClone((Wizard) (task.getChampions()
					.get(i))));
		}
		if (task instanceof FirstTask)
			clone = new FirstTask(temp);
		else if (task instanceof SecondTask)
			clone = new SecondTask(temp);
		else
			clone = new ThirdTask(temp);
		clone.setTraitActivated(task.isTraitActivated());
		clone.setAllowedMoves(task.getAllowedMoves());
		clone.setCurrentChamp((Champion) getWizardClone((Wizard) task
				.getCurrentChamp()));
		clone.getChampions().clear();
		for (int i = 0; i < length; i++) {
			clone.getChampions().add(
					(Champion) getWizardClone((Wizard) (task.getChampions()
							.get(i))));
		}

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				clone.getMap()[i][j] = getCellClone(task.getMap()[i][j],
						clone.getCurrentChamp());
		return clone;

	}

	private Wizard getWizardClone(Wizard wizard) {

		Wizard clone = null;
		if (wizard instanceof GryffindorWizard)
			clone = new GryffindorWizard(wizard.getName());
		else if (wizard instanceof SlytherinWizard)
			clone = new SlytherinWizard(wizard.getName());
		else if (wizard instanceof HufflepuffWizard)
			clone = new HufflepuffWizard(wizard.getName());
		else if (wizard instanceof RavenclawWizard)
			clone = new RavenclawWizard(wizard.getName());
		clone.setHp(wizard.getHp());
		clone.setIp(wizard.getIp());
		clone.setLocation(new Point(wizard.getLocation().x, wizard
				.getLocation().y));
		clone.setTraitCooldown(wizard.getTraitCooldown());
		clone.getSpells().clear();
		for (int i = 0; i < wizard.getSpells().size(); i++)
			clone.getSpells().add(getSpellClone(wizard.getSpells().get(i)));
		for (int i = 0; i < wizard.getInventory().size(); i++)
			clone.getInventory().add(
					new Potion(((Potion) wizard.getInventory().get(i))
							.getName(), ((Potion) wizard.getInventory().get(i))
							.getAmount()));
		return clone;
	}

	private void onSlythMovesToInvalidCellsHelper(Task task,
			Point championLocation, Point targetLocation, Direction direction,
			Cell invalidCell, SlytherinWizard s, Champion c) throws Exception {

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(s);
		task.getMap()[championLocation.x][championLocation.y] = new ChampionCell(
				s);
		((Wizard) task.getCurrentChamp()).setLocation(championLocation);

		if (invalidCell instanceof CollectibleCell)
			task.getMap()[targetLocation.x][targetLocation.y] = new CollectibleCell(
					new Potion("p", 100));
		else if (invalidCell instanceof TreasureCell)
			task.getMap()[targetLocation.x][targetLocation.y] = new TreasureCell(
					s);
		else if (invalidCell instanceof ObstacleCell)
			task.getMap()[targetLocation.x][targetLocation.y] = new ObstacleCell(
					new PhysicalObstacle(100));
		else if (invalidCell instanceof WallCell)
			task.getMap()[targetLocation.x][targetLocation.y] = new WallCell();
		else if (invalidCell instanceof CupCell)
			task.getMap()[targetLocation.x][targetLocation.y] = new CupCell();
		else if (invalidCell instanceof ChampionCell)
			task.getMap()[targetLocation.x][targetLocation.y] = new ChampionCell(
					c);

		Exception thrown = null;

		boolean exceptionThrown = false;
		try {
			s.setTraitDirection(direction);
			s.useTrait();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
			thrown = e1;
		}

		assertTrue("In the " + task.getClass().getSimpleName()
				+ ", when slytherin trait is activated in the " + direction
				+ " direction, and the target cell is "
				+ invalidCell.getClass().getSimpleName()
				+ ", InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		assertEquals("In the " + task.getClass().getSimpleName()
				+ ", when slytherin trait is activated in the " + direction
				+ " direction, and the target cell is "
				+ invalidCell.getClass().getSimpleName()
				+ ", InvalidTargetCell Exception should be thrown",
				InvalidTargetCellException.class, thrown.getClass());

		task.setCurrentChamp(s);

		Point newLocation = ((Wizard) task.getCurrentChamp()).getLocation();
		assertEquals(
				"In the "
						+ task.getClass().getSimpleName()
						+ ", when slytherin trait is activated in the "
						+ direction
						+ " direction, and the target cell is "
						+ invalidCell.getClass().getSimpleName()
						+ ", it is considered an invalid move and the champion's location shouldn't change",
				championLocation, newLocation);

		assertEquals(
				"In the "
						+ task.getClass().getSimpleName()
						+ ", when slytherin trait is activated in the "
						+ direction
						+ " direction, and the target cell is "
						+ invalidCell.getClass().getSimpleName()
						+ ", it is considered an invalid move and the target cell shouldn't change",
				invalidCell.getClass(),
				task.getMap()[targetLocation.x][targetLocation.y].getClass());
	}

	private void relocateInvalidTarget(Task task, RelocatingSpell s,
			Direction d, Direction t, int r, Point targetPoint, Point destPoint)
			throws Exception {

		Champion curr = task.getCurrentChamp();
		Class<? extends Cell> targetClass = task.getMap()[targetPoint.x][targetPoint.y]
				.getClass();
		int PrevIp = ((Wizard) task.getCurrentChamp()).getIp();
		try {

			task.castRelocatingSpell(s, d, t, r);

			fail("If a champion tries to use a relocating spell on "
					+ targetClass.getSimpleName()
					+ ", an exception of type InvalidTargetCellException should be thrown ");

		} catch (InvalidTargetCellException e) {

			assertEquals(
					"If a champion tries to use a relocating spell on "
							+ targetClass.getSimpleName()
							+ ", an exception of type InvalidTargetCellException should be thrown ",
					InvalidTargetCellException.class, e.getClass());

			assertEquals("If a champion tries to use a relocating spell on "
					+ targetClass.getSimpleName()
					+ ", the cell should not be affected ", targetClass,
					task.getMap()[targetPoint.x][targetPoint.y].getClass());

			assertEquals("If a champion tries to use a relocating spell on "
					+ targetClass.getSimpleName()
					+ ", the target cell should not change ", EmptyCell.class,
					task.getMap()[destPoint.x][destPoint.y].getClass());

			assertEquals("If a champion tries to use a relocating spell on "
					+ targetClass.getSimpleName()
					+ ", the spell coolDown should not change ", 0,
					s.getCoolDown());

			assertEquals("If a champion tries to use a relocating spell on "
					+ targetClass.getSimpleName()
					+ ", the champion's IP should not change ", PrevIp,
					((Wizard) task.getCurrentChamp()).getIp());

			assertEquals("If a champion tries to use a relocating spell on "
					+ targetClass.getSimpleName()
					+ ", the allowedMoves should not change ", 1,
					task.getAllowedMoves());

			assertEquals("If a champion tries to use a relocating spell on "
					+ targetClass.getSimpleName()
					+ ", the currentChamp should not change ", curr,
					task.getCurrentChamp());

		}

	}

	private void relocateToInvalidDestination(Task task, RelocatingSpell s,
			Direction d, Direction t, int r, Point targetPoint, Point destPoint)
			throws Exception {

		Champion curr = task.getCurrentChamp();
		Class<? extends Cell> targetClass = task.getMap()[targetPoint.x][targetPoint.y]
				.getClass();
		Class<? extends Cell> destClass = task.getMap()[destPoint.x][destPoint.y]
				.getClass();
		int PrevIp = ((Wizard) task.getCurrentChamp()).getIp();

		try {

			task.castRelocatingSpell(s, d, t, r);

			fail("If a champion tries to relocate "
					+ targetClass.getSimpleName()
					+ " to a cell of type "
					+ destClass.getSimpleName()
					+ ", an exception of type InvalidTargetCellException should be thrown ");

		} catch (InvalidTargetCellException e) {

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell of type "
							+ destClass.getSimpleName()
							+ ", an exception of type InvalidTargetCellException should be thrown ",
					InvalidTargetCellException.class, e.getClass());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell of type " + destClass.getSimpleName()
							+ ", the cell should not be affected ",
					targetClass,
					task.getMap()[targetPoint.x][targetPoint.y].getClass());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell of type " + destClass.getSimpleName()
							+ ", the destination cell should not change ",
					destClass,
					task.getMap()[destPoint.x][destPoint.y].getClass());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell of type " + destClass.getSimpleName()
							+ ", the spell coolDown should not change ", 0,
					s.getCoolDown());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell of type " + destClass.getSimpleName()
							+ ", the champion's IP should not change ", PrevIp,
					((Wizard) task.getCurrentChamp()).getIp());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell of type " + destClass.getSimpleName()
							+ ", the allowedMoves should not change ", 1,
					task.getAllowedMoves());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell of type " + destClass.getSimpleName()
							+ ", the currentChamp should not change ", curr,
					task.getCurrentChamp());

		}

	}

	private void relocateToOutOfBorders(Task task, RelocatingSpell s,
			Direction d, Direction t, int r, Point targetPoint)
			throws Exception {

		Champion curr = task.getCurrentChamp();
		Class<? extends Cell> targetClass = task.getMap()[targetPoint.x][targetPoint.y]
				.getClass();
		int PrevIp = ((Wizard) task.getCurrentChamp()).getIp();

		try {

			task.castRelocatingSpell(s, d, t, r);

			fail("If a champion tries to relocate "
					+ targetClass.getSimpleName()
					+ " to a cell outside the borders of the map, an exception of type OutOfBordersException should be thrown ");

		} catch (OutOfBordersException e) {

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell outside the borders of the map, an exception of type OutOfBordersException should be thrown ",
					OutOfBordersException.class, e.getClass());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell outside the borders of the map, the cell should not be affected ",
					targetClass,
					task.getMap()[targetPoint.x][targetPoint.y].getClass());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell outside the borders of the map, the spell coolDown should not change ",
					0, s.getCoolDown());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell outside the borders of the map, the champion's IP should not change ",
					PrevIp, ((Wizard) task.getCurrentChamp()).getIp());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell outside the borders of the map, the allowedMoves should not change ",
					1, task.getAllowedMoves());

			assertEquals(
					"If a champion tries to relocate "
							+ targetClass.getSimpleName()
							+ " to a cell outside the borders of the map, the currentChamp should not change ",
					curr, task.getCurrentChamp());

		}

	}

	private void SlytherinMovesToCellOutOfBordersOnTraitActivatedHelper(
			Task task, Champion currentChamp) throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			int x = 0;
			int y = new Random().nextInt(10);

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			boolean exceptionThrown = false;
			Exception thrown = null;

			try {
				((SlytherinWizard) currentChamp)
						.setTraitDirection(Direction.FORWARD);
				currentChamp.useTrait();
			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				thrown = ee;
			}

			assertTrue(
					"In the "
							+ task.getClass()
							+ ", when slytherin trait is activated in the Forward direction, and the target cell is out of the borders, OutOfBordersException should be thrown",
					exceptionThrown && thrown instanceof OutOfBordersException);

			x = 9;
			y = new Random().nextInt(10);

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			exceptionThrown = false;
			thrown = null;

			try {

				((SlytherinWizard) currentChamp)
						.setTraitDirection(Direction.BACKWARD);
				currentChamp.useTrait();

			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				thrown = ee;
			}

			assertTrue(
					"In the "
							+ task.getClass()
							+ ", when slytherin trait is activated in the Backward direction, and the target cell is out of the borders, OutOfBordersException should be thrown",
					exceptionThrown && thrown instanceof OutOfBordersException);

			x = new Random().nextInt(10);
			y = 0;

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			exceptionThrown = false;
			thrown = null;

			try {

				((SlytherinWizard) currentChamp)
						.setTraitDirection(Direction.LEFT);
				currentChamp.useTrait();

			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				thrown = ee;
			}

			assertTrue(
					"In the "
							+ task.getClass()
							+ ", when slytherin trait is activated in the Left direction, and the target cell is out of the borders, OutOfBordersException should be thrown",
					exceptionThrown && thrown instanceof OutOfBordersException);

			x = new Random().nextInt(10);
			y = 9;
			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			task.setCurrentChamp(currentChamp);
			task.getMap()[x][y] = new ChampionCell(currentChamp);

			((Wizard) task.getCurrentChamp()).setLocation(new Point(x, y));

			exceptionThrown = false;
			thrown = null;

			try {

				((SlytherinWizard) currentChamp)
						.setTraitDirection(Direction.RIGHT);
				currentChamp.useTrait();

			} catch (OutOfBordersException ee) {
				exceptionThrown = true;
				thrown = ee;
			}

			assertTrue(
					"In the "
							+ task.getClass()
							+ ", when slytherin trait is activated in the Right direction, and the target cell is out of the borders, OutOfBordersException should be thrown",
					exceptionThrown && thrown instanceof OutOfBordersException);

		}
	}

	@Test(timeout = 1000)
	public void tesInvalidTargetCellExceptionExtendsException() {
		testClassIsSubClass(InvalidTargetCellException.class,
				InvalidActionException.class);
	}

	@Test(timeout = 1000)
	public void testCastDamagingSpellOutOfBordersFirstTask() throws Exception {

		testMethodExistsInClass(Task.class, "castDamagingSpell", true,
				Void.TYPE, DamagingSpell.class, Direction.class);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);
		castDamagingSpellOutOfBordersHelper(task, h);
	}

	@Test(timeout = 1000)
	public void testCastDamagingSpellWhileCoolingDownThirdTask()
			throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);
			ThirdTask task1 = new ThirdTask(e);
			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);
			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}
			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[2][1] = new ChampionCell(h);
			h.setLocation(new Point(2, 1));
			task1.getMap()[3][1] = new ChampionCell(r);
			r.setLocation(new Point(3, 1));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));
			int initFirstSpellCooldown = (int) ((5 * Math.random()) + 3);
			int initSecondSpellCooldown = (int) ((5 * Math.random()) + 3);
			int initThirdSpellCooldown = (int) ((5 * Math.random()) + 3);
			HealingSpell s1 = new HealingSpell("HEL", 100,
					initFirstSpellCooldown, 200);
			RelocatingSpell s2 = new RelocatingSpell("RELOC", 5,
					initSecondSpellCooldown, 5);
			DamagingSpell s3 = new DamagingSpell("DMG", 10,
					initThirdSpellCooldown, 50);
			g.getSpells().add(s1);
			g.getSpells().add(s2);
			g.getSpells().add(s3);

			task1.setCurrentChamp(g);

			try {
				task1.castDamagingSpell(s3, Direction.LEFT);
			} catch (InCooldownException exp) {
				fail("If a champion tries to use a spell while it is NOT in coolDown, an exception should NOT be thrown");
			}

			task1.setCurrentChamp(h);
			task1.moveForward();

			task1.setCurrentChamp(r);
			task1.moveBackward();

			task1.setCurrentChamp(s);
			task1.moveLeft();

			task1.setCurrentChamp(g);

			ThirdTask oldTask = (ThirdTask) getTaskClone(task1);

			try {
				task1.castDamagingSpell(s3, Direction.LEFT);

				fail("If a champion tries to use a spell while it is still in coolDown, an exception of type InCooldownException should be thrown");

			} catch (InCooldownException exp) {

				testNoChange(oldTask, task1, exp.getClass().getSimpleName());

				assertEquals(
						"If a champion tries to use a spell while it is still in coolDown, an exception of type InCooldownException should be thrown with the correct \"remainingTurns\" ",
						s3.getCoolDown(),
						((InCooldownException) exp).getRemainingTurns());

			}
		}
	}

	@Test(timeout = 1000)
	public void testCastHealingSpellWhileCoolingDownSecondTask()
			throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);
			SecondTask task1 = new SecondTask(e);
			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);
			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}
			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[3][3] = new ChampionCell(h);
			h.setLocation(new Point(3, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));
			int initFirstSpellCooldown = (int) ((5 * Math.random()) + 3);
			int initSecondSpellCooldown = (int) ((5 * Math.random()) + 3);
			int initThirdSpellCooldown = (int) ((5 * Math.random()) + 3);
			HealingSpell s1 = new HealingSpell("HEL", 100,
					initFirstSpellCooldown, 200);
			RelocatingSpell s2 = new RelocatingSpell("RELOC", 5,
					initSecondSpellCooldown, 5);
			DamagingSpell s3 = new DamagingSpell("DMG", 10,
					initThirdSpellCooldown, 50);
			g.getSpells().add(s1);
			g.getSpells().add(s2);
			g.getSpells().add(s3);

			task1.setCurrentChamp(g);

			try {
				task1.castHealingSpell(s1);
			} catch (InCooldownException exp) {
				fail("If a champion tries to use a spell while it is NOT in coolDown, an exception should NOT be thrown");
			}

			task1.setCurrentChamp(h);
			task1.moveRight();

			task1.setCurrentChamp(r);
			task1.moveLeft();

			task1.setCurrentChamp(s);
			task1.moveLeft();

			task1.setCurrentChamp(g);

			SecondTask oldTask = (SecondTask) getTaskClone(task1);

			try {
				task1.castHealingSpell(s1);

				fail("If a champion tries to use a spell while it is still in coolDown, an exception of type InCooldownException should be thrown");

			} catch (InCooldownException exp) {

				testNoChange(oldTask, task1, exp.getClass().getSimpleName());

				assertEquals(
						"If a champion tries to use a spell while it is still in coolDown, an exception of type InCooldownException should be thrown with the correct \"remainingTurns\" ",
						s1.getCoolDown(),
						((InCooldownException) exp).getRemainingTurns());

			}
		}
	}

	@Test(timeout = 1000)
	public void testCastingDamagingSpellWithEnoughIP() throws Exception {

		testMethodExistsInClass(Task.class, "castDamagingSpell", true,
				Void.TYPE, DamagingSpell.class, Direction.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(g);
		task.getMap()[4][3] = new ChampionCell(g);

		task.getMap()[5][3] = new ChampionCell(r);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 3));

		r.setLocation(new Point(5, 3));

		DamagingSpell dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
		((Wizard) task.getCurrentChamp()).getSpells().add(dmg);

		boolean exceptionThrown = false;
		Exception thrown = null;
		try {
			task.castDamagingSpell(dmg, Direction.BACKWARD);
		} catch (NotEnoughIPException ee) {
			exceptionThrown = true;
			thrown = ee;
		}

		assertFalse(
				"In the First Task, when a champion tries to cast a damaging spell while having enough IP, no Exception should be thrown",
				exceptionThrown && thrown instanceof NotEnoughIPException);
		assertEquals(
				"In FirstTask, when the current champion casts a damaging spell on a Champion cell, the hp of the champion in the cell should be affected",
				450, r.getHp());

		// Second Task
		e = new ArrayList<>();
		g = new GryffindorWizard("gryff");
		h = new HufflepuffWizard("huff");
		r = new RavenclawWizard("raven");
		s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		SecondTask task2 = new SecondTask(e);
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(h);
		task2.getMap()[4][3] = new ChampionCell(h);
		Merperson Merperson = new Merperson(1000, 10);
		task2.getMap()[5][3] = new ObstacleCell(Merperson);

		((Wizard) task2.getCurrentChamp()).setLocation(new Point(4, 3));

		dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
		((Wizard) task2.getCurrentChamp()).getSpells().add(dmg);

		exceptionThrown = false;
		thrown = null;

		try {
			task2.castDamagingSpell(dmg, Direction.BACKWARD);
		} catch (NotEnoughIPException ee) {
			exceptionThrown = true;
			thrown = ee;
		}

		assertFalse(
				"In the Second Task, when a champion tries to cast a damaging spell while having enough IP, no Exception should be thrown",
				exceptionThrown && thrown instanceof NotEnoughIPException);
		assertEquals(
				"In the SecondTask, when the current champion casts a damaging spell, the damage value of the spell should be deducted from the hp of the obstacles",
				700, Merperson.getHp());

		// Third Task
		e = new ArrayList<>();
		g = new GryffindorWizard("gryff");
		h = new HufflepuffWizard("huff");
		r = new RavenclawWizard("raven");
		s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		ThirdTask task3 = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(g);
		task3.getMap()[4][3] = new ChampionCell(g);

		task3.getMap()[5][3] = new ChampionCell(h);

		((Wizard) task3.getCurrentChamp()).setLocation(new Point(4, 3));

		h.setLocation(new Point(5, 3));

		dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
		((Wizard) task3.getCurrentChamp()).getSpells().add(dmg);

		thrown = null;

		exceptionThrown = false;
		try {
			task3.castDamagingSpell(dmg, Direction.BACKWARD);
		} catch (NotEnoughIPException ee) {
			exceptionThrown = true;
			thrown = ee;
		}

		assertFalse(
				"In the Third Task, when a champion tries to cast a damaging spell while having enough IP, no Exception should be thrown",
				exceptionThrown && thrown instanceof NotEnoughIPException);
		assertEquals(
				"In the Third Task, when the current champion casts a damaging spell on a Champion cell, the hp of the champion in the cell should be deducted by half the damage in case of HufflepuffWizard",
				850, h.getHp());
	}

	@Test(timeout = 1000)
	public void testCastingDamagingSpellWithNotEnoughIPInFirstTask()
			throws Exception {

		testMethodExistsInClass(Task.class, "castDamagingSpell", true,
				Void.TYPE, DamagingSpell.class, Direction.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		g.setIp(50);
		int hp_R = r.getHp();
		task.setCurrentChamp(g);
		task.getMap()[4][3] = new ChampionCell(g);

		task.getMap()[5][3] = new ChampionCell(r);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 3));

		r.setLocation(new Point(5, 3));

		DamagingSpell dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
		((Wizard) task.getCurrentChamp()).getSpells().add(dmg);

		FirstTask oldTask = (FirstTask) getTaskClone(task);
		boolean exceptionThrown = false;
		Exception thrown = null;
		try {
			task.castDamagingSpell(dmg, Direction.BACKWARD);
		} catch (NotEnoughIPException ee) {
			exceptionThrown = true;
			thrown = ee;
		}

		assertTrue(
				"In the First Task, when a champion tries to cast a damaging spell while not having enough IP, NotEnoughIPException should be thrown",
				exceptionThrown);

		assertEquals(
				"In FirstTask, when the current champion casts a damaging spell on a Champion cell while not having enough IP, the hp of the champion in the cell should not be affected",
				hp_R, r.getHp());

		testNoChange(oldTask, task, "NotEnoughIPException");

		assertEquals(
				"In FirstTask, when the current champion casts a damaging spell on a Champion cell while not having enough IP, NotEnoughIPException should be thrown with the correct \"requiredIP\" ",
				dmg.getCost(), ((NotEnoughIPException) thrown).getRequiredIP());

		assertEquals(
				"In FirstTask, when the current champion casts a damaging spell on a Champion cell while not having enough IP, NotEnoughIPException should be thrown with the correct \"remainingIP\" ",
				dmg.getCost() - g.getIp(),
				((NotEnoughIPException) thrown).getRemainingIP());

	}

	@Test(timeout = 1000)
	public void testCastingHealingSpellWithEnoughIP() throws Exception {

		testMethodExistsInClass(Task.class, "castHealingSpell", true,
				Void.TYPE, HealingSpell.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e) {

			@Override
			public void finalizeAction() {

			}

			@Override
			public void useSpell(Spell s) {

			}
		};

		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		HealingSpell spell = new HealingSpell("HEL", 300, 4, 120);
		g.getSpells().add(spell);
		g.setHp(g.getHp() - 200);
		task1.setCurrentChamp(g);
		int initHp = g.getHp();
		int healingHp = spell.getHealingAmount();
		boolean exceptionThrown = false;
		Exception thrown = null;

		try {
			task1.castHealingSpell(spell);
		} catch (NotEnoughIPException ee) {
			exceptionThrown = true;
			thrown = ee;
		}

		assertFalse(
				"In the First Task, when a champion tries to cast a Healing spell while having enough IP, no Exception should be thrown",
				exceptionThrown && thrown instanceof NotEnoughIPException);

		assertEquals(
				"In FirstTask after castHealingSpell, the current champion's health points should be healed by spell's healing amount",
				initHp + healingHp, g.getHp());

		SecondTask task2 = new SecondTask(e) {
			@Override
			public void finalizeAction() {

			}

			@Override
			public void useSpell(Spell s) {

			}
		};
		task2.getChampions().clear();
		task2.getChampions().add(g);
		task2.getChampions().add(h);
		task2.getChampions().add(s);
		task2.getChampions().add(r);
		g = new GryffindorWizard("gryff");
		spell = new HealingSpell("HEL", 300, 4, 120);
		g.getSpells().add(spell);
		g.setHp(g.getHp() - 200);
		task2.setCurrentChamp(g);
		initHp = g.getHp();
		healingHp = spell.getHealingAmount();
		exceptionThrown = false;
		thrown = null;

		try {
			task2.castHealingSpell(spell);
		} catch (NotEnoughIPException ee) {
			exceptionThrown = true;
			thrown = ee;
		}

		assertFalse(
				"In the Second Task, when a champion tries to cast a Healing spell while having enough IP, no Exception should be thrown",
				exceptionThrown && thrown instanceof NotEnoughIPException);
		assertEquals(
				"In SecondTask after castHealingSpell, the current champion's health points should be healed by spell's healing amount",
				initHp + healingHp, g.getHp());

		ThirdTask task3 = new ThirdTask(e) {
			@Override
			public void finalizeAction() {

			}

			@Override
			public void useSpell(Spell s) {

			}
		};
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		task3.getChampions().add(s);
		g = new GryffindorWizard("gryff");
		spell = new HealingSpell("HEL", 300, 4, 120);
		g.getSpells().add(spell);
		g.setHp(g.getHp() - 200);
		task3.setCurrentChamp(g);
		initHp = g.getHp();
		healingHp = spell.getHealingAmount();
		exceptionThrown = false;
		thrown = null;
		try {
			task3.castHealingSpell(spell);
		} catch (NotEnoughIPException ee) {
			exceptionThrown = true;
			thrown = ee;
		}

		assertFalse(
				"In the Third Task, when a champion tries to cast a Healing spell while having enough IP, no Exception should be thrown",
				exceptionThrown && thrown instanceof NotEnoughIPException);
		assertEquals(
				"In ThirdTask after castHealingSpell, the current champion's health points should be healed by spell's healing amount",
				initHp + healingHp, g.getHp());

	}

	@Test(timeout = 1000)
	public void testCastingHealingSpellWithNotEnoughIPInThirdTask()
			throws Exception {

		testMethodExistsInClass(Task.class, "castHealingSpell", true,
				Void.TYPE, HealingSpell.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		ThirdTask task3 = new ThirdTask(e) {
			@Override
			public void finalizeAction() {

			}

			@Override
			public void useSpell(Spell s) {

			}
		};
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		task3.getChampions().add(s);
		g = new GryffindorWizard("gryff");
		g.setIp(299);
		HealingSpell spell = new HealingSpell("HEL", 300, 4, 120);
		g.getSpells().add(spell);
		g.setHp(g.getHp() - 200);
		task3.setCurrentChamp(g);
		int initHp = g.getHp();
		g.setLocation(new Point(0, 0));

		ThirdTask oldTask = (ThirdTask) getTaskClone(task3);

		boolean exceptionThrown = false;
		Exception thrown = null;

		try {
			task3.castHealingSpell(spell);
		} catch (NotEnoughIPException ee) {
			exceptionThrown = true;
			thrown = ee;
		}

		assertTrue(
				"In the Third Task, when a champion tries to cast a Healing spell while not having enough IP, NotEnoughIPException should be thrown",
				exceptionThrown);
		assertEquals(
				"In ThirdTask after castHealingSpell, the current champion's health points should be healed by spell's healing amount",
				initHp, g.getHp());

		testNoChange(oldTask, task3, "NotEnoughIPException");

		assertEquals(
				"In ThirdTask, when the current champion casts a Healing spell while not having enough IP, NotEnoughIPException should be thrown with the correct \"requiredIP\" ",
				spell.getCost(),
				((NotEnoughIPException) thrown).getRequiredIP());

		assertEquals(
				"In ThirdTask, when the current champion casts a Healing spell while not having enough IP, NotEnoughIPException should be thrown with the correct \"remainingIP\" ",
				spell.getCost() - g.getIp(),
				((NotEnoughIPException) thrown).getRemainingIP());

	}

	@Test(timeout = 1000)
	public void testCastingRelocatingSpellWithNotEnoughIPInFirstTask()
			throws Exception {

		testMethodExistsInClass(Task.class, "castRelocatingSpell", true,
				Void.TYPE, RelocatingSpell.class, Direction.class,
				Direction.class, int.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		h.setIp(20);

		task.getMap()[7][8] = new ChampionCell(h);

		PhysicalObstacle p = new PhysicalObstacle(1000);
		task.getMap()[8][8] = new ObstacleCell(p);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(7, 8));

		RelocatingSpell rel = new RelocatingSpell("Accio", 100, 1, 1);
		((Wizard) task.getCurrentChamp()).getSpells().add(rel);

		boolean exceptionThrown = false;
		Exception thrown = null;
		FirstTask oldTask = (FirstTask) getTaskClone(task);
		try {
			task.castRelocatingSpell(rel, Direction.BACKWARD,
					Direction.FORWARD, 1);
		} catch (NotEnoughIPException ee) {
			exceptionThrown = true;
			thrown = ee;
		}

		assertTrue(
				"In the First Task, when a champion tries to cast a Relocating spell while not having enough IP, NotEnoughIPException should be thrown",
				exceptionThrown);

		assertEquals(
				"In the First Task, when a champion tries to cast a Relocating spell while not having enough IP, no cells should be changed",
				task.getMap()[6][8].getClass(), EmptyCell.class);

		assertEquals(
				"In the First Task, when a champion tries to cast a Relocating spell while not having enough IP, no cells should be changed",
				task.getMap()[8][8].getClass(), ObstacleCell.class);

		testNoChange(oldTask, task, "NotEnoughIPException");

		assertEquals(
				"In the First Task, when the current champion casts a Relocating spell while not having enough IP, NotEnoughIPException should be thrown with the correct \"requiredIP\" ",
				rel.getCost(), ((NotEnoughIPException) thrown).getRequiredIP());

		assertEquals(
				"In the First Task, when the current champion casts a Relocating spell while not having enough IP, NotEnoughIPException should be thrown with the correct \"remainingIP\" ",
				rel.getCost() - h.getIp(),
				((NotEnoughIPException) thrown).getRemainingIP());

	}

	@Test(timeout = 1000)
	public void testCastRelocatingSpellOnTargetOutOfBordersThirdTask()
			throws Exception {

		testMethodExistsInClass(Task.class, "castDamagingSpell", true,
				Void.TYPE, DamagingSpell.class, Direction.class);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		ThirdTask task = new ThirdTask(e);
		castRelocatingSpellOnTargetOutOfBordersHelper(task, g);

	}

	@Test(timeout = 1000)
	public void testCastRelocationSpellWhileCoolingDownFirstTask()
			throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);
			FirstTask task1 = new FirstTask(e);
			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);
			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}
			task1.getMap()[2][2] = new ChampionCell(g);
			g.setLocation(new Point(2, 2));
			task1.getMap()[2][3] = new ChampionCell(h);
			h.setLocation(new Point(2, 3));
			task1.getMap()[2][4] = new ChampionCell(r);
			r.setLocation(new Point(2, 4));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			int initFirstSpellCooldown = (int) ((5 * Math.random()) + 3);
			int initSecondSpellCooldown = (int) ((5 * Math.random()) + 3);
			int initThirdSpellCooldown = (int) ((5 * Math.random()) + 3);

			HealingSpell s1 = new HealingSpell("HEL", 100,
					initFirstSpellCooldown, 200);
			RelocatingSpell s2 = new RelocatingSpell("RELOC", 5,
					initSecondSpellCooldown, 5);
			DamagingSpell s3 = new DamagingSpell("DMG", 10,
					initThirdSpellCooldown, 50);

			g.getSpells().add(s1);
			g.getSpells().add(s2);
			g.getSpells().add(s3);

			task1.setCurrentChamp(g);

			try {
				task1.castRelocatingSpell(s2, Direction.RIGHT,
						Direction.FORWARD, 1);
			} catch (InCooldownException exp) {
				fail("If a champion tries to use a relocating spell while it is NOT in coolDown, an exception should NOT be thrown");
			}

			task1.setCurrentChamp(h);
			task1.moveForward();

			task1.setCurrentChamp(r);

			task1.moveLeft();

			task1.setCurrentChamp(s);
			task1.moveLeft();

			task1.setCurrentChamp(g);

			FirstTask oldTask = (FirstTask) getTaskClone(task1);

			try {

				task1.castRelocatingSpell(s2, Direction.RIGHT, Direction.LEFT,
						2);

				fail("If a champion tries to use a spell while it is still in coolDown, an exception of type InCooldownException should be thrown");

			} catch (InCooldownException exp) {

				testNoChange(oldTask, task1, exp.getClass().getSimpleName());

				assertEquals(
						"If a champion tries to use a spell while it is still in coolDown, an exception of type InCooldownException should be thrown with the correct \"remainingTurns\" ",
						s2.getCoolDown(),
						((InCooldownException) exp).getRemainingTurns());

			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void testClassIsAbstract(Class aClass) {
		assertTrue("You should not be able to create new instances from "
				+ aClass.getName() + " class.",
				Modifier.isAbstract(aClass.getModifiers()));
	}

	@SuppressWarnings("rawtypes")
	private void testClassIsSubClass(Class subClass, Class superClass) {
		assertEquals(subClass.getName() + " class should inherit from "
				+ superClass.getName() + ".", superClass,
				subClass.getSuperclass());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testConstructorExists(Class aClass, Class... inputs) {
		boolean thrown = false;
		try {
			aClass.getConstructor(inputs);
		} catch (NoSuchMethodException e) {
			thrown = true;
		}

		if (inputs.length > 0) {
			String msg = "";
			int i = 0;
			do {
				msg += inputs[i].getSimpleName() + " and ";
				i++;
			} while (i < inputs.length);

			msg = msg.substring(0, msg.length() - 4);

			assertFalse(
					"Missing constructor with " + msg + " parameter"
							+ (inputs.length > 1 ? "s" : "") + " in "
							+ aClass.getName() + " class.",

					thrown);
		} else
			assertFalse(
					"Missing constructor with zero parameters in "
							+ aClass.getName() + " class.",

					thrown);

	}

	@Test(timeout = 1000)
	public void testConstructorInitializationInCooldownException()
			throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			testInCooldownExceptionConstructorExists();

			int remainingTurns = (int) ((Math.random() * 5) + 5);
			InCooldownException e = new InCooldownException(remainingTurns);

			Field remTurnsField;
			try {
				remTurnsField = InCooldownException.class
						.getDeclaredField("remainingTurns");
				remTurnsField.setAccessible(true);

				assertEquals(
						"In InCooldownException class, the variable remainingTurns is not set correctly in the constructor",
						remainingTurns, remTurnsField.get(e));

			} catch (NoSuchFieldException e1) {

				fail("InCooldownException class should have an instance variable called remainingTurns ");
			}

			String msg = e.getMessage();

			assertTrue(
					"The message of InCooldownException should indicate the remainingTurns",
					msg.contains(remainingTurns + ""));

		}
	}

	@Test(timeout = 1000)
	public void testConstructorInitializationInvalidTargetCellException() {

		for (int iteration = 0; iteration < 50; iteration++) {

			testInvalidTargetCellExceptionConstructorExists2();

			int random = (int) ((Math.random() * 5000) + 5000);
			String msg = "Exception test message " + random;
			InvalidTargetCellException e = new InvalidTargetCellException(msg);
			assertEquals(
					"The message of InvalidTargetCellException is not set correctly in the constructor",
					msg, e.getMessage());

		}
	}

	@Test(timeout = 1000)
	public void testConstructorInitializationNotEnoughIPException()
			throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			testNotEnoughIPExceptionConstructorExists();

			int requiredIP = (int) ((Math.random() * 20) + 20);
			int remainingIP = ((int) (Math.random() * 10) + 10);
			NotEnoughIPException e = new NotEnoughIPException(requiredIP,
					remainingIP);
			Field reqIPField;
			try {
				reqIPField = NotEnoughIPException.class
						.getDeclaredField("requiredIP");
				reqIPField.setAccessible(true);

				assertEquals(
						"In NotEnoughIPException class, the variable requiredIP is not set correctly in the constructor",
						requiredIP, reqIPField.get(e));

			} catch (NoSuchFieldException e1) {

				fail("NotEnoughIPException class should have an instance variable called requiredIP ");
			}

			Field remIPField;
			try {
				remIPField = NotEnoughIPException.class
						.getDeclaredField("remainingIP");
				remIPField.setAccessible(true);

				assertEquals(
						"In NotEnoughIPException class, the variable remainingIP is not set correctly in the constructor",
						remainingIP, remIPField.get(e));

			} catch (NoSuchFieldException e1) {

				fail("NotEnoughIPException class should have an instance variable called remainingIP ");
			}

			String msg = e.getMessage();

			assertTrue(
					"The message of NotEnoughIPException should indicate the requiredIP",
					msg.contains(requiredIP + ""));
			assertTrue(
					"The message of NotEnoughIPException should indicate the remainingIP",
					msg.contains(remainingIP + ""));

		}
	}

	@Test(timeout = 1000)
	public void testConstructorInitializationOutOfBordersException() {

		for (int iteration = 0; iteration < 50; iteration++) {

			testOutOfBordersExceptionConstructorExists2();

			int random = (int) ((Math.random() * 5000) + 5000);
			String msg = "Exception test message " + random;
			OutOfBordersException e = new OutOfBordersException(msg);
			assertEquals(
					"The message of OutOfBordersException is not set correctly in the constructor",
					msg, e.getMessage());

		}
	}

	@Test(timeout = 1000)
	public void testConstructorInitializationOutOfRangeException()
			throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			testOutOfRangeExceptionConstructorExists();

			int allowedRange = (int) ((Math.random() * 5) + 5);
			OutOfRangeException e = new OutOfRangeException(allowedRange);

			Field allowedRangeField;
			try {
				allowedRangeField = OutOfRangeException.class
						.getDeclaredField("allowedRange");
				allowedRangeField.setAccessible(true);

				assertEquals(
						"In OutOfRangeException class, the variable allowedRange is not set correctly in the constructor",
						allowedRange, allowedRangeField.get(e));

			} catch (NoSuchFieldException e1) {

				fail("OutOfRangeException class should have an instance variable called allowedRange ");
			}

			String msg = e.getMessage();

			assertTrue(
					"The message of OutOfRangeException should indicate the allowedRange",
					msg.contains(allowedRange + ""));

		}
	}

	@Test(timeout = 1000)
	public void testDamagingCupCellThirdTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();

			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			ThirdTask task3 = new ThirdTask(champs);

			task3.getChampions().clear();
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(r);
			task3.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task3, new Cell[10][10]);
			Cell[][] taskMap = task3.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int j = (int) (Math.random() * 4) + 1;
			int i = (int) (Math.random() * 8) + 1;

			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task3.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task3.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task3.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task3.getMap()[i][j - 1] = new CupCell();
			task3.getMap()[i - 1][j] = new ObstacleCell(new PhysicalObstacle(
					210));

			DamagingSpell dmgSpell = new DamagingSpell("s", 5, 10, 100);

			g.getSpells().clear();
			g.getSpells().add(dmgSpell);

			task3.setCurrentChamp(g);

			damageInvalidTarget(task3, dmgSpell, Direction.LEFT, new Point(i,
					j - 1));

			try {

				task3.castDamagingSpell(dmgSpell, Direction.FORWARD);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a damaging spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testDamagingPotionFirstTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();

			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			FirstTask task1 = new FirstTask(champs);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int i = (int) (Math.random() * 4) + 1;
			int j = (int) (Math.random() * 8) + 1;

			task1.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task1.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task1.getMap()[i - 1][j] = new CollectibleCell(new Potion("Potion",
					50));
			task1.getMap()[i][j + 1] = new ObstacleCell(
					new PhysicalObstacle(10));

			DamagingSpell dmgSpell = new DamagingSpell("dmg", 5, 3, 5);

			g.getSpells().clear();
			g.getSpells().add(dmgSpell);

			task1.setCurrentChamp(g);

			damageInvalidTarget(task1, dmgSpell, Direction.FORWARD, new Point(
					i - 1, j));

			try {

				task1.castDamagingSpell(dmgSpell, Direction.RIGHT);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a damaging spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testDamagingWallThirdTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();

			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			ThirdTask task3 = new ThirdTask(champs);

			task3.getChampions().clear();
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(r);
			task3.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task3, new Cell[10][10]);
			Cell[][] taskMap = task3.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int j = (int) (Math.random() * 4) + 1;
			int i = (int) (Math.random() * 8) + 1;

			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task3.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task3.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task3.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task3.getMap()[i][j - 1] = new WallCell();
			task3.getMap()[i - 1][j] = new ObstacleCell(new PhysicalObstacle(
					210));

			DamagingSpell dmgSpell = new DamagingSpell("s", 5, 10, 100);

			g.getSpells().clear();
			g.getSpells().add(dmgSpell);

			task3.setCurrentChamp(g);

			damageInvalidTarget(task3, dmgSpell, Direction.LEFT, new Point(i,
					j - 1));

			try {

				task3.castDamagingSpell(dmgSpell, Direction.FORWARD);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a damaging spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testGetterMethodExistsInClass(Class aClass, String methodName,
			Class<Integer> returnedType) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		String varName = methodName.substring(3).toLowerCase();
		assertTrue(
				"The " + varName + " instance variable in class "
						+ aClass.getName() + " is a READ variable.", found);

		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + " class.", m.getReturnType()
				.isAssignableFrom(returnedType));
	}

	@Test(timeout = 1000)
	public void testInCooldownExceptionConstructorExists() {
		testConstructorExists(InCooldownException.class, int.class);
	}

	@Test(timeout = 1000)
	public void testInCooldownExceptionExtendsException() {
		testClassIsSubClass(InCooldownException.class,
				NotEnoughResourcesException.class);
	}

	@Test(timeout = 1000)
	public void testInCooldownExceptionInstanceVariables1() throws Exception {
		testInstanceVariablesArePresent(InCooldownException.class,
				"remainingTurns", true);
		testInstanceVariablesArePrivate(InCooldownException.class,
				"remainingTurns");
	}

	@Test(timeout = 1000)
	public void testInCooldownExceptionRemTurnsGetter() throws Exception {
		testGetterMethodExistsInClass(InCooldownException.class,
				"getRemainingTurns", int.class);
	}

	@Test(timeout = 1000)
	public void testInCooldownExceptionRemTurnsSetter() throws Exception {
		testSetterMethodExistsInClass(InCooldownException.class,
				"setRemainingTurns", int.class, false);
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private void testInstanceVariablesArePresent(Class aClass, String varName,
			boolean implementedVar) throws Exception {
		Field f;
		boolean thrown = false;
		try {
			f = aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		if (implementedVar) {
			assertFalse("There should be " + varName
					+ " instance variable in class " + aClass.getName(), thrown);
		} else {
			assertTrue("There should not be " + varName
					+ " instance variable in class " + aClass.getName()
					+ ", it should be inherited from the super class", thrown);
		}
	}

	@SuppressWarnings("rawtypes")
	private void testInstanceVariablesArePrivate(Class aClass, String varName)
			throws Exception {
		Field f = aClass.getDeclaredField(varName);
		assertEquals(
				varName + " instance variable in class " + aClass.getName()
						+ " should not be accessed outside that class", 2,
				f.getModifiers());
	}

	@Test(timeout = 1000)
	public void testInvalidActionExceptionAbstract() {
		testClassIsAbstract(InvalidActionException.class);
	}

	@Test(timeout = 1000)
	public void testInvalidActionExceptionConstructorExists() {
		testConstructorExists(InvalidActionException.class);
	}

	@Test(timeout = 1000)
	public void testInvalidActionExceptionConstructorExists2() {
		testConstructorExists(InvalidActionException.class, String.class);
	}

	@Test(timeout = 1000)
	public void testInvalidActionExceptionExtendsException() {
		testClassIsSubClass(InvalidActionException.class, Exception.class);
	}

	@Test(timeout = 1000)
	public void testInvalidTargetCellExceptionConstructorExists() {
		testConstructorExists(InvalidTargetCellException.class);
	}

	@Test(timeout = 1000)
	public void testInvalidTargetCellExceptionConstructorExists2() {
		testConstructorExists(InvalidTargetCellException.class, String.class);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testMethodExistsInClass(Class aClass, String methodName,
			boolean implementedMethod, Class returnType, Class... inputTypes) {

		Method[] methods = aClass.getDeclaredMethods();

		if (implementedMethod) {
			assertTrue(
					"The " + methodName + " method in class "
							+ aClass.getName() + " should be implemented.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse(
					"The "
							+ methodName
							+ " method in class "
							+ aClass.getName()
							+ " should not be implemented, only inherited from super class.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputTypes);
		} catch (NoSuchMethodException e) {
			found = false;
		}
		String inputsList = "";
		for (Class inputType : inputTypes) {
			inputsList += inputType.getSimpleName() + ", ";
		}
		if (inputsList.equals(""))
			assertTrue(aClass.getName() + " class should have " + methodName
					+ " method that takes no parameters", found);
		else {
			if (inputsList.charAt(inputsList.length() - 1) == ' ')
				inputsList = inputsList.substring(0, inputsList.length() - 2);
			assertTrue(aClass.getName() + " class should have " + methodName
					+ " method that takes " + inputsList + " parameter(s)",
					found);
		}

		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + ".", m.getReturnType().equals(returnType));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testMethodExistsInClassOrSuperClass(Class aClass,
			String methodName, boolean implementedMethod, Class returnType,
			Class... inputTypes) {

		Method[] methods = aClass.getDeclaredMethods();
		Method[] superMethods = aClass.getSuperclass().getDeclaredMethods();
		if (implementedMethod) {

			assertTrue("The method " + methodName
					+ " should be implemented in " + aClass.getName()
					+ " class or " + aClass.getSuperclass().getName()
					+ " class", containsMethodName(methods, methodName)
					|| containsMethodName(superMethods, methodName));
		} else {
			assertFalse(
					"The "
							+ methodName
							+ " method in class "
							+ aClass.getName()
							+ " should not be implemented, only inherited from super class.",
					containsMethodName(methods, methodName)
							|| containsMethodName(superMethods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputTypes);
		} catch (NoSuchMethodException e) {
			try {
				m = aClass.getSuperclass().getDeclaredMethod(methodName,
						inputTypes);
			} catch (NoSuchMethodException e1) {
				found = false;
			}
		}

		String inputsList = "";
		for (Class inputType : inputTypes) {
			inputsList += inputType.getSimpleName() + ", ";
		}
		if (inputsList.equals(""))
			assertTrue("The method " + methodName
					+ " should be implemented in " + aClass.getName()
					+ " class or " + aClass.getSuperclass().getName()
					+ " class", found);
		else {
			if (inputsList.charAt(inputsList.length() - 1) == ' ')
				inputsList = inputsList.substring(0, inputsList.length() - 2);
			assertTrue(
					"The method " + methodName + " implemented in "
							+ aClass.getName() + " class or "
							+ aClass.getSuperclass().getName()
							+ " class, should take " + inputsList
							+ " parameter(s)", found);
		}

		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + ".", m.getReturnType().equals(returnType));

	}

	@Test(timeout = 1000)
	public void testMoveBackwardToChampionCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveBackward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[4][3] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 3));
		task.getMap()[5][3] = new ChampionCell(r);
		((Wizard) r).setLocation(new Point(5, 3));

		boolean exceptionThrown = false;
		FirstTask oldTask = (FirstTask) getTaskClone(task);
		try {
			task.moveBackward();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the First Task, when a champion tries to Move Backward to a Champion cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task.setCurrentChamp(h);

		Point newLocation = ((Wizard) task.getCurrentChamp()).getLocation();
		assertEquals(
				"In the First Task, when a champion tries to Move Backward to a Champion cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(4, 3), newLocation);

		assertEquals(
				"In the First Task, when a champion tries to Move Backward to a Champion cell, it is considered an invalid move and the target cell shouldn't change",
				ChampionCell.class, task.getMap()[5][3].getClass());
		testNoChange(oldTask, task, "InvalidTargetCellException");

		SecondTask task2 = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(h);
		task2.getMap()[4][3] = new ChampionCell(h);
		((Wizard) task2.getCurrentChamp()).setLocation(new Point(4, 3));
		task2.getMap()[5][3] = new ChampionCell(r);
		((Wizard) r).setLocation(new Point(5, 3));

		exceptionThrown = false;
		SecondTask oldTask2 = (SecondTask) getTaskClone(task2);
		try {
			task2.moveBackward();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Second Task, when a champion tries to Move Backward to a Champion cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task2.setCurrentChamp(h);

		newLocation = ((Wizard) task2.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Second Task, when a champion tries to Move Backward to a Champion cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(4, 3), newLocation);

		assertEquals(
				"In the Second Task, when a champion tries to Move Backward to a Champion cell, it is considered an invalid move and the target cell shouldn't change",
				ChampionCell.class, task2.getMap()[5][3].getClass());
		testNoChange(oldTask2, task2, "InvalidTargetCellException");

		ThirdTask task3 = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(h);
		task3.getMap()[4][3] = new ChampionCell(h);
		((Wizard) task3.getCurrentChamp()).setLocation(new Point(4, 3));
		task3.getMap()[5][3] = new ChampionCell(r);
		((Wizard) r).setLocation(new Point(5, 3));

		exceptionThrown = false;
		ThirdTask oldTask3 = (ThirdTask) getTaskClone(task3);
		try {
			task3.moveBackward();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Third Task, when a champion tries to Move Backward to a Champion cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task3.setCurrentChamp(h);

		newLocation = ((Wizard) task3.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Third Task, when a champion tries to Move Backward to a Champion cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(4, 3), newLocation);

		assertEquals(
				"In the Third Task, when a champion tries to Move Backward to a Champion cell, it is considered an invalid move and the target cell shouldn't change",
				ChampionCell.class, task3.getMap()[5][3].getClass());
		testNoChange(oldTask3, task3, "InvalidTargetCellException");

	}

	@Test(timeout = 1000)
	public void testMoveBackwardToIncorrectTreasureCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveBackward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		SecondTask task2 = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(r);
		task2.getMap()[7][2] = new ChampionCell(r);
		((Wizard) task2.getCurrentChamp()).setLocation(new Point(7, 2));
		task2.getMap()[8][2] = new TreasureCell(s);

		boolean exceptionThrown = false;
		SecondTask oldTask = (SecondTask) getTaskClone(task2);

		try {
			task2.moveBackward();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Second Task, when the current champion tries to Move Backward to a Treasure cell that doesn't have the current champion's treasure, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task2.setCurrentChamp(r);

		Point newLocation = ((Wizard) task2.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Second Task, when the current champion tries to Move Backward to a Treasure cell that doesn't have the current champion's treasure, it is considered an invalid move and the champion's location shouldn't change",
				new Point(7, 2), newLocation);

		assertEquals(
				"In the Second Task, when the current champion tries to Move Backward to a Treasure cell that doesn't have the current champion's treasure, it is considered an invalid move and the target cell shouldn't change",
				TreasureCell.class, task2.getMap()[8][2].getClass());

		assertEquals(
				"In the Second Task, when the current champion tries to Move Backward to a Treasure cell that doesn't have the current champion's treasure, it is considered an invalid move and the treasure shouldn't be moved",
				s, ((TreasureCell) task2.getMap()[8][2]).getOwner());
		testNoChange(oldTask, task2, "InvalidTargetCellException");

	}

	@Test(timeout = 1000)
	public void testMoveBackwardToWallCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveBackward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		ThirdTask task3 = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(h);
		task3.getMap()[4][3] = new ChampionCell(h);
		((Wizard) task3.getCurrentChamp()).setLocation(new Point(4, 3));

		task3.getMap()[5][3] = new WallCell();

		boolean exceptionThrown = false;
		ThirdTask oldTask3 = (ThirdTask) getTaskClone(task3);

		try {
			task3.moveBackward();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Third Task, when a champion tries to Move Backward to a Wall cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task3.setCurrentChamp(h);

		Point newLocation = ((Wizard) task3.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Third Task, when a champion tries to Move Backward to a Wall cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(4, 3), newLocation);

		assertEquals(
				"In the Third Task, when a champion tries to Move Backward to a Wall cell, it is considered an invalid move and the target cell shouldn't change",
				WallCell.class, task3.getMap()[5][3].getClass());
		testNoChange(oldTask3, task3, "InvalidTargetCellException");

	}

	@Test(timeout = 1000)
	public void testMoveForwardToChampionCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveForward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[4][3] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 3));
		task.getMap()[3][3] = new ChampionCell(r);
		((Wizard) r).setLocation(new Point(3, 3));
		FirstTask oldTask = (FirstTask) getTaskClone(task);
		boolean exceptionThrown = false;
		try {
			task.moveForward();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the First Task, when a champion tries to Move Forward to a Champion cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task.setCurrentChamp(h);

		Point newLocation = ((Wizard) task.getCurrentChamp()).getLocation();
		assertEquals(
				"In the First Task, when a champion tries to Move Forward to a Champion cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(4, 3), newLocation);

		assertEquals(
				"In the First Task, when a champion tries to Move Forward to a Champion cell, it is considered an invalid move and the target cell shouldn't change",
				ChampionCell.class, task.getMap()[3][3].getClass());
		testNoChange(oldTask, task, "InvalidTargetCellException");

		SecondTask task2 = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(h);
		task2.getMap()[4][3] = new ChampionCell(h);
		((Wizard) task2.getCurrentChamp()).setLocation(new Point(4, 3));
		task2.getMap()[3][3] = new ChampionCell(r);
		((Wizard) r).setLocation(new Point(3, 3));

		exceptionThrown = false;
		SecondTask oldTask2 = (SecondTask) getTaskClone(task2);
		try {
			task2.moveForward();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Second Task, when a champion tries to Move Forward to a Champion cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task2.setCurrentChamp(h);

		newLocation = ((Wizard) task2.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Second Task, when a champion tries to Move Forward to a Champion cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(4, 3), newLocation);

		assertEquals(
				"In the Second Task, when a champion tries to Move Forward to a Champion cell, it is considered an invalid move and the target cell shouldn't change",
				ChampionCell.class, task2.getMap()[3][3].getClass());
		testNoChange(oldTask2, task2, "InvalidTargetCellException");

		ThirdTask task3 = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(h);
		task3.getMap()[4][3] = new ChampionCell(h);
		((Wizard) task3.getCurrentChamp()).setLocation(new Point(4, 3));
		task3.getMap()[3][3] = new ChampionCell(r);
		((Wizard) r).setLocation(new Point(3, 3));

		exceptionThrown = false;
		ThirdTask oldTask3 = (ThirdTask) getTaskClone(task3);
		try {
			task3.moveForward();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Third Task, when a champion tries to Move Forward to a Champion cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task3.setCurrentChamp(h);

		newLocation = ((Wizard) task3.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Third Task, when a champion tries to Move Forward to a Champion cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(4, 3), newLocation);

		assertEquals(
				"In the Third Task, when a champion tries to Move Forward to a Champion cell, it is considered an invalid move and the target cell shouldn't change",
				ChampionCell.class, task3.getMap()[3][3].getClass());
		testNoChange(oldTask3, task3, "InvalidTargetCellException");
	}

	@Test(timeout = 1000)
	public void testMoveForwardToIncorrectTreasureCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveForward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		SecondTask task2 = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(h);
		task2.getMap()[4][3] = new ChampionCell(h);
		((Wizard) task2.getCurrentChamp()).setLocation(new Point(4, 3));
		task2.getMap()[3][3] = new TreasureCell(g);

		boolean exceptionThrown = false;
		SecondTask oldTask = (SecondTask) getTaskClone(task2);
		try {
			task2.moveForward();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Second Task, when the current champion tries to Move Forward to a Treasure cell that doesn't have the current champion's treasure, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task2.setCurrentChamp(h);

		Point newLocation = ((Wizard) task2.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Second Task, when the current champion tries to Move Forward to a Treasure cell that doesn't have the current champion's treasure, it is considered an invalid move and the champion's location shouldn't change",
				new Point(4, 3), newLocation);

		assertEquals(
				"In the Second Task, when the current champion tries to Move Forward to a Treasure cell that doesn't have the current champion's treasure, it is considered an invalid move and the target cell shouldn't change",
				TreasureCell.class, task2.getMap()[3][3].getClass());

		assertEquals(
				"In the Second Task, when the current champion tries to Move Forward to a Treasure cell that doesn't have the current champion's treasure, it is considered an invalid move and the treasure shouldn't be moved",
				g, ((TreasureCell) task2.getMap()[3][3]).getOwner());

		testNoChange(oldTask, task2, "InvalidTargetCellException");
	}

	@Test(timeout = 1000)
	public void testMoveLeftToObstacleCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveLeft", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[2][3] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(2, 3));
		PhysicalObstacle obstacle = new PhysicalObstacle(100);
		task.getMap()[2][2] = new ObstacleCell(obstacle);

		boolean exceptionThrown = false;
		FirstTask oldTask = (FirstTask) getTaskClone(task);
		try {
			task.moveLeft();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the First Task, when a champion tries to Move Left to a Obstacle cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task.setCurrentChamp(h);

		Point newLocation = ((Wizard) task.getCurrentChamp()).getLocation();
		assertEquals(
				"In the First Task, when a champion tries to Move Left to a Obstacle cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(2, 3), newLocation);

		assertEquals(
				"In the First Task, when a champion tries to Move Left to a Obstacle cell, it is considered an invalid move and the target cell shouldn't change",
				ObstacleCell.class, task.getMap()[2][2].getClass());

		assertEquals(
				"In the First Task, when a champion tries to Move Left to a Obstacle cell, it is considered an invalid move and the obstacle shouldn't be moved",
				obstacle, ((ObstacleCell) task.getMap()[2][2]).getObstacle());
		testNoChange(oldTask, task, "InvalidTargetCellException");

		SecondTask task2 = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(h);
		task2.getMap()[2][3] = new ChampionCell(h);
		((Wizard) task2.getCurrentChamp()).setLocation(new Point(2, 3));
		obstacle = new PhysicalObstacle(100);
		task2.getMap()[2][2] = new ObstacleCell(obstacle);

		exceptionThrown = false;
		SecondTask oldTask2 = (SecondTask) getTaskClone(task2);
		try {
			task2.moveLeft();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Second Task, when a champion tries to Move Left to a Obstacle cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task2.setCurrentChamp(h);

		newLocation = ((Wizard) task2.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Second Task, when a champion tries to Move Left to a Obstacle cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(2, 3), newLocation);

		assertEquals(
				"In the Second Task, when a champion tries to Move Left to a Obstacle cell, it is considered an invalid move and the target cell shouldn't change",
				ObstacleCell.class, task2.getMap()[2][2].getClass());

		assertEquals(
				"In the Second Task, when a champion tries to Move Left to a Obstacle cell, it is considered an invalid move and the obstacle shouldn't be moved",
				obstacle, ((ObstacleCell) task2.getMap()[2][2]).getObstacle());

		testNoChange(oldTask2, task2, "InvalidTargetCellException");

		ThirdTask task3 = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(h);
		task3.getMap()[2][3] = new ChampionCell(h);
		((Wizard) task3.getCurrentChamp()).setLocation(new Point(2, 3));
		obstacle = new PhysicalObstacle(100);
		task3.getMap()[2][2] = new ObstacleCell(obstacle);

		exceptionThrown = false;
		ThirdTask oldTask3 = (ThirdTask) getTaskClone(task3);
		try {
			task3.moveLeft();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Third Task, when a champion tries to Move Left to a Obstacle cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task3.setCurrentChamp(h);

		newLocation = ((Wizard) task3.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Third Task, when a champion tries to Move Left to a Obstacle cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(2, 3), newLocation);

		assertEquals(
				"In the Third Task, when a champion tries to Move Left to a Obstacle cell, it is considered an invalid move and the target cell shouldn't change",
				ObstacleCell.class, task3.getMap()[2][2].getClass());

		assertEquals(
				"In the Third Task, when a champion tries to Move Left to a Obstacle cell, it is considered an invalid move and the obstacle shouldn't be moved",
				obstacle, ((ObstacleCell) task3.getMap()[2][2]).getObstacle());

		testNoChange(oldTask3, task3, "InvalidTargetCellException");
	}

	@Test(timeout = 1000)
	public void testMoveLeftToWallCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveLeft", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		ThirdTask task3 = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(h);
		task3.getMap()[2][3] = new ChampionCell(h);
		((Wizard) task3.getCurrentChamp()).setLocation(new Point(2, 3));

		task3.getMap()[2][2] = new WallCell();

		boolean exceptionThrown = false;
		ThirdTask oldTask3 = (ThirdTask) getTaskClone(task3);

		try {
			task3.moveLeft();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Third Task, when a champion tries to Move Left to a Wall cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task3.setCurrentChamp(h);

		Point newLocation = ((Wizard) task3.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Third Task, when a champion tries to Move Left to a Wall cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(2, 3), newLocation);

		assertEquals(
				"In the Third Task, when a champion tries to Move Left to a Wall cell, it is considered an invalid move and the target cell shouldn't change",
				WallCell.class, task3.getMap()[2][2].getClass());
		testNoChange(oldTask3, task3, "InvalidTargetCellException");

	}

	@Test(timeout = 1000)
	public void testMoveOutOfBordersFromCornerSecondTask() throws Exception {

		GryffindorWizard c1 = new GryffindorWizard("1");
		HufflepuffWizard c2 = new HufflepuffWizard("2");
		RavenclawWizard c3 = new RavenclawWizard("3");
		SlytherinWizard c4 = new SlytherinWizard("4");
		ArrayList<Champion> tempChampions = new ArrayList<Champion>();
		tempChampions.add(c1);
		tempChampions.add(c2);
		tempChampions.add(c3);
		tempChampions.add(c4);
		SecondTask secondTask = new SecondTask(tempChampions);
		SecondTask oldTask = secondTask;
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (!(secondTask.getMap()[i][j] instanceof ChampionCell))
					secondTask.getMap()[i][j] = new EmptyCell();
		for (int i = 0; i < 12; i++) {

			oldTask = (SecondTask) getTaskClone(secondTask);
			try {

				switch (i) {
				case 0:
					secondTask.moveLeft();
					break;
				case 1:
					secondTask.moveBackward();
					break;
				case 2:
					secondTask.moveRight();
					break;
				case 3:
					secondTask.moveBackward();
					break;
				case 4:
					secondTask.moveRight();
					break;
				case 5:
					secondTask.moveForward();
					break;
				case 6:
					secondTask.moveRight();
					break;
				case 7:
					secondTask.moveForward();
					break;
				case 8:
					secondTask.moveBackward();
					break;
				case 9:
					secondTask.moveForward();
					break;
				case 10:
					secondTask.moveLeft();
					break;
				case 11:
					secondTask.moveRight();
					break;

				}
				if (((i + 1) % 3) != 0)
					fail("Moving outside the border should result in OutOfBorders exception");

			} catch (OutOfBordersException e) {
				if (((i + 1) % 3) == 0)
					fail("Moving within the border should not result in an "
							+ e.getClass().getSimpleName() + " exception");
				testNoChange(oldTask, secondTask, e.getClass().getSimpleName());

			}
		}
	}

	@Test(timeout = 1000)
	public void testMoveOutOfBordersFromEdgeFirstTask() throws Exception {

		GryffindorWizard c1 = new GryffindorWizard("1");
		HufflepuffWizard c2 = new HufflepuffWizard("2");
		RavenclawWizard c3 = new RavenclawWizard("3");
		SlytherinWizard c4 = new SlytherinWizard("4");
		ArrayList<Champion> tempChampions = new ArrayList<Champion>();
		tempChampions.add(c1);
		tempChampions.add(c2);
		tempChampions.add(c3);
		tempChampions.add(c4);
		FirstTask firstTask = new FirstTask(tempChampions);
		FirstTask oldTask = firstTask;
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				firstTask.getMap()[i][j] = new EmptyCell();

		firstTask.getMap()[0][4] = new ChampionCell(firstTask.getChampions()
				.get(0));
		((Wizard) firstTask.getChampions().get(0)).setLocation(new Point(0, 4));

		firstTask.getMap()[9][4] = new ChampionCell(firstTask.getChampions()
				.get(1));
		((Wizard) firstTask.getChampions().get(1)).setLocation(new Point(9, 4));

		firstTask.getMap()[5][0] = new ChampionCell(firstTask.getChampions()
				.get(2));
		((Wizard) firstTask.getChampions().get(2)).setLocation(new Point(5, 0));

		firstTask.getMap()[6][9] = new ChampionCell(firstTask.getChampions()
				.get(3));
		((Wizard) firstTask.getChampions().get(3)).setLocation(new Point(6, 9));

		for (int i = 0; i < 8; i++) {
			try {
				oldTask = (FirstTask) getTaskClone(firstTask);
				switch (i) {
				case 0:
					firstTask.moveForward();
					break;
				case 1:
					firstTask.moveRight();
					break;
				case 2:
					firstTask.moveBackward();
					break;
				case 3:
					firstTask.moveLeft();
					break;
				case 4:
					firstTask.moveLeft();
					break;
				case 5:
					firstTask.moveForward();
					break;
				case 6:
					firstTask.moveRight();
					break;
				case 7:
					firstTask.moveBackward();
					break;
				}
				if (((i + 1) % 2) != 0)
					fail("Moving outside the border should result in OutOfBorders exception");

			} catch (OutOfBordersException e) {
				if (((i + 1) % 2) == 0)
					fail("Moving within the border should not result in an "
							+ e.getClass().getSimpleName() + " exception");
				testNoChange(oldTask, firstTask, e.getClass().getSimpleName());

			}
		}
	}

	@Test(timeout = 1000)
	public void testMoveRightToObstacleCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveRight", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[2][3] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(2, 3));
		PhysicalObstacle obstacle = new PhysicalObstacle(100);
		task.getMap()[2][4] = new ObstacleCell(obstacle);

		boolean exceptionThrown = false;
		FirstTask oldTask = (FirstTask) getTaskClone(task);
		try {
			task.moveRight();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the First Task, when a champion tries to Move Right to a Obstacle cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task.setCurrentChamp(h);

		Point newLocation = ((Wizard) task.getCurrentChamp()).getLocation();
		assertEquals(
				"In the First Task, when a champion tries to Move Right to a Obstacle cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(2, 3), newLocation);

		assertEquals(
				"In the First Task, when a champion tries to Move Right to a Obstacle cell, it is considered an invalid move and the target cell shouldn't change",
				ObstacleCell.class, task.getMap()[2][4].getClass());

		assertEquals(
				"In the First Task, when a champion tries to Move Right to a Obstacle cell, it is considered an invalid move and the obstacle shouldn't be moved",
				obstacle, ((ObstacleCell) task.getMap()[2][4]).getObstacle());

		testNoChange(oldTask, task, "InvalidTargetCellException");

		SecondTask task2 = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(h);
		task2.getMap()[2][3] = new ChampionCell(h);
		((Wizard) task2.getCurrentChamp()).setLocation(new Point(2, 3));
		obstacle = new PhysicalObstacle(100);
		task2.getMap()[2][4] = new ObstacleCell(obstacle);

		exceptionThrown = false;
		SecondTask oldTask2 = (SecondTask) getTaskClone(task2);

		try {
			task2.moveRight();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Second Task, when a champion tries to Move Right to a Obstacle cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task2.setCurrentChamp(h);

		newLocation = ((Wizard) task2.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Second Task, when a champion tries to Move Right to a Obstacle cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(2, 3), newLocation);

		assertEquals(
				"In the Second Task, when a champion tries to Move Right to a Obstacle cell, it is considered an invalid move and the target cell shouldn't change",
				ObstacleCell.class, task2.getMap()[2][4].getClass());

		assertEquals(
				"In the Second Task, when a champion tries to Move Right to a Obstacle cell, it is considered an invalid move and the obstacle shouldn't be moved",
				obstacle, ((ObstacleCell) task2.getMap()[2][4]).getObstacle());

		testNoChange(oldTask2, task2, "InvalidTargetCellException");

		ThirdTask task3 = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(h);
		task3.getMap()[2][3] = new ChampionCell(h);
		((Wizard) task3.getCurrentChamp()).setLocation(new Point(2, 3));
		obstacle = new PhysicalObstacle(100);
		task3.getMap()[2][4] = new ObstacleCell(obstacle);

		exceptionThrown = false;
		ThirdTask oldTask3 = (ThirdTask) getTaskClone(task3);

		try {
			task3.moveRight();
		} catch (InvalidTargetCellException e1) {
			exceptionThrown = true;
		}
		assertTrue(
				"In the Third Task, when a champion tries to Move Right to a Obstacle cell, InvalidTargetCell Exception should be thrown",
				exceptionThrown);

		task3.setCurrentChamp(h);

		newLocation = ((Wizard) task3.getCurrentChamp()).getLocation();
		assertEquals(
				"In the Third Task, when a champion tries to Move Right to a Obstacle cell, it is considered an invalid move and the champion's location shouldn't change",
				new Point(2, 3), newLocation);

		assertEquals(
				"In the Third Task, when a champion tries to Move Right to a Obstacle cell, it is considered an invalid move and the target cell shouldn't change",
				ObstacleCell.class, task3.getMap()[2][4].getClass());

		assertEquals(
				"In the Third Task, when a champion tries to Move Right to a Obstacle cell, it is considered an invalid move and the obstacle shouldn't be moved",
				obstacle, ((ObstacleCell) task3.getMap()[2][4]).getObstacle());

		testNoChange(oldTask3, task3, "InvalidTargetCellException");

	}

	private void testNoChange(Cell before, Cell after, String exception) {

		assertEquals("The type of the cells should not be changed when an "
				+ exception + " exception is thrown", before.getClass(),
				after.getClass());

		if (before instanceof ObstacleCell) {
			assertEquals(
					"The hp of the obstacles should not be changed when an "
							+ exception + " exception is thrown",
					(((ObstacleCell) before).getObstacle()).getHp(),
					(((ObstacleCell) after).getObstacle()).getHp());
		}
	}

	private void testNoChange(Spell before, Spell after, String champion,
			String exception) {

		assertEquals("The type of the spells of the " + champion
				+ " should not be changed when an " + exception
				+ " exception is thrown", before.getClass(), after.getClass());

		assertEquals("The name of the spells of the " + champion
				+ " should not be changed when an " + exception
				+ " exception is thrown", before.getName(), after.getName());

		assertEquals("The cooldown of the spells of the " + champion
				+ " should not be changed when an " + exception
				+ " exception is thrown", before.getCoolDown(),
				after.getCoolDown());
	}

	private void testNoChange(Task before, Task after, String exception) {
		assertEquals("The traitActivated flag should not be changed when an "
				+ exception + " exception is thrown",
				before.isTraitActivated(), after.isTraitActivated());
		assertEquals("The allowed moves should not be changed when an "
				+ exception + " exception is thrown", before.getAllowedMoves(),
				after.getAllowedMoves());

		assertEquals(
				"The number of champions currently competing should not be changed when an "
						+ exception + " exception is thrown", before
						.getChampions().size(), after.getChampions().size());

		testNoChange((Wizard) before.getCurrentChamp(),
				(Wizard) after.getCurrentChamp(), "current champion", exception);

		testNoChange((Wizard) before.getChampions().get(0), (Wizard) after
				.getChampions().get(0), "current champion", exception);

		for (int i = 1; i < before.getChampions().size(); i++)
			testNoChange((Wizard) before.getChampions().get(i), (Wizard) after
					.getChampions().get(i), "champion", exception);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				testNoChange(before.getMap()[i][j], after.getMap()[i][j],
						exception);

	}

	private void testNoChange(Wizard before, Wizard after, String champion,
			String exception) {

		assertEquals("The type of the " + champion
				+ " should not be changed when an " + exception
				+ " exception is thrown", before.getClass(), after.getClass());

		assertEquals("The name of the " + champion
				+ " should not be changed when an " + exception
				+ " exception is thrown, check if you have shifted the turn",
				before.getName(), after.getName());

		assertEquals("The trait cooldown of the " + champion
				+ " should not be changed when an " + exception
				+ " exception is thrown", before.getTraitCooldown(),
				after.getTraitCooldown());

		assertEquals("The Hp of the " + champion
				+ " should not be changed when an " + exception
				+ " exception is thrown", before.getHp(), after.getHp());

		assertEquals("The ip of the " + champion
				+ " should not be changed when an " + exception
				+ " exception is thrown", before.getIp(), after.getIp());

		assertEquals("The row location of the " + champion
				+ " should not be changed when an" + exception
				+ " exception is throw", before.getLocation().x,
				after.getLocation().x);

		assertEquals("The column location of the " + champion
				+ " should not be changed when an exception is throw",
				before.getLocation().y, after.getLocation().y);

		assertEquals("The number of spells of the " + champion
				+ " should not be changed when an " + exception
				+ " exception is thrown", before.getSpells().size(), after
				.getSpells().size());

		for (int i = 0; i < before.getSpells().size(); i++)
			testNoChange(before.getSpells().get(i), after.getSpells().get(i),
					champion, exception);

		assertEquals("The number of collectibles in the inventory of the "
				+ champion + " should not be changed when an " + exception
				+ " exception is thrown", before.getInventory().size(), after
				.getInventory().size());

	}

	@Test(timeout = 1000)
	public void testNotEnoughIPExceptionConstructorExists() {
		testConstructorExists(NotEnoughIPException.class, int.class, int.class);
	}

	@Test(timeout = 1000)
	public void testNotEnoughIPExceptionExtendsException() {
		testClassIsSubClass(NotEnoughIPException.class,
				NotEnoughResourcesException.class);
	}

	@Test(timeout = 1000)
	public void testNotEnoughIPExceptionInstanceVariables1() throws Exception {
		testInstanceVariablesArePresent(NotEnoughIPException.class,
				"requiredIP", true);
		testInstanceVariablesArePrivate(NotEnoughIPException.class,
				"requiredIP");
	}

	@Test(timeout = 1000)
	public void testNotEnoughIPExceptionInstanceVariables2() throws Exception {
		testInstanceVariablesArePresent(NotEnoughIPException.class,
				"remainingIP", true);
		testInstanceVariablesArePrivate(NotEnoughIPException.class,
				"remainingIP");
	}

	@Test(timeout = 1000)
	public void testNotEnoughIPExceptionRemIPGetter() throws Exception {
		testGetterMethodExistsInClass(NotEnoughIPException.class,
				"getRemainingIP", int.class);
	}

	@Test(timeout = 1000)
	public void testNotEnoughIPExceptionRemIPSetter() throws Exception {
		testSetterMethodExistsInClass(NotEnoughIPException.class,
				"setRemainingIP", int.class, false);
	}

	@Test(timeout = 1000)
	public void testNotEnoughIPExceptionReqIPGetter() throws Exception {
		testGetterMethodExistsInClass(NotEnoughIPException.class,
				"getRequiredIP", int.class);
	}

	@Test(timeout = 1000)
	public void testNotEnoughIPExceptionReqIPSetter() throws Exception {
		testSetterMethodExistsInClass(NotEnoughIPException.class,
				"setRequiredIP", int.class, false);
	}

	@Test(timeout = 1000)
	public void testNotEnoughResourcesExceptionAbstract() {
		testClassIsAbstract(NotEnoughResourcesException.class);
	}

	@Test(timeout = 1000)
	public void testNotEnoughResourcesExceptionConstructorExists() {
		testConstructorExists(NotEnoughResourcesException.class);
	}

	@Test(timeout = 1000)
	public void testNotEnoughResourcesExceptionConstructorExists2() {
		testConstructorExists(NotEnoughResourcesException.class, String.class);
	}

	@Test(timeout = 1000)
	public void testNotEnoughResourcesExceptionExtendsException() {
		testClassIsSubClass(NotEnoughResourcesException.class,
				InvalidActionException.class);
	}

	@Test(timeout = 1000)
	public void testOutOfBordersExceptionConstructorExists() {
		testConstructorExists(OutOfBordersException.class);
	}

	@Test(timeout = 1000)
	public void testOutOfBordersExceptionConstructorExists2() {
		testConstructorExists(OutOfBordersException.class, String.class);
	}

	@Test(timeout = 1000)
	public void testOutOfBordersExceptionExtendsException() {
		testClassIsSubClass(OutOfBordersException.class,
				InvalidActionException.class);
	}

	@Test(timeout = 1000)
	public void testOutOfRangeExceptionAllowedRangeGetter() throws Exception {
		testGetterMethodExistsInClass(OutOfRangeException.class,
				"getAllowedRange", int.class);
	}

	@Test(timeout = 1000)
	public void testOutOfRangeExceptionAllowedRangeSetter() throws Exception {
		testSetterMethodExistsInClass(OutOfRangeException.class,
				"setAllowedRange", int.class, false);
	}

	@Test(timeout = 1000)
	public void testOutOfRangeExceptionConstructorExists() {
		testConstructorExists(OutOfRangeException.class, int.class);
	}

	// Helper Methods-------------------------

	@Test(timeout = 1000)
	public void testOutOfRangeExceptionExtendsException() {
		testClassIsSubClass(OutOfRangeException.class,
				InvalidActionException.class);
	}

	@Test(timeout = 1000)
	public void testOutOfRangeExceptionInstanceVariables1() throws Exception {
		testInstanceVariablesArePresent(OutOfRangeException.class,
				"allowedRange", true);
		testInstanceVariablesArePrivate(OutOfRangeException.class,
				"allowedRange");
	}

	@Test(timeout = 1000)
	public void testRelocatingChampionOutOfBordersThirdTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			ThirdTask task3 = new ThirdTask(champs);

			task3.getChampions().clear();
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(r);
			task3.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task3, new Cell[10][10]);
			Cell[][] taskMap = task3.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int range = (int) ((3 * Math.random()) + 3);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task3.setCurrentChamp(g);

			int i = (int) (Math.random() * 8) + 1;
			int j = (int) (Math.random() * inRange);
			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));

			task3.getMap()[i][j + 1] = new ChampionCell(r);

			r.setLocation(new Point(i, j + 1));

			relocateToOutOfBorders(task3, relSpell, Direction.RIGHT,
					Direction.LEFT, inRange, new Point(i, j + 1));

			task3.getMap()[i][j] = new EmptyCell();
			task3.getMap()[i][j + 1] = new EmptyCell();

			i = (int) (Math.random() * 8) + 1;
			j = (int) (Math.random() * inRange) + (10 - inRange);
			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));

			task3.getMap()[i][j - 1] = new ChampionCell(h);

			h.setLocation(new Point(i, j - 1));

			relocateToOutOfBorders(task3, relSpell, Direction.LEFT,
					Direction.RIGHT, inRange, new Point(i, j - 1));

			task3.getMap()[i][j] = new EmptyCell();
			task3.getMap()[i][j - 1] = new EmptyCell();

			j = (int) (Math.random() * 8) + 1;
			i = (int) (Math.random() * inRange);
			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));

			task3.getMap()[i][j + 1] = new ChampionCell(r);

			r.setLocation(new Point(i, j + 1));

			relocateToOutOfBorders(task3, relSpell, Direction.RIGHT,
					Direction.FORWARD, inRange, new Point(i, j + 1));

			task3.getMap()[i][j] = new EmptyCell();
			task3.getMap()[i][j + 1] = new EmptyCell();

			j = (int) (Math.random() * 8) + 1;
			i = (int) (Math.random() * inRange) + (10 - inRange);
			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));

			task3.getMap()[i][j + 1] = new ChampionCell(s);

			s.setLocation(new Point(i, j + 1));

			relocateToOutOfBorders(task3, relSpell, Direction.RIGHT,
					Direction.BACKWARD, inRange, new Point(i, j + 1));

			try {

				task3.castRelocatingSpell(relSpell, Direction.RIGHT,
						Direction.FORWARD, inRange);

			} catch (OutOfBordersException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);
			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingChampionOutOfRangeFirstTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			FirstTask task1 = new FirstTask(champs);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[0][3] = new ChampionCell(g);
			g.setLocation(new Point(0, 3));
			task1.getMap()[1][3] = new ChampionCell(h);
			h.setLocation(new Point(1, 3));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			int range = (int) ((3 * Math.random()) + 2);
			int outRange = (int) ((3 * Math.random()) + 5);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task1.setCurrentChamp(g);

			try {

				task1.castRelocatingSpell(relSpell, Direction.BACKWARD,
						Direction.BACKWARD, outRange);

				fail("If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown");

			} catch (OutOfRangeException e) {

				assertEquals(
						"If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown",
						OutOfRangeException.class, e.getClass());

			}

			try {

				task1.castRelocatingSpell(relSpell, Direction.BACKWARD,
						Direction.BACKWARD, inRange);

			} catch (OutOfRangeException e) {

				if (e instanceof OutOfRangeException)
					fail("If a champion tries to use a relocating spell with a range NOT greater than that of the spell, no exceptions should be thrown. Found: "
							+ e);

				fail("An exception was raised while trying to cast a spell. "
						+ e);

			}

			map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[8][9] = new ChampionCell(g);
			g.setLocation(new Point(8, 9));
			task1.getMap()[8][8] = new ChampionCell(h);
			h.setLocation(new Point(8, 8));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			range = (int) ((3 * Math.random()) + 2);
			outRange = (int) ((3 * Math.random()) + 5);
			inRange = (int) ((range - 2) * Math.random()) + 2;

			relSpell = new RelocatingSpell("RELOCATE", 5, 5, range);

			h.getSpells().add(relSpell);

			task1.setCurrentChamp(h);

			try {

				task1.castRelocatingSpell(relSpell, Direction.RIGHT,
						Direction.LEFT, outRange);

				fail("If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown");

			} catch (OutOfRangeException e) {

				assertEquals(
						"If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown",
						OutOfRangeException.class, e.getClass());

			}

			try {

				task1.castRelocatingSpell(relSpell, Direction.RIGHT,
						Direction.LEFT, inRange);

			} catch (OutOfRangeException e) {

				if (e instanceof OutOfRangeException)
					fail("If a champion tries to use a relocating spell with a range NOT greater than that of the spell, no exceptions should be thrown. Found: "
							+ e);

				fail("An exception was raised while trying to cast a spell. "
						+ e);

			}

			map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[1][1] = new ChampionCell(g);
			g.setLocation(new Point(1, 1));
			task1.getMap()[9][2] = new ChampionCell(h);
			h.setLocation(new Point(9, 2));
			task1.getMap()[9][3] = new ChampionCell(r);
			r.setLocation(new Point(9, 3));
			task1.getMap()[7][7] = new ChampionCell(s);
			s.setLocation(new Point(7, 7));

			range = (int) ((3 * Math.random()) + 2);
			outRange = (int) ((3 * Math.random()) + 5);
			inRange = (int) ((range - 2) * Math.random()) + 2;

			relSpell = new RelocatingSpell("RELOCATE", 5, 5, range);

			r.getSpells().add(relSpell);

			task1.setCurrentChamp(r);

			try {

				task1.castRelocatingSpell(relSpell, Direction.LEFT,
						Direction.FORWARD, outRange);

				fail("If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown");

			} catch (OutOfRangeException e) {

				assertEquals(
						"If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown",
						OutOfRangeException.class, e.getClass());

			}

			try {

				task1.castRelocatingSpell(relSpell, Direction.LEFT,
						Direction.FORWARD, inRange);

			} catch (OutOfRangeException e) {

				if (e instanceof OutOfRangeException)
					fail("If a champion tries to use a relocating spell with a range NOT greater than that of the spell, no exceptions should be thrown. Found: "
							+ e);

				fail("An exception was raised while trying to cast a spell. "
						+ e);
			}

			map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task1.getMap()[1][1] = new ChampionCell(g);
			g.setLocation(new Point(1, 1));
			task1.getMap()[5][5] = new ChampionCell(h);
			h.setLocation(new Point(5, 5));
			task1.getMap()[6][1] = new ChampionCell(r);
			r.setLocation(new Point(6, 1));
			task1.getMap()[7][1] = new ChampionCell(s);
			s.setLocation(new Point(7, 1));

			range = (int) ((3 * Math.random()) + 2);
			outRange = (int) ((3 * Math.random()) + 5);
			inRange = (int) ((range - 2) * Math.random()) + 2;

			relSpell = new RelocatingSpell("RELOCATE", 5, 5, range);

			s.getSpells().add(relSpell);

			task1.setCurrentChamp(s);

			try {

				task1.castRelocatingSpell(relSpell, Direction.FORWARD,
						Direction.RIGHT, outRange);

				fail("If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown");

			} catch (OutOfRangeException e) {

				assertEquals(
						"If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown",
						OutOfRangeException.class, e.getClass());

			}

			try {

				task1.castRelocatingSpell(relSpell, Direction.FORWARD,
						Direction.RIGHT, inRange);

			} catch (OutOfRangeException e) {

				if (e instanceof OutOfRangeException)
					fail("If a champion tries to use a relocating spell with a range NOT greater than that of the spell, no exceptions should be thrown. Found: "
							+ e);

				fail("An exception was raised while trying to cast a spell. "
						+ e);
			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingChampionToCollectibleSecondTask()
			throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			SecondTask task2 = new SecondTask(champs);

			task2.getChampions().clear();
			task2.getChampions().add(g);
			task2.getChampions().add(h);
			task2.getChampions().add(r);
			task2.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task2, new Cell[10][10]);
			Cell[][] taskMap = task2.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int i = (int) (Math.random() * 4) + 1;
			int j = (int) (Math.random() * 8) + 1;

			task2.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task2.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task2.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task2.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task2.getMap()[i - 1][j] = new ChampionCell(r);
			r.setLocation(new Point(i - 1, j));

			int range = (int) ((3 * Math.random()) + 3);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task2.setCurrentChamp(g);

			task2.getMap()[i + inRange][j] = new CollectibleCell(new Potion(
					"Pot", 45));

			relocateToInvalidDestination(task2, relSpell, Direction.FORWARD,
					Direction.BACKWARD, inRange, new Point(i - 1, j),
					new Point(i + inRange, j));

			try {

				task2.getMap()[i + inRange][j] = new EmptyCell();

				task2.castRelocatingSpell(relSpell, Direction.FORWARD,
						Direction.BACKWARD, inRange);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingChampionToObstacleFirstTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			FirstTask task1 = new FirstTask(champs);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int i = (int) (Math.random() * 8) + 1;
			int j = (int) (Math.random() * 4) + 5;

			task1.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task1.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task1.getMap()[i + 1][j] = new ChampionCell(h);

			h.setLocation(new Point(i + 1, j));

			int range = (int) ((3 * Math.random()) + 3);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task1.setCurrentChamp(g);

			task1.getMap()[i][j - inRange] = new ObstacleCell(
					new PhysicalObstacle(78));

			relocateToInvalidDestination(task1, relSpell, Direction.BACKWARD,
					Direction.LEFT, inRange, new Point(i + 1, j), new Point(i,
							j - inRange));

			try {

				task1.getMap()[i][j - inRange] = new EmptyCell();

				task1.castRelocatingSpell(relSpell, Direction.BACKWARD,
						Direction.LEFT, inRange);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingChampionToWallThirdTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			ThirdTask task3 = new ThirdTask(champs);

			task3.getChampions().clear();
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(r);
			task3.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task3, new Cell[10][10]);
			Cell[][] taskMap = task3.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int i = (int) (Math.random() * 4) + 1;
			int j = (int) (Math.random() * 8) + 1;

			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task3.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task3.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task3.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task3.getMap()[i - 1][j] = new ChampionCell(s);
			s.setLocation(new Point(i - 1, j));

			int range = (int) ((3 * Math.random()) + 3);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task3.setCurrentChamp(g);

			task3.getMap()[i + inRange][j] = new WallCell();

			relocateToInvalidDestination(task3, relSpell, Direction.FORWARD,
					Direction.BACKWARD, inRange, new Point(i - 1, j),
					new Point(i + inRange, j));

			try {

				task3.getMap()[i + inRange][j] = new EmptyCell();

				task3.castRelocatingSpell(relSpell, Direction.FORWARD,
						Direction.BACKWARD, inRange);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingEmptyCellThirdTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();

			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(r);
			champs.add(g);
			champs.add(h);
			champs.add(s);

			ThirdTask task3 = new ThirdTask(champs);

			task3.getChampions().clear();
			task3.getChampions().add(r);
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task3, new Cell[10][10]);
			Cell[][] taskMap = task3.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int j = (int) (Math.random() * 4) + 1;
			int i = (int) (Math.random() * 8) + 1;

			task3.getMap()[i][j] = new ChampionCell(r);
			r.setLocation(new Point(i, j));
			task3.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task3.getMap()[5][5] = new ChampionCell(g);
			g.setLocation(new Point(5, 5));
			task3.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task3.getMap()[i][j - 1] = new EmptyCell();
			task3.getMap()[i - 1][j] = new ObstacleCell(new PhysicalObstacle(
					210));

			int range = (int) ((3 * Math.random()) + 3);

			int inRange = (int) ((range - 2) * Math.random()) + 2;

			if (!(task3.getMap()[i][j + inRange] instanceof EmptyCell))
				task3.getMap()[i][j + inRange] = new EmptyCell();

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			r.getSpells().clear();
			r.getSpells().add(relSpell);

			task3.setCurrentChamp(r);

			relocateInvalidTarget(task3, relSpell, Direction.LEFT,
					Direction.RIGHT, inRange, new Point(i, j - 1), new Point(i,
							j + inRange));

			try {

				task3.castRelocatingSpell(relSpell, Direction.FORWARD,
						Direction.RIGHT, inRange);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingObstacleOutOfBordersThirdTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			ThirdTask task3 = new ThirdTask(champs);

			task3.getChampions().clear();
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(r);
			task3.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task3, new Cell[10][10]);
			Cell[][] taskMap = task3.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int range = (int) ((3 * Math.random()) + 3);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task3.setCurrentChamp(g);

			int i = (int) (Math.random() * 8) + 1;
			int j = (int) (Math.random() * inRange);
			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));

			task3.getMap()[i][j + 1] = new ObstacleCell(new PhysicalObstacle(
					100));

			relocateToOutOfBorders(task3, relSpell, Direction.RIGHT,
					Direction.LEFT, inRange, new Point(i, j + 1));

			task3.getMap()[i][j] = new EmptyCell();
			task3.getMap()[i][j + 1] = new EmptyCell();

			i = (int) (Math.random() * 8) + 1;
			j = (int) (Math.random() * inRange) + (10 - inRange);
			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));

			task3.getMap()[i][j - 1] = new ObstacleCell(new PhysicalObstacle(
					100));

			relocateToOutOfBorders(task3, relSpell, Direction.LEFT,
					Direction.RIGHT, inRange, new Point(i, j - 1));

			task3.getMap()[i][j] = new EmptyCell();
			task3.getMap()[i][j - 1] = new EmptyCell();

			j = (int) (Math.random() * 8) + 1;
			i = (int) (Math.random() * inRange);
			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));

			task3.getMap()[i][j + 1] = new ObstacleCell(new PhysicalObstacle(
					100));

			relocateToOutOfBorders(task3, relSpell, Direction.RIGHT,
					Direction.FORWARD, inRange, new Point(i, j + 1));

			task3.getMap()[i][j] = new EmptyCell();
			task3.getMap()[i][j + 1] = new EmptyCell();

			j = (int) (Math.random() * 8) + 1;
			i = (int) (Math.random() * inRange) + (10 - inRange);
			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));

			task3.getMap()[i][j + 1] = new ObstacleCell(new PhysicalObstacle(
					100));

			relocateToOutOfBorders(task3, relSpell, Direction.RIGHT,
					Direction.BACKWARD, inRange, new Point(i, j + 1));

			try {

				task3.castRelocatingSpell(relSpell, Direction.RIGHT,
						Direction.FORWARD, inRange);

			} catch (OutOfBordersException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);
			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingObstacleOutOfRangeSecondTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			SecondTask task2 = new SecondTask(champs);

			task2.getChampions().clear();
			task2.getChampions().add(g);
			task2.getChampions().add(h);
			task2.getChampions().add(r);
			task2.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task2, new Cell[10][10]);
			Cell[][] taskMap = task2.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task2.getMap()[2][3] = new ChampionCell(g);
			g.setLocation(new Point(2, 3));
			task2.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task2.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task2.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task2.getMap()[1][3] = new ObstacleCell(new Merperson(100, 10));

			int range = (int) ((3 * Math.random()) + 2);
			int outRange = (int) ((3 * Math.random()) + 5);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task2.setCurrentChamp(g);

			try {

				task2.castRelocatingSpell(relSpell, Direction.FORWARD,
						Direction.BACKWARD, outRange);

				fail("If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown");

			} catch (OutOfRangeException e) {

				assertEquals(
						"If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown",
						OutOfRangeException.class, e.getClass());

			}

			try {

				task2.castRelocatingSpell(relSpell, Direction.FORWARD,
						Direction.BACKWARD, inRange);

			} catch (OutOfRangeException e) {

				if (e instanceof OutOfRangeException)
					fail("If a champion tries to use a relocating spell with a range NOT greater than that of the spell, no exceptions should be thrown. Found: "
							+ e);

				fail("An exception was raised while trying to cast a spell. "
						+ e);
			}

			map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task2, new Cell[10][10]);
			taskMap = task2.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task2.getMap()[3][9] = new ChampionCell(g);
			g.setLocation(new Point(3, 9));
			task2.getMap()[8][8] = new ChampionCell(h);
			h.setLocation(new Point(8, 8));
			task2.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task2.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task2.getMap()[8][7] = new ObstacleCell(new Merperson(100, 10));

			range = (int) ((3 * Math.random()) + 2);
			outRange = (int) ((3 * Math.random()) + 5);
			inRange = (int) ((range - 2) * Math.random()) + 2;

			relSpell = new RelocatingSpell("RELOCATE", 5, 5, range);

			h.getSpells().add(relSpell);

			task2.setCurrentChamp(h);

			try {

				task2.castRelocatingSpell(relSpell, Direction.LEFT,
						Direction.LEFT, outRange);

				fail("If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown");

			} catch (OutOfRangeException e) {

				assertEquals(
						"If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown",
						OutOfRangeException.class, e.getClass());

			}

			try {

				task2.castRelocatingSpell(relSpell, Direction.LEFT,
						Direction.LEFT, inRange);

			} catch (OutOfRangeException e) {

				if (e instanceof OutOfRangeException)
					fail("If a champion tries to use a relocating spell with a range NOT greater than that of the spell, no exceptions should be thrown. Found: "
							+ e);

				fail("An exception was raised while trying to cast a spell. "
						+ e);
			}

			map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task2, new Cell[10][10]);
			taskMap = task2.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task2.getMap()[1][1] = new ChampionCell(g);
			g.setLocation(new Point(1, 1));
			task2.getMap()[0][0] = new ChampionCell(h);
			h.setLocation(new Point(0, 0));
			task2.getMap()[9][3] = new ChampionCell(r);
			r.setLocation(new Point(9, 3));
			task2.getMap()[7][7] = new ChampionCell(s);
			s.setLocation(new Point(7, 7));

			task2.getMap()[9][4] = new ObstacleCell(new Merperson(100, 10));

			range = (int) ((3 * Math.random()) + 2);
			outRange = (int) ((3 * Math.random()) + 5);
			inRange = (int) ((range - 2) * Math.random()) + 2;

			relSpell = new RelocatingSpell("RELOCATE", 5, 5, range);

			r.getSpells().add(relSpell);

			task2.setCurrentChamp(r);

			try {

				task2.castRelocatingSpell(relSpell, Direction.RIGHT,
						Direction.FORWARD, outRange);

				fail("If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown");

			} catch (OutOfRangeException e) {

				assertEquals(
						"If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown",
						OutOfRangeException.class, e.getClass());

			}

			try {

				task2.castRelocatingSpell(relSpell, Direction.RIGHT,
						Direction.FORWARD, inRange);

			} catch (OutOfRangeException e) {

				if (e instanceof OutOfRangeException)
					fail("If a champion tries to use a relocating spell with a range NOT greater than that of the spell, no exceptions should be thrown. Found: "
							+ e);

				fail("An exception was raised while trying to cast a spell. "
						+ e);
			}

			map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task2, new Cell[10][10]);
			taskMap = task2.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			task2.getMap()[1][1] = new ChampionCell(g);
			g.setLocation(new Point(1, 1));
			task2.getMap()[5][5] = new ChampionCell(h);
			h.setLocation(new Point(5, 5));
			task2.getMap()[6][6] = new ChampionCell(r);
			r.setLocation(new Point(6, 6));
			task2.getMap()[7][1] = new ChampionCell(s);
			s.setLocation(new Point(7, 1));

			task2.getMap()[8][1] = new ObstacleCell(new Merperson(100, 10));

			range = (int) ((3 * Math.random()) + 2);
			outRange = (int) ((3 * Math.random()) + 5);
			inRange = (int) ((range - 2) * Math.random()) + 2;

			relSpell = new RelocatingSpell("RELOCATE", 5, 5, range);

			s.getSpells().add(relSpell);

			task2.setCurrentChamp(s);

			try {

				task2.castRelocatingSpell(relSpell, Direction.BACKWARD,
						Direction.RIGHT, outRange);

				fail("If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown");

			} catch (OutOfRangeException e) {

				assertEquals(
						"If a champion tries to use a relocating spell with a range greater than that of the spell, an exception of type OutOfRangeException should be thrown",
						OutOfRangeException.class, e.getClass());

			}

			try {

				task2.castRelocatingSpell(relSpell, Direction.BACKWARD,
						Direction.RIGHT, inRange);

			} catch (OutOfRangeException e) {

				if (e instanceof OutOfRangeException)
					fail("If a champion tries to use a relocating spell with a range NOT greater than that of the spell, no exceptions should be thrown. Found: "
							+ e);

				fail("An exception was raised while trying to cast a spell. "
						+ e);
			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingObstacleToChampionSecondTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			SecondTask task2 = new SecondTask(champs);

			task2.getChampions().clear();
			task2.getChampions().add(g);
			task2.getChampions().add(h);
			task2.getChampions().add(r);
			task2.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task2, new Cell[10][10]);
			Cell[][] taskMap = task2.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int i = (int) (Math.random() * 4) + 1;
			int j = (int) (Math.random() * 8) + 1;

			task2.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task2.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task2.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task2.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task2.getMap()[i - 1][j] = new ObstacleCell(new Merperson(100, 90));

			int range = (int) ((3 * Math.random()) + 3);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task2.setCurrentChamp(g);

			task2.getMap()[i + inRange][j] = new ChampionCell(h);
			h.setLocation(new Point(i + inRange, j));

			relocateToInvalidDestination(task2, relSpell, Direction.FORWARD,
					Direction.BACKWARD, inRange, new Point(i - 1, j),
					new Point(i + inRange, j));

			try {

				task2.getMap()[i + inRange][j] = new EmptyCell();

				task2.castRelocatingSpell(relSpell, Direction.FORWARD,
						Direction.BACKWARD, inRange);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingObstacleToCollectibleThirdTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			ThirdTask task3 = new ThirdTask(champs);

			task3.getChampions().clear();
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(r);
			task3.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task3, new Cell[10][10]);
			Cell[][] taskMap = task3.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int i = (int) (Math.random() * 4) + 1;
			int j = (int) (Math.random() * 8) + 1;

			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task3.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task3.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task3.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task3.getMap()[i - 1][j] = new ObstacleCell(new PhysicalObstacle(
					100));

			int range = (int) ((3 * Math.random()) + 3);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task3.setCurrentChamp(g);

			task3.getMap()[i + inRange][j] = new CollectibleCell(new Potion(
					"Pot", 45));

			relocateToInvalidDestination(task3, relSpell, Direction.FORWARD,
					Direction.BACKWARD, inRange, new Point(i - 1, j),
					new Point(i + inRange, j));

			try {

				task3.getMap()[i + inRange][j] = new EmptyCell();

				task3.castRelocatingSpell(relSpell, Direction.FORWARD,
						Direction.BACKWARD, inRange);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingObstacleToCupThirdTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			ThirdTask task3 = new ThirdTask(champs);

			task3.getChampions().clear();
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(r);
			task3.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task3, new Cell[10][10]);
			Cell[][] taskMap = task3.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int i = (int) (Math.random() * 4) + 1;
			int j = (int) (Math.random() * 8) + 1;

			task3.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task3.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task3.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task3.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task3.getMap()[i - 1][j] = new ObstacleCell(new PhysicalObstacle(
					100));

			int range = (int) ((3 * Math.random()) + 3);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task3.setCurrentChamp(g);

			task3.getMap()[i + inRange][j] = new CupCell();

			relocateToInvalidDestination(task3, relSpell, Direction.FORWARD,
					Direction.BACKWARD, inRange, new Point(i - 1, j),
					new Point(i + inRange, j));

			try {

				task3.getMap()[i + inRange][j] = new EmptyCell();

				task3.castRelocatingSpell(relSpell, Direction.FORWARD,
						Direction.BACKWARD, inRange);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingObstacleToObstacleFirstTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			FirstTask task1 = new FirstTask(champs);

			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int i = (int) (Math.random() * 8) + 1;
			int j = (int) (Math.random() * 4) + 5;

			task1.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task1.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task1.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task1.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task1.getMap()[i + 1][j] = new ObstacleCell(new PhysicalObstacle(
					100));

			int range = (int) ((3 * Math.random()) + 3);
			int inRange = (int) ((range - 2) * Math.random()) + 2;

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().add(relSpell);

			task1.setCurrentChamp(g);

			task1.getMap()[i][j - inRange] = new ObstacleCell(
					new PhysicalObstacle(78));

			relocateToInvalidDestination(task1, relSpell, Direction.BACKWARD,
					Direction.LEFT, inRange, new Point(i + 1, j), new Point(i,
							j - inRange));

			try {

				task1.getMap()[i][j - inRange] = new EmptyCell();

				task1.castRelocatingSpell(relSpell, Direction.BACKWARD,
						Direction.LEFT, inRange);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingPotionSecondTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();

			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			SecondTask task2 = new SecondTask(champs);

			task2.getChampions().clear();
			task2.getChampions().add(g);
			task2.getChampions().add(h);
			task2.getChampions().add(r);
			task2.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task2, new Cell[10][10]);
			Cell[][] taskMap = task2.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int i = (int) (Math.random() * 4) + 5;
			int j = (int) (Math.random() * 8) + 1;

			task2.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task2.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task2.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task2.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task2.getMap()[i + 1][j] = new CollectibleCell(new Potion("Potion",
					50));
			task2.getMap()[i][j - 1] = new ObstacleCell(new Merperson(10, 60));

			int range = (int) ((3 * Math.random()) + 3);

			int inRange = (int) ((range - 2) * Math.random()) + 2;

			if (!(task2.getMap()[i - inRange][j] instanceof EmptyCell))
				task2.getMap()[i - inRange][j] = new EmptyCell();

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().clear();
			g.getSpells().add(relSpell);

			task2.setCurrentChamp(g);

			relocateInvalidTarget(task2, relSpell, Direction.BACKWARD,
					Direction.FORWARD, inRange, new Point(i + 1, j), new Point(
							i - inRange, j));

			try {

				task2.castRelocatingSpell(relSpell, Direction.LEFT,
						Direction.FORWARD, inRange);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@Test(timeout = 1000)
	public void testRelocatingTreasureCellSecondTask() throws Exception {

		for (int iteration = 0; iteration < 50; iteration++) {

			ArrayList<Champion> champs = new ArrayList<>();

			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			champs.add(g);
			champs.add(h);
			champs.add(r);
			champs.add(s);

			SecondTask task2 = new SecondTask(champs);

			task2.getChampions().clear();
			task2.getChampions().add(g);
			task2.getChampions().add(h);
			task2.getChampions().add(r);
			task2.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task2, new Cell[10][10]);
			Cell[][] taskMap = task2.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			int j = (int) (Math.random() * 4) + 5;
			int i = (int) (Math.random() * 8) + 1;

			task2.getMap()[i][j] = new ChampionCell(g);
			g.setLocation(new Point(i, j));
			task2.getMap()[3][1] = new ChampionCell(h);
			h.setLocation(new Point(3, 1));
			task2.getMap()[5][5] = new ChampionCell(r);
			r.setLocation(new Point(5, 5));
			task2.getMap()[6][6] = new ChampionCell(s);
			s.setLocation(new Point(6, 6));

			task2.getMap()[i - 1][j] = new TreasureCell(s);
			task2.getMap()[i + 1][j] = new TreasureCell(h);
			task2.getMap()[i][j - 1] = new TreasureCell(r);
			task2.getMap()[i][j + 1] = new TreasureCell(g);

			int range = (int) ((3 * Math.random()) + 3);

			int inRange = (int) ((range - 2) * Math.random()) + 2;

			if (!(task2.getMap()[i][j - inRange] instanceof EmptyCell))
				task2.getMap()[i][j - inRange] = new EmptyCell();

			RelocatingSpell relSpell = new RelocatingSpell("RELOCATE", 5, 5,
					range);

			g.getSpells().clear();
			g.getSpells().add(relSpell);

			task2.setCurrentChamp(g);

			relocateInvalidTarget(task2, relSpell, Direction.BACKWARD,
					Direction.LEFT, inRange, new Point(i + 1, j), new Point(i,
							j - inRange));

			relocateInvalidTarget(task2, relSpell, Direction.FORWARD,
					Direction.LEFT, inRange, new Point(i - 1, j), new Point(i,
							j - inRange));

			relocateInvalidTarget(task2, relSpell, Direction.LEFT,
					Direction.LEFT, inRange, new Point(i, j - 1), new Point(i,
							j - inRange));

			relocateInvalidTarget(task2, relSpell, Direction.RIGHT,
					Direction.LEFT, inRange, new Point(i, j + 1), new Point(i,
							j - inRange));

			try {

				task2.getMap()[i][j - 1] = new ObstacleCell(new Merperson(10,
						60));

				task2.castRelocatingSpell(relSpell, Direction.LEFT,
						Direction.LEFT, inRange);

			} catch (InvalidTargetCellException e) {

				fail("If a champion tries to use a relocating spell with valid parameters, no exceptions should be thrown. Found: "
						+ e);

			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testSetterMethodExistsInClass(Class aClass, String methodName,
			Class<Integer> inputType, boolean writeVariable) {

		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3).toLowerCase();
		if (writeVariable) {
			assertTrue("The " + varName + " instance variable in class "
					+ aClass.getName() + " is a WRITE variable.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse("The " + varName + " instance variable in class "
					+ aClass.getName() + " is a READ ONLY variable.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		assertTrue(aClass.getName() + " class should have " + methodName
				+ " method that takes one " + inputType.getSimpleName()
				+ " parameter", found);

		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + ".", m.getReturnType().equals(Void.TYPE));

	}

	@Test(timeout = 1000)
	public void testSlytherinMovesToCellOutOfBordersOnTraitActivatedSecondTask()
			throws Exception {

		testMethodExistsInClass(Task.class, "castDamagingSpell", true,
				Void.TYPE, DamagingSpell.class, Direction.class);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		SecondTask task = new SecondTask(e);
		SlytherinMovesToCellOutOfBordersOnTraitActivatedHelper(task, s);

	}

	@Test(timeout = 1000)
	public void testSlytherinMovesToCupCellOnTraitActivated() throws Exception {

		testMethodExistsInClassOrSuperClass(SlytherinWizard.class, "useTrait",
				true, Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		ThirdTask task3 = new ThirdTask(e);
		task3.getChampions().add(s);
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);

		task3.getMap()[3][3] = new CupCell();
		Point cupLocation = new Point(3, 3);

		onSlythMovesToInvalidCellsHelper(task3, new Point(cupLocation.x + 2,
				cupLocation.y), cupLocation, Direction.FORWARD,
				task3.getMap()[3][3], s, null);

		onSlythMovesToInvalidCellsHelper(task3, new Point(cupLocation.x - 2,
				cupLocation.y), cupLocation, Direction.BACKWARD,
				task3.getMap()[3][3], s, null);

		onSlythMovesToInvalidCellsHelper(task3, new Point(cupLocation.x,
				cupLocation.y + 2), cupLocation, Direction.LEFT,
				task3.getMap()[3][3], s, null);

		onSlythMovesToInvalidCellsHelper(task3, new Point(cupLocation.x,
				cupLocation.y - 2), cupLocation, Direction.RIGHT,
				task3.getMap()[3][3], s, null);

	}

	@Test(timeout = 1000)
	public void testSlytherinMovesToTreasureCellOnTraitActivated()
			throws Exception {

		testMethodExistsInClassOrSuperClass(SlytherinWizard.class, "useTrait",
				true, Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		SecondTask task2 = new SecondTask(e);
		task2.getChampions().add(s);
		task2.getChampions().add(g);
		task2.getChampions().add(h);
		task2.getChampions().add(r);

		task2.getMap()[3][3] = new TreasureCell(s);
		onSlythMovesToInvalidCellsHelper(task2, new Point(5, 3),
				new Point(3, 3), Direction.FORWARD, task2.getMap()[3][3], s,
				null);
		task2.getMap()[3][3] = new EmptyCell();

		task2.getMap()[7][3] = new TreasureCell(s);
		onSlythMovesToInvalidCellsHelper(task2, new Point(5, 3),
				new Point(7, 3), Direction.BACKWARD, task2.getMap()[7][3], s,
				null);
		task2.getMap()[7][3] = new EmptyCell();

		task2.getMap()[5][1] = new TreasureCell(s);
		onSlythMovesToInvalidCellsHelper(task2, new Point(5, 3),
				new Point(5, 1), Direction.LEFT, task2.getMap()[5][1], s, null);
		task2.getMap()[5][1] = new EmptyCell();

		task2.getMap()[5][5] = new TreasureCell(s);
		onSlythMovesToInvalidCellsHelper(task2, new Point(5, 3),
				new Point(5, 5), Direction.RIGHT, task2.getMap()[5][5], s, null);

	}

	@Test(timeout = 1000)
	public void testSlytherinMovesToWallCellOnTraitActivated() throws Exception {

		testMethodExistsInClassOrSuperClass(SlytherinWizard.class, "useTrait",
				true, Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		ThirdTask task3 = new ThirdTask(e);
		task3.getChampions().add(s);
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);

		task3.getMap()[3][3] = new WallCell();
		onSlythMovesToInvalidCellsHelper(task3, new Point(5, 3),
				new Point(3, 3), Direction.FORWARD, task3.getMap()[3][3], s,
				null);

		task3.getMap()[7][3] = new WallCell();
		onSlythMovesToInvalidCellsHelper(task3, new Point(5, 3),
				new Point(7, 3), Direction.BACKWARD, task3.getMap()[7][3], s,
				null);

		task3.getMap()[5][1] = new WallCell();
		onSlythMovesToInvalidCellsHelper(task3, new Point(5, 3),
				new Point(5, 1), Direction.LEFT, task3.getMap()[5][1], s, null);

		task3.getMap()[5][5] = new WallCell();
		onSlythMovesToInvalidCellsHelper(task3, new Point(5, 3),
				new Point(5, 5), Direction.RIGHT, task3.getMap()[5][5], s, null);

	}

	@Test(timeout = 1000)
	public void testUseOnGryffindorTraitWhileInCoolDownSecondTask()
			throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		SecondTask task1 = new SecondTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task1, new Cell[10][10]);
		Cell[][] taskMap = task1.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		task1.getMap()[2][2] = new ChampionCell(g);
		g.setLocation(new Point(2, 2));
		task1.getMap()[2][3] = new ChampionCell(h);
		h.setLocation(new Point(2, 3));
		task1.getMap()[2][4] = new ChampionCell(r);
		r.setLocation(new Point(2, 4));
		task1.getMap()[6][6] = new ChampionCell(s);
		s.setLocation(new Point(6, 6));

		task1.setCurrentChamp(g);

		try {
			g.useTrait();
		} catch (InCooldownException exp) {
			fail("If a champion tries to use his/her trait while he/she is NOT in coolDown, an exception should NOT be thrown");
		}

		task1.moveBackward();
		task1.moveLeft();

		task1.setCurrentChamp(h);
		task1.moveForward();

		task1.setCurrentChamp(r);
		task1.moveRight();

		task1.setCurrentChamp(s);
		task1.moveLeft();

		task1.setCurrentChamp(g);

		SecondTask oldTask = (SecondTask) getTaskClone(task1);

		try {
			g.useTrait();
			fail("If a champion tries to use his/her trait while he/she is still in coolDown, an exception of type InCooldownException should be thrown");
		} catch (InCooldownException exp) {

			testNoChange(oldTask, task1, exp.getClass().getSimpleName());

			assertEquals(
					"If a champion tries to use his/her trait while he/she is still in coolDown, an exception of type InCooldownException should be thrown with the correct \"remainingTurns\" ",
					g.getTraitCooldown(),
					((InCooldownException) exp).getRemainingTurns());

		}

	}

	@Test(timeout = 1000)
	public void testUseOnHufflepuffWhileInCoolDownFirstTask() throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(h);
		task1.getChampions().add(g);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task1, new Cell[10][10]);
		Cell[][] taskMap = task1.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		task1.getMap()[2][2] = new ChampionCell(h);
		h.setLocation(new Point(2, 2));
		task1.getMap()[2][3] = new ChampionCell(g);
		g.setLocation(new Point(2, 3));
		task1.getMap()[2][4] = new ChampionCell(r);
		r.setLocation(new Point(2, 4));
		task1.getMap()[6][6] = new ChampionCell(s);
		s.setLocation(new Point(6, 6));

		task1.setCurrentChamp(h);

		try {
			h.useTrait();
		} catch (InCooldownException exp) {
			fail("If a champion tries to use his/her trait while he/she is NOT in coolDown, an exception should NOT be thrown");
		}

		task1.moveLeft();

		task1.setCurrentChamp(g);
		task1.moveForward();

		task1.setCurrentChamp(r);
		task1.moveRight();

		task1.setCurrentChamp(s);
		task1.moveLeft();

		task1.setCurrentChamp(h);

		FirstTask oldTask = (FirstTask) getTaskClone(task1);

		try {
			h.useTrait();
			fail("If a champion tries to use his/her trait while he/she is still in coolDown, an exception of type InCooldownException should be thrown");
		} catch (InCooldownException exp) {

			testNoChange(oldTask, task1, exp.getClass().getSimpleName());

			assertEquals(
					"If a champion tries to use his/her trait while he/she is still in coolDown, an exception of type InCooldownException should be thrown with the correct \"remainingTurns\" ",
					h.getTraitCooldown(),
					((InCooldownException) exp).getRemainingTurns());

		}

	}

	@Test(timeout = 1000)
	public void testUseOnHufflepuffWhileInCoolDownThirdTask() throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		ThirdTask task1 = new ThirdTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(h);
		task1.getChampions().add(g);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task1, new Cell[10][10]);
		Cell[][] taskMap = task1.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		task1.getMap()[2][2] = new ChampionCell(h);
		h.setLocation(new Point(2, 2));
		task1.getMap()[2][3] = new ChampionCell(g);
		g.setLocation(new Point(2, 3));
		task1.getMap()[2][4] = new ChampionCell(r);
		r.setLocation(new Point(2, 4));
		task1.getMap()[6][6] = new ChampionCell(s);
		s.setLocation(new Point(6, 6));

		task1.setCurrentChamp(h);

		try {
			h.useTrait();
		} catch (InCooldownException exp) {
			fail("If a champion tries to use his/her trait while he/she is NOT in coolDown, an exception should NOT be thrown");
		}

		task1.moveLeft();

		task1.setCurrentChamp(g);
		task1.moveForward();

		task1.setCurrentChamp(r);
		task1.moveRight();

		task1.setCurrentChamp(s);
		task1.moveLeft();

		task1.setCurrentChamp(h);

		try {
			h.useTrait();
		} catch (InCooldownException exp) {

			if (exp instanceof InCooldownException)
				fail("In ThirdTask, The Hufflepuff trait should be always activated and should never throw an InCooldownException. "
						+ exp);
		}
	}

	@Test(timeout = 1000)
	public void testUseOnRavenclawWhileInCoolDownThirdTask() throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		ThirdTask task1 = new ThirdTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(r);
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(s);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task1, new Cell[10][10]);
		Cell[][] taskMap = task1.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		task1.getMap()[2][2] = new ChampionCell(r);
		r.setLocation(new Point(2, 2));
		task1.getMap()[2][3] = new ChampionCell(g);
		g.setLocation(new Point(2, 3));
		task1.getMap()[2][4] = new ChampionCell(h);
		h.setLocation(new Point(2, 4));
		task1.getMap()[6][6] = new ChampionCell(s);
		s.setLocation(new Point(6, 6));

		task1.setCurrentChamp(r);

		try {
			r.useTrait();
		} catch (InCooldownException exp) {
			fail("If a champion tries to use his/her trait while he/she is NOT in coolDown, an exception should NOT be thrown");
		}

		task1.moveLeft();

		task1.setCurrentChamp(g);
		task1.moveForward();

		task1.setCurrentChamp(h);
		task1.moveRight();

		task1.setCurrentChamp(s);
		task1.moveLeft();

		task1.setCurrentChamp(r);

		ThirdTask oldTask = (ThirdTask) getTaskClone(task1);

		try {
			r.useTrait();
			fail("If a champion tries to use his/her trait while he/she is still in coolDown, an exception of type InCooldownException should be thrown");
		} catch (InCooldownException exp) {

			testNoChange(oldTask, task1, exp.getClass().getSimpleName());

			assertEquals(
					"If a champion tries to use his/her trait while he/she is still in coolDown, an exception of type InCooldownException should be thrown with the correct \"remainingTurns\" ",
					r.getTraitCooldown(),
					((InCooldownException) exp).getRemainingTurns());

		}

	}

	@Test(timeout = 1000)
	public void testUseOnSlytherinWhileInCoolDownThirdTask() throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		ThirdTask task3 = new ThirdTask(e);
		task3.getChampions().clear();
		task3.getChampions().add(s);
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task3, new Cell[10][10]);
		Cell[][] taskMap = task3.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		task3.getMap()[2][2] = new ChampionCell(s);
		s.setLocation(new Point(2, 2));
		task3.getMap()[2][3] = new ChampionCell(g);
		g.setLocation(new Point(2, 3));
		task3.getMap()[2][4] = new ChampionCell(h);
		h.setLocation(new Point(2, 4));
		task3.getMap()[6][6] = new ChampionCell(r);
		r.setLocation(new Point(6, 6));

		task3.setCurrentChamp(s);

		try {
			s.setTraitDirection(Direction.FORWARD);
			s.useTrait();
		} catch (InCooldownException exp) {
			fail("If a champion tries to use his/her trait while he/she is NOT in coolDown, an exception should NOT be thrown");
		}

		task3.setCurrentChamp(g);
		task3.moveForward();

		task3.setCurrentChamp(h);
		task3.moveRight();

		task3.setCurrentChamp(r);
		task3.moveLeft();

		task3.setCurrentChamp(s);

		ThirdTask oldTask = (ThirdTask) getTaskClone(task3);

		try {
			s.setTraitDirection(Direction.BACKWARD);
			s.useTrait();
			fail("If a champion tries to use his/her trait while he/she is still in coolDown, an exception of type InCooldownException should be thrown");
		} catch (InCooldownException exp) {

			testNoChange(oldTask, task3, exp.getClass().getSimpleName());

			assertEquals(
					"If a champion tries to use his/her trait while he/she is still in coolDown, an exception of type InCooldownException should be thrown with the correct \"remainingTurns\" ",
					s.getTraitCooldown(),
					((InCooldownException) exp).getRemainingTurns());

		}

	}

}
