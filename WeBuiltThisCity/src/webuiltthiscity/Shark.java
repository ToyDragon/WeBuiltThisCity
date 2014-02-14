package webuiltthiscity;

public class Shark {
	public static int shark_speed = 8;
	public static int initial_speed = 8;
	
	//sharks are defined as a top left x,y coordinate and a width,height
	int x,y,w,h;
	
	//sharks tick once every frame
	public void tick(){
		//move shark left by shark_speed
		x -= shark_speed;
	}
	
	public int[][] getCorners(){
		int[][] corners = new int[][]{
				 {x,y}
				,{x+w,y}
				,{x+w,y+h}
				,{x,y+h}
		};
		return corners;
	}
	
	public boolean overlaps(Shark b){
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
