import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Monster implements Entity{
	
	protected float x;
	protected float y;
	protected float velocity = (float) 2;
	private int checkfieldX = 0;
	private int checkfieldY = 0;
	
	private boolean errorPosition = false;
	private boolean errorStarted = true;
	private boolean errorFinish = false;
	
	private int checkfield1 = 0;
	private int checkfield2 = 0;
	private int checkfield3 = 0;
	
	
	//set everything in monster
	protected int checkAnimation = 0;
	protected int attackCastle;
	protected int hpMonster  = 500;
	protected int element = 0;

    public Monster(float x, float y){
		this.x = x;
		this.y = y;
    }
    
    public float getX(){
    	return x;
    }
    
    public float getY(){
    	return y;
    }
    
    
    public void MonsterAttacked(int attack,int element){
    	if(this.element == 0){
    		hpMonster -= attack;
    	}else if(this.element == 1 && element == 2){
    		hpMonster -= attack*2;
    	}
    }
    
    public boolean getDeath(){
    	if(hpMonster <= 0){
    		return true;
    	}else{
        	return false;
    	}
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
						){
					errorPosition = true;
				}
				else{
				
					if(checkfieldX <= fieldBuild.x_size && checkfieldX >= 2 
					&& checkfieldY <= fieldBuild.y_size && checkfieldY >= 2 ){
					
						if(fieldBuild.fieldTerrain[checkfieldY+2][checkfieldX] == 99){
							checkfield1 = 0;
						}else{
							checkfield1 = fieldBuild.fieldTerrain[checkfieldY+2][checkfieldX];
						}
						if(fieldBuild.fieldTerrain[checkfieldY-2][checkfieldX] == 99){
							checkfield3 = 0;
						}else{
							checkfield3 = fieldBuild.fieldTerrain[checkfieldY-2][checkfieldX];
						}
						if(fieldBuild.fieldTerrain[checkfieldY][checkfieldX] == 99){
							checkfield2 = 0;
						}else{
							checkfield2 = fieldBuild.fieldTerrain[checkfieldY][checkfieldX];
						}
						
						//check position to walk monster
						if(fieldBuild.fieldTerrain[checkfieldY][checkfieldX+2] == 1
							){
							x+=velocity;
							checkAnimation = 0;
							//monsterLv1.setRotation(0);
						}
						else if(checkfield1 > checkfield2){
							y+=velocity;
							checkAnimation = 0;
							//monsterLv1.setRotation(90);
						}
						else if(checkfield3 > checkfield2){
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
