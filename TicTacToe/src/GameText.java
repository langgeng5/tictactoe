import java.awt.Font;

import javax.swing.JLabel;

public class GameText extends JLabel {

	GameText(){
		setFont(new Font("MV Boli", Font.BOLD, 15));
	}
	
	GameText(String text){
		setText(text);
		setFont(new Font("MV Boli", Font.BOLD, 15));
	}
	
	GameText(String text, int horizontalAlignment){
		setText(text);
		setFont(new Font("MV Boli", Font.BOLD, 15));
		setHorizontalAlignment(horizontalAlignment);
	}
}
