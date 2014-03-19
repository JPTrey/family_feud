package obj;

import gui.AdminWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Answer as shown in AdminWindow
 * @author jonpaulsimonelli
 */
public class AnswerButton extends JButton {
	private String				text;
	private int					points;
	private Answer				ans;
	private AdminWindow 		aw;			// AdminWindow associated with this 
	
	public AnswerButton(final Answer ans, final AdminWindow aw) {
		this.text = ans.getText();
		this.points = ans.getPoints();
		this.ans = ans;
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				aw.revealAnswer(ans);
			}
		});
		setVisible(true);
	}
	
	public String getText() { return text + " " + points + "%"; }		// button text
	public Answer getAnswer() { return ans; }
	public String getAnswerText() { return text; }
	public int getPoints() { return points; }
}
