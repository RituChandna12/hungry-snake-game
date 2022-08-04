
public class GameBoard {
	
	private int boardSize;
	private char board[][];
	Snake snake;
	Food food;
	private int foodCount;
	
	GameBoard(int boardSize){
		this.boardSize = boardSize;
		snake = new Snake(boardSize);
		board = new char[boardSize][boardSize];
		food = new Food();
	}
	
	public int getFoodCount() {
		return foodCount;
	}
	
	public void generateFood() {
		this.food.generateNewFood(board);
	}
	
	public void defaultGameboard() {
		for(int i=0; i< boardSize; i++) {
			for(int j = 0;j <boardSize;j++) {
				board[i][j]= '.';
				
			}
		}
		for(int i= 1; i<snake.xcor.size();i++) {
			board[snake.xcor.get(i)][snake.ycor.get(i)]= '*';
		}
		board[snake.xcor.get(0)][snake.ycor.get(0)]= snake.getDirection();
	}
	
	public void updateGameboard() {
		for(int i=0; i< boardSize; i++) {
			for(int j = 0;j <boardSize;j++) {
				board[i][j]= '.';
				
			}
		}
		board[food.foodCor[0]][food.foodCor[1]] = '@';
		
		for(int i= 1; i<snake.xcor.size();i++) {
			board[snake.xcor.get(i)][snake.ycor.get(i)]= '*';
		}
		board[snake.xcor.get(0)][snake.ycor.get(0)]= snake.getDirection();
	}
	
	public void displayGameboard() {
		for(int i=0; i< boardSize; i++) {
			for(int j = 0;j <boardSize;j++) {
				System.out.print(board[i][j]);
				
			}
			System.out.println();
		}
	}
	
	public boolean handleInput(char ch) {
		if(ch=='>'||ch=='<'||ch=='^'||ch=='d') {
		if(!(ch=='>' && snake.getDirection()=='<') && !(ch=='<' && snake.getDirection()=='>')&& !(ch=='^' && snake.getDirection()=='d') && !(ch=='d' && snake.getDirection()=='^')) {
			snake.setDirection(ch);
		}
		}
		
		boolean foodEaten = snake.updateSnake(food.foodCor);
		
		if(snake.collisionWithSelf()) return false;
		else if (snake.collisionWithWall()) return false;
		
		updateGameboard();
		if(foodEaten) {
			foodCount++;
			generateFood();
			updateGameboard();
		}
		
		
		return true;
	}
}
