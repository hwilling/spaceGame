package gameFiles;

public class ShieldGenerator {

	private int shieldCharge, rechargeRate;
	
	public ShieldGenerator(int shield, int recharge){
		shieldCharge = shield;
		rechargeRate = recharge;
	}
	
	public int getShieldCharge(){
		return shieldCharge;
	}
	
	public void setShieldCharge(int charge){
		shieldCharge = charge;
	}
	
	public int getRechargeRate(){
		return rechargeRate;
	}
	
	public void setRechargeRate(int newRate){
		rechargeRate = newRate;
	}
}
