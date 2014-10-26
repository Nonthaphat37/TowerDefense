package Package;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class MonsterLv4Boss extends Monster {
	
	//monster
	private SpriteSheet Monster;
	private Animation MonsterAnimation;
	private int attack = 30;				//set attck Hp
	private float v = (float)1.3;				//set velocity
	
	private SpriteSheet MonsterSkill1;
	private Animation MonsterSkillAnimation1;
	private SpriteSheet MonsterSkill2;
	private Animation MonsterSkillAnimation2;
	private boolean skillShow1 = false;
	private boolean skillShow2 = false;
	private int hpMonsterthis = 120000;
	
	//skill1
	private int timingSkillConstant = 2000;
	private int timingSkill = timingSkillConstant;
	private int timingSummon1Constant = 1610;   
	private int timingSummon1 = timingSummon1Constant;
	private Random random = new Random();
	private int skillMonster;
	
	//skill2
	private int timingDefense50_1Constant = 1800;   
	private int timingDefense50_1 = timingDefense50_1Constant;
	private int timingDefense50_2Constant = 6500;   
	private int timingDefense50_2 = timingDefense50_2Constant;
	private Image Shiled50Skill;

	public MonsterLv4Boss(float x, float y) throws SlickException {
		super(x, y);
		Monster = new SpriteSheet("res/Monsters/MonsterLv4Boss/MonsterLv4Boss.png", 160, 180);
		MonsterSkill1 = new SpriteSheet("res/Monsters/MonsterLv4Boss/MonsterLv4BossSkill1.png", 160, 180);
		MonsterSkill2 = new SpriteSheet("res/Monsters/MonsterLv4Boss/MonsterLv4BossSkill2.png", 300, 250);
		Shiled50Skill = new Image("res/Monsters/MonsterLv4Boss/Shiled50Effect.png");
		MonsterAnimation = new Animation(Monster, 160);
		MonsterSkillAnimation1 = new Animation(MonsterSkill1,230);
		MonsterSkillAnimation2 = new Animation(MonsterSkill2,150);
		//monsterLv1 = new Image("res/Monster_Lv1.png");
		attackCastle = attack;		//set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		CalWidthHpBar = hpMonster/50;
		typeMonster = "MonsterLv4Boss";
		velocityGetSkill = v;
		skillMonster = random.nextInt(5);
		timingSkillConstant = 2000+random.nextInt(7000);
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
				MonsterSkillAnimation1.draw(x-36,y-91);
				if(TowerDefenseGame.typeRunes == 1){
					MonsterSkillAnimation1.stop();
				}
				else{
					MonsterSkillAnimation1.start();
				}
			}
			else if(skillShow2){
				MonsterSkillAnimation2.draw(x-51,y-131);
				if(TowerDefenseGame.typeRunes == 1){
					MonsterSkillAnimation2.stop();
				}
				else{
					MonsterSkillAnimation2.start();
				}
			}
			else{
				MonsterAnimation.draw(x-36,y-91);  //20 255 200
			}
			
			if(skillDefense50On){
				Shiled50Skill.draw(x+43,y-111);
			}
			
			HpBar(g,14,80);
			HpBarForBoss(g);
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
				if(skillMonster == 0 || skillMonster == 1 || skillMonster == 2){
					skillShow1 = true;
					timingSummon1 -= delta;
					if(timingSummon1 <= 0){
						timingSkillConstant = 2000+random.nextInt(7000);
						timingSkill = timingSkillConstant;
						timingSummon1 = timingSummon1Constant;
						skillShow1 = false;
						skillSummonOn = true;
						MonsterSkillAnimation1.stop();
						v = velocityGetSkill;
						velocity = v;
						skillMonster = random.nextInt(5);
					 }
				}
				else{
					skillShow2 = true;
					timingDefense50_1 -= delta;
					if(timingDefense50_1 <= 0){
						timingSkillConstant = 2000+random.nextInt(7000);
						timingSkill = timingSkillConstant;
						timingDefense50_1 = timingDefense50_1Constant;
						skillShow2 = false;
						skillDefense50On = true;
						MonsterSkillAnimation2.stop();
						v = velocityGetSkill;
						velocity = v;
						skillMonster = random.nextInt(5);
						timingDefense50_2 = timingDefense50_2Constant;
					 }
				}
			}
			if(skillDefense50On){
				timingDefense50_2 -= delta;
				if(timingDefense50_2 <= 0){
					timingDefense50_2 = timingDefense50_2Constant;
					skillDefense50On = false;
				}
			}
		}
	}
}
