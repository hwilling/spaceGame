package gameFiles;

public class combatController {
	private boolean players[];
	//ship[] player1Ships, player2Ships;
	combatController(int numPlayers, ship[] player1Ships, ship[] player2Ships){
		players = new boolean[numPlayers];
		//player1Ships = new ship[player1Ships.length];
		//player2Ships = new ship[player2Ships.length];
		for(int i = 0; i < players.length; i++){
			if(i == 0){
				players[i] = true;
			}
			else{
				players[i] = false;
			}
		}
	}
	
	public void playGame(){
		boolean gameOver = false;
		//game
		while(!gameOver){
			//TODO add event listeners for the game
		}
	}
	
	//TODO functions for in game events
	public void moveShip(ship shipToMove, int dx, int dy){
		int xdif, ydif;
		int shipx = shipToMove.getXCoord();
		int shipy = shipToMove.getYCoord();
		xdif = shipx - dx;
		if(xdif < 0){
			xdif = xdif * -1;
		}
		ydif = shipy - dy;
		if(ydif < 0){
			ydif = ydif * -1;
		}
		int movesUsed = 0;
		if(shipToMove.getMovesRemaining() >= movesUsed){
			shipToMove.setXCoord(dx);
			shipToMove.setYCoord(dy);
			shipToMove.modMovesRemaining(-1 * movesUsed);
		}
	}
}