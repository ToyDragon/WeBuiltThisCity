package webuiltthiscity;

public class GameMain {
	public MachineInterface machine_interface;
	public GraphicsInterface graphics_interface;
	
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
		double[] cursor_pos = machine_interface.getCursor();
		machine_interface.log("Cursor pos:  " + cursor_pos[0] + ", " + cursor_pos[1]);
		paint();
	}
	public void paint(){
		int x = 50  + (int) (50 * Math.sin(System.currentTimeMillis()/750d));
		
		graphics_interface.fill(0xffffffff);
		graphics_interface.drawImage("test_image", x, 0);
		graphics_interface.publish();
	}
}
