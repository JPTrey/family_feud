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
//		setTitle(title);
//		this.q = q;
//		ansRemain = q.answerCount();
////		ansLabels = new ArrayList<JLabel>();
//
//		mainPanel = new JPanel(new BorderLayout());
//		textPanel = new JPanel(new GridLayout(2,1,25,0));
//		numPanel = new JPanel(new GridLayout(4,1,6,0));
//		setQuestion(q);
//
//		backgroundPanel = new JPanel();
//		backgroundPanel.setSize(this.getSize());
//		background = new JLabel(Main.BACKGROUND_ICON_IMG);
//		background.setSize(this.getSize());
//		backgroundPanel.add(background);
////		add(backgroundPanel);
//
//
//		numPanel.add(setupPointsPanel(),BorderLayout.EAST);
//		numPanel.add(setupTeamPanel(),BorderLayout.WEST);
//		numPanel.add(setupStrikePanel(),BorderLayout.CENTER);
//
//		mainPanel.setBackground(new Color(12345));
//		mainPanel.add(textPanel,BorderLayout.CENTER);
//		mainPanel.add(numPanel,BorderLayout.EAST);
////		add(mainPanel);
//
//		this.setContentPane(wrapInBackgroundImage(mainPanel, Main.BACKGROUND_ICON_IMG));
//		
//		setSize(Main.getPLAY_DIM());
//		setVisible(true);
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
		//		int i = 0;		// enables proper order of answer labels
//		ansLabels = new ArrayList<JLabel>();
//
//		if (ansPanel == null) { 
//			ansPanel = new JPanel(new GridLayout(5,2,10,50)); 
//		}
////		else { 
////			removeAnswers(); 
////		}
//
//		this.q = q;
//
//		questPanel = setupQuestPanel();
//		textPanel.add(questPanel);
//		//		if (ansPanel == null) { 
//
//		ansPanel.setVisible(true);
//		textPanel.add(ansPanel);
//		//		}
//
//		//		else { textPanel.remove(ansPanel); }
//
//		for (int i=0; i<10; i++) {
//			JLabel placeholder = new JLabel("blue.jpg");
//			placeholder.setVisible(true);
//			ansLabels.add(i, placeholder);		// placeholder label			
//		}
//		
//		for (int i=0; i<q.answerCount(); i++) {
//			JLabel newAns = new JLabel(q.getAnswers().get(i).getBoth());
//			newAns.setVisible(false);
//			ansLabels.remove(i); 
//			ansLabels.add(i, newAns); 
//			ansPanel.add(ansLabels.get(i));
//		}

		// switch (q.answerCount()) {				// assign a JLabel for each answer
		// case 10: 	
		// 	if (a10 == null) { 
		// 		a10 = new JLabel(); 
		// 		ansPanel.add(a10);
		// 	} 
		// 	a10.setText(q.getAnswers().get(i).getBoth());
		// 	a10.setVisible(false); 
		// 	i++; Text.debug("added " + a10.getText());
		// case 9: 	
		// 	if (a9 == null) { 
		// 		a9 = new JLabel(); 
		// 		ansPanel.add(a9);
		// 	} 
		// 	a9.setText(q.getAnswers().get(i).getBoth());
		// 	a9.setVisible(false); 
		// 	i++; Text.debug("added " + a9.getText());
		// case 8: 	
		// 	if (a8 == null) { 
		// 		a8 = new JLabel(); 
		// 		ansPanel.add(a8);
		// 	} 
		// 	a8.setText(q.getAnswers().get(i).getBoth());
		// 	a8.setVisible(false); 
		// 	i++; Text.debug("added " + a8.getText());	
		// case 7: 	
		// 	if (a7 == null) { 
		// 		a7 = new JLabel(); 
		// 		ansPanel.add(a7);
		// 	} 
		// 	a7.setText(q.getAnswers().get(i).getBoth());
		// 	a7.setVisible(false); 
		// 	i++; Text.debug("added " + a7.getText());	
		// case 6: 	
		// 	if (a6 == null) { 
		// 		a6 = new JLabel(); 
		// 		ansPanel.add(a6);
		// 	} 
		// 	a6.setText(q.getAnswers().get(i).getBoth());
		// 	a6.setVisible(false); 
		// 	i++; Text.debug("added " + a6.getText());
		// case 5: 	
		// 	if (a5 == null) { 
		// 		a5 = new JLabel(); 
		// 		ansPanel.add(a5);
		// 	} 
		// 	a5.setText(q.getAnswers().get(i).getBoth());
		// 	a5.setVisible(false); 
		// 	i++; Text.debug("added " + a5.getText());
		// case 4: 	
		// 	if (a4 == null) { 
		// 		a4 = new JLabel(); 
		// 		ansPanel.add(a4);
		// 	} 
		// 	a4.setText(q.getAnswers().get(i).getBoth());
		// 	a4.setVisible(false); 
		// 	i++; Text.debug("added " + a4.getText());
		// case 3: 	
		// 	if (a3 == null) { 
		// 		a3 = new JLabel(); 
		// 		ansPanel.add(a3);
		// 	} 
		// 	a3.setText(q.getAnswers().get(i).getBoth());
		// 	a3.setVisible(false); 
		// 	i++; Text.debug("added " + a3.getText());
		// case 2: 	
		// 	if (a2 == null) { 
		// 		a2 = new JLabel(); 
		// 		ansPanel.add(a2);
		// 	} 
		// 	a2.setText(q.getAnswers().get(i).getBoth());
		// 	a2.setVisible(false); 
		// 	i++; Text.debug("added " + a2.getText());
		// case 1: 	
		// 	if (a1 == null) { 
		// 		a1 = new JLabel(); 
		// 		ansPanel.add(a1);
		// 	} 
		// 	a1.setText(q.getAnswers().get(i).getBoth());
		// 	a1.setVisible(false); 
		// 	i++; Text.debug("added " + a1.getText());

		//		case 9:		a9 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a9); a9.setVisible(false);
		//		i++; Text.debug("added " + a9.getText());
		//
		//		case 8:		a8 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a8); a8.setVisible(false);
		//		i++; Text.debug("added " + a8.getText());
		//
		//		case 7: 	a7 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a7); a7.setVisible(false);
		//		i++; Text.debug("added " + a7.getText());
		//
		//		case 6:		a6 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a6); a6.setVisible(false);
		//		i++; Text.debug("added " + a6.getText());
		//
		//		case 5:		a5 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a5); a5.setVisible(false);
		//		i++; Text.debug("added " + a5.getText());
		//
		//		case 4:		a4 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a4); a4.setVisible(false);
		//		i++; Text.debug("added " + a4.getText());
		//
		//		case 3:		a3 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a3); a3.setVisible(false);
		//		i++; Text.debug("added " + a3.getText());
		//
		//		case 2:		a2 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a2); a2.setVisible(false);
		//		i++; Text.debug("added " + a2.getText());
		//
		//		case 1:		a1 = new JLabel(q.getAnswers().get(i).getBoth()); ansPanel.add(a1); a1.setVisible(false);
		//		Text.debug("added " + a1.getText()); 
		//		break;
		//		default: 	Text.debug("Answers not found!"); break;


//		setupQuestPanel();
//		setupPointsPanel();
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
	
//	public void revealAnswer(String ansText, int slot) {
////		ansLabels.get(slot).setText(ansText);
//		ansLabels.remove(slot);
//		ansLabels.add(slot, new JLabel(ansText));
////		ansLabels.set(slot, new JLabel(ansText));
//		ansLabels.get(slot).setVisible(true);;
//	}
	public void revealAnswer(String ansText, int slot) {	
		
		frame.revealAnswer(ansText, slot);
		//		try { Thread.sleep(Main.DRAMATIC_PAUSE); } catch (InterruptedException e) { e.printStackTrace();} // pause for dramatic effect

//		for (int i=0; i<ansLabels.size(); i++) {
//			if (ansText.equalsIgnoreCase(ansLabels.get(i).getText())) {
//				ansLabels.get(i).setVisible(true); 
//				break;
//			}
//		}
//
//		// if 		(ans.getBoth().equalsIgnoreCase(a1.getText())) { a1.setVisible(true); }
//		// else if (ans.getBoth().equalsIgnoreCase(a2.getText())) { a2.setVisible(true); }	
//		// else if (ans.getBoth().equalsIgnoreCase(a3.getText())) { a3.setVisible(true); }
//		// else if (ans.getBoth().equalsIgnoreCase(a4.getText())) { a4.setVisible(true); }
//		// else if (ans.getBoth().equalsIgnoreCase(a5.getText())) { a5.setVisible(true); }
//		// else if (ans.getBoth().equalsIgnoreCase(a6.getText())) { a6.setVisible(true); }
//		// else if (ans.getBoth().equalsIgnoreCase(a7.getText())) { a7.setVisible(true); }
//		// else if (ans.getBoth().equalsIgnoreCase(a8.getText())) { a8.setVisible(true); }
//		// else if (ans.getBoth().equalsIgnoreCase(a9.getText())) { a9.setVisible(true); }
//		// else if (ans.getBoth().equalsIgnoreCase(a10.getText())) { a10.setVisible(true); }
//		// else { Text.debug("Button for '" + ans.getText() + "' not found"); }
//		setPoints(ans.getPoints());
//		Text.debug("'" + ans.getText() + "' revealed in PlayWindow for " + ans.getPoints() + " points" );
	}

	/**
	 * Called when a new question is set
	 */
	private void removeAnswers() {

		for (int i=0; i<ansLabels.size(); i++) {
			ansLabels.remove(i);
		}
		
//		switch(q.answerCount())	{
		//		case 10: 	ansPanel.remove(a10);
		//		case 9:		ansPanel.remove(a9);
		//		case 8:		ansPanel.remove(a8);
		//		case 7: 	ansPanel.remove(a7);
		//		case 6:		ansPanel.remove(a6);
		//		case 5:		ansPanel.remove(a5);
		//		case 4:		ansPanel.remove(a4);
		//		case 3:		ansPanel.remove(a3);
		//		case 2:		ansPanel.remove(a2);
		//		case 1:		ansPanel.remove(a1); break;

//		case 10: 	ansPanel.setVisible(true);
//		case 9:		ansPanel.setVisible(true);
//		case 8:		ansPanel.setVisible(true);
//		case 7: 	ansPanel.setVisible(true);
//		case 6:		ansPanel.setVisible(true);
//		case 5:		ansPanel.setVisible(true);
//		case 4:		ansPanel.setVisible(true);
//		case 3:		ansPanel.setVisible(true);
//		case 2:		ansPanel.setVisible(true);
//		case 1:		ansPanel.setVisible(true); break;
//		default: break;
//		}

	}

	public void setPoints(int cur_points) { 
//		pointsLabel.setText("Points: " + Integer.toString(cur_points));
		frame.setPoints(cur_points);
	}

	public void setStrikes(int strikeCount) { 
		frame.setStrikes(strikeCount);
		
		//		try { Thread.sleep(Main.DRAMATIC_PAUSE); } catch (InterruptedException e) { e.printStackTrace(); } // pause for dramatic effect
//		if (showStrikes == null) {
//			showStrikes = new JPanel();
//			showStrikes.setSize(Main.getPLAY_DIM());
//		}
//		mainPanel.setVisible(false);
//
//		switch(Main.getCUR_TEAM().getStrikeCount()) {		// display 'X'
//		case 1: add(showStrikes.add(new JLabel("X"))); Text.debug("Strike 1"); break;
//		case 2: add(showStrikes.add(new JLabel("XX"))); break;
//		case 3: add(showStrikes.add(new JLabel("XXX"))); break;
//		default: break;
//		}
//
//		//		try { Thread.sleep(Main.DRAMATIC_PAUSE); } catch (InterruptedException e) { e.printStackTrace(); } // pause for dramatic effect
//		strikeLabel.setText("Strikes: " + Integer.toString(Main.getCUR_TEAM().getStrikeCount())); 
//		mainPanel.setVisible(true);
	}

	public void switchTeamLabel(int cur_team) { frame.setCurrentTeam(cur_team);; }
	public void setTeamLabel(int team) { frame.setCurrentTeam(team); }
//	public void switchPlayerLabel(String s) { frame.setCurrentPlayer(s); }
	
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
