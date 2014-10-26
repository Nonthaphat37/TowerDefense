package Package;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MonsterLv5Boss1 extends Monster {

	// monster
	private SpriteSheet Monster1;
	private SpriteSheet Monster2;
	private Animation MonsterAnimation1;
	private Animation MonsterAnimation2;
	private int attack = 15; // set attck Hp
	private float v = (float) 2.5; // set velocity

	private SpriteSheet MonsterSkill;
	private Animation MonsterSkillAnimation;
	private int timeSkill = 0;
	private boolean skillShow = false;
	private int hpMonsterthis = 300000;

	public MonsterLv5Boss1(float x, float y) throws SlickException {
		super(x, y);
		Monster1 = new SpriteSheet(
				"res/Monsters/MonsterLv5Boss1/MonsterLv5Boss1_1.png", 200, 220);
		Monster2 = new SpriteSheet(
				"res/Monsters/MonsterLv5Boss1/MonsterLv5Boss1_2.png", 200, 220);
		MonsterSkill = new SpriteSheet(
				"res/Monsters/MonsterLv5Boss1/MonsterLv5BossSkill.png", 70, 60);
		MonsterAnimation1 = new Animation(Monster1, 100);
		MonsterAnimation2 = new Animation(Monster2, 100);
		MonsterSkillAnimation = new Animation(MonsterSkill, 100);
		// monsterLv1 = new Image("res/Monster_Lv1.png");
		attackCastle = attack; // set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		hpMonsterMax = hpMonster;
		CalWidthHpBar = hpMonster / 80;
		CalWidthHpBarBoss = hpMonster / 50;
		typeMonster = "MonsterLv5Boss1";
		velocityGetSkill = v;
		
		element = 0;
	}

	@Override
	public void render(Graphics g) {
		if (TowerDefenseGame.typeRunes != 1 && !checkrunes) {
			MonsterAnimation1.start();
			MonsterAnimation2.start();
			checkrunes = true;
		} else if (TowerDefenseGame.typeRunes == 1 && checkrunes) {
			MonsterAnimation1.stop();
			MonsterAnimation2.stop();
			checkrunes = false;
		}

		if (checkAnimation == 0 || checkAnimation == 1) {
			MonsterAnimation1.draw(x - 41, y - 133);
		} else {
			MonsterAnimation2.draw(x - 41, y - 133);
		}
		if (skillShow) {
			MonsterSkillAnimation.draw(x + 10, y - 200);
		}
		HpBar(g, -12, 135);
		HpBarForBoss(g);
	}

	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
		skill(delta);
	}

	public void skill(int delta) {
		if (hpMonster <= 50000) {
			skillShow = true;
			timeSkill += delta;
			if (timeSkill >= 1500) {
				v = (float) 5;
				velocity = v;
			} else {
				v = 0;
				velocity = v;
			}
		}
	}
}
