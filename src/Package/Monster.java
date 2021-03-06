package Package;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Monster implements Entity {

	protected float x;
	protected float y;
	protected float velocity = (float) 2;
	protected float velocityOld = velocity;

	private int checkfieldX = 0;
	private int checkfieldY = 0;

	private boolean errorPosition = false;
	private boolean errorStarted = true;
	private boolean errorFinish = false;

	private int checkfield1 = 0;
	private int checkfield2 = 0;
	private int checkfield3 = 0;

	// set everything in monster
	protected int checkAnimation = 0;
	protected int attackCastle;
	protected int hpMonsterMax = 500;
	protected int hpMonster = hpMonsterMax;
	protected int element = 0;

	private Rectangle hpBar;
	private Rectangle hpBarBoss;
	protected int CalWidthHpBar;
	protected int CalWidthHpBarBoss;

	protected String typeMonster;

	protected boolean checkrunes = true;

	// skill tower
	protected int skilTowerTimer = 0;
	private int skilTowerTimerMax = 2000;
	public boolean getSkillTower = false;
	protected float velocityGetSkill = velocity / 2;
	protected boolean getSkillFrost = false;

	// skill Monster
	protected boolean skillDefenseOn = false; // skill boss fire
	protected boolean skillSummonOn = false; // skill boss water
	protected boolean skillDefense50On = false; // skill boss water
	protected boolean passiveskill2percentHp = false;
	protected int hp25percentPerRoundConstant = hpMonster / 4; // skill passive
																// boss final 2
	protected int hp25percentPerRound = hpMonster - hp25percentPerRoundConstant; // skill
																					// passive
																					// boss
																					// final
																					// 2

	public Monster(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void MonsterAttacked(int attack, int element, boolean skillOn) {
		if (skillDefense50On) {
			attack /= 2;
		}
		if (!skillDefenseOn) {
			if (this.element == 1 && element == 3) {
				hpMonster -= attack * 2;
			} else if (this.element == 1 && element == 2) {
				hpMonster -= attack / 2; 
			} else if (this.element == 2 && element == 1) {
				hpMonster -= attack * 2; 
			} else if (this.element == 2 && element == 3) {
				hpMonster -= attack / 2; 
			} else if (this.element == 3 && element == 2) {
				hpMonster -= attack * 2; 
			} else if (this.element == 3 && element == 1) {
				hpMonster -= attack / 2; 
			} else{
				hpMonster -= attack; 
			} 
				
			if (skillOn) {
				skilTowerTimer = 0;
			}
		}
		if (typeMonster == "MonsterLv5Boss2" && passiveskill2percentHp) {
			if (hpMonster <= hp25percentPerRound) {
				hpMonster = hp25percentPerRound;
			}
		}
	}

	public boolean getDeath() {
		if (hpMonster <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public void positionError(int block_x, int block_y) {
		if (checkfieldY == 5) {
			if (checkfieldX == 20) {
				errorPosition = false;
			} else {
				x += velocity;
				checkAnimation = 0;
				// monsterLv1.setRotation(0);
			}
		} else {
			y -= velocity;
			checkAnimation = 2;
			// monsterLv1.setRotation(270);
		}
	}

	public void Start(int check_startX) {
		if (checkfieldX < 2) {
			x += velocity;
			checkAnimation = 0;
			// monsterLv1.setRotation(0);
		} else {
			errorStarted = false;
		}
	}

	public void Finish(int check_finshY) {
		if (checkfieldY > -2) {
			// monsterLv1.setRotation(270);
			y -= velocity;
			checkAnimation = 2;
		} else {
			TowerDefenseGame.HpGame -= attackCastle;
			x = TowerDefenseGame.monster_startX - 100; // / monster in castle
			y = TowerDefenseGame.monster_startY;
			errorStarted = true;
			errorFinish = false;

			if (passiveskill2percentHp) {
				hp25percentPerRound = hpMonster - hp25percentPerRoundConstant;
			}
		}

	}

	@Override
	public void render(Graphics g) {

	}

	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
	}

	protected void updateposition() {
		if (TowerDefenseGame.typeRunes != 1) {
			checkfieldX = (int) (x / fieldBuild.sizeRect);
			checkfieldY = (int) (y / fieldBuild.sizeRect);
			if (errorStarted) {
				Start(checkfieldX);
			} else if (checkfieldX == 32 && checkfieldY == 1) {
				errorFinish = true;
			} else if (!errorFinish) {
				if (errorPosition) {
					positionError(checkfieldX, checkfieldY);
				} else {
					if (fieldBuild.fieldTerrain[checkfieldY - 1][checkfieldX] == fieldBuild.fieldTerrain[checkfieldY][checkfieldX]
							&& fieldBuild.fieldTerrain[checkfieldY - 1][checkfieldX] != 1
							&& fieldBuild.fieldTerrain[checkfieldY][checkfieldX] != 1
							&& fieldBuild.fieldTerrain[checkfieldY][checkfieldX + 2] == 1
							&& fieldBuild.fieldTerrain[checkfieldY + 2][checkfieldX] != 0) {
						errorPosition = true;
					} else {

						if (checkfieldX <= fieldBuild.x_size
								&& checkfieldX >= 2
								&& checkfieldY <= fieldBuild.y_size
								&& checkfieldY >= 2) {

							if (fieldBuild.fieldTerrain[checkfieldY + 2][checkfieldX] == 99) {
								checkfield1 = 0;
							} else {
								checkfield1 = fieldBuild.fieldTerrain[checkfieldY + 2][checkfieldX];
							}
							if (fieldBuild.fieldTerrain[checkfieldY - 2][checkfieldX] == 99) {
								checkfield3 = 0;
							} else {
								checkfield3 = fieldBuild.fieldTerrain[checkfieldY - 2][checkfieldX];
							}
							if (fieldBuild.fieldTerrain[checkfieldY][checkfieldX] == 99) {
								checkfield2 = 0;
							} else {
								checkfield2 = fieldBuild.fieldTerrain[checkfieldY][checkfieldX];
							}

							// check position to walk monster
							if (fieldBuild.fieldTerrain[checkfieldY][checkfieldX + 2] == 1) {
								x += velocity;
								checkAnimation = 0;
								// monsterLv1.setRotation(0);
							} else if (checkfield1 > checkfield2) {
								y += velocity;
								checkAnimation = 1;
								// monsterLv1.setRotation(90);
							} else if (checkfield3 > checkfield2) {
								y -= velocity;
								checkAnimation = 2;

								// monsterLv1.setRotation(270);

							}
							/*
							 * else
							 * if(fieldBuild.fieldTerrain[checkfieldY][checkfieldX
							 * -2] == 1){ x--; }
							 */
						}
					}
				}
			}
			if (errorFinish) {
				Finish(checkfieldY);
			}
		}
	}

	protected void HpBar(Graphics g, float barX, float barY) {
		hpBar = new Rectangle(x + 14 + barX, y + 3 - barY,
				(float) (hpMonsterMax / CalWidthHpBar), 4);
		g.setColor(new Color(0, 0, 0));
		g.draw(hpBar);
		g.setColor(new Color(255, 255, 255));
		g.fill(hpBar);
		hpBar = new Rectangle(x + 14 + barX, y + 3 - barY,
				(float) (hpMonster / CalWidthHpBar), 4);
		g.setColor(new Color(200, 0, 0));
		g.fill(hpBar);
	}

	protected void HpBarForBoss(Graphics g) {
		hpBarBoss = new Rectangle(1370, 990, (float) (hpMonster
				/ CalWidthHpBarBoss * 5), 27);
		g.drawString("" + hpMonster, 1375, 970);
		g.drawString("BossHp", 1290, 995);
		g.setColor(new Color(0, 0, 0));
		g.draw(hpBarBoss);
		g.setColor(new Color(200, 0, 0));
		g.fill(hpBarBoss);
		g.drawString("Attack : " + attackCastle, 1670, 990);
	}

	protected void getskillWaterOn(int elementSkill) {
		if (elementSkill == 1) {
			velocity = velocityGetSkill;
		}
	}

	protected void TimerSkill(int delta) {
		if (velocity == velocityGetSkill) {
			skilTowerTimer += delta;
			getSkillFrost = true;
			if (skilTowerTimer >= skilTowerTimerMax) {
				velocity = velocityOld;
				skilTowerTimer = 0;
				getSkillFrost = false;
			}
		}
	}
}
