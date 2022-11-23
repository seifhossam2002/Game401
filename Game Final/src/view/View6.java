package view;

import java.io.File;
import java.awt.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class View6 extends JFrame {
	private JLabel label;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JLabel text;
	private JPanel winners;
	private JLayeredPane layeredpane;

	public View6(String s) {
		layeredpane = new JLayeredPane();
		layeredpane.setBounds(0, 0, 1800, 1000);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Marvel");
		setBackground(Color.black);
		b1 = new JButton();
		ImageIcon black = new ImageIcon("Blackcard.png");
		b1.setIcon(black);
		b1.setBounds(400, 500, 400, 400);
		b1.setOpaque(false);
		b1.setBackground(null);
		b1.setContentAreaFilled(false);
		b1.setBorderPainted(false);
		b2 = new JButton();
		b2.setIcon(black);
		b2.setBounds(700, 500, 400, 400);
		b2.setOpaque(false);
		b2.setBackground(null);
		b2.setContentAreaFilled(false);
		b2.setBorderPainted(false);
		b3 = new JButton();
		b3.setIcon(black);
		b3.setBounds(1000, 500, 400, 400);
		b3.setOpaque(false);
		b3.setBackground(null);
		b3.setContentAreaFilled(false);
		b3.setBorderPainted(false);

		// setLayout(new BorderLayout());
		ImageIcon image1 = new ImageIcon("Logo.png");
		setIconImage(image1.getImage());
		setLayout(null);
		ImageIcon vic = new ImageIcon("end.jpg");
		label = new JLabel();
		label.setBackground(Color.red);
		label.setIcon(vic);
		label.setBounds(0, 0, 1800, 1000);
		label.setOpaque(true);
		layeredpane.add(label, Integer.valueOf(0));
		layeredpane.add(b1, Integer.valueOf(1));
		layeredpane.add(b2, Integer.valueOf(1));
		layeredpane.add(b3, Integer.valueOf(1));

		this.add(layeredpane);// ,BorderLayout.NORTH);
		/*
		 * p=new JPanel(); p.setBounds(0,400,1800,642); p.setLayout(new
		 * FlowLayout(FlowLayout.CENTER, 50 , 100)); p.setBackground(Color.red);
		 * p.setOpaque(true); this.add(p);
		 */
		setVisible(true);
	}

	public JLabel getLabel1() {
		return text;
	}

	public void addLabel() {
		text = new JLabel();
		text.setText("E X I T");
		text.setBounds(850, 850, 120, 76);
		text.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 50));
		text.setForeground(Color.white);
		// text.setBackground(Color.black);
		// text.setOpaque(true);
		text.setHorizontalTextPosition(JLabel.CENTER);
		text.setVerticalTextPosition(JLabel.CENTER);
		layeredpane.add(text, Integer.valueOf(1));
	}

	public static void main(String[] args) {
		View6 v = new View6("S");
	}

	public void add1(JButton b) {

		b.setIcon(null);
		// b.setBounds()

	}

	public JLabel getLabel() {
		return text;
	}

	public JButton getB1() {
		return b1;
	}

	public JButton getB2() {
		return b2;
	}

	public JButton getB3() {
		return b3;
	}

	public JPanel getWinners() {
		return winners;
	}

	private File file;
	private Clip clip;

	public void play(String path) {
		try {
			file = new File(path);
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
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
}