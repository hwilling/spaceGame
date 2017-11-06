package gameTests;

import static org.junit.Assert.*;

import org.junit.Test;

import gameFiles.ship;

public class shipttests {

	ship testee = new ship("testShip");
	private int setDMGVal = 15;
	private int setMovesVal = 10;
	private int setSpeedVal = 11;
	private int setRangeVal = 9;
	private int setCoords[] = {5, 5};
	private int setActionVal = 2;
	private int setMaxActionVal = 3;
	private int setHPVal = 12;
	private int setMaxHPVal = 20;
	
	@Test
	public void testSettingShip(){
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
		assertEquals(testee.getDamageValue(), setDMGVal);
		assertEquals(testee.getMovesRemaining(), setMovesVal);
		assertEquals(testee.getSpeed(), setSpeedVal);
		assertEquals(testee.getRange(), setRangeVal);
		assertEquals(testee.getXCoord(), setCoords[0]);
		assertEquals(testee.getYCoord(), setCoords[1]);
		assertEquals(testee.getCurrentActions(), setActionVal);
		assertEquals(testee.getMaxActions(), setMaxActionVal);
		assertEquals(testee.getHP(), setHPVal);
		assertEquals(testee.getMaxHP(), setMaxHPVal);
	}
	
	@Test
	public void testTurnReset() {
		testee.modCurrentActions(-1);
		testee.modMovesRemaining(-3);
		testee.turnReset();
		assertEquals(testee.getCurrentActions(), testee.getMaxActions());
		assertEquals(testee.getMovesRemaining(), testee.getSpeed());
	}
	
	@Test
	public void testModHP(){
		int hpCheck = testee.getHP();
		hpCheck = hpCheck -4;
		testee.modHP(-4);
		assertEquals(testee.getHP(), hpCheck);
		testee.setHP(testee.getMaxHP());
		testee.modHP(11);
		assertEquals(testee.getHP(), testee.getMaxHP());
	}

}
