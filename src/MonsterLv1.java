import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;


public class MonsterLv1 extends Monster {
	
	//monster
	private Image Monster;
	private int attack = 10;				//set attck Hp
	private float v = (float)0.5;				//set velocity
	private int hpMonsterthis = 500;
	
	private Rectangle hpBar;

	public MonsterLv1(float x, float y) throws SlickException {
		super(x, y);
		Monster = new Image("res/Monster_Lv1.png");
		//monsterLv1 = new Image("res/Monster_Lv1.png");
		attackCastle = attack;		//set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;

	}
	
	@Override
	public void render(Graphics g) {
		Monster.draw(x,y);
		HpBar(g);
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
		if(checkAnimation == 0){
			Monster.setRotation(0);
		}
		else if(checkAnimation == 1){
			Monster.setRotation(90);
		}
		else if(checkAnimation == 2){
			Monster.setRotation(270);
		}
		
	}
	
}
