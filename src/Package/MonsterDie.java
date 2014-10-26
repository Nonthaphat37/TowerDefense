package Package;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MonsterDie implements Entity {

	private SpriteSheet SpriteSheetDie;
	private Animation AnimationDie;
	private boolean checkAddDieMonster = false;
	private float x;
	private float y;
	private float gy;
	private float TimeToDelete = 5000;
	public boolean checkEndTime = false;
	Random random = new Random();
	private int dropGold;
	private boolean checkTextGold = true;
	private int setMon = 0;

	public MonsterDie(float x, float y, String typeMonster, int checkAnimation)
			throws SlickException {
		if (typeMonster == "MonsterLv1") {
			dropGold = 1 + random.nextInt(5);
			if (checkAnimation == 0 || checkAnimation == 1) {
				SpriteSheetDie = new SpriteSheet(
						"res/Monsters/MonsterLv1/MonsterLv1_Die01.png", 78, 78);
				AnimationDie = new Animation(SpriteSheetDie, 100);
			} else {
				SpriteSheetDie = new SpriteSheet(
						"res/Monsters/MonsterLv1/MonsterLv1_Die2.png", 78, 78);
				AnimationDie = new Animation(SpriteSheetDie, 100);
			}
			checkAddDieMonster = true;
			setMon = 0;
		} else if (typeMonster == "MonsterLv1MiniBoss") {
			dropGold = 20 + random.nextInt(10);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv1MiniBoss/MonsterLv1MiniBoss_Die.png",
					78, 78);
			AnimationDie = new Animation(SpriteSheetDie, 200);
			checkAddDieMonster = true;
		} else if (typeMonster == "MonsterLv1Boss") {
			dropGold = 70 + random.nextInt(15);
			if (checkAnimation == 0 || checkAnimation == 1) {
				SpriteSheetDie = new SpriteSheet(
						"res/Monsters/MonsterLv1Boss/MonsterLv1Boss_Die01.png",
						78, 78);
				AnimationDie = new Animation(SpriteSheetDie, 100);
			} else {
				SpriteSheetDie = new SpriteSheet(
						"res/Monsters/MonsterLv1Boss/MonsterLv1Boss_Die2.png",
						78, 78);
				AnimationDie = new Animation(SpriteSheetDie, 100);
			}
			checkAddDieMonster = true;
			setMon = 0;
		} else if (typeMonster == "MonsterLv2") {
			dropGold = 5 + random.nextInt(5);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv2/MonsterLv2_Die.png", 78, 78);
			AnimationDie = new Animation(SpriteSheetDie, 100);
			checkAddDieMonster = true;
			setMon = 0;
		} else if (typeMonster == "MonsterLv2MiniBoss") {
			dropGold = 35 + random.nextInt(15);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv2MiniBoss/MonsterLv2MiniBoss_Die.png",
					78, 78);
			AnimationDie = new Animation(SpriteSheetDie, 150);
			checkAddDieMonster = true;
			setMon = 0;
		} else if (typeMonster == "MonsterLv2Boss") {
			dropGold = 130 + random.nextInt(30);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv2Boss/MonsterLv2Boss_Die.png", 100,
					100);
			AnimationDie = new Animation(SpriteSheetDie, 150);
			checkAddDieMonster = true;
			setMon = 1;
		} else if (typeMonster == "MonsterLv3") {
			dropGold = 10 + random.nextInt(8);
			if (checkAnimation == 0 || checkAnimation == 1) {
				SpriteSheetDie = new SpriteSheet(
						"res/Monsters/MonsterLv3/MonsterLv3_Die01.png", 78, 78);
				AnimationDie = new Animation(SpriteSheetDie, 100);
			} else {
				SpriteSheetDie = new SpriteSheet(
						"res/Monsters/MonsterLv3/MonsterLv3_Die2.png", 78, 78);
				AnimationDie = new Animation(SpriteSheetDie, 100);
			}
			checkAddDieMonster = true;
			setMon = 0;
		} else if (typeMonster == "MonsterLv3MiniBoss") {
			dropGold = 65 + random.nextInt(25);
			if (checkAnimation == 0 || checkAnimation == 1) {
				SpriteSheetDie = new SpriteSheet(
						"res/Monsters/MonsterLv3MiniBoss/MonsterLv3MiniBoss_Die01.png",
						110, 110);
				AnimationDie = new Animation(SpriteSheetDie, 100);
			} else {
				SpriteSheetDie = new SpriteSheet(
						"res/Monsters/MonsterLv3MiniBoss/MonsterLv3MiniBoss_Die2.png",
						110, 110);
				AnimationDie = new Animation(SpriteSheetDie, 100);
			}
			checkAddDieMonster = true;
			setMon = 2;
		} else if (typeMonster == "MonsterLv3Boss") {
			dropGold = 160 + random.nextInt(60);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv3Boss/MonsterLv3Boss_Die.png", 120,
					150);
			AnimationDie = new Animation(SpriteSheetDie, 150);
			checkAddDieMonster = true;
			setMon = 3;
		} else if (typeMonster == "MonsterLv4") {
			dropGold = 13 + random.nextInt(7);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv4/MonsterLv4_Die.png", 78, 78);
			AnimationDie = new Animation(SpriteSheetDie, 100);
			checkAddDieMonster = true;
			setMon = 0;
		} else if (typeMonster == "MonsterLv4MiniBoss") {
			dropGold = 135 + random.nextInt(35);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv4MiniBoss/MonsterLv4MiniBoss_Die.png",
					130, 120);
			AnimationDie = new Animation(SpriteSheetDie, 100);
			checkAddDieMonster = true;
			setMon = 4;
		} else if (typeMonster == "MonsterLv4Boss") {
			dropGold = 285 + random.nextInt(85);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv4Boss/MonsterLv4Boss_Die.png", 160,
					180);
			AnimationDie = new Animation(SpriteSheetDie, 150);
			checkAddDieMonster = true;
			setMon = 5;
		} else if (typeMonster == "MonsterLv4Summon") {
			dropGold = 1;
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv4Summon/MonsterLv4Summon_Die.png",
					78, 78);
			AnimationDie = new Animation(SpriteSheetDie, 150);
			checkAddDieMonster = true;
			setMon = 0;
		} else if (typeMonster == "MonsterLv5") {
			dropGold = 13 + random.nextInt(7);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv5/MonsterLv5_Die.png", 100, 100);
			AnimationDie = new Animation(SpriteSheetDie, 100);
			checkAddDieMonster = true;
			setMon = 1;
		} else if (typeMonster == "MonsterLv5MiniBoss") {
			dropGold = 13 + random.nextInt(7);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv5MiniBoss/MonsterLv5MiniBoss_Die.png",
					170, 120);
			AnimationDie = new Animation(SpriteSheetDie, 200);
			checkAddDieMonster = true;
			setMon = 6;
		} else if (typeMonster == "MonsterLv5Boss1") {
			dropGold = 70 + random.nextInt(15);
			if (checkAnimation == 0 || checkAnimation == 1) {
				SpriteSheetDie = new SpriteSheet(
						"res/Monsters/MonsterLv5Boss1/MonsterLv5Boss1_Die01.png",
						200, 220);
				AnimationDie = new Animation(SpriteSheetDie, 100);
			} else {
				SpriteSheetDie = new SpriteSheet(
						"res/Monsters/MonsterLv5Boss1/MonsterLv5Boss1_Die2.png",
						200, 220);
				AnimationDie = new Animation(SpriteSheetDie, 100);
			}
			checkAddDieMonster = true;
			setMon = 7;
		} else if (typeMonster == "MonsterLv5Boss2") {
			dropGold = 13 + random.nextInt(7);
			SpriteSheetDie = new SpriteSheet(
					"res/Monsters/MonsterLv5Boss2/MonsterLv5Boss2_Die.png",
					140, 130);
			AnimationDie = new Animation(SpriteSheetDie, 100);
			checkAddDieMonster = true;
			setMon = 8;
		}
		this.x = x;
		this.y = y;
		this.gy = y;
	}

	@Override
	public void render(Graphics g) {
		if (checkAddDieMonster) {
			if (setMon == 0) {
				AnimationDie.draw(x, y, new Color(255f, 255f, 255f,
						(float) (TimeToDelete / 4000)));
			} else if (setMon == 1) {
				AnimationDie.draw(x - 12, y - 12, new Color(255f, 255f, 255f,
						(float) (TimeToDelete / 4000)));
			} else if (setMon == 2) {
				AnimationDie.draw(x - 22, y - 22, new Color(255f, 255f, 255f,
						(float) (TimeToDelete / 4000)));
			} else if (setMon == 3) {
				AnimationDie.draw(x - 20, y - 72, new Color(255f, 255f, 255f,
						(float) (TimeToDelete / 4000)));
			} else if (setMon == 4) {
				AnimationDie.draw(x - 26, y - 24, new Color(255f, 255f, 255f,
						(float) (TimeToDelete / 4000)));
			} else if (setMon == 5) {
				AnimationDie.draw(x - 36, y - 91, new Color(255f, 255f, 255f,
						(float) (TimeToDelete / 4000)));
			} else if (setMon == 6) {
				AnimationDie.draw(x - 41, y - 30, new Color(255f, 255f, 255f,
						(float) (TimeToDelete / 4000)));
			} else if (setMon == 7) {
				AnimationDie.draw(x - 41, y - 133, new Color(255f, 255f, 255f,
						(float) (TimeToDelete / 4000)));
			} else if (setMon == 8) {
				AnimationDie.draw(x - 21, y - 41, new Color(255f, 255f, 255f,
						(float) (TimeToDelete / 4000)));
			}
			AnimationDie.setLooping(false);
			TextGold(g);
		}
	}

	public void TextGold(Graphics g) {
		if (checkTextGold) {
			g.setColor(new Color(155f, 255f, 20f, (float) (1 - (y - gy) / 50)));
			g.drawString("+" + dropGold, x + 25, gy + 5);
		}
	}

	@Override
	public void update(GameContainer container, int delta) {
		if (TimeToDelete == 5000) {
			TowerDefenseGame.Gold += dropGold;
		}
		TimeToDelete -= delta;
		if (TimeToDelete <= 0) {
			checkEndTime = true;
		}
		if (checkTextGold) {
			gy -= 0.8;
			if (gy < y - 50) {
				checkTextGold = false;
			}
		}
	}

}
