package classes;

import gui.AdminWindow;
import gui.CreateQPFrame;
import gui.CreateTeamFrame;
import gui.InputLongPrompt;
import gui.LoadQPFrame;
import gui.MenuFrame;
import gui.NamePrompt;
import gui.NewQuestionFrame;
import gui.PlayWindow;
import gui.WinnerFrame;
import gui.loadQuestionFrame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import obj.Answer;
import obj.Player;
import obj.Question;
import obj.QuestionPack;
import obj.Team;
import sound.Sound;

// JAR and relative URLs
// test and debug
// test on Windows
// remove useless code/classes

public class Main {

	/* Global Variables */
	public static boolean 		DEBUG = true,			// true: debug methods and statements will be shown
			ALT_EVERY_TURN = false,	// true: team mate switched after every answer 
			FAST_MONEY = false;		
	public static int 			DEBUG_WAIT = 10,		// used for debug output; controls rate of String output (in milliseconds)
			CHAR_WAIT = 0,			// used for text output; controls rate of character output (in milliseconds)
			TEXT_WAIT = 0,			// used for text output; controls pause time when reading '~'
			MAX_ANSWERS = 10,
			MAX_TEAMS = 2,
			MAX_TEAM_SIZE = 10,
			MAX_STRIKES = 3,
			POINTS_TO_WIN = 300,	// points required to enter Fast Money
			POINTS_TO_WIN_FM = 200;
	public static long			DRAMATIC_PAUSE = 1000;	// in milliseconds	
	public static String 		ADMIN_TITLE = "Administrator",
			PLAY_TITLE = "Family Feud";	
	public static File 			QUESTION_FILE = new File("./qpacks/questions.txt");	
	public static Dimension 	MENU_DIM = new Dimension(800, 600),
			PLAY_DIM = new Dimension(1024, 768),
			ADMIN_DIM = new Dimension(480, 300);
	public static ImageIcon 	BACKGROUND_ICON_IMG = new ImageIcon("FamilyFeudBoard.jpg");

	/* Private Variable */
	private static QuestionPack 			qpack;						// collection of questions
	private static ArrayList<QuestionPack> 	qpacks;
	private static Team[] 					teams = new Team[MAX_TEAMS];
	private static Question 				cur_question;				// current question sent by QuestionPack; if == 3, double points
	private static boolean 					fm_player2 = false;			// true: player 2 is playing Fast Money
	private static int 						cur_question_num, 
	total_questions,			
	cur_team, 
	cur_player,					// slot indices for arrays
	cur_turn,					// turn count
	cur_points,
	team_count,					// number of teams in Team[]
	fm_cur_question;			// current Fast Money slot in int[] selections
	private static int[]					selections;					// array of questions used during Fast Money
	private static JFrame 					title, 
	menu;
	private static AdminWindow 				aw;
	private static PlayWindow 				pw;
	private static JMenuBar 				menubar;
	private static Scanner 					sc = new Scanner(System.in);
	private static Sound					theme = new Sound("./wav/FamilyFeud-Theme.wav"),
			blip = new Sound("./wav/FamilyFeud-Blip.wav"),
			bell = new Sound("./wav/FamilyFeud-Bell.wav"),
			strike = new Sound("./wav/ff-strike3.wav"),
			dup = new Sound("./wav/FamilyFeud-Buzzer1.wav");

	public static void main(String[] args) throws FileNotFoundException {
		showMenu();
	}

	/* Setup methods */
	public static void showMenu() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					MenuFrame frame = new MenuFrame();
//					frame.setVisible(true);
					if (qpacks == null) {
						qpacks = new ArrayList<QuestionPack>();
					}
					showLoadQuestionPackWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void showLoadQuestionPackWindow() {
		try {
			loadQuestions();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Text.debug("Showing LoadQuestionPackFrame");
		LoadQPFrame frame = new LoadQPFrame(qpacks);
		frame.setVisible(true);
	}

	public static void showLoadQuestionWindow() {
		Text.debug("Showing LoadQuestionFrame");

	}

	public static void showPacknamePrompt() {
		Text.debug("Showing NamePrompt");
		NamePrompt prompt = new NamePrompt();
		prompt.setVisible(true);
	}

	public static void showCreateQuestionPackWindow(String packname, ArrayList<Question> questions) {
		Text.debug("Showing CreateQuestionPackFrame");
		if (qpack == null) {
			qpack = new QuestionPack(packname, questions);
		}
		CreateQPFrame frame = new CreateQPFrame(qpack);
		frame.setVisible(true);
	}

	public static void showCreateQuestionWindow() {
		Text.debug("Showing NewQuestionFrame");
		NewQuestionFrame frame = new NewQuestionFrame();
		frame.setVisible(true);
	}

	public static void showInputLongPrompt() {
		try {
			InputLongPrompt dialog = new InputLongPrompt();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new PlayWindow for each new question
	 */
	private static void makePlayWindow() {
		theme = bell;
		bell.play();
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
			choice = Input.getInt(2);
		}
	}

	/**
	 * Builds the selected QuestionPack from 'questions.txt', if found.
	 *
	 * @param name selection by user
	 * @throws FileNotFoundException
	 * @throws InterruptedException TODO run in a separate thread
	 */
	private static void loadQuestions() throws FileNotFoundException {
		qpacks = new ArrayList<QuestionPack>();
		Scanner fileSC = new Scanner(QUESTION_FILE);
		String packname = null, 
				qText, 
				aText,
				str = null;
		ArrayList<Question> questions = new ArrayList<Question>();
		ArrayList<Answer> answers;
		int aPoints;
		boolean foundPack = false;
		if (fileSC.hasNextLine()) {
			str = fileSC.nextLine();
		}
		while (fileSC.hasNextLine()) {
			if (str.isEmpty()) {
				str = fileSC.nextLine();
			}
			else if (str.contains("PACK::")) {
				String[] packInfo = str.split("PACK::");
				Text.debug("Pack = " + packInfo[1]);
				packname = packInfo[1];
				//				if (packInfo[1].equalsIgnoreCase("'" + packname + "'")) { 			// if: pack is found
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
						if (fileSC.hasNextLine())
							str = fileSC.nextLine();
						Text.debug("\tAnswer = " + aText + ", " + aPoints + "%");
					}
					questions.add(new Question(qText, answers));
					Text.debug("Question '" + qText + "' added\n}\n");
				}
				Text.debug(str);
				qpacks.add(new QuestionPack(packname, questions));
				Text.debug("Adding QPACK::" + packname);
				questions = new ArrayList<Question>();
				cur_question_num = 0;
			}
			Text.debug("str = " + str);
			if (foundPack) {
				foundPack = false;
			}
		}
	}

	/**
	 * Writes QPack to file.
	 * @param qpack
	 */
	public static void saveQPack() {
		Text.debug("qpacks size = " + qpacks.size());
		Text.debug("qpack size = " + qpack.size());

		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(QUESTION_FILE, true)));

			// write name
			out.print("\nPACK::'" + qpack.name() + "'");
			Text.debug("Writing packname");

			// write all Question
			for (int i=0; i<qpack.size(); i++) {
				out.print("\nQ::\"" + qpack.exportQuestion(i).getText() + "\"");
				Text.debug("Writing question: " + qpack.exportQuestion(i).getText());
				Text.debug("i = " + i);

				// Write Answers
				for (int j=0; j<qpack.exportQuestion(i).answerCount(); j++) {
					out.print("\n\tA::\"" + qpack.exportQuestion(i).getAnswers().get(j).getText() + "\"=" + 
							qpack.exportQuestion(i).getAnswers().get(j).getPoints());
					Text.debug("Writing answer: " + qpack.exportQuestion(i).getAnswers().get(j).getText());
				}
			}
			out.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}


	/* END Setup methods */
	public static void playGame() {

		for (int i=0; i<qpacks.size(); i++)
			Text.debug(qpacks.get(i).name());

		showLoadQuestionPackWindow();

		//		if (DEBUG) { 		// cmd team input
		//			Scanner sc = new Scanner(System.in);
		//			showAllQuestions();
		//			for (int i = 0; i < MAX_TEAMS; i++) {			// placeholder teams
		//				Text.out("\nTeam " + i + " name: ");
		//				teams[i] = new Team(sc.nextLine());
		//				Text.out("\nPlayer " + i + " name: ");
		//				Text.debug("Team " + (i + 1) + ":Player " + (i + 1));
		//			}
		//		} else {
		//		for (int i = 0; i < MAX_TEAMS; i++) {
		//			cur_team = i;
		//		}
		//		}
		//		Text.debug("cur_team=" + cur_team);
		//		cur_team = 0;
		//		team_count = 0;
		//		showCreateTeamFrame();
		//		cur_player = -1;
		//		nextQuestion();
		//		while (qpack.hasNext()) {;
		//		}
	}

	public static void showCreateTeamFrame() {		
		//		if (cur_team < MAX_TEAMS) {
		CreateTeamFrame frame = new CreateTeamFrame();
		frame.setVisible(true);
		Text.debug("cur_team=" + cur_team);
		//		}
	}

	/**
	 * Called from CreateTeamFrame.
	 * @param teamName
	 * @param playerNames
	 */
	public static void addTeam(String teamName, ArrayList<String> playerNames) {
		Team t = new Team(teamName);
		//		for (int i=0; i<playerNames.size(); i++) {
		//			t.addPlayer(new Player(playerNames.get(i)));
		//		}
		//
		teams[team_count] = t;
		team_count++;
	}

	/**
	 * Called from CreateQuestionFrame.  Adds new question to new QuestionPack.
	 * @param q
	 */
	public static void addQuestion(Question q) {
		qpack.addQuestion(q);
	}

	private static void showAllQuestions() {
		qpack.showAllEntries();
	}

	/**
	 * Adds points to question jackpot.
	 */
	public static void addPoints(int points) {
		if (Main.cur_question_num == 3) {
			points *= 2;
		}
		cur_points += points;
		Text.debug(Integer.toString(cur_points));
		Text.out(Integer.toString(cur_points));
		pw.setPoints(cur_points);

		// if: in Fast Money mode, hard-wire points without requiring nextQuestion request
		if (FAST_MONEY) {
			teams[cur_team].addPoints(points);
			pw.setPoints(teams[cur_team].getPoints());
		}
	}

	/**
	 * Increase total points count for team that won question.
	 */
	public static void awardPoints() {
		if (cur_team != -1) {
			teams[cur_team].addPoints(cur_points);
			Text.debug("Team '" + teams[cur_team].getName() + "' points: " + teams[cur_team].getPoints());
		}
	}

	/**
	 * Interface with PlayFrame. Updates points counter for current team.
	 */
	public static void updateTeamPoints() {
		if (cur_team != -1) {
			Text.out("Points = " + teams[cur_team].getPoints());
			pw.setTeamPoints(teams[cur_team].getPoints(), cur_team);
		}
	}

	private static void declareWinner() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (cur_team != -1) {
						WinnerFrame frame = new WinnerFrame();
						frame.setVisible(true);
						if (teams[cur_team].getPoints() >= POINTS_TO_WIN_FM) {
							frame.setWinnerText(teams[cur_team].getName() + " Wins!");
						}

						else {
							frame.setWinnerText("Too Bad!");
						}
					}

					else {
						EXIT();
					}
					//					frame.setWinnerPoints("Total Points: " + teams[cur_team].getPoints());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Called after 'Next Question' button is pressed;
	 * Gets new question from NewQuestionFrame;
	 * Awards jackpot to selected team (not in Fast Money mode);
	 * Sets up a new AdminWindow.
	 */
	public static void nextQuestion() {
		Text.debug("Loading next question...");
		cur_question_num++;

		if (!FAST_MONEY) {
			awardPoints();
			updateTeamPoints();

			// if: only five questions remain, start Fast Money mode
			if (qpack.size() == 5) {
				setFAST_MONEY();
				
				selections = new int[5];
				for (int i=0; i<selections.length; i++) {
					selections[i] = (i);
				}
				
//				Main.newQuestion(selections[fm_cur_question]); 
//				Text.debug("Requesting Fast Money mode...");
			}
		}

		if (qpack.size() == 0) {
			declareWinner();
			Text.debug("QPack out of questions.  Declaring winner.");
		} 

		if (FAST_MONEY) {
			Text.debug("fm_cur_question: " + fm_cur_question);

			// if: Player 1 is done, reset for Player 2
			if (fm_cur_question == selections.length && !fm_player2) {	
				fm_cur_question = 0;
				fm_player2 = true;
				Main.newQuestion(selections[fm_cur_question]);
				Text.debug("setting fm_player2 = true");
				Text.debug("Setting up Fast Money question # " + fm_cur_question);
			}

			// if: both players are finished
			else if (fm_cur_question == selections.length && fm_player2) {
				Text.debug("Awarding Fast Money points");
				awardPoints();
				updateTeamPoints();
				pw.setPoints(cur_points);
				declareWinner();
				Text.debug("Awarding " + teams[cur_team].getPoints() + " points for Fast Money...");
			}

			// else: next question
			else {
				Main.newQuestion(selections[fm_cur_question]);
				Text.debug("Setting up Fast Money question # " + fm_cur_question);
			}
		}

		/*		// if: either team has earned 300 points or more
		else if (teams[0].getPoints() >= 300 || teams[1].getPoints() >= 300) {
			setFAST_MONEY();
		}

		// if: only five questions remain
		else if (qpack.size() == 5) {
			setFAST_MONEY();
			selections = new int[5];
			for (int i=0; i<selections.length; i++) {
				selections[i] = (i);
			}
			Main.newQuestion(selections[fm_cur_question]);
			Text.debug("EENT");
		}
		 */

		// else: continue with regular questions
		else {
			cur_team = -1;
			cur_points = 0;		// reset points between questions

			// show question selection window
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						if (FAST_MONEY) {

						}
						else {
							loadQuestionFrame frame = new loadQuestionFrame(qpack);
							frame.setVisible(true);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/** 
	 * Sets up a new AdminFrame and PlayFrame, based on user selection.
	 */
	public static void newQuestion(int selection) {
		if (FAST_MONEY) { 		// if: during Fast Money mode
			selection = selections[fm_cur_question];
			fm_cur_question++;
			Text.debug("Setting " + selection);
		}
		cur_question = qpack.getQuestion(selection);
		makePlayWindow();
		makeAdminWindow();
	}

	/**
	 * Called when the current player receives a strike; 
	 * every turn when ALT_EVERY_TURN == true
	 */
	public static void nextPlayer() {
		//		cur_player++;
		//		Text.debug("cur_player = " + cur_player);
		//		if (cur_player == teams[cur_team].size()) {
		//			cur_player = 0;
		//		}
		//		//		teams[cur_team].nextPlayer(cur_player);
		//		aw.switchPlayerLabel(teams[cur_team].getPlayerName(cur_player));
	}

	public static void addStrike() {
		Sound s = new Sound("FamilyFeud-Blip.wav");
		s.play();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		s = new Sound("ff-strike3.wav");
		s.play();
		Text.debug("Strike added to Team" + cur_team);
		if (cur_team != -1) {

			teams[cur_team].addStrike();
			if (teams[cur_team].checkStrikes()) { 	// if: team has too many strikes
				switchTeams();
				pw.switchTeamLabel(cur_team);
				aw.switchTeamLabel();
			}

			nextPlayer();
			pw.setStrikes(teams[cur_team].getStrikeCount());
			//			pw.switchPlayerLabel(teams[cur_team].getPlayerName(cur_player));
		}
		else {
			Text.debug("No Team Assigned Strike!");
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

		pw.switchTeamLabel(cur_team);
	}

	/* 
	 * Button Actions
	 */
	public static void setTeamButtonAction(int cur_team) {
		setCUR_TEAM(cur_team);
		pw.switchTeamLabel(cur_team);
		//		pw.switchPlayerLabel(Main.getCUR_PLAYER_NAME());
	}

	/**
	 * Called when an ansLabel is to be revealed.  
	 * @param ansText
	 * @param ansPoints
	 * @param slot ansLabel 1-10.
	 * @param solved set to false if revealAll is clicked.  Points are not awarded.
	 */
	public static void revealAnswer(String ansText, int ansPoints, int slot, boolean solved) {
		pw.revealAnswer(ansText, slot);
		if (solved) {
			if (!FAST_MONEY) { 
				playSound("bell");
			}
			addPoints(ansPoints); 
		}
	}

	public static void playSound(String sound) {

		if (sound.equalsIgnoreCase("reveal_bell")) {
			//			bell.play();
		}

		else if (sound.equalsIgnoreCase("duplicate")) {
			dup.play();
		}

		else {
			blip.play();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (sound.equalsIgnoreCase("strike")) {
				strike.play();
			}

			else if (sound.equalsIgnoreCase("bell")) {
				bell.play();
			}

		}
	}

	public static void endGame() {
		declareWinner();
		Text.debug("Team '" + teams[cur_team].getName() + "' points: " + teams[cur_team].getPoints());
	}

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
		return team_count;
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
		if (cur_team != -1) {
			return teams[cur_team]; 
		}	
		else {
			return null;
		}
	}

	public static String getCUR_PLAYER_NAME() {
		return teams[cur_team].getPlayerName(cur_player);
	}

	public static String getCUR_TEAM_NAME() {
		return teams[cur_team].getName();
	}

	public static String getTEAM_NAME(int i) {
		if (i < MAX_TEAMS) {
			return teams[i].getName();
		}
		else return null;
	}

	public static void setCUR_TEAM(int i) {
		cur_team = i;
		if (i != -1) {
			pw.setTeamLabel(i);
		}
	}

	/**
	 * Sets the Fast Money team to whichever team has more points.
	 */
	public static void setFM_TEAM() {

		// if: Team 1 has more points
		if (teams[0].getPoints() > teams[1].getPoints()) {
			cur_team = 0;
			pw.removeTeamLabel(1);
		}

		else if (teams[0].getPoints() < teams[1].getPoints()) {
			cur_team = 1;
			pw.removeTeamLabel(0);
		}

		// if: both teams have equal points, and not zero points
		else if (teams[0].getPoints() == teams[1].getPoints()  && teams[0].getPoints() > 0) {
			cur_team = new Random().nextInt(1);
		}

		// else: if not points were awarded, default to first team
		else {
			cur_team = 0;
		}

		Text.debug("Cur_Team = " + cur_team);
	}

	public static int getCUR_QUESTION_NUM() {
		return cur_question_num;
	}

	public static int getTOTAL_QUESTIONS() {
		return total_questions;
	}

	public static void setQUESTIONS(File qUESTIONS) {
		QUESTION_FILE = qUESTIONS;
	}

	public static void setALT_EVERY_TURN(boolean b) {
		ALT_EVERY_TURN = b;
	}

	/**
	 * Called from LoadQuestionFrame, or when QPack has five questions remaining.  Begins Fast Money mode.
	 */
	public static void setFAST_MONEY() {
		Text.debug("Entering Fast Money mode");
		FAST_MONEY = true;
		cur_question_num = 0;
		fm_cur_question = 0;
		total_questions = 10;
		setFM_TEAM();
		teams[cur_team].resetPoints();
	}

	/**
	 * Called from LoadQPFrame. Sets question pack to use in this game.
	 */
	public static void setQPack(QuestionPack questionPack) {
		qpack = questionPack;	
		if (questionPack != null) {
			total_questions = qpack.size();
		}
	}

	public static void setFMSelections(int[] selections) {
		Main.selections = selections;
	}

	public static int getFM_cur_question() {
		return fm_cur_question;
	}

}
