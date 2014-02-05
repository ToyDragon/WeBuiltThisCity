package webuiltthiscity.launder;

import java.util.HashMap;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import webuiltthiscity.*;


@SuppressLint("ViewConstructor")
public class AndroidGraphicsInterface extends android.view.View implements GraphicsInterface{
	
	Bitmap buffer;
	Bitmap buffer1,buffer2;
	Canvas buffer_canvas;
	Canvas canvas1,canvas2;
	HashMap<String, Bitmap> bitmap_map = new HashMap<String,Bitmap>();
	int screen_w,screen_h;
	public AndroidGraphicsInterface(Context context,int screen_w,int screen_h) {
		super(context);
		bitmap_map.put("test_image",BitmapFactory.decodeResource(getResources(),R.drawable.test_image));
		bitmap_map.put("block",BitmapFactory.decodeResource(getResources(),R.drawable.block));
		bitmap_map.put("baseball_thing",BitmapFactory.decodeResource(getResources(),R.drawable.baseball_thing));
		buffer1 = Bitmap.createBitmap(screen_w, screen_h, Bitmap.Config.ARGB_8888);
		buffer2 = Bitmap.createBitmap(screen_w, screen_h, Bitmap.Config.ARGB_8888);
		buffer = buffer1;
		canvas1 = new Canvas(buffer1);
		canvas2 = new Canvas(buffer2);
		buffer_canvas = canvas1;
		this.screen_w = screen_w;
		this.screen_h = screen_h;
	}
	
	public synchronized void onDraw(Canvas canvas){
		super.onDraw(canvas);
		//android.util.Log.d("WeBuiltThisCity","Drawing!");
		canvas.drawBitmap(buffer, 0, 0, null);
		if(buffer == buffer1){
			buffer = buffer2;
			buffer_canvas = canvas2;
		}
		else{
			buffer = buffer1;
			buffer_canvas = canvas1;
		}
	}

	public synchronized void drawImage(String image_name, int x, int y) {
		buffer_canvas.drawBitmap(bitmap_map.get(image_name), x, y, null);
	}

	public synchronized void drawImage(String image_name, int x, int y, int w, int h) {
		//buffer_canvas.drawBitmap(bitmap_map.get(image_name), new Rect(0,0,bitmap_map.get(image_name).getWidth(),bitmap_map.get(image_name).getHeight()), new Rect(x,y,w,h), null);
		//buffer_canvas.drawBitmap(bitmap_map.get(image_name), null, new Rect(x,y,w,h), null);
		buffer_canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap_map.get(image_name),w,h,false), x, y, null);
		
	}

	public synchronized void drawText(String text, int x, int y) {
		Paint p = new Paint();
		buffer_canvas.drawText(text, 0, text.length(), x, y, p);
	}	

	public int[] getDrawAreaDimensions() {
		return new int[]{screen_w,screen_h};
	}

	public void updateDisplay() {
		postInvalidate();
	}

	public void translate(int x, int y) {
		canvas1.translate(x, y);
		canvas2.translate(x, y);
	}

	public synchronized void fill(int color) {
		Paint paint = new Paint();
		paint.setColor(color | 0xff000000);
		android.util.Log.d("WeBuiltThisCity","Color: " + color);
		buffer_canvas.drawRect(new Rect(0,0,screen_w,screen_h), paint);
	}
}
