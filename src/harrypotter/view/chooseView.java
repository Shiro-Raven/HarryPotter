package harrypotter.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

import harrypotter.controller.TaskController;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.Tournament;

@SuppressWarnings("serial")
public class chooseView extends JFrame implements ActionListener {
	public JLabel main;
	public String[] name = new String[4];
	public Wizard[] champs = new Wizard[4];
	@SuppressWarnings("unchecked")
	public ArrayList<Spell>[] spells = new ArrayList[4];
	public ArrayList<JButton> Bspells = new ArrayList<>();
	public ArrayList<JTextField> Tname = new ArrayList<>();
	public ArrayList<JButton> house = new ArrayList<>();
	public Tournament tour;
	private JButton next;
	
	public chooseView() throws IOException {
		tour = new Tournament();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setExtendedState(MAXIMIZED_BOTH);
		main = new JLabel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(main);
		JLabel up = new JLabel();
		up.setLayout(new GridLayout(1, 4));
		up.setBounds(0, 0, this.getWidth(), this.getHeight() - 100);
		up.setBackground(Color.RED);
		// up.setSize(this.getWidth(),this.getHeight()-80);
		next = new JButton("Next");
		next.setEnabled(false);
		next.setBounds(this.getWidth() / 2 - 30, this.getHeight() - 110, 100, 50);
		next.addActionListener(this);
		for (int i = 0; i < 4; i++) {
			JLabel champ1 = new JLabel();
			champ1.setLayout(new GridLayout(7, 1));
			champ1.setSize(up.getWidth() / 4, up.getHeight());
			JTextField txt1 = new JTextField("Select Champion Number #" + (i + 1));
			txt1.setEditable(false);
			champ1.add(txt1);
			JTextField txt = new JTextField("Enter name");
			txt.addActionListener(this);
			txt.setEditable(true);
			champ1.add(txt);
			Tname.add(txt);
			for (int j = 0; j < 4; j++) {
				ImageIcon bb = new ImageIcon(j + "h.png");
				Image newb = ((Image) bb.getImage()).getScaledInstance(champ1.getWidth(), champ1.getHeight() / 7,
						java.awt.Image.SCALE_SMOOTH);
				bb = new ImageIcon(newb);
				JButton h = new JButton(bb);
				switch(j%4){
				case 0: h.setToolTipText("Max Hp: 900 Max Ip:500");break;
				case 1: h.setToolTipText("Max Hp: 850 Max Ip:550");break;
				case 2: h.setToolTipText("Max Hp: 1000 Max Ip:450");break;
				case 3: h.setToolTipText("Max Hp: 750 Max Ip:700");break;
				}
				h.setBorderPainted(false);
				h.setFocusPainted(false);
				h.setContentAreaFilled(false);
				h.addActionListener(this);
				champ1.add(h);
				house.add(h);
			}
			JButton sp = new JButton(new ImageIcon("Spells1.png"));
			sp.setContentAreaFilled(false);
			sp.setBorderPainted(false);
			sp.setFocusable(false);
			sp.addActionListener(this);
			champ1.add(sp);
			Bspells.add(sp);
			up.add(champ1);
		}
		
		

		main.add(up);
		main.add(next);
		
		ImageIcon icon = new ImageIcon("bckm.png");
		icon = new ImageIcon(((Image) icon.getImage()).getScaledInstance(this.getWidth(),this.getHeight(), java.awt.Image.SCALE_SMOOTH));
		main.setIcon(icon);
		setVisible(true);
	}

	public void nextView() {
	//	ArrayList<Champion> champ = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			Wizard w = champs[i];
			w.setName(name[i]);
			w.setSpells(spells[i]);
			w.i = i;
			tour.addChampion((Champion) w);
		}
		
		chooseView c = this;
		
		Timer t = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.dispose();
			}
		});
		t.setRepeats(false);
		t.start();
		
		
		new TaskController(tour);
		
	}

	public void check() {
		boolean f = true;
		for (String s : name)
			if (s == null)
				f = false;
		for (ArrayList<Spell> s : spells)
			if (s == null)
				f = false;
		for (Wizard w : champs)
			if (w == null)
				f = false;
		if (f)
			next.setEnabled(true);

	}

	public void addSpells(ArrayList<Spell> s, int i) {
		spells[i] = s;
		check();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o instanceof JTextField) {
			JTextField t = (JTextField) o;
			name[Tname.indexOf(t)] = t.getText();
			check();
		} else {
			JButton j = (JButton) o;
			if (house.contains(j)) {
				int i = house.indexOf(j) / 4;
				int k = house.indexOf(j) % 4;
				switch (k) {
				case 0:
					champs[i] = new GryffindorWizard("");
					break;
				case 1:
					champs[i] = new HufflepuffWizard("");
					break;
				case 2:
					champs[i] = new RavenclawWizard("");
					break;
				case 3:
					champs[i] = new SlytherinWizard("");
					break;
				}
				check();
				for(int z = 0; z<4; z++)
					if(z != k)
						house.get(i*4 + z).setEnabled(false);
			} else if (Bspells.contains(j)) {
				name[Bspells.indexOf(j)] = Tname.get(Bspells.indexOf(j)).getText();
				check();
				((JButton)j).setIcon(new ImageIcon("Spells2.png"));
				new spellsView(tour.getSpells(), this, Bspells.indexOf(j));
			} else
				nextView();
		}
	}

	public static void main(String[] args) throws IOException {
		new chooseView();
	}

}
