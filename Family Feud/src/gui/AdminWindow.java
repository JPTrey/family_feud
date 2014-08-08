package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import obj.Answer;
import obj.AnswerButton;
import obj.Question;
import classes.Main;

/**
 * Window displayed to program administrator during play;
 * Enables answer toggling, advancing questions, triggering end-game
 * @author Jon Paul
 */
public class AdminWindow extends JFrame {
	private AdminFrame 			frame;
	private JLabel				ansLabel, teamLabel;
	private JPanel				mainPanel, ansPanel, controlPanel;
	private JButton				strikeButton, revealAllButton, nextQuestButton, setTeam1Button, setTeam2Button, endGameButton;
	private ArrayList<AnswerButton> ansButtons;
	private AnswerButton		a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
	private Question			q;			// question currently associated with this window, and PlayWindow
	private PlayWindow			pw;			// PlayWindow associated with this AdminWindow
	
	public AdminWindow(String title, final Question q, PlayWindow pw) {
				this.q = q;
				this.pw = pw;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AdminFrame();
					frame.setVisible(true);
					frame.registerQuestion(q);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Prepares window for new question
	 * Called from Main.makeAdminWindow
	 * @param q current question associated with this window and PlayWindow
	 */
	public void setQuestion(Question q) {
		frame.registerQuestion(q);
		this.q = q;
	}

	private void setupControlPanel() {
		if (controlPanel == null) { 
			controlPanel = new JPanel(); 
			strikeButton = new JButton("Strike"); 
			strikeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					strike();
				}
			});

			revealAllButton = new JButton("Reveal All");
			revealAllButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) { 
					revealAll();
					nextQuestButton.setVisible(true);
					revealAllButton.setVisible(false);
				}
			});

			nextQuestButton = new JButton("Next Question");
			nextQuestButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) { 
					Main.nextQuestion();
				}
			});
			nextQuestButton.setVisible(false);

			setTeam1Button = new JButton("Team '" + Main.getTEAM_NAME(0) + "'");
			setTeam1Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) { 
					Main.setTeamButtonAction(0); 
					setTeam1Button.setVisible(false);
					setTeam2Button.setVisible(false);
				}
			});

			setTeam2Button = new JButton("Team '" + Main.getTEAM_NAME(1) + "'");
			setTeam2Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) { 
					Main.setTeamButtonAction(1); 
					setTeam1Button.setVisible(false);
					setTeam2Button.setVisible(false);
				}
			});

			endGameButton = new JButton("End Game");
			endGameButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) { 
					// confirm
					// show winner
					Main.EXIT();
				}
			});

			controlPanel.add(strikeButton);
			controlPanel.add(setTeam1Button);
			controlPanel.add(setTeam2Button);
			controlPanel.add(revealAllButton);
			controlPanel.add(nextQuestButton);
			controlPanel.add(endGameButton);
		}

		else {
			setTeam1Button.setVisible(true);
			setTeam2Button.setVisible(true);
			revealAllButton.setVisible(true);
			nextQuestButton.setVisible(false);
		}

	}

	public void switchTeamLabel() {
		frame.switchTeamLabel();
	}

	private void strike() {
		Main.addStrike();
	}

	/**
	 * Called from associated ansButton
	 * Reveals answer on PlayWindow
	 * Increases point total
	 * @param ans answer to be revealed, per button press
	 */
	public void revealAnswer(Answer ans, int slot) {
		Main.revealAnswer(ans);

		for (int i=0; i<ansButtons.size(); i++) {
			if (ans.getText().equalsIgnoreCase(ansButtons.get(i).getAnswerText())) { 
				ansButtons.get(i).setVisible(false); 
				break;
			}
		}
	}

	private void revealAll() {
		for (int i=0; i<ansButtons.size(); i++) {
			Main.revealAnswer(ansButtons.get(i).getAnswer());
			ansButtons.get(i).setVisible(false);
		}
	}

	public void switchPlayerLabel(String playerName) {
		frame.setCurrentPlayer(playerName);
		
	}
}
