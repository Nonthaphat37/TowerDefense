
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;


public class MonsterLv2 extends Monster {
	
	//monster
	private SpriteSheet Monster1;
	private SpriteSheet Monster2;
	private Animation MonsterAnimation1;
	private Animation MonsterAnimation2;
	private int attack = 1;				//set attck Hp
	private float v = (float)2.5;				//set velocity
	private int hpMonsterthis = 350;
	
	private Rectangle hpBar;

	public MonsterLv2(float x, float y) throws SlickException {
		super(x, y);
		Monster1 = new SpriteSheet("res/Monsters/MonsterLv2/MonsterLv2_1.png",78,78);
		Monster2 = new SpriteSheet("res/Monsters/MonsterLv2/MonsterLv2_2.png",78,78);
		MonsterAnimation1 = new Animation(Monster1,100);
		MonsterAnimation2 = new Animation(Monster2,100);
		attackCastle = attack;		//set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		CalWidthHpBar = hpMonster/50;
		typeMonster = "MonsterLv2";
	}
	
	@Override
	public void render(Graphics g) {
		if(TowerDefenseGame.typeRunes != 1 && !checkrunes){
			MonsterAnimation1 = new Animation(Monster1, 100);
			MonsterAnimation2 = new Animation(Monster2, 100);
			checkrunes = true;
		}
		else if(TowerDefenseGame.typeRunes == 1 && checkrunes){
			MonsterAnimation1 = new Animation(Monster1, 100000);
			MonsterAnimation2 = new Animation(Monster2, 100000);
			checkrunes = false;
		}
	
		if(checkAnimation == 0 || checkAnimation == 1){
			MonsterAnimation1.draw(x,y);
		}
		else{
			MonsterAnimation2.draw(x,y);
		}
		HpBar(g);
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
	}
	
}
