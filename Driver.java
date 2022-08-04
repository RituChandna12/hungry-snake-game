import java.util.Scanner;

public class Driver {

	static char ch;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		GameBoard b = new GameBoard(15);
		b.defaultGameboard();
		b.generateFood();
		boolean continueGame = true;
		bb:
		while(continueGame)
		{
		b.updateGameboard();
		b.displayGameboard();
		
		long begin = System.currentTimeMillis();
		String input = sc.nextLine();
		long currentTime = System.currentTimeMillis();
		long duration = (currentTime - begin)/1000;
		System.out.println("time passed "+duration);
		long numberOfDefaultCycles = duration/5;
		System.out.println("Shoot! the snake moved "+ numberOfDefaultCycles+" before moving in your inputted direction!");
		for(int i=0;i<numberOfDefaultCycles;i++) {
			continueGame = b.handleInput('n');
			b.displayGameboard();
			if(!continueGame) {
				System.out.println("game over");
				break bb;
			}
		}
		if(input.length()>0) {
			continueGame = b.handleInput(input.charAt(0));
		}
		else 
			continueGame = b.handleInput('n');
		
		
	}
		
		
		System.out.println("gameover");
		
		sc.close();
	}
	


}
