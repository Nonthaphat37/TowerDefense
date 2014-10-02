import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TowerWater extends Tower {
	
	//set Status tower
		private int Range = 300;
		private int Attack = 50;
		private int Speed = 10;
		private int Element = 1;
		
	
	private Image towerWater;

	public TowerWater(float x, float y) throws SlickException {
		super(x, y);
		towerWater = new Image("res/testTower2.png");
		rangeTower = this.Range;
		attackTower = this.Attack;
		speedTower = this.Speed;
		element = this.Element;
	}
	
	@Override
	public void render(Graphics g) {
		towerWater.draw(x,y);
		RenderRuneAttack();
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		towerWater.setRotation(dir);	
	}
	
}
