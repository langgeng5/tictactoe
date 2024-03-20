import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class TicTacToe implements ActionListener {

	GameFrame frame = new GameFrame();
	JPanel title_panel = new JPanel();
	JPanel board_panel = new JPanel();
	
	GameTitle gameTitle = new GameTitle();
	
	int gridCount = 3;
	GameButton[][] buttons = new GameButton[gridCount][gridCount];
	
	boolean player1_turn;
	String player1_name = "Player 1";
	String player2_name = "Player 2";
	Color player1_color = Color.BLACK;
	Color player2_color = Color.BLACK;
	
	TicTacToe(){
		init();
	}
	
	TicTacToe(int gridCount, String player1_name, String player2_name, Color player1_color, Color player2_color){
		this.gridCount = gridCount;
		this.player1_name = player1_name;
		this.player2_name = player2_name;
		this.player1_color = player1_color;
		this.player2_color = player2_color;
		
		this.buttons = new GameButton[gridCount][gridCount];
		
		init();
	}
	
	private void init() {
		
		setupTitle();
		
		setupBoard();
		
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(board_panel);
		
		firstTurn();
		
	}
	
	private void firstTurn() {
		gameTitle.setText(player1_name+"'s turn");
		player1_turn = true;
	}

	private void setupBoard() {
		board_panel.setLayout(new GridLayout(gridCount,gridCount));
		board_panel.setBackground(new Color(150,150,150));
		generateButtons();
	}

	private void setupTitle() {
		title_panel.setLayout(new BorderLayout());
		title_panel.add(gameTitle);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < (gridCount); i++) {
			for (int j = 0; j < (gridCount); j++) {
				if (e.getSource() == buttons[i][j]) {
					
					if (player1_turn) {
						if (buttons[i][j].getText() == "") {
							buttons[i][j].setForeground(player1_color);
							buttons[i][j].setText("X");
							player1_turn = false;
							gameTitle.setText(player2_name+"'s turn");
						}
					} else {
						if (buttons[i][j].getText() == "") {
							buttons[i][j].setForeground(player2_color);
							buttons[i][j].setText("O");
							player1_turn = true;
							gameTitle.setText(player1_name+"'s turn");
						}
					}
					checkBoard();
				}
			}
		}
	}
	
	private void generateButtons() {
		for (int i = 0; i < (gridCount); i++) {
			for (int j = 0; j < (gridCount); j++) {
				buttons[i][j] = new GameButton();
				buttons[i][j].addActionListener(this);
				
				board_panel.add(buttons[i][j]);
			}
		}
	}

	private void checkBoard() {
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
	
	private void results(String player, List<JButton> buttonList) {
		if (player == "") {
			gameTitle.setText("Draw");
		} else if (player == "X") {			
			gameTitle.setText(player1_name+" Wins");
		} else {
			gameTitle.setText(player2_name+" Wins");
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
