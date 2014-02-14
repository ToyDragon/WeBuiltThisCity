package webuiltthiscity;
import java.util.Random;
public class LaserShark extends Shark{
	
	private int laser_ticks;
	public LaserShark()
	{
		super();
		laser_ticks = 5;
	}
	public void tick()
	{
		laser_ticks--;
		if(laser_ticks == 0)
		{
			shootLaser();
			laser_ticks = (int) new Random().nextGaussian()+5;
		}
		super.tick();
	}
	public void shootLaser()
	{
		
	}

}
