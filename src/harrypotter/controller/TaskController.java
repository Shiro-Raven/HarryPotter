package harrypotter.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.Task;
import harrypotter.model.tournament.ThirdTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.Obstacle;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.WallCell;
import harrypotter.view.TaskView;
import harrypotter.view.battleView;



 
public class TaskController implements ActionListener, WindowListener{

	public Tournament tour;
	public TaskView view;
	public Task task;
	public ArrayList<Champion>champs;
	
	public battleView fight;
	public Champion currentEnemy;
	public ArrayList<Champion> enemies;
	public Champion temp;
	
	public TaskController(Tournament t){
		tour = t;
		champs=t.getChampions();
		view = new TaskView(task,this);
		try {
			tour.beginTournament();
		} catch (IOException e) {
			e.printStackTrace();
		}
		task = tour.getFirstTask();
		task.cont = this;
		loadMap();
	}
	
	
	public void loadMap()
	{
		if(task instanceof SecondTask )
		{
		ImageIcon mai = new ImageIcon("secondback.png");
		mai = new ImageIcon(((Image) mai.getImage()).getScaledInstance(view.getHeight(),view.getHeight(), java.awt.Image.SCALE_SMOOTH));
		view.center.setIcon(mai);
		}
		if(task instanceof ThirdTask)
		{
			ImageIcon mai = new ImageIcon("thirdback.png");
			mai = new ImageIcon(((Image) mai.getImage()).getScaledInstance(view.getHeight(),view.getHeight(), java.awt.Image.SCALE_SMOOTH));
			view.center.setIcon(mai);
		}
		for(int i =0 ; i< 10; i++){
			for(int j=0; j<10; j++){
				view.mapcells.get(j + 10*i).setToolTipText(null);
				Cell c = task.getMap()[i][j];
				ImageIcon bb;
				JLabel lb = view.mapcells.get(j+i*10);
				if(c instanceof ChampionCell){
					lb.setToolTipText(((Wizard)((ChampionCell)c).getChamp()).getName());
					bb = new ImageIcon(((Wizard)(((ChampionCell)c).getChamp())).i+"c.png");
					bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(view.mapcells.get(0).getWidth(),view.mapcells.get(0).getHeight(), java.awt.Image.SCALE_SMOOTH));
					
				}
				else
					if(c instanceof ObstacleCell  ){
						Obstacle ob =( (ObstacleCell)c).getObstacle();
						if (ob instanceof PhysicalObstacle)
						{

							lb.setToolTipText("HP: "+ ob.getHp()+" hp");
							bb = new ImageIcon("obstacle.png");
							bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(view.mapcells.get(0).getWidth(),view.mapcells.get(0).getHeight(), java.awt.Image.SCALE_SMOOTH));
						
						}
						else
						{
							lb.setToolTipText(ob.getHp()+" HP" +((Merperson)ob).getDamage() + " damage " );
							bb = new ImageIcon("mer.png");
							bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(view.mapcells.get(0).getWidth(),view.mapcells.get(0).getHeight(), java.awt.Image.SCALE_SMOOTH));
						}
						}
						else
						if(c instanceof WallCell ){
							lb.setToolTipText("Wall Cell");
							bb = new ImageIcon("wall.png");
							bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(view.mapcells.get(0).getWidth(),view.mapcells.get(0).getHeight(), java.awt.Image.SCALE_SMOOTH));	
							
						}
						
						else
						bb = new ImageIcon();
				if(i==4&&j==4 && task instanceof FirstTask)
				{
					lb.setToolTipText("The Egg");
					bb = new ImageIcon("egg.png");
					bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(view.mapcells.get(0).getWidth(),view.mapcells.get(0).getHeight(), java.awt.Image.SCALE_SMOOTH));
				}
				lb.setIcon(bb);
			}
		}
		loadCurrent();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton o = (JButton)e.getSource();
		if( o == view.up)
		{
			try {
				task.moveForward();
			} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				if(!(e1.getMessage().equals("Cannot move to WallCell")))
					JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}else
			if( o == view.down)
			{
				try {
					task.moveBackward();
				} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
					
					if(!(e1.getMessage().equals("Cannot move to WallCell")))
						JOptionPane.showMessageDialog(null,e1.getMessage());
				}
			}else
				if( o == view.left)
				{
					try {
						task.moveLeft();
					} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
						
						if(!(e1.getMessage().equals("Cannot move to WallCell")))
							JOptionPane.showMessageDialog(null,e1.getMessage());
					}
				}else
					if( o == view.right)
					{
						try {
							task.moveRight();
						} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
							
							if(!(e1.getMessage().equals("Cannot move to WallCell")))
								JOptionPane.showMessageDialog(null,e1.getMessage());
						}
					}
		if(o == view.trait)
		{
			Trait();
		}
	
		if(view.spells.contains(o))
		{

			Spell s =((Wizard)task.getCurrentChamp()).getSpells().get(view.spells.indexOf(o));
			String name = "Name: "+s.getName();
			String cost = "Cost: " + s.getCost() ;
			String cool = "Cooldown: "+ s.getDefaultCooldown();
			String curcol = "Current Cooldown: " + s.getCoolDown();
			String am = "";
			if(s instanceof DamagingSpell)
				am = "Damage amount: "+ ((DamagingSpell)s).getDamageAmount();
			else
				if(s instanceof HealingSpell)
					am = "Heal amount: "+ ((HealingSpell)s).getHealingAmount();
				else
					if(s instanceof RelocatingSpell)
						am = "Range: "+ ((RelocatingSpell)s).getRange();

			int ia = JOptionPane.showConfirmDialog(null,name + '\n' + cost + '\n' + cool + '\n' + curcol + '\n' + am , "Spell", JOptionPane.CANCEL_OPTION);
			if(ia == JOptionPane.OK_OPTION)
			{
				if(s instanceof DamagingSpell)
					{
					int ds = JOptionPane.showOptionDialog(null, "Target?", "Spell ",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Direction.values(),
							Direction.values()[0]);
					
					if(! (ds == JOptionPane.CLOSED_OPTION))
						try {
							task.castDamagingSpell((DamagingSpell)s, Direction.values()[ds]);
						} catch (InCooldownException | NotEnoughIPException | OutOfBordersException
								| InvalidTargetCellException | IOException e1) {
							
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					
					
					}
				else
					if(s instanceof HealingSpell)
						try {
							task.castHealingSpell((HealingSpell)s);
						} catch (InCooldownException | NotEnoughIPException | IOException e1) {
							
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					else
						if(s instanceof RelocatingSpell)
						{
							int ds = JOptionPane.showOptionDialog(null, "From?", "Spell ",
									JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Direction.values(),
									Direction.values()[0]);
							
							if(! (ds == JOptionPane.CLOSED_OPTION))
							{
								int dto = JOptionPane.showOptionDialog(null, "To?", "Spell ",
										JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Direction.values(),
										Direction.values()[0]);
								
								if(! (dto == JOptionPane.CLOSED_OPTION))
								{
									
									String range = JOptionPane.showInputDialog("Enter the range: ");
									try{
										
									
									int ra = Integer.parseInt(range);
									task.castRelocatingSpell((RelocatingSpell)s, Direction.values()[ds], Direction.values()[dto], ra);
									
									}
									catch (Exception e2) {
										JOptionPane.showMessageDialog(null, e2.getMessage());
									//	System.out.println(e2.getMessage());
										
									}
									}
							}
						}
			}
		}
		if(o == view.potion  )
		{

			if(((Wizard)task.getCurrentChamp()).getInventory().size() ==0)
				JOptionPane.showMessageDialog(null, "No Potions");
			else{
			String [] nam = new String[((Wizard)task.getCurrentChamp()).getInventory().size()];
			for(int i = 0;i< nam.length;i++)
			{
				nam[i]=((Wizard)task.getCurrentChamp()).getInventory().get(i).getName()+", IP: "+((Potion)((Wizard)task.getCurrentChamp()).getInventory().get(i)).getAmount();
			}
			int po= JOptionPane.showOptionDialog(null, "What potions do you want to use?", "use potion",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, nam,
					nam[0]);
			if(po!=JOptionPane.CLOSED_OPTION){
				if(((Wizard)task.getCurrentChamp()).getInventory().size()>0)
					task.usePotion((Potion)(((Wizard)task.getCurrentChamp()).getInventory().get(po)));
			}
			}
		}
		if(o == view.exit){
			int ia = JOptionPane.showConfirmDialog(null,"Do you wanna leave?", "EXIT", JOptionPane.CANCEL_OPTION);
			if(ia == JOptionPane.OK_OPTION)
				view.dispose();
		}
		
		
	}
	
		
	
	public void fi(ArrayList<Point> ma)
	{
		JLabel f = new JLabel(new ImageIcon("fire.png"));
	//	System.out.println(ma.size());
		f.setSize(view.mapcells.get(0).getSize());
		for(Point p : ma)
			view.mapcells.get(p.y+p.x*10).setBorder(BorderFactory.createLineBorder(Color.RED, 2));;
		Timer t= new Timer(1500, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(Point p : ma)
					view.mapcells.get(p.y+p.x*10).setBorder(BorderFactory.createEmptyBorder());;
				view.repaint();
				view.revalidate();
			}
		});
		t.setRepeats(false);
		t.start();
		
		
	}
	
	public void Trait(){
		
		String msg = "";
		
		
		
		if(task.getCurrentChamp() instanceof GryffindorWizard)
			msg = "This turn, the champion can make 2 moves instead of 1. (Cooldown : 4)";
		else if(task.getCurrentChamp() instanceof SlytherinWizard){
			if(task instanceof FirstTask)
				msg = "This turn, the champion can choose between:" + '\n'+
				"1. Jumping over a cell containing an obstacle without destroying or moving the obstacle; provided that he ends up in an empty cell." + '\n'+
				"2. Traversing two cells instead of one (Cooldown : 6)";
			else if(task instanceof SecondTask)
				msg = "This turn, the champion can choose between: traverse two cells instead of one provided that he ends up in an empty cell(Cooldown : 4)";
			else if(task instanceof ThirdTask)
				msg = "This turn, the champion can choose between:" + '\n'+
				"1. Moving through a wall given that the cell he ends up in is not another wall" + '\n' +
				"2. Jumping over a cell containing an obstacle without destroying or moving the obstacle; provided that he ends up in an empty cell." + '\n'+
				"3. Traversing two cells instead of one (Cooldown : 10)";}
		else if(task.getCurrentChamp() instanceof HufflepuffWizard){
			if(task instanceof FirstTask)
				msg = "This turn, the dragon doesn’t attack (Cooldown: 3)";
			else if(task instanceof SecondTask)
				msg = "This turn, the merpeople won’t do any damage (Cooldown : 6)";
			else if(task instanceof ThirdTask)
				msg = "Attacks from other champions will only deal half the damage (Always activated, no cooldown)"; 
		}else if(task.getCurrentChamp() instanceof RavenclawWizard){
			if(task instanceof FirstTask)
				msg = "This turn, the champion is shown where the dragon is going to attack (Cooldown : 5)";
			else if(task instanceof SecondTask)
				msg = "This turn, the champion is given a hint on where the target is hidden relative to the current position (left or right or up or down) (Cooldown : 7)";
			else if(task instanceof ThirdTask)
				msg = "This turn, the champion is given a hint on where the cup is hidden relative to the current position (left or right or up or down) (Cooldown : 7)";
		}
		
		int i = JOptionPane.showConfirmDialog(null, msg + '\n' + "Want to activate your trait?");
		if(i==JOptionPane.OK_OPTION){
			//slytherin tala3 option pane tany
			if(((Wizard)task.getCurrentChamp()) instanceof SlytherinWizard)
			{
			int o = JOptionPane.showOptionDialog(null, "Where do you want to go?", "use trait",
			JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Direction.values(),
			Direction.values()[0]);
		    
			// use slyther trait Direction.values()[0]
			if(! (o == JOptionPane.CLOSED_OPTION))
				((SlytherinWizard)task.getCurrentChamp()).setTraitDirection(Direction.values()[o]);
			}
			try {
					task.getCurrentChamp().useTrait();
				} catch (InCooldownException | OutOfBordersException | InvalidTargetCellException | IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				
			}
		}
		
	}
	
	public void moveFromTo(Point from, Point to)
	{
		
		ImageIcon fp = (ImageIcon) (view.mapcells.get(from.y+from.x*10).getIcon());
		view.mapcells.get(from.y+from.x*10).setIcon(null);
		view.mapcells.get(to.y+to.x*10).setIcon(fp);
		
		if(task.getMap()[to.x][to.y] instanceof ObstacleCell)
		{
			
			view.mapcells.get(to.y+to.x*10).setToolTipText(view.mapcells.get(from.y+from.x*10).getToolTipText());
			view.mapcells.get(from.y+from.x*10).setToolTipText(null);
		}
		
		
		
	}
	
	
	
	
	public void loadCurrent(){
		
		for(int i =0 ; i<3; i++)
		{
			view.spells.get(i).setText(((Wizard)task.getCurrentChamp()).getSpells().get(i).getName());
			view.spells.get(i).setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
			Spell spell = ((Wizard)task.getCurrentChamp()).getSpells().get(i);
			if(spell instanceof DamagingSpell)
				view.spells.get(i).setBackground(Color.red);
			if(spell instanceof RelocatingSpell)
				view.spells.get(i).setBackground(Color.yellow);
			if(spell instanceof HealingSpell)
				view.spells.get(i).setBackground(Color.green);
		}
		
		for(Champion c : task.getChampions())
		{
			view.hp.get(((Wizard)c).i).setValue(((Wizard)c).getHp());
			view.hp.get(((Wizard)c).i).setString("HP: " + ((Wizard)c).getHp());
			
			view.ip.get(((Wizard)c).i).setValue(((Wizard)c).getIp());
			view.ip.get(((Wizard)c).i).setString("IP: " + ((Wizard)c).getIp());
		}
		Wizard w = (Wizard)task.getCurrentChamp();
		view.infoPic.setLayout(new GridLayout(1,2));
		ImageIcon bb = new ImageIcon(w.i+"c.png");
		bb = new ImageIcon(((Image) bb.getImage()).getScaledInstance(view.infoPic.getWidth(),view.infoPic.getHeight(), java.awt.Image.SCALE_SMOOTH));
		view.infoPic.setIcon(bb);
		
		view.infoTxt.removeAll();
		
		view.infoTxt.add(new JLabel("Current Champion"));
		view.infoTxt.add(new JLabel(w.getName()));
		view.infoTxt.add(new JLabel(w.getClass().getSimpleName()));
		view.infoTxt.add(new JLabel("HP : "+w.getHp() +""));
		view.infoTxt.add(new JLabel("IP : "+w.getIp() +""));
		view.infoTxt.add(new JLabel("The Trait is " + (task.isTraitActivated() ? "":"Not ") + "Activated" ) );
		view.infoTxt.add(new JLabel("You have "+task.getAllowedMoves()+" move(s) left"));
		// add spells buttons
		view.trait.setToolTipText(w.getTraitCooldown()+ "turn(s) to cooldown");
		view.potion.setToolTipText(w.getInventory().size()+ " potion(s) ");
		view.repaint();
		view.revalidate();
		
	}
	public void startFight(ArrayList<Champion> enemies, Champion current) throws IOException{
		this.enemies = enemies;
		temp = current;
		String [] enem = new String[this.enemies.size()];
		for(int i = 0;i< this.enemies.size();i++)
		{
			enem[i]=((Wizard)this.enemies.get(i)).getName();
		}
		int po= JOptionPane.showOptionDialog(null, "Whom do you want to fight?", "Battle Time!",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, enem,
				enem[0]);
		if(po==-1){
		while(po==-1){
			 po= JOptionPane.showOptionDialog(null, "You can't run away!" + '\n' + "Whom do you want to fight?", "Battle Time!",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, enem,
					enem[0]);
			 if(po!=-1){
					currentEnemy = this.enemies.get(po);
					fight = new battleView(task.getCurrentChamp(),this.enemies.get(po), this);
					fight.addWindowListener(this);
					enemies.remove(po);
//					if(this.enemies.size()==0){
//						task.endTurn();
//						return;
//					}
				}
		}}
		else{
			currentEnemy = this.enemies.get(po);
			fight = new battleView(task.getCurrentChamp(),this.enemies.get(po), this);
			fight.addWindowListener(this);
			enemies.remove(po);
//			if(this.enemies.size()==0){
//				task.endTurn();
//				loadCurrent();
//				return;
//			}
		}
		
	}
	
	@Override
	public void windowClosed(WindowEvent arg0) {
		if(arg0.getWindow().equals(fight) && enemies.size()>0){
			try {
				startFight(enemies, temp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				task.endTurn();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loadCurrent();
			endTurnMessage();
		}
	}
	
	public void endTurnMessage(){
		JOptionPane.showMessageDialog(null, "Your turn is over!");
	}
	
	public void rempoint(Point p)
	{
		view.mapcells.get(p.y +p.x*10).setIcon(null);
		view.mapcells.get(p.y+p.x*10).removeAll();
	}


	public static void main(String[] args) {
//		Tournament tour = null;
//		try {
//			tour = new Tournament();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		
//		ArrayList<Spell> s1 = new ArrayList<>();
//		for(int i =0 ; i <3; i ++)
//			s1.add(tour.getSpells().get((int)(Math.random()*21)));
//		ArrayList<Spell> s2 = new ArrayList<>();
//		for(int i =0 ; i <3; i ++)
//			s2.add(tour.getSpells().get((int)(Math.random()*21)));
//		ArrayList<Spell> s3 = new ArrayList<>();
//		for(int i =0 ; i <3; i ++)
//			s3.add(tour.getSpells().get((int)(Math.random()*21)));
//		ArrayList<Spell> s4 = new ArrayList<>();
//		for(int i =0 ; i <3; i ++)
//			s4.add(tour.getSpells().get((int)(Math.random()*21)));
//		
//		Champion g = new GryffindorWizard("gryff");
//		((Wizard)g).i = 0;
//		((Wizard)g).setSpells(s1);
//		//((Wizard)g).setDefaultHp(1);
//		Champion h = new HufflepuffWizard("huff");
//		((Wizard)h).i = 1;
//		((Wizard)h).setSpells(s2);
//		//((Wizard)h).setDefaultHp(1);
//		Champion s = new SlytherinWizard("sly");
//		((Wizard)s).i = 2;
//		((Wizard)s).setSpells(s3);
//		//((Wizard)s).setDefaultHp(1);
//		Champion r = new RavenclawWizard("rav");
//		((Wizard)r).i = 3;
//		((Wizard)r).setSpells(s4);
//		//((Wizard)r).setDefaultHp(1);
//		tour.addChampion(g);
//		tour.addChampion(h);
//		tour.addChampion(s);
//		tour.addChampion(r);
//		new TaskController(tour);
	}

	
	//UNUSED METHODS
	public void windowClosing(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	public void windowActivated(WindowEvent arg0) {}
	
	
}
