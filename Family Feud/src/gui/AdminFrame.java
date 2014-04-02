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

public class AdminFrame extends JFrame {

	private static AdminFrame frame;
	private JPanel contentPane;
	private JLabel questLabel;
	private JButton ansButton1, ansButton2, ansButton3, ansButton4, ansButton5, 
	ansButton6, ansButton7, ansButton8, ansButton9, ansButton10;
	private int	ansButton1Pts, ansButton2Pts, ansButton3Pts, ansButton4Pts, ansButton5Pts, 
	ansButton6Pts, ansButton7Pts, ansButton8Pts, ansButton9Pts, ansButton10Pts; 
	private JButton team2Button;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(infoPanel, BorderLayout.NORTH);
		infoPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel questCountLabel = new JLabel("New label");
		infoPanel.add(questCountLabel);

		JLabel ansCountLabel = new JLabel("New label");
		infoPanel.add(ansCountLabel);

		JLabel teamLabel = new JLabel("New label");
		infoPanel.add(teamLabel);

		JLabel strikesLabel = new JLabel("Strikes: ");
		infoPanel.add(strikesLabel);

		questLabel = new JLabel("\"Question Text\"");
		infoPanel.add(questLabel);

		JPanel ansPanel = new JPanel();
		ansPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(ansPanel, BorderLayout.CENTER);
		ansPanel.setLayout(new GridLayout(5, 2, 0, 0));

		ansButton1 = new JButton("New button");
		ansButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton1.setEnabled(false);
				Main.revealAnswer(ansButton1.getText(), ansButton1Pts, 1);
			}
		});
		ansButton1.setVisible(false);

		ansPanel.add(ansButton1);

		ansButton6 = new JButton("New button");
		ansButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ansButton6.setEnabled(false);
				Main.revealAnswer(ansButton6.getText(), ansButton6Pts, 6);
			}
		});
		ansButton6.setVisible(false);
		ansPanel.add(ansButton6);

		ansButton2 = new JButton("New button");
		ansButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton2.setEnabled(false);
				Main.revealAnswer(ansButton2.getText(), ansButton2Pts, 2);
			}
		});
		ansButton2.setVisible(false);
		ansPanel.add(ansButton2);

		ansButton7 = new JButton("New button");
		ansButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton7.setEnabled(false);
				Main.revealAnswer(ansButton7.getText(), ansButton7Pts, 7);
			}
		});
		ansButton7.setVisible(false);
		ansPanel.add(ansButton7);

		ansButton3 = new JButton("New button");
		ansButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton3.setEnabled(false);
				Main.revealAnswer(ansButton3.getText(), ansButton3Pts, 3);
			}
		});
		ansButton3.setVisible(false);
		ansPanel.add(ansButton3);

		ansButton8 = new JButton("New button");
		ansButton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton8.setEnabled(false);
				Main.revealAnswer(ansButton8.getText(), ansButton8Pts, 8);
			}
		});
		ansButton8.setVisible(false);
		ansPanel.add(ansButton8);

		ansButton4 = new JButton("New button");
		ansButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton4.setEnabled(false);
				Main.revealAnswer(ansButton4.getText(), ansButton4Pts, 4);
			}
		});
		ansButton4.setVisible(false);
		ansPanel.add(ansButton4);

		ansButton9 = new JButton("New button");
		ansButton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton9.setEnabled(false);
				Main.revealAnswer(ansButton9.getText(), ansButton9Pts, 9);
			}
		});
		ansButton9.setVisible(false);
		ansPanel.add(ansButton9);

		ansButton5 = new JButton("New button");
		ansButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton5.setEnabled(false);
				Main.revealAnswer(ansButton5.getText(), ansButton5Pts, 5);
			}
		});
		ansButton5.setVisible(false);
		ansPanel.add(ansButton5);

		ansButton10 = new JButton("New button");
		ansButton10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ansButton10.setEnabled(false);
				Main.revealAnswer(ansButton10.getText(), ansButton10Pts, 10);
			}
		});
		ansButton10.setVisible(false);
		ansPanel.add(ansButton10);

		JPanel cmdPanel = new JPanel();
		cmdPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(cmdPanel, BorderLayout.EAST);
		cmdPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JButton strikeButton = new JButton("Strike");
		strikeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.addStrike();
			}
		});
		cmdPanel.add(strikeButton);

		JButton revealAllButton = new JButton("Reveal All");
		cmdPanel.add(revealAllButton);

		JButton nextButton = new JButton("Next Question");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.nextQuestion();
			}
		});
		cmdPanel.add(nextButton);

		JButton team1Button = new JButton("Team 1");
		team1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setCUR_TEAM(0);
			}
		});
		cmdPanel.add(team1Button);
		
		team2Button = new JButton("Team 2");
		team2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setCUR_TEAM(1);
			}
		});
		cmdPanel.add(team2Button);

		JButton endButton = new JButton("End Game");
		cmdPanel.add(endButton);
	}

	public void registerQuestion(Question q) {
		questLabel.setText(q.getText());
		
		switch (q.answerCount()) {				// assign a button for each answer
		case 10:	ansButton10.setText(q.getAnswers().get(9).getBoth()); 
		ansButton10Pts = q.getAnswers().get(9).getPoints(); 
		ansButton10.setVisible(true);
		case 9:		ansButton9.setText(q.getAnswers().get(8).getBoth()); 
		ansButton9Pts = q.getAnswers().get(8).getPoints(); 
		ansButton9.setVisible(true);
		case 8:		ansButton8.setText(q.getAnswers().get(7).getBoth()); 
		ansButton8Pts = q.getAnswers().get(7).getPoints(); 
		ansButton8.setVisible(true);
		case 7: 	ansButton7.setText(q.getAnswers().get(6).getBoth()); 
		ansButton7Pts = q.getAnswers().get(6).getPoints(); 
		ansButton7.setVisible(true);
		case 6:		ansButton6.setText(q.getAnswers().get(5).getBoth()); 
		ansButton6Pts = q.getAnswers().get(5).getPoints(); 
		ansButton6.setVisible(true);
		case 5:		ansButton5.setText(q.getAnswers().get(4).getBoth()); 
		ansButton5Pts = q.getAnswers().get(4).getPoints(); 
		ansButton5.setVisible(true);
		case 4:		ansButton4.setText(q.getAnswers().get(3).getBoth()); 
		ansButton4Pts = q.getAnswers().get(3).getPoints(); 
		ansButton4.setVisible(true);
		case 3:		ansButton3.setText(q.getAnswers().get(2).getBoth()); 
		ansButton3Pts = q.getAnswers().get(2).getPoints(); 
		ansButton3.setVisible(true);
		case 2:		ansButton2.setText(q.getAnswers().get(1).getBoth()); 
		ansButton2Pts = q.getAnswers().get(1).getPoints(); 
		ansButton2.setVisible(true);
		case 1:		ansButton1.setText(q.getAnswers().get(0).getBoth()); 
		ansButton1Pts = q.getAnswers().get(0).getPoints(); 
		ansButton1.setVisible(true);
		break;
		default: 	Text.debug("Answers not found!"); break;
		}
	}

//	public void registerButton(int slot, String info) {
//		switch (slot) {
//		case 4: ansButton4.setText(info); ansButton4.setVisible(true);
//
//
//		default: break;
//
//		}
//	}

}
