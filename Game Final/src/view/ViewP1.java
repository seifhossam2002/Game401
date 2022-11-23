package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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

public class ViewP1 extends JFrame {
	private Champion cur;
	private boolean Leader;

	public ViewP1(Champion cur, boolean leader) {
		this.setTitle("Marvel");
		ImageIcon image1 = new ImageIcon("Logo.png");
		setIconImage(image1.getImage());
		this.setBackground(Color.WHITE);
		this.cur = cur;
		this.Leader = leader;
		this.setSize(new Dimension(550, 650));
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(550, 650));
		panel.setBackground(Color.WHITE);

		JLabel label = new JLabel();
		label.setSize(new Dimension(300, 600));
		label.setText(Champ(cur));
		label.setBackground(Color.WHITE);

		this.setVisible(true);
		JLabel label2 = new JLabel();
		label2.setBackground(Color.WHITE);
		label2.setSize(new Dimension(200, 200));
		String img = cur.getName() + ".png";
		ImageIcon image = new ImageIcon(img);
		label2.setIcon(image);
		panel.add(label2, BorderLayout.EAST);
		panel.add(label, BorderLayout.WEST);
		this.add(panel);
	}

	public String Champ(Champion champ) {
		String l = "";
		if (Leader)
			l = "LEADER";
		String type = null;
		if (champ instanceof Villain)
			type = " Villain";
		if (champ instanceof Hero)
			type = " Hero";
		if (champ instanceof AntiHero)
			type = "AntiHero";
		String s = "Name: " + champ.getName() + "&nbsp;&nbsp;" + "&nbsp;&nbsp;" + l + "<br>" + "Type: " + type + "<br>"
				+ "CurrentHp: " + champ.getCurrentHP() + "<br>" + "Speed: " + champ.getSpeed() + "<br>" + "Mana: "
				+ champ.getMana() + "<br>" + "Max Action Points: " + champ.getMaxActionPointsPerTurn() + "<br>" + "<br>"
				+ "ATTACK" + "<br>" + "Attack Damage: " + champ.getAttackDamage() + " Attack Range: "
				+ champ.getAttackRange() + "<br>" + "<br>";

		String eff = "";
		for (int i = 0; i < champ.getAppliedEffects().size(); i++) {
			eff += champ.getAppliedEffects().get(i).getName() + "  " + champ.getAppliedEffects().get(i).getDuration()
					+ " turns";

		}
		if (eff == "")
			eff = "No Effects";
		String out = "<html>" + s + "EFFECTS" + "<br>" + eff + "<html>";
		return out;
	}
}