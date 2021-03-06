package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import classes.Main;

/**
 * Initial menu window when program launches
 * @author jonpaulsimonelli
 */
public class MenuWindow extends JFrame {
	private JPanel			mainPanel, playPanel, loadPanel, exitPanel, aboutPanel;
	private JTextArea		textArea;
	private JButton			playButton, loadButton, confirmLoadButton, quitButton, aboutButton;

	/**
	 * Called when loadButton is pressed
	 */
	private void load() {
		textArea = new JTextArea();
		textArea.setSize(500,100);
		confirmLoadButton = new JButton("OK");
		confirmLoadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) { confirmLoad(); }
		});
		loadPanel.add(textArea, BorderLayout.CENTER);
		loadPanel.add(confirmLoadButton, BorderLayout.EAST);
	}

	/**
	 * Called when confirmLoadButton is pressed
	 */
	private void confirmLoad() { 
		Main.setQUESTIONS(new File(textArea.getText() + ".txt")); 
		playButton.setVisible(true);
	}

	public MenuWindow() {
		mainPanel = new JPanel(new GridLayout(3,1,100,100));
		playPanel = new JPanel();
		loadPanel = new JPanel(new GridLayout(1,2,100,100));
		exitPanel = new JPanel();
		
		playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Main.playGame();
			}
		});
		playButton.setVisible(false);
		playPanel.add(playButton);
		
		loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				load();
			}
		});
		loadPanel.add(loadButton, BorderLayout.WEST);
		loadPanel.setBounds(new Rectangle(10,20));
		
		textArea = new JTextArea();
		loadPanel.add(textArea, BorderLayout.CENTER);
		
		quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Main.EXIT();
			}
		});	
		exitPanel.add(quitButton);
		
		mainPanel.add(playPanel);
		mainPanel.add(loadPanel);
		mainPanel.add(exitPanel);
		add(mainPanel);
		
		setSize(Main.getMENU_DIM());
		setTitle(Main.PLAY_TITLE + " Configuation");
		setVisible(true);
	}

	public static void main(String[] args) {
		MenuWindow mw = new MenuWindow();
	}
}
