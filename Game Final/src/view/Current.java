package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.AntiHero;
import model.world.Champion;
import model.world.Hero;
import model.world.Villain;

public class Current extends JFrame{
	Champion cur;
	JPanel panel=new JPanel();

	public Current(Champion cur){
		this.setBackground(Color.WHITE);
		this.cur=cur;
		this.setSize(new Dimension (550,650));
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		JPanel panel= new JPanel();
		panel.setSize(new Dimension (550,650));
		panel.setBackground(Color.WHITE);
		
		JLabel label =new JLabel();
		label.setSize(new Dimension (300,600));
		label.setText(currentChamp(cur));
		label.setBackground(Color.WHITE);

		panel.add(label,BorderLayout.WEST);
		this.setVisible(true);
		JLabel label2=new JLabel();
		label2.setBackground(Color.WHITE);
		label2.setSize(new Dimension(200,200));
		String img =cur.getName()+ ".png";
		ImageIcon image = new ImageIcon (img);
		label2.setIcon(image);
		panel.add(label2,BorderLayout.EAST);
		this.add(panel);
		this.pack();
	}
	public String currentChamp(Champion champ){
		String type=null;
		if (champ instanceof Villain)
			type =" Villain";
		if (champ instanceof Hero)
			type=" Hero";
		if (champ instanceof AntiHero)
			type="AntiHero";
		String s= "Current Champion: "+"<br>"+"Name: "+champ.getName()+"<br>"
				+ "Type: "+type +"<br>"+
				"CurrentHp: "+champ.getCurrentHP()+"<br>" +
				"Mana: "+ champ.getMana()+"<br>"+
				"Current Action Points: "+champ.getCurrentActionPoints()+"<br>"+"Speed: "+champ.getSpeed()+
				"<br>"+"<br>"+"ATTACK"+"&nbsp;&nbsp;" +"&nbsp;&nbsp;"+"<br>"+"Attack Damage: "+champ.getAttackDamage()+" Attack Range: "+champ.getAttackRange()
				+"<br>"+"<br>"+
				"ABILITIES:"+"&nbsp;&nbsp;" +"&nbsp;&nbsp;";
		String ability="";
		for(int i=0;i<champ.getAbilities().size();i++){
			Ability a= champ.getAbilities().get(i);
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
		String eff="";
		for(int i=0;i<champ.getAppliedEffects().size();i++){
			eff+= champ.getAppliedEffects().get(i).getName()+"  "+champ.getAppliedEffects().get(i).getDuration()+" turns";
			
		}
		if(eff.equals(""))
			eff="No Effects";
		String out = "<html>"+s+"<br>"+ability+"EFFECTS"+"<br>"+eff+"<html>";
		return out;
	}
}
