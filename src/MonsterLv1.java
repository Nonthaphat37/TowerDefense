import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class MonsterLv1 extends Monster {
	
	//monster
	private SpriteSheet Monster;
	private Animation MonsterAnimation;

	public MonsterLv1(float x, float y, float velocity) throws SlickException {
		super(x, y);
		Monster = new SpriteSheet("res/testAnimation.png",78,78);
		MonsterAnimation = new Animation(Monster,100);
		this.velocity = velocity;
		//monsterLv1 = new Image("res/Monster_Lv1.png");
	}
	
	@Override
	public void render(Graphics g) {
		 MonsterAnimation.draw(x,y);
	}

}
