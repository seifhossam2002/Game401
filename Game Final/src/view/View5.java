package view;

import java.awt.*;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import engine.Player;

public class View5 extends JFrame {

	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel5;
	private JPanel p1;
	private JPanel p2;
	private String pl1;
	private String pl2;
	private JPanel panel10;
	private JPanel panel9;
	private JPanel panel6;
	private JLabel la;
	private File file;
	private Clip clip;
	private File file1;
	private Clip clip1;

	public View5(String pl1, String pl2) {
		JPanel big = new JPanel();
		big.setBounds(0,0,1800,950);
		big.setLayout(new BorderLayout());
		big.setOpaque(false);
		
		
		ImageIcon black = new ImageIcon("game.png");
		
		la = new JLabel();
		la.setBounds(0,0,1800,1000);
		la.setIcon(black);
		
		
		
		JLayeredPane layeredpane= new JLayeredPane();
		layeredpane.setBounds(0,0,1800,1100);
		layeredpane.add(la, Integer.valueOf(0));
		
		this.pl1 = pl1;
		this.pl2 = pl2;
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// this.getContentPane().setBackground(new Color(90, 60, 70));
		this.setTitle("Marvel");
		ImageIcon image1 = new ImageIcon("Logo.png");
		setIconImage(image1.getImage());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(10, 10));

		panel2 = new JPanel();
		panel3 = new JPanel();
		panel5 = new JPanel();

		panel2.setOpaque(false);
		panel3.setOpaque(false);
		getPanel5().setOpaque(false);

		panel2.setPreferredSize(new Dimension(200, 100));
		panel3.setPreferredSize(new Dimension(200, 100));
		getPanel5().setPreferredSize(new Dimension(100, 500));
		
		getPanel5().setLayout(new GridLayout(5, 5));
		getPanel5().setOpaque(false);
		panel3.setLayout(new FlowLayout());
		p1 = new JPanel();
		p2 = new JPanel();

		panel2.setLayout(new GridLayout(2, 1));
		getP1().setOpaque(false);
		getP1().setLayout(new FlowLayout(FlowLayout.CENTER, 50, 35));
		getP2().setOpaque(false);
		getP2().setLayout(new FlowLayout(FlowLayout.CENTER, 50, 35));
		JLabel label1 = new JLabel();
		String out = "<html>" + "Player 1: " + pl1 + "<br>" + "<br>" + "<html>";
		label1.setText(out);
		label1.setBounds(0,0,250,250);
		label1.setFont(new Font("Copperplate", Font.PLAIN, 20));
		label1.setForeground(Color.white);
		label1.setHorizontalTextPosition(JLabel.CENTER);
		 label1.setVerticalTextPosition(JLabel.TOP);
		getP1().add(label1);
		JLabel label2 = new JLabel();
		out = "<html>" + "Player 2: " + pl2 + "<br>" + "<br>" + "<html>";
		label2.setBounds(0,0,250,250);
		label2.setText(out);
		
		label2.setFont(new Font("Copperplate", Font.PLAIN, 20));
		label2.setHorizontalTextPosition(JLabel.CENTER);
		label2.setForeground(Color.black);
		getP2().add(label2);
		panel2.add(getP1());
		panel2.add(getP2());
		getPanel5().requestFocus();
		getPanel5().setFocusable(true);
		getPanel5().setRequestFocusEnabled(true);
		getPanel5().setOpaque(false);
		getPanel5().requestFocusInWindow();
		//add(a, BorderLayout.WEST);
		//add(b, BorderLayout.EAST);
		big.add(panel2, BorderLayout.WEST);
		big.add(panel3, BorderLayout.EAST);

		big.add(getPanel5(), BorderLayout.CENTER);
		layeredpane.add(big, Integer.valueOf(1));
		add(layeredpane);
		getPanel5().grabFocus();
		panel3.setLayout(new GridLayout(3, 1));
		panel6 = new JPanel();
		panel9 = new JPanel();
		panel10 = new JPanel();
		getPanel6().setOpaque(false);
		getPanel9().setOpaque(false);
		getPanel10().setOpaque(false);

		panel3.add(getPanel6());
		panel3.add(getPanel9());
		panel3.add(getPanel10());

	}

	public void addThing(JButton button) {
		getPanel5().add(button);
	}

	public void addEnd(JButton button) {
		panel3.add(button);
	}

	public void add1(JButton button) {
		getP1().add(button);

	}

	public void add2(JButton button) {
		getP2().add(button);

	}

	public void moves(JButton b) {
		panel3.add(b);
	}

	public void attack(JButton b) {
		panel3.add(b);
	}

	public void ability(JButton b) {
		panel3.add(b);
	}

	public void setname1() {
		JLabel label1 = new JLabel();
		String out = "<html>" + "Player 1: " + pl1 + "<br>" + "<br>" + "<html>";
		label1.setText(out);
		label1.setFont(new Font("Copperplate", Font.ITALIC, 20));
		label1.setHorizontalTextPosition(JLabel.CENTER);
		label1.setForeground(Color.white);
		// label1.setVerticalTextPosition(JLabel.TOP);
		getP1().add(label1);
	}

	public void setname2() {
		JLabel label2 = new JLabel();
		String out = "<html>" + "Player 2: " + pl2 + "<br>" + "<br>" + "<html>";
		label2.setText(out);
		label2.setFont(new Font("Copperplate", Font.ITALIC, 20));
		label2.setHorizontalTextPosition(JLabel.CENTER);
		label2.setForeground(Color.black);
		// label1.setVerticalTextPosition(JLabel.TOP);
		getP2().add(label2);
	}

	public void turn(JButton b) {
		panel3.add(b, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new View5("jj", "pp");
	}

	public void play(String path) {
		try {
			file = new File(path);
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			// clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			System.err.println(
					"Put the music.wav file in the sound folder if you want to play background music, only optional!");
		}
	}
	
	public void play1(String path) {
		try {
			file1 = new File(path);
			clip1 = AudioSystem.getClip();
			clip1.open(AudioSystem.getAudioInputStream(file));
			clip1.loop(Clip.LOOP_CONTINUOUSLY);
			clip1.start();
		} catch (Exception e) {
			System.err.println(
					"Put the music.wav file in the sound folder if you want to play background music, only optional!");
		}
	}
	
	public void pause() {
		try {
			clip.stop();
		} catch (Exception e) {
			System.err.println(
					"Put the music.wav file in the sound folder if you want to play background music, only optional!");
		}
	}

	public JPanel getPanel6() {
		return panel6;
	}

	public JPanel getPanel9() {
		return panel9;
	}

	public JPanel getPanel10() {
		return panel10;
	}

	public JPanel getP1() {
		return p1;
	}

	public JPanel getP2() {
		return p2;
	}

	public JPanel getPanel5() {
		return panel5;
	}

}