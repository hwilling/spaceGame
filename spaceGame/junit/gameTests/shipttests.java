package gameTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import gameFiles.Engine;
import gameFiles.Hull;
import gameFiles.Reactor;
import gameFiles.ShieldGenerator;
import gameFiles.WeaponSystems;
import gameFiles.combatController;
import gameFiles.ship;

public class shipttests {

	
	private ship testee = new ship("testShip");
	private ship testee2 = new ship("other testShip");
	private ArrayList<ship> ships1 = new ArrayList<ship>();
	private ArrayList<ship> ships2 = new ArrayList<ship>();
	private int numPlayers = 2;
	private int setDMGVal = 8;
	private int setMovesVal = 10;
	private int setSpeedVal = 11;
	private int setRangeVal = 9;
	private int setCoords[] = {5, 5, 6, 6};
	private int setActionVal = 2;
	private int setMaxActionVal = 3;
	private int setHPVal = 12;
	private int setMaxHPVal = 20;
	/*private Engine engine = new Engine(setSpeedVal);
	private ShieldGenerator shieldGen = new ShieldGenerator(5, 5);
	private Hull hull = new Hull(5, 5);
	private Reactor reactor = new Reactor(12);
	private WeaponSystems weapons = new WeaponSystems(20, 21);*/
	//private ship testee = new ship("testShip", engine, shieldGen, weapons, reactor, hull);
	//private ship testee2 = new ship("other testShip", engine, shieldGen, weapons, reactor, hull);
	
	@Before
	public void setUp(){
		testee.setDamageValue(setDMGVal);
		testee.setMovesRemaining(setMovesVal);
		testee.setSpeed(setSpeedVal);
		testee.setRange(setRangeVal);
		testee.setXCoord(setCoords[0]);
		testee.setYCoord(setCoords[1]);
		testee.setCurrentActions(setActionVal);
		testee.setMaxActions(setMaxActionVal);
		testee.setHP(setHPVal);
		testee.setMaxHP(setMaxHPVal);
		testee2.setDamageValue(setDMGVal);
		testee2.setMovesRemaining(setMovesVal);
		testee2.setSpeed(setSpeedVal);
		testee2.setRange(setRangeVal);
		testee2.setXCoord(setCoords[2]);
		testee2.setYCoord(setCoords[3]);
		testee2.setCurrentActions(setActionVal);
		testee2.setMaxActions(setMaxActionVal);
		testee2.setHP(setHPVal);
		testee2.setMaxHP(setMaxHPVal);
	}
	
	private combatController testCC = new combatController(numPlayers, ships1, ships2);
	
	/*@Test
	public void testSubSystems(){
		assertEquals(setSpeedVal, testee.getEngine().getMoves());
		engine.setMoves(300);
		assertEquals(setSpeedVal, testee.getEngine().getMoves());
	}*/
	
	@Test
	public void testSettingShip(){
		assertEquals(setDMGVal, testee.getDamageValue());
		assertEquals(setMovesVal, testee.getMovesRemaining());
		assertEquals(setSpeedVal, testee.getSpeed());
		assertEquals(setRangeVal, testee.getRange());
		assertEquals(setCoords[0], testee.getXCoord());
		assertEquals(setCoords[1], testee.getYCoord());
		assertEquals(setActionVal, testee.getCurrentActions());
		assertEquals(setMaxActionVal, testee.getMaxActions());
		assertEquals(setHPVal, testee.getHP());
		assertEquals(setMaxHPVal, testee.getMaxHP());
	}
	
	@Test
	public void testTurnReset() {
		testee.modCurrentActions(-1);
		testee.modMovesRemaining(-3);
		testee.turnReset();
		assertEquals(testee.getMaxActions(), testee.getCurrentActions());
		assertEquals(testee.getSpeed(), testee.getMovesRemaining());
	}
	
	@Test
	public void testModHP(){
		int hpCheck = testee.getHP();
		hpCheck = hpCheck -4;
		testee.modHP(-4);
		assertEquals(hpCheck, testee.getHP());
		testee.setHP(testee.getMaxHP());
		testee.modHP(11);
		assertEquals(testee.getMaxHP(), testee.getHP());
	}
	
	@Test
	public void testGetDist(){
		//diagonals
		assertEquals(1, testCC.getDist(testee.getXCoord(), testee.getYCoord(), testee2.getXCoord(), testee2.getYCoord()));
		testee2.setYCoord(4);
		assertEquals(1, testCC.getDist(testee.getXCoord(), testee.getYCoord(), testee2.getXCoord(), testee2.getYCoord()));
		testee2.setXCoord(4);
		assertEquals(1, testCC.getDist(testee.getXCoord(), testee.getYCoord(), testee2.getXCoord(), testee2.getYCoord()));
		testee2.setYCoord(6);
		assertEquals(1, testCC.getDist(testee.getXCoord(), testee.getYCoord(), testee2.getXCoord(), testee2.getYCoord()));
		for(int i = 5; i < 25; i++){
			//up
			assertEquals(i-5, testCC.getDist(testee.getXCoord(), testee.getYCoord(), 5, i));
			//right
			assertEquals(i-5, testCC.getDist(testee.getXCoord(), testee.getYCoord(), i, 5));
		}
		for(int i = 0; i < 5; i++){
			//down
			assertEquals(5-i, testCC.getDist(testee.getXCoord(), testee.getYCoord(), 5, i));
			//left
			assertEquals(5-i, testCC.getDist(testee.getXCoord(), testee.getYCoord(), i, 5));
		}
	}
	
	@Test
	public void testMove(){
		int startMovement = testee.getMovesRemaining();
		testCC.moveShip(testee, 6, 6);
		assertEquals(6, testee.getXCoord());
		assertEquals(6, testee.getYCoord());
		assertEquals(startMovement -1, testee.getMovesRemaining());
		//TODO more vigorous testing
		testCC.moveShip(testee, 10, 12);
		assertEquals(10, testee.getXCoord());
		assertEquals(12, testee.getYCoord());
	}
	
	@Test
	public void testShoot(){
		testee.setXCoord(5);
		testee.setYCoord(5);
		ships1.add(testee);
		ships2.add(testee2);
		combatController testController = new combatController(numPlayers, ships1, ships2);
		//System.out.println(testee2.getHP());
		testController.shoot(0, 0);
		//System.out.println(testee2.getHP());
		assertEquals(setHPVal - setDMGVal, testee2.getHP());
	}
	
	@Test
	public void testWins(){
		ships1.add(testee);
		ships2.add(testee2);
		combatController testController = new combatController(numPlayers, ships1, ships2);
		assertEquals(0, testController.whoWins());
		assertEquals(false, testController.endGame());
		testController.shoot(0, 0);
		testController.shoot(0, 0);
		assertEquals(1, testController.whoWins());
		assertEquals(true, testController.endGame());
	}
	
	@Test
	public void testTurnSwitch(){
		ships1.add(testee);
		ships2.add(testee2);
		combatController testController = new combatController(numPlayers, ships1, ships2);
		assertEquals(0, testController.whosTurn());
		testController.switchPlayer();
		assertEquals(1, testController.whosTurn());
		testController.switchPlayer();
		assertEquals(0, testController.whosTurn());
	}

}
