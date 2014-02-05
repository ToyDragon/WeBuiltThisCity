package webuiltthiscity;

public class Player {
	public int x,y;
	public int width = 50,height = 50;
	public double velx,vely;
	public double gravity = 4;
	public double jumpspeed = 25;
	public double speed = 10;
	public boolean collided_last_tick;
	public int life = 1;
	public int score = 0;
	public GameMain game;
	
	public Player(GameMain game){
		this.game = game;
		
		int screen_w = game.graphics_interface.getDrawAreaDimensions()[0];
		int screen_h = game.graphics_interface.getDrawAreaDimensions()[1];
		
		setPosition(screen_w / 4, screen_h - 275);
	}
	
	public void setPosition(int new_x,int new_y){
		x = new_x;
		y = new_y;
	}
	
	public void tick(){
		boolean[] buttons = game.machine_interface.getButtonStatus();
		//if(buttons[0]){}// up
		//if(buttons[2]){}// down
		
		if(buttons[1]){// left
			velx = -speed;
		}
		if(buttons[3]){// right
			velx = speed;
		}
		if(buttons[4]){// jump
			if(collided_last_tick){
				vely = -jumpspeed;
			}
		}
		if(!buttons[1] && !buttons[3]){
			velx = 0;
		}
		
		//make sure player doesn't fall through the floor
		if(collided_last_tick){
			vely = Math.min(0, vely);
		}
		
		//predict the next player position and test its collision
		int nx = (int) (x+velx);
		int ny = (int) (y+vely);

		boolean collide = false;
		int min_y_dist = (int)100;
		
		//test for collision with all sharks, and only let them fall to the closest shark and not through it
		for(Shark b : game.sharks){
			int[][] player_corners = new int[][]{
					{nx,ny},
					{nx+width,ny},
					{nx+width,ny+height},
					{nx,ny+height}
			};
			for(int i = 0; i < 4; i++){
				int tx = player_corners[i][0];
				int ty = player_corners[i][1];
				if(tx >= b.x && tx <= b.x+b.w && ty >= b.y && ty <= b.y + b.h){
					if(ty - b.y < min_y_dist){
						min_y_dist = ty - b.y;
					}
					collide = true;
				}
			}
		}
		
		//move player with shark hes standing and shift ny so he doesnt fall through the shark
		if(collide){
			nx -= game.block_speed;
			ny -= min_y_dist;
		}
		
		//move player to new predicted spot
		x = nx;
		y = ny;
		
		//add effects due to gravity
		vely += gravity;
		
		long elapsed_time =  System.currentTimeMillis() - game.start_time;
		score = (int) (elapsed_time/1000);
		
		collided_last_tick = collide;
	}
}
