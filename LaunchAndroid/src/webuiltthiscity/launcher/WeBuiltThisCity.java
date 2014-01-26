package webuiltthiscity.launcher;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import webuiltthiscity.*;

public class WeBuiltThisCity extends Activity {
	GameMain game;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_we_built_this_city);
		
		game = new GameMain();
		AndroidMachineInterface machine_interface = new AndroidMachineInterface();
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
	
	protected void onDestroy(){
		super.onDestroy();
		//game.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.we_built_this_city, menu);
		return true;
	}

}
