package webuiltthiscity;

public class Player extends CollisionObject{
	
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
	
	private int stunTime = 0;
	
	public int life = 1;
	public int score = 0;
	public GameMain game;
	
	//public int[] screenDimensions = graphics_interface.getAreaDrawDimensions();
	
	public Player(GameMain game){
		this.game = game;
		width = 50;
		height = 50;
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
		
		//checkIfEaten();
		
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
		
		if(stunTime <= 0)
		{
			if(buttons[MachineInterface.BUTTON_LEFT])velx = -speed;
			
			if(buttons[MachineInterface.BUTTON_RIGHT])velx = speed;
			
			if(buttons[MachineInterface.BUTTON_JUMP]){
				//only allow a jump if the player is standing on a shark
				if(last_vely == 0){
					vely = -jumpspeed;
				}
			}
			//if the player isn't trying to move, set his xvel to 0 so he isnt sliding around everywhere
			if(!buttons[MachineInterface.BUTTON_LEFT] && !buttons[MachineInterface.BUTTON_RIGHT]){
				velx = 0;
			}
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
		for(CollisionObject s : GameMain.collision_objects){
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
		game.machine_interface.log(""+velx+ " : " + vely);
		int[] max_dist = new int[]{(int)velx,(int)vely};
		for(CollisionObject o : GameMain.collision_objects){
			int[] this_dist = detectCollision(o);
			game.machine_interface.log(o.getImage() + " " +max_dist[0]+ " : " + max_dist[1]);
			if(this_dist[0] < max_dist[0])max_dist[0] = this_dist[0];
			if(this_dist[1] < max_dist[1])max_dist[1] = this_dist[1];
		}
		
		game.machine_interface.log(""+max_dist[0]+ " : " + max_dist[1]);
		
		int max_x_dist = max_dist[0];
		int max_y_dist = max_dist[1];
		
		//collide is whether or not he will collide this frame
		//min_y_Dist is the maximum distance he can fall without hitting a shark
		
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
	
	int getSpeed() {
		return 0;
	}
	
	String getType() {
		return "player";
	}
	
	public String getImage() {
		return "beiber";
	}
}
