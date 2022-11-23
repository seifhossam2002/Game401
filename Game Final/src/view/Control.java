package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.world.Champion;
import engine.Game;
import engine.Player;

public class Control implements ActionListener, MouseListener {
	private static Game game;
	private static Main view;
	private static View2 view2;
	private JButton start;
	private static Object name1;
	private static Object name2;

	public Control() {
		new loading();
		view = new Main();
		start = new JButton();
		start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		//ImageIcon img = new ImageIcon("Start.png");
		//start.setIcon(img);
		start.setText("Start");
		start.setForeground(Color.white);
		start.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 40));
		start.setBounds(731, 460, 350, 500);
		start.addActionListener(this);
		start.addMouseListener(this);
		start.setFocusable(false);
		view.addButton(start);

	}

	public static void main(String[] args) {
		new Control();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			try {
				startgame();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public static void startgame() throws IOException {
		ImageIcon image = new ImageIcon("lol.png");
		name1 = "";

		while (name1 != null && name1.toString().length() == 0) {
			name1 = JOptionPane.showInputDialog(null, "Choose your username",
					"FirstPlayer", JOptionPane.PLAIN_MESSAGE, image, null,
					"Player 1");
			if (name1 == null || (name1 != null && ("".equals(name1))))
				name1 = "Player 1";

		}

		name2 = "";
		while (name2 != null && name2.toString().length() == 0) {
			name2 = JOptionPane.showInputDialog(null, "Choose your username",
					"SecondPlayer", JOptionPane.PLAIN_MESSAGE, image, null,
					"Player 2");
			if (name2 == null || (name2 != null && ("".equals(name2))))
				name2 = "Player 2";
		}
		game = new Game(new Player((String) name1), new Player((String) name2));
		try {
			game.loadAbilities("Abilities.csv");
		} catch (IOException e) {
			System.out.println("Abilities file not found");
		}
		try {
			game.loadChampions("Champions.csv");
		} catch (IOException e) {
			System.out.println("Abilities file not found");
		}
		view.setVisible(false);
		view.pause();
		new Control2(game, (String) name1, (String) name2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		start.setBounds(731, 460, 350, 300);
		start.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 80));

	}

	@Override
	public void mouseExited(MouseEvent e) {
		start.setBounds(731, 460, 350, 300);
		start.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 40));

	}

}