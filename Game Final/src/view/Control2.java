package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import engine.Game;
import engine.Player;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;

public class Control2 implements ActionListener, MouseListener {
	private static View2 view2;
	private static ArrayList<JButton> buttons;
	private Game game;
	private static int i = 0;
	private String name1;
	private String name2;
	private JButton ready = new JButton();
	private JButton champButton = new JButton();
	private ArrayList<Champion> x = new ArrayList<>();
	private ArrayList<String> names = new ArrayList<>();
	private ArrayList<Champion> sel;

	public Control2(Game game, String name1, String name2) {
		names.add("");
		names.add("");
		names.add("");
		names.add("");
		names.add("");
		names.add("");
		view2 = new View2();
		view2.play("The Last Of us - Theme song.wav");
		JPanel panel = view2.getPanel3();
		buttons = new ArrayList<>();
		sel = new ArrayList<>();
		int i = 0;
		this.game = game;
		this.name1 = name1;
		this.name2 = name2;
		for (Champion c : game.getAvailableChampions()) {
			JButton champButton = new JButton();
			champButton.setSize(new Dimension(1, 100));
			champButton.setOpaque(false);
			champButton.setContentAreaFilled(false);
			champButton.setBorderPainted(false);
			String img = c.getName() + ".png";
			ImageIcon image = new ImageIcon(img);
			String s = "<html>" + "Name: " + c.getName() + "<br>";
			s += "MaxHp: " + c.getMaxHP() + "<br>";
			s += "Attack Damage: " + c.getAttackDamage() + "<br>";
			s += "Attack Range: " + c.getAttackRange() + "<br>";
			s += "Speed: " + c.getSpeed() + "<br>";
			s += "Max Action Points: " + c.getMaxActionPointsPerTurn() + "<br>";
			s += "Abilities: ";
			String ability = "";
			for (int j = 0; j < c.getAbilities().size(); j++) {
				Ability a = c.getAbilities().get(j);
				String ty = null;
				String am = null;
				if (a instanceof HealingAbility) {
					ty = "HealingAbility";
					am = "HealAmount: " + ((HealingAbility) a).getHealAmount();
				}
				if (a instanceof DamagingAbility) {
					ty = "DamagingAbility";
					am = "DamageAmount: " + ((DamagingAbility) a).getDamageAmount();
				}
				if (a instanceof CrowdControlAbility) {
					ty = "CrowdControlAbility";
					am = "Effect " + ((CrowdControlAbility) a).getEffect().getName() + " "
							+ ((CrowdControlAbility) a).getEffect().getDuration() + " turns";

				}
				ability += " name:" + "&nbsp;&nbsp;" + a.getName() + "<br>" + ty + "<br>" + a.getCastArea()
						+ "&nbsp;&nbsp;" + "&nbsp;&nbsp;" + "Cast range: " + a.getCastRange() + "<br>" + "Mana:"
						+ a.getManaCost() + "&nbsp;&nbsp;" + "&nbsp;&nbsp;" + "Action points:"
						+ a.getRequiredActionPoints() + "<br>" + "Current CoolDown:" + a.getCurrentCooldown()
						+ "&nbsp;&nbsp;" + "&nbsp;&nbsp;" + " Base CoolDown: " + a.getBaseCooldown() + "<br>" + am
						+ "<br>" + "<br>";
			}
			s += "<br>" + ability;
			champButton.setIcon(image);

			champButton.setToolTipText(s);
			champButton.setText(c.toString());
			champButton.addActionListener(this);
			champButton.setFocusable(false);
			champButton.addMouseListener(this);

			view2.addChamp(champButton);
			sel.add(c); // update
			buttons.add(champButton);

		}

		ImageIcon go = new ImageIcon("Proceed.png");
		ready = new JButton("Proceed");
		ready.setIcon(go);
		ready.setOpaque(false);
		ready.setContentAreaFilled(false);
		ready.setBorderPainted(false);
		// ready.setEnabled(false); ready.setSize(new Dimension(1,100));
		view2.addChamp(ready);
		ready.addActionListener(this); 
		buttons.add(ready);

		view2.setVisible(true);
		System.out.println(game.getFirstPlayer().getTeam().size());
		System.out.println(game.getSecondPlayer().getTeam().size());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton champButton = (JButton) e.getSource();
		if(e.getSource()!=ready) {
		champButton.setEnabled(false);
		int ChampIndex = buttons.indexOf(champButton);
		Champion champ = game.getAvailableChampions().get(ChampIndex);
		ArrayList<Champion> x = new ArrayList<>();
		ImageIcon im = new ImageIcon(champ.getName() + ".png");
		if (names.get(0).equals("")) {
			view2.changeLabel1();
			game.getFirstPlayer().getTeam().add(champ);
			x.add(champ);
			view2.getLabel1().setIcon(im);
			view2.getLabel1().addMouseListener(this);
			names.set(0, champ.getName());
			i++;

		} else if (names.get(1).equals("")) {
			view2.changeLabel1();
			game.getFirstPlayer().getTeam().add(champ);
			x.add(champ);
			view2.getLabel2().setIcon(im);
			view2.getLabel2().addMouseListener(this);
			names.set(1, champ.getName());
			i++;
		} else if (names.get(2).equals("")) {
			view2.changeLabel();
			game.getFirstPlayer().getTeam().add(champ);
			x.add(champ);
			view2.getLabel3().setIcon(im);
			view2.getLabel3().addMouseListener(this);
			names.set(2, champ.getName());
			i++;
		} else if (names.get(3).equals("")) {
			view2.changeLabel();
			game.getSecondPlayer().getTeam().add(champ);
			x.add(champ);
			view2.getLabel4().setIcon(im);
			view2.getLabel4().addMouseListener(this);
			names.set(3, champ.getName());
			i++;
		} else if (names.get(4).equals("")) {
			view2.changeLabel();
			game.getSecondPlayer().getTeam().add(champ);
			x.add(champ);
			view2.getLabel5().setIcon(im);
			view2.getLabel5().addMouseListener(this);
			names.set(4, champ.getName());
			i++;
		} else if (names.get(5).equals("")) {
			view2.changeLabel();
			game.getSecondPlayer().getTeam().add(champ);
			x.add(champ);
			view2.getLabel6().setIcon(im);
			view2.getLabel6().addMouseListener(this);
			names.set(5, champ.getName());
			i++;
		}
		int i = 0;
		while (!names.get(i).equals("")) {
			i++;
			if (i >= 6)
				break;
		}}
		if (e.getSource() == ready) {
			System.out.println(i);
			if (i >= 6)
				chooseLeader();
			else {
				if (game.getFirstPlayer().getTeam().size() != 3 && game.getSecondPlayer().getTeam().size() != 3)
					JOptionPane.showMessageDialog(null, "TEAMAK MESH KAMEL YA BANI2ADAM",
							name1 + " & " + name2, JOptionPane.WARNING_MESSAGE);
				else {
					if (game.getFirstPlayer().getTeam().size() != 3)
						JOptionPane.showMessageDialog(null, "Can't start the game as the teams are incomplete", name1,
								JOptionPane.WARNING_MESSAGE);

					if (game.getSecondPlayer().getTeam().size() != 3)
						JOptionPane.showMessageDialog(null, "Can't start the game as the teams are incomplete", name2,
								JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		// names.add(champ.getName());
		view2.revalidate();
		view2.repaint();

	}

	public void disable(ArrayList<JButton> t) {
		for (int i = 0; i < t.size(); i++) {
			t.get(i).setEnabled(false);
		}
	}

	public void chooseLeader() {
		view2.setVisible(false);
		//view2.pause();
		new Control3(game, name1, name2, game.getFirstPlayer().getTeam());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == view2.getLabel1()) {
			view2.changeLabel1();
			ImageIcon im = new ImageIcon("Blackcard.png");
			for (int i = 0; i < game.getFirstPlayer().getTeam().size(); i++) {
				if (names.get(0).equals(game.getFirstPlayer().getTeam().get(i).getName()))
					game.getFirstPlayer().getTeam().remove(i);
			}
			i--;
			names.set(0, "");
			view2.getLabel1().setIcon(im);
		}

		if (e.getSource() == view2.getLabel2()) {
			view2.changeLabel1();
			ImageIcon im = new ImageIcon("Blackcard.png");
			view2.getLabel2().setIcon(im);
			for (int i = 0; i < game.getFirstPlayer().getTeam().size(); i++) {
				if (names.get(1).equals(game.getFirstPlayer().getTeam().get(i).getName()))
					game.getFirstPlayer().getTeam().remove(i);
			}
			names.set(1, "");
		}

		if (e.getSource() == view2.getLabel3()) {
			view2.changeLabel1();
			// i--;
			ImageIcon im = new ImageIcon("Blackcard.png");
			view2.getLabel3().setIcon(im);
			for (int i = 0; i < game.getFirstPlayer().getTeam().size(); i++) {
				if (names.get(2).equals(game.getFirstPlayer().getTeam().get(i).getName()))
					game.getFirstPlayer().getTeam().remove(i);
			}
			i--;
			names.set(2, "");
		}

		for (int i = 0; i < sel.size(); i++) {
			boolean flag = false;
			for (int j = 0; j < names.size(); j++) {
				if (sel.get(i).getName().equals(names.get(j))) {
					flag = true;
				}
			}
			if (!flag)
				buttons.get(i).setEnabled(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == view2.getLabel4()) {
			view2.changeLabel();
			// i--;
			ImageIcon im = new ImageIcon("Blackcard.png");
			view2.getLabel4().setIcon(im);
			for (int i = 0; i < game.getSecondPlayer().getTeam().size(); i++) {
				if (names.get(3).equals(game.getSecondPlayer().getTeam().get(i).getName()))
					game.getSecondPlayer().getTeam().remove(i);
			}
			i--;
			names.set(3, "");
		}
		if (e.getSource() == view2.getLabel5()) {
			view2.changeLabel();
			// i--;
			ImageIcon im = new ImageIcon("Blackcard.png");
			view2.getLabel5().setIcon(im);
			for (int i = 0; i < game.getSecondPlayer().getTeam().size(); i++) {
				if (names.get(4).equals(game.getSecondPlayer().getTeam().get(i).getName()))
					game.getSecondPlayer().getTeam().remove(i);
			}
			i--;
			names.set(4, "");
		}
		if (e.getSource() == view2.getLabel6()) {
			view2.changeLabel();
			// i--;
			ImageIcon im = new ImageIcon("Blackcard.png");
			view2.getLabel6().setIcon(im);
			for (int i = 0; i < game.getSecondPlayer().getTeam().size(); i++) {
				if (names.get(5).equals(game.getSecondPlayer().getTeam().get(i).getName()))
					game.getSecondPlayer().getTeam().remove(i);
			}
			i--;
			names.set(5, "");
		}

		for (int i = 0; i < sel.size(); i++) {
			boolean flag = false;
			for (int j = 0; j < names.size(); j++) {
				if (sel.get(i).getName().equals(names.get(j))) {
					flag = true;
				}
			}
			if (!flag)
				buttons.get(i).setEnabled(true);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	//	view2.play("Roblox Death Sound - Sound Effect (HD).wav");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}