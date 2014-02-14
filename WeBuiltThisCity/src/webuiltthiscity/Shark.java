package webuiltthiscity;

public class Shark extends CollisionObject{
	public static int shark_speed = 8;
	public static int initial_speed = 8;
	
	public Shark()
	{
		super();
	}
	
	//sharks tick once every frame
	public void tick(){
		//move shark left by shark_speed
		x -= shark_speed;
	}
	
	public int getSpeed()
	{
		return shark_speed;
	}
	
	public String getType()
	{
		return "shark";
	}
	
	public String getImage() {
		return "shark";
	}
}
