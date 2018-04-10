package harrypotter.model.tournament;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Tournament implements TaskListener {

	private ArrayList<Champion> champions;
	private ArrayList<Spell> spells;
	private FirstTask firstTask;
	private SecondTask secondTask;
	private ThirdTask thirdTask;

	public Tournament() throws IOException {

		champions = new ArrayList<Champion>();
		spells = new ArrayList<Spell>();
		loadSpells("Spells.csv");

	}

	public ArrayList<Champion> getChampions() {
		return champions;
	}

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public FirstTask getFirstTask() {
		return firstTask;
	}

	public SecondTask getSecondTask() {
		return secondTask;
	}

	public ThirdTask getThirdTask() {
		return thirdTask;
	}

	public void addChampion(Champion c) {
		champions.add(c);
	}

	private void loadSpells(String filePath) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {

			String[] content = line.split(",");
			switch (content[0]) {

			case "DMG":
				spells.add(new DamagingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			case "HEL":
				spells.add(new HealingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			case "REL":
				spells.add(new RelocatingSpell(content[1], Integer
						.parseInt(content[2]), Integer.parseInt(content[4]),
						Integer.parseInt(content[3])));
				break;

			}

			line = br.readLine();

		}

		br.close();

	}

	public void beginTournament() throws IOException {

		firstTask = new FirstTask(champions);
		firstTask.setListener(this);

	}

	public void onFinishingFirstTask(ArrayList<Champion> winners)
			throws IOException {

		if (!winners.isEmpty()) {
			String wi = "The Winners are : ";
			for(Champion c :winners)
				wi += ((Wizard)c).getName() + " // ";
			wi += " To the second task we go! ";
			JOptionPane.showMessageDialog(null, wi);
			
			secondTask = new SecondTask(winners);
			secondTask.setListener(this);
			secondTask.cont =firstTask.cont;
			secondTask.cont.task = secondTask;
			secondTask.cont.loadMap();
			
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Game Over!");
			firstTask.cont.view.dispose();
		}
		
	}

	public void onFinishingSecondTask(ArrayList<Champion> winners)
			throws IOException {

		if (!winners.isEmpty()) {
			String wi = "The Winners are : ";
			for(Champion c :winners)
				wi += ((Wizard)c).getName() + " // ";
			wi += " To the final task we go! ";
			JOptionPane.showMessageDialog(null, wi);
			
			thirdTask= new ThirdTask(winners);
			thirdTask.setListener(this);
			thirdTask.cont =secondTask.cont;
			thirdTask.cont.task =thirdTask;
			thirdTask.cont.loadMap();
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "All Champions are dead! Game Over!");
			secondTask.cont.view.dispose();
		}

	}

	public void onFinishingThirdTask(Champion winner) {
		
		JOptionPane.showMessageDialog(null, "The Winner is " + ((Wizard)winner).getName());
		JFrame cup = new JFrame();
		cup.pack();
		cup.setTitle("Cup");
		cup.setSize(500, 500);
		cup.setLocationRelativeTo(null);
		ImageIcon bb = new ImageIcon("cup.png");
		bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(cup.getWidth()-10,cup.getHeight() -10, java.awt.Image.SCALE_SMOOTH));
		cup.add(new JLabel(bb));
		cup.setVisible(true);
		Timer t = new Timer(5000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
		});
		
		t.setRepeats(false);
		t.start();

	}
}
