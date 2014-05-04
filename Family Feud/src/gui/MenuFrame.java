package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Main;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;

public class MenuFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
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
	public MenuFrame() {
		setTitle("Family Feud");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAboutFamilyFeud = new JMenuItem("About Family Feud");
		mnFile.add(mntmAboutFamilyFeud);
		
		JMenuItem mntmNewQuestionPack = new JMenuItem("New Question Pack...");
		mnFile.add(mntmNewQuestionPack);
		
		JMenuItem mntmQuitFamilyFeud = new JMenuItem("Quit Family Feud");
		mnFile.add(mntmQuitFamilyFeud);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JCheckBoxMenuItem chckbxmntmCycleEveryAnswer = new JCheckBoxMenuItem("Cycle Every Answer");
		mnOptions.add(chckbxmntmCycleEveryAnswer);
		
		JCheckBoxMenuItem chckbxmntmRandomizeQuestions = new JCheckBoxMenuItem("Randomize Questions");
		mnOptions.add(chckbxmntmRandomizeQuestions);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("center:241px"),},
			new RowSpec[] {
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("29px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		final JButton button = new JButton("Start Game");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setTitle("Loading...");
				button.setEnabled(false);
				Main.playGame();
				dispose();
			}
		});
		contentPane.add(button, "1, 4, center, top");
		
		JButton newButton = new JButton("Create New");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.showPacknamePrompt();
			}
		});
		contentPane.add(newButton, "1, 8, center, top");
		
		JButton btnAbout = new JButton("About");
		contentPane.add(btnAbout, "1, 12, center, top");
	}

}
