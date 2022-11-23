package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class KeyBinding extends JFrame{
	private JLabel label;
	private JPanel panel;
	public KeyBinding() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		label=new JLabel("PRESS A BUTTON!");
		label.setFont(new Font ("MV Boli",Font.PLAIN,30));
		panel=new JPanel();
		panel.add(label);
		add(panel);
		setLocationRelativeTo(null);
		this.pack();
	}
}