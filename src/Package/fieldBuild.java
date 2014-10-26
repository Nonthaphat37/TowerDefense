package Package;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class fieldBuild implements Entity {
	public static int x_size = 40;
	public static int y_size = 20;

	public static int[] x = new int[x_size];
	public static int[] y = new int[y_size];
	public static int sizeRect = 39;
	
	public static int fieldTerrain[][] = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,9,0,0,0,0,0,0}			//0
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,9,0,0,0,0,0,0}			//1
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,8,0,0,0,0,0,0}			//2
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,8,0,0,0,0,0,0}			//3
		   ,{1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,0,0,0,0,0,0}			//4
		   ,{1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,0,0,0,0,0,0}			//5
		   ,{0,0,0,0,0,0,2,2,0,0,0,0,0,0,5,5,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0,0,0}			//6
		   ,{0,0,0,0,0,0,2,2,0,0,0,0,0,0,5,5,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0,0,0}			//7
		   ,{0,0,0,0,0,0,3,3,0,0,0,0,0,0,4,4,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0}			//8
		   ,{0,0,0,0,0,0,3,3,0,0,0,0,0,0,4,4,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0}			//9
		   ,{0,0,0,0,0,0,4,4,0,0,0,0,0,0,3,3,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0}			//10
		   ,{0,0,0,0,0,0,4,4,0,0,0,0,0,0,3,3,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0}			//11
		   ,{0,0,0,0,0,0,5,5,0,0,0,0,0,0,2,2,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0}			//12
		   ,{0,0,0,0,0,0,5,5,0,0,0,0,0,0,2,2,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0}			//13
		   ,{0,0,0,0,0,0,6,6,1,1,1,1,1,1,1,1,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0}			//14
		   ,{0,0,0,0,0,0,6,6,1,1,1,1,1,1,1,1,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0}			//15
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0}			//16
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0}			//17
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}			//18
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}			//19
	};
	
	
	public static int checkCol_mouseXRectX;
	public static int checkCol_mouseXRectY;

	Rectangle[][] build;

	// Show Tower On MouseMoved
	private static Image towershop1;
	private static Image towershop2;
	private static Image towershop3;
	private static Image towershop4;
	private static Image goldbuilding;
	private int[] Rangetower = new int[] { 280, 320, 300, 350, 50 };
	Circle Rangetowershop;

	public fieldBuild() throws SlickException {
		build = new Rectangle[x_size][y_size];
		towershop1 = new Image("res/Towers/testTower.png");
		towershop2 = new Image("res/Towers/testTower2.png");
		towershop3 = new Image("res/Towers/testTower3.png");
		towershop4 = new Image("res/Towers/testTower4.png");
		goldbuilding = new Image("res/Towers/GoldBuilding.png");

		for (int i = 0; i < x_size; i++) {
			for (int j = 0; j < y_size; j++) {
				x[i] = i * sizeRect;
				y[j] = j * sizeRect;
			}
		}
	}

	public static int checkTowerTerrainX(float X) {
		for (int i = 0; i < x_size; i++) {
			if (X == x[i]) {
				return i;
			}
		}
		return -1;
	}

	public static int checkTowerTerrainY(float Y) {
		for (int i = 0; i < y_size; i++) {
			if (Y == y[i]) {
				return i;
			}
		}
		return -1;
	}

	public static int checkMouseMoveX(float mouseX) {
		for (int i = 0; i < x_size; i++) {
			if (mouseX > x[i] && mouseX < x[i] + sizeRect) {
				return i;
			}
		}
		return -1;
	}

	public static int checkMouseMoveY(float mouseY) {
		for (int j = 0; j < y_size; j++) {
			if (mouseY > y[j] && mouseY < y[j] + sizeRect) {
				return j;
			}
		}
		return -1;
	}

	private void checkPositionMouseAndRect(Graphics g) {
		Color outRect = new Color(0f, 0f, 0f, 0f);
		Color myAlphaColor = new Color(0f, 150f, 0f, 0.5f);
		Color notbuildColor = new Color(150f, 0f, 0f, 0.3f);
		for (int i = 0; i < x_size; i++) {
			for (int j = 0; j < y_size; j++) {
				build[i][j] = new Rectangle(i * sizeRect, j * sizeRect,
						sizeRect, sizeRect);
				x[i] = i * sizeRect;
				y[j] = j * sizeRect;
				g.setColor(outRect);
				g.draw(build[i][j]);
				if (((i == checkCol_mouseXRectX && j == checkCol_mouseXRectY)
						|| (i == checkCol_mouseXRectX + 1 && j == checkCol_mouseXRectY)
						|| (i == checkCol_mouseXRectX && j == checkCol_mouseXRectY - 1) || (i == checkCol_mouseXRectX + 1 && j == checkCol_mouseXRectY - 1))
						&& checkCol_mouseXRectY != 20
						&& checkCol_mouseXRectY != 0
						&& checkCol_mouseXRectX != -1
						&& checkCol_mouseXRectX != 39) {

					if (fieldTerrain[j][i] != 0) {
						g.setColor(notbuildColor);
					} else {
						g.setColor(myAlphaColor);
					}
				}
				g.fill(build[i][j]);
			}
		}
	}

	public void drawTower(Graphics g) {
		if (TowerDefenseGame.checkMouseClickCell
				&& checkCol_mouseXRectX != -1
				&& checkCol_mouseXRectY != -1
				&& TowerDefenseGame.Gold >= TowerDefenseGame.priceTower[TowerDefenseGame.checkClicktower]) {
			if (TowerDefenseGame.checkClicktower == 4
					&& TowerDefenseGame.checkGoldBuildingBuild) {
			} else {
				if (checkCol_mouseXRectY - 1 >= 0
						&& checkCol_mouseXRectX + 1 != 40) {
					// delete circle show range if build in red field
					if (fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY - 1][fieldBuild.checkCol_mouseXRectX] == 0
							&& fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY][fieldBuild.checkCol_mouseXRectX] == 0
							&& fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY - 1][fieldBuild.checkCol_mouseXRectX + 1] == 0
							&& fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY][fieldBuild.checkCol_mouseXRectX + 1] == 0) {

						Rangetowershop = new Circle((checkCol_mouseXRectX + 1)
								* sizeRect, checkCol_mouseXRectY * sizeRect,
								Rangetower[TowerDefenseGame.checkClicktower]);
						g.setColor(new Color(1f, 1f, 1f, 0f));
						g.draw(Rangetowershop);
						g.setColor(new Color(1f, 1f, 1f, 0.2f));
						g.fill(Rangetowershop);
					}
					if (TowerDefenseGame.checkClicktower == 0) {
						towershop1.draw(checkCol_mouseXRectX * sizeRect,
								(checkCol_mouseXRectY - 1) * sizeRect);
					} else if (TowerDefenseGame.checkClicktower == 1) {
						towershop2.draw(checkCol_mouseXRectX * sizeRect,
								(checkCol_mouseXRectY - 1) * sizeRect);
					} else if (TowerDefenseGame.checkClicktower == 2) {
						towershop3.draw(checkCol_mouseXRectX * sizeRect,
								(checkCol_mouseXRectY - 1) * sizeRect);
					} else if (TowerDefenseGame.checkClicktower == 3) {
						towershop4.draw(checkCol_mouseXRectX * sizeRect,
								(checkCol_mouseXRectY - 1) * sizeRect);
					} else if (TowerDefenseGame.checkClicktower == 4) {
						goldbuilding.draw(checkCol_mouseXRectX * sizeRect,
								(checkCol_mouseXRectY - 1) * sizeRect);
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		checkPositionMouseAndRect(g);
		drawTower(g);
	}

	@Override
	public void update(GameContainer container, int delta) {

	}

}
