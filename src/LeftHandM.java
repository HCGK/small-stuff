import java.awt.Point;

public class LeftHandM {
	private static final int[][] MOVEMENT = {{0,1},{1,0},{0,-1},{-1,0}};
	
	private LHdM_Test map;
	
	private int boundX;
	private int boundY;
	
	private int xMax = 0;
	private Point startP;
	private int CrossingsOfNeg_xAxis;
	
	LeftHandM(int bound, LHdM_Test map){
		constuctor(bound, bound, map);
	}
	LeftHandM(int boundX, int boundY,LHdM_Test map) {
		constuctor(boundX, boundY, map);
	}
	
	private void constuctor(int bX, int bY, LHdM_Test m) {
		boundX = bX;
		boundY = bY;
		map = m;
	}
	
	int tester(){
		for(xMax =0; xMax < boundX; xMax ++){
			if(traversable(xMax,0) == 1){
				startP = new Point(xMax, 0);
				int i = Itrr(startP,1);
				if(i == 0)
					System.out.println("Island " + startP.x +" : " + xMax);
				else if (i == 1){
					System.out.println("loop "+ startP.x +" : " + xMax);
					return 1;
				}
				else if (i == 2){
					System.out.println("path "+ startP.x +" : " + xMax);
					return 2;
				}
				else if (i == 3)
					System.out.println("point " + xMax);
			}
			else
				System.out.println("boat " + xMax);
		}
		return 4;
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
		System.out.println(q);
		if(q.x == 0){
			xMax = Math.max(q.y,xMax);
			if(p.x == 1 && q.y<0){
				CrossingsOfNeg_xAxis ++;
				System.out.println("X");
			}
		}
		else if(q.x == 1 && p.x == 0 && q.y<0){
			CrossingsOfNeg_xAxis ++;
			System.out.println("+");
		}
	}
	
	private int traversable(Point p) {
		return traversable(p.x, p.y);
	}
	private int traversable(int x, int y){
		if(Math.abs(x) > boundX || Math.abs(y) > boundY)
			return 3;
		else 
			return map.readMap(x, y);
	}
}