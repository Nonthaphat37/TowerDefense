package Package;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class MonsterLv3Boss extends Monster {
	
	//monster
	private SpriteSheet Monster;
	private Animation MonsterAnimation;
	private int attack = 15;				//set attck Hp
	private float v = (float)2.2;				//set velocity
	private float velocitySkillMonster = (float) 3.2;
	
	private SpriteSheet MonsterSkill1;
	private SpriteSheet MonsterSkill2;
	private Animation MonsterSkillAnimation1;
	private Animation MonsterSkillAnimation2;
	private int hpMonsterthis = 45000;
	
	private int timingSkillConstant = 4000;
	private int timingSkill = timingSkillConstant;
	private int timingDefense1Constant = 1800;
	private int timingDefense1 = timingDefense1Constant;
	private int timingDefense2Constant = 3000;
	private int timingDefense2 = timingDefense2Constant;
	
	private int timingFireball1Constant = 2100;
	private int timingFireball1 = timingFireball1Constant;
	private int timingFireball2Constant = 3000;
	private int timingFireball2 = timingFireball2Constant;
	
	// skill
	private Image ShiledSkill;
	private Image SpeedSkill;
	private boolean skillShow1 = false;
	private boolean skillShow2 = false;
	private Random random = new Random();
	private int skillMonster;
	private boolean skillFireballOn = false;


	public MonsterLv3Boss(float x, float y) throws SlickException {
		super(x, y);
		Monster = new SpriteSheet("res/Monsters/MonsterLv3Boss/MonsterLv3Boss.png", 120, 150);
		MonsterSkill1 = new SpriteSheet("res/Monsters/MonsterLv3Boss/MonsterLv3BossSkill1.png", 150, 150);
		MonsterSkill2 = new SpriteSheet("res/Monsters/MonsterLv3Boss/MonsterLv3BossSkill2.png", 150, 150);
		ShiledSkill = new Image("res/Monsters/MonsterLv3Boss/ShiledEffect.png");
		SpeedSkill = new Image("res/Monsters/MonsterLv3Boss/SpeedEffect.png");
		MonsterAnimation = new Animation(Monster, 100);
		MonsterSkillAnimation1 = new Animation(MonsterSkill1,100);
		MonsterSkillAnimation2 = new Animation(MonsterSkill2,100);
		attackCastle = attack;		//set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		CalWidthHpBar = hpMonster/50;
		typeMonster = "MonsterLv3Boss";
		velocityGetSkill = v;
		skillMonster = random.nextInt(3);
		timingSkillConstant = 4000+random.nextInt(4000);
		timingSkill = timingSkillConstant;
	}
	
	@Override
	public void render(Graphics g) {
			if(TowerDefenseGame.typeRunes != 1 && !checkrunes){
				MonsterAnimation.start();
				checkrunes = true;
			}
			else if(TowerDefenseGame.typeRunes == 1 && checkrunes){
				MonsterAnimation.stop();
				checkrunes = false;
			}
			if(skillShow1){
				MonsterSkillAnimation1.draw(x-20,y-72);
				if(TowerDefenseGame.typeRunes == 1){
					MonsterSkillAnimation1.stop();
				}
				else{
					MonsterSkillAnimation1.start();
				}
			}
			else if(skillShow2){
				MonsterSkillAnimation2.draw(x-20,y-72);
				if(TowerDefenseGame.typeRunes == 1){
					MonsterSkillAnimation2.stop();
				}
				else{
					MonsterSkillAnimation2.start();
				}
			}
			else{
				MonsterAnimation.draw(x-20,y-72);  //20 255 200
			}
			HpBar(g,2,50);
			HpBarForBoss(g);
			if(skillDefenseOn){
				ShiledSkill.draw(x+28,y-80);
			}
			else if(skillFireballOn){
				SpeedSkill.draw(x+28,y-75);
			}
	}
	
	@Override
	public void update(GameContainer container, int delta) {
			updateposition();
			skill(delta);
	}
	
	
	public void skill(int delta){
		if(TowerDefenseGame.typeRunes != 1){
			if(timingSkill > 0){
				timingSkill -= delta;
			}
			else{
				v = 0;
				velocity = v;
				if(skillMonster == 0 || skillMonster == 1){
					skillShow1 = true;
					timingDefense1 -= delta;
					if(timingDefense1 <= 0){
						timingSkillConstant = 4000+random.nextInt(4000);
						timingSkill = timingSkillConstant;
						timingDefense1 = timingDefense1Constant;
						skillShow1 = false;
						skillDefenseOn = true;
						MonsterSkillAnimation1.stop();
						v = velocityGetSkill;
						velocity = v;
						skillMonster = random.nextInt(3);
					 }
				}
				else{
					skillShow2 = true;
					timingFireball1 -= delta;
					if(timingFireball1 <= 0){
						timingSkillConstant = 4000+random.nextInt(4000);
						timingSkill = timingSkillConstant;
						timingFireball1 = timingFireball1Constant;
						skillShow2 = false;
						skillFireballOn = true;
						MonsterSkillAnimation2.stop();
						v = velocityGetSkill;
						velocity = v;
						skillMonster = random.nextInt(3);
						attack+=5;
						attackCastle = attack;
					 }
				}
			}
			if(skillDefenseOn){
				timingDefense2 -= delta;
				if(timingDefense2 <= 0){
					timingDefense2 = timingDefense2Constant;
					skillDefenseOn = false;
				}
			}
			else if(skillFireballOn){
				timingFireball2 -= delta;
				v = velocitySkillMonster;
				if(timingFireball2 <= 0){
					timingFireball2 = timingFireball2Constant;
					v = velocityGetSkill;
					skillFireballOn = false;
				}
				velocity = v;
			}
		}
	}
}
