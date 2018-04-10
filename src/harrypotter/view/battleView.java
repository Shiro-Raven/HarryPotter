package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import harrypotter.controller.TaskController;
import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;

@SuppressWarnings("serial")
public class battleView extends JFrame implements KeyListener{
	TaskController cont;
	JProgressBar battleState;
	JLabel Current;
	JLabel Enemy;
	int champIp;
	int enemyIp;
	Champion main;
	Champion enemy;

	public battleView(Champion main, Champion enemy, TaskController cont) {
		super();
		this.main = main;
		
		this.enemy = enemy;
		champIp = ((Wizard)main).getIp();
		enemyIp = ((Wizard)enemy).getIp();
		this.cont = cont;
		cont.view.setEnabled(false);
		Current = new JLabel();
		Enemy = new JLabel();
		setTitle("The Battle between: " + ((Wizard)main).getName() + " and " + ((Wizard)enemy).getName());
		setSize(750, 650);
	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		addKeyListener(this);
		
		//Progress Bar
		battleState = new JProgressBar();
		battleState.setString("Battle State");
		battleState.setStringPainted(true);
		battleState.setMaximum(10000);
		battleState.setValue(5000);
		
		switch(((Wizard)main).i){
		case 0: battleState.setForeground(new Color(128, 21, 21));;break;
		case 1: battleState.setForeground(new Color(170, 164, 52));break;
		case 2: battleState.setForeground(new Color(49, 51, 119));break;
		case 3: battleState.setForeground(new Color(37, 101, 41));break;
		}
		switch(((Wizard)enemy).i){
		case 0: battleState.setBackground(new Color(128, 21, 21));;break;
		case 1: battleState.setBackground(new Color(170, 164, 52));break;
		case 2: battleState.setBackground(new Color(49, 51, 119));break;
		case 3: battleState.setBackground(new Color(37, 101, 41));break;
		}
		battleState.setPreferredSize(new Dimension(1000, 100));
		getContentPane().add(battleState, BorderLayout.NORTH);
		
		
		ImageIcon current = new ImageIcon(((Wizard)main).i+"cl.png");
		current = new ImageIcon(((Image) current.getImage()).getScaledInstance(400,300, java.awt.Image.SCALE_SMOOTH));
		Current.setIcon(current);
		JLabel cham1 = new JLabel();
		cham1.setIcon(current);
		cham1.setOpaque(false);
		
		ImageIcon enem = new ImageIcon(((Wizard)enemy).i+"cr.png");
		enem = new ImageIcon(((Image) enem.getImage()).getScaledInstance(400,300, java.awt.Image.SCALE_SMOOTH));
		Enemy.setIcon(enem);
		JLabel cham2 = new JLabel();
		cham2.setIcon(enem);
		cham2.setOpaque(true);
		
		
		getContentPane().add(cham1, BorderLayout.WEST);
		getContentPane().add(cham2, BorderLayout.EAST);
		
		JLabel names = new JLabel();
		names.setLayout(new GridLayout(1, 2));
		names.add(new JLabel(((Wizard)main).getName() , SwingConstants.CENTER));
		names.add(new JLabel(((Wizard)enemy).getName(), SwingConstants.CENTER));
		names.setPreferredSize(new Dimension(1000, 100));
		
		getContentPane().add(names, BorderLayout.SOUTH);
		setIconImage((new ImageIcon("battleback")).getImage());
		setVisible(true);
		String message = "The rules:" + '\n' + "THERE'S NO RULES! THIS IS A MASH BUTTON CHALLENGE" 
				+ '\n' + ((Wizard)main).getName() + ": Keep mashing the W button." + '\n' +
				((Wizard)enemy).getName() + ": Keep hitting the up arrow button." + '\n' + 
				"The higher your IP, the stronger the effect of your 'mash'! :D"+ '\n' + "Good Luck!";
		JOptionPane.showMessageDialog(null, message);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
			battleState.setValue(battleState.getValue()+champIp);
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			battleState.setValue(battleState.getValue()-enemyIp);
		}
		if(battleState.getValue() >= 10000)
			try {
				mainWinner();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		else if(battleState.getValue() <= 0)
			try {
				enemyWinner();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
	public void mainWinner() throws IOException {
		Wizard wiz1 = (Wizard)main;
		Wizard wiz2 = (Wizard)enemy;
		JOptionPane.showMessageDialog(null, (wiz1.getName()+ " is the winner!"+ '\n'+
				wiz2.getName() + " loses half his hp! :("));
		wiz2.setHp(wiz2.getHp()/2);
		
		cont.view.hp.get(wiz2.i).setValue(wiz2.getHp());
		cont.view.hp.get(wiz2.i).setString("HP: " + ((Wizard)wiz2).getHp());
		if(wiz2.getHp()<=0){
			JOptionPane.showMessageDialog(null,wiz2.getName() + " died! :(");
			cont.rempoint(wiz2.getLocation());
			cont.task.getChampions().remove((Champion)wiz2);
//			cont.task.endTurn();
		}
		
		cont.view.setEnabled(true);
		
		cont.loadCurrent();
		this.dispose();
		
	}
	public void enemyWinner() throws IOException {
		Wizard wiz1 = (Wizard)main;
		Wizard wiz2 = (Wizard)enemy;
		JOptionPane.showMessageDialog(null, (wiz2.getName()+ " is the winner!"+ '\n'+
				wiz1.getName() + " loses half his hp! :("));
		wiz1.setHp(wiz1.getHp()/2);
		
		cont.view.hp.get(wiz1.i).setValue(wiz1.getHp());
		cont.view.hp.get(wiz1.i).setString("HP: " + ((Wizard)wiz1).getHp());
		
		if(wiz1.getHp()<=0){
			JOptionPane.showMessageDialog(null,wiz1.getName() + " died! :(");
			cont.rempoint(wiz1.getLocation());
			cont.task.getChampions().remove((Champion)wiz1);
//			cont.task.endTurn();
		}
		
		cont.view.setEnabled(true);
		cont.loadCurrent();
		this.dispose();		
	}
	
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}

}
