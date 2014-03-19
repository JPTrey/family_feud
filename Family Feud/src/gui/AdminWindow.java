package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import obj.Answer;
import obj.AnswerButton;
import obj.Question;
import classes.Main;
import classes.Text;

/**
 * Window displayed to program administrator during play;
 * Enables answer toggling, advancing questions, triggering end-game
 * @author Jon Paul
 */
public class AdminWindow extends JFrame {
	private int					buttonCount, totalPoints;
	private JLabel				ansLabel, teamLabel;
	private JPanel				mainPanel, ansPanel, controlPanel;
	private JButton				strikeButton, endQuestButton, nextQuestButton, setTeam1Button, setTeam2Button, endGameButton;
	private AnswerButton		a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
	private Question			q;			// question currently associated with this window, and PlayWindow
	private PlayWindow			pw;			// PlayWindow associated with this AdminWindow

	public AdminWindow(String title, Question q, PlayWindow pw) {
		setTitle(title);
		this.q = q;
		this.pw = pw;	
		totalPoints = 0;

		mainPanel = new JPanel(new GridLayout(2,1,5,0));
		setQuestion(q);
		//		setupControlPanel();
		buttonCount = q.answerCount();
		mainPanel.add(ansPanel);
		mainPanel.add(controlPanel);
		add(mainPanel);

		setVisible(true);
		setSize(Main.getADMIN_DIM());
	}

	/**
	 * Prepares window for new question
	 * Called from Main.makeAdminWindow
	 * @param q current question associated with this window and PlayWindow
	 */
	public void setQuestion(Question q) {
		//		questPanel = new JPanel();
		if (ansPanel == null) { ansPanel = new JPanel(); }

		else {
			if (a10 != null) { a10.setVisible(false);}
			if (a9 != null) { a9.setVisible(false);}
			if (a8 != null) { a8.setVisible(false);}
			if (a7 != null) { a7.setVisible(false);}
			if (a6 != null) { a6.setVisible(false);}
			if (a5 != null) { a5.setVisible(false);}
			if (a4 != null) { a4.setVisible(false);}
			if (a3 != null) { a3.setVisible(false);}
			if (a2 != null) { a2.setVisible(false);}
			if (a1 != null) { a1.setVisible(false);}
		}

		switch (q.answerCount()) {				// assign a button for each answer
		case 10: 	a10 = new AnswerButton(q.getAnswers().get(9),this); ansPanel.add(a10);
		case 9:		a9 = new AnswerButton(q.getAnswers().get(8),this); ansPanel.add(a9);
		case 8:		a8 = new AnswerButton(q.getAnswers().get(7),this); ansPanel.add(a8);
		case 7: 	a7 = new AnswerButton(q.getAnswers().get(6),this); ansPanel.add(a7);
		case 6:		a6 = new AnswerButton(q.getAnswers().get(5),this); ansPanel.add(a6);
		case 5:		a5 = new AnswerButton(q.getAnswers().get(4),this); ansPanel.add(a5);
		case 4:		a4 = new AnswerButton(q.getAnswers().get(3),this); ansPanel.add(a4);
		case 3:		a3 = new AnswerButton(q.getAnswers().get(2),this); ansPanel.add(a3);
		case 2:		a2 = new AnswerButton(q.getAnswers().get(1),this); ansPanel.add(a2);
		case 1:		a1 = new AnswerButton(q.getAnswers().get(0),this); ansPanel.add(a1); break;
		default: 	Text.debug("Answers not found!"); break;
		}

		//		questPanel.add(questLabel);
		//		questPanel.setSize(Main.getADMIN_DIM().width, Main.getADMIN_DIM().height/2);
		//		questPanel.setVisible(true);
		//		questLabel.setVisible(true);
		setupControlPanel();
		switchTeamLabel();
		ansPanel.setVisible(true);
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

			endQuestButton = new JButton("End Question");
			endQuestButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) { 
					revealAll();
					nextQuestButton.setVisible(true);
					endQuestButton.setVisible(false);
				}
			});
			
			nextQuestButton = new JButton("Next Question");
			nextQuestButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) { 
					Main.nextQuestion(totalPoints);
				}
			});
			nextQuestButton.setVisible(false);

			setTeam1Button = new JButton("Team '" + Main.getTEAM_NAME(0) + "'");
			setTeam1Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) { 
					Main.setCUR_TEAM(0); 
					pw.switchTeamLabel(); 
					pw.switchPlayerLabel(Main.getCUR_PLAYER_NAME());
					setTeam1Button.setVisible(false);
					setTeam2Button.setVisible(false);
				}
			});
			setTeam2Button = new JButton("Team '" + Main.getTEAM_NAME(1) + "'");
			setTeam2Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) { 
					Main.setCUR_TEAM(1); 
					pw.switchTeamLabel();
					pw.switchPlayerLabel(Main.getCUR_PLAYER_NAME());
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
			controlPanel.add(endQuestButton);
			controlPanel.add(nextQuestButton);
			controlPanel.add(endGameButton);
		}

		else {
			setTeam1Button.setVisible(true);
			setTeam2Button.setVisible(true);
			endQuestButton.setVisible(true);
			nextQuestButton.setVisible(false);
		}

	}

	// TODO: allow PW to enable visibility
	public void setQuestButtonVisible() { nextQuestButton.setVisible(true); }

	private void switchTeamLabel() {
		if (teamLabel == null) { teamLabel = new JLabel(Main.getCUR_TEAM().getName()); }
		teamLabel.setText(Main.getCUR_TEAM().getName() + " controls");
	}

	private void strike() {
		Main.addStrike();
		pw.setStrikes();
	}

	/**
	 * Called from associated ansButton
	 * Reveals answer on PlayWindow
	 * Increases point total
	 * @param ans answer to be revealed, per button press
	 */
	public void revealAnswer(Answer ans) {
		pw.revealAnswer(ans); 

		// remove associated button
		if 		(ans.getText().equalsIgnoreCase(a1.getAnswerText())) { a1.setVisible(false); }
		else if (ans.getText().equalsIgnoreCase(a2.getAnswerText())) { a2.setVisible(false); }	
		else if (ans.getText().equalsIgnoreCase(a3.getAnswerText())) { a3.setVisible(false); }
		else if (ans.getText().equalsIgnoreCase(a4.getAnswerText())) { a4.setVisible(false); }
		else if (ans.getText().equalsIgnoreCase(a5.getAnswerText())) { a5.setVisible(false); }
		else if (ans.getText().equalsIgnoreCase(a6.getAnswerText())) { a6.setVisible(false); }
		else if (ans.getText().equalsIgnoreCase(a7.getAnswerText())) { a7.setVisible(false); }
		else if (ans.getText().equalsIgnoreCase(a8.getAnswerText())) { a8.setVisible(false); }
		else if (ans.getText().equalsIgnoreCase(a9.getAnswerText())) { a9.setVisible(false); }
		else if (ans.getText().equalsIgnoreCase(a10.getAnswerText())) { a10.setVisible(false); }
		else { Text.debug("Button for '" + ans.getText() + "' not found"); }

		totalPoints += ans.getPoints();
		buttonCount--;
		if (buttonCount == 0) { nextQuestButton.setVisible(true); }
	}

	private void revealAll() {
		switch (q.answerCount()) {
		case 10: 	pw.revealAnswer(a10.getAnswer()); a10.setVisible(false);
		case 9:		pw.revealAnswer(a9.getAnswer()); a9.setVisible(false);
		case 8:		pw.revealAnswer(a8.getAnswer()); a8.setVisible(false);
		case 7: 	pw.revealAnswer(a7.getAnswer()); a7.setVisible(false);
		case 6:		pw.revealAnswer(a6.getAnswer()); a6.setVisible(false);
		case 5:		pw.revealAnswer(a5.getAnswer()); a5.setVisible(false);
		case 4:		pw.revealAnswer(a4.getAnswer()); a4.setVisible(false);
		case 3:		pw.revealAnswer(a3.getAnswer()); a3.setVisible(false);
		case 2:		pw.revealAnswer(a2.getAnswer()); a2.setVisible(false);
		case 1:		pw.revealAnswer(a1.getAnswer()); a1.setVisible(false); break;
		default: 	Text.debug("Answers not found!"); break;
		}
	}
}
