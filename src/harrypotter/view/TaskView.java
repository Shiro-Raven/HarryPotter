package harrypotter.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import harrypotter.controller.TaskController;
import harrypotter.model.character.Wizard;
import harrypotter.model.tournament.Task;

//TODO el house of each champ


@SuppressWarnings("serial")
public class TaskView extends JFrame {
	TaskController cont;
	public ArrayList<JProgressBar> hp = new ArrayList<>();
	public ArrayList<JProgressBar> ip = new ArrayList<>();
	public JButton trait;
	public ArrayList<JButton> spells= new ArrayList<>();
	public JButton potion;
	public JButton up;
	public JButton down;
	public JButton left;
	public JButton right;
	public JButton exit;
	public JLabel infoPic;
	public JLabel infoTxt;
	public ArrayList<JLabel>mapcells = new ArrayList<>();
	public JLabel center;
	public TaskView(Task task, TaskController cont){
		this.cont=cont;
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setUndecorated(true);
//		getContentPane().setBackground(Color.red);
		setExtendedState(MAXIMIZED_BOTH);
		JLabel main = new JLabel();
		
		JLabel west = new JLabel();
		west.setLayout(new GridLayout(4,1));
		west.setSize((this.getWidth()-this.getHeight())/2, this.getHeight());
		west.setBounds(0,0,(this.getWidth()-this.getHeight())/2, this.getHeight());
		for(int i= 0; i< 4; i++){
			JLabel champ =new JLabel();
			champ.setSize(west.getWidth(), west.getHeight()/4);
			champ.setLayout(new GridLayout(1,2));
			JLabel pic = new JLabel();
			pic.setSize(champ.getWidth()/2,champ.getHeight());
			champ.add(pic);
			ImageIcon bb = new ImageIcon(i + "c1.png");
			bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(pic.getWidth(),pic.getHeight(), java.awt.Image.SCALE_SMOOTH));
			pic.setIcon(bb);
			
			JLabel house = new JLabel();
			bb = new ImageIcon(i + "h1.png");
			bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(west.getWidth()/4,west.getWidth()/5, java.awt.Image.SCALE_SMOOTH));
			
			//TODO pics
			house.setIcon(bb);
			JProgressBar hp1 = new JProgressBar();
			hp1.setMaximum(((Wizard)cont.champs.get(i)).getDefaultHp());
			hp1.setValue(hp1.getMaximum());
			hp1.setForeground(Color.BLUE);
			hp1.setStringPainted(true);
			JProgressBar ip1 = new JProgressBar();
			ip1.setMaximum(((Wizard)cont.champs.get(i)).getDefaultIp());
			ip1.setValue(ip1.getMaximum());
			ip1.setStringPainted(true);
			ip1.setForeground(Color.GREEN);
			JLabel name = new JLabel(((Wizard)cont.champs.get(i)).getName());
			name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
			JLabel bars = new JLabel();
			bars.setLayout(new GridLayout(4,1));
			bars.add(name);
			
			JLabel hs = new JLabel();
			hs.setLayout(new GridLayout(1, 3));
			hs.add(new JLabel(""));
			hs.add(house);
			hs.add(new JLabel("        "));
			bars.add(hs);
			
			bars.add(hp1);
			bars.add(ip1);
			hp.add(hp1);
			ip.add(ip1);
			//champ.add(photos);
			champ.setBackground(new Color(0,0,0,0));
			champ.add(bars);
			west.add(champ);
		}
		main.add(west);
		center = new JLabel();
		center.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		center.setSize(this.getHeight(),this.getHeight());
		ImageIcon mai = new ImageIcon("firstback.png");
		mai = new ImageIcon(((Image) mai.getImage()).getScaledInstance(this.getHeight(),this.getHeight(), java.awt.Image.SCALE_SMOOTH));
		center.setIcon(mai);
		center.setLayout(new GridLayout(10,10));
		for(int i =0 ; i<100; i++){
			mapcells.add(new JLabel());
			center.add(mapcells.get(i));
		}
		main.add(center);
		center.setBackground(Color.yellow);
		center.setBounds((this.getWidth()-this.getHeight())/2,0, getHeight(), getHeight());
		JLabel east = new JLabel();
		east.setLayout(new GridLayout(3,1));
		east.setSize((this.getWidth()-this.getHeight())/2, this.getHeight());
		JLabel eastup=  new JLabel();
		eastup.setLayout(new GridLayout(3,1));
		trait = new JButton(new ImageIcon("Trait.png"));
		trait.setBackground(Color.BLACK);
		trait.setOpaque(false);
		trait.addActionListener(cont);
		trait.setBorderPainted(false);
		eastup.add(trait);
		JLabel sp = new JLabel();
		sp.setLayout(new GridLayout(1,4));
		ImageIcon bb = new ImageIcon("Spells3.png");
		bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(east.getWidth()/4,east.getHeight()/6, java.awt.Image.SCALE_SMOOTH));
		sp.add(new JLabel(bb));
		for(int i =0 ; i< 3; i++){
			JButton bspell =new JButton();
			bspell.setBackground(Color.white);
//			bspell.setOpaque(false);
			bspell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
			bspell.setBorderPainted(true);
			bspell.addActionListener(cont);
			sp.add(bspell);
			spells.add(bspell);
		}
		eastup.add(sp);
		ImageIcon pt = new ImageIcon("potion.png");
		pt = new ImageIcon(((Image) pt.getImage()).getScaledInstance(east.getWidth(),east.getHeight()/10, java.awt.Image.SCALE_SMOOTH));
		
		potion = new JButton(pt);
		potion.setBackground(Color.black);
		potion.setOpaque(false);
		potion.addActionListener(cont);
		potion.setBorderPainted(false);
	//	potion.setContentAreaFilled(false);
		eastup.add(potion);
		eastup.setBackground(new Color(0,0,0,0));
		east.add(eastup);
		JLabel eastm = new JLabel();
		eastm.setLayout(new GridLayout(3,3));
		ImageIcon aa = new ImageIcon("up.png");
		aa = new ImageIcon(((Image) aa.getImage()).getScaledInstance(east.getWidth()/3,east.getWidth()/3, java.awt.Image.SCALE_SMOOTH));
		up = new JButton(aa);
		up.setBackground(Color.black);
		up.setOpaque(false);
		up.addActionListener(cont);
	//	up.setContentAreaFilled(false);
		up.setBorderPainted(false);
		JLabel lr = new JLabel();
		lr.setLayout(new GridLayout(1,2));
		ImageIcon cc = new ImageIcon("left.png");
		cc = new ImageIcon(((Image) cc.getImage()).getScaledInstance(east.getWidth()/3,east.getWidth()/3, java.awt.Image.SCALE_SMOOTH));
		left = new JButton(cc);
		left.setBackground(Color.black);
		left.setOpaque(false);
		left.addActionListener(cont);
	//	left.setContentAreaFilled(false);
		left.setBorderPainted(false);
		ImageIcon zz = new ImageIcon("right.png");
		zz = new ImageIcon(((Image) zz.getImage()).getScaledInstance(east.getWidth()/3,east.getWidth()/3, java.awt.Image.SCALE_SMOOTH));
		right = new JButton(zz);
		right.setBackground(Color.cyan);
		right.setOpaque(false);
		right.addActionListener(cont);
	//	right.setContentAreaFilled(false);
		right.setBorderPainted(false);
		//reset left right here
		ImageIcon dd = new ImageIcon("down.png");
		dd = new ImageIcon(((Image) dd.getImage()).getScaledInstance(east.getWidth()/3,east.getWidth()/3, java.awt.Image.SCALE_SMOOTH));
		down = new JButton(dd);
		down.setBackground(Color.BLACK);
		down.setOpaque(false);
		down.addActionListener(cont);
	//	down.setContentAreaFilled(false);
		down.setBorderPainted(false);
		eastm.add(new JLabel(""));
		eastm.add(up);
		eastm.add(new JLabel(""));
		eastm.add(left);
		eastm.add(new JLabel(""));
		eastm.add(right);
	//	eastm.add(lr);
		eastm.add(new JLabel(""));
		eastm.add(down);
		eastm.add(new JLabel(""));
		eastm.setBackground(new Color(0,0,0,0));
		east.add(eastm);
		JLabel Info = new JLabel();
		infoPic = new JLabel();
		infoPic.setBounds(10, 0, 250,250);
		infoPic.setOpaque(false);
		infoPic.setBackground(Color.CYAN);
		Info.add(infoPic);
		infoTxt = new JLabel();
		infoTxt.setOpaque(false);
		infoTxt.setLayout(new GridLayout(7, 1));
		infoTxt.setBounds(255, 0, 300,250);
		infoTxt.setBackground(Color.GREEN);
		Info.add(infoTxt);
		exit = new JButton("EXIT");
		exit.addActionListener(cont);
		exit.setBounds(270, 270, 100, 50);
		Info.add(exit);
		Info.setOpaque(false);
		east.add(Info);
		east.setOpaque(false);
		east.setForeground(Color.RED);
		east.setBounds(west.getWidth()+center.getWidth(), 0, west.getWidth(), this.getHeight());
		west.setBackground(Color.black);
		main.add(east);
		Info.setBackground(new Color(0,0,0,0));
		add(main);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		eastm.setOpaque(false);
		
		main.setOpaque(false);
	}
	

}
