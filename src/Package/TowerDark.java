package Package;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TowerDark extends Tower {
	 
	//set Status tower
	private int Attack = 25;
	private int Speed = 20;
	private int Range = 280;
	private int Element = 0;
	
	private Image towerDark;
	
	public TowerDark(float x, float y) throws SlickException {
		super(x, y);
		towerDark = new Image("res/Towers/testTower.png");
		rangeTower = this.Range;
		attackTower = this.Attack;
		speedTower = this.Speed;
		element = this.Element;
	}
	

	@Override
	public void render(Graphics g) {
		towerDark.draw(x,y);
		RenderRuneAttack();
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		towerDark.setRotation(dir);	
	}
}
