import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TowerWater extends Tower {
	
	//set Status tower
	private int Attack = 45;
	private int Speed = 25;
	private int Range = 320;
	private int Element = 1;
		
	
	private Image towerWater;

	public TowerWater(float x, float y) throws SlickException {
		super(x, y);
		towerWater = new Image("res/Towers/testTower2.png");
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
