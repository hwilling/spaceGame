package gameFiles;

public class Engine {

	private int moves;
	
	public Engine(int moves){
		this.moves = moves;
	}
	
	public int getMoves(){
		return moves;
	}
	
	public void setMoves(int newMoves){
		moves = newMoves;
	}
}
