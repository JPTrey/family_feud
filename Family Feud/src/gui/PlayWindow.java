package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import obj.Answer;
import obj.Question;
import classes.Main;
import classes.Text;

/**
 * Window displayed to players
 * Displays question text, total points, current team, total strikes and, when selected, answer labels
 * @author jonpaulsimonelli
 */
public class PlayWindow extends JFrame {
//	private String				title;
	private Question			q;			// this window's question
	private JPanel				mainPanel, textPanel, numPanel, questPanel, ansPanel, strikePanel, pointsPanel, teamPanel;
	private JLabel				background, questLabel, strikeLabel, pointsLabel, teamLabel, playerLabel,
								a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;		// answers #1-10
	private int					totalPoints, ansRemain;
	//	private ArrayList<AnswerLabel>	ansLabels;	// answers for this window's question
	//	private ArrayList<JLabel>	ansLabels;	// answers for this window's question
	//	private ArrayList<JLabel>	censors;	// cover answer before revealed

	public PlayWindow(String title, Question q) {
		setTitle(title);
		this.q = q;
		ansRemain = q.answerCount();
		
		mainPanel = new JPanel(new BorderLayout());
		textPanel = new JPanel(new GridLayout(2,1,25,0));
		numPanel = new JPanel(new GridLayout(4,1,6,0));
		setQuestion(q);
		
		background = new JLabel(new ImageIcon("images.jpeg"));
		
		numPanel.add(setupPointsPanel(),BorderLayout.EAST);
		numPanel.add(setupTeamPanel(),BorderLayout.WEST);
		numPanel.add(setupStrikePanel(),BorderLayout.CENTER);
		
		add(background);
		mainPanel.add(textPanel,BorderLayout.CENTER);
		mainPanel.add(numPanel,BorderLayout.EAST);
		add(mainPanel);

		setSize(Main.getPLAY_DIM());
		setVisible(true);
	}

	/**
	 * Prepares question text and concealed answer JLabels
	 */
	private void setQuestion(Question q) {
		int i = 0;		// enables proper order of answer labels
		
		if (questPanel == null) { questPanel = setupQuestPanel(q); }
		if (ansPanel == null) { ansPanel = new JPanel(new GridLayout(5,2,10,50)); }
		switch (q.answerCount()) {				// assign a JLabel for each answer
		case 10: 	a10 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a10); a10.setVisible(false);
					i++; Text.debug("added " + a10.getText());
		
		case 9:		a9 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a9); a9.setVisible(false);
					i++; Text.debug("added " + a9.getText());
		
		case 8:		a8 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a8); a8.setVisible(false);
					i++; Text.debug("added " + a8.getText());
		
		case 7: 	a7 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a7); a7.setVisible(false);
					i++; Text.debug("added " + a7.getText());
		
		case 6:		a6 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a6); a6.setVisible(false);
					i++; Text.debug("added " + a6.getText());
		
		case 5:		a5 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a5); a5.setVisible(false);
					i++; Text.debug("added " + a5.getText());
		
		case 4:		a4 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a4); a4.setVisible(false);
					i++; Text.debug("added " + a4.getText());
		
		case 3:		a3 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a3); a3.setVisible(false);
					i++; Text.debug("added " + a3.getText());
		
		case 2:		a2 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a2); a2.setVisible(false);
					i++; Text.debug("added " + a2.getText());
		
		case 1:		a1 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a1); a1.setVisible(false);
					Text.debug("added " + a1.getText()); 
					break;
		default: 	Text.debug("Answers not found!"); break;
		}

		ansPanel.setVisible(true);
		textPanel.add(questPanel);
		textPanel.add(ansPanel);
	}

	private JPanel setupQuestPanel(Question q) {
		if (strikePanel == null) {
			questPanel = new JPanel();
			questPanel.setSize(Main.getPLAY_DIM().width, 50);
		}
		questLabel = new JLabel(q.getText());
		questLabel.setVisible(true);
		questPanel.add(questLabel);
		return questPanel;
	}

	private JPanel setupStrikePanel() {
		if (strikePanel == null) { 
			strikePanel = new JPanel(); 
			strikeLabel = new JLabel("Strikes: 0"); 
			strikePanel.add(strikeLabel);
			strikePanel.setSize(Main.getADMIN_DIM().width/4,Main.getADMIN_DIM().height/5);	
		}
		return strikePanel;
	}

	private JPanel setupPointsPanel() {
		if (pointsPanel == null) {
			pointsPanel = new JPanel();
			pointsLabel = new JLabel("Points: 0");
			pointsPanel.add(pointsLabel);
		}
		return pointsPanel;
	}

	private JPanel setupTeamPanel() {
		if (teamPanel == null) {
			teamPanel = new JPanel();
		}
		teamLabel = new JLabel("No Team Controls!");
		playerLabel = new JLabel();
		teamPanel.add(teamLabel);
		teamPanel.add(playerLabel);
		return teamPanel;
	}

	/**
	 * Called when either:
	 * An answer was found in the dictionary or;
	 * An answer was selected in AdminWindow
	 * @param ans answer selected for reveal
	 */
	public void revealAnswer(Answer ans) {	
//		try { Thread.sleep(Main.DRAMATIC_PAUSE); } catch (InterruptedException e) { e.printStackTrace();} // pause for dramatic effect
		
		if 		(ans.getBoth().equalsIgnoreCase(a1.getText())) { a1.setVisible(true); }
		else if (ans.getBoth().equalsIgnoreCase(a2.getText())) { a2.setVisible(true); }	
		else if (ans.getBoth().equalsIgnoreCase(a3.getText())) { a3.setVisible(true); }
		else if (ans.getBoth().equalsIgnoreCase(a4.getText())) { a4.setVisible(true); }
		else if (ans.getBoth().equalsIgnoreCase(a5.getText())) { a5.setVisible(true); }
		else if (ans.getBoth().equalsIgnoreCase(a6.getText())) { a6.setVisible(true); }
		else if (ans.getBoth().equalsIgnoreCase(a7.getText())) { a7.setVisible(true); }
		else if (ans.getBoth().equalsIgnoreCase(a8.getText())) { a8.setVisible(true); }
		else if (ans.getBoth().equalsIgnoreCase(a9.getText())) { a9.setVisible(true); }
		else if (ans.getBoth().equalsIgnoreCase(a10.getText())) { a10.setVisible(true); }
		else { Text.debug("Button for '" + ans.getText() + "' not found"); }
		setPoints(ans.getPoints());
		Text.debug("'" + ans.getText() + "' revealed in PlayWindow");
	}

	private void setPoints(int points) { 
		totalPoints += points; 
		pointsLabel.setText("Points: " + Integer.toString(totalPoints));
	}

	public void setStrikes() { 
//		try { Thread.sleep(Main.DRAMATIC_PAUSE); } catch (InterruptedException e) { e.printStackTrace(); } // pause for dramatic effect
		JPanel showStrikes = new JPanel();
		showStrikes.setSize(Main.getPLAY_DIM());
		mainPanel.setVisible(false);
		
		switch(Main.getCUR_TEAM().getStrikeCount()) {		// display 'X'
		case 1: add(showStrikes.add(new JLabel("X"))); Text.debug("Strike 1"); break;
		case 2: add(showStrikes.add(new JLabel("XX"))); break;
		case 3: add(showStrikes.add(new JLabel("XXX"))); break;
		default: break;
		}
		
//		try { Thread.sleep(Main.DRAMATIC_PAUSE); } catch (InterruptedException e) { e.printStackTrace(); } // pause for dramatic effect
		strikeLabel.setText("Strikes: " + Integer.toString(Main.getCUR_TEAM().getStrikeCount())); 
		mainPanel.setVisible(true);
	}
	
	public void switchTeamLabel() { teamLabel.setText("'" + Main.getCUR_TEAM().getName() + "' Controls"); }
	public void switchPlayerLabel(String s) { playerLabel.setText("'" + s + "'"); }
}
