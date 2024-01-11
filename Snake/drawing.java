
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.Timer;

public class drawing extends mainClass implements ActionListener, KeyListener {
	Random r = new Random();
	Timer t = new Timer(100,this);
	private boolean repeat = true;
	private boolean repeatChecker = false;
	private int count = 1;
	private int score = 0;
	private PrintWriter out;
	private Scanner in;
	private int randomx = r.nextInt(800);
	private int randomy = r.nextInt(800);
	private int ax = randomx - randomx%30;
	private int ay = randomy - randomy%30;
	private int tempCode=KeyEvent.VK_UP;
	private int dim = 30;
	private int r1x = 600, r1y = 600;
	private ArrayList<Integer> listx = new ArrayList<>(Arrays.asList(r1x));
	private ArrayList<Integer> listy = new ArrayList<>(Arrays.asList(r1y));
	private ArrayList<Integer> listVelX = new ArrayList<>(Arrays.asList(0));
	private ArrayList<Integer> listVelY = new ArrayList<>(Arrays.asList(-dim));
	private ArrayList<String> pos = new ArrayList<>(Arrays.asList("2020"));

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.black);
		Graphics2D g2 = (Graphics2D) g;

		for(int i = 0; i < count; i++) {
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke(4.0f));
			g2.drawRect(listx.get(i), listy.get(i), dim,dim );
			Rectangle rect1 = new Rectangle(listx.get(i),listy.get(i),dim,dim);
			g2.setColor(Color.green);
			g2.fill(rect1);
			pos.set(i, Integer.toString(listx.get(i)/30)+ Integer.toString(listy.get(i)/30));

		}
		Rectangle apple = new Rectangle(ax,ay,dim,dim);

		g2.setColor(Color.red);
		g2.fill(apple);;

	}

	public drawing() {
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		
	}
	public void actionPerformed(ActionEvent e) {

		for(int i = 1; i < pos.size(); i++) {
			if(pos.get(0).contentEquals(pos.get(i))) {

				count = 1;
				listx.clear();
				listy.clear();
				listx.add(0, 600);
				listy.add(0, 600);
				listVelX.clear();
				listVelY.clear();
				pos.clear();
				listVelY.add(0, -dim);
				listVelX.add(0, 0);
				pos.add(0,"2020");
				tempCode=KeyEvent.VK_UP;
			}
		}
		if(listx.get(0) == ax && listy.get(0) == ay) {
			while(repeat == true) {
				score++;
				randomx = r.nextInt(1360);
				randomy = r.nextInt(800);
				ax = randomx - randomx%30;
				ay = randomy - randomy%30;
				String temp = Integer.toString(ax/30) + Integer.toString(ay/30);
				for(int i = 0; i < pos.size(); i++ ) {
					if(temp.contentEquals(pos.get(i))) {
						repeatChecker = true;
						i = pos.size();
					}
					if(repeatChecker == false) {
						repeat = false;
					}
					repeatChecker = false;
				}
			}
			repeat = true;
			
			if(listVelY.get(listVelY.size()-1) == dim ) {
				listx.add(listx.get(listx.size()-1));
				listy.add(listy.get(listy.size()-1)-dim);
				pos.add(count, Integer.toString(listx.get(count)/30)+ Integer.toString(listy.get(count)/30));
				
				listVelX.add(0);
				listVelY.add(dim);
			}
			if(listVelY.get(listVelY.size()-1) == -dim ) {
				listx.add(listx.get(listx.size()-1));
				listy.add(listy.get(listy.size()-1)+dim);
				pos.add(count, Integer.toString(listx.get(count)/30)+ Integer.toString(listy.get(count)/30));
				
				listVelX.add(0);
				listVelY.add(-dim);
			}
			if(listVelX.get(listVelX.size()-1) == dim ) {
				listx.add(listx.get(listx.size()-1)-dim);
				listy.add(listy.get(listy.size()-1));
				pos.add(count, Integer.toString(listx.get(count)/30)+ Integer.toString(listy.get(count)/30));
				
				listVelX.add(dim);
				listVelY.add(0);
			}
			if(listVelX.get(listVelX.size()-1) == -dim ) {
				listx.add(listx.get(listx.size()-1)+dim);
				listy.add(listy.get(listy.size()-1));
				pos.add(count, Integer.toString(listx.get(count)/30)+ Integer.toString(listy.get(count)/30));
				
				listVelX.add(-dim);
				listVelY.add(0);
			}

			count++;

		}
		if(listx.get(0) >= 1330 || listx.get(0) < 0 || listy.get(0) > 870 || listy.get(0) < 0) {
			try {
				in = new Scanner(new FileReader("HighScore.txt"));
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			count = 1;
			pos.clear();
			listx.clear();
			listy.clear();
			listx.add(0, 600);
			listy.add(0, 600);
			pos.add(0,"2020");
			listVelX.clear();
			listVelY.clear();
			listVelY.add(0, -dim);
			listVelX.add(0, 0);
			tempCode=KeyEvent.VK_UP;
			
		}


		
	for(int i = 0; i < listx.size(); i++){	
		listx.set(i, listx.get(i) + listVelX.get(i));
		listy.set(i, listy.get(i) + listVelY.get(i));
	}
	
	for(int i = 1; i < count; i++) {
		if(listx.get(0) == listx.get(i) && listy.get(0) == listy.get(i)) {
			listx.clear();
			listy.clear();
			count = 1;
			listx.add(0, 600);
			listy.add(0, 600);
			listVelX.set(0, 0);
			listVelY.set(0, -dim);
			tempCode=0;	
		}
	}
	
	for(int i = listVelX.size()-1; i > 0; i--) {
		listVelX.set(i, listVelX.get(i-1));
		listVelY.set(i, listVelY.get(i-1));
	}

		repaint();
	}

	public void keyTyped(KeyEvent e) {}
	
	
	public void up() {
		listVelY.set(0, -dim);
		listVelX.set(0, 0);

	}
	public void down() {
		listVelY.set(0, dim);
		listVelX.set(0, 0);
	}
	public void right() {
		listVelY.set(0, 0);
		listVelX.set(0, dim);
	}
	public void left() {
		listVelY.set(0, 0);
		listVelX.set(0, -dim);
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_UP && tempCode != KeyEvent.VK_DOWN ) {
			tempCode = code;
			up();
		}
		if(code == KeyEvent.VK_DOWN &&  tempCode != KeyEvent.VK_UP) {
			tempCode = code;
			down();
		}
		if(code == KeyEvent.VK_LEFT && tempCode != KeyEvent.VK_RIGHT) {
			tempCode = code;
			left();
		}
		if(code == KeyEvent.VK_RIGHT && tempCode != KeyEvent.VK_LEFT) {
			tempCode = code;
			right();
		}
		
	}


	public void keyReleased(KeyEvent e) {
		
	}
}
