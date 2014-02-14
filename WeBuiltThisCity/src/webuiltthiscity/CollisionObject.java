package webuiltthiscity;

public abstract class CollisionObject extends GameObject{
	
	abstract int getSpeed();
	abstract String getType();
	
	//collision objects are defined as a top left x,y coordinate and a width,height
	
	public int[][] getCorners(){
		int[][] corners = new int[][]{
			 {x,y}
			,{x+w,y}
			,{x+w,y+h}
			,{x,y+h}
		};
		return corners;
	}
	
	//returns an (x,y) pair where |x| < |velx| and |y| < |vely|
	//where x,y is the maximum distance this object can move before
	//colliding with object b.
	public int[] detectCollision(CollisionObject b){
		int max_x_dist = (int)velx;
		int max_y_dist = (int)vely;
		
		int nx = (int) (x+velx);
		int ny = (int) (y+vely);
		
		//test for collision with all sharks, and only let them fall to the closest shark and not through it
		//check player collisions with each corner of the player
		int[][] a_corners = new int[][]{
				 {nx,ny}
				,{nx+width,ny}
				,{nx+width,ny+height}
				,{nx,ny+height}
		};
		for(int i = 0; i < a_corners.length; i++){
			
			//extract point from array to make it easier to read
			int test_x = a_corners[i][0];
			int test_y = a_corners[i][1];
			
			int colobj_tl_x = (int) (b.x + b.velx);
			int colobj_tl_y = (int) (b.y + b.vely);
			int colobj_br_x = (int) (b.x + b.w + b.velx);
			int colobj_br_y = (int) (b.y + b.h + b.vely);
			
			//if this point is inside of the shark, the player will collide with the shark
			if(test_x >= colobj_tl_x && test_x <= colobj_br_x &&
			   test_y >= colobj_tl_y && test_y <= colobj_br_y){
				//if the distance to this shark is the shortest distance recorded, this distance
				//is the minimum y distance he can fall
				int y_dist_top = colobj_tl_y - test_y;
				int y_dist_bot = colobj_br_y - test_y;
				
				int x_dist_left =  colobj_tl_x - test_x;
				int x_dist_right = colobj_tl_x - test_x;
				
				//if this dist is the same direction as the movement in the y direction
				if(b.getType() == "shark")
				{
					if( (y_dist_top < 0 && vely > 0) || (y_dist_top > 0 && vely < 0))
						//if this dist is smaller in magnitude than the record
						if(Math.abs(max_y_dist) >= Math.abs(y_dist_top))
							max_y_dist = (int)vely + y_dist_top;

					if( (y_dist_bot < 0 && vely > 0) || (y_dist_bot > 0 && vely < 0))
						if(Math.abs(max_y_dist) >= Math.abs(y_dist_bot))
							max_y_dist = (int)vely + y_dist_bot;
					
					if( (x_dist_left < 0 && velx > 0) || (x_dist_left > 0 && velx < 0))
						if(Math.abs(max_x_dist) >= Math.abs(x_dist_left))
							max_x_dist = (int)velx + x_dist_left;

					if( (x_dist_right < 0 && velx > 0) || (x_dist_right > 0 && velx < 0))
						if(Math.abs(max_x_dist) >= Math.abs(x_dist_right))
							max_x_dist = (int)velx + x_dist_right;
				}
				
			}
		}
		return new int[]{max_x_dist,max_y_dist};
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
