package gameFiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	
	//Moving the Ship with directional arrows
	public void keyPressed(KeyEvent e) {
			
		int key = e.getKeyCode();
			
		if(key == KeyEvent.VK_LEFT) {
			xCoord = -1;
			movesRemaining = -1;
		}
		if(key == KeyEvent.VK_RIGHT) {
			xCoord = 1;
			movesRemaining = -1;
		}
		if(key == KeyEvent.VK_UP) {
			yCoord = -1;
			movesRemaining = -1;
		}
		if(key == KeyEvent.VK_DOWN) {
			yCoord = 1;
			movesRemaining = -1;
		}
	}
}
