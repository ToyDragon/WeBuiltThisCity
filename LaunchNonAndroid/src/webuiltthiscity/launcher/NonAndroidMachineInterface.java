package webuiltthiscity.launcher;
import java.awt.Graphics;
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
	public double getDirection() {
		return 0;
	}
	public Graphics getGraphics() {
		return null;
	}
	public void updateDisplay() {
		game_frame.repaint();
	}
	public void log(String message) {
		System.out.println(message);
	}
	
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
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
