package BrickBreaker;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainClass extends JPanel  {
	static int width = 1400, height = 900;
	public static void main(String[]args) {
		
		JFrame gui = new JFrame();
		drawing DC = new drawing();
		gui.add(DC);
		gui.setVisible(true);
		gui.setSize(width,height);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setTitle("Brick Breaker");
	}

}
