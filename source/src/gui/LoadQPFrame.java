package gui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import obj.QuestionPack;
import classes.Main;
import classes.Text;

public class LoadQPFrame extends JFrame {

	private JPanel contentPane;
	private ArrayList<QuestionPack> qpacks;
	private JButton loadButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadQPFrame frame = new LoadQPFrame();
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
	public LoadQPFrame(final ArrayList<QuestionPack> qpacks) {
		setTitle("Select Question Pack");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));

		final JList qpackList = new JList();
		qpackList.setListData(qpacks.toArray());
		qpackList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				loadButton.setEnabled(true);
			}
		});
		contentPane.add(qpackList);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);

		loadButton = new JButton("Load");
		loadButton.setEnabled(false);
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setQPack(qpacks.get(qpackList.getSelectedIndex())); 
				Main.showCreateTeamFrame();
				dispose();
			}
		});
		panel_1.add(loadButton);

		JButton newButton = new JButton("New");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.showPacknamePrompt();
				dispose();
			}
		});
		panel_1.add(newButton);
	}
}
