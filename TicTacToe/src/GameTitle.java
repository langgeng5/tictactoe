import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class GameTitle extends JLabel{

	GameTitle(){
		setBackground(new Color(25,25,25));
		setForeground(new Color(25,255,0));
		setFont(new Font("MV Boli", Font.BOLD, 50));
		setHorizontalAlignment(JLabel.CENTER);
		setText("TIC TAC TOE");
		setOpaque(true);
	}
}
