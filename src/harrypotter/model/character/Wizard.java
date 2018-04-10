package harrypotter.model.character;

import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.Spell;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Wizard {

	private WizardListener listener;
	private String name;
	private int defaultHp;
	private int defaultIp;
	private int hp;
	private int ip;
	private ArrayList<Spell> spells;
	private ArrayList<Collectible> inventory;
	private ArrayList<Champion> enemies;
	
	

	private Point location;
	private int traitCooldown;

	public int i;
	
	public void setSpells(ArrayList<Spell> spells) {
		this.spells = spells;
	}

	public Wizard(String name) {

		this.name = name;
		spells = new ArrayList<Spell>();
		inventory = new ArrayList<Collectible>();
		enemies = new ArrayList<Champion>();

	}

	public Wizard(String name, int dhp, int dip) {

		this.name = name;
		this.defaultHp = dhp;
		this.hp = dhp;
		this.defaultIp = dip;
		this.ip = dip;
		spells = new ArrayList<Spell>();
		inventory = new ArrayList<Collectible>();
		enemies = new ArrayList<Champion>();

	}
	public ArrayList<Champion> getEnemies() {
		return enemies;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDefaultHp() {
		return defaultHp;
	}

	public void setDefaultHp(int defaultHp) {
		this.defaultHp = defaultHp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getIp() {
		return ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public ArrayList<Collectible> getInventory() {
		return inventory;
	}

	public int getTraitCooldown() {
		return traitCooldown;
	}

	public void setTraitCooldown(int traitCooldown) {
		this.traitCooldown = traitCooldown;
	}

	public int getDefaultIp() {
		return defaultIp;
	}

	public void setDefaultIp(int defaultIp) {
		this.defaultIp = defaultIp;
	}

	public WizardListener getListener() {
		return listener;
	}

	public void setListener(WizardListener listener) {
		this.listener = listener;
	}

}
