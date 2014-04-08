package obj;

import java.util.ArrayList;
import java.util.Random;

import classes.Main;
import classes.Text;

public class QuestionPack {
	private String				name;
	private ArrayList<Question> questions = new ArrayList<Question>();

	public QuestionPack(String name, ArrayList<Question> questions) {
		this.name = name;
		this.questions = questions;
		Text.out("'" + name + "' loaded\n");
	}

	public String name() {
		return name;
	}
	
	public Question getQuestion(int selection) {
		Question		q = questions.get(selection);
		questions.remove(selection);
		
		return q;
	}
	
	public Object[] getQuestions() {
		return questions.toArray();
	}

	/**
	 * Called to determine end-of-game
	 * @return true if unused questions exist
	 */
	public boolean hasNext() {
		if (questions.size() > 0) { return true; }
		else { return false; }
	}
	
	public int size() {
		return questions.size();
	}
	
	/**
	 * Debug tool
	 */
	public void showAllEntries() {
		if (Main.DEBUG) { 
			for (int i=0; i<questions.size(); i++) { Text.out(questions.get(i).getText()); }
		} 
	}
}
