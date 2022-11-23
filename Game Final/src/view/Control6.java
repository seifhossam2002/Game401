package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import engine.*;
import model.world.Champion;

public class Control6 implements MouseListener {
	private Game game;
	private Player p1;
	private Player p2;
	private ArrayList<Champion> a;
	private ArrayList<Champion> b;
	private View6 v6;
	private ArrayList<JButton> x;
	private JLabel exit;

	public Control6(Game game, Player p1, Player p2, ArrayList<Champion> a, ArrayList<Champion> b) {
		this.a = a;
		this.b = b;
		this.game = game;
		this.p1 = p1;
		this.p2 = p2;
		if (game.checkGameOver() == p1) {
			v6 = new View6(p1.getName());
			// v6.play("end.wav");
			ImageIcon a1 = new ImageIcon(a.get(0).getName() + ".png");
			ImageIcon a2 = new ImageIcon(a.get(1).getName() + ".png");
			ImageIcon a3 = new ImageIcon(a.get(2).getName() + ".png");
			v6.getB1().setIcon(a1);
			v6.getB2().setIcon(a2);
			v6.getB3().setIcon(a3);
		} else {
			v6 = new View6(p2.getName());
			ImageIcon a1 = new ImageIcon(b.get(0).getName() + ".png");
			ImageIcon a2 = new ImageIcon(b.get(1).getName() + ".png");
			ImageIcon a3 = new ImageIcon(b.get(2).getName() + ".png");
			v6.getB1().setIcon(a1);
			v6.getB2().setIcon(a2);
			v6.getB3().setIcon(a3);
		}

		v6.addLabel();
		exit = v6.getLabel();
		exit.addMouseListener(this);

	}
	/*
	 * public static void main (String[]args) { new Control6(null,null,null); }
	 */

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exit) {
			v6.setVisible(false);
			System.exit(0);

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}