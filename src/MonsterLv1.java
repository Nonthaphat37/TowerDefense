import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class MonsterLv1 extends Monster {
	
	//monster
	private SpriteSheet Monster1;
	private SpriteSheet Monster2;
	private Animation MonsterAnimation1;
	private Animation MonsterAnimation2;
	private int attack = 1;				//set attck Hp
	private float v = (float)0.5;				//set velocity
	
	
	
	
	
	

	public MonsterLv1(float x, float y) throws SlickException {
		super(x, y);
		Monster1 = new SpriteSheet("res/MonsterLv1_1.png", 78, 78);
		Monster2 = new SpriteSheet("res/MonsterLv1_2.png", 78, 78);
		MonsterAnimation1 = new Animation(Monster1, 100);
		MonsterAnimation2 = new Animation(Monster2, 100);
		//monsterLv1 = new Image("res/Monster_Lv1.png");
		attackCastle = attack;		//set attack Hp
		velocity = v;
	}
	
	@Override
	public void render(Graphics g) {
		if(checkAnimation == 0){
			MonsterAnimation1.draw(x,y);
		}
		else{
			MonsterAnimation2.draw(x,y);
		}
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
	}
	

}
