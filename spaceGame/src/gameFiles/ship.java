package gameFiles;

public class ship {
	
	private String name;
	private int hpMax, hpCurrent, speed, movesRemaining, currentActions, maxActions, dmg, range;
	private int xCoord, yCoord;
	
	public ship(String name){
		this.name = name;
	}
	public String getName(){
		return name;
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
	//movement
	public int getMovesRemaining(){
		return movesRemaining;
	}
	public void setMovesRemaining(int newMoves){
		movesRemaining = newMoves;
	}
	public void modMovesRemaining(int modAmount){
		movesRemaining += modAmount;
	}
	//actions
	public int getCurrentActions(){
		return currentActions;
	}
	public void modCurrentActions(int modAmount){
		currentActions += modAmount;
	}
	//reset values at start of turn
	public void turnReset(){
		movesRemaining = speed;
		currentActions = maxActions;
	}
	//range and damage values
	public int getDamageValue(){
		return dmg;
	}
	public void setDamageValue(int newdmg){
		dmg = newdmg;
	}
	public void modDamageValue(int modVal){
		dmg += modVal;
	}
	public int getRange(){
		return range;
	}
	public void setRange(int newRange){
		range = newRange;
	}
	public void modRange(int modVal){
		range += modVal;
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
