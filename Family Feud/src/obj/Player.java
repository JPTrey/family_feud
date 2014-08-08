package obj;

public class Player {
	private String		name;
	private int			points;
	
	public Player(String name) {
		this.name = name;
		points = 0;
	}

	public String getName() { return name; }
	public int getPoints() { return points; }
	public void addPoints(int p) { points += p; }
	
}
