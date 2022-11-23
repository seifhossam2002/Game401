package view;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class View3 extends JFrame {
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JLabel label=new JLabel();
	private JLayeredPane layeredpane = new JLayeredPane() ;
	public View3(String name) {
		
		Container con = this.getContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle("Marvel");
		ImageIcon image1 = new ImageIcon("Logo.png");
		setIconImage(image1.getImage());
		
		ImageIcon image2 = new ImageIcon("Leader.jpg");
		panel1 = new JPanel();
		panel1.setBounds(0,-15,1800,500);
		label.setIcon(image2);
		panel1.setBackground(Color.BLACK);
		
		//panel1.setLayout(new FlowLayout(FlowLayout.CENTER,400,150));
		setLayout(null);
		setBackground(new Color(117,18,38));
		label.setText(name+" choose your leader!");
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setForeground(Color.black);
		label.setFont(new Font ("Avengeance Heroic Avenger",Font.BOLD,50));
		panel1.add(label,BorderLayout.CENTER);
	//	JOptionPane.showMessageDialog(null, "Choose your leader!!","Leader",JOptionPane.INFORMATION_MESSAGE);
		panel2=new JPanel();
		//panel2.setLayout(new FlowLayout(FlowLayout.CENTER,50,40));
		panel2.setBounds(400,550,900,500);
		JLabel pic = new JLabel();
		ImageIcon image3 = new ImageIcon("Marvel");
		pic.setIcon(image3);
		//layeredpane.add(pic);
		panel2.add(pic);
		
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER,50,40));
		panel2.setOpaque(false);
		//setBackground(new Color(117,18,38));
		
		
		
		panel3=new JPanel();
		panel3.setBounds(0,470,1800,600);
		JLabel icon = new JLabel();
		ImageIcon image = new ImageIcon("Marvel123.jpg");
		icon.setIcon(image);
		panel3.add(icon);
		con.add(panel1);
		con.add(panel2);
		con.add(panel3);
		this.repaint();
		this.revalidate();
	}
	public void setPanel() {
		panel2=new JPanel();
	}
	
	public void changeLabel(String name){
		label.setText(name+"'s leader");
	}
	public void addChamp(JButton button) {
		panel2.add(button);
	}
}