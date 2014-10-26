package Package;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MonsterLv5MiniBoss extends Monster {

	// monster
	private SpriteSheet Monster;
	private Animation MonsterAnimation;
	private int attack = 20; // set attck Hp
	private float v = (float) 1.5; // set velocity

	private int hpMonsterthis = 100000;

	// skill
	private boolean skillShow = false;
	private float timeSkill1Constant = 6000;
	private float timeSkill1 = timeSkill1Constant;
	private float timeSkill2Constant = 1800;
	private float timeSkill2 = timeSkill2Constant;
	private float timeSkill3Constant = 6000;
	private float timeSkill3 = timeSkill3Constant;
	private SpriteSheet MonsterSkill;
	private Animation MonsterSkillAnimation;
	private Image Shiled50Skill;
	private Random random;

	public MonsterLv5MiniBoss(float x, float y) throws SlickException {
		super(x, y);
		Monster = new SpriteSheet(
				"res/Monsters/MonsterLv5MiniBoss/MonsterLv5MiniBoss.png", 170,
				120);
		MonsterSkill = new SpriteSheet(
				"res/Monsters/MonsterLv5MiniBoss/MonsterLv5MiniBossSkill.png",
				200, 200);
		Shiled50Skill = new Image(
				"res/Monsters/MonsterLv5MiniBoss/Shiled50Effect.png");
		MonsterAnimation = new Animation(Monster, 130);
		MonsterSkillAnimation = new Animation(MonsterSkill, 150);
		attackCastle = attack; // set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		hpMonsterMax = hpMonster;
		CalWidthHpBar = hpMonster / 70;
		typeMonster = "MonsterLv5MiniBoss";
		random = new Random();
		timeSkill1 = timeSkill1Constant + random.nextInt(4000);
		velocityOld = v;
		velocityGetSkill = v / 2;
		
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
		if (skillShow) {
			if (getSkillFrost) {
				MonsterSkillAnimation.draw(x - 56, y - 91, new Color(40, 160,
						230));
			} else {
				MonsterSkillAnimation.draw(x - 56, y - 91);
			}
			if (TowerDefenseGame.typeRunes == 1) {
				MonsterSkillAnimation.stop();
			} else {
				MonsterSkillAnimation.start();
			}
		} else if (getSkillFrost) {
			MonsterAnimation.draw(x - 41, y - 30, new Color(40, 160, 230));
		} else {
			MonsterAnimation.draw(x - 41, y - 30);
		}
		HpBar(g, -11, 31);
		if (skillDefense50On) {
			Shiled50Skill.draw(x + 25, y - 66);
		}
	}

	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
		TimerSkill(delta);
		Skill(delta);
	}

	public void Skill(int delta) {
		if (TowerDefenseGame.typeRunes != 1) {
			if (timeSkill1 > 0) {
				timeSkill1 -= delta;
			} else {
				timeSkill2 -= delta;
				skillShow = true;
				v = (float) 0;
				velocity = v;
				if (timeSkill2 <= 0) {
					timeSkill1 = timeSkill1Constant + random.nextInt(5000);
					timeSkill2 = timeSkill2Constant;
					v = velocityOld;
					velocity = v;
					skilTowerTimer = 0;
					getSkillFrost = false;
					skillDefense50On = true;
					skillShow = false;
					MonsterSkillAnimation.stop();
				}
			}
			if (skillDefense50On) {
				timeSkill3 -= delta;
				if (timeSkill3 <= 0) {
					timeSkill3 = timeSkill3Constant;
					skillDefense50On = false;
				}
			}
		}
	}
}
