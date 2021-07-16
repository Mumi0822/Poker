package gamesrc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Userside2 extends JFrame{
	Gamemaster gm = new Gamemaster();

	JPanel p3 = new JPanel(new GridLayout(2,1));
	JLabel l1 = new JLabel();
	JLabel l2 = new JLabel();
	JLabel l3 = new JLabel();
	JLabel l4 = new JLabel();
	JLabel l5 = new JLabel();
	JLabel l6 = new JLabel();
	JLabel l7 = new JLabel();
	JLabel l8 = new JLabel();
	JLabel l9 = new JLabel();
	JLabel l10 = new JLabel();
	JLabel l11 = new JLabel();
	public int changenum = 0;
	JCheckBox[] cb = new JCheckBox[5];
	JButton change = new JButton("Change");
	JButton decide = new JButton("OK");
	private ActionListener changeAct = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			change();
		}
	};
	private ActionListener decideAct = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			gm.judge();
		}
	};
	private ActionListener okAct = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};



	public Userside2(){

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 50,1100, 700);
		setVisible(true);
		JPanel p1 = new JPanel(new FlowLayout());
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER,140,50));
		p1.setBackground(Color.black);
		p2.setBackground(Color.black);
		cb[0]= new JCheckBox("交換する");
		cb[1]= new JCheckBox("交換する");
		cb[2]= new JCheckBox("交換する");
		cb[3]= new JCheckBox("交換する");
		cb[4]= new JCheckBox("交換する");
		cb[0].setBackground(Color.black);
		cb[0].setForeground(Color.white);
		cb[1].setBackground(Color.black);
		cb[1].setForeground(Color.white);
		cb[2].setBackground(Color.black);
		cb[2].setForeground(Color.white);
		cb[3].setBackground(Color.black);
		cb[3].setForeground(Color.white);
		cb[4].setBackground(Color.black);
		cb[4].setForeground(Color.white);
		gm.make_deck();
		gm.hang(true);
		gm.hang(false);
		System.out.println("hang");
		display();
		gm.hands1=gm.sort(gm.hands1);
		gm.hands2=gm.sort(gm.hands2);
		System.out.println("sort");
		display();
		changenum = gm.autochange();
		System.out.println("autochange");
		display();
		System.out.println("card"+gm.hands1[0][2]+".png");
		System.out.println("card"+gm.hands1[1][2]+".png");
		System.out.println("card"+gm.hands1[2][2]+".png");
		System.out.println("card"+gm.hands1[3][2]+".png");
		System.out.println("card"+gm.hands1[4][2]+".png");

		change.addActionListener(changeAct);
		decide.addActionListener(decideAct);
		l1 = new JLabel(new ImageIcon("./bin/gamesrc/image/card"+gm.hands1[0][2]+".png"));
		l2 = new JLabel(new ImageIcon("./bin/gamesrc/image/card"+gm.hands1[1][2]+".png"));
		l3 = new JLabel(new ImageIcon("./bin/gamesrc/image/card"+gm.hands1[2][2]+".png"));
		l4 = new JLabel(new ImageIcon("./bin/gamesrc/image/card"+gm.hands1[3][2]+".png"));
		l5 = new JLabel(new ImageIcon("./bin/gamesrc/image/card"+gm.hands1[4][2]+".png"));
		l6.setText("相手は"+ changenum +"枚交換しました。");
		p1.add(l1);p1.add(l2);p1.add(l3);p1.add(l4);p1.add(l5);
		p2.add(cb[0]);p2.add(cb[1]);p2.add(cb[2]);p2.add(cb[3]);p2.add(cb[4]);
		p2.add(change);p2.add(l6);p2.add(decide);
		p3.add(p1);
		p3.add(p2);
		getContentPane().add(p3);
	}
	public void display(){
		System.out.println("===============");
		for(int i=0; i<5; i++){
			int n = i+1;
			System.out.println(n+"枚目："+gm.hands1[i][0]+","+gm.hands1[i][1]+","+gm.hands1[i][2]);
		}
		System.out.println("===============");
		for(int i=0; i<5; i++){
			int n = i+1;
			System.out.println(n+"枚目："+gm.hands2[i][0]+","+gm.hands2[i][1]+","+gm.hands2[i][2]);
		}
		System.out.println("===============");
	}
	public void change(){
		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,140,50));
		panel1.setBackground(Color.black);
		panel2.setBackground(Color.black);
		for(int i=0; i<cb.length; i++){
			if(cb[i].isSelected()){
				gm.change(true,i);
			}
		}
		gm.sort(gm.hands1);
		l1 = new JLabel(new ImageIcon("./bin/gamesrc/image/card"+gm.hands1[0][2]+".png"));
		l2 = new JLabel(new ImageIcon("./bin/gamesrc/image/card"+gm.hands1[1][2]+".png"));
		l3 = new JLabel(new ImageIcon("./bin/gamesrc/image/card"+gm.hands1[2][2]+".png"));
		l4 = new JLabel(new ImageIcon("./bin/gamesrc/image/card"+gm.hands1[3][2]+".png"));
		l5 = new JLabel(new ImageIcon("./bin/gamesrc/image/card"+gm.hands1[4][2]+".png"));

		getContentPane().removeAll();
		decide.addActionListener(okAct);
		panel1.removeAll();
		panel2.removeAll();
		p3.removeAll();
		panel1.add(l1);panel1.add(l2);panel1.add(l3);panel1.add(l4);panel1.add(l5);
		panel2.add(decide);
		p3.add(panel1);
		p3.add(panel2);
		getContentPane().add(p3);
		setVisible(true);
		display();

	}
	public void change2(){
	//	panel2.removeAll();
	//	panel2.setBackground(Color.black);
	}

}
