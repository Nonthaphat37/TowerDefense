import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Upgrate{
	private int upgrateRangetower;
	private int upgrateSpeedtower;
	private int upgrateAttacktower;
	
	public Upgrate(Tower t) {
		upgrateRangetower = t.rangeTower;
		upgrateSpeedtower = t.speedTower;
		upgrateAttacktower = t.attackTower;
	}
	
	public int upgrateAttack(){
		upgrateAttacktower += 50;
		return upgrateAttacktower;
	}
	
	public int upgrateSpeed(){
		upgrateSpeedtower += 20;
		return upgrateSpeedtower;
	}
	
	public int upgrateRange(){
		upgrateRangetower += 20;
		return upgrateRangetower;
	}	
}
