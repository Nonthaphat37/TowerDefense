import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class MonsterLv1 extends Monster {
	
	//monster
	private Image monsterLv1;
	private float x;
	private float y;
	private float velocity = (float) 1;
	private int checkfieldX = 0;
	private int checkfieldY = 0;
	
	private boolean errorPosition = false;
	private boolean errorStarted = true;
	private boolean errorFinish = false;

	public MonsterLv1(float x, float y) throws SlickException {
		super(x, y);
		monsterLv1 = new Image("res/Monster_Lv1.png");
		this.x = x;
		this.y = y;
	}
	
	
	public void positionError(int block_x,int block_y){
		if(checkfieldY == 5){
			if(checkfieldX == 20){
				errorPosition = false;
			}
			else{
				x+=velocity;
				monsterLv1.setRotation(0);
			}
		}
		else{
			y-=velocity;
			monsterLv1.setRotation(270);
		}
	}
	
	public void Start(int check_startX){
		if(checkfieldX < 2){
			x+=velocity;
			monsterLv1.setRotation(0);
		}
		else{
			errorStarted = false;
		}
	}
	
	public void Finish(int check_finshY){
		if(checkfieldY > -2){
			monsterLv1.setRotation(270);
			y-=velocity;
		}
		else{
			x = TowerDefenseGame.monster_startX;								/// monster in castle
			y = TowerDefenseGame.monster_startY;
			errorStarted = true;
			errorFinish = false;
		}
		
	}
	
	@Override
	public void render(Graphics g) {
			monsterLv1.draw(x,y);	
	}
	
	
	@Override
	public void updateposition(){
		
		checkfieldX = (int)(x/fieldBuild.sizeRect);
		checkfieldY = (int)(y/fieldBuild.sizeRect);
		//System.out.println("x = " + x + " y = " + y + " [x] = " + checkfieldX + " [y] = " + checkfieldY);
		if(errorStarted){
			Start(checkfieldX);
		}
		else if(checkfieldX == 32 && checkfieldY == 1){
			errorFinish = true;
		}
		else if(!errorFinish){
			if(errorPosition){
				positionError(checkfieldX,checkfieldY);
			}
			else{
				if(fieldBuild.fieldTerrain[checkfieldY-1][checkfieldX] == fieldBuild.fieldTerrain[checkfieldY][checkfieldX]
						&& fieldBuild.fieldTerrain[checkfieldY-1][checkfieldX] != 1
						&& fieldBuild.fieldTerrain[checkfieldY][checkfieldX] != 1
						&& fieldBuild.fieldTerrain[checkfieldY][checkfieldX+2] == 1
						&& fieldBuild.fieldTerrain[checkfieldY+2][checkfieldX] != 0
						&& fieldBuild.fieldTerrain[checkfieldY+2][checkfieldX] != 0
						){
					errorPosition = true;
				}
				else{
				
					if(checkfieldX <= fieldBuild.x_size && checkfieldX >= 2 
					&& checkfieldY <= fieldBuild.y_size && checkfieldY >= 2 ){
					
						if(fieldBuild.fieldTerrain[checkfieldY][checkfieldX+2] == 1){
							x+=velocity;
							monsterLv1.setRotation(0);
						}
						else if(fieldBuild.fieldTerrain[checkfieldY+2][checkfieldX] > fieldBuild.fieldTerrain[checkfieldY][checkfieldX]){
							y+=velocity;
							monsterLv1.setRotation(90);
						}
						else if(fieldBuild.fieldTerrain[checkfieldY-2][checkfieldX] > fieldBuild.fieldTerrain[checkfieldY][checkfieldX]){
							y-=velocity;
							monsterLv1.setRotation(270);
						}
						/*else if(fieldBuild.fieldTerrain[checkfieldY][checkfieldX-2] == 1){
							x--;
						}*/
					}
				}	
			}
		}

		if(errorFinish){
			Finish(checkfieldY);
		}
	}
	

}