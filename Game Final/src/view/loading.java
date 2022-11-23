package view;
import java.awt.*;

import javax.swing.*;
public class loading extends JFrame{
private JLabel background;
public loading() {
	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//label=new JLabel();
	ImageIcon image = new ImageIcon("start.jpg");
	background = new JLabel("", image, JLabel.CENTER);
	background.setBounds(0, 0, 1800, 1000);
	add(background);
	setVisible(true);
	
}
}
