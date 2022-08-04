import java.util.*;


public class TimedScanner {


	public static boolean validGameLevel(char c) {
		if (c=='e'|| c=='m'||c=='h')
			return true;
		return false;
	}

	public static int getFoodPoint(char c) {
		if (c=='e') return 5;  //user gets 5 points for each food eaten in easy level
		else if(c=='m') return 10;  //user gets 10 points for each food eaten in easy level
		return 15;   //user gets 15 points for each food eaten in easy level
	}

	public static int getTime(char c) {
		if (c=='e') return 10; //user gets 10 seconds to input move in easy level
		else if(c=='m') return 7;  //user gets 7 seconds to input move in medium level
		else return 4;  //user gets 4 seconds to input move in hard level
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Scanner sc = new Scanner(System.in);
		Timed scanner = new Timed();
		boolean programRunning = true;
		char input;
		String temp;
		String username;
		String password;
		int boardSize=10;
		int time = 10;
		Score10 score10 = new Score10();
		Score15 score15 = new Score15();
		Score20 score20 = new Score20();

		int foodPoint = 5;
		char level='e';
		Player currentPlayer = null;
		LinkedList<Player> userDetails = new LinkedList<>();
		boolean gotostart = false;

		bb:
			while(programRunning) {

				gotostart = false;
				System.out.println("ENTER 'l' to LOGIN\nENTER 's' to SIGN UP\nENTER 'e' to END GAMING\nENTER 'w' to SHOW-LEADERBOARD");
				temp = scanner.nextLine(-1);
				input = temp.trim().toLowerCase().charAt(0);
				switch(input) {
				case 'l': {
					System.out.println("Enter UserName: ");
					username = scanner.nextLine(-1);
					currentPlayer = Player.userExistence(userDetails, username);
					if(!(currentPlayer == null)) {
						System.out.println("Enter Password: ");
						if(scanner.nextLine(-1).equals(currentPlayer.getPassword()))
						{}
						else {gotostart = true;
						System.out.println("Invalid password!");}
					}
					else {
						System.out.println("User doesn't exist! Try Signing up!");
						gotostart=true;
					}
					break;}
				case 's': {
					System.out.println("Enter UserName: ");
					username = scanner.nextLine(-1);
					currentPlayer = Player.userExistence(userDetails, username);
					if(currentPlayer == null) {
						System.out.println("Enter new Password: ");
						password = scanner.nextLine(-1);
						currentPlayer= new Player();
						currentPlayer.setName(username);
						currentPlayer.setPassword(password);
						userDetails.add(currentPlayer);
					}
					else {
						System.out.println("SignUp unsuccessful! User Already Exists!");
						gotostart=true;
					}
					break;}
				case 'e': {
					programRunning = false;
					break;
				}

				case 'w': {
					gotostart = true;
					while(true) {
						System.out.println("Enter the type of leaderboard you want to see:\n'a' for 10x10\n'b' for 15x15\n'c' for 20x20");
						input = scanner.nextLine(-1).trim().toLowerCase().charAt(0);
						if(input =='a'||input=='b'||input=='c') {
							break;
						}
						else System.out.println("Invalid Choice! Try Again!");
					}
					

					System.out.println("Name\t\tScore");
					System.out.println("----\t\t-----");
					switch(input) {
					case 'a':{
						Collections.sort(userDetails, score10);
						for(int i = 0; i<userDetails.size(); i++) {
							System.out.println(userDetails.get(i).getName()+"\t\t"+userDetails.get(i).getScore10());
						}
						break;
					}
					case 'b':{
						Collections.sort(userDetails, score15);
						for(int i = 0; i<userDetails.size(); i++) {
							System.out.println(userDetails.get(i).getName()+"\t\t"+userDetails.get(i).getScore15());
						}
						break;
					}

					case 'c':{
						Collections.sort(userDetails, score20);
						for(int i = 0; i<userDetails.size(); i++) {
							System.out.println(userDetails.get(i).getName()+"\t\t"+userDetails.get(i).getScore20());
						}
						break;
					}



					}
					break;	
				}

				default: {
					System.out.println("Invalid input! Try Again!");
					gotostart = true;
				}


				}

				if(gotostart) continue;

				if(programRunning) {


					while(true) {
						System.out.println("ENTER 'l' to LOGOUT\nENTER 'p' to PLAY A GAME");
						temp = scanner.nextLine(-1);
						input = temp.trim().toLowerCase().charAt(0);
						switch(input){
						case 'l': {
							System.out.println("LogOut successful!");continue bb;
						}
						case 'p':{

							boolean validBoardSize = false;
							while(!validBoardSize) {
								System.out.println("Avalilable board sizes: 10, 15, 20\nEnter board size:");
								temp = scanner.nextLine(-1);
								String inputBoardSize = temp.trim();
								if(inputBoardSize.equals("10") || inputBoardSize.equals("15") || inputBoardSize.equals("20")) {
									boardSize = Integer.parseInt(inputBoardSize);
									validBoardSize = true;
									boolean validLevel = false;
									while(!validLevel) {
										System.out.println("Avalilable levels: EASY, MEDIUM, HARD\nENTER 'e' for EASY\nENTER 'm' for MEDIUM\nENTER 'h' for HARD\\n");
										temp = scanner.nextLine(-1);
										input = temp.trim().toLowerCase().charAt(0);
										if(TimedScanner.validGameLevel(input)) {
											validLevel = true;
											level = input;
										}
										else System.out.println("Invalid Level! Try Again!");

									}
								}
								else System.out.println("Invalid BoardSize! Try Again!");
							}
							GameBoard b = new GameBoard(boardSize);
							time = TimedScanner.getTime(level);
							foodPoint = TimedScanner.getFoodPoint(level);
							b.defaultGameboard();
							b.generateFood();
							boolean continueGame = true;

							while(continueGame)
							{
								b.updateGameboard();
								b.displayGameboard();


								System.out.println("Enter your move! (Mind the timer.)");
								String name = scanner.nextLine(time*1000);
								if (name == null)
								{
									System.out.println("you were too slow!");
									continueGame = b.handleInput('n');
								}
								else
								{
									if(name.length()>0) {
										continueGame = b.handleInput(name.charAt(0));
									}
									else continueGame = b.handleInput('n');
								}}

							System.out.println("GAMEOVER!");
							System.out.println(b.getFoodCount());
							if(currentPlayer != null) currentPlayer.updateScore(boardSize, foodPoint*b.getFoodCount());
							if(currentPlayer!=null) {
								System.out.println(currentPlayer.getScore10());
								System.out.println(currentPlayer.getScore15());
								System.out.println(currentPlayer.getScore20());
							}
							break;

						}
						default : System.out.println("Invalid choice! Try Again");
						}
					}


				}



			}}




}



