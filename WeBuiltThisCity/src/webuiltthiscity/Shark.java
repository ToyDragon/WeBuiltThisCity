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
	//returns the minimum distance from this sharks rectangle to b's rectangle
	public double dist(Shark b){
		//This is broken.
		return 20;
	}
}
