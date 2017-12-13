package gameFiles;

public enum ProjectileType {
	ION(0.5, 1.50), LASER(1, 1), MISSLE(1.5, 0.5), KINETIC(1,1);
	private double shieldDMG, hpDMG = 0;
	
	private ProjectileType(double hpDMG, double shieldDMG){
		this.hpDMG = hpDMG;
		this.shieldDMG = shieldDMG;
	}
	
	public double getHpDMG(){
		return hpDMG;
	}
	
	public double getShieldDMG(){
		return shieldDMG;
	}
}
