package classes;

import gui.AdminWindow;
import gui.CreateQPFrame;
import gui.CreateTeamFrame;
import gui.LoadQPFrame;
import gui.MenuFrame;
import gui.NamePrompt;
import gui.NewQuestionFrame;
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
import javax.swing.JMenuBar;

import obj.Answer;
import obj.Player;
import obj.Question;
import obj.QuestionPack;
import obj.Team;

// TODO properly track team points, question count
// TODO make pretty, add sounds
// TODO announce winner!
// TODO beta test

public class Main {

	/* Global Variables */
	public static boolean 		DEBUG = false,			// true: debug methods and statements will be shown
			ALT_EVERY_TURN = false,	// true: team mate switched after every answer 
			FAST_MONEY = false;		
	public static int 			DEBUG_WAIT = 50,		// used for debug output; controls rate of String output (in milliseconds)
			CHAR_WAIT = 0,			// used for text output; controls rate of character output (in milliseconds)
			TEXT_WAIT = 0,			// used for text output; controls pause time when reading '~'
			MAX_ANSWERS = 10,
			MAX_TEAMS = 2,
			MAX_TEAM_SIZE = 10,
			MAX_STRIKES = 3,
			POINTS_TO_WIN = 300;
	public static long			DRAMATIC_PAUSE = 1000;	// in milliseconds	
	public static String 		ADMIN_TITLE = "Administrator",
			PLAY_TITLE = "Family Feud";	
	public static File 			QUESTION_FILE = new File("questions.txt");	
	public static Dimension 	MENU_DIM = new Dimension(800, 600),
			PLAY_DIM = new Dimension(1024, 768),
			ADMIN_DIM = new Dimension(480, 300);
	public static ImageIcon 	BACKGROUND_ICON_IMG = new ImageIcon("FamilyFeudBoard.jpg");

	/* Private Variable */
	private static QuestionPack qpack;						// collection of questions
	private static ArrayList<QuestionPack> qpacks;
	private static Team[] 		teams = new Team[MAX_TEAMS];
	private static Question 	cur_question;				// current question sent by QuestionPack
	private static boolean 		fm_player2 = false;					// true: player 2 is playing Fast Money
	private static int 			cur_question_num, 
	total_questions,			
	cur_team, 
	cur_player,					// slot indices for arrays
	cur_turn,					// turn count
	cur_points,
	team_count,					// number of teams in Team[]
	fm_cur_question;			// current Fast Money slot in int[] selections
	private static int[]		selections;		// array of questions used during Fast Money
	private static JFrame 		title, 
	menu;
	private static AdminWindow 	aw;
	private static PlayWindow 	pw;
	private static JMenuBar 	menubar;
	private static Scanner 		sc = new Scanner(System.in);

	//TODO end after all questions
	//TODO make pretty
	//TODO absolute layout for components
	//TODO startup interface
	public static void main(String[] args) throws FileNotFoundException {
		//		if (DEBUG) { 	// skip menu            
		//			playGame();
		//		} else {
		showMenu();
		//		}
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
		qpacks = new ArrayList<QuestionPack>();
		Scanner fileSC = new Scanner(QUESTION_FILE);
		String packname = null, 
				qText, 
				aText;
		ArrayList<Question> questions = new ArrayList<Question>();
		ArrayList<Answer> answers;
		int aPoints;
		boolean foundPack = false;

		//		if (DEBUG) {
		//			packname = "dogs";
		//		} else {
		//			Text.out("Question Pack name: ");
		//			packname = (new Scanner(System.in)).nextLine();
		//		}
		//		}


		String str = fileSC.nextLine();
		//		Text.debug("Loading Question Pack '" + packname + "'");
		while (fileSC.hasNextLine() && !str.equalsIgnoreCase("END")) {
			if (str.contains("PACK::")) {
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
						str = fileSC.nextLine();
						Text.debug("\tAnswer = " + aText + ", " + aPoints + "%");
					}

					//					if (str.equalsIgnoreCase("END")) {
					//						Text.debug(str);
					//					}
					questions.add(new Question(qText, answers));
					Text.debug("Question '" + qText + "' added\n}\n");
				}
				Text.debug(str);
				qpacks.add(new QuestionPack(packname, questions));
				Text.debug("Adding QPACK::" + packname);
				questions = new ArrayList<Question>();
				cur_question_num = 0;

				//			} else if (fileSC.hasNextLine()) {
				//				str = fileSC.nextLine();
			}
			//			} else {
			//				str = fileSC.nextLine();
			//			}
			Text.debug("str = " + str);
			if (foundPack) {
				//			qpack = new QuestiosnPack(packname, questions);
				//				qpacks.add(new QuestionPack(packname, questions));
				//				questions.clear();
				//				cur_question_num = 0;
				foundPack = false;
				//				Text.debug("Adding QPACK::" + packname);
			}
		}
		//		} else {
		//			Text.out(packname + " not found! Create a new pack named " + packname + "?\n[0] Yes\n[1] No");
		//			int choice = Input.getInt(2);
		//			if (choice == 0) {
		//				QuestionPacker.makeQuestionPack(packname);
		//			}
		//			loadQuestions();
		//		}
	}


	/* END Setup methods */
	public static void playGame() {

		try {
			loadQuestions();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

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
		//		total_questions = qpack.size();
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
		cur_points += points;
		Text.debug(Integer.toString(cur_points));
		pw.setPoints(cur_points);
	}

	/**
	 * Increase total points count for team that won question.
	 */
	public static void awardPoints() {
		if (cur_team != -1) {
			teams[cur_team].addPoints(cur_points);
			if (teams[cur_team].getPoints() >= POINTS_TO_WIN) {
				declareWinner();
			}
		}
	}

	/**
	 * Interface with PlayFrame. Updates points counter for current team.
	 */
	public static void updateTeamPoints() {
		if (cur_team != -1) {
			pw.setTeamPoints(teams[cur_team].getPoints(), cur_team);
		}
	}

	private static void declareWinner() {
		Text.debug(cur_team + " wins!!");
	}

	// TODO enable loading second question onwards

	/**
	 * Called after all answers have been revealed. Gets new question, at
	 * random, from QuestionPack. Sets up a new AdminWindow, if enabled.
	 */
	public static void nextQuestion() {
		Text.debug("Loading next question");
		cur_question_num++;
		if (!FAST_MONEY) {
			awardPoints();
			updateTeamPoints();
		}

		if (qpack.size() == 0) {
			declareWinner();
		} 

		else if (FAST_MONEY) {
			if (fm_cur_question == selections.length && !fm_player2) {
				fm_cur_question = 0;
				fm_player2 = true;
				Text.debug("setting fm_player2 = true");
				
				Main.newQuestion(selections[fm_cur_question]);
				Text.debug("Setting up Fast Money question # " + fm_cur_question);
			}
			
			else if (fm_cur_question == selections.length && fm_player2) {
				// do nothing!
				Text.debug("I ain't doin' shit!");
			}
			
			else {
				Main.newQuestion(selections[fm_cur_question]);
				Text.debug("Setting up Fast Money question # " + fm_cur_question);
			}
			
		}
		
		else if (qpack.size() == 5) {
			setFAST_MONEY();
			selections = new int[5];
			for (int i=0; i<selections.length; i++) {
				selections[i] = (i);
			}
			Main.newQuestion(selections[fm_cur_question]);
			Text.debug("EENT");
		}

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
			addPoints(ansPoints); 
		}
	}

	//	public static void revealAnswer(Answer ans, int slot) {
	//		pw.revealAnswer(ans.getText(), slot);
	//	}
	public static void endGame() {
		declareWinner();
		EXIT();
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
		if (teams[0].getPoints() > teams[1].getPoints()) {
			cur_team = 0;
		}
		
		else if (teams[0].getPoints() < teams[1].getPoints()) {
			cur_team = 1;
		}
		
		else {
			// IT'S A TIE!!
		}
		
		pw.setTeamLabel(cur_team);
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
	 * Called from LoadQuestionFrame.  Begins Fast Money mode.
	 */
	public static void setFAST_MONEY() {
		Text.debug("Entering Fast Money mode");
		FAST_MONEY = true;
		setFM_TEAM();
	}

	/**
	 * Called from LoadQPFrame. Sets question pack to use in this game.
	 */
	public static void setQPack(QuestionPack questionPack) {
		qpack = questionPack;	
	}

	public static void setFMSelections(int[] selections) {
		Main.selections = selections;
	}

	public static int getFM_cur_question() {
		return fm_cur_question;
	}

}
