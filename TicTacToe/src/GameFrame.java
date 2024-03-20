import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{

	GameFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,800);
		getContentPane().setBackground(new Color(50,50,50));
		setLayout(new BorderLayout());
		setVisible(true);
	}
}
