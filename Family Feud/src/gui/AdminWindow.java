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
	private JButton				strikeButton, nextQuestButton, setTeam1Button, setTeam2Button, endGameButton;
	private AnswerButton		a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
	private Question			q;
	private PlayWindow			pw;			// PlayWindow associated with this AdminWindow

	public AdminWindow(String title, Question q, PlayWindow pw) {
		setTitle(title);
		this.q = q;
		this.pw = pw;	
		totalPoints = 0;

		mainPanel = new JPanel(new GridLayout(2,1,5,0));
		setQuestion(q);
		switchTeamLabel();
		setupControlPanel();
		buttonCount = q.answerCount();
		mainPanel.add(ansPanel);
		mainPanel.add(controlPanel);
		add(mainPanel);

		setVisible(true);
		setSize(Main.getADMIN_DIM());
	}

	public void setQuestion(Question q) {
		//		questPanel = new JPanel();
		ansPanel = new JPanel();
		//		questLabel = new JLabel(q.getText());
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
		ansPanel.setVisible(true);
	}

	private void setupControlPanel() {
		controlPanel = new JPanel();
		strikeButton = new JButton("Strike");
		strikeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				strike();
			}
		});
		
		nextQuestButton = new JButton("Next Question");
		nextQuestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { Main.nextQuestion(totalPoints); }
		});
		nextQuestButton.setVisible(false);
		
		setTeam1Button = new JButton("Team " + Main.getTEAM_NAME(0));
		setTeam1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				Main.setCUR_TEAM(0); 
				pw.switchTeamLabel(); 
				setTeam1Button.setVisible(false);
				setTeam2Button.setVisible(false);
			}
		});
		setTeam2Button = new JButton("Team " + Main.getTEAM_NAME(1));
		setTeam2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				Main.setCUR_TEAM(1); 
				pw.switchTeamLabel();
				setTeam1Button.setVisible(false);
				setTeam2Button.setVisible(false);
			}
		});
		
		endGameButton = new JButton("End Game");
		endGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { 
				
			}
		});
		
		controlPanel.add(strikeButton);
		controlPanel.add(setTeam1Button);
		controlPanel.add(setTeam2Button);
		controlPanel.add(nextQuestButton);
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
}
