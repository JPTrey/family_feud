package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

public class WinnerFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblTeamXWins;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinnerFrame frame = new WinnerFrame();
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
	public WinnerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(139, 0, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblTeamXWins = new JLabel("Team X Wins");
		lblTeamXWins.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamXWins.setForeground(new Color(255, 255, 0));
		lblTeamXWins.setFont(new Font("Kino MT", Font.BOLD, 60));
		panel.add(lblTeamXWins, BorderLayout.CENTER);
	}

	public void setWinnerText(String string) {
		lblTeamXWins.setText(string);
	}

	public void setWinnerPoints(String string) {
	}

	
}
