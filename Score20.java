import java.util.Comparator;

public class Score20 implements Comparator<Player> {

	public int compare(Player p1, Player p2) {
		if(p1.getScore20()> p2.getScore20())
			return -1;
		else if (p1.getScore20()< p2.getScore20())
			return 1;
		else return 0;
	}

}