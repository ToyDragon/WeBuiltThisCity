package webuiltthiscity.launcher;
import android.app.Activity;
import webuiltthiscity.*;

public class AndroidMachineInterface implements MachineInterface{

	Activity main_activity;
	GameMain game;
	public void setMainActivity(Activity main_activity){
		this.main_activity = main_activity;
	}
	public double[] getCursor() {
		return new double[]{10.0,15.0};
	}
	public double getDirection() {
		return 0;
	}
	public void log(String message) {
		android.util.Log.d("WeBuiltThisCity",message);
	}
	public boolean[] getButtonStatus() {
		return null;
	}
	public void setGame(GameMain game) {
		this.game = game;
	}
}
