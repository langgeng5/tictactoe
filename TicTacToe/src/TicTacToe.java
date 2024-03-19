import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class TicTacToe implements ActionListener {

	Random random = new Random();
	JFrame frame = new JFrame();
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textField = new JLabel();
	int gridCount = 4;
	JButton[][] buttons = new JButton[gridCount][gridCount];
	boolean player1_turn = true;
	
	int time_per_turn = 5;
	
	String player1_name = "Player 1";
	String player2_name = "Player 2";
	
	Color player1_color = Color.BLACK;
	Color player2_color = Color.BLACK;
	
	TicTacToe(int gridCount, String player1_name, String player2_name, Color player1_color, Color player2_color){
		this.gridCount = gridCount;
		this.player1_name = player1_name;
		this.player2_name = player2_name;
		this.player1_color = player1_color;
		this.player2_color = player2_color;
		
		this.buttons = new JButton[gridCount][gridCount];
		
		//frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		//textField
		textField.setBackground(new Color(25,25,25));
		textField.setForeground(new Color(25,255,0));
		textField.setFont(new Font("Ink Free", Font.BOLD, 75));
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText("TIC TAC TOE");
		textField.setOpaque(true);
		
		//title_panel
		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,800,100);
		
		//button_panel
		button_panel.setLayout(new GridLayout(gridCount,gridCount));
		button_panel.setBackground(new Color(150,150,150));
		
		//generate button
		for (int i = 0; i < (gridCount); i++) {
			for (int j = 0; j < (gridCount); j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setFont(new Font("MV Boli", Font.BOLD, 120));
				buttons[i][j].setFocusable(false);
				buttons[i][j].addActionListener(this);
				
				button_panel.add(buttons[i][j]);
			}
		}
		
		title_panel.add(textField);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);
		
		textField.setText(player1_name+" turn");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < (gridCount); i++) {
			for (int j = 0; j < (gridCount); j++) {
				if (e.getSource() == buttons[i][j]) {
					
					if (player1_turn) {
						if (buttons[i][j].getText() == "") {
							buttons[i][j].setForeground(player1_color);
							buttons[i][j].setText("X");
							player1_turn = false;
							textField.setText(player2_name+" turn");
						}
					} else {
						if (buttons[i][j].getText() == "") {
							buttons[i][j].setForeground(player2_color);
							buttons[i][j].setText("O");
							player1_turn = true;
							textField.setText(player1_name+" turn");
						}
					}
					check();
				}
			}
		}
	}

	public void check() {
		List<JButton> verticalList = new ArrayList<JButton>();
		List<JButton> horizontalList = new ArrayList<JButton>();
		List<JButton> diagonal1List = new ArrayList<JButton>();
		List<JButton> diagonal2List = new ArrayList<JButton>();
		String verticalVal = "X";
		String horizontalVal = "X";
		String diagonal1Val = "X";
		String diagonal2Val = "X";
		int emptyGrid = 0;
		for (int i = 0; i < gridCount; i++) {
			horizontalList.clear();
			verticalList.clear();
			for (int j = 0; j < gridCount; j++) {
				
				if (horizontalVal == buttons[i][j].getText() && buttons[i][j].getText() != "") {
					horizontalList.add(buttons[i][j]);
				} else {
					horizontalVal = buttons[i][j].getText();
					horizontalList.clear();
					horizontalList.add(buttons[i][j]);
				}
				
				if (verticalVal == buttons[j][i].getText() && buttons[j][i].getText() != "") {
					verticalList.add(buttons[j][i]);
				} else {
					verticalVal = buttons[j][i].getText();
					verticalList.clear();
					verticalList.add(buttons[j][i]);
				}
				
				if (i == j && buttons[i][j].getText() != "") {
					if (diagonal1Val == buttons[i][j].getText()) {
						diagonal1List.add(buttons[i][j]);
					} else {
						diagonal1Val = buttons[i][j].getText();
						diagonal1List.clear();
						diagonal1List.add(buttons[i][j]);
					}
				}
				
				if (i+j == gridCount-1 && buttons[i][j].getText() != "") {
					if (diagonal2Val == buttons[i][j].getText()) {
						diagonal2List.add(buttons[i][j]);
					} else {
						diagonal2Val = buttons[i][j].getText();
						diagonal2List.clear();
						diagonal2List.add(buttons[i][j]);
					}
				}
				
				if (verticalList.size() == gridCount || horizontalList.size() == gridCount || diagonal1List.size() == gridCount || diagonal2List.size() == gridCount) {
					break;
				}
				
				if (buttons[i][j].getText() == "") {
					emptyGrid++;
				}
			}
			if (verticalList.size() == gridCount || horizontalList.size() == gridCount || diagonal1List.size() == gridCount || diagonal2List.size() == gridCount) {
				break;
			}
		}
		
		if (verticalList.size() == gridCount) {
			results(verticalVal, verticalList);
		} else if (horizontalList.size() == gridCount) {
			results(horizontalVal, horizontalList);
		} else if (diagonal1List.size() == gridCount) {
			results(diagonal1Val, diagonal1List);
		} else if (diagonal2List.size() == gridCount) {
			results(diagonal2Val, diagonal2List);
		} else if (emptyGrid == 0) {
			results("", new ArrayList<JButton>());
		}
	}
	
	public void results(String player, List<JButton> buttonList) {
		if (player == "") {
			textField.setText("Draw");
		} else {			
			textField.setText(player+" Wins");
		}
		
		for (JButton button : buttonList) {
			button.setBackground(Color.GREEN);
		}
		
		for (int i = 0; i < gridCount; i++) {
			for (int j = 0; j < gridCount; j++) {
				buttons[j][i].setEnabled(false);
			}
		}
	}
}