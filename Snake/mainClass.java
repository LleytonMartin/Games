import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainClass extends JPanel  {
	public static void main(String[]args) throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader("PrevHS.txt"));
		drawing DC = new drawing();
		JFrame gui = new JFrame();
		gui.add(DC);
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(1360,900);
		gui.setTitle("Snake");
		gui.getContentPane().setBackground(Color.black);
		
		
	}
}
