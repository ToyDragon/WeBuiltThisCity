package webuiltthiscity;

import java.util.ArrayList;

public class GameMain {
	
	//Used for interfacing with the system. Will be set by
	//system launchers after GameMain is initialized
	public MachineInterface machine_interface;
	public GraphicsInterface graphics_interface;
	
	//time in millis that the game started; used to calculate score.
	public long start_time;
	
	//how many ticks between attempting to spawn sharks
	public int frequency = 14;
	
	//current tick
	public int tick = 0;
	
	Player player;	
	ArrayList<Shark> sharks;
	
	public GameMain(){}
	
	public void setMachineInterface(MachineInterface machine_interface){
		this.machine_interface = machine_interface;
	}
	public void setGraphicsInterface(GraphicsInterface graphics_interface){
		this.graphics_interface = graphics_interface;
	}
	
	public void init(){
		//grab start time
		start_time = System.currentTimeMillis();
	
		//initialize player
		player = new Player(this);
		
		//initialize sharks
		initSharks();
		
		//this loop will call the tick() method and repeat 30 times a second
		Thread game_loop = new Thread(){
			
			public void run(){
		
				//milli seconds per tick
				long time_target = 1000/30;
				
				//repeat until the application closes
				while(true){
					
					//grab time before executing the tick
					long start_time = System.currentTimeMillis();
					//execute the tick
					tick();
					try{
						//grab the time elapsed since before executing tick
						long time_elapsed = System.currentTimeMillis() - start_time;
						//set time_to_sleep to target-elapsed, and make sure it is not a negative number
						long time_to_sleep = Math.max(0,time_target - time_elapsed);
						//pause this thread for that long
						Thread.sleep(time_to_sleep);
					}catch(InterruptedException e){
						//Thread.sleep() can only be manually interrupted, which we never do.
						//This catch will never execute.
					}
				}
	
			}
		
		};
		
		//call game_loop.run() in a new system thread
		game_loop.start();
	}
	
	public void initSharks(){
		int screen_w = graphics_interface.getDrawAreaDimensions()[0];
		int screen_h = graphics_interface.getDrawAreaDimensions()[1];
		
		sharks = new ArrayList<Shark>();
		
		//all sharks spawn off screen, so this one provides an initial platform for beiber
		Shark first_shark = new Shark();
		
		first_shark.x = screen_w/4;
		first_shark.y = screen_h - 100;
		first_shark.w = screen_w;
		first_shark.h = 75;
		
		sharks.add(first_shark);
	}
	public void handleSharks(){
		//remove all of the sharks that are off screen from the physics and drawing
		for(int i = sharks.size()-1; i >= 0; i--){
			sharks.get(i).tick();
			if(sharks.get(i).x + sharks.get(i).w < -50){
				sharks.remove(i);
			}
		}
			
		//create a new shark every (frequency) amt of ticks
		tick++;
		if(tick >= frequency){
			tick = 0;
			Shark b = new Shark();
			int screen_w = graphics_interface.getDrawAreaDimensions()[0];
			int screen_h = graphics_interface.getDrawAreaDimensions()[1];
			
			int spawn_x = screen_w;
			int spawn_y_min = screen_h/10;
			int spawn_y_max = screen_h*4/5;
			int shark_width_min = screen_w/8;
			int shark_width_max = screen_w/6;
			int shark_height_min = screen_h/8;
			int shark_height_max = screen_h/6;
			
			b.x = spawn_x;
			b.y = (int)(Math.random()*(spawn_y_max-spawn_y_min) + spawn_y_min);
			b.w = (int)(Math.random()*(shark_width_max-shark_width_min) + shark_width_min);
			b.h = (int)(Math.random()*(shark_height_max-shark_height_min) + shark_height_min);
			
			//don't make sharks too close, this is where the sharks should be
			//spawned in a way that isn't impossible. shark.dist is dumb right now
			//so this doesn't work right
			boolean good_spot = true;
			for(Shark bb : sharks){
				good_spot = good_spot && b.overlaps(bb);
			}
			
			//if(good_spot)
				sharks.add(b);
		}
	}
	//tick the world one frame
	public void tick(){
		
		if(player.life > 0){
			//tick all sharks
			handleSharks();
			
			//tick the player
			player.tick();
			
			//recalculate the time running
			long time_running = System.currentTimeMillis() - start_time;
			
			//sharks will double in speed after the first 10 seconds and
			//continue speeding up in a linear way
			Shark.shark_speed = (int)(Shark.initial_speed * (1 + time_running/10000.0));
		}
		//paint the graphics to the screen
		paint();
	}
	public void paint(){
		//                 R  << 16  +    G << 8  +   B
		int beiber_blue = (255 << 24) + (57 << 16) + (112 << 8) + 143;
		graphics_interface.fill(beiber_blue);
		
		//draw beiber
		graphics_interface.drawImage("baseball_thing", player.x, player.y, player.width, player.height);
		
		//draw all of the sharks
		for(Shark b : sharks){
			graphics_interface.drawImage("block", b.x, b.y, b.w, b.h);
		}
		
		//draw the player life and score in the top left of the screen
		graphics_interface.drawText( "" + player.life + "     " + player.score, 20,20);
		
		//dim screen
		int gray = (120 << 24) + (0 << 16) + (0 << 8) + (0 << 0);
		
		if(player.life <= 0){
			graphics_interface.fill(gray);
		}

		//All painting goes to a buffer, to push the buffer to the screen call updateDisplay()
		graphics_interface.updateDisplay();
	}
}
