import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TowerFire extends Tower {
	
	//set Status tower
	private int Attack = 15;
	private int Speed = 35;
	private int Range = 300;
	private int Element = 2;
		
	
	private Image towerFire;

	public TowerFire(float x, float y) throws SlickException {
		super(x, y);
		towerFire = new Image("res/Towers/testTower3.png");
		rangeTower = this.Range;
		attackTower = this.Attack;
		speedTower = this.Speed;
		element = this.Element;
	}
	
	@Override
	public void render(Graphics g) {
		towerFire.draw(x,y);
		RenderRuneAttack();
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		towerFire.setRotation(dir);	
	}
	
}
