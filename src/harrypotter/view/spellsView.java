package harrypotter.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
//import harrypotter.model.tournament.Tournament;

@SuppressWarnings("serial")
public class spellsView extends JFrame implements ActionListener {

	public int champ;
	public ArrayList<Spell>myspells= new ArrayList<>();
	public JButton sel;
	public ArrayList<JButton> btns;
	public ArrayList<Spell> spells;
	public JLabel inf;
	private JButton add;
	private JButton done;

	public chooseView cv;
	public spellsView(ArrayList<Spell> spells, chooseView c, int cham) {

		champ = cham;
		cv = c;
		this.spells= spells;
		btns = new ArrayList<>();
		JLabel main = new JLabel(new ImageIcon("bckspells.png"));
		setSize(1067, 600);
		add(main);
		setLocationRelativeTo(null);
		JTextField dmg = new JTextField("Damaging spells");
		dmg.setEditable(false);
		dmg.setFont(dmg.getFont().deriveFont(30.0f));
		dmg.setBounds(0, 0, 250, 50);
		main.add(dmg);

		JLabel dmgG = new JLabel();
		dmgG.setLayout(new GridLayout(2, 6));
		dmgG.setBounds(0, 55, 500, 100);
		for (int i = 0; i < 11; i++) {
			JButton btn = new JButton(spells.get(i).getName());
			btn.addActionListener(this);
			btn.setBorderPainted(false);
			dmgG.add(btn);
			btns.add(btn);
		}
		main.add(dmgG);

		JTextField hel = new JTextField("Healing spells");
		hel.setEditable(false);
		hel.setFont(hel.getFont().deriveFont(30.0f));
		hel.setBounds(0, 160, 250, 50);
		main.add(hel);

		JLabel helG = new JLabel();
		helG.setLayout(new GridLayout(1, 6));
		helG.setBounds(0, 215, 500, 50);
		for (int i = 11; i < 17; i++) {
			JButton btn = new JButton(spells.get(i).getName());
			btn.addActionListener(this);
			btn.setBorderPainted(false);
			helG.add(btn);
			btns.add(btn);
		}
		main.add(helG);

		JTextField rel = new JTextField("Relocating spells");
		rel.setEditable(false);
		rel.setFont(rel.getFont().deriveFont(30.0f));
		rel.setBounds(0, 270, 250, 50);
		main.add(rel);

		JLabel relG = new JLabel();
		relG.setLayout(new GridLayout(1, 6));
		relG.setBounds(0, 325, 500, 50);
		for (int i = 17; i < 21; i++) {
			JButton btn = new JButton(spells.get(i).getName());
			btn.addActionListener(this);
			btn.setBorderPainted(false);
			relG.add(btn);
			btns.add(btn);
		}
		main.add(relG);
		
		inf = new JLabel(); 
		inf.setLayout(new GridLayout(4,1));
		inf.setBounds(700,150,300,100);
		main.add(inf);
		
		
		add = new JButton("Add");
		add.setFont(add.getFont().deriveFont(30.0f));
		add.addActionListener(this);
		add.setBounds(700,325 ,100, 65);
		add.setEnabled(false);
		add.setBorderPainted(false);
		main.add(add);
		
		done = new JButton("Next");
		done.setFont(done.getFont().deriveFont(30.0f));
		done.addActionListener(this);
		done.setBounds(700,390 , 100, 65);
		done.setEnabled(false);
		done.setBorderPainted(false);
		main.add(done);
		
		
		
		setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		//Tournament t = new Tournament();
		///new spellsView(t.getSpells());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton j= (JButton)e.getSource();
		if(btns.contains(j)){
			inf.removeAll();
			
			sel= j;
			Spell s= spells.get(btns.indexOf(sel));
		//	inf.setLayout(new GridLayout(1,4));
			inf.add(new JLabel("Name: "+s.getName()));
			inf.add(new JLabel("Cost: " + s.getCost() + ""));
			inf.add(new JLabel("Cooldown: "+ s.getDefaultCooldown() ));
			if(s instanceof DamagingSpell)
				inf.add(new JLabel("Damage amount: "+ ((DamagingSpell)s).getDamageAmount()));
			else
				if(s instanceof HealingSpell)
					inf.add(new JLabel("Heal amount: "+ ((HealingSpell)s).getHealingAmount()));
				else
					if(s instanceof RelocatingSpell)
						inf.add(new JLabel("Range: "+ ((RelocatingSpell)s).getRange()));

			repaint();
			revalidate();
			if(myspells.size()!=3)
			add.setEnabled(true);
		}
		else
			if(j==add){
				Spell s= spells.get(btns.indexOf(sel));
				Spell c=null;
				if(s instanceof DamagingSpell){
					DamagingSpell ds= (DamagingSpell)s;
					c= new DamagingSpell(ds.getName(), ds.getCost(), ds.getDefaultCooldown(), ds.getDamageAmount());
				}
				else
					if(s instanceof HealingSpell){
						HealingSpell ds= (HealingSpell)s;
						c= new HealingSpell(ds.getName(), ds.getCost(), ds.getDefaultCooldown(), ds.getHealingAmount());
					}
					else
						{
							RelocatingSpell ds= (RelocatingSpell)s;
							c= new RelocatingSpell(ds.getName(), ds.getCost(), ds.getDefaultCooldown(), ds.getRange());
						}
				myspells.add(c);
				add.setEnabled(false);
				sel.setEnabled(false);
				if(myspells.size()==3)done.setEnabled(true);
				inf.removeAll();
			}
			else
			{
				cv.addSpells(myspells, champ);
				this.dispose();
			}

	}

}
