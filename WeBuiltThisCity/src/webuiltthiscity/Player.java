package webuiltthiscity;

public class Player {
	//x,y position of the top left of the player, and the width,height of the player
	public int x,y;
	public int width = 50,height = 50;
	
	//velx is the change in the players x per tick
	public double velx,vely;
	
	//gravity is the constant added to vely every tick
	public double gravity = 4;
	
	//jump speed is the initial y velocity of the player when he jumps
	//higher number = bigger jump
	public double jumpspeed = 25;
	
	//change in velx when the player presses left or right
	public double speed = 10;
	
	//whether or not the player was touching a shark last tick
	//used to keep him from falling through the sharks
	public boolean collided_last_tick;
	
	public int life = 1;
	public int score = 0;
	public GameMain game;
	
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
		boolean[] buttons = game.machine_interface.getButtonStatus();
		//if(buttons[MachineInterface.BUTTON_UP]){}// up
		//if(buttons[MachineInterface.BUTTON_DOWN]){}// down
		
		if(buttons[MachineInterface.BUTTON_LEFT])velx = -speed;
		
		if(buttons[MachineInterface.BUTTON_RIGHT])velx = speed;
		
		if(buttons[MachineInterface.BUTTON_JUMP]){
			//only allow a jump if the player is standing on a shark
			if(collided_last_tick){
				vely = -jumpspeed;
			}
		}
		
		//if the player isn't trying to move, set his xvel to 0 so he isnt sliding around everywhere
		if(!buttons[MachineInterface.BUTTON_LEFT] && !buttons[MachineInterface.BUTTON_RIGHT]){
			velx = 0;
		}
		
		//make sure player doesn't fall through the floor if hes on a shark
		if(collided_last_tick){
			vely = Math.min(0, vely);
		}
		
		//predict the next player position and test its collision
		int nx = (int) (x+velx);
		int ny = (int) (y+vely);

		//PHYICS---------------------------------------------------------------------------------------------------
		
		//collide is whether or not he will collide this frame
		//min_y_Dist is the maximum distance he can fall without hitting a shark
		boolean collide = false;
		int min_y_dist = (int)100;
		
		//test for collision with all sharks, and only let them fall to the closest shark and not through it
		for(Shark b : game.sharks){
			//check player collisions with each corner of the player
			int[][] player_corners = new int[][]{
					{nx,ny},
					{nx+width,ny},
					{nx+width,ny+height},
					{nx,ny+height}
			};
			for(int i = 0; i < 4; i++){
				
				//extract point from array to make it easier to read
				int tx = player_corners[i][0];
				int ty = player_corners[i][1];
				
				//if this point is inside of a shark, the player will collide with the shark
				if(tx >= b.x && tx <= b.x+b.w && ty >= b.y && ty <= b.y + b.h){
					//if the distance to this shark is the shortest distance recorded, this distance
					//is the minimum y distance he can fall
					if(ty - b.y < min_y_dist){
						min_y_dist = ty - b.y;
					}
					collide = true;
				}
			
			}
		}
		
		//move player with shark hes standing and shift ny so he doesnt fall through the shark
		if(collide){
			nx -= Shark.shark_speed;
			ny -= min_y_dist;
		}
		
		//move player to new predicted spot
		x = nx;
		y = ny;
		
		//add effects due to gravity
		vely += gravity;
		
		//update the players score
		long elapsed_time =  System.currentTimeMillis() - game.start_time;
		score = (int) (elapsed_time/1000);
		
		collided_last_tick = collide;
	}
}
