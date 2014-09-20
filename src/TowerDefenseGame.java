import java.util.ArrayList;
import java.util.LinkedList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


import org.newdawn.slick.Input; //import key if finish delete
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class TowerDefenseGame extends BasicGame{
	
	public static int Screen_Width = 1920;
	public static int Screen_Height = 1080;
	public static int Stage_Width = 1560;
	public static int Stage_Height = 780;
	public static int Stage_x = 0;
	public static int Stage_y = 0;
	public static int Store_Height = 300;
	
	
	
	
	private float time = 0;
	public float timer = 0;
	
	public int currentWave = 2;
	public static int wave = 0;
	private boolean checkWave = true;  // check to release monster in next wave
	
	 private LinkedList<Entity> entities;
	
	 private TerrainDarkStage darkterrain;
	 private Store cell;
	 private fieldBuild filedbuild;
	 private Door doorHeart;
	 
	 
	 //monster
	 public static float monster_startX = -78;
	 public static float monster_startY = 156;
	 private boolean monster_checkTotal = false;
	private static int number_monster = 0;
	private static int max_monster =  10;
	private static float timerdelay_monster = (float) 0.5;
	private static float timer_monster = 0;
	 private static ArrayList<Monster> monsterAll = new ArrayList<Monster>();
	 

	
	//Background
	private static float changeResolutionBg = 1;
	private static int checkResolutionBg = 1;
	
	
	//Hp game use in Door
	public static int HpGame = 100;
	private int Heart_x = 1300;
	private int Heart_y = 920;
	private Rectangle hpLoaded;
	
	
	//Image Screen
	private Image darkstage;
	private Image Shopbackground;
	private Image Upgratebackground;

	
	//Store Tower
	private static ArrayList<Tower> towerdark = new ArrayList<Tower>();
	public static boolean checkMouseClickCell = false;
	private static ArrayList<Integer> rangeTowerdark = new ArrayList<Integer>();
	
	
	//Mouse
	private static float mouseError = 21;
	
	
	//Bullet
	private static ArrayList<ArrayList<Bullet>> bullet = new ArrayList<ArrayList<Bullet>>();	
	private static int monsterRememberBullet = 0;
	
	//GameStart and GameOver
	private boolean isGameStarted = false;
	private boolean isGameOver = false;
	
	
	//Upgrate
	private boolean checkBuild = true;
	private boolean checkMouseClick = false;
	private int checktowerClick = -1;
	Circle Rangetowershop1;
	private Image plus1;
	private Image plus2;
	private Image plus3;
	private Upgrate upgrate;
	
	

	public TowerDefenseGame(String title) throws SlickException {
		super(title);
		entities = new LinkedList<Entity>();
	}
	
	
	
	//SetBackGround
	public void setBackgroundinit(GameContainer container) throws SlickException{
		Color background = new Color(0, 0, 0);
		container.getGraphics().setBackground(background);
		darkstage = new Image("res/DarkStage.png");
		Shopbackground = new Image("res/Shop.png");
		Upgratebackground = new Image("res/Upgrate.png");
		plus1 = new Image("res/Plus.png");
		plus2 = new Image("res/Plus.png");
		plus3 = new Image("res/Plus.png");

	}
	
	public void setBackgroundRender(Graphics g){
		g.setColor(new Color(0, 0, 0));
		darkstage.draw(Stage_x, Stage_y);
		Shopbackground.draw(30, Stage_Height+10);
		Upgratebackground.draw(Stage_Width+3, 0);
	}

	public void ChangeResolutionBg(){
		if(checkResolutionBg == 1){
			changeResolutionBg -= 0.05;
			if(changeResolutionBg <= 0){
				checkResolutionBg = 0;
			}
		}
		else if(checkResolutionBg == 0){
			changeResolutionBg += 0.05;
			if(changeResolutionBg >= 1){
				checkResolutionBg = 1;
			}
		}
	}
	
	public void Timer(int delta){
		//time in Game
				time += delta;
				if(time > 500){
					time = 0;
					timer += 0.5;
					ChangeResolutionBg();
					if(currentWave != 0 && Math.ceil(timer) - timer != 0.5){
						currentWave--;
					}
					if(checkWave && currentWave == 0){
						wave++;
						currentWave = 5;
					    checkWave = false;
					}
				}
	}
	
	//Monster
	public void releaseMonster() throws SlickException{
		if(wave == 1 && number_monster < max_monster){
			if(!monster_checkTotal){
				monsterAll.add(new MonsterLv1(monster_startX, monster_startY));
				monster_checkTotal = true;			// check monster release
				timer_monster = timer+timerdelay_monster;
				number_monster++;
			}
			else if(timer_monster == timer){
				monster_checkTotal = false;			// check monster release
			}
		}
	}
	
	
	//release bullet
	public static void setupBullet(){
		for ( int i = 0; i < towerdark.size(); i++ ){
			bullet.add(new ArrayList<Bullet>());
		}
	}
	
	public static void releaseBullet(int numTower){
		//System.out.println(numTower);
		bullet.get(numTower).add(new BulletDark(towerdark.get(numTower).x+39,towerdark.get(numTower).y+39));
		 if(bullet.get(numTower).get(bullet.get(numTower).size()-1).getNumMon() == -1){
			 monsterRememberBullet = bullet.get(numTower).get(bullet.get(numTower).size()-1).setNumMon(towerdark.get(numTower).getNumMon());
			 bullet.get(numTower).get(bullet.get(numTower).size()-1).setMonster(monsterAll.get(monsterRememberBullet));
		 }
		
	}

	
	//Draw textHp
	public void HpLoaded(Graphics g){
			g.setColor(new Color(255, 0, 0));
			g.drawString("" + HpGame + "/100",Heart_x +75 , Heart_y-7);
			hpLoaded = new Rectangle(Heart_x +70 , Heart_y +13,4*HpGame,25);
			g.setColor(new Color(0,0,0));
			g.draw(hpLoaded);
			g.setColor(new Color(100f,0f,0f,0.3f+HpGame/100f));
			g.fill(hpLoaded);
	}
	
	public void death(){
		if(HpGame == 0){
			isGameOver = true;
			isGameStarted = false;
		}
		else if(HpGame < 0){
			HpGame = 0;
		}
	}
	
	//buy tower in shop
	public void mouseClickBuyItemShop(int button, int x, int y){
		if(Store.checkMouseTower(x,y) && !checkMouseClickCell && button == 0){		//click tower in shop
			checkMouseClickCell = true;
			checkBuild = true;
		}
		else if(checkMouseClickCell && button == 0 &&
				fieldBuild.checkCol_mouseXRectY-1 != -1 &&
				fieldBuild.checkCol_mouseXRectX != -1 &&
				fieldBuild.checkCol_mouseXRectY != -1 &&
				fieldBuild.checkCol_mouseXRectX+1 != -1){
					if(fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY-1][fieldBuild.checkCol_mouseXRectX] == 0 &&
					   fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY][fieldBuild.checkCol_mouseXRectX] == 0 &&
			           fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY-1][fieldBuild.checkCol_mouseXRectX+1] == 0 &&
					   fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY][fieldBuild.checkCol_mouseXRectX+1] == 0){
						
						//add tower///////////////////////////////////////////////////////////
						
						try {
							towerdark.add(new TowerDark(fieldBuild.checkCol_mouseXRectX * fieldBuild.sizeRect,
									(fieldBuild.checkCol_mouseXRectY-1)* fieldBuild.sizeRect));
							checkBuild = false;   //click For build success
									 for(int i=0;i<towerdark.size();i++){
										 
										 rangeTowerdark.add(towerdark.get(towerdark.size()-1).rangeTower);
										
										 if(i==towerdark.size()-1){
											 towerdark.get(i).getNumTower(towerdark.size()-1);
										 }
										 
										 
										 //// range tower
										 
										
										 
										 //setup bullet
									 }
									
							}	 catch (SlickException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						//give red field
						fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY-1][fieldBuild.checkCol_mouseXRectX] = 99;
						fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY][fieldBuild.checkCol_mouseXRectX] = 99;
						fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY-1][fieldBuild.checkCol_mouseXRectX+1] = 99;
						fieldBuild.fieldTerrain[fieldBuild.checkCol_mouseXRectY][fieldBuild.checkCol_mouseXRectX+1] = 99;
						
						//remove terrain and click
						fieldBuild.checkCol_mouseXRectX = -1;
						fieldBuild.checkCol_mouseXRectY = -1;
						checkMouseClickCell = false;
					}
		}
		else if(checkMouseClickCell && button == 1){
			//remove buy
				checkMouseClickCell = false;
				fieldBuild.checkCol_mouseXRectX = -1;
				fieldBuild.checkCol_mouseXRectY = -1;
		}
	}
	
	
	
	public void mouseClickForUpgrateTower(int button, int mouseX, int mouseY){
		if(button == 0 && !checkMouseClick && !checkBuild){
			for(int i=0;i < towerdark.size();i++){
				if(mouseX >= towerdark.get(i).x && mouseX <= towerdark.get(i).x+78 &&
				   mouseY >= towerdark.get(i).y && mouseY <= towerdark.get(i).y+78	){
					Rangetowershop1 = new Circle(towerdark.get(i).x+39,towerdark.get(i).y+39,towerdark.get(i).rangeTower);
					checkMouseClick = true;
					checktowerClick = i;
				}
			}
		}
		else if(button == 0 && checkMouseClick && checktowerClick != -1)
			if(mouseX >= towerdark.get(checktowerClick).x && mouseX <= towerdark.get(checktowerClick).x+78 &&
			   mouseY >= towerdark.get(checktowerClick).y && mouseY <= towerdark.get(checktowerClick).y+78	){
				checkMouseClick = false;
				checktowerClick = -1;
		}
		
		if(checkMouseClick && mouseX > 1730 && mouseX<1730+40 && mouseY > 85 && mouseY < 85+40){
			upgrate = new Upgrate(towerdark.get(checktowerClick));
			towerdark.get(checktowerClick).attackTower = upgrate.upgrateAttack();
		}
		else if(checkMouseClick && mouseX > 1730 && mouseX<1730+40 && mouseY > 135 && mouseY < 135+40){
			upgrate = new Upgrate(towerdark.get(checktowerClick));
			towerdark.get(checktowerClick).speedTower = upgrate.upgrateSpeed();
		}
		else if(checkMouseClick && mouseX > 1730 && mouseX<1730+40 && mouseY > 185 && mouseY < 185+40){
			upgrate = new Upgrate(towerdark.get(checktowerClick));
			towerdark.get(checktowerClick).rangeTower = upgrate.upgrateRange();
			Rangetowershop1 = new Circle(towerdark.get(checktowerClick).x+39,towerdark.get(checktowerClick).y+39,towerdark.get(checktowerClick).rangeTower);
		}
	
	}
	
	
	
	//Collide Bullet and Monster to Destroy bullet and monster
	public void setCollideAndDestroy(int delta){
		for(int i=0;i<towerdark.size();i++){
			for(int j=0;j<bullet.get(i).size();j++){
			 //Collide
				if(monsterRememberBullet < monsterAll.size()){
					 if(bullet.get(i).get(j).CollideMonster()){
						 bullet.get(i).remove(j);
						 monsterAll.get(monsterRememberBullet).MonsterAttacked(towerdark.get(i).getAttack(),towerdark.get(i).getElement());
						 if(monsterAll.get(monsterRememberBullet).getDeath()){
							 monsterAll.remove(monsterRememberBullet);
							 towerdark.get(i).rememberNumMon = -1;
							 towerdark.get(i).checkremember_Mon = false;
							 for(int k=0; k < towerdark.size();k++){
								 for(int l=0 ; l < bullet.get(k).size();l++){
									 bullet.get(k).remove(l);
								 }
							 }
						 }
					 }
					}
				}
		
		}
		death();
		for (int i =0; i < monsterAll.size(); i++) {
			for(int j=0;j<towerdark.size();j++){
				towerdark.get(j).rangeCheck(monsterAll.get(i),i,delta);
	
			}
		}
	}
	
	public void drawUpgrate(Graphics g){
		g.setColor(new Color(1f, 1f, 1f, 0f));
		g.draw(Rangetowershop1);
		g.setColor(new Color(1f, 1f, 1f, 0.2f));
		g.fill(Rangetowershop1);
		
		g.setColor(new Color(0, 0, 0));
		g.drawString("Attack   " + towerdark.get(checktowerClick).attackTower, 1600, 100);
		g.drawString("Speed   " + towerdark.get(checktowerClick).speedTower, 1600, 150);
		g.drawString("Range   " + towerdark.get(checktowerClick).rangeTower, 1600, 200);
		g.drawString("Element   " + towerdark.get(checktowerClick).element, 1600, 250);
		
		plus1.draw(1730,85);
		plus2.draw(1730,135);
		plus3.draw(1730,185);
	}
	
	
	@Override
	  public void keyPressed(int key, char c) {
		if(isGameStarted && !isGameOver){
		 if (key == Input.KEY_T) {
		 }
		}
	  }
	
	//Update Render and init
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		setBackgroundRender(g);
		
		//draw textWave
		g.drawString("Wave   " + wave, 1300, 850);
		g.drawString("Next Wave  " + currentWave, 1300, 870);
		
		//draw Hp and TextHp
		HpLoaded(g);
		
		for(Entity entity : entities) {
			entity.render(g);
		}
		for(Monster monster : monsterAll){
			monster.render(g);
		}	
		for(Tower tower : towerdark){
			tower.render(g);
		}	
		for(int i=0;i<towerdark.size();i++){
			setupBullet();
			for(Bullet bullet: bullet.get(i)){
				bullet.render(g);
			}
		}
		if(checkMouseClick){
			drawUpgrate(g);	
		}
	}

	
	@Override
	public void init(GameContainer container) throws SlickException {
		isGameStarted = true;
		
		setBackgroundinit(container);
		darkterrain = new TerrainDarkStage(Stage_x, Stage_y);
		cell = new Store();
		filedbuild = new fieldBuild();
		 doorHeart = new Door((int)32*fieldBuild.sizeRect, 0, Heart_x, Heart_y);
		 entities.add(darkterrain);
		 entities.add(cell);
		 entities.add(filedbuild);
		 entities.add(doorHeart);
		 
	}

	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(isGameStarted && !isGameOver){
			releaseMonster();
			Timer(delta);
			if(wave >= 1){
				darkstage.setColor((int) 1f, 1f, 1f, changeResolutionBg);
			}
			for (Entity entity : entities) {
			      entity.update(container, delta);
			    }
			for(Monster monster : monsterAll){
				monster.update(container, delta);
			}
			for(Tower tower : towerdark){
				tower.update(container, delta);
			}
			for(int i=0;i<towerdark.size();i++){
				setupBullet();
				for(Bullet bullet: bullet.get(i)){
					bullet.update(container, delta);
				}
			}
			setCollideAndDestroy(delta);
			
			//destroy Bullet after monster death all
			if(monsterAll.size() == 0){
				for(int i=0;i<towerdark.size();i++){
					for(int j=0;j<bullet.get(i).size();j++){
						bullet.get(i).remove(j);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try {
		      TowerDefenseGame game = new TowerDefenseGame("TowerDefenseGame");
		      AppGameContainer appgc = new AppGameContainer(game);
		      appgc.setDisplayMode(Screen_Width, Screen_Height ,false);
		      appgc.start();
		    } catch (SlickException e) {
		      e.printStackTrace();
		    }
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		if(isGameStarted && !isGameOver){
			//mouse drag on cell
			Store.checkMouseInCell(newx, newy);
		
			//mouse drag on buildField
			if(checkMouseClickCell){
				fieldBuild.checkCol_mouseXRectX = fieldBuild.checkMouseMoveX(newx-mouseError);
				fieldBuild.checkCol_mouseXRectY = fieldBuild.checkMouseMoveY(newy+mouseError/3);
			}
			else{
				fieldBuild.checkCol_mouseXRectX = -1;
				fieldBuild.checkCol_mouseXRectY = -1;
			}
			
			//set Alpha plus
			if(checkMouseClick){
				if(newx >1730 && newx<1730+40 && newy > 85 && newy < 85+40){
					plus1.setAlpha(0.8f);
					plus2.setAlpha(1f);
					plus3.setAlpha(1f);
				}else if(newx >1730 && newx<1730+40 && newy > 135 && newy < 135+40){
					plus1.setAlpha(1f);
					plus2.setAlpha(0.8f);
					plus3.setAlpha(1f);
				}else if(newx >1730 && newx<1730+40 && newy > 185 && newy < 185+40){
					plus1.setAlpha(1f);
					plus2.setAlpha(1f);
					plus3.setAlpha(0.8f);
				}else{
					plus1.setAlpha(1f);
					plus2.setAlpha(1f);
					plus3.setAlpha(1f);
				}
			}
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y){
		if(isGameStarted && !isGameOver){
			mouseClickForUpgrateTower(button, x, y);
			mouseClickBuyItemShop(button, x, y);
		}
	}
}
