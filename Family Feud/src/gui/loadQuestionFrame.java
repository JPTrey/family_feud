package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import obj.QuestionPack;
import classes.Main;
import classes.Text;

import java.awt.GridLayout;

import javax.swing.JSeparator;

public class loadQuestionFrame extends JFrame {

	private JPanel contentPane;
	private JButton selectButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loadQuestionFrame frame = new loadQuestionFrame();
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
	public loadQuestionFrame(QuestionPack qpack) {
		setTitle("Select Question");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		final JList questList = new JList(qpack.getQuestions());
		questList.setSelectedIndex(-1);
		questList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		questList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				selectButton.setEnabled(true);
			}
		});	
		contentPane.add(questList, BorderLayout.CENTER);
		
		JPanel selectPanel = new JPanel();
		contentPane.add(selectPanel, BorderLayout.EAST);
		selectPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		selectButton = new JButton("Select");
		selectPanel.add(selectButton);
		selectButton.setPreferredSize(new Dimension(81, 20));
		selectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.newQuestion(questList.getSelectedIndex());
				dispose();
			}			
		});
		selectButton.setEnabled(false);
		
		final JButton fastMoneyButton = new JButton("Fast Money");
		fastMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.setFAST_MONEY();
				setTitle("Select Five Questions");
				questList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				questList.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {
						Text.debug((questList.getSelectedIndices().length + " " + questList.getSelectedValues().length));
						if (questList.getSelectedValues().length == 5) {
							Text.debug("Showing Select Button");
							selectButton.setEnabled(true);
						}
					}
				});	
				
				selectButton.setEnabled(false);
				fastMoneyButton.setEnabled(false);
			}
		});
		selectPanel.add(fastMoneyButton);
	}

}
