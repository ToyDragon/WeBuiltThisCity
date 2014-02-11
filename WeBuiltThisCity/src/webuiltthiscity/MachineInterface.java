package webuiltthiscity;

/*
 * Machine interface will be implemented differently for each system launcher.
 * Once GameMain is initialized, its setMachineInterface method will be used.
 * 
 * Machine interfaces are used to get user input, and to output to the log.
 */
public interface MachineInterface{
	static final int BUTTON_UP=0,BUTTON_LEFT=1,BUTTON_DOWN=2,BUTTON_RIGHT=3,BUTTON_JUMP=4;
	public double[] getCursor();
	public boolean[] getButtonStatus();
	public void log(String message);
	public void setGame(GameMain game);
}
