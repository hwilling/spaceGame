package gameFiles;

public class Reactor {
	private int numActions;
	public Reactor(int numberOfActions){
		numActions = numberOfActions;
	}
	
	public int getActions(){
		return numActions;
	}
	
	public void setActions(int actionNum){
		numActions = actionNum;
	}
}
