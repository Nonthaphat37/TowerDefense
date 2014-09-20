
public class SearchDirection {
	private static float x;
	private static float y;
	private static float dirsin;
	private static float dircos;
	private static float distance;
	private static float vx;
	private static float vy;
	
    public static void calculatorDistance(float x1, float y1, float x2, float y2){
		  x = x1-x2;
		  y = y1-y2;
		  distance = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	  
	public static float SearchX(float x1, float x2, float y1, float y2, float ovx){
		calculatorDistance(x1, x2, y1, y2);
		dircos = (float)Math.acos(x/distance);
			vx = (float) (ovx *2* Math.cos(dircos));

		return vx;
	}
	
	public static float SearchY(float x1, float x2, float y1, float y2, float ovy){
		calculatorDistance(x1, x2, y1, y2);
		dirsin = (float)Math.asin(y/distance);
			vy = (float) (ovy *2* Math.sin(dirsin));
		return vy;
	}
}
