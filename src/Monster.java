import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Monster implements Entity{
	
	protected float x;
	protected float y;
	protected float velocity = (float) 0.5;
	private int checkfieldX = 0;
	private int checkfieldY = 0;
	
	private boolean errorPosition = false;
	private boolean errorStarted = true;
	private boolean errorFinish = false;
	
	protected int checkAnimation = 0;
	protected int attackCastle;

    public Monster(float x, float y){
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
				checkAnimation = 0;
				//monsterLv1.setRotation(0);
			}
		}
		else{
			y-=velocity;
			checkAnimation = 1;
			//monsterLv1.setRotation(270);
		}
	}
	
	public void Start(int check_startX){
		if(checkfieldX < 2){
			x+=velocity;
			checkAnimation = 0;
			//monsterLv1.setRotation(0);
		}
		else{
			errorStarted = false;
		}
	}
	
	public void Finish(int check_finshY){
		if(checkfieldY > -2){
			//monsterLv1.setRotation(270);
			y-=velocity;
			checkAnimation = 1;
		}
		else{
			TowerDefenseGame.HpGame -= attackCastle ;
			x = TowerDefenseGame.monster_startX;								/// monster in castle
			y = TowerDefenseGame.monster_startY;
			errorStarted = true;
			errorFinish = false;
		}
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
	}
	
	protected void updateposition(){
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
							checkAnimation = 0;
							//monsterLv1.setRotation(0);
						}
						else if(fieldBuild.fieldTerrain[checkfieldY+2][checkfieldX] > fieldBuild.fieldTerrain[checkfieldY][checkfieldX]){
							y+=velocity;
							checkAnimation = 0;
							//monsterLv1.setRotation(90);
						}
						else if(fieldBuild.fieldTerrain[checkfieldY-2][checkfieldX] > fieldBuild.fieldTerrain[checkfieldY][checkfieldX]){
							y-=velocity;
							checkAnimation = 1;
							
							//monsterLv1.setRotation(270);
							
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
