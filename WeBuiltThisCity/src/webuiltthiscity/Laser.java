package webuiltthiscity;

public class Laser extends CollisionObject{
	
	public int laser_speed;
	
	public Laser()
	{
		super();
		laser_speed = 17;
	}
	
	public void tick()
	{
		x -= laser_speed;
	}
	
	public int getSpeed()
	{
		return laser_speed;
	}
	
	public String getType()
	{
		return "laser";
	}
	
	public String getImage() {
		return "shark";
	}

}
