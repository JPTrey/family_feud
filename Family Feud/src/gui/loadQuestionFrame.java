package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

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

public class loadQuestionFrame extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		final JList questList = new JList(qpack.getQuestions());
		questList.setSelectedIndex(0);
		questList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		questList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				Main.newQuestion(questList.getSelectedIndex());
			}
		});	
		contentPane.add(questList, BorderLayout.CENTER);
		
		JPanel selectPanel = new JPanel();
		contentPane.add(selectPanel, BorderLayout.EAST);
		
		JButton selectButton = new JButton("Select");
		selectPanel.add(selectButton);
		selectButton.setPreferredSize(new Dimension(81, 20));
		selectButton.setEnabled(false);
	}

}
