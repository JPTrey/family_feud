package classes;

import gui.AdminWindow;
import gui.MenuFrame;
import gui.PlayWindow;
import gui.loadQuestionFrame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import obj.Answer;
import obj.Player;
import obj.Question;
import obj.QuestionPack;
import obj.Team;

public class Main {

	/* Global Variables */
	public static boolean DEBUG = true;			// true: debug methods and statements will be shown
	public static int DEBUG_WAIT = 50;		// used for debug output; controls rate of String output (in milliseconds)
	public static int CHAR_WAIT = 0;			// used for text output; controls rate of character output (in milliseconds)
	public static int TEXT_WAIT = 0;			// used for text output; controls pause time when reading '~'
	public static int TEAM_COUNT = 2;
	public static int MAX_ANSWERS = 10;
	public static int MAX_TEAMS = 2;
	public static int MAX_TEAM_SIZE = 10;
	public static int MAX_STRIKES = 3;
	public static int POINTS_TO_WIN = 300;
	public static long DRAMATIC_PAUSE = 1000;	// in milliseconds
	public static String ADMIN_TITLE = "Administrator";
	public static String PLAY_TITLE = "Family Feud";
	public static boolean ALT_EVERY_TURN = false;	// true: team mate switched after every answer 
	public static File QUESTION_FILE = new File("dogs.txt");
	public static Dimension MENU_DIM = new Dimension(800, 600);
	public static Dimension PLAY_DIM = new Dimension(1024, 768);
	public static Dimension ADMIN_DIM = new Dimension(480, 300);
	public static ImageIcon BACKGROUND_ICON_IMG = new ImageIcon("FamilyFeudBoard.jpg");

	/* Private Variable */
	private static QuestionPack qpack;						// collection of questions
	private static Team[] teams = new Team[MAX_TEAMS];
	private static Question cur_question;				// current question sent by QuestionPack
	private static int cur_question_num;
	private static int cur_team, cur_player;		// slot indices for arrays
	private static int cur_turn;					// turn count
	private static int cur_points;
	private static JFrame title, menu;
	private static AdminWindow aw;
	private static PlayWindow pw;
	private static JMenuBar menubar;
	private static Scanner sc = new Scanner(System.in);

	//TODO end after all questions
	//TODO make pretty
	//TODO absolute layout for components
	//TODO startup interface
	public static void main(String[] args) throws FileNotFoundException {
		if (DEBUG) { 	// skip menu            
			playGame();
		} else {
			showMenu();
		}
	}

	/* Setup methods */
	private static void showMenu() {
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

	public static void showLoadQuestionPackWindow() {

	}

	/**
	 * Creates a new PlayWindow for each new question
	 */
	private static void makePlayWindow() {
		if (pw == null) {
			pw = new PlayWindow(PLAY_TITLE, cur_question);
		} else {
			pw.setQuestion(cur_question);
		}
	}

	/**
	 * Creates a new AdminWindow for each new question
	 */
	private static void makeAdminWindow() {
		if (aw == null) {
			aw = new AdminWindow(ADMIN_TITLE + " - Question " + cur_question_num
					+ " of " + qpack.size(), cur_question, pw);
		} else {
			aw.setQuestion(cur_question);
		}
	}

	private static void addTeam(int slot) {
		String name, playerName;
		int choice;

		Text.out("Enter name for team #" + (slot + 1) + ": ");
		name = Input.getString();
		Text.debug("Name = " + name);
		teams[slot] = new Team(name);
		choice = 1;
		while (choice == 1 && teams[slot].size() <= MAX_TEAM_SIZE) {	// while: user still inputs new names
			playerName = null;
			choice = -1;
			Text.out("\nPlayer name: ");
			playerName = Input.getString();
			teams[slot].addPlayer(new Player(playerName));
			Text.out("[1] Add Another\n[2] Done");
			//			cur_player++;
			choice = Input.getInt(2);
		}

		//		cur_player = 0;		// reset
	}

	/**
	 * Builds the selected QuestionPack from 'questions.txt', if found.
	 *
	 * @param name selection by user
	 * @throws FileNotFoundException
	 * @throws InterruptedException TODO run in a separate thread
	 */
	private static void loadQuestions() throws FileNotFoundException {
		Scanner fileSC = new Scanner(QUESTION_FILE);
		String packname, qText, aText;
		ArrayList<Question> questions = new ArrayList<Question>();
		ArrayList<Answer> answers;
		int aPoints;
		boolean foundPack = false;

		if (DEBUG) {
			packname = "dogs";
		} else {
			Text.out("Question Pack name: ");
			packname = (new Scanner(System.in)).nextLine();
		}

		String str = fileSC.nextLine();
		Text.debug("Loading Question Pack '" + packname + "'");
		while (fileSC.hasNextLine() && !str.equalsIgnoreCase("END")) {
			if (str.contains("PACK::")) {
				String[] packInfo = str.split("PACK::");
				Text.debug("Pack = " + packInfo[1]);
				if (packInfo[1].equalsIgnoreCase("'" + packname + "'")) { 			// if: pack is found
					foundPack = true;
					str = fileSC.nextLine();
					Text.debug("Found pack");

					while (str.contains("Q::")) {								// while: questions remain
						String[] qInfo = str.split("Q::");						// prepare questions and answers
						qText = qInfo[1];
						str = fileSC.nextLine();
						answers = new ArrayList<Answer>();						// answers for current question
						Text.debug("Question = " + qText + " {");
						for (int j = 0; str.contains("A::") && j < MAX_ANSWERS; j++) {				// load all answers
							String[] aInfo = str.split("A::");
							str = aInfo[1];
							aInfo = str.split("=");
							aText = aInfo[0];
							aPoints = Integer.parseInt(aInfo[1]);
							answers.add(new Answer(aText, aPoints));
							str = fileSC.nextLine();
							Text.debug("\tAnswer = " + aText + ", " + aPoints + "%");
						}

						if (str.equalsIgnoreCase("END")) {
							Text.debug(str);
						};
						questions.add(new Question(qText, answers));
						Text.debug("Question '" + qText + "' added\n}\n");
					}

				} else if (fileSC.hasNextLine()) {
					str = fileSC.nextLine();
				}
			} else {
				str = fileSC.nextLine();
			}
		}

		Text.debug("str = " + str);
		if (foundPack) {
			qpack = new QuestionPack(packname, questions);
			cur_question_num = 0;
		} else {
			Text.out(packname + " not found! Create a new pack named " + packname + "?\n[0] Yes\n[1] No");
			int choice = Input.getInt(2);
			if (choice == 0) {
				QuestionPacker.makeQuestionPack(packname);
			}
			loadQuestions();
		}
	}

	/* END Setup methods */
	public static void playGame() {
		try {
			loadQuestions();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (DEBUG) { 		// cmd team input
			Scanner sc = new Scanner(System.in);
			showAllQuestions();
			for (int i = 0; i < MAX_TEAMS; i++) {			// placeholder teams
				Text.out("\nTeam " + i + " name: ");
				teams[i] = new Team(sc.nextLine());
				Text.out("\nPlayer " + i + " name: ");
				Text.debug("Team " + (i + 1) + ":Player " + (i + 1));
			}
		} else {
			for (int i = 0; i < MAX_TEAMS; i++) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						new TeamWindow().setVisible(true);
					}
				});

			}
		}
		cur_player = 0;
		nextQuestion();
		while (qpack.hasNext()) {;
		}
	}

	public static void addTeam() {
		String teamName = "";
		while (teamName.equalsIgnoreCase(""));  // wait for user input


	}

	private static void showAllQuestions() {
		qpack.showAllEntries();
	}

	/**
	 * Adds points to question jackpot.
	 */
	public static void addPoints(int points) {
		cur_points += points;
		Text.debug(Integer.toString(cur_points));
		pw.setPoints(cur_points);
	}

	public static void awardPoints(int points) {
		teams[cur_team].addPoints(points);
		if (teams[cur_team].getPoints() >= POINTS_TO_WIN) {
			declareWinner();
		}
	}

	private static void declareWinner() {
		JPanel winnerPanel = new JPanel();
		winnerPanel.add(new JLabel(teams[cur_team].getName()));

		winnerPanel.setSize(pw.getSize());
		winnerPanel.setVisible(true);
		pw.add(winnerPanel);

	}

	/**
	 * Called after all answers have been revealed. Gets new question, at
	 * random, from QuestionPack. Sets up a new AdminWindow, if enabled.
	 */
	public static void nextQuestion() {
		cur_question_num++;
		if (qpack.size() == 0) {
			declareWinner();
		} else {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						loadQuestionFrame frame = new loadQuestionFrame(qpack);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			//            cur_question = qpack.getQuestion();
			//            Text.out(cur_question.getText() + "\n");
			//            makePlayWindow();
			//            makeAdminWindow();
		}
	}

	/** 
	 * Sets up a new AdminFrame and PlayFrame, based on user selection.
	 */
	public static void newQuestion(int selection) {
		cur_question = qpack.getQuestion(selection);
		makePlayWindow();
		makeAdminWindow();
	}

	/**
	 * Called when the current player receives a strike; every turn is
	 * ALT_EVERY_TURN == true
	 */
	public static void nextPlayer() {
		cur_player++;
		Text.debug("cur_player = " + cur_player);
		if (cur_player == teams[cur_team].size()) {
			cur_player = 0;
		}
		//		teams[cur_team].nextPlayer(cur_player);
		pw.switchPlayerLabel(teams[cur_team].getPlayerName(cur_player));
	}

	public static void addStrike() {
		if (cur_team != -1) {

			teams[cur_team].addStrike();
			if (teams[cur_team].checkStrikes()) { 	// if: team has too many strikes
				switchTeams();
			}

			nextPlayer();
			pw.setStrikes(teams[cur_team].getStrikeCount());
			pw.switchPlayerLabel(teams[cur_team].getPlayerName(cur_player));
		}
	}

	/**
	 * Called when current team has three strikes
	 */
	private static void switchTeams() {
		cur_team++;
		if (cur_team == MAX_TEAMS) {
			cur_team = 0;
		}

		pw.switchTeamLabel();
	}

	/* 
	 * Button Actions
	 */
	public static void setTeamButtonAction(int cur_team) {
		setCUR_TEAM(cur_team);
		pw.switchTeamLabel();
		pw.switchPlayerLabel(Main.getCUR_PLAYER_NAME());
	}

	public static void revealAnswer(String ansText, int ansPoints, int slot) {
		pw.revealAnswer(ansText, slot);
		addPoints(ansPoints);
	}

	//	public static void revealAnswer(Answer ans, int slot) {
	//		pw.revealAnswer(ans.getText(), slot);
	//	}
	public static void EXIT() {
		System.exit(0);
	}

	/* Setter and Getter methods */
	public static boolean isDEBUG() {
		return DEBUG;
	}

	public static int getDEBUG_WAIT() {
		return DEBUG_WAIT;
	}

	public static int getCHAR_WAIT() {
		return CHAR_WAIT;
	}

	public static int getTEXT_WAIT() {
		return TEXT_WAIT;
	}

	public static int getTEAM_COUNT() {
		return TEAM_COUNT;
	}

	public static int getMAX_TEAM_SIZE() {
		return MAX_TEAM_SIZE;
	}

	//	public static int getMAX_QUESTIONS() { return MAX_QUESTIONS; }
	public static int getMAX_STRIKES() {
		return MAX_STRIKES;
	}

	public static File getQUESTION_FILE() {
		return QUESTION_FILE;
	}

	public static Dimension getADMIN_DIM() {
		return ADMIN_DIM;
	}

	public static Dimension getPLAY_DIM() {
		return PLAY_DIM;
	}

	public static Dimension getMENU_DIM() {
		return MENU_DIM;
	}

	public static Team getCUR_TEAM() {
		return teams[cur_team];
	}

	public static String getCUR_PLAYER_NAME() {
		return teams[cur_team].getPlayerName(cur_player);
	}

	public static String getTEAM_NAME(int i) {
		return teams[i].getName();
	}

	public static void setCUR_TEAM(int i) {
		cur_team = i;
	}

	public static void setQUESTIONS(File qUESTIONS) {
		QUESTION_FILE = qUESTIONS;
	}

	public static void setALT_EVERY_TURN(boolean b) {
		ALT_EVERY_TURN = b;
	}
}
