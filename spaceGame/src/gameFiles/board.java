package gameFiles;

public class board {
	
	private int x, y;
	private ship[][] spaces;
	
	public board(int x, int y){
		this.x = x;
		this.y = y;
		spaces = new ship[x][y];
	}
	
	//sizes
	int getXSize(){
		return x;
	}
	int getYSize(){
		return y;
	}
	//board
	ship[][] getBoard(){
		return spaces;
	}
	void setBoard(ship[][] newBoard){
		spaces = newBoard;
	}
	//spaces
	ship getBoardSpace(int x, int y){
		return spaces[x][y];
	}
	void setBoardSpace(int x, int y, ship newShip){
		spaces[x][y] = newShip;
	}
}
