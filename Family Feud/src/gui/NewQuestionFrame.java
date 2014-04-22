package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import obj.Answer;
import obj.Question;
import classes.Main;
import classes.Text;

public class NewQuestionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtQuestionText;
	private JTextField txtAnswer6;
	private JTextField txtAnswer7;
	private JTextField txtAnswer8;
	private JTextField txtAnswer10;
	private JTextField txtAnswer9;
	private JTextField txtAnswer1;
	private JTextField txtAnswer2;
	private JTextField txtAnswer3;
	private JTextField txtAnswer4;
	private JTextField txtAnswer5;
	private JButton addQuestionButton;
	private JTextField txtPts1;
	private JTextField txtPts2;
	private JTextField txtPts3;
	private JTextField txtPts4;
	private JTextField txtPts5;
	private JTextField txtPts6;
	private JTextField txtPts7;
	private JTextField txtPts8;
	private JTextField txtPts9;
	private JTextField txtPts10;

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
		setTitle("Create Question");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 0};
		gbl_contentPane.rowHeights = new int[]{42, 229, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JPanel questionPanel = new JPanel();
		questionPanel.setPreferredSize(new Dimension(5, 10));
		FlowLayout flowLayout = (FlowLayout) questionPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_questionPanel = new GridBagConstraints();
		gbc_questionPanel.fill = GridBagConstraints.BOTH;
		gbc_questionPanel.insets = new Insets(0, 0, 5, 0);
		gbc_questionPanel.gridx = 0;
		gbc_questionPanel.gridy = 0;
		contentPane.add(questionPanel, gbc_questionPanel);

		txtQuestionText = new JTextField();
		txtQuestionText.setForeground(Color.GRAY);
		questionPanel.add(txtQuestionText);
		txtQuestionText.setText("Question Text:");
		txtQuestionText.setColumns(30);

		JPanel ansPanel = new JPanel();
		GridBagConstraints gbc_ansPanel = new GridBagConstraints();
		gbc_ansPanel.insets = new Insets(0, 0, 5, 0);
		gbc_ansPanel.fill = GridBagConstraints.BOTH;
		gbc_ansPanel.gridx = 0;
		gbc_ansPanel.gridy = 1;
		contentPane.add(ansPanel, gbc_ansPanel);
		GridBagLayout gbl_ansPanel = new GridBagLayout();
		gbl_ansPanel.columnWidths = new int[]{440, 0};
		gbl_ansPanel.rowHeights = new int[]{103, 107, 0};
		gbl_ansPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_ansPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		ansPanel.setLayout(gbl_ansPanel);

		JPanel ansPanel610 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) ansPanel610.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_ansPanel610 = new GridBagConstraints();
		gbc_ansPanel610.fill = GridBagConstraints.BOTH;
		gbc_ansPanel610.insets = new Insets(0, 0, 5, 0);
		gbc_ansPanel610.gridx = 0;
		gbc_ansPanel610.gridy = 0;
		ansPanel.add(ansPanel610, gbc_ansPanel610);

		txtAnswer1 = new JTextField();
		txtAnswer1.setForeground(Color.GRAY);
		txtAnswer1.setText("empty");
		txtAnswer1.setColumns(10);
		ansPanel610.add(txtAnswer1);

		txtPts1 = new JTextField();
		txtPts1.setForeground(Color.GRAY);
		txtPts1.setToolTipText("Points assigned to this answer (0-100).");
		txtPts1.setText("0");
		ansPanel610.add(txtPts1);
		txtPts1.setColumns(2);

		txtAnswer2 = new JTextField();
		txtAnswer2.setForeground(Color.GRAY);
		txtAnswer2.setText("empty");
		txtAnswer2.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtAnswer2.getText().equalsIgnoreCase("empty")) {
					txtAnswer2.setText("");
					txtAnswer2.setForeground(Color.BLACK);
				}
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {		
				if (txtAnswer2.getText().equalsIgnoreCase("")) { 
					txtAnswer2.setForeground(Color.GRAY);
					txtAnswer2.setText("empty");
				} 
				else if (txtPts2.getText().equals("")) {
					addQuestionButton.setEnabled(false);
				}
				else {
					addQuestionButton.setEnabled(true);
				}
			}
		});
		txtAnswer2.setColumns(10);
		ansPanel610.add(txtAnswer2);

		txtPts2 = new JTextField();
		txtPts2.setText("0");
		txtPts2.setForeground(Color.GRAY);
		txtPts2.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtPts2.getText().equalsIgnoreCase("0")) {
					txtPts2.setText("");
					txtPts2.setForeground(Color.BLACK);
				}
				
			}

			@Override
			public void focusLost(FocusEvent arg0) {		
				if (txtAnswer2.getText().equalsIgnoreCase("empty")) { 
					addQuestionButton.setEnabled(false);
				} 
				
				if (txtPts2.getText().equals("")) {
					Text.debug("EENT");
					txtPts2.setText("0");
					txtPts2.setForeground(Color.GRAY);
					addQuestionButton.setEnabled(false);
				}
				else {
					addQuestionButton.setEnabled(true);
				}
			}
		});
		txtPts2.setColumns(2);
		ansPanel610.add(txtPts2);
		
		txtAnswer3 = new JTextField();
		txtAnswer3.setForeground(Color.GRAY);
		txtAnswer3.setText("empty");
		txtAnswer3.setColumns(10);
		ansPanel610.add(txtAnswer3);

		txtPts3 = new JTextField();
		txtPts3.setForeground(Color.GRAY);
		txtPts3.setText("0");
		txtPts3.setColumns(2);
		ansPanel610.add(txtPts3);

		txtAnswer4 = new JTextField();
		txtAnswer4.setForeground(Color.GRAY);
		txtAnswer4.setText("empty");
		txtAnswer4.setColumns(10);
		ansPanel610.add(txtAnswer4);

		txtPts4 = new JTextField();
		txtPts4.setForeground(Color.GRAY);
		txtPts4.setText("0");
		txtPts4.setColumns(2);
		ansPanel610.add(txtPts4);

		txtAnswer5 = new JTextField();
		txtAnswer5.setForeground(Color.GRAY);
		txtAnswer5.setText("empty");
		txtAnswer5.setColumns(10);
		ansPanel610.add(txtAnswer5);

		txtPts5 = new JTextField();
		txtPts5.setForeground(Color.GRAY);
		txtPts5.setText("0");
		txtPts5.setColumns(2);
		ansPanel610.add(txtPts5);

		JPanel ansPanel15 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) ansPanel15.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_ansPanel15 = new GridBagConstraints();
		gbc_ansPanel15.fill = GridBagConstraints.BOTH;
		gbc_ansPanel15.gridx = 0;
		gbc_ansPanel15.gridy = 1;
		ansPanel.add(ansPanel15, gbc_ansPanel15);

		txtAnswer6 = new JTextField();
		txtAnswer6.setForeground(Color.GRAY);
		txtAnswer6.setText("empty");
		txtAnswer6.setColumns(10);
		ansPanel15.add(txtAnswer6);

		txtPts6 = new JTextField();
		txtPts6.setForeground(Color.GRAY);
		txtPts6.setText("0");
		txtPts6.setColumns(2);
		ansPanel15.add(txtPts6);

		txtAnswer7 = new JTextField();
		txtAnswer7.setForeground(Color.GRAY);
		txtAnswer7.setText("empty");
		txtAnswer7.setColumns(10);
		ansPanel15.add(txtAnswer7);

		txtPts7 = new JTextField();
		txtPts7.setForeground(Color.GRAY);
		txtPts7.setText("0");
		txtPts7.setColumns(2);
		ansPanel15.add(txtPts7);

		txtAnswer8 = new JTextField();
		txtAnswer8.setForeground(Color.GRAY);
		txtAnswer8.setText("empty");
		txtAnswer8.setColumns(10);
		ansPanel15.add(txtAnswer8);

		txtPts8 = new JTextField();
		txtPts8.setForeground(Color.GRAY);
		txtPts8.setText("0");
		txtPts8.setColumns(2);
		ansPanel15.add(txtPts8);

		txtAnswer9 = new JTextField();
		txtAnswer9.setForeground(Color.GRAY);
		txtAnswer9.setText("empty");
		txtAnswer9.setColumns(10);
		ansPanel15.add(txtAnswer9);

		txtPts9 = new JTextField();
		txtPts9.setForeground(Color.GRAY);
		txtPts9.setText("0");
		txtPts9.setColumns(2);
		ansPanel15.add(txtPts9);

		txtAnswer10 = new JTextField();
		txtAnswer10.setForeground(Color.GRAY);
		txtAnswer10.setText("empty");
		txtAnswer10.setColumns(10);
		ansPanel15.add(txtAnswer10);

		txtPts10 = new JTextField();
		txtPts10.setForeground(Color.GRAY);
		txtPts10.setText("0");
		txtPts10.setColumns(2);
		ansPanel15.add(txtPts10);

		addQuestionButton = new JButton("Add Question");
		addQuestionButton.setEnabled(false);
		addQuestionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Answer> answers = new ArrayList<Answer>();
				if (!txtAnswer1.getText().equalsIgnoreCase("empty") && !txtPts1.getText().equalsIgnoreCase("0")) {
					answers.add(new Answer(txtAnswer1.getText(),Integer.parseInt(txtPts1.getText())));
				}
				if (!txtAnswer2.getText().equalsIgnoreCase("empty") && !txtPts2.getText().equalsIgnoreCase("0")) {
					answers.add(new Answer(txtAnswer2.getText(),Integer.parseInt(txtPts2.getText())));
				}
				if (!txtAnswer3.getText().equalsIgnoreCase("empty") && !txtPts3.getText().equalsIgnoreCase("0")) {
					answers.add(new Answer(txtAnswer3.getText(),Integer.parseInt(txtPts3.getText())));
				}
				if (!txtAnswer4.getText().equalsIgnoreCase("empty") && !txtPts4.getText().equalsIgnoreCase("0")) {
					answers.add(new Answer(txtAnswer4.getText(),Integer.parseInt(txtPts4.getText())));
				}
				if (!txtAnswer5.getText().equalsIgnoreCase("empty") && !txtPts5.getText().equalsIgnoreCase("0")) {
					answers.add(new Answer(txtAnswer5.getText(),Integer.parseInt(txtPts5.getText())));
				}
				if (!txtAnswer6.getText().equalsIgnoreCase("empty") && !txtPts6.getText().equalsIgnoreCase("0")) {
					answers.add(new Answer(txtAnswer6.getText(),Integer.parseInt(txtPts6.getText())));
				}
				if (!txtAnswer7.getText().equalsIgnoreCase("empty") && !txtPts7.getText().equalsIgnoreCase("0")) {
					answers.add(new Answer(txtAnswer7.getText(),Integer.parseInt(txtPts7.getText())));
				}
				if (!txtAnswer8.getText().equalsIgnoreCase("empty") && !txtPts8.getText().equalsIgnoreCase("0")) {
					answers.add(new Answer(txtAnswer8.getText(),Integer.parseInt(txtPts8.getText())));
				}
				if (!txtAnswer9.getText().equalsIgnoreCase("empty") && !txtPts9.getText().equalsIgnoreCase("0")) {
					answers.add(new Answer(txtAnswer9.getText(),Integer.parseInt(txtPts9.getText())));
				}
				if (!txtAnswer10.getText().equalsIgnoreCase("empty") && !txtPts10.getText().equalsIgnoreCase("0")) {
					answers.add(new Answer(txtAnswer10.getText(),Integer.parseInt(txtPts10.getText())));
				}
				Question q = new Question(txtQuestionText.getText(), answers);
				Main.addQuestion(q);
				Main.showCreateQuestionPackWindow(null, null);
				dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(addQuestionButton, gbc_btnNewButton);
	}

}
