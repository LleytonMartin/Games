package tetris;
import java.awt.Color;

import javax.swing.*;
public class mainClass extends JPanel{
	public static void main(String[]args) {
		JFrame gui = new JFrame();
		drawing DC = new drawing();
		gui.add(DC);
		gui.setVisible(true);
		gui.setSize(700,900);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setTitle("Tetris");
	}

}
