package webuiltthiscity.launcher;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import webuiltthiscity.GameMain;
import webuiltthiscity.MachineInterface;
public class NonAndroidMachineInterface implements MachineInterface, MouseMotionListener, MouseListener, KeyListener{
	
	double[] cursor_pos = new double[2];
	boolean[] buttons = new boolean[5];
	String[] button_labels = {"up","down","left","right","jump"};
	JFrame game_frame;
	GameMain game;
	
	public NonAndroidMachineInterface() {
	}
	
	public void setGame(GameMain game){
		this.game = game;
	}
	public double[] getCursor() {
		return cursor_pos;
	}
	public void log(String message) {
		System.out.println(message);
	}
	public boolean[] getButtonStatus() {
		return buttons;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
			buttons[0] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			buttons[1] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			buttons[2] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			buttons[3] = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			buttons[4] = true;
		}
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
			buttons[0] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			buttons[1] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			buttons[2] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			buttons[3] = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			buttons[4] = false;
		}
	}
	public void keyTyped(KeyEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {
		cursor_pos = new double[]{e.getX(),e.getY()};
	}
	public void mouseMoved(MouseEvent e) {
		cursor_pos = new double[]{e.getX(),e.getY()};
	}	
}
