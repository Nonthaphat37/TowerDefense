import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TowerFire extends Tower {
	
	//set Status tower
		private int Range = 400;
		private int Attack = 10;
		private int Speed = 700;
		private int Element = 2;
		
	
	private Image towerFire;

	public TowerFire(float x, float y) throws SlickException {
		super(x, y);
		towerFire = new Image("res/testTower3.png");
		rangeTower = this.Range;
		attackTower = this.Attack;
		speedTower = this.Speed;
		element = this.Element;
	}
	
	@Override
	public void render(Graphics g) {
		towerFire.draw(x,y);
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		towerFire.setRotation(dir);	
	}
	
}
