package Package;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class Store implements Entity {

	private static int cellSpace = 50;
	private static int shopXFirst = 300;
	private static int shopWidth = 5;
	private static int buttonSize = 78;

	private static int[] x = new int[] { shopXFirst,
			shopXFirst + buttonSize + cellSpace,
			shopXFirst + 2 * buttonSize + 2 * cellSpace,
			shopXFirst + 3 * buttonSize + 3 * cellSpace,
			shopXFirst + 4 * buttonSize + 4 * cellSpace };
	private static int y = 880;

	private static Image[] cell = new Image[shopWidth];
	private static Image[] cellmouse = new Image[shopWidth];

	private static boolean checkmouse[] = new boolean[] { false, false, false,
			false, false }; // check mouse on cell

	// Tower
	private static Image towershop1;
	private static Image towershop2;
	private static Image towershop3;
	private static Image towershop4;
	private static Image goldbuilding;

	private Rectangle goldCooldown;

	public Store() throws SlickException {

		towershop1 = new Image("res/Towers/testTower.png");
		towershop2 = new Image("res/Towers/testTower2.png");
		towershop3 = new Image("res/Towers/testTower3.png");
		towershop4 = new Image("res/Towers/testTower4.png");
		goldbuilding = new Image("res/Towers/GoldBuilding.png");
		for (int i = 0; i < cell.length; i++) {
			cell[i] = new Image("res/cell.png");
			cellmouse[i] = new Image("res/cellmouse.png");
		}
	}

	public static void checkMouseInCell(int mouseX, int mouseY) {
		for (int i = 0; i < Store.shopWidth; i++) {
			if (mouseX > x[i] && mouseX < x[i] + Store.buttonSize && mouseY > y
					&& mouseY < y + buttonSize) {
				checkmouse[i] = true;
			} else {
				checkmouse[i] = false;
			}
		}
	}

	/*
	 * //////////////////////////////////////////////////////////////////////////
	 * ///// //tower
	 */
	public static boolean checkMouseTower(int mouseX, int mouseY, int i) {
		if (i != -1) {
			if (mouseX > x[i] && mouseX < x[i] + Store.buttonSize && mouseY > y
					&& mouseY < y + buttonSize) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public static int checkClickTower(int mouseX, int mouseY) {
		for (int i = 0; i < 5; i++) {
			if (mouseX > x[i] && mouseX < x[i] + Store.buttonSize && mouseY > y
					&& mouseY < y + buttonSize) {
				return i;
			}
		}
		return -1;
	}

	public void shopTower(Graphics g) {
		towershop1.draw(shopXFirst, y); // render tower1
		towershop2.draw(shopXFirst + buttonSize + cellSpace, y);
		towershop3.draw(shopXFirst + 2 * (buttonSize + cellSpace), y);
		towershop4.draw(shopXFirst + 3 * (buttonSize + cellSpace), y);
		goldbuilding.draw(shopXFirst + 4 * (buttonSize + cellSpace), y);

		goldCooldown = new Rectangle(shopXFirst + 4 * (buttonSize + cellSpace),
				y, 78, 78);

		if (TowerDefenseGame.checkGoldBuildingBuild) {
			g.setColor(new Color(255f, 255f, 255f, (float) 0.2
					+ (float) TowerDefenseGame.CooldownCountGoldBuilding
					/ 25000));
			g.draw(goldCooldown);
			g.fill(goldCooldown);
		}
	}

	// //////////////////////////////////////////////////////////////////

	@Override
	public void render(Graphics g) {

		for (int i = 0; i < cell.length; i++) {
			if (!checkmouse[i]) {
				cell[i].draw(x[i], y);
			} else {
				cellmouse[i].draw(x[i], y);
			}
		}
		shopTower(g);

	}

	@Override
	public void update(GameContainer container, int delta) {

	}
}
