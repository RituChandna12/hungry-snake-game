import java.util.Comparator;

public class Score10 implements Comparator<Player> {

	public int compare(Player p1, Player p2) {
		if(p1.getScore10()> p2.getScore10())
			return -1;
		else if (p1.getScore10()< p2.getScore10())
			return 1;
		else return 0;
	}

}
