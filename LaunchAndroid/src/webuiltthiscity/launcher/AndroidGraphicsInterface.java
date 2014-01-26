package webuiltthiscity.launcher;

import java.util.HashMap;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import webuiltthiscity.*;


public class AndroidGraphicsInterface extends android.view.View implements GraphicsInterface{
	
	Bitmap buffer;
	Bitmap buffer1,buffer2;
	Canvas buffer_canvas;
	HashMap<String, Bitmap> bitmap_map = new HashMap<String,Bitmap>();
	int screen_w,screen_h;
	public AndroidGraphicsInterface(Context context,int screen_w,int screen_h) {
		super(context);
		bitmap_map.put("test_image",BitmapFactory.decodeResource(getResources(),R.drawable.test));
		buffer1 = Bitmap.createBitmap(screen_w, screen_h, Bitmap.Config.ARGB_8888);
		buffer2 = Bitmap.createBitmap(screen_w, screen_h, Bitmap.Config.ARGB_8888);
		buffer = buffer1;
		buffer_canvas = new Canvas(buffer);
		this.screen_w = screen_w;
		this.screen_h = screen_h;
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void onDraw(Canvas canvas){
		super.onDraw(canvas);
		android.util.Log.d("WeBuiltThisCity","Drawing!");
		canvas.drawBitmap(buffer, 0, 0, null);
		if(buffer == buffer1)buffer = buffer2;
		else buffer = buffer1;
		buffer_canvas = new Canvas(buffer);
	}

	public synchronized void drawImage(String image_name, int x, int y) {
		// TODO Auto-generated method stub
		buffer_canvas.drawBitmap(bitmap_map.get(image_name), x, y, null);
	}

	public synchronized void drawImage(String image_name, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
		
	}

	public synchronized void drawText(String arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	public int[] getDrawAreaDimensions() {
		// TODO Auto-generated method stub
		return null;
	}

	public void publish() {
		// TODO Auto-generated method stub
		postInvalidate();
	}

	public void translate(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public synchronized void fill(int color) {
		Paint paint = new Paint();
		paint.setColor(color);
		android.util.Log.d("WeBuiltThisCity","" + color);
		buffer_canvas.drawRect(new Rect(0,0,screen_w,screen_h), paint);
	}


}
