package webuiltthiscity;

/*
 * Graphics interface will be implemented differently for each system launcher.
 * Once GameMain is initialized, its setGraphicsInterface method will be used
 * 
 * Graphics interfaces are used to interface with the drawing capabilites of 
 * a machine.
 */
public interface GraphicsInterface {
	public void drawImage(String image_name, int top_left_x,int top_left_y);
	public void drawImage(String image_name, int top_left_x,int top_left_y,int width,int height);
	public void translate(int dx,int dy);
	public void fill(int fill_color);
	public void drawText(String text,int top_left_x,int top_left_y);
	public int[] getDrawAreaDimensions();
	public void updateDisplay();
}
