package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import engine.Game;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;

public class Control3 implements ActionListener {
	private Game game;
	private String name1;
	private String name2;
	private ArrayList<Champion> team;
	private ArrayList<JButton> buttons;
	static private int i = 0;
	private View3 view3;
	public Control3(Game game, String name1, String name2, ArrayList<Champion> team) {
		this.game = game;
		this.name1 = name1;
		this.name2 = name2;
		this.team = team;
		view3 = new View3(name1);
		view3.setVisible(true);
		// JOptionPane.showMessageDialog(null, "Choose your
		// leader!!","Leader",JOptionPane.INFORMATION_MESSAGE);
		buttons = new ArrayList<>();
		for (Champion c : team) {
		//	System.out.print(i++);
			// create a JButton for each product in the supermarket
			JButton champButton = new JButton();
			ImageIcon img = new ImageIcon(c.getName()+"1.png");
			champButton.setIcon(img);
			champButton.setOpaque(false);
			champButton.setContentAreaFilled(false);
			champButton.setBorderPainted(false);
			//champButton.setOpaque(true);
			// set its text to the product's info

			String s = "<html>" + "Name: " + c.getName() + "<br>";
			s += "MaxHp: " + c.getMaxHP() + "<br>";
			s += "Attack Damage: " + c.getAttackDamage() + "<br>";
			s += "Attack Range: " + c.getAttackRange() + "<br>";
			s += "Speed: " + c.getSpeed() + "<br>";
			s += "Max Action Points: " + c.getMaxActionPointsPerTurn() + "<br>";
			s += "Abilities: " + c.getAbilities().get(0).getName() + ", " + c.getAbilities().get(1).getName() + ", "
					+ c.getAbilities().get(2).getName() + "<html>";
			String ability="";
			for(int i=0;i<c.getAbilities().size();i++){
				Ability a= c.getAbilities().get(i);
				String ty=null;
				String am=null;
				if (a instanceof HealingAbility){
					ty="HealingAbility";
					am= "HealAmount: "+((HealingAbility)a).getHealAmount();
				}
				if (a instanceof DamagingAbility){
					ty="DamagingAbility";
				am= "DamageAmount: "+((DamagingAbility)a).getDamageAmount();
				}
				if (a instanceof CrowdControlAbility){
					ty="CrowdControlAbility";
					am= "Effect "+((CrowdControlAbility)a).getEffect().getName()+" "+((CrowdControlAbility)a).getEffect().getDuration()+" turns";

				}
				int x= i+1;
				ability+=x+" name:" +"&nbsp;&nbsp;"+a.getName()+"<br>"+ty+"<br>"+a.getCastArea()+"&nbsp;&nbsp;" +"&nbsp;&nbsp;"+
					"Cast range: "+a.getCastRange()+"<br>"+ "Mana:"+ a.getManaCost()+"&nbsp;&nbsp;" +"&nbsp;&nbsp;"+ "Action points:"+a.getRequiredActionPoints()
					+ "<br>"+"Current CoolDown:"+a.getCurrentCooldown()+"&nbsp;&nbsp;" +"&nbsp;&nbsp;"+" Base CoolDown: "+a.getBaseCooldown()+"<br>"+am+"<br>"+"<br>";
			}
			s+="<br>"+ability;
			champButton.setToolTipText(s);
			champButton.addActionListener(this);
			champButton.setFocusable(false);
			view3.addChamp(champButton);

			// and also add it to the ArrayList for later use
			buttons.add(champButton);

		}
	}

	public void actionPerformed(ActionEvent e) {
		JButton champButton = (JButton) e.getSource();
		champButton.setEnabled(false);
		int ChampIndex = buttons.indexOf(champButton);
		Champion champ = team.get(ChampIndex);
		
		game.getFirstPlayer().setLeader(champ);
		view3.setVisible(false);
		new Control4 (game,name1,name2,game.getSecondPlayer().getTeam());
		
	}
}
