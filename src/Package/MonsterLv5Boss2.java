package Package;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MonsterLv5Boss2 extends Monster {

	// monster
	private SpriteSheet Monster;
	private Animation MonsterAnimation;
	private int attack = 10; // set attck Hp
	private float v = (float) 1.2; // set velocity

	private SpriteSheet MonsterSkill1;
	private Animation MonsterSkillAnimation1;
	private SpriteSheet MonsterSkill2;
	private Animation MonsterSkillAnimation2;
	private boolean skillShow1 = false;
	private boolean skillShow2 = false;
	private int hpMonsterthis = 600000;

	// skill1
	private int timingSkillConstant = 3000;
	private int timingSkill = timingSkillConstant;
	private int timingBoost1Constant = 1500;
	private int timingBoost1 = timingBoost1Constant;
	private int timingBoost2Constant = 3000;
	private int timingBoost2 = timingBoost2Constant;
	private Random random = new Random();
	private int skillMonster;
	private Image SpeedSkill;
	private boolean skillSpeedOn = false;
	private float velocitySkillMonster = (float) 3.5;

	// skill2
	private int timingDefense50_1Constant = 1800;
	private int timingDefense50_1 = timingDefense50_1Constant;
	private int timingDefense50_2Constant = 6000;
	private int timingDefense50_2 = timingDefense50_2Constant;
	private Image Shiled50Skill;

	public MonsterLv5Boss2(float x, float y) throws SlickException {
		super(x, y);
		Monster = new SpriteSheet(
				"res/Monsters/MonsterLv5Boss2/MonsterLv5Boss2.png", 110, 130);
		MonsterSkill1 = new SpriteSheet(
				"res/Monsters/MonsterLv5Boss2/MonsterLv5Boss2Skill1.png", 270,
				130);
		MonsterSkill2 = new SpriteSheet(
				"res/Monsters/MonsterLv5Boss2/MonsterLv5Boss2Skill2.png", 170,
				140);
		Shiled50Skill = new Image(
				"res/Monsters/MonsterLv5Boss2/Shiled50Effect.png");
		SpeedSkill = new Image("res/Monsters/MonsterLv5Boss2/SpeedEffect.png");
		MonsterAnimation = new Animation(Monster, 160);
		MonsterSkillAnimation1 = new Animation(MonsterSkill1, 150);
		MonsterSkillAnimation2 = new Animation(MonsterSkill2, 150);
		// monsterLv1 = new Image("res/Monster_Lv1.png");
		attackCastle = attack; // set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		hpMonsterMax = hpMonster;
		hp25percentPerRoundConstant = hpMonster / 4;
		hp25percentPerRound = hpMonster - hp25percentPerRoundConstant;
		passiveskill2percentHp = true;
		CalWidthHpBar = hpMonster / 80;
		CalWidthHpBarBoss = hpMonster / 50;
		typeMonster = "MonsterLv5Boss2";
		velocityGetSkill = v;
		skillMonster = random.nextInt(7);
		timingSkillConstant = 3000 + random.nextInt(4000);
		timingSkill = timingSkillConstant;
		
		element = 0;
	}

	@Override
	public void render(Graphics g) {
		if (TowerDefenseGame.typeRunes != 1 && !checkrunes) {
			MonsterAnimation.start();
			checkrunes = true;
		} else if (TowerDefenseGame.typeRunes == 1 && checkrunes) {
			MonsterAnimation.stop();
			checkrunes = false;
		}
		if (skillShow1) {
			MonsterSkillAnimation1.draw(x - 93, y - 38);
			if (TowerDefenseGame.typeRunes == 1) {
				MonsterSkillAnimation1.stop();
			} else {
				MonsterSkillAnimation1.start();
			}
		} else if (skillShow2) {
			MonsterSkillAnimation2.draw(x - 46, y - 45);
			if (TowerDefenseGame.typeRunes == 1) {
				MonsterSkillAnimation2.stop();
			} else {
				MonsterSkillAnimation2.start();
			}
		} else {
			MonsterAnimation.draw(x - 16, y - 41); // 20 255 200
		}

		if (skillDefense50On) {
			Shiled50Skill.draw(x + 31, y - 68);
		} else if (skillSpeedOn) {
			SpeedSkill.draw(x + 31, y - 68);
		}

		HpBar(g, -10, 40);
		HpBarForBoss(g);
	}

	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
		skill(delta);
	}

	public void skill(int delta) {
		if (TowerDefenseGame.typeRunes != 1) {
			if (timingSkill > 0) {
				timingSkill -= delta;
			} else {
				v = 0;
				velocity = v;
				if (skillMonster == 0) {
					skillShow1 = true;
					timingBoost1 -= delta;
					if (timingBoost1 <= 0) {
						timingSkillConstant = 3000 + random.nextInt(4000);
						timingSkill = timingSkillConstant;
						timingBoost1 = timingBoost1Constant;
						skillShow1 = false;
						attack += 5;
						attackCastle = attack;
						MonsterSkillAnimation1.stop();
						v = velocityGetSkill;
						velocity = v;
						skillMonster = random.nextInt(7);
						skillDefense50On = false;
						skillSpeedOn = true;
					}
				} else {
					skillShow2 = true;
					timingDefense50_1 -= delta;
					if (timingDefense50_1 <= 0) {
						timingSkillConstant = 3000 + random.nextInt(4000);
						timingSkill = timingSkillConstant;
						timingDefense50_1 = timingDefense50_1Constant;
						skillShow2 = false;
						skillDefense50On = true;
						MonsterSkillAnimation2.stop();
						v = velocityGetSkill;
						velocity = v;
						skillMonster = random.nextInt(7);
						timingDefense50_2 = timingDefense50_2Constant;
					}
				}
			}
			if (skillDefense50On) {
				timingDefense50_2 -= delta;
				if (timingDefense50_2 <= 0) {
					timingDefense50_2 = timingDefense50_2Constant;
					skillDefense50On = false;
				}
			} else if (skillSpeedOn) {
				timingBoost2 -= delta;
				v = velocitySkillMonster;
				if (timingBoost2 <= 0) {
					timingBoost2 = timingBoost2Constant;
					v = velocityGetSkill;
					skillSpeedOn = false;
				}
				velocity = v;
			}
		}
	}

}
