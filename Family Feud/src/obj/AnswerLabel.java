package obj;

import javax.swing.JLabel;

/** Answers as shown in PlayWindow
 * Obscured by an image until revealed
 * @author jonpaulsimonelli
 *
 */
public class AnswerLabel {
	private JLabel		ansLabel;			// JLabel to be revealed once clicked
	
	public AnswerLabel(JLabel ansLabel) { this.ansLabel = ansLabel; }
	
	public JLabel reveal() { return ansLabel; }
	public JLabel censor() { return new JLabel("blue.jpg"); }
}
