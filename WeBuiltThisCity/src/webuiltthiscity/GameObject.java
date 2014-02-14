package webuiltthiscity;

public abstract class GameObject {
	int x,y,w,h;
	double velx,vely;
	public int width = 50,height = 50;
	public abstract void tick();
	public abstract String getImage();
}
