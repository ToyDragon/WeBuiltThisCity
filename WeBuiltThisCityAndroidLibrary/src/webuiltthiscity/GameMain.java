package webuiltthiscity;

import java.util.ArrayList;

public class GameMain {
	public MachineInterface machine_interface;
	public GraphicsInterface graphics_interface;
	public static int block_speed = 8;
	public static int base_speed = 8;
	public int x,y;
	public double velx,vely;
	public double gravity = 4;
	public double jumpspeed = 40;
	public double speed = 10;
	boolean collided_last_tick;
	public int frequency = 12;
	public int tick = 0;
	
	int life = 20;
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	
	public GameMain(){}
	
	public void setMachineInterface(MachineInterface machine_interface){
		machine_interface.log("Set machine interface to " + machine_interface);
		this.machine_interface = machine_interface;
	}
	public void setGraphicsInterface(GraphicsInterface graphics_interface){
		this.graphics_interface = graphics_interface;
	}
	
	public void init(){
		machine_interface.log("Hello World!");
		machine_interface.log("This is a test");
		int[] screen_size = graphics_interface.getDrawAreaDimensions();
		
		x = screen_size[0] / 4;
		y = screen_size[1] - 275;
		
		
		Block floor = new Block();
		floor.x = screen_size[0]/4;
		floor.y = screen_size[1] - 100;
		floor.w = screen_size[0]/2;
		floor.h = 75;
		
		blocks.add(floor);
		
		Thread game_loop = new Thread(){
			public void run(){
				long time_target = 1000/30;
				while(true){
					long start_time = System.currentTimeMillis();
					tick();
					try{
						long time_elapsed = System.currentTimeMillis() - start_time;
						Thread.sleep(Math.max(0,time_target - time_elapsed));
					}catch(InterruptedException e){}// this sleep should never be interrupted
				}
			}
		};
		game_loop.start();
	}
	
	public void tick(){
		boolean[] buttons = machine_interface.getButtonStatus();
		if(buttons[0]){// up
					
		}
		if(buttons[1]){// left
			velx = -speed;
		}
		if(buttons[2]){// down
			
		}
		if(buttons[3]){// right
			velx = speed;
		}
		if(buttons[4]){// jump
			if(collided_last_tick){
				machine_interface.log("Jump!");
				vely = -jumpspeed;
			}
		}
		if(!buttons[1] && !buttons[3]){
			velx = 0;
		}
		
		if(collided_last_tick){
			vely = Math.min(0, vely);
		}
		
		int nx = (int) (x+velx);
		int ny = (int) (y+vely);

		boolean collide = false;
		int min_y_dist = (int)100;
		for(Block b : blocks){
			int[][] player_corners = new int[][]{
					{nx,ny},
					{nx+100,ny},
					{nx+100,ny+100},
					{nx,ny+100}
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
		
		x = nx;
		y = ny;
		if(y >= graphics_interface.getDrawAreaDimensions()[1] + 100){
			y -= graphics_interface.getDrawAreaDimensions()[1] + 200;
			life--;
		}
		if(x <= -200){
			x += graphics_interface.getDrawAreaDimensions()[0] + 50;
		}
		if(x >= graphics_interface.getDrawAreaDimensions()[0] + 100){
			x -= graphics_interface.getDrawAreaDimensions()[0] + 150;
		}
		if(collide){
			y -= min_y_dist;
		}
		
		
		vely += gravity;
		int cap = 40;
		if(vely > cap)vely=cap;
		
		for(int i = blocks.size()-1; i >= 0; i--){
			blocks.get(i).tick();
			if(blocks.get(i).x + blocks.get(i).w < -50){
				blocks.remove(i);
				//machine_interface.log("Removed!");
			}
		}
		tick++;
		machine_interface.log("" + tick + " < " + frequency);
		if(tick >= frequency){
			tick = 0;
			Block b = new Block();
			int width = graphics_interface.getDrawAreaDimensions()[0];
			int height = graphics_interface.getDrawAreaDimensions()[1];
			b.x = width;
			b.y = (int)(Math.random()*height);
			b.w = (int)(Math.random()*.125*width + .125*width);
			b.h = (int)(Math.random()*.0625*height + .0625*height);
			
			boolean good_spot = true;
			for(Block bb : blocks){
				double dist = b.dist(bb);
				machine_interface.log(""+dist);
				if(dist < 15)good_spot = false;
			}
			if(good_spot)
				blocks.add(b);
			//machine_interface.log("" + b.x + ", " + b.x + ", " + b.w + ", " + b.h);
		}
		collided_last_tick = collide;
		
		
		int dif = x - graphics_interface.getDrawAreaDimensions()[0]/4;
		double scale = dif/(graphics_interface.getDrawAreaDimensions()[0]/4.0) + 1;
		if(dif<0) block_speed = base_speed;
		else block_speed = (int)(scale*base_speed);
		
		paint();
	}
	public void paint(){
		//int x = 50  + (int) (50 * Math.sin(System.currentTimeMillis()/750d));
		
		graphics_interface.fill(0x000000ff);
		graphics_interface.drawImage("baseball_thing", (int)x, (int)y, 100, 100);
		
		for(Block b : blocks){
			graphics_interface.drawImage("block", b.x, b.y, b.w, b.h);
		}
		
		graphics_interface.drawText( "" + life, 20,20);
		graphics_interface.updateDisplay();
	}
}
