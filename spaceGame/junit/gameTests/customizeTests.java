package gameTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gameFiles.ship;
import gameFiles.game;
import gameFiles.customizationController;

public class customizeTests {

	private ship testee = new ship("testShip");
	private ship testee2 = new ship("other testShip");
	private ship ships1[] = {testee};
	private ship ships2[] = {testee2};
	private int setDMGVal = 8;
	private int setMovesVal = 10;
	private int setSpeedVal = 11;
	private int setRangeVal = 9;
	private int setCoords[] = {5, 5, 6, 6};
	private int setActionVal = 2;
	private int setMaxActionVal = 3;
	private int setHPVal = 12;
	private int setMaxHPVal = 20;
	private boolean	Player1;
	private boolean Player2;
	private boolean selectH = false;
	private boolean selectA = false;
	private boolean selectS = false;
	private boolean selectR = false;
	
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
	
	private customizationController testCustom = new customizationController(Player1, Player2, ships1, ships2);
	
	@Test
	public void testP1HealthBoost()
	{
		selectH = true;
			
		assertEquals(21, testCustom.P1boostHealth(testee));
	}
	
	@Test
	public void testP1AttackBoost()
	{
		selectA = true;

		assertEquals(9, testCustom.P1boostAttack(testee));
	}
	
	@Test
	public void testP1SpeedBoost()
	{
		selectS = true;

		assertEquals(12, testCustom.P1boostSpeed(testee));
	}
	
	@Test
	public void testP1RangeBoost()
	{
		selectR = true;

		assertEquals(10, testCustom.P1boostRange(testee));
	}
	
	@Test
	public void testP2HealthBoost()
	{
		selectH = true;
			
		assertEquals(21, testCustom.P2boostHealth(testee2));
	}
	
	@Test
	public void testP2AttackBoost()
	{
		selectA = true;

		assertEquals(9, testCustom.P2boostAttack(testee2));
	}
	
	@Test
	public void testP2SpeedBoost()
	{
		selectS = true;

		assertEquals(12, testCustom.P2boostSpeed(testee2));
	}
	
	@Test
	public void testP2RangeBoost()
	{
		selectR = true;

		assertEquals(10, testCustom.P2boostRange(testee2));
	}
}
