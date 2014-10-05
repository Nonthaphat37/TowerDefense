
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Tower implements Entity{
	
	 private int Width = 39;
	 private int Height = 39;
	 protected float x;
	 protected float y;
	 private float dx;
	 private float dy;
	 protected int rememberNumMon = 0;
	 public boolean checkremember_Mon = false;
	 
	 public boolean checkBullet = false;
	 
	 protected float dir = 0;

	 
	 protected float mx;
	 protected float my;
	 
	 private int numTower = 0;
	
	 protected boolean checkMonster = false;
	 
	 
	 //upgrate
	 protected int rangeTower = 50;
	 protected  int speedTower = 0;
	 protected int speedrealTower;
	 protected int attackTower = 0;
	 protected int goldRate = 10;
	 protected int goldTimeRate = 0;
	 protected int element = 0;
	 
	 public int upgrateAttack = 0;
	 public int upgrateSpeed = 0;
	 public int upgrateRange = 0;
	 
	 public int upgrateGoldRate = 0;
	 public int upgrateGoldTimeRate = 0;
	 
	 private int speedSecTower = 2500;
	 private int calSpeed = 0;
	 
	 //getRunes
	 public int BonusAttack = 1;
	 private Image RuneAttack;
	 
	 public Tower(float x, float y) throws SlickException{
			this.x = x;
			this.y = y;
			RuneAttack = new Image("res/getRuneAttack.png");
	    }
	 
	 public int upgratePrice(String checkUpgrate){
		 if(checkUpgrate == "Attack"){
			 return (int) (10+20*upgrateAttack + Math.pow(2, upgrateAttack ));
		 }
		 else if(checkUpgrate == "Speed"){
			 return (int) (10+20*upgrateSpeed + Math.pow(2, upgrateSpeed ));
		 }
		 else if(checkUpgrate == "Range"){
			 return (int) (10+10*upgrateRange + Math.pow(1.4, upgrateSpeed));
		 } 
		 else if(checkUpgrate == "GoldRate"){
			 return 100+150*upgrateGoldRate;
		 }
		 else if(checkUpgrate == "GoldTimeRate"){
			 return 70+120*upgrateGoldTimeRate;
		 }
		 return 0;
	 }
	 
	 public int getAttack(){
		 if(TowerDefenseGame.typeRunes == 2){
			 BonusAttack = 2;
		 }
		 else{
			 BonusAttack = 1;
		 }
		 return attackTower*BonusAttack;
	 }
	 
	 public int getElement(){
		 return element;
	 }
	 
	 public int getNumMon(){
		 return rememberNumMon;
	 }
	 
	 public float getAngle(float dir){
		return (float) (dir/Math.PI*180);
	 }
	 
     public void rangeCheck(Monster m, int numMon, int delta){
		 /*if(Math.sqrt(Math.pow(m.getX()-x, 2) + Math.pow(m.getY()-y, 2)) <= rangeTower && !checkremember_Mon){
			 rememberNumMon = numMon;
			 checkremember_Mon = true;
		  }*/
		  //else if(rememberNumMon == numMon && checkremember_Mon){
    	 if(!checkremember_Mon){
			 rememberNumMon = numMon;
			 checkremember_Mon = true;
			 
    	 }
		 if(rememberNumMon == numMon){
			
			 	if(Math.sqrt(Math.pow(m.getX()-x, 2) + Math.pow(m.getY()-y, 2)) <= rangeTower){
					 dx = x-m.getX();
					 dy = y-m.getY();
					 dir =  (float) Math.atan(dy/dx);
					 dir = getAngle(dir); 
					 if(dx < 0){
						 dir+=180;
					 }
					 CalSpeedTower(delta);   // release bullet and delay time
					 
			 	}else{
			 		checkremember_Mon = false;
			 		
			 	}
		 }
		 else if(rememberNumMon > numMon){
			 rememberNumMon = numMon;
		 }
    }
    
     protected void RenderRuneAttack(){
    	 if(TowerDefenseGame.typeRunes == 2){
        	 RuneAttack.draw(x+36,y-12);
    	 }
     }
     
    private void CalSpeedTower(int delta){
    	calSpeed+=delta;
    	speedrealTower = Math.abs(speedTower*35-speedSecTower);
    	if(calSpeed>=speedrealTower){
    		calSpeed = 0;
    		TowerDefenseGame.setupBullet();
			TowerDefenseGame.releaseBullet(numTower);
			
    	}
    }
     
 	protected void getNumTower(int numTower){
 		this.numTower = numTower;
 	}
 	
	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void update(GameContainer container, int delta) {
	}
	
}
