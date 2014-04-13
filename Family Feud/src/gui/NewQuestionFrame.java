package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class NewQuestionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtQuestionText;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewQuestionFrame frame = new NewQuestionFrame();
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
	public NewQuestionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 3, 0, 0));
		
		JPanel questionPanel = new JPanel();
		questionPanel.setPreferredSize(new Dimension(5, 10));
		FlowLayout flowLayout = (FlowLayout) questionPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(questionPanel);
		
		txtQuestionText = new JTextField();
		questionPanel.add(txtQuestionText);
		txtQuestionText.setText("Question Text:");
		txtQuestionText.setColumns(10);
		
		JPanel ansPanel = new JPanel();
		contentPane.add(ansPanel);
		ansPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel ansPanel610 = new JPanel();
		ansPanel.add(ansPanel610);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		ansPanel610.add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		ansPanel610.add(textField_16);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		ansPanel610.add(textField_17);
		
		textField_18 = new JTextField();
		textField_18.setColumns(10);
		ansPanel610.add(textField_18);
		
		textField_19 = new JTextField();
		textField_19.setColumns(10);
		ansPanel610.add(textField_19);
		
		JPanel ansPanel15 = new JPanel();
		ansPanel.add(ansPanel15);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		ansPanel15.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		ansPanel15.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		ansPanel15.add(textField_7);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		ansPanel15.add(textField_9);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		ansPanel15.add(textField_8);
	}

}
