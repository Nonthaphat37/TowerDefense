import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

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
	 private static int[] max_monsterwave =  new int[]{10,1};
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
	private static ArrayList<Tower> tower = new ArrayList<Tower>();
	public static boolean checkMouseClickCell = false;
	public static int checkClicktower = -1;
	
	
	//Mouse
	private static float mouseError = 21;
	
	
	//Bullet
	private static ArrayList<ArrayList<Bullet>> bullet = new ArrayList<ArrayList<Bullet>>();	
	private static int monsterRememberBullet = 0;
	
	//GameStart and GameOver
	private boolean isGameStarted = false;
	private boolean isGameOver = false;
	
	
	//Upgrate
	private boolean checkBuild = false;
	private boolean checkMouseClicktower = false;
	private int checktowerClick = -1;
	private boolean checktowerClicktoCloseUpgrage = false;
	Circle Rangetowershop1;
	private Image plus1;
	private Image plus2;
	private Image plus3;
	private Upgrate upgrate;
	
	//GoldBuild and Goldsystem
	private GoldBuilding goldbuilding;
	
	//Rune System
	private static ArrayList<Rune> runes = new ArrayList<Rune>();
	public static int typeRunes = 0;
	private float TimingForRunes = 4;
	private boolean checkTimeRunes = false;
	private Rectangle timingRectangle;
	private float timingShowRenderRectangle = 0;
	

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
	
	public void Timer(int delta) throws SlickException{
		//time in Game
				if(typeRunes != 1){
					time += delta;	
				}
				timingShowRenderRectangle -= delta;
				if(time > 500){
					time = 0;
					timer += 0.5;
					ChangeResolutionBg();
					if(currentWave != 0 && Math.ceil(timer) - timer != 0.5){
						checkWave = true;
						currentWave--;
						if(currentWave == 7){
							releaseRune();
						}
					}
					if(checkWave && currentWave == 0){
						wave++;
						number_monster = 0;          //release monster wave2
						monster_checkTotal = false;  //release monster wave2
						currentWave = 10;
					    checkWave = false;
					}
				}

				timingRunes();
	}
	
	//Rune
	public void releaseRune() throws SlickException{
		//if(wave == 1){
			Random random = new Random();
			float releaseX = 800+random.nextInt(400);
			runes.add(new RuneTiming(releaseX,0));
		//}
	}
	
	public void timingRunes(){
		if(typeRunes > 0){
			if(!checkTimeRunes){
				checkTimeRunes = true;
			}
			else if(timingShowRenderRectangle <= 0){
				checkTimeRunes = false;
				typeRunes = 0;
			}
		}
	}
	
	//Monster
	public void releaseMonster() throws SlickException{
		if(wave == 1 && number_monster < max_monsterwave[0]){
			if(!monster_checkTotal){
				monsterAll.add(new MonsterLv1Boss(monster_startX, monster_startY));
				monster_checkTotal = true;			// check monster release
				timer_monster = timer+timerdelay_monster;
				number_monster++;
			}
			else if(timer_monster == timer){
				monster_checkTotal = false;			// check monster release
			}
		}
		else if(wave == 2 && number_monster < max_monsterwave[1]){
			if(!monster_checkTotal){
				monsterAll.add(new MonsterLv1Boss(monster_startX, monster_startY));
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
		for ( int i = 0; i < tower.size(); i++ ){
			bullet.add(new ArrayList<Bullet>());
		}
	}
	
	public static void releaseBullet(int numTower){
		//System.out.println(numTower);
		if(tower.get(numTower).getElement() == 0){
			bullet.get(numTower).add(new BulletDark(tower.get(numTower).x+39,tower.get(numTower).y+39));
		}
		else if(tower.get(numTower).getElement() == 1){
			bullet.get(numTower).add(new BulletWater(tower.get(numTower).x+39,tower.get(numTower).y+39));
		}
		else if(tower.get(numTower).getElement() == 2){
			bullet.get(numTower).add(new BulletFire(tower.get(numTower).x+39,tower.get(numTower).y+39));
		}
		else if(tower.get(numTower).getElement() == 3){
			bullet.get(numTower).add(new BulletEarth(tower.get(numTower).x+39,tower.get(numTower).y+39));
		}
		 if(bullet.get(numTower).get(bullet.get(numTower).size()-1).getNumMon() == -1){
			 monsterRememberBullet = bullet.get(numTower).get(bullet.get(numTower).size()-1).setNumMon(tower.get(numTower).getNumMon());
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
		if(!checkBuild){
			checkClicktower = Store.checkClickTower(x, y);
		}
		if(Store.checkMouseTower(x,y,checkClicktower) && !checkMouseClickCell && button == 0){		//click tower in shop
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
							if(checkClicktower == 0){
								tower.add(new TowerDark(fieldBuild.checkCol_mouseXRectX * fieldBuild.sizeRect,
									(fieldBuild.checkCol_mouseXRectY-1)* fieldBuild.sizeRect));
							}
							else if(checkClicktower == 1){
								tower.add(new TowerWater(fieldBuild.checkCol_mouseXRectX * fieldBuild.sizeRect,
										(fieldBuild.checkCol_mouseXRectY-1)* fieldBuild.sizeRect));
								}
							else if(checkClicktower == 2){
								tower.add(new TowerFire(fieldBuild.checkCol_mouseXRectX * fieldBuild.sizeRect,
										(fieldBuild.checkCol_mouseXRectY-1)* fieldBuild.sizeRect));
								}
							else if(checkClicktower == 3){
								tower.add(new TowerEarth(fieldBuild.checkCol_mouseXRectX * fieldBuild.sizeRect,
										(fieldBuild.checkCol_mouseXRectY-1)* fieldBuild.sizeRect));
								}
							else if(checkClicktower == 4){
								entities.add(new GoldBuilding(fieldBuild.checkCol_mouseXRectX * fieldBuild.sizeRect,
										(fieldBuild.checkCol_mouseXRectY-1)* fieldBuild.sizeRect));
								}
							checkBuild = false;   //click For build success
									 for(int i=0;i<tower.size();i++){
										 
					
										
										 if(i==tower.size()-1){
											 tower.get(i).getNumTower(tower.size()-1);
										 }
										 
										 
										 //// range tower
										 
										
										 
										 //setup bullet
									 }
									 checkClicktower = -1;
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
				checkBuild = false;
				checkMouseClickCell = false;
				fieldBuild.checkCol_mouseXRectX = -1;
				fieldBuild.checkCol_mouseXRectY = -1;
		}
	}
	
	
	
	public void mouseClickForUpgrateTower(int button, int mouseX, int mouseY){
		if(button == 0 && !checkBuild && !checktowerClicktoCloseUpgrage){
			for(int i=0;i < tower.size();i++){
				if(mouseX >= tower.get(i).x && mouseX <= tower.get(i).x+78 &&
				   mouseY >= tower.get(i).y && mouseY <= tower.get(i).y+78	){
					Rangetowershop1 = new Circle(tower.get(i).x+39,tower.get(i).y+39,tower.get(i).rangeTower);
					checkMouseClicktower = true;
					if(checktowerClick == i){
						checktowerClicktoCloseUpgrage = true;
					}
					checktowerClick = i;
					
				}
			}
		}
		if(button == 0 && checktowerClick != -1 && checktowerClicktoCloseUpgrage){
			if(mouseX >= tower.get(checktowerClick).x && mouseX <= tower.get(checktowerClick).x+78 &&
			   mouseY >= tower.get(checktowerClick).y && mouseY <= tower.get(checktowerClick).y+78	){
				checkMouseClicktower = false;
				checktowerClick = -1;
				checktowerClicktoCloseUpgrage = false;
			}
		}
		if(checkMouseClicktower && mouseX > 1730 && mouseX<1730+40 && mouseY > 85 && mouseY < 85+40){
			upgrate = new Upgrate(tower.get(checktowerClick));
			tower.get(checktowerClick).attackTower = upgrate.upgrateAttack();
		}
		else if(checkMouseClicktower && mouseX > 1730 && mouseX<1730+40 && mouseY > 135 && mouseY < 135+40){
			upgrate = new Upgrate(tower.get(checktowerClick));
			tower.get(checktowerClick).speedTower = upgrate.upgrateSpeed();
		}
		else if(checkMouseClicktower && mouseX > 1730 && mouseX<1730+40 && mouseY > 185 && mouseY < 185+40){
			upgrate = new Upgrate(tower.get(checktowerClick));
			tower.get(checktowerClick).rangeTower = upgrate.upgrateRange();
			Rangetowershop1 = new Circle(tower.get(checktowerClick).x+39,tower.get(checktowerClick).y+39,tower.get(checktowerClick).rangeTower);
		}
	
	}
	
	
	
	//Collide Bullet and Monster to Destroy bullet and monster
	public void setCollideAndDestroy(int delta){
		for(int i=0;i<tower.size();i++){
			for(int j=0;j<bullet.get(i).size();j++){
			 //Collide
				if(monsterRememberBullet < monsterAll.size()){
					 if(bullet.get(i).get(j).CollideMonster()){
						 bullet.get(i).remove(j);
						 monsterAll.get(monsterRememberBullet).MonsterAttacked(tower.get(i).getAttack(),tower.get(i).getElement());
						 if(monsterAll.get(monsterRememberBullet).getDeath()){
							 monsterAll.remove(monsterRememberBullet);
							 tower.get(i).rememberNumMon = -1;
							 tower.get(i).checkremember_Mon = false;
							 for(int k=0; k < tower.size();k++){
								 bullet.get(k).removeAll(bullet);
							 }
						 }
					 }
					}
				}
		
		}
		death();
		for (int i =0; i < monsterAll.size(); i++) {
			for(int j=0;j<tower.size();j++){
				tower.get(j).rangeCheck(monsterAll.get(i),i,delta);
	
			}
		}
	}
	
	public void drawUpgrate(Graphics g){
		g.setColor(new Color(1f, 1f, 1f, 0f));
		g.draw(Rangetowershop1);
		g.setColor(new Color(1f, 1f, 1f, 0.2f));
		g.fill(Rangetowershop1);
		
		g.setColor(new Color(0, 0, 0));
		g.drawString("Attack   " + tower.get(checktowerClick).attackTower, 1600, 100);
		g.drawString("Speed   " + tower.get(checktowerClick).speedTower, 1600, 150);
		g.drawString("Range   " + tower.get(checktowerClick).rangeTower, 1600, 200);
		g.drawString("Element   " + tower.get(checktowerClick).element, 1600, 250);
		
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
		for(Tower tower : tower){
			tower.render(g);
		}	
		for(Rune rune : runes){
			rune.render(g);
		}
		for(int i=0;i<tower.size();i++){
			setupBullet();
			for(Bullet bullet: bullet.get(i)){
				bullet.render(g);
			}
		}
		if(typeRunes > 0){
			RenderTimngRune(g);
		}
		else{
			timingShowRenderRectangle = TimingForRunes*1000;
		}
		if(checkMouseClicktower){
			drawUpgrate(g);	
		}
	}

	
	public void RenderTimngRune(Graphics g){
		if(timingShowRenderRectangle > 0){
			timingRectangle = new Rectangle(0,0,Stage_Width,Stage_Height);
			g.setColor(new Color(255f,255f,255f,0f));
			g.draw(timingRectangle);
			g.setColor(new Color(255f,255f,255f,(timingShowRenderRectangle/(TimingForRunes*2000))));
			g.fill(timingRectangle);
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
			for(Tower tower : tower){
				tower.update(container, delta);
			}
			for(Rune rune : runes){
				rune.update(container, delta);
			}
			for(int i=0;i<tower.size();i++){
				setupBullet();
				for(Bullet bullet: bullet.get(i)){
					bullet.update(container, delta);
				}
			}
			setCollideAndDestroy(delta);
			
			//destroy Bullet after monster death all
			if(monsterAll.size() == 0){
				for(int i=0;i<tower.size();i++){
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
		      appgc.setDisplayMode(Screen_Width, Screen_Height, true);
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
			if(checkMouseClicktower){
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
			for(int i = 0;i<runes.size();i++){
				if(button == 0 && runes.get(i).getClick(x, y)){
					typeRunes = runes.get(i).getTypeRunes();
					runes.remove(i);
				}
			}
			
			mouseClickForUpgrateTower(button, x, y);
			mouseClickBuyItemShop(button, x, y);
		}
	}
}
