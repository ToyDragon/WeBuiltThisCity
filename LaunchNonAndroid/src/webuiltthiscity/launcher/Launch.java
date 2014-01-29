package webuiltthiscity.launcher;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import webuiltthiscity.*;

public class Launch {
	public static void main(String[] args){
		
		final GameMain game = new GameMain();
		final MachineInterface machine_interface = new NonAndroidMachineInterface();
		final NonAndroidGraphicsInterface graphics_interface = new NonAndroidGraphicsInterface();
		game.setMachineInterface(machine_interface);
		game.setGraphicsInterface(graphics_interface);
		
		machine_interface.setGame(game);
		/* Testing Sync!!!
		 * 
		 *                    _
                  H||
                  H||
        __________H||___________
       [|.......................|
       ||.........## --.#.......|
       ||.........   #  # ......|            @@@@
       ||.........     *  ......|          @@@@@@@
       ||........     -^........|   ,      - @@@@
       ||.....##\        .......|   |     '_ @@@
       ||....#####     /###.....|   |     __\@ \@
       ||....########\ \((#.....|  _\\  (/ ) @\_/)____
       ||..####,   ))/ ##.......|   |(__/ /     /|% #/
       ||..#####      '####.....|    \___/ ----/_|-'/
       ||..#####\____/#####.....|       ,:   '(
       ||...######..######......|       |:     \
       ||.....""""  """"...b'ger|       |:      )
       [|_______________________|       |:      |
              H||_______H||             |_____,_|
              H||________\|              |   / (
              H||       H||              |  /\  )
              H||       H||              (  \| /
             _H||_______H||__            |  /'=.
           H|________________|           '=>/  \
                                        /  \ /|/
                                      ,___/|
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		 
		 
		JFrame frame = new JFrame("We Built This City!");
		graphics_interface.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setPreferredSize(new Dimension(900,600));
		frame.getContentPane().add(new JPanel(){
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g){
				g.drawImage(graphics_interface.buffer,0,0,getWidth(),getHeight(),null);
			}
		});
		
		frame.getContentPane().addMouseListener((NonAndroidMachineInterface)machine_interface);
		frame.getContentPane().addMouseMotionListener((NonAndroidMachineInterface)machine_interface);
		frame.addKeyListener((NonAndroidMachineInterface)machine_interface);
		
		frame.pack();

		int frame_x = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - frame.getSize().getWidth()/2);
		int frame_y = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - frame.getSize().getHeight()/2);
		
		frame.setLocation(frame_x,frame_y);
		
		frame.setVisible(true);
		
		game.init();
	}
}
