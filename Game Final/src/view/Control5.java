package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import engine.*;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.EffectType;
import model.world.*;

public class Control5 implements KeyListener, ActionListener {
	private Game game;
	private View5 view5;
	private JButton end;
	private Player p1;
	private Player p2;
	private JButton current;
	private JButton Leader1;
	private JButton Leader2;
	private JButton move;
	private JButton attack;
	private JButton ability;
	private KeyBinding key;
	private boolean SingleTarget = false;
	private Ability st;
	private static ArrayList<JButton> abilities;
	private static ArrayList<JButton> buttons1;
	private static ArrayList<JButton> buttons2;
	private ArrayList<JButton> A1;
	private ArrayList<JButton> A2;
	private ArrayList<JButton> A3;
	private ArrayList<JButton> A4;
	private ArrayList<JButton> A0;
	private ArrayList<Champion> team1Copy;
	private ArrayList<Champion> team2Copy;
	private boolean Move;
	private boolean Attack;

	private void refresh() {
		view5.getPanel5().removeAll();
		if (game.checkGameOver() != null) {
			view5.setVisible(false);
			new Control6(game, p1, p2, team1Copy, team2Copy);
		}
		A1 = new ArrayList<>();
		A2 = new ArrayList<>();
		A3 = new ArrayList<>();
		A4 = new ArrayList<>();
		A0 = new ArrayList<>();

		Object[][] board = game.getBoard();
		for (int i = 4; i > -1; i--) {
			ArrayList<JButton> bArray = new ArrayList<>();
			if (i == 0)
				bArray = A0;
			if (i == 1)
				bArray = A1;
			if (i == 2)
				bArray = A2;
			if (i == 3)
				bArray = A3;
			if (i == 4)
				bArray = A4;
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] instanceof Cover) {
					JButton b = new JButton();
					ImageIcon img = new ImageIcon("cover.png");
					b.setIcon(img);
					b.setBackground(new Color(0, 0, 0, 128));
					b.setOpaque(true);
					b.setBorder(BorderFactory.createLineBorder(Color.white, 2));
					view5.addThing(b);
					Cover cov = (Cover) board[i][j];
					b.setToolTipText("HP: " + cov.getCurrentHP());
					b.addActionListener(this);
					bArray.add(b);
				} else {
					if (board[i][j] instanceof Champion) {
						Champion c = (Champion) board[i][j];
						JButton b = new JButton();
						b.setBackground(new Color(0, 0, 0, 128));
						b.setOpaque(true);
						b.setBorder(BorderFactory.createLineBorder(Color.white, 2));
						ImageIcon img = new ImageIcon(c.getName() + "3.png");
						b.setIcon(img);
						view5.addThing(b);
						b.addActionListener(this);
						bArray.add(b);
					} else {
						JButton b = new JButton();
						b.setBackground(new Color(0, 0, 0, 128));
						b.setOpaque(true);
						b.setBorder(BorderFactory.createLineBorder(Color.white, 2));
						view5.addThing(b);
						b.addActionListener(this);

						bArray.add(b);
					}
				}

			}
			// System.out.println();
		}

		view5.repaint();
		view5.revalidate();
		// view5.panel5.requestFocus();
		// view5.panel5.setFocusable(true);
		// view5.panel5.setRequestFocusEnabled(true);
		// view5.panel5.requestFocusInWindow();
	}

	public void refresh2() {
		view5.getPanel6().removeAll();
		view5.getPanel9().removeAll();
		view5.getPanel10().removeAll();
		abilities = new ArrayList<>();
		String img = game.getCurrentChampion().getName() + "2.png";
		ImageIcon image = new ImageIcon(img);
		current = new JButton();
		current.setIcon(image);
		current.setOpaque(false);
		current.setContentAreaFilled(false);
		current.setBorderPainted(false);

		current.setIcon(image);
		current.addActionListener(this);
		view5.getPanel6().add(current);
		for (int i = 0; i < game.getCurrentChampion().getAbilities().size(); i++) {
			JButton b = new JButton();
			b.setText("   " + game.getCurrentChampion().getAbilities().get(i).getName() + "  ");
			b.addActionListener(this);
			abilities.add(b);
			view5.getPanel9().add(b);

		}

		move = new JButton("      Move      ");
		attack = new JButton("     Attack     ");

		move.addActionListener(this);
		attack.addActionListener(this);
		view5.getPanel9().add(move);
		view5.getPanel9().add(attack);
		String turn = "<html>" + "Next:" + "<br>";
		ArrayList<Champion> temp = new ArrayList<>();
		temp.add((Champion) game.getTurnOrder().remove());// update
		while (!game.getTurnOrder().isEmpty()) {
			Champion champ = (Champion) game.getTurnOrder().peekMin();
			// JButton t=new JButton(champ.getName());
			turn += champ.getName() + "<br>";
			temp.add((Champion) game.getTurnOrder().remove());
		}
		turn += "<html>" + "\n";
		JLabel turnOrder = new JLabel();
		turnOrder.setText(turn);
		turnOrder.setFont(new Font("Avengeance Heroic Avenger", Font.BOLD, 25));
		turnOrder.setForeground(Color.white);

		view5.getPanel10().add(turnOrder);
		for (int i = 0; i < temp.size(); i++) {
			game.getTurnOrder().insert(temp.get(i));
		}

		end = new JButton("   End Turn   ");
		end.addActionListener(this);
		view5.getPanel9().add(end);
		view5.repaint();
		view5.validate();
		view5.repaint();
		view5.validate();
	}

	public void refresh3() {
		view5.getP1().removeAll();
		view5.getP2().removeAll();
		buttons1 = new ArrayList<>();
		buttons2 = new ArrayList<>();
		view5.setname1();
		view5.setname2();
		for (int i = 0; i < p1.getTeam().size(); i++) {
			System.out.println("was here");
			JButton b = new JButton();
			b.setText(p1.getTeam().get(i).getName());
			// ImageIcon img3 = new ImageIcon(p1.getTeam().get(i).getName() + "4.png");
			// b.setIcon(img3);
			b.setForeground(Color.white);
			b.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 20));
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.addActionListener(this);

			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			// b.setText(p1.getTeam().get(i).getName());

			b.addActionListener(this);
			view5.add1(b);
			buttons1.add(b);
		}
		if (game.isFirstLeaderAbilityUsed() == false) {
			Leader1 = new JButton();
			Leader1.setOpaque(false);
			Leader1.setContentAreaFilled(false);
			Leader1.setBorderPainted(false);
			Leader1.setText("Use Leader Ability1");
			Leader1.setForeground(Color.white);
			Leader1.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 20));
			Leader1.addActionListener(this);
			view5.add1(Leader1);
		} else {
			Leader1 = new JButton();
			Leader1.setOpaque(false);
			Leader1.setContentAreaFilled(false);
			Leader1.setBorderPainted(false);
			Leader1.setText("Use Leader Ability1");
			Leader1.setForeground(Color.white);
			Leader1.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 20));
			Leader1.addActionListener(this);
			view5.add1(Leader1);
			Leader1.setEnabled(false);
		}

		for (int i = 0; i < p2.getTeam().size(); i++) {
			System.out.println("was here");
			JButton b = new JButton();
			b.setText(p2.getTeam().get(i).getName());
			// ImageIcon img3 = new ImageIcon(p1.getTeam().get(i).getName() + "4.png");
			// b.setIcon(img3);
			b.setForeground(Color.black);
			b.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 20));
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.addActionListener(this);
			view5.add2(b);
			buttons2.add(b);
		}
		if (game.isSecondLeaderAbilityUsed() == false) {
			Leader2 = new JButton();
			Leader2.setOpaque(false);
			Leader2.setContentAreaFilled(false);
			Leader2.setBorderPainted(false);
			Leader2.setText("Use Leader Ability");
			Leader2.setForeground(Color.black);
			Leader2.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 20));
			Leader2.addActionListener(this);

			view5.add2(Leader2);
		} else {
			Leader2 = new JButton();
			Leader2.setOpaque(false);
			Leader2.setContentAreaFilled(false);
			Leader2.setBorderPainted(false);
			Leader2.setText("Use Leader Ability");
			Leader2.setForeground(Color.black);
			Leader2.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 20));
			Leader2.addActionListener(this);
			Leader2.setEnabled(false);
			view5.add2(Leader2);
		}
		view5.repaint();
		view5.validate();

	}

	public Control5(Player p1, Player p2, String name1, String name2) {
		this.game = new Game(p1, p2);
		this.p1 = p1;
		this.p2 = p2;
		team1Copy = p1.getTeam();
		team2Copy = p2.getTeam();
		A1 = new ArrayList<>();
		A2 = new ArrayList<>();
		A3 = new ArrayList<>();
		A4 = new ArrayList<>();
		A0 = new ArrayList<>();
		buttons1 = new ArrayList<>();
		buttons2 = new ArrayList<>();
		abilities = new ArrayList<>();
		Object[][] board = game.getBoard();
		view5 = new View5(name1, name2);
		view5.play1("Spacetoon.wav");
		view5.setVisible(true);
		for (int i = 4; i > -1; i--) {
			ArrayList<JButton> bArray = new ArrayList<>();
			if (i == 0)
				bArray = A0;
			if (i == 1)
				bArray = A1;
			if (i == 2)
				bArray = A2;
			if (i == 3)
				bArray = A3;
			if (i == 4)
				bArray = A4;
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] instanceof Cover) {
					JButton b = new JButton();
					// b.setOpaque(false);
					b.setBackground(new Color(0, 0, 0, 128));
					b.setOpaque(true);
					b.setBorder(BorderFactory.createLineBorder(Color.white, 2));
					// b.setContentAreaFilled(false);
					// b.setFocusPainted(false);
					// b.setOpacity(0.5);
					b.repaint();
					// b.setBorderPainted(false);
					ImageIcon img = new ImageIcon("cover.png");
					b.setIcon(img);
					view5.addThing(b);
					Cover cov = (Cover) board[i][j];
					b.setToolTipText("HP: " + cov.getCurrentHP());
					b.addActionListener(this);
					bArray.add(b);
				} else {
					if (board[i][j] instanceof Champion) {
						Champion c = (Champion) board[i][j];
						JButton b = new JButton();
						b.setBackground(new Color(0, 0, 0, 128));
						b.setOpaque(true);
						b.setBorder(BorderFactory.createLineBorder(Color.white, 2));
						// b.setOpaque(false);
						// b.setContentAreaFilled(false);
						// b.setBorderPainted(false);
						b.setHorizontalTextPosition(JButton.CENTER);
						ImageIcon img = new ImageIcon(c.getName() + "3.png");
						b.setIcon(img);
						view5.addThing(b);
						b.addActionListener(this);
						bArray.add(b);
					} else {
						JButton b = new JButton();
						b.setBackground(new Color(0, 0, 0, 128));
						b.setOpaque(true);
						b.setBorder(BorderFactory.createLineBorder(Color.white, 2));

						// b.setBackground(Color.white);
						// b.setForeground(Color.white);
						view5.addThing(b);
						b.addActionListener(this);

						bArray.add(b);
					}
				}

			}
			// System.out.println();
		}
		String img = game.getCurrentChampion().getName() + "2.png";
		ImageIcon image = new ImageIcon(img);
		current = new JButton();
		current.setIcon(image);
		current.setOpaque(false);
		current.setContentAreaFilled(false);
		current.setBorderPainted(false);
		current.addActionListener(this);
		view5.getPanel6().add(current);
		for (int i = 0; i < game.getCurrentChampion().getAbilities().size(); i++) {
			JButton b = new JButton();
			b.setText(game.getCurrentChampion().getAbilities().get(i).getName());
			b.addActionListener(this);
			abilities.add(b);
			view5.getPanel9().add(b);

		}
		for (int i = 0; i < p1.getTeam().size(); i++) {
			JButton b = new JButton();
			b.setText(p1.getTeam().get(i).getName());
			// ImageIcon img3 = new ImageIcon(p1.getTeam().get(i).getName() + "4.png");
			// b.setIcon(img3);
			b.setForeground(Color.white);
			b.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 20));
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			b.addActionListener(this);
			view5.add1(b);
			buttons1.add(b);
		}
		if (game.isFirstLeaderAbilityUsed() == false) {
			Leader1 = new JButton();
			Leader1.setOpaque(false);
			Leader1.setContentAreaFilled(false);
			Leader1.setBorderPainted(false);
			Leader1.setText("Use Leader Ability1");
			Leader1.setForeground(Color.white);
			Leader1.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 20));
			Leader1.addActionListener(this);
			view5.add1(Leader1);
		}

		for (int i = 0; i < p2.getTeam().size(); i++) {
			JButton b = new JButton();
			b.setText(p2.getTeam().get(i).getName());
			b.setForeground(Color.black);
			b.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 20));
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			// b.setIcon(img3);
			b.addActionListener(this);
			view5.add2(b);
			buttons2.add(b);
		}
		if (game.isSecondLeaderAbilityUsed() == false) {
			Leader2 = new JButton();
			Leader2.setOpaque(false);
			Leader2.setContentAreaFilled(false);
			Leader2.setBorderPainted(false);
			Leader2.setText("Use Leader Ability2");
			Leader2.setForeground(Color.black);
			Leader2.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 20));
			Leader2.addActionListener(this);
			view5.add2(Leader2);
		}

		// l.setSize(new Dimension(100, 700));
		// l.add(current);

		move = new JButton("      Move      ");
		attack = new JButton("     Attack     ");
		view5.getPanel9().add(move);
		view5.getPanel9().add(attack);

		// ability=new JButton("ability");
		// view5.moves(move);
		// view5.attack(attack);
		// view5.ability(ability);
		move.addActionListener(this);
		attack.addActionListener(this);
		// ability.addActionListener(this);
		String turn = "<html>" + "Next:" + "<br>";
		ArrayList<Champion> temp = new ArrayList<>();
		temp.add((Champion) game.getTurnOrder().remove());// update
		while (!game.getTurnOrder().isEmpty()) {
			Champion champ = (Champion) game.getTurnOrder().peekMin();
			// JButton t=new JButton(champ.getName());
			turn += champ.getName() + "<br>";
			temp.add((Champion) game.getTurnOrder().remove());
		}
		turn += "<html>" + "<html>";
		JLabel turnOrder = new JLabel();
		turnOrder.setText(turn);
		turnOrder.setFont(new Font("Avengeance Heroic Avenger", Font.BOLD, 25));
		turnOrder.setForeground(Color.white);

		view5.getPanel10().add(turnOrder);
		for (int i = 0; i < temp.size(); i++) {
			game.getTurnOrder().insert(temp.get(i));
		}
		end = new JButton("   End Turn   ");
		end.addActionListener(this);
		view5.getPanel9().add(end);
		view5.repaint();
		view5.validate();
		// view5.panel5.requestFocus();
		// view5.panel5.setFocusable(true);
		// view5.panel5.setRequestFocusEnabled(true);
		// view5.panel5.requestFocusInWindow();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		key = new KeyBinding();
		if (!SingleTarget) {
			int x = 0, y = 0;
			if (A0.indexOf(button) != -1) {
				x = 0;
				y = A0.indexOf(button);
			}
			if (A1.indexOf(button) != -1) {
				x = 1;
				y = A1.indexOf(button);
			}
			if (A2.indexOf(button) != -1) {
				x = 2;
				y = A2.indexOf(button);
			}
			if (A3.indexOf(button) != -1) {
				x = 3;
				y = A3.indexOf(button);
			}
			if (A4.indexOf(button) != -1) {
				x = 4;
				y = A4.indexOf(button);
			}
			System.out.println(x + "  " + y);
			Object board[][] = game.getBoard();
			if (board[x][y] instanceof Champion) {
				new Current2((Champion) board[x][y]);
			}
		}
		if (SingleTarget) {
			System.out.println("single");
			int x = 0, y = 0;
			if (A0.indexOf(button) != -1) {
				x = 0;
				y = A0.indexOf(button);
			}
			if (A1.indexOf(button) != -1) {
				x = 1;
				y = A1.indexOf(button);
			}
			if (A2.indexOf(button) != -1) {
				x = 2;
				y = A2.indexOf(button);
			}
			if (A3.indexOf(button) != -1) {
				x = 3;
				y = A3.indexOf(button);
			}
			if (A4.indexOf(button) != -1) {
				x = 4;
				y = A4.indexOf(button);
			}
			System.out.println(x + "  " + y);
			try {
				game.castAbility(st, x, y);
				view5.play("Nani (Original).wav");
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (AbilityUseException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (InvalidTargetException e3) {
				JOptionPane.showMessageDialog(null, e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (CloneNotSupportedException e4) {
				JOptionPane.showMessageDialog(null, e4.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			SingleTarget = false;
			refresh();
			refresh2();
			refresh3();
			return;
		}
		if (button == move) {
			Move = true;
			System.out.println("was");
			key.addKeyListener(this);
			key.setVisible(true);
		}
		if (button == attack) {
			Attack = true;
			key.addKeyListener(this);
			key.setVisible(true);
		}
		if (abilities.indexOf(button) != -1) {
			int index = abilities.indexOf(button);
			useAbility(index);
			return;
		}
		if (buttons1.indexOf(button) == -1 && buttons2.indexOf(button) == -1) {
			if (e.getSource() == end) {
				game.endTurn();
				System.out.println("end");
				refresh2();
			}
			if (e.getSource() == current) {
				new Current(game.getCurrentChampion());
			}
			if (e.getSource() == Leader1) {
				try {
					if (game.getFirstPlayer().getTeam().contains(game.getCurrentChampion())) {
						game.useLeaderAbility();
					}
				} catch (LeaderNotCurrentException el) {
					JOptionPane.showMessageDialog(null, "The current champion is not a leader", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (LeaderAbilityAlreadyUsedException el) {
					JOptionPane.showMessageDialog(null, "This leader already used his ability", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				refresh();
				refresh2();
				refresh3();
			}
			if (e.getSource() == Leader2) {
				try {
					if (game.getSecondPlayer().getTeam().contains(game.getCurrentChampion())) {
						game.useLeaderAbility();
					}
				} catch (LeaderNotCurrentException el) {
					JOptionPane.showMessageDialog(null, "The current champion is not a leader", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (LeaderAbilityAlreadyUsedException el) {
					JOptionPane.showMessageDialog(null, "This leader already used his ability", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				refresh();
				refresh2();
				refresh3();
			}
		} else {
			System.out.println(buttons2.indexOf(button));
			if (buttons2.indexOf(button) != -1) {
				int index = buttons2.indexOf(button);
				Champion ch = p2.getTeam().get(index);
				boolean flag = (p2.getLeader() == ch);
				System.out.println(index);
				new ViewP1(ch, flag);
			} else {
				if (buttons1.indexOf(button) != -1) {
					int index = buttons1.indexOf(button);
					Champion ch = p1.getTeam().get(index);
					boolean flag = (p1.getLeader() == ch);
					System.out.println(index);
					new ViewP1(ch, flag);
				}
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		/*
		 * if ((e.getKeyCode() == 37 || e.getKeyCode() == 38 || e.getKeyCode() == 39 ||
		 * e.getKeyCode() == 40 || e.getKeyChar() == 'q' || e.getKeyChar() == '1' ||
		 * e.getKeyChar() == '2' || e.getKeyChar() == '3' || e .getKeyChar() == '4' &&
		 * key.isActive()) && p2.getTeam().contains(game.getCurrentChampion())) {
		 * key.dispose(); switch (e.getKeyChar()) { case 38: try {
		 * game.move(Direction.UP); } catch (NotEnoughResourcesException e1) {
		 * JOptionPane.showMessageDialog(null, "Not Enough Resources Exception",
		 * "Error", JOptionPane.ERROR_MESSAGE); } catch (UnallowedMovementException e1)
		 * { JOptionPane.showMessageDialog(null, "Unallowed Movement Exception",
		 * "Error", JOptionPane.ERROR_MESSAGE); } refresh(); refresh2(); refresh3();
		 * break; case 39: try { game.move(Direction.RIGHT); } catch
		 * (NotEnoughResourcesException e1) { JOptionPane.showMessageDialog(null,
		 * "Not Enough Resources Exception", "Error", JOptionPane.ERROR_MESSAGE); }
		 * catch (UnallowedMovementException e1) { JOptionPane.showMessageDialog(null,
		 * "Unallowed Movement Exception", "Error", JOptionPane.ERROR_MESSAGE); }
		 * refresh(); refresh2(); refresh3(); break; case 37: try {
		 * game.move(Direction.LEFT); } catch (NotEnoughResourcesException e1) {
		 * JOptionPane.showMessageDialog(null, "Not Enough Resources Exception",
		 * "Error", JOptionPane.ERROR_MESSAGE); } catch (UnallowedMovementException e1)
		 * { JOptionPane.showMessageDialog(null, "Unallowed Movement Exception",
		 * "Error", JOptionPane.ERROR_MESSAGE); } refresh(); refresh2(); refresh3();
		 * break; case 40: try { game.move(Direction.DOWN); } catch
		 * (NotEnoughResourcesException e1) { JOptionPane.showMessageDialog(null,
		 * "Not Enough Resources Exception", "Error", JOptionPane.ERROR_MESSAGE); }
		 * catch (UnallowedMovementException e1) { JOptionPane.showMessageDialog(null,
		 * "Unallowed Movement Exception", "Error", JOptionPane.ERROR_MESSAGE); }
		 * refresh(); refresh2(); refresh3(); break; case 'q': String[] options = new
		 * String[] { "UP", "DOWN", "LEFT", "RIGHT" }; int response =
		 * JOptionPane.showOptionDialog(null, "Choose your direction", "Attack",
		 * JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
		 * options[0]); Direction d = null; switch (response) { case 0: d =
		 * Direction.UP; break; case 1: d = Direction.DOWN; break; case 2: d =
		 * Direction.LEFT; break; case 3: d = Direction.RIGHT; break; } try {
		 * game.attack(d); } catch (NotEnoughResourcesException e1) {
		 * JOptionPane.showMessageDialog(null, "Not Enough Resources Exception",
		 * "Error", JOptionPane.ERROR_MESSAGE); } catch (ChampionDisarmedException e2) {
		 * JOptionPane.showMessageDialog(null, "Champion is Disarmed", "Error",
		 * JOptionPane.ERROR_MESSAGE); } catch (InvalidTargetException e3) {
		 * JOptionPane.showMessageDialog(null, "Invalid Target", "Error",
		 * JOptionPane.ERROR_MESSAGE); } refresh(); refresh2(); refresh3(); break; case
		 * '1': useAbility(0); break; case '2': useAbility(1); break; case '3':
		 * useAbility(2); break; case '4': Champion curr = game.getCurrentChampion();
		 * ArrayList<Ability> ab = curr.getAbilities(); try {
		 * System.out.println("here"); Ability t=ab.get(3); useAbility(3); }
		 * catch(NullPointerException ee) { System.out.println("t"); }
		 * catch(IndexOutOfBoundsException eee) { System.out.println("t"); } break; /*
		 * case 'l': try { game.useLeaderAbility(); } catch (LeaderNotCurrentException
		 * e1) { JOptionPane.showMessageDialog(null,
		 * "Current Chapion is not the leader!", "Error", JOptionPane.ERROR_MESSAGE); }
		 * catch (LeaderAbilityAlreadyUsedException e1) {
		 * JOptionPane.showMessageDialog(null, "Already Used Leader Ability", "Error",
		 * JOptionPane.ERROR_MESSAGE); } break;
		 * 
		 * }
		 * 
		 * }
		 */
	}

	private void useAbility(int x) {
		Champion curr = game.getCurrentChampion();
		ArrayList<Ability> ab = curr.getAbilities();
		Ability ab1 = ab.get(x);
		AreaOfEffect area = ab1.getCastArea();
		boolean flag = false;
		switch (area) {
		case DIRECTIONAL:
			String[] option = new String[] { "UP", "DOWN", "LEFT", "RIGHT" };
			int responses = JOptionPane.showOptionDialog(null, "Choose your direction", "Abilty",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
			Direction di = null;
			switch (responses) {
			case 0:
				di = Direction.UP;
				break;
			case 1:
				di = Direction.DOWN;
				break;
			case 2:
				di = Direction.LEFT;
				break;
			case 3:
				di = Direction.RIGHT;
				break;
			}
			try {
				game.castAbility(ab1, di);
				flag = true;
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Resources Exception", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (CloneNotSupportedException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (AbilityUseException e1) {
				JOptionPane.showMessageDialog(null, "Ability Use Exception", "Error", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case SINGLETARGET:
			JOptionPane.showMessageDialog(null, "Choose which cell you want to cast ability on");
			this.SingleTarget = true;
			st = ab1;
			return;
		default:
			try {
				game.castAbility(ab1);
				flag = true;
				System.out.println("was here");
			} catch (NotEnoughResourcesException e1) {
				JOptionPane.showMessageDialog(null, "Not Enough Resources Exception", "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (CloneNotSupportedException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (AbilityUseException e1) {
				JOptionPane.showMessageDialog(null, "Ability Use Exception", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (ab1 instanceof HealingAbility || (ab1 instanceof CrowdControlAbility
				&& ((CrowdControlAbility) ab1).getEffect().getType() == EffectType.BUFF) && flag)
			view5.play("meme -OH MY GOD.wav");
		else if (flag)
			view5.play("Nani (Original).wav");
		refresh();
		refresh2();
		refresh3();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == 37 || e.getKeyCode() == 38 || e.getKeyCode() == 39 || e.getKeyCode() == 40)
				&& key.isActive()) {
			key.dispose();
			if (Move) {
				switch (e.getKeyCode()) {
				case 37:
					try {
						game.move(Direction.LEFT);
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "Not Enough Resources Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (UnallowedMovementException e1) {
						JOptionPane.showMessageDialog(null, "Unallowed Movement Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					refresh();
					refresh2();
					refresh3();
					break;
				case 38:
					try {
						game.move(Direction.UP);
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "Not Enough Resources Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (UnallowedMovementException e1) {
						JOptionPane.showMessageDialog(null, "Unallowed Movement Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					refresh();
					refresh2();
					refresh3();
					break;
				case 39:
					try {
						game.move(Direction.RIGHT);
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "Not Enough Resources Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (UnallowedMovementException e1) {
						JOptionPane.showMessageDialog(null, "Unallowed Movement Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					refresh();
					refresh2();
					refresh3();
					break;
				case 40:
					try {
						game.move(Direction.DOWN);
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "Not Enough Resources Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (UnallowedMovementException e1) {
						JOptionPane.showMessageDialog(null, "Unallowed Movement Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					refresh();
					refresh2();
					refresh3();
					break;
				}
				Move = false;
			}

			if (Attack) {
				boolean flag = false;
				switch (e.getKeyCode()) {
				case 37:
					try {
						game.attack(Direction.LEFT);
						flag = true;
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "Not Enough Resources Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (ChampionDisarmedException e1) {
						JOptionPane.showMessageDialog(null, "Champion Disarmed", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidTargetException e1) {
						JOptionPane.showMessageDialog(null, "Invalid Target", "Error", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case 38:
					try {
						game.attack(Direction.UP);
						flag = true;
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "Not Enough Resources Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (ChampionDisarmedException e1) {
						JOptionPane.showMessageDialog(null, "Champion Disarmed", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidTargetException e1) {
						JOptionPane.showMessageDialog(null, "Invalid Target", "Error", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case 39:
					try {
						game.attack(Direction.RIGHT);
						flag = true;
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "Not Enough Resources Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (ChampionDisarmedException e1) {
						JOptionPane.showMessageDialog(null, "Champion Disarmed", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidTargetException e1) {
						JOptionPane.showMessageDialog(null, "Invalid Target", "Error", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case 40:
					try {
						game.attack(Direction.DOWN);
						flag = true;
					} catch (NotEnoughResourcesException e1) {
						JOptionPane.showMessageDialog(null, "Not Enough Resources Exception", "Error",
								JOptionPane.ERROR_MESSAGE);
					} catch (ChampionDisarmedException e1) {
						JOptionPane.showMessageDialog(null, "Champion Disarmed", "Error", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidTargetException e1) {
						JOptionPane.showMessageDialog(null, "Invalid Target", "Error", JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				Attack = false;
				if (flag)
					view5.play("Nani (Original).wav");
				refresh();
				refresh2();
				refresh3();
			}
		}
	}

}