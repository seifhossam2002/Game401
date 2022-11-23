package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Main extends JFrame {
	// JMenuItem loaditem;
	// JMenuItem saveitem;
	// JMenuItem exititem;
	// JMenuItem controlsitem;
	// JMenuItem brightnessitem;
	// JMenuItem fontitem;
	private JPanel panel = new JPanel();
	private JPanel back = new JPanel();
//	private JButton start = new JButton();
	private ImageIcon saveicon;
	private JSlider slide;
	private JLabel background;
	private File file;
	private Clip clip;

	public Main() {

		this.setLayout(null);
		this.setTitle("Marvel");
		ImageIcon image1 = new ImageIcon("icon.png");
		setIconImage(image1.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ImageIcon image = new ImageIcon("start.jpg");
		background = new JLabel("", image, JLabel.CENTER);
		background.setBounds(0, 0, 1800, 1000);
		this.play(getName());
		this.add(background);
		this.play("SpongeBob SquarePants _ Theme Song (Arabic).wav");
		// panel.setBackground(Color.red);
		// panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,500));
		setVisible(true);

	}

	public void addButton(JButton b) {
		this.background.add(b);
	}

	public void play(String path) {
		try {
			file = new File(path);
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (Exception e) {
			System.err
					.println("Put the music.wav file in the sound folder if you want to play background music, only optional!");
		}
	}

	public void pause() {
		try {
			clip.stop();
		} catch (Exception e) {
			System.err
					.println("Put the music.wav file in the sound folder if you want to play background music, only optional!");
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}