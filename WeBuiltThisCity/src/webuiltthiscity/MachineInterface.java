package webuiltthiscity;
import webuiltthiscity.*;

public interface MachineInterface{
	public double getDirection();
	public double[] getCursor();
	public void updateDisplay();
	public void log(String message);
	public void setGame(GameMain game);
}
