package obj;

/**
 * Answer object referenced whenever a Player attempts to answer a Question
 * @author jonpaulsimonelli
 */
public class Answer {
	private String			text;
	private int				points;
	
	public Answer(String text, int points) {
		this.text = text;
		this.points = points;
	}
	
	public String getText() { return text; }
	public int getPoints() { return points; }
	public String getBoth() { return (text + "\t|\t" + Integer.toString(points)); }
}
