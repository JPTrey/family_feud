package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import obj.Question;
import classes.Main;
import classes.Text;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// TODO end game after all answers have been logged

public class AdminFrame extends JFrame {

	private static AdminFrame 	frame;
	private JPanel				contentPane;
	private JLabel 				questLabel, questCountLabel, teamLabel, strikesLabel;
	private JButton 			ansButton1, ansButton2, ansButton3, ansButton4, ansButton5, 
	ansButton6, ansButton7, ansButton8, ansButton9, ansButton10;
	private int					cur_strikes, ansRemaining;
	private int 				ansButton1Pts, ansButton2Pts, ansButton3Pts, ansButton4Pts, ansButton5Pts, 
	ansButton6Pts, ansButton7Pts, ansButton8Pts, ansButton9Pts, ansButton10Pts; 
	private JButton				strikeButton, revealAllButton, nextButton, team1Button, team2Button;
	private JPanel				countPanel;

	// Fast Money vars
	private int					slot;							// used for caching answers/points for Fast Money round
	private String[]			ansCache = new String[10];		// stores answers for Fast Money round
	private int[]				ptsCache = new int[10];			// stores answer points for Fast Money round

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AdminFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminFrame() {
		setTitle("Family Feud - Admin");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(infoPanel, BorderLayout.NORTH);
		infoPanel.setLayout(new GridLayout(2, 2, 0, 0));

		countPanel = new JPanel();
		infoPanel.add(countPanel);
		countPanel.setLayout(new GridLayout(0, 2, 0, 0));

		questCountLabel = new JLabel("Question x of n");
		countPanel.add(questCountLabel);

		teamLabel = new JLabel("No Team Controls");
		countPanel.add(teamLabel);

		strikesLabel = new JLabel("Strikes: ");
		countPanel.add(strikesLabel);

		questLabel = new JLabel("\"Question Text\"");
		infoPanel.add(questLabel);

		JPanel ansPanel = new JPanel();
		ansPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(ansPanel, BorderLayout.CENTER);
		ansPanel.setLayout(new GridLayout(5, 2, 0, 0));

		ansButton1 = new JButton();
		ansButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton1.setEnabled(false);
				if (Main.FAST_MONEY) {
					Text.debug("FAST_MONEY = " + Main.FAST_MONEY);
					ansCache[slot] = ansButton1.getText();
					ptsCache[slot] = ansButton1Pts;
					Text.debug(ansCache[slot] + " in ansCache, slot = " + slot);
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {		// if: all five answers received, reveal all cached answers
						revealCached();
					}
					else {
						Main.nextQuestion();
						Text.debug("Doing stuff");
					}
				}
				else {
					Main.revealAnswer(ansButton1.getText(), ansButton1Pts, 1, true);
					ansRemaining--;
					checkDone();
				}
			}
		});
		ansButton1.setVisible(false);

		ansPanel.add(ansButton1);

		ansButton6 = new JButton();
		ansButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ansButton6.setEnabled(false);
				if (Main.FAST_MONEY) {
					ansCache[slot] = ansButton6.getText();
					ptsCache[slot] = ansButton6Pts;
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {	
						revealCached();
					}
					else {
						Main.nextQuestion();
					}
				}
				else {
					Main.revealAnswer(ansButton6.getText(), ansButton6Pts, 6, true);
					ansRemaining--;
					checkDone();
				}
			}
		});


		ansButton6.setVisible(false);
		ansPanel.add(ansButton6);



		ansButton2 = new JButton();
		ansButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton2.setEnabled(false);
				if (Main.FAST_MONEY) {
					ansCache[slot] = ansButton2.getText();
					ptsCache[slot] = ansButton2Pts;
					Text.debug(ansCache[slot] + " in ansCache");
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {		// if: all five answers received, reveal all cached answers
						revealCached();
					}
					else {
						Text.debug("Doing more stuff");
						Main.nextQuestion();
					}
				}
				else {
					Main.revealAnswer(ansButton2.getText(), ansButton2Pts, 2, true);
					ansRemaining--;
					checkDone();
				}
			}
		});
		ansButton2.setVisible(false);
		ansPanel.add(ansButton2);

		ansButton7 = new JButton();
		ansButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton7.setEnabled(false);
				if (Main.FAST_MONEY) {
					ansCache[slot] = ansButton7.getText();
					ptsCache[slot] = ansButton7Pts;
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {		// if: all five answers received, reveal all cached answers
						revealCached();
					}
					else {
						Main.nextQuestion();
					}
				}
				else {
					Main.revealAnswer(ansButton7.getText(), ansButton7Pts, 7, true);
					ansRemaining--;
					checkDone();
				}
			}
		});
		ansButton7.setVisible(false);
		ansPanel.add(ansButton7);

		ansButton3 = new JButton();
		ansButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton3.setEnabled(false);
				if (Main.FAST_MONEY) {
					ansCache[slot] = ansButton3.getText();
					ptsCache[slot] = ansButton3Pts;
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {		// if: all five answers received, reveal all cached answers
						revealCached();
					}
					else {
						Main.nextQuestion();
					}
				}
				else {
					Main.revealAnswer(ansButton3.getText(), ansButton3Pts, 3, true);
					ansRemaining--;
					checkDone();
				}
			}
		});
		ansButton3.setVisible(false);
		ansPanel.add(ansButton3);

		ansButton8 = new JButton();
		ansButton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton8.setEnabled(false);
				if (Main.FAST_MONEY) {
					ansCache[slot] = ansButton8.getText();
					ptsCache[slot] = ansButton8Pts;
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {		// if: all five answers received, reveal all cached answers
						revealCached();
					}
					else {
						Main.nextQuestion();
					}
				}
				else {
					Main.revealAnswer(ansButton8.getText(), ansButton8Pts, 8, true);
					ansRemaining--;
					checkDone();
				}
			}
		});
		ansButton8.setVisible(false);
		ansPanel.add(ansButton8);

		ansButton4 = new JButton();
		ansButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton4.setEnabled(false);
				if (Main.FAST_MONEY) {
					ansCache[slot] = ansButton4.getText();
					ptsCache[slot] = ansButton4Pts;
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {		// if: all five answers received, reveal all cached answers
						revealCached();
					}
					else {
						Main.nextQuestion();
					}
				}
				else {
					Main.revealAnswer(ansButton4.getText(), ansButton4Pts, 4, true);
					ansRemaining--;
					checkDone();
				}
			}
		});
		ansButton4.setVisible(false);
		ansPanel.add(ansButton4);

		ansButton9 = new JButton();
		ansButton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton9.setEnabled(false);
				if (Main.FAST_MONEY) {
					ansCache[slot] = ansButton9.getText();
					ptsCache[slot] = ansButton9Pts;
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {		// if: all five answers received, reveal all cached answers
						revealCached();
					}
					else {
						Main.nextQuestion();
					}
				}
				else {
					Main.revealAnswer(ansButton9.getText(), ansButton9Pts, 9, true);
					ansRemaining--;
					checkDone();
				}
			}
		});
		ansButton9.setVisible(false);
		ansPanel.add(ansButton9);

		ansButton5 = new JButton();
		ansButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton5.setEnabled(false);
				if (Main.FAST_MONEY) {
					ansCache[slot] = ansButton5.getText();
					ptsCache[slot] = ansButton5Pts;
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {		// if: all five answers received, reveal all cached answers
						revealCached();
					}
					else {
						Main.nextQuestion();
					}
				} else {
					Main.revealAnswer(ansButton5.getText(), ansButton5Pts, 5, true);
					ansRemaining--;
					checkDone();
				}
			}
		});
		ansButton5.setVisible(false);
		ansPanel.add(ansButton5);

		ansButton10 = new JButton("New button");
		ansButton10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton10.setEnabled(false);
				if (Main.FAST_MONEY) {
					ansCache[slot] = ansButton10.getText();
					ptsCache[slot] = ansButton10Pts;
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {		// if: all five answers received, reveal all cached answers
						revealCached();
					}
					else {
						Main.nextQuestion();
					}
				} 
				else {
					Main.revealAnswer(ansButton10.getText(), ansButton10Pts, 10, true);
					ansRemaining--;
					checkDone();
				}
			}
		});
		ansButton10.setVisible(false);
		ansPanel.add(ansButton10);

		JPanel cmdPanel = new JPanel();
		cmdPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(cmdPanel, BorderLayout.EAST);
		cmdPanel.setLayout(new GridLayout(0, 1, 0, 0));

		cur_strikes = 0;
		strikeButton = new JButton("Strike");
		strikeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Main.FAST_MONEY) {
					ansCache[slot] = "X";
					ptsCache[slot] = 0;
					slot++; disableAnsButtons();
					if (slot == 5 || slot == 10) {		// if: all five answers received, reveal all cached answers
						revealCached();
					}
					else {
						Main.nextQuestion();
					}
				}

				else {
					if (Main.getCUR_TEAM() != null) {
						cur_strikes++;
						if (cur_strikes == 3) {
							cur_strikes = 0;
						}
						Main.addStrike();
						setStrikesLabel();
					}
				}
			}
		});
		cmdPanel.add(strikeButton);

		revealAllButton = new JButton("Reveal All");
		revealAllButton.setEnabled(false);
		cmdPanel.add(revealAllButton);

		nextButton = new JButton("Next Question");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.nextQuestion();
				teamLabel.setText("No Team Controls");
				team1Button.setEnabled(true);
				team2Button.setEnabled(true);
			}
		});
		nextButton.setEnabled(false);
		cmdPanel.add(nextButton);

		team1Button = new JButton(Main.getTEAM_NAME(0) + " Controls");
		team1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setCUR_TEAM(0);
				teamLabel.setText(Main.getCUR_TEAM_NAME() + " Controls");
				team1Button.setEnabled(false);
				team2Button.setEnabled(false);
			}
		});
		cmdPanel.add(team1Button);

		team2Button = new JButton(Main.getTEAM_NAME(1) + " Controls");
		team2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setCUR_TEAM(1);
				teamLabel.setText(Main.getCUR_TEAM().getName());
				team1Button.setEnabled(false);
				team2Button.setEnabled(false);
			}
		});
		cmdPanel.add(team2Button);

		JButton endButton = new JButton("End Game");
		endButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.endGame();
			}
		});
		cmdPanel.add(endButton);
		slot = 0;
	}

	public void registerQuestion(final Question q) {
		ansRemaining = q.answerCount();
		questLabel.setText(q.getText());

		// reset variables
		revealAllButton.setEnabled(true);
		strikeButton.setEnabled(true);
		nextButton.setEnabled(false);
		ansButton1.setVisible(false);
		ansButton2.setVisible(false);
		ansButton3.setVisible(false);
		ansButton4.setVisible(false);
		ansButton5.setVisible(false);
		ansButton6.setVisible(false);
		ansButton7.setVisible(false);
		ansButton8.setVisible(false);
		ansButton9.setVisible(false);
		ansButton10.setVisible(false);

		if (Main.FAST_MONEY) {
			setTitle("Fast Money - Admin");
			team1Button.setEnabled(false);
			team2Button.setEnabled(false);
			teamLabel.setText(Main.getCUR_TEAM_NAME() + " Controls");
		}

		switch (q.answerCount()) {				// assign a button for each answer
		case 10:	ansButton10.setText(q.getAnswers().get(9).getBoth()); 
		ansButton10Pts = q.getAnswers().get(9).getPoints(); 
		ansButton10.setEnabled(true);
		ansButton10.setVisible(true);
		case 9:		ansButton9.setText(q.getAnswers().get(8).getBoth()); 
		ansButton9Pts = q.getAnswers().get(8).getPoints(); 
		ansButton9.setEnabled(true);
		ansButton9.setVisible(true);
		case 8:		ansButton8.setText(q.getAnswers().get(7).getBoth()); 
		ansButton8Pts = q.getAnswers().get(7).getPoints(); 
		ansButton8.setEnabled(true);
		ansButton8.setVisible(true);
		case 7: 	ansButton7.setText(q.getAnswers().get(6).getBoth()); 
		ansButton7Pts = q.getAnswers().get(6).getPoints(); 
		ansButton7.setEnabled(true);
		ansButton7.setVisible(true);
		case 6:		ansButton6.setText(q.getAnswers().get(5).getBoth()); 
		ansButton6Pts = q.getAnswers().get(5).getPoints(); 
		ansButton6.setEnabled(true);
		ansButton6.setVisible(true);
		case 5:		ansButton5.setText(q.getAnswers().get(4).getBoth()); 
		ansButton5Pts = q.getAnswers().get(4).getPoints(); 
		ansButton5.setEnabled(true);
		ansButton5.setVisible(true);
		case 4:		ansButton4.setText(q.getAnswers().get(3).getBoth()); 
		ansButton4Pts = q.getAnswers().get(3).getPoints(); 
		ansButton4.setEnabled(true);
		ansButton4.setVisible(true);
		case 3:		ansButton3.setText(q.getAnswers().get(2).getBoth()); 
		ansButton3Pts = q.getAnswers().get(2).getPoints(); 
		ansButton3.setEnabled(true);
		ansButton3.setVisible(true);
		case 2:		ansButton2.setText(q.getAnswers().get(1).getBoth()); 
		ansButton2Pts = q.getAnswers().get(1).getPoints(); 
		ansButton2.setEnabled(true);
		ansButton2.setVisible(true);
		case 1:		ansButton1.setText(q.getAnswers().get(0).getBoth()); 
		ansButton1Pts = q.getAnswers().get(0).getPoints(); 
		ansButton1.setEnabled(true);
		ansButton1.setVisible(true);
		break;
		default: 	Text.debug("Answers not found!"); break;
		}

		// enabled revealAllButton
		revealAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Main.FAST_MONEY) {
					concealAll();
				}

				else {
					switch (q.answerCount()) {				
					case 10:	ansButton10.setEnabled(false);
					Main.revealAnswer(ansButton10.getText(), ansButton10Pts, 10, false);
					case 9:		ansButton9.setEnabled(false);
					Main.revealAnswer(ansButton9.getText(), ansButton9Pts, 9, false);
					case 8:		ansButton8.setEnabled(false);
					Main.revealAnswer(ansButton8.getText(), ansButton8Pts, 8, false);
					case 7: 	ansButton7.setEnabled(false);
					Main.revealAnswer(ansButton7.getText(), ansButton7Pts, 7, false);
					case 6:		ansButton6.setEnabled(false);
					Main.revealAnswer(ansButton6.getText(), ansButton6Pts, 6, false);
					case 5:		ansButton5.setEnabled(false);
					Main.revealAnswer(ansButton5.getText(), ansButton5Pts, 5, false);
					case 4:		ansButton4.setEnabled(false);
					Main.revealAnswer(ansButton4.getText(), ansButton4Pts, 4, false);
					case 3:		ansButton3.setEnabled(false);
					Main.revealAnswer(ansButton3.getText(), ansButton3Pts, 3, false);
					case 2:		ansButton2.setEnabled(false);
					Main.revealAnswer(ansButton2.getText(), ansButton2Pts, 2, false);
					case 1:		ansButton1.setEnabled(false);
					Main.revealAnswer(ansButton1.getText(), ansButton1Pts, 1, false);
					break;

					default: 	Text.debug("Answers not found!"); break;
					}
					nextButton.setEnabled(true);
					revealAllButton.setEnabled(false);
				}
			}
		});
		revealAllButton.setEnabled(true);	

		questCountLabel.setText("Question " + Main.getCUR_QUESTION_NUM() +
				" of " + Main.getTOTAL_QUESTIONS());
	}

	private void setStrikesLabel() {
		if (teamLabel.getText().equalsIgnoreCase("No Team Controls")) {
			;
		}

		else {
			String strikesOutput = "";
			for (int i=0; i<cur_strikes; i++) {
				strikesOutput += "X";
			}
			strikesLabel.setText("Strikes: " + strikesOutput);
		}
	}

	public void setCurrentPlayer(String playerName) {

	}

	private void checkDone() {
		if (ansRemaining == 0) {
			nextButton.setEnabled(true);
			strikeButton.setEnabled(false);
			revealAllButton.setEnabled(false);
		}
	}

	public void switchTeamLabel() {
		teamLabel.setText(Main.getCUR_TEAM_NAME() + " Controls");
	}

	/**
	 * Called when five answers have been selected during Fast Money round.  Reveals all answers.
	 */
	private void revealCached() {
		boolean addPoints = false;			// true: add points to total, false: just reveal text
		Text.debug("Revealing all");

		if (slot == 10) {
			addPoints = true;
		}

		// reveal player 1's choices
		for (int i=0; i<5; i++) {
			Text.debug("Revealing " + ansCache[i].toString());
			Main.revealAnswer(ansCache[i], ptsCache[i], i+1, addPoints);
			revealAllButton.setText("Conceal");
		}

		// reveal all choices
		if (slot == 10) {
			for (int i=5; i<ansCache.length; i++) {
				Text.debug("" +i);
				if (ansCache[i].equalsIgnoreCase(ansCache[i-5])) {	// if: duplicates are found
					Main.revealAnswer(ansCache[i], 0, (i+1), false);
					Text.debug("Found duplicate in slot " + (i-4));
				}

				else {
					Main.revealAnswer(ansCache[i], ptsCache[i], (i+1), true);
					Text.debug("Revealing answer in slot " + (i+1));
				}
			}
		}

	}

	/**
	 * Conceals Player 1's answers in preparation for Player 2.
	 */
	private void concealAll() {
		for (int i=1; i<=5; i++) {
			Main.revealAnswer("", 0, i, false);
			nextButton.setEnabled(true);
		}

		//		revealAllButton.setText("Reveal All");
		revealAllButton.setEnabled(false);
	}

	private void disableAnsButtons() {
		ansButton1.setEnabled(false);
		ansButton2.setEnabled(false);
		ansButton3.setEnabled(false);
		ansButton4.setEnabled(false);
		ansButton5.setEnabled(false);
		ansButton6.setEnabled(false);
		ansButton7.setEnabled(false);
		ansButton8.setEnabled(false);
		ansButton9.setEnabled(false);
		ansButton10.setEnabled(false);
		strikeButton.setEnabled(false);
	}

}
