package webuiltthiscity.launder;

import android.R;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import tv.ouya.console.api.OuyaController;
import webuiltthiscity.*;

public class WeBuiltThisCity extends Activity {
	GameMain game;
	AndroidMachineInterface machine_interface;
	OuyaController ouya_controller;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		OuyaController.init(this);
		ouya_controller = OuyaController.getControllerByPlayer(0);
		game = new GameMain();
		machine_interface = new AndroidMachineInterface();
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		AndroidGraphicsInterface graphics_interface = new AndroidGraphicsInterface(this,size.x,size.y);
		machine_interface.setMainActivity(this);
		game.setMachineInterface(machine_interface);
		setContentView((View)graphics_interface);
		game.setGraphicsInterface(graphics_interface);
		game.init();
		
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean handled = false;
        //ouya_controller = OuyaController.getControllerByDeviceId(event.getDeviceId());
        if (keyCode == OuyaController.BUTTON_O) {
            machine_interface.buttons[4] = true;
            handled=true;
        }
        return handled || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //ouya_controller = OuyaController.getControllerByDeviceId(event.getDeviceId());
        boolean handled = false;
        if (keyCode == OuyaController.BUTTON_O) {
            machine_interface.buttons[4] = false;
            handled=true;
        }
        return handled || super.onKeyUp(keyCode, event);
    }

}
