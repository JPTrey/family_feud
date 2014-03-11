package obj;

import java.util.ArrayList;

import classes.Text;

public class Team {
	private String					name;
	private int						size, points, strikes;
	private ArrayList <Player>		players;

	public Team(String name) {
		this.name = name;
		players = new ArrayList<Player>();
		strikes = 0;
	}

	public boolean checkStrikes() {
		if (strikes == 3) {
			strikes = 0;
			return true;
		} 
		else { return false; }
	}

	public void addPlayer(Player p) { 
		players.add(p); 
		Text.out("'" + p + "' added to team");
	}

	public void removePlayer(String name) { 
		for (int i=0; i<players.size(); i++) {
			if (name.equalsIgnoreCase(players.get(i).getName())) { 
				players.remove(i);
				break;
			}
		}
	}

	public int size() { return players.size(); }
	public void addPoints(int points) { this.points += points; }
	public void addStrike() { strikes++; }
	public Player nextPlayer(int cur_player) { return players.get(cur_player); }
	public String getName() { return name; }
	public String getPlayerName(int cur_player) { return players.get(cur_player).getName(); }
	public int getStrikeCount() { return strikes; }
}
