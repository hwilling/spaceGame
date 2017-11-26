package gameFiles;

public class Hull {
	private int hp, armor;
	
	public Hull(int hp, int armor){
		this.hp = hp;
		this.armor = armor;
	}
	
	public int getHP(){
		return hp;
	}
	
	public void setHP(int hp){
		this.hp = hp;
	}
	
	public int getArmor(){
		return armor;
	}
	
	public void setArmor(int armor){
		this.armor = armor;
	}

}
