package webuiltthiscity.launcher;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import webuiltthiscity.*;

public class AndroidMachineInterface implements MachineInterface{

	Activity main_activity;
	
	public void setMainActivity(Activity main_activity){
		this.main_activity = main_activity;
	}
	
	public double[] getCursor() {
		// TODO Auto-generated method stub
		return new double[]{10.0,15.0};
	}

	public double getDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void log(String message) {
		android.util.Log.d("WeBuiltThisCity",message);
	}

	public void setGame(GameMain arg0) {
		// TODO Auto-generated method stub
		
	}

	public void updateDisplay() {
		// TODO Auto-generated method stub
		
	}
}
