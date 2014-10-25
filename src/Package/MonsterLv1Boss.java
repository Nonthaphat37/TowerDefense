package Package;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class MonsterLv1Boss extends Monster {
	
	//monster
	private SpriteSheet Monster1;
	private SpriteSheet Monster2;
	private Animation MonsterAnimation1;
	private Animation MonsterAnimation2;
	private int attack = 15;				//set attck Hp
	private float v = (float)2.5;				//set velocity
	
	private SpriteSheet MonsterSkill;
	private Animation MonsterSkillAnimation;
	private int timeSkill = 0;
	private boolean skillShow = false;
	private int hpMonsterthis = 3500;


	public MonsterLv1Boss(float x, float y) throws SlickException {
		super(x, y);
		Monster1 = new SpriteSheet("res/Monsters/MonsterLv1Boss/MonsterLv1Boss_1.png", 78, 78);
		Monster2 = new SpriteSheet("res/Monsters/MonsterLv1Boss/MonsterLv1Boss_2.png", 78, 78);
		MonsterSkill = new SpriteSheet("res/MonsterLv1BossSkill.png", 70, 60);
		MonsterAnimation1 = new Animation(Monster1, 100);
		MonsterAnimation2 = new Animation(Monster2, 100);
		MonsterSkillAnimation = new Animation(MonsterSkill,100);
		//monsterLv1 = new Image("res/Monster_Lv1.png");
		attackCastle = attack;		//set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		CalWidthHpBar = hpMonster/50;
		typeMonster = "MonsterLv1Boss";
		velocityGetSkill = v;
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
			if(skillShow){
				MonsterSkillAnimation.draw(x+4,y-60);
			}
			HpBar(g,0,0);
			HpBarForBoss(g);
	}
	
	@Override
	public void update(GameContainer container, int delta) {
			updateposition();
			skill(delta);
	}
	
	
	public void skill(int delta){
		if(hpMonster <= 1000){
			skillShow = true;
			timeSkill += delta;
			if(timeSkill >= 1000){
				v = (float) 5.5;
				velocity = v;
			}
			else{
				v = 0;
				velocity = v;
			}
		}
	}
}
