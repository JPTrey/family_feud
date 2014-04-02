package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.FlowLayout;

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
		namePanel.add(teamTextField);
		teamTextField.setToolTipText("Team Name");
		teamTextField.setColumns(10);
		
		JPanel playersPanel = new JPanel();
		contentPane.add(playersPanel, BorderLayout.CENTER);
		playersPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtPlayer1 = new JTextField();
		txtPlayer1.setText("Player 1");
		txtPlayer1.setName("");
		playersPanel.add(txtPlayer1);
		txtPlayer1.setColumns(10);
		
		txtPlayer2 = new JTextField();
		txtPlayer2.setText("Player 2");
		txtPlayer2.setColumns(10);
		playersPanel.add(txtPlayer2);
		
		txtPlayer3 = new JTextField();
		txtPlayer3.setText("Player 3");
		txtPlayer3.setColumns(10);
		playersPanel.add(txtPlayer3);
		
		txtPlayer4 = new JTextField();
		txtPlayer4.setText("Player 4");
		txtPlayer4.setColumns(10);
		playersPanel.add(txtPlayer4);
		
		txtPlayer5 = new JTextField();
		txtPlayer5.setText("Player 5");
		txtPlayer5.setColumns(10);
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
		panel.add(addButton);
	}

}
