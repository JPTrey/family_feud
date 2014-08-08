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
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;

public class CreateTeamFrame extends JFrame {

	private JPanel contentPane;
	private JTextField teamTextField;
	private JButton addButton;
	private int		team_num;		// team being created
	private JTextField teamTextField2;

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
		setBounds(new Rectangle(83, 22, 320, 200));
		setTitle("Enter Team Names");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 350, 200);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 320, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {204, 150, 0};
		gbl_contentPane.rowHeights = new int[]{40, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		teamTextField = new JTextField();
		GridBagConstraints gbc_teamTextField = new GridBagConstraints();
		gbc_teamTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_teamTextField.insets = new Insets(0, 0, 5, 5);
		gbc_teamTextField.gridx = 0;
		gbc_teamTextField.gridy = 0;
		contentPane.add(teamTextField, gbc_teamTextField);
		teamTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
		teamTextField.setHorizontalAlignment(SwingConstants.LEFT);
		teamTextField.setText("Team 1 Name");
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
				else if (teamTextField2.getText().equalsIgnoreCase("Team Name")) { 
					addButton.setEnabled(false); 
				}
				
				else { 
					addButton.setEnabled(true); 
				}
			}
		});
		teamTextField.setToolTipText("Team Name");
		teamTextField.setColumns(10);

		teamTextField2 = new JTextField();
		teamTextField2.setToolTipText("Team Name");
		teamTextField2.setText("Team 2 Name");
		teamTextField2.setHorizontalAlignment(SwingConstants.LEFT);
		teamTextField2.setColumns(10);
		teamTextField2.setAlignmentX(0.0f);
		teamTextField2.addFocusListener(new FocusListener() {

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
				else if (teamTextField2.getText().equalsIgnoreCase("Team Name")) { 
					addButton.setEnabled(false); 
				}
				
				else { 
					addButton.setEnabled(true); 
				}
			}
			
		});

		GridBagConstraints gbc_teamTextField2 = new GridBagConstraints();
		gbc_teamTextField2.fill = GridBagConstraints.HORIZONTAL;
		gbc_teamTextField2.insets = new Insets(0, 0, 5, 5);
		gbc_teamTextField2.gridx = 0;
		gbc_teamTextField2.gridy = 1;
		contentPane.add(teamTextField2, gbc_teamTextField2);

		addButton = new JButton("Play");
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.anchor = GridBagConstraints.EAST;
		gbc_addButton.gridx = 1;
		gbc_addButton.gridy = 3;
		contentPane.add(addButton, gbc_addButton);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ON_CLOSE();
					Main.setCUR_TEAM(-1);
					Main.nextQuestion();
				dispose();
			}

		});
	}

	/**
	 * Sends user input to Main for constructing teams.
	 */
	private void ON_CLOSE() {
		Main.addTeam(teamTextField.getText(), null);
		Main.addTeam(teamTextField2.getText(), null);
	}
}
