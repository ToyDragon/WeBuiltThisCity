package webuiltthiscity;

public abstract class CollisionObject {
	
	abstract void tick();
	abstract int getSpeed();
	abstract String getType();
	
	//collision objects are defined as a top left x,y coordinate and a width,height
	
	int x,y,w,h;
	public int[][] getCorners(){
		int[][] corners = new int[][]{
				 {x,y}
				,{x+w,y}
				,{x+w,y+h}
				,{x,y+h}
		};
		return corners;
	}
	
	public boolean overlaps(CollisionObject b){
		int[][] a_corners = getCorners();
		int[][] b_corners = b.getCorners();
		for(int i = 0; i < a_corners.length; i++){
			if(a_corners[i][0] >= b_corners[0][0] && a_corners[i][0] <= b_corners[2][0]
			 &&a_corners[i][1] >= b_corners[0][1] && a_corners[i][1] <= b_corners[2][1])
				return true;
		}
		for(int i = 0; i < b_corners.length; i++){
			if(b_corners[i][0] >= a_corners[0][0] && b_corners[i][0] <= a_corners[2][0]
			 &&b_corners[i][1] >= a_corners[0][1] && b_corners[i][1] <= a_corners[2][1])
				return true;
		}
		return false;
	}

}
