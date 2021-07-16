package gamesrc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Userside extends JFrame{

	public Userside(){
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(200, 50,980, 700);
		ImageIcon icon = new ImageIcon("./bin/gamesrc/image/title.png");
		JLabel label = new JLabel(icon);

		label.setBounds(-21, 0,1015, getHeight()/7*6);
		JButton start = new JButton("S   T   A   R   T");
		start.setForeground(Color.white);
		start.setBackground(Color.black);
		start.setBorderPainted(false);
		start.setFocusPainted(false);
		start.setFont(new Font(null,Font.BOLD, 30));
		start.setBounds(-15,(getHeight()-100),1000,61);
		start.addActionListener(startAct);
		getContentPane().add(label);
		label.setVisible(true);
		add(start);

	}
	private ActionListener startAct = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			Userside2 us2 = new Userside2();
		}

	};

	public static void main(String[] args) {
		Userside us = new Userside();
		us.setVisible(true);

	}

}
