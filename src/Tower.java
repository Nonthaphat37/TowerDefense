import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Tower implements Entity{
	
	 protected float x;
	 protected float y;
	 private float dx;
	 private float dy;
	 private int Width = 39;
	 private int Height = 39;
	 private int rememberNumMon = 0;
	 private boolean checkremember_Mon = false;
	 
	 protected float dir = 0;
	 
	 protected int rangeTower = 400;
	 
	 public Tower(float x, float y){
			this.x = x;
			this.y = y;
	    }
	 
	 public float getAngle(float dir){
		return (float) (dir/Math.PI*180);
	 }
	 
     protected void rangeCheck(Monster m, int numMon){
		 if(Math.sqrt(Math.pow(m.getX()-x, 2) + Math.pow(m.getY()-y, 2)) <= rangeTower && !checkremember_Mon){
			 rememberNumMon = numMon;
			 checkremember_Mon = true;
		  }
		 else if(rememberNumMon == numMon && checkremember_Mon){
			 if(Math.sqrt(Math.pow(m.getX()-x, 2) + Math.pow(m.getY()-y, 2)) <= rangeTower){
				 dx = x-m.getX();
				 dy = y-m.getY();
				 dir =  (float) Math.atan(dy/dx);
				 dir = getAngle(dir); 
				 if(dx < 0){
					 dir+=180;
				 }
				 System.out.println(rememberNumMon);
			 }
			 else{
				 checkremember_Mon = false;
			 }
		 }
    }
	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void update(GameContainer container, int delta) {
		
	}
}
