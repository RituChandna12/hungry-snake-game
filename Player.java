import java.util.LinkedList;
import java.util.ListIterator;

public class Player{
	private String username;
	private long score10;
	private long score15;
	private long score20;
	private String password;
	
	public long getScore10() {
		return score10;
	}
	
	public long getScore15() {
		return score15;
	}
	
	public long getScore20() {
		return score20;
	}
	
	public String getName() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setName(String name) {
		username = name;
	}
	
	public void setScore10(long score) {
		this.score10 = score;
	}
	
	public void setScore15(long score) {
		this.score15 = score;
	}
	
	public void setScore20(long score) {
		this.score20 = score;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public void updateScore(int boardsize, long score) {
		if (boardsize == 10) score10+=score;
		else if(boardsize ==15) score15+=score;
		else if(boardsize ==20) score20+=score;
	}
	
	
	
	
	
	public static Player userExistence(LinkedList<Player> playerList, String name) {
		if (playerList.size()==0) return null;
		ListIterator<Player> itr = playerList.listIterator();
		while(itr.hasNext()) {
			Player user = itr.next();
			if(user.username.equals(name))
				return user;
		}
		return null;
	}
	
	//public boolean addUser(LinkedList<Player> playerList, )
	
	
}
