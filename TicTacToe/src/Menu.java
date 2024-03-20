import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu {

	GameFrame frame = new GameFrame();
	GameTitle gameTitle = new GameTitle();
	JPanel main_panel = new JPanel();
	JPanel title_panel = new JPanel();
	JPanel settings_panel = new JPanel();
	JPanel settings_player = new JPanel();
	JPanel button_panel = new JPanel();
	
	String[] colors = {"Black", "Red", "Green", "Blue"};
	
	Integer gridNumber = 3;
	String player1Name = "Player 1";
	String player2Name = "Player 2";
	Color player1Color = Color.BLACK;
	Color player2Color = Color.BLACK;
	
	Menu(){
		
		setupTitle();
		
		setupSettings();
		
		//main panel
		main_panel.setBounds(0, 0, 800, 800);
		main_panel.setLayout(new GridLayout(3,1));
		main_panel.add(title_panel);
		main_panel.add(settings_panel);
		main_panel.add(button_panel);
		
		frame.add(main_panel);
		
	}
	
	private void setupSettings() {
		Font textFont = new Font("MV Boli", Font.PLAIN, 15);
		
		//setting player
		settings_player.setLayout(new GridLayout(3,2));
		
		//form panel1
		JPanel form_panel1 = new JPanel();
		form_panel1.add(new GameText("Number of x Grid (min. 3)"));
		JTextField gridCountInput = new JTextField("3",5);
		gridCountInput.setFont(textFont);
		form_panel1.add(gridCountInput);
		
		//form panel2 - player1 name
		JPanel form_panel2 = new JPanel();
		form_panel2.add(new GameText("Name"));
		JTextField input_name1 = new JTextField(20);
		input_name1.setFont(textFont);
		form_panel2.add(input_name1);
		
		//form panel3 - player2 name
		JPanel form_panel3 = new JPanel();
		form_panel3.add(new GameText("Name"));
		JTextField input_name2 = new JTextField(20);
		input_name2.setFont(textFont);
		form_panel3.add(input_name2);
		
		//form panel4 - player1 color
		JPanel form_panel4 = new JPanel();
		form_panel4.add(new GameText("Color"));
		JComboBox<String> color1 = new JComboBox<String>(colors);
		color1.setFont(textFont);
		form_panel4.add(color1);
		
		//form panel5 - player2 color
		JPanel form_panel5 = new JPanel();
		form_panel5.add(new GameText("Color"));
		JComboBox<String> color2 = new JComboBox<String>(colors);
		color2.setFont(textFont);
		form_panel5.add(color2);
				
		settings_player.add(new GameText("Player 1", GameText.CENTER));
		settings_player.add(new GameText("Player 2", GameText.CENTER));
		settings_player.add(form_panel2);
		settings_player.add(form_panel3);
		settings_player.add(form_panel4);
		settings_player.add(form_panel5);
		
		//setting panel
		settings_panel.setLayout(new BorderLayout());
		settings_panel.add(form_panel1, BorderLayout.NORTH);
		settings_panel.add(settings_player, BorderLayout.CENTER);
		
		//start button
		JButton startButton = new JButton();
		startButton.setFont(new Font("MV Boli", Font.BOLD, 20));
		startButton.setText("Start Game");
		startButton.setHorizontalAlignment(GameText.CENTER);
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                try {
                	gridNumber = Integer.parseInt(gridCountInput.getText()) > 3 ? Integer.parseInt(gridCountInput.getText()) : 3;
                	player1Name = input_name1.getText().equals("") ? "Player 1" : input_name1.getText();
                	player2Name = input_name2.getText().equals("") ? "Player 2" : input_name2.getText();
           
                	player1Color = convertColor(color1.getSelectedItem().toString());
                	player2Color = convertColor(color2.getSelectedItem().toString());
                	
                	TicTacToe ticTacToe = new TicTacToe(gridNumber, player1Name, player2Name, player1Color, player2Color);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Number of Grid must be valid Number!");
                }
            }
		});
		
		//button_panel
		button_panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
		button_panel.add(startButton);
	}

	private void setupTitle() {
		title_panel.setLayout(new BorderLayout());
		title_panel.add(gameTitle);
	}
	
	private Color convertColor(String color) {
		Color result = Color.BLACK;
		switch (color) {
			case "Black": {
				result = Color.BLACK;
				break;
			}
			case "Red": {
				result = Color.RED;
				break;
			}
			case "Green": {
				result = Color.GREEN;
				break;
			}
			case "Blue": {
				result = Color.BLUE;
				break;
			}
			default:
				break;
		}
		return result;
	}
}
