package view;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.awt.*;
import java.io.File;

public class View2 extends JFrame {
	private JLayeredPane layeredpane = new JLayeredPane();
	private JLabel label = new JLabel();
	private JLabel label1 = new JLabel();
	private JLabel label2 = new JLabel();
	private JLabel label3 = new JLabel();
	private JLabel label4 = new JLabel();
	private JLabel label5 = new JLabel();
	private JLabel label6 = new JLabel();
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private File file;
	private Clip clip;

	public View2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		panel1.setBounds(0, 0, 1792, 100);
		ImageIcon img = new ImageIcon("PickChamps.png");
		label.setIcon(img);
		label.setText("First Player Please Choose three Champions");
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setFont(new Font("Avengeance Heroic Avenger", Font.PLAIN, 30));
		panel1.add(label);
		// panel1.setBackground(Color.black);
		add(panel1);
		panel2.setBounds(0, 80, 1900, 400);
//s panel2.setBackground(Color.red);
		ImageIcon black = new ImageIcon("BlackCard.png");
		panel2.setLayout(null);
		// panel2.setBackground(Color.black);
		layeredpane.setBounds(0, 0, 1900, 400);
		label1.setIcon(black);
		label1.setBounds(0, 50, 229, 320); // first champ team 1
		label2.setBounds(225, 50, 229, 320);
		label2.setIcon(black); // second champ team 1
		label3.setBounds(450, 50, 229, 320); // third champ team 1
		label3.setIcon(black);
		layeredpane.setBackground(Color.black);
		label4.setIcon(black);
		label4.setBounds(1070, 50, 229, 320); // first champ team 2
		label5.setIcon(black);
		label5.setBounds(1295, 50, 229, 320); // second champ team 2
		label6.setIcon(black);
		label6.setBounds(1520, 50, 229, 320); // third champ team 2

		panel1.setBackground(Color.black);
		panel2.setBackground(Color.black);
		panel3.setLayout(new GridLayout(2, 8));
		panel3.setBackground(Color.black);
		panel3.setBounds(0, 471, 1792, 550);

		/*
		 * JLabel label7 =new JLabel(); ImageIcon i= new
		 * ImageIcon("Captain America.png"); label7.setIcon(i);
		 * label7.setBounds(50,30,202,202); JLabel label8 =new JLabel();
		 * label8.setIcon(i); label8.setBounds(50,240,202,202); JLabel label9 =new
		 * JLabel(); label9.setIcon(i); label9.setBounds(250,30,202,202);
		 * panel3.add(label7); panel3.add(label8); panel3.add(label9);
		 */

		ImageIcon image = new ImageIcon("VS.png");
		JLabel background = new JLabel("", image, JLabel.CENTER);
		background.setBounds(0, 0, 1800, 440);
		// background.setOpaque(true);
		layeredpane.add(background, Integer.valueOf(0));
		layeredpane.add(label1, Integer.valueOf(1));
		layeredpane.add(label2, Integer.valueOf(1));
		layeredpane.add(label3, Integer.valueOf(1));
		layeredpane.add(label4, Integer.valueOf(1));
		layeredpane.add(label5, Integer.valueOf(1));
		layeredpane.add(label6, Integer.valueOf(1));
		panel2.add(layeredpane);

		add(panel2);
		add(panel3);

	}

	public void addChamp(JButton Champ) {
		panel3.add(Champ);
	}

	public static void main(String[] args) {
		new View2();
	}

	public void changeLabel() {
		label.setText("Second Player Please Choose three Champions");

	}
	
	public void changeLabel1() {
		label.setText("First Player Please Choose three Champions");

	}

	public JLabel getLabel() {
		return label;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public JLabel getLabel3() {
		return label3;
	}

	public JLabel getLabel4() {
		return label4;
	}

	public JLabel getLabel5() {
		return label5;
	}

	public JLabel getLabel6() {
		return label6;
	}

	public JPanel getPanel3() {
		return panel3;
	}

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