package webuiltthiscity.launder;
import android.app.Activity;
import tv.ouya.console.api.OuyaController;
import webuiltthiscity.*;

public class AndroidMachineInterface implements MachineInterface{

	WeBuiltThisCity main_activity;
	GameMain game;
	boolean[] buttons = new boolean[5];
	public AndroidMachineInterface() {
	}
	public void setMainActivity(WeBuiltThisCity main_activity){
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
		if(main_activity.ouya_controller != null){
			log(""+main_activity.ouya_controller.getAxisValue(OuyaController.AXIS_LS_X));
			if(main_activity.ouya_controller.getAxisValue(OuyaController.AXIS_LS_X) > 0 && main_activity.ouya_controller.getAxisValue(0) < Math.PI/4)
				buttons[0] = true;
			else buttons[0] = false;
	
			if(main_activity.ouya_controller.getAxisValue(0) > Math.PI*1/4 && main_activity.ouya_controller.getAxisValue(0) < Math.PI*3/4)
				buttons[1] = true;
			else buttons[1] = false;
			
			if(main_activity.ouya_controller.getAxisValue(0) > Math.PI*3/4 && main_activity.ouya_controller.getAxisValue(0) < Math.PI*5/4)
				buttons[2] = true;
			else buttons[2] = false;
			
			if(main_activity.ouya_controller.getAxisValue(0) > Math.PI*5/4 && main_activity.ouya_controller.getAxisValue(0) < Math.PI*7/4)
				buttons[3] = true;
			else buttons[3] = false;
		}

		
		
		return buttons;
	}
	public void setGame(GameMain game) {
		this.game = game;
	}
}
