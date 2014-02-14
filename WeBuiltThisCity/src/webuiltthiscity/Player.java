package webuiltthiscity;

public class Player {
	//x,y position of the top left of the player, and the width,height of the player
	public int x,y;
	public int width = 50,height = 50;
	
	//velx is the change in the players x per tick
	public double velx,vely;
	
	public double velx_cap = 1000,vely_cap = 30;
	
	//gravity is the constant added to vely every tick
	public double gravity = 4;
	
	//jump speed is the initial y velocity of the player when he jumps
	//higher number = bigger jump
	public double jumpspeed = 45;
	
	//change in velx when the player presses left or right
	public double speed = 10;
	
	//store collision information from previous tick
	public int last_velx;
	public int last_vely;
	
	private int stunTime = 30;
	
	public int life = 1;
	public int score = 0;
	public GameMain game;
	
	//public int[] screenDimensions = graphics_interface.getAreaDrawDimensions();
	
	public Player(GameMain game){
		this.game = game;
		
		int screen_w = game.graphics_interface.getDrawAreaDimensions()[0];
		int screen_h = game.graphics_interface.getDrawAreaDimensions()[1];
		
		setPosition(screen_w / 4, screen_h / 2);
	}
	
	public void setPosition(int new_x,int new_y){
		x = new_x;
		y = new_y;
	}
	
	public void tick(){
		
		handleUserInput();
		
		detectCollisionsAndStep();
		
		checkIfEaten();
		
		if(stunTime > 0)
			stunTime--;
		
		//update the players score
		long elapsed_time =  System.currentTimeMillis() - game.start_time;
		score = (int) (elapsed_time/1000);
	}
	
	private void handleUserInput(){
		boolean[] buttons = game.machine_interface.getButtonStatus();
		//if(buttons[MachineInterface.BUTTON_UP]){}// up
		//if(buttons[MachineInterface.BUTTON_DOWN]){}// down
		
		if(stunTime == 0)
		{
			if(buttons[MachineInterface.BUTTON_LEFT])velx = -speed;
			
			if(buttons[MachineInterface.BUTTON_RIGHT])velx = speed;
			
			if(buttons[MachineInterface.BUTTON_JUMP]){
				//only allow a jump if the player is standing on a shark
				if(last_vely == 0){
					vely = -jumpspeed;
				}
			}
		}
		//if the player isn't trying to move, set his xvel to 0 so he isnt sliding around everywhere
		if(!buttons[MachineInterface.BUTTON_LEFT] && !buttons[MachineInterface.BUTTON_RIGHT]){
			velx = 0;
		}
		
	}
	
	private void checkIfEaten(){
		int[][] corners = new int[][]{
			 {x,y}
			,{x+width,y}
			,{x+width,y+height}
			,{x,y+height}
		};
		boolean eaten = false;
		for(CollisionObject s : game.objects){
			int[][] s_corners = s.getCorners();
			for(int i = 0; i < corners.length; i++){
				if(corners[i][0] > s_corners[0][0] && corners[i][0] < s_corners[2][0]
				 &&corners[i][1] > s_corners[0][1] && corners[i][1] < s_corners[2][1])
					eaten = true;
			}
		}
		if(eaten){
			//life--;
			y -= height*3/2;
		}
	}
	
	private void stunPlayer()
	{
		stunTime = 5;
	}
	private void detectCollisionsAndStep(){	
		vely += gravity;
	
		//predict the next player position and test its collision
		int nx = (int) (x+velx);
		int ny = (int) (y+vely);

		//PHYICS---------------------------------------------------------------------------------------------------
		
		//collide is whether or not he will collide this frame
		//min_y_Dist is the maximum distance he can fall without hitting a shark
		
		int max_x_dist = (int)velx;
		int max_y_dist = (int)vely;
		
		
		//test for collision with all sharks, and only let them fall to the closest shark and not through it
		for(CollisionObject b : game.objects){
			//check player collisions with each corner of the player
			int[][] player_corners = new int[][]{
					 {nx,ny}
					,{nx+width,ny}
					,{nx+width,ny+height}
					,{nx,ny+height}
			};
			for(int i = 0; i < player_corners.length; i++){
				
				//extract point from array to make it easier to read
				int test_x = player_corners[i][0];
				int test_y = player_corners[i][1];
				
				int colobj_tl_x = b.x - b.getSpeed();
				int colobj_tl_y = b.y;
				int colobj_br_x = b.x + b.w - b.getSpeed();
				int colobj_br_y = b.y + b.h;
				
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
					else if(b.getType() == "laser")
					{
						if(stunTime == 0)
						stunPlayer();
					}
					
				}
			}
		}
		
		boolean collided_y = max_y_dist != vely;
		boolean collided_x = max_x_dist != velx;
		
		if(collided_y || collided_x){
			max_x_dist -= Shark.shark_speed;
		}
		
		//update velocities to reflect collisions
		velx = max_x_dist;
		vely = max_y_dist;
		
		//move player to new predicted spot
		x = x + (int)velx;
		y = y + (int)vely;
		
		//keep player in screen
		if(x < 0) 
			x = 0;
		if(x > game.graphics_interface.getDrawAreaDimensions()[0] - width)
			x = game.graphics_interface.getDrawAreaDimensions()[0] - width;

		//vertical wrap
		if(y > game.graphics_interface.getDrawAreaDimensions()[1]) {
			y = -height;
			vely = 0;
			System.out.println(--life);
		}
		
		//cap velocities
		if(velx > velx_cap) velx = velx_cap;
		if(velx < -velx_cap) velx = -velx_cap;
		if(vely > velx_cap) vely = vely_cap;
		if(vely < -velx_cap) vely = -vely_cap;
		
		last_velx = (int)velx;
		last_vely = (int)vely;
	}
}
