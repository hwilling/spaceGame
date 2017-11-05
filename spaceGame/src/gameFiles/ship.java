package gameFiles;

public class ship {
	
	private String name;
	private int hpMax, hpCurrent, speed, movesRemaining, currentActions, maxActions, dmg;
	private int xCoord, yCoord;
	
	public ship(String name){
		this.name = name;
	}
	
	public int getHP(){
		return hpCurrent;
	}
	public void modHP(int modAmount){
		hpCurrent += modAmount;
		//make sure hp does not go over max amount
		if(hpCurrent > hpMax){
			hpCurrent = hpMax;
		}
		//TODO function to destroy ship if hp is less than 0 (switch this to controller?)
	}
	public int getMovesRemaining(){
		return movesRemaining;
	}
	public void modMovesRemaining(int modAmount){
		movesRemaining += modAmount;
	}
	public int getCurrentActions(){
		return currentActions;
	}
	public void modCurrentActions(int modAmount){
		currentActions += modAmount;
	}
	public void turnReset(){
		movesRemaining = speed;
		currentActions = maxActions;
	}
	public int getDamageValue(){
		return dmg;
	}
	/************************Coordinate functions for ship*************************************/
	public int getXCoord(){
		return xCoord;
	}
	public void setXCoord(int newCoord){
		xCoord = newCoord;
	}
	public int getYCoord(){
		return yCoord;
	}
	public void setYCoord(int newCoord){
		yCoord = newCoord;
	}
	/*******************************************************************************************/
}
