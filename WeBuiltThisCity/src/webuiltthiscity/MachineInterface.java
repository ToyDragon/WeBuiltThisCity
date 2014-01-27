package webuiltthiscity;
import webuiltthiscity.*;

public interface MachineInterface{
	public double[] getCursor();
	public boolean[] getButtonStatus();
	public void log(String message);
	public void setGame(GameMain game);
}
