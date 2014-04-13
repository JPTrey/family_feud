package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import classes.Main;
import classes.Text;

public class CreateTeamFrame extends JFrame {

	private JPanel contentPane;
	private JTextField teamTextField;
	private JTextField txtPlayer1;
	private JTextField txtPlayer5;
	private JTextField txtPlayer4;
	private JTextField txtPlayer3;
	private JTextField txtPlayer2;
	private JSeparator separator_1;
	private JPanel panel;
	private JButton addButton;
	private int		team_num;		// team being created

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTeamFrame frame = new CreateTeamFrame();
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
	public CreateTeamFrame() {
		setTitle("Create Team");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel namePanel = new JPanel();
		contentPane.add(namePanel, BorderLayout.NORTH);

		teamTextField = new JTextField();
		teamTextField.setHorizontalAlignment(SwingConstants.LEFT);
		teamTextField.setText("Team Name");
		teamTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (teamTextField.getText().equalsIgnoreCase("Team Name")) {
					teamTextField.setText("");
					addButton.setEnabled(false);
				}

				else if (teamTextField.getText().equalsIgnoreCase("")) {
					addButton.setEnabled(false);
				}

				else {
					addButton.setEnabled(true);
				}

				teamTextField.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent arg0) {		
				if (teamTextField.getText().equalsIgnoreCase("")) { 
					teamTextField.setForeground(Color.GRAY);
					teamTextField.setText("Team Name");
					addButton.setEnabled(false);
				} 

				else if (teamTextField.getText().equalsIgnoreCase("Team Name")) { 
					addButton.setEnabled(false); 
				}

				else { 
					addButton.setEnabled(true); 
				}
			}
		});
		namePanel.add(teamTextField);
		teamTextField.setToolTipText("Team Name");
		teamTextField.setColumns(10);

		JPanel playersPanel = new JPanel();
		contentPane.add(playersPanel, BorderLayout.CENTER);
		playersPanel.setLayout(new GridLayout(0, 1, 0, 0));

		txtPlayer1 = new JTextField();
		txtPlayer1.setText("Player 1");
		txtPlayer1.setName("");
		txtPlayer1.setForeground(Color.GRAY);
		txtPlayer1.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtPlayer1.getText().equalsIgnoreCase("Player 1")) {
					txtPlayer1.setText("");
				}
				txtPlayer1.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent arg0) {		
				if (txtPlayer1.getText().equalsIgnoreCase("")) { 
					txtPlayer1.setForeground(Color.GRAY);
					txtPlayer1.setText("Player 1");

				}
			}
		});
		playersPanel.add(txtPlayer1);
		txtPlayer1.setColumns(10);

		txtPlayer2 = new JTextField();
		txtPlayer2.setText("Player 2");
		txtPlayer2.setForeground(Color.GRAY);
		txtPlayer2.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtPlayer2.getText().equalsIgnoreCase("Player 2")) {
					txtPlayer2.setText("");
				}
				txtPlayer2.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent arg0) {		
				if (txtPlayer2.getText().equalsIgnoreCase("")) { 
					txtPlayer2.setForeground(Color.GRAY);
					txtPlayer2.setText("Player 2");

				}
			}
		});
		txtPlayer2.setColumns(10);
		playersPanel.add(txtPlayer2);

		txtPlayer3 = new JTextField();
		txtPlayer3.setText("Player 3");
		txtPlayer3.setForeground(Color.GRAY);
		txtPlayer3.setColumns(10);
		txtPlayer3.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtPlayer3.getText().equalsIgnoreCase("Player 3")) {
					txtPlayer3.setText("");
				}
				txtPlayer3.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent arg0) {		
				if (txtPlayer3.getText().equalsIgnoreCase("")) { 
					txtPlayer3.setForeground(Color.GRAY);
					txtPlayer3.setText("Player 3");

				}
			}
		});
		playersPanel.add(txtPlayer3);

		txtPlayer4 = new JTextField();
		txtPlayer4.setText("Player 4");
		txtPlayer4.setForeground(Color.GRAY);
		txtPlayer4.setColumns(10);
		txtPlayer4.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtPlayer4.getText().equalsIgnoreCase("Player 4")) {
					txtPlayer4.setText("");
				}
				txtPlayer4.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent arg0) {		
				if (txtPlayer4.getText().equalsIgnoreCase("")) { 
					txtPlayer4.setForeground(Color.GRAY);
					txtPlayer4.setText("Player 4");

				}
			}
		});
		playersPanel.add(txtPlayer4);

		txtPlayer5 = new JTextField();
		txtPlayer5.setText("Player 5");
		txtPlayer5.setForeground(Color.GRAY);
		txtPlayer5.setColumns(10);
		txtPlayer5.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtPlayer5.getText().equalsIgnoreCase("Player 5")) {
					txtPlayer5.setText("");
				}
				txtPlayer5.setForeground(Color.BLACK);
			}

			@Override
			public void focusLost(FocusEvent arg0) {		
				if (txtPlayer5.getText().equalsIgnoreCase("")) { 
					txtPlayer5.setForeground(Color.GRAY);
					txtPlayer5.setText("Player 5");

				}
			}
		});
		playersPanel.add(txtPlayer5);

		separator_1 = new JSeparator();
		separator_1.setPreferredSize(new Dimension(150, 12));
		contentPane.add(separator_1, BorderLayout.EAST);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setPreferredSize(new Dimension(10, 60));
		contentPane.add(panel, BorderLayout.SOUTH);

		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ON_CLOSE();
				if (Main.getTEAM_COUNT() < Main.MAX_TEAMS) {
					Main.showCreateTeamFrame();
					Text.debug("Making new CreateTeamFrame");
				}

				else {
					Main.setCUR_TEAM(-1);
					Main.nextQuestion();
				}
				dispose();
			}
		});
		panel.add(addButton);
	}

	/**
	 * Sends user input to Main for constructing teams.
	 */
	private void ON_CLOSE() {
		ArrayList<String> playerNames = new ArrayList<String>();

		if (txtPlayer1.getText().equalsIgnoreCase("Player 1"));
		else playerNames.add(txtPlayer1.getText());
		if (txtPlayer2.getText().equalsIgnoreCase("Player 2"));
		else playerNames.add(txtPlayer2.getText());
		if (txtPlayer3.getText().equalsIgnoreCase("Player 3"));
		else playerNames.add(txtPlayer3.getText());
		if (txtPlayer4.getText().equalsIgnoreCase("Player 4"));
		else playerNames.add(txtPlayer4.getText());
		if (txtPlayer5.getText().equalsIgnoreCase("Player 5"));
		else playerNames.add(txtPlayer5.getText());

		Main.addTeam(teamTextField.getText(), playerNames);
	}

}
