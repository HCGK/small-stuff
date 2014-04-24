import java.awt.Point;

public abstract class LeftHandM {
	private static final int[][] MOVEMENT = {{0,1},{1,0},{0,-1},{-1,0}};
	private int boundX;
	private int boundY;
	
	private int xMax = 0;
	private Point startP;
	private int CrossingsOfNeg_xAxis;
	
	LeftHandM(int bound){
		constuctor(bound, bound);
	}
	LeftHandM(int boundX, int boundY) {
		constuctor(boundX, boundY);
	}
	
	private void constuctor(int bX, int bY) {
		boundX = bX;
		boundY = bY;
	}
	
	private void tester(){
		
	}
	/*
	 * assumes p is traversable
	 * 
	 * returns 0 for islands
	 * 	 	   1 for a loop
	 *         2 for 2 path to the boundary
	 *         3 for just a point of traversable
	 */
	private int Itrr(Point p, int facing){
		for(int i=0;i<4;i++){
			int turn = (i + 3 + facing ) % 4;
			Point newP = new Point(p);
			newP.translate(MOVEMENT[turn][0], MOVEMENT[turn][1]);
			if(traversable(newP) == 1){
				tests(p, newP); //modifies fields
				if(newP.equals(startP))
					return CrossingsOfNeg_xAxis % 2;
				else
					return Itrr(newP, turn);
			}
			else if(traversable(newP) == 2){
				return 2;
			}
		}
		return 3;
	}
	
	private void tests(Point p, Point q) {
		if(q.x == 0){
			xMax = Math.max(q.y,xMax);
			if(p.x == 1 && q.y<0)
				CrossingsOfNeg_xAxis ++;
		}
		else if(q.x == 1 && p.x == 0 && q.y<0);
		CrossingsOfNeg_xAxis ++;
	}
	
	private int traversable(Point p) {
		if(Math.abs(p.x) > boundX || Math.abs(p.y) > boundY)
			return 2;
		else 
			return 2;
	}
}
