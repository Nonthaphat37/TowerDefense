package Package;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Package.TowerWater.State;

public class TowerEarth extends Tower {

	// set Status tower
	private int Attack = 90; // 90
	private int Speed = 55; // 5-10
	private int Range = 350;
	private int Element = 3;

	private Image towerEarth;

	public TowerEarth(float x, float y) throws SlickException {
		super(x, y);
		towerEarth = new Image("res/Towers/testTower4.png");
		rangeTower = this.Range;
		attackTower = this.Attack;
		speedTower = this.Speed;
		element = this.Element;
	}

	@Override
	public void render(Graphics g) {
		towerEarth.draw(x, y);
		RenderRuneAttack();
	}

	@Override
	public void update(GameContainer container, int delta) {
		towerEarth.setRotation(dir);
	}

}
