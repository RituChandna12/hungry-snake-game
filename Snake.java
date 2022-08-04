import java.util.*;
public class Snake {
	private char direction;
	public LinkedList<Integer> xcor;  //x cordinates of snake body segments
	public LinkedList<Integer> ycor;  //y cordinates of snake body segments
	private int boardsize;

	Snake(int boardsize){
		this.boardsize = boardsize;
		xcor = new LinkedList<>();
		ycor = new LinkedList<>();
		int k = (boardsize-4)/2;
		for(int i=3;i>=0;i--) {
			ycor.add(k+i);
			xcor.add(boardsize/2);
		}
		direction = '>';

	}
	public void setDirection(char ch) {
		direction = ch;
	}
	public char getDirection() {
		return direction;
	}

	public boolean updateSnake(int[] foodCor) {
		switch (direction) {
		case '>':
			ycor.add(0, ycor.get(0)+1);
			xcor.add(0, xcor.get(0));
			break;
		case '<':
			ycor.add(0, ycor.get(0)-1);
			xcor.add(0, xcor.get(0));
			break;
		case '^':
			ycor.add(0, ycor.get(0));
			xcor.add(0, xcor.get(0)-1);
			break;
		case 'd':
			ycor.add(0, ycor.get(0));
			xcor.add(0, xcor.get(0)+1);
			break;

		}
		boolean flag = foodEaten(foodCor);
		if (!flag) {

			xcor.removeLast();
			ycor.removeLast();
		}
		return flag;


	}
	public boolean collisionWithWall() {
		int xcorHead = xcor.getFirst();
		int ycorHead = ycor.getFirst();
		if(xcorHead>=boardsize || xcorHead <0|| ycorHead >= boardsize|| ycorHead<0) {
			System.out.println("OOPS! THE SNAKE COLLIDED WITH THE WALL!");
			return true;
		}
		return false;
	}

	public boolean foodEaten(int[] foodCor) {
		if(foodCor[0]== xcor.getFirst()&& foodCor[1]== ycor.getFirst()) {
			return true;
		}
		return false;
	}

	public boolean collisionWithSelf() {
		int xcorHead = xcor.getFirst();
		int ycorHead = ycor.getFirst();
		for(int i=1;i<xcor.size();i++) {
			if(xcor.get(i)==xcorHead && ycor.get(i)== ycorHead) {
				System.out.println("OOPS! THE SNAKE COLLIDED WITH ITSELF!");
				return true;
			}
		}
		return false;
	}


}
