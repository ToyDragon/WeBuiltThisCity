package webuiltthiscity;

import java.util.ArrayList;

public class GameMain {
	public MachineInterface machine_interface;
	public GraphicsInterface graphics_interface;
	
	public static int block_speed = 8;
	public static int base_speed = 8;
	
	public long start_time;
	public int frequency = 9;
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
		start_time = System.currentTimeMillis();
		
		player = new Player(this);
		
		initSharks();
		
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
		//remove all of the sharks that are off screen
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
			int spawn_y_min = screen_w/10;
			int spawn_y_max = screen_w*9/10;
			int shark_width_min = screen_w/8;
			int shark_width_max = screen_w/6;
			int shark_height_min = screen_h/16;
			int shark_height_max = screen_h/8;
			
			b.x = spawn_x;
			b.y = (int)(Math.random()*(spawn_y_max-spawn_y_min) + spawn_y_min);
			b.w = (int)(Math.random()*(shark_width_max-shark_width_min) + shark_width_min);
			b.h = (int)(Math.random()*(shark_height_max-shark_height_min) + shark_height_min);
			
			//don't make sharks too close, this is where the sharks should be
			//spawned in a way that isn't impossible. shark.dist is dumb right now
			//so this doesn't work right
			boolean good_spot = true;
			for(Shark bb : sharks){
				double dist = b.dist(bb);
				if(dist < 45)good_spot = false;
			}
			if(good_spot){
				sharks.add(b);
			}
		}
	}
	public void tick(){
		
		handleSharks();
		
		player.tick();
		
		long time_running = System.currentTimeMillis() - start_time;
		
		//sharks will double in speed after the first 10 seconds and
		//continue speeding up in a linear way
		block_speed = (int)(base_speed * (1 + time_running/10000.0));
		
		paint();
	}
	public void paint(){
		//int x = 50  + (int) (50 * Math.sin(System.currentTimeMillis()/750d));
		
		graphics_interface.fill((57 << 16) + (112 << 8) + (143 << 0));
		graphics_interface.drawImage("baseball_thing", player.x, player.y, player.width, player.height);
		
		for(Shark b : sharks){
			graphics_interface.drawImage("block", b.x, b.y, b.w, b.h);
		}
		
		graphics_interface.drawText( "" + player.life + "     " + player.score, 20,20);
		graphics_interface.updateDisplay();
	}
}
