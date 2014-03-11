package obj;

import java.util.ArrayList;

import classes.Text;

/**
 * Question object; contains text and all possible Answer objects
 * @author jonpaulsimonelli
 *
 */
public class Question {
	private String				text;
	private ArrayList<Answer>	answers;

	public Question(String text, ArrayList<Answer> answers) {
		this.text = text;
		this.answers = answers;
	}

	public String getText() { return text; }

	/** 
	 * Called when Player attempts an answer
	 * @param text input from Player
	 * @return Answer with point value, if found; null otherwise
	 */
//	public Answer findAnswer(String text) {
//		for (int i = 0; i < answers.length; i++) {
//			if (text.equalsIgnoreCase(answers[i].getText())) { 
//				aCount--;
//				return answers[i]; 
//			}
//		}
//		return null;
//	}
	
	public boolean markAnswer(Answer a) { 
		if (answers.contains(a)) {
		answers.remove(a);
		Text.debug("Removed answer " + a);
		return true;
		}
		else { return false; }
	}

	public int answerCount() { return answers.size(); }
	public ArrayList<Answer> getAnswers() { return answers; }
}