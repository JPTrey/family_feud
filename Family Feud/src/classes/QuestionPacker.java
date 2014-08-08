package classes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import obj.Answer;
import obj.Question;
import obj.QuestionPack;

public class QuestionPacker {
	static ArrayList<Question> questions = new ArrayList<Question>();
	static Scanner sc = new Scanner(System.in);

	public static QuestionPack makeQuestionPack(String...packname) {
		ArrayList<Answer> answers;
		String input = null;
		String qText, filename;
		int points;

		if (packname == null) {
			Text.out("Pack name: ");
			filename = Input.getString();
		}
		else { filename = packname[0]; }
		

		// add questions
		for (int i=0; ; i++){	
			Text.out("Question #" + i + "(type 'done' if finished) :");
			input = Input.getString();
			if (input.equalsIgnoreCase("done")) { break; }
			else { qText = input; }

			// add answers
			answers = new ArrayList<Answer>();
			for (int j=0; ; j++) { 
				Text.out("Answer #" + j + " (type 'done' if finished) :");
				input = Input.getString();
				if (input.equalsIgnoreCase("done")) { break; }
				else { 
					Text.out("Points: ");
					points = Input.getInt(101);
					answers.add(new Answer(Input.getString(), points)); 
				}
			}
			questions.add(new Question(qText, answers));
		}

		// output to file
		try {
			PrintWriter out = new PrintWriter(new FileWriter(filename + ".txt")); 
			out.println("PACK::'"+filename+"'");
			for (int i=0; i<questions.size(); i++) {
				out.println("Q::\"" + questions.get(i).getText() + "\""); 
				for (int j=0; j<questions.get(i).answerCount(); j++) {
					out.println("\tA::\"" + questions.get(i).getAnswers().get(j) + "\"");
				}
				out.println("world"); 
			}
			out.close();	
		}
		catch(IOException e1) {
			Text.debug("Error during reading/writing");
		}
		return new QuestionPack(filename, questions);
	}
}
