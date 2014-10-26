package Package;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MonsterLv2Boss extends Monster {

	// monster
	private SpriteSheet Monster;
	private Animation MonsterAnimation;
	private int attack = 25; // set attck Hp
	private float v = (float) 1.5; // set velocity

	private SpriteSheet MonsterSkill;
	private Animation MonsterSkillAnimation;
	private boolean skillShow = false;
	private int hpMonsterthis = 12000;

	private int hpHeal = 1000;
	private int timingHeal1 = 8000;
	private int timingHeal2 = 2000;

	public MonsterLv2Boss(float x, float y) throws SlickException {
		super(x, y);
		Monster = new SpriteSheet(
				"res/Monsters/MonsterLv2Boss/MonsterLv2Boss.png", 100, 100);
		MonsterSkill = new SpriteSheet(
				"res/Monsters/MonsterLv2Boss/MonsterLv2BossSkill.png", 100, 100);
		MonsterAnimation = new Animation(Monster, 100);
		MonsterSkillAnimation = new Animation(MonsterSkill, 100);
		// monsterLv1 = new Image("res/Monster_Lv1.png");
		attackCastle = attack; // set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		hpMonsterMax = hpMonster;
		CalWidthHpBar = hpMonster / 50;
		CalWidthHpBarBoss = hpMonster / 50;
		typeMonster = "MonsterLv2Boss";
		velocityGetSkill = v;
		
		element = 3;
	}

	@Override
	public void render(Graphics g) {
		if (TowerDefenseGame.typeRunes != 1 && !checkrunes) {
			MonsterAnimation = new Animation(Monster, 100);
			checkrunes = true;
		} else if (TowerDefenseGame.typeRunes == 1 && checkrunes) {
			MonsterAnimation = new Animation(Monster, 100000);
			checkrunes = false;
		}
		MonsterAnimation.draw(x - 12, y - 12, new Color(20, 255, 200)); // 20
																		// 255
																		// 200
		if (skillShow) {
			MonsterSkillAnimation.draw(x - 12, y - 12);
			if (TowerDefenseGame.typeRunes == 1) {
				MonsterSkillAnimation.stop();
			} else {
				MonsterSkillAnimation.start();
			}
		}
		HpBar(g, 0, 3);
		HpBarForBoss(g);
	}

	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
		skill(delta);
	}

	public void skill(int delta) {
		if (hpMonster < 12000 && TowerDefenseGame.typeRunes != 1) {
			if (timingHeal1 > 0) {
				timingHeal1 -= delta;
			} else {
				v = 0;
				velocity = v;
				skillShow = true;
				timingHeal2 -= delta;
				if (timingHeal2 <= 0) {
					timingHeal1 = 8000;
					timingHeal2 = 2000;
					hpMonster += hpHeal;
					if (hpMonster > 12000) {
						hpMonster = 12000;
					}
					skillShow = false;
					MonsterSkillAnimation.stop();
					v = velocityGetSkill;
					velocity = v;
				}
			}
		}
	}
}
