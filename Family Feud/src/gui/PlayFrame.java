package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import obj.Question;
import classes.Text;

public class PlayFrame extends JFrame {

	// TODO fix formatting error with less than 10 questions
	// TODO fix issue with text wrapping
	
	private JPanel contentPane;
	private Question q;
	private static PlayFrame frame;
	private JLabel ansLabel1, ansLabel2, ansLabel3, ansLabel4, ansLabel5, 
	ansLabel6, ansLabel7, ansLabel8, ansLabel9, ansLabel10,
	pointsLabel, questLabel, strikesLabel, teamLabel1, teamLabel2, teamPoints1, teamPoints2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					frame = new PlayFrame(9);
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
	public PlayFrame(Question q) {
		registerQuestion(q);
		
		int ansCount = q.answerCount();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.BLUE);
		infoPanel.setForeground(Color.BLUE);
		contentPane.add(infoPanel, BorderLayout.NORTH);
		infoPanel.setLayout(new BorderLayout(0,0));

		questLabel = new JLabel(q.getText());
		questLabel.setForeground(new Color(255, 255, 255));
		questLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		questLabel.setFont(new Font("MingLiU-ExtB", Font.BOLD, 40));
		questLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(questLabel, BorderLayout.SOUTH);

		JPanel teamPanel = new JPanel();
		teamPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		teamPanel.setBackground(new Color(255, 165, 0));
		teamPanel.setPreferredSize(new Dimension(250, 10));
		teamPanel.setForeground(Color.BLUE);
		infoPanel.add(teamPanel, BorderLayout.EAST);
		teamPanel.setLayout(new GridLayout(0, 2, 0, 0));

		teamLabel1 = new JLabel("Team 1");
		teamLabel1.setFont(new Font("Marker Felt", Font.PLAIN, 25));
		teamPanel.add(teamLabel1);

		teamPoints1 = new JLabel("New label");
		teamPoints1.setFont(new Font("Marker Felt", Font.PLAIN, 25));
		teamPanel.add(teamPoints1);

		teamLabel2 = new JLabel("Team 2");
		teamLabel2.setFont(new Font("Marker Felt", Font.PLAIN, 25));
		teamPanel.add(teamLabel2);

		teamPoints2 = new JLabel("New label");
		teamPoints2.setFont(new Font("Marker Felt", Font.PLAIN, 25));
		teamPanel.add(teamPoints2);

		JPanel pointsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pointsPanel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		pointsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pointsPanel.setBackground(Color.BLUE);
		infoPanel.add(pointsPanel, BorderLayout.CENTER);

		pointsLabel = new JLabel("0");
		pointsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		pointsLabel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 0), null, null, null));
		pointsPanel.add(pointsLabel);
		pointsLabel.setBackground(new Color(30, 144, 255));
		pointsLabel.setForeground(new Color(255, 255, 0));
		pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pointsLabel.setFont(new Font("Princetown LET", Font.BOLD, 80));

		JPanel strikesPanel = new JPanel();
		strikesPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		strikesPanel.setPreferredSize(new Dimension(300, 10));
		strikesPanel.setBackground(new Color(255, 165, 0));
		infoPanel.add(strikesPanel, BorderLayout.WEST);

		strikesLabel = new JLabel();
		strikesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		strikesPanel.add(strikesLabel);
		strikesLabel.setForeground(Color.RED);
		strikesLabel.setFont(new Font("Wide Latin", Font.PLAIN, 70));

		JPanel ansPanel = new JPanel();
		ansPanel.setBackground(new Color(255, 165, 0));
		ansPanel.setForeground(Color.BLUE);
		contentPane.add(ansPanel, BorderLayout.CENTER);
		ansPanel.setLayout(new BorderLayout(0, 0));

		JPanel ansCol15 = new JPanel();
		ansCol15.setBackground(new Color(255, 215, 0));
		ansCol15.setPreferredSize(new Dimension(462, 10));
		ansCol15.setBounds(new Rectangle(0, 0, 100, 0));
		ansPanel.add(ansCol15, BorderLayout.WEST);
		ansCol15.setLayout(new GridLayout(0, 1, 0, 0));

		// create Label for each answer

		if (ansCount >= 1) {
			ansLabel1 = new JLabel("1");
			ansLabel1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			ansLabel1.setBackground(new Color(255, 215, 0));
			ansLabel1.setFont(new Font("Krungthep", Font.PLAIN, 30));
			ansCol15.add(ansLabel1);
			ansLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if (ansCount >= 2) {
			ansLabel2 = new JLabel("2");
			ansLabel2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			ansLabel2.setBackground(new Color(255, 215, 0));
			ansLabel2.setFont(new Font("Krungthep", Font.PLAIN, 30));
			ansCol15.add(ansLabel2);
			ansLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if (ansCount >= 3) {
			ansLabel3 = new JLabel("3");
			ansLabel3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			ansLabel3.setBackground(new Color(255, 215, 0));
			ansLabel3.setFont(new Font("Krungthep", Font.PLAIN, 30));
			ansCol15.add(ansLabel3);
			ansLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if (ansCount >= 4) {
			ansLabel4 = new JLabel("4");
			ansLabel4.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			ansLabel4.setBackground(new Color(255, 215, 0));
			ansLabel4.setFont(new Font("Krungthep", Font.PLAIN, 30));
			ansCol15.add(ansLabel4);
			ansLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if (ansCount >= 5) {
			ansLabel5 = new JLabel("5");
			ansLabel5.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			ansLabel5.setBackground(new Color(255, 215, 0));
			ansLabel5.setFont(new Font("Krungthep", Font.PLAIN, 30));
			ansCol15.add(ansLabel5);
			ansLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		}

		JPanel ansCol610 = new JPanel();
		ansCol610.setBackground(new Color(255, 215, 0));
		ansCol610.setPreferredSize(new Dimension(462, 10));
		ansPanel.add(ansCol610, BorderLayout.EAST);
		ansCol610.setLayout(new GridLayout(0, 1, 0, 0));

		if (ansCount >= 6) {
			ansLabel6 = new JLabel("6");
			ansLabel6.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			ansLabel6.setFont(new Font("Krungthep", Font.PLAIN, 30));
			ansCol610.add(ansLabel6);
			ansLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if (ansCount >= 7) {
			ansLabel7 = new JLabel("7");
			ansLabel7.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			ansLabel7.setBackground(new Color(255, 215, 0));
			ansLabel7.setFont(new Font("Krungthep", Font.PLAIN, 30));
			ansCol610.add(ansLabel7);
			ansLabel7.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if (ansCount >= 8) {
			ansLabel8 = new JLabel("8");
			ansLabel8.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			ansLabel8.setBackground(new Color(255, 215, 0));
			ansLabel8.setFont(new Font("Krungthep", Font.PLAIN, 30));
			ansCol610.add(ansLabel8);
			ansLabel8.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if (ansCount >= 9) {
			ansLabel9 = new JLabel("9");
			ansLabel9.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			ansLabel9.setBackground(new Color(255, 215, 0));
			ansLabel9.setFont(new Font("Krungthep", Font.PLAIN, 30));
			ansCol610.add(ansLabel9);
			ansLabel9.setHorizontalAlignment(SwingConstants.CENTER);
		}

		if (ansCount >= 10) {
			ansLabel10 = new JLabel("10");
			ansLabel10.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			ansLabel10.setBackground(new Color(255, 215, 0));
			ansLabel10.setFont(new Font("Krungthep", Font.PLAIN, 30));
			ansCol610.add(ansLabel10);
			ansLabel10.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	public void registerQuestion(Question q) {
		this.q = q;
	}
	
	public void revealAnswer(String ansText, int slot) {
		switch (slot) {				// assign a button for each answer
		case 10:	ansLabel10.setText(ansText); break;
		case 9:		ansLabel9.setText(ansText); break;
		case 8:		ansLabel8.setText(ansText); break;
		case 7: 	ansLabel7.setText(ansText); break;
		case 6:		ansLabel6.setText(ansText); break;
		case 5:		ansLabel5.setText(ansText); break;
		case 4:		ansLabel4.setText(ansText); break;
		case 3:		ansLabel3.setText(ansText); break;
		case 2:		ansLabel2.setText(ansText); break;
		case 1:		ansLabel1.setText(ansText); break;
		default: 	Text.debug("Answers not found!"); break;
		}
	}
	
	public void setPoints(int points) {
		pointsLabel.setText(Integer.toString(points));
	}
	
	public void setTeamName(String name, int team) {
		if (team == 1) {
			teamLabel1.setText(name);
		}
		
		else {
			teamLabel2.setText(name);
		}
	}
	
	public void setTeamPoints(int points, int team) {
		if (team == 1) {
			teamPoints1.setText(Integer.toString(points));
		}
		
		else {
			teamPoints2.setText(Integer.toString(points));
		}
	}
	
	public void setStrikes(int strikeCount) {
		String strikes = "";
		for (int i=0; i<strikeCount; i++) {
			strikes += "X";
		}
		strikesLabel.setText(strikes);
	}

	public void switchTeam() {
		// TODO grey out opposite team label
		
	}
}
