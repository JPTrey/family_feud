package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import obj.Answer;
import obj.Question;
import classes.Main;
import classes.Text;

/**
 * Window displayed to players;
 * Displays question text, total points, current team, total strikes and, when selected, answer labels
 * @author Jon Paul
 */
public class PlayWindow extends JFrame {
	private static PlayFrame 	frame;
	private Question			q;			// this window's question
	private JPanel				mainPanel, backgroundPanel, textPanel, numPanel, questPanel, ansPanel, strikePanel, pointsPanel, teamPanel, showStrikes;
	private JLabel				background, questLabel, strikeLabel, pointsLabel, teamLabel, playerLabel,
	a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;		// answers #1-10
	private ArrayList<JLabel>  	ansLabels;
	private int					totalPoints, ansRemain;

	public PlayWindow(String title, final Question q) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new PlayFrame(q);
					frame.setVisible(true);
					frame.setTeamName(Main.getTEAM_NAME(0), 1);
					frame.setTeamName(Main.getTEAM_NAME(1), 2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Prepares question text and concealed answer JLabels.  
	 * Called by Main.makePlayWindow
	 */
	public void setQuestion(Question q) {
		frame.registerQuestion(q);
	}

	private JPanel setupQuestPanel() {
		if (questPanel == null) {
			questPanel = new JPanel();
			questLabel = new JLabel();
			questPanel.setSize(Main.getPLAY_DIM().width, 50);
			questLabel.setVisible(true);
			questPanel.add(questLabel);
		}
		questLabel.setText(q.getText());
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
			pointsLabel = new JLabel();
			pointsPanel.add(pointsLabel);
		}
		totalPoints = 0;
		pointsLabel.setText("Points: " + totalPoints);
		return pointsPanel;
	}

	private JPanel setupTeamPanel() {
		if (teamPanel == null) {
			teamPanel = new JPanel();
			teamLabel = new JLabel();
			playerLabel = new JLabel();
			teamPanel.add(teamLabel);
			teamPanel.add(playerLabel);
		}
		teamLabel.setText("No Team Controls!");;
		return teamPanel;
	}

	/**
	 * Called when either:
	 * An answer was found in the dictionary or;
	 * An answer was selected in AdminWindow
	 * @param slot 
	 * @param ans answer selected for reveal
	 */
	public void revealAnswer(String ansText, int slot) {	
		frame.revealAnswer(ansText, slot);
	}

	/**
	 * Called when a new question is set
	 */
	private void removeAnswers() {
		for (int i=0; i<ansLabels.size(); i++) {
			ansLabels.remove(i);
		}
	}

	public void setPoints(int cur_points) { 
		//		pointsLabel.setText("Points: " + Integer.toString(cur_points));
		frame.setPoints(cur_points);
	}

	public void setStrikes(int strikeCount) { 
		frame.setStrikes(strikeCount);
	}

	public void switchTeamLabel(int cur_team) { frame.setCurrentTeam(cur_team);; }
	public void setTeamLabel(int team) { frame.setCurrentTeam(team); }
	public void removeTeamLabel(int team) { frame.removeTeamLabel(team); }

	/*
	 * Supplemented Code
	 */

	// Set up contraints so that the user supplied component and the
	// background image label overlap and resize identically
	private static final GridBagConstraints gbc;
	static {
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTHWEST;
	}

	/**
	 * Wraps a Swing JComponent in a background image. Simply invokes the overloded
	 * variant with Top/Leading alignment for background image.
	 *
	 * @param component - to wrap in the a background image
	 * @param backgroundIcon - the background image (Icon)
	 * @return the wrapping JPanel
	 */
	public static JPanel wrapInBackgroundImage(JComponent component,
			Icon backgroundIcon) {
		return wrapInBackgroundImage(
				component,
				backgroundIcon,
				JLabel.TOP,
				JLabel.LEADING);
	}

	/**
	 * Wraps a Swing JComponent in a background image. The vertical and horizontal
	 * alignment of background image can be specified using the alignment
	 * contants from JLabel.
	 *
	 * @param component - to wrap in the a background image
	 * @param backgroundIcon - the background image (Icon)
	 * @param verticalAlignment - vertical alignment. See contants in JLabel.
	 * @param horizontalAlignment - horizontal alignment. See contants in JLabel.
	 * @return the wrapping JPanel
	 */
	public static JPanel wrapInBackgroundImage(JComponent component,
			Icon backgroundIcon,
			int verticalAlignment,
			int horizontalAlignment) {

		// make the passed in swing component transparent
		component.setOpaque(false);

		// create wrapper JPanel
		JPanel backgroundPanel = new JPanel(new GridBagLayout());

		// add the passed in swing component first to ensure that it is in front
		backgroundPanel.add(component, gbc);

		// create a label to paint the background image
		JLabel backgroundImage = new JLabel(backgroundIcon);

		// set minimum and preferred sizes so that the size of the image
		// does not affect the layout size
		backgroundImage.setPreferredSize(new Dimension(1,1));
		backgroundImage.setMinimumSize(new Dimension(1,1));

		// align the image as specified.
		backgroundImage.setVerticalAlignment(verticalAlignment);
		backgroundImage.setHorizontalAlignment(horizontalAlignment);

		// add the background label
		backgroundPanel.add(backgroundImage, gbc);

		// return the wrapper
		return backgroundPanel;
	}

	/**
	 * Updates team points label on the PlayFrame.
	 * @param team current team
	 * @param points points to be displayed
	 */
	public void setTeamPoints(int points, int team) {
		frame.setTeamPoints(points, team);
	}

	public void storeAnswer(String ansText, int slot) {
		frame.storeAnswer(ansText, slot);
	}
}
