package gameFiles;

public class ship {
	
	private String name = "";
	private int hpMax, hpCurrent, speed, movesRemaining, currentActions, maxActions, dmg, range, armor = 0;
	private int xCoord, yCoord = 0;
	private Engine engine;
	private ShieldGenerator shieldGen;
	private WeaponSystems weapons;
	private Reactor reactor;
	private Hull hull;
	
	//maintain old input to not mess up rest of program
	public ship(String name){
		this.name = name;
		//TODO set ships systems to the systems being passed in on creation
	}
	public String getName(){
		return name;
	}
	//hitpoints
	public int getHP(){
		return hpCurrent;
	}
	public void setHP(int newHP){
		hpCurrent = newHP;
	}
	public void modHP(int modAmount){
		hpCurrent += modAmount;
		//make sure hp does not go over max amount
		if(hpCurrent > hpMax){
			hpCurrent = hpMax;
		}
		//TODO function to destroy ship if hp is less than 0 (switch this to controller?)
	}
	public int getMaxHP(){
		return hpMax;
	}
	public void setMaxHP(int newMaxHP){
		hpMax = newMaxHP;
	}
	public void modMaxHP(int modVal){
		hpMax += modVal;
	}
	//armor
	public int getArmor(){
		return armor;
	}
	public void setArmor(int newArmor){
		armor = newArmor;
	}
	public void modArmor(int modVal){
		armor += modVal;
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
	//speed
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}
	public void modSpeed(int modVal){
		speed += modVal;
	}
	//actions
	public int getCurrentActions(){
		return currentActions;
	}
	public void setCurrentActions(int newActionAmount){
		currentActions = newActionAmount;
	}
	public void modCurrentActions(int modAmount){
		currentActions += modAmount;
	}
	public int getMaxActions(){
		return maxActions;
	}
	public void setMaxActions(int newMaxAction){
		maxActions = newMaxAction;
	}
	public void modMaxActions(int modVal){
		maxActions += modVal;
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
