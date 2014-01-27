package webuiltthiscity.launcher;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import webuiltthiscity.*;

public class NonAndroidGraphicsInterface implements GraphicsInterface{

	GameMain game;
	JFrame frame;
	MachineInterface machine_interface;
	public BufferedImage buffer = new BufferedImage(900,600,BufferedImage.TYPE_INT_ARGB);
	HashMap<String, BufferedImage> image_map = new HashMap<String, BufferedImage>();
	
	public NonAndroidGraphicsInterface(){
		try {
			//load all resources
			File image_location = new File("Resources/Images");
			for(File f : image_location.listFiles()){
				if(f.getName().endsWith(".png") || f.getName().endsWith(".bmp")){
					image_map.put(f.getName().substring(0,g.getName().lastIndexOf("\\.")),ImageIO.read(f));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setGame(GameMain game){
		this.game = game;
	}
	public void setFrame(JFrame frame){
		this.frame = frame;
	}
	public void setMachineInterface(MachineInterface machine_interface){
		this.machine_interface = machine_interface;
	}
	
	public int[] getDrawAreaDimensions() {
		return new int[]{frame.getContentPane().getWidth(),frame.getContentPane().getHeight()};
	}
	public void drawImage(String image_name, int x, int y) {
		buffer.getGraphics().drawImage(image_map.get(image_name),x,y,null);
	}
	public void drawImage(String image_name, int x, int y, int w, int h) {
		buffer.getGraphics().drawImage(image_map.get(image_name),x,y,w,h,null);
		
	}
	public void drawText(String text, int x, int y) {
		buffer.getGraphics().drawString(text, x, y);
	}
	public void translate(int x, int y) {
		buffer.getGraphics().translate(x, y);
	}
	public void publish() {
		frame.repaint();
	}
	public void fill(int color) {
		buffer.getGraphics().setColor(new Color(color));
		buffer.getGraphics().fillRect(0, 0, 900, 600);
	}
	public void updateDisplay() {
		frame.repaint();
	}
}
