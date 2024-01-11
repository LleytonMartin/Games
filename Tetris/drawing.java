package tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class drawing extends mainClass implements ActionListener,KeyListener, MouseMotionListener, MouseListener{
	Timer t = new Timer(5,this);
	public int timeCounter = 5;
	int strokeWidth = 4;
	Random r = new Random();
	int[][] colorBoard = new int[20][10];
	ArrayList<Integer> pos = new ArrayList<Integer>();
	ArrayList<Integer> color = new ArrayList<Integer>();
	boolean[][] active = new boolean[20][10];
	ArrayList<Integer> blockOrder = new ArrayList<Integer>();
	Boolean spawn = true;
	int blockSpawnCount = 0;
	boolean repaint = true;
	boolean repaint1 = true;
	boolean repaint2 = true;
	int state = 0;
	int levels = 1;
	int timeDrop = 100-((levels-1)*50);
	int linesCleared = 0;
	
	public void paintComponent(Graphics g) {
		if(spawn == true) {
			state = 0;
			repaint = true;
			if(blockOrder.get(blockSpawnCount) == 2) {
				colorBoard[0][5] = 2;
				colorBoard[0][6] = 2;
				colorBoard[1][5] = 2;
				colorBoard[1][6] = 2;
				active[0][5] = true;
				active[0][6] = true;
				active[1][5] = true;
				active[1][6] = true;
				
			}else if(blockOrder.get(blockSpawnCount) == 3) {
				colorBoard[0][4] = 3;
				colorBoard[0][5] = 3;
				colorBoard[0][6] = 3;
				colorBoard[0][7] = 3;
				active[0][4] = true;
				active[0][5] = true;
				active[0][6] = true;
				active[0][7] = true;
				
			}else if(blockOrder.get(blockSpawnCount) == 4) {
				colorBoard[0][4] = 4;
				colorBoard[0][5] = 4;
				colorBoard[0][6] = 4;
				colorBoard[1][5] = 4;
				active[0][4] = true;
				active[0][5] = true;
				active[0][6] = true;
				active[1][5] = true;
			}else if(blockOrder.get(blockSpawnCount) == 5) {
				colorBoard[0][6] = 5;
				colorBoard[0][5] = 5;
				colorBoard[1][5] = 5;
				colorBoard[1][4] = 5;
				active[0][6] = true;
				active[0][5] = true;
				active[1][5] = true;
				active[1][4] = true;
			}else if(blockOrder.get(blockSpawnCount) == 6) {
				colorBoard[0][4] = 6;
				colorBoard[0][5] = 6;
				colorBoard[1][5] = 6;
				colorBoard[1][6] = 6;
				active[0][4] = true;
				active[0][5] = true;
				active[1][5] = true;
				active[1][6] = true;
			}else if(blockOrder.get(blockSpawnCount) == 7) {
				colorBoard[0][4] = 7;
				colorBoard[0][5] = 7;
				colorBoard[0][6] = 7;
				colorBoard[1][6] = 7;
				active[0][4] = true;
				active[0][5] = true;
				active[0][6] = true;
				active[1][6] = true;
			}else if(blockOrder.get(blockSpawnCount) == 8) {
				colorBoard[0][4] = 8;
				colorBoard[0][5] = 8;
				colorBoard[0][6] = 8;
				colorBoard[1][4] = 8;
				active[0][4] = true;
				active[0][5] = true;
				active[0][6] = true;
				active[1][4] = true;
			}

			spawn = false;
			blockSpawnCount++;
		}
		Rectangle2D.Double rect = new Rectangle2D.Double();
		super.paintComponent(g);
		setBackground(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		for(int j = 0; j < 20; j++) {
		for(int i = 0; i < 10;i++) {
			if(colorBoard[j][i] == 0) {
				g2.setColor(Color.BLACK);
			}else if(colorBoard[j][i] == 1) {
				g2.setColor(Color.DARK_GRAY);
			}else if(colorBoard[j][i] == 2) {
				g2.setColor(Color.YELLOW);
			}else if(colorBoard[j][i] == 3) {
				g2.setColor(Color.CYAN);
			}else if(colorBoard[j][i] == 4) {
				g2.setColor(new Color(114, 11, 152));
			}else if(colorBoard[j][i] == 5) {
				g2.setColor(Color.RED);
			}else if(colorBoard[j][i] == 6) {
				g2.setColor(Color.GREEN);
			}else if(colorBoard[j][i] == 7) {
				g2.setColor(Color.BLUE);
			}else if(colorBoard[j][i] == 8) {
				g2.setColor(Color.ORANGE);
			}
			rect = new Rectangle2D.Double(150 + i * 30, 100 + j*30, 30, 30);
			g2.fill(rect);

			if(colorBoard[j][i] > 1) {
				g2.setColor(Color.BLACK);
				g2.drawRect(150 + i * 30, 100 + j*30, 30, 30);
				g2.setColor(Color.WHITE);
				g2.fillRect(1+150 + i * 30, 1+100 + j*30, 3, 3);
				g2.fillRect(1+150 + i * 30+3, 1+100 + j*30, 3, 3);
				g2.fillRect(1+150 + i * 30, 1+100 + j*30+3, 3, 3);

			}
		}
		}
		g2.setColor(new Color(255,255,255));
		g2.setStroke(new BasicStroke(strokeWidth));
		

		/*
		 * 
		 * Old Line Method to Create Rectangle
		//left
		g2.drawLine(100 - strokeWidth/2, 100, 100-strokeWidth/2, 100+(int)rect.getWidth()*20);
		//right
		g2.drawLine((int)(100+rect.getWidth()*10)+strokeWidth/2, 100, (int)(100+rect.getWidth()*10)+strokeWidth/2, 100+(int)rect.getHeight()*20);
		//top
		g2.drawLine(100-strokeWidth/2, 100-strokeWidth/2, 100+(int)(rect.getWidth()*10)+strokeWidth/2, 100-strokeWidth/2);
		//bottom
		g2.drawLine(100-strokeWidth/2,100+(int)rect.getHeight()*20 +strokeWidth/2, 100+(int)(rect.getWidth()*10)+strokeWidth/2, 100+(int)rect.getHeight()*20 +strokeWidth/2);
		*/
		//Outside Layer
		g2.drawRect(150-strokeWidth/2, 100-strokeWidth/2,  (int)(rect.getWidth()*10)+strokeWidth, (int)rect.getWidth()*20+strokeWidth);
		Font stringFont = new Font( "SansSerif", Font.PLAIN, 18 );
		g2.setFont(stringFont);
		g2.drawString("Next",(int)(150-strokeWidth/2 + rect.getWidth()*10+strokeWidth+rect.getWidth()) , 75);
		for(int i = 0; i < 5; i++) {
		//Right Boxes
		g2.drawRect((int)(150-strokeWidth/2 + rect.getWidth()*10+strokeWidth+rect.getWidth()),(int)(100 + i *rect.getWidth()*4), ((int)(rect.getWidth()*10)+strokeWidth)/3, (int)(rect.getHeight()*3));
		}
		//Left Box
		g2.drawRect((int)(150-rect.getWidth()-((int)(rect.getWidth()*10)+strokeWidth)/3), (int)(100-strokeWidth/2), ((int)(rect.getWidth()*10)+strokeWidth)/3, ((int)rect.getWidth()*20+strokeWidth)/6);	
	}
	
	public drawing() {
		t.start();
		addKeyListener(this);
		setFocusable(true);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusTraversalKeysEnabled(false);
		//first two random blocks
		for(int i = 0; i < 6; i++) {
			blockOrder.add(r.nextInt(7)+2);
		}
		//Random Color Generator()
		for(int j = 0; j < 20; j++) {
		for(int i = 0; i < 10;i++) {
			if(i%2 == 0 && j%2 == 0) {
			colorBoard[j][i] = 0;
			}else if(i%2 == 1 && j%2 == 0){
			colorBoard[j][i] = 1;
			}else if(i%2 == 0 && j%2 == 1) {
			colorBoard[j][i] = 1;	
			}else {
			colorBoard[j][i] = 0;
			}
		}
		}

		
	}
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 1; i < blockOrder.size(); i++) {
			
		}


		if(timeCounter%timeDrop == 0) {
			for(int j = 0; j <20; j++) {
				for(int i = 0;i < 10; i++) {					
					if(colorBoard[j][i] > 1 && active[j][i] == true) {
						pos.add(j*10+i);
						color.add(colorBoard[j][i]);
					}
				}
			}
			
		
		repaint = true;
		for(int i = 0; i < pos.size();i++) {
			if(pos.get(i)/10 >= 19 && timeCounter%timeDrop == 0) {
				state = 0;
				repaint = false;
				spawn = true;
				i = pos.size();
				blockOrder.add(r.nextInt(7)+2);
			}else 
			if(i < pos.size() && colorBoard[pos.get(i)/10+1][pos.get(i)%10] > 1 && active[pos.get(i)/10+1][pos.get(i)%10] == false && timeCounter%timeDrop == 0) {
				state = 0;
				repaint = false;
				spawn = true;
				blockOrder.add(r.nextInt(7)+2);

				i = pos.size();
			}
		}
		active = new boolean[20][10];

		if(repaint == true) {
		
		for(int i = 0; i < pos.size(); i++) {
			//Top search
			if(pos.get(i)/10 - 1 > -1  ) {
				if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 0){
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
				}else if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 1) {
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
				}
			}
			//Bottom Search
			if(pos.get(i)/10 + 1 < 20) {
				if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 0){
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
				}else if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 1) {
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
				}
			}
			//Left Search
			if(pos.get(i)%10 - 1 > -1) {
				if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 0){
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
				}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 1) {
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
				}
			}
			//Right Search
			if(pos.get(i)%10 + 1 < 10) {
				if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 0){
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
				}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 1) {
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
				}
			}
		}


		for(int i = 0; i < pos.size(); i++) {
		colorBoard[pos.get(i)/10 + 1][pos.get(i)%10]  = color.get(i);
		active[pos.get(i)/10 + 1][pos.get(i)%10] = true;
		}
		}
		pos.clear();
		color.clear();		
		}
		
		ArrayList<Integer> lineList = new ArrayList<Integer>();
	boolean pass = true;
for(int j = 0 ; j < active.length;j++) {
	for(int i = 0; i < active[0].length;i++) {
		if(active[j][i] == true) {
			pass = false;
		}
	}
}
if(pass == true) {
		for(int j = 4; j < 20; j++) {
			boolean lineCheck = true;
			for(int i = 0; i < 10; i++) {
				if(colorBoard[j][i] <= 1) {
					lineCheck = false;
				}
			}
			if(lineCheck == true) {
				System.out.println("Full Line");
				lineList.add(j);
				System.out.println(j);
			}
		}
		for(int i = 0; i < lineList.size(); i++) {
			linesCleared++;
			System.out.println("Lines Cleared: " + linesCleared + " Requirement: " + levels*10 );
			for(int j = lineList.get(i); j >= 4; j--) {
				for(int k = 0; k < 10; k++) {
						if(k%2 == 0 && j%2 == 0) {
						colorBoard[j][k] = 0;
						}else if(k%2 == 0 && j%2 == 1) {
						colorBoard[j][k] = 1;	
						}else if(k%2 == 1 && j%2 == 0) {
							colorBoard[j][k] = 1;	
						}else if(k%2 == 1 && j%2 == 1) {
								colorBoard[j][k] = 0;	
							}			
					if(colorBoard[j-1][k] > 1) {
						colorBoard[j][k] = colorBoard[j-1][k];
					}
				}
			}
		}
		if(linesCleared >= levels * 10 ) {
			levels++;
			linesCleared = linesCleared%10;
		}
		lineList.clear();

}
		timeCounter +=5;
		
		
		
		repaint();
		
	}
	
	
	

	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		repaint = true;
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_RIGHT) {
			for(int j = 0; j < 20; j++) {
				for(int i = 0;i < 10; i++) {	
					if(colorBoard[j][i] > 1 && active[j][i] == true) {
						pos.add(j*10+i);
						color.add(colorBoard[j][i]);
					}
				}
			}
			
		

		for(int i = 0; i < pos.size();i++) {
			if(pos.get(i)%10 >= 9 ) {
				repaint = false;
			}else if(colorBoard[pos.get(i)/10][pos.get(i)%10+1] > 1 && active[pos.get(i)/10][pos.get(i)%10+1] == false) {
				repaint = false;
			}
			
		}
		
		
		if(repaint == true) {
			active = new boolean[20][10];
			for(int i = 0; i < pos.size(); i++) {
				//Top search
				if(pos.get(i)/10 - 1 > -1) {
					if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
				//Bottom Search
				if(pos.get(i)/10 + 1 < 20) {
					if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
				//Left Search
				if(pos.get(i)%10 - 1 > -1) {
					if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
				//Right Search
				if(pos.get(i)%10 + 1 < 10) {
					if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
			}


			for(int i = 0; i < pos.size(); i++) {
			colorBoard[pos.get(i)/10][pos.get(i)%10 + 1]  = color.get(i);
			active[pos.get(i)/10][pos.get(i)%10 + 1] = true;
			}			
		}
		}
		
		if(code == KeyEvent.VK_LEFT) {
			for(int j = 0; j <20; j++) {
				for(int i = 0;i < 10; i++) {	
					if(colorBoard[j][i] > 1 && active[j][i] == true) {
						pos.add(j*10+i);
						color.add(colorBoard[j][i]);
					}
				}
			}
			
		

		repaint = true;
		for(int i = 0; i < pos.size();i++) {
			if(pos.get(i)%10 <= 0 ) {				
				repaint = false;
			}else if(colorBoard[pos.get(i)/10][pos.get(i)%10-1] > 1 && active[pos.get(i)/10][pos.get(i)%10-1] == false ) {
				repaint = false;
			}
		}
		
		if(repaint == true) {
			active = new boolean[20][10];
			for(int i = 0; i < pos.size(); i++) {
				//Top search
				if(pos.get(i)/10 - 1 > -1) {
					if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
				//Bottom Search
				if(pos.get(i)/10 + 1 < 20) {
					if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
				//Left Search
				if(pos.get(i)%10 - 1 > -1) {
					if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
				//Right Search
				if(pos.get(i)%10 + 1 < 10) {
					if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
			}


			for(int i = 0; i < pos.size(); i++) {
			colorBoard[pos.get(i)/10][pos.get(i)%10 - 1]  = color.get(i);
			active[pos.get(i)/10][pos.get(i)%10 - 1] = true;
			}			
		}
		}
		
		pos.clear();
		color.clear();
		if(code == KeyEvent.VK_DOWN) {
			timeDrop = 50;
		}
		
		if( code == KeyEvent.VK_UP) {


			repaint = true;
			for(int j = 0; j < 20; j++) {
				for(int i = 0;i < 10; i++) {	
					if(colorBoard[j][i] > 1 && active[j][i] == true) {
						pos.add(j*10+i);
						color.add(colorBoard[j][i]);
					}
				}
			}
			
			//Straight Line Spin 3
			
			if(color.get(0) == 3) {
				if(pos.get(1)/10 == 0) {
					timeCounter -= timeCounter%timeDrop;
					for(int i = 0; i < pos.size(); i++) {
						if(pos.get(i)%2 == 0) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}else {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;	
						}
						pos.set(i,pos.get(i)+20);
						colorBoard[pos.get(i)/10][pos.get(i)%10]  = color.get(0);

					}
				}else if(pos.get(1)/10 == 1) {
					timeCounter -= timeCounter%timeDrop;
					for(int i = 0; i < pos.size(); i++) {
						if(pos.get(i)%2 == 0) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}else {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;	
						}
						pos.set(i,pos.get(i)+10);
						colorBoard[pos.get(i)/10][pos.get(i)%10]  = color.get(0);

					}
				}
				repaint1 = true;
				repaint2 = false;
			for(int i = 0; i < pos.size();i++) {
				if(pos.get(0)%10 == 0 && state == 1 || pos.get(0)%10 == 9 && state == 3 ||
						pos.get(0)%10 == 9 && state == 1 || pos.get(0)%10 == 0 && state == 3 ) {
					repaint2 = true;
				}
			}
			

			if(repaint1 == true) {
				active = new boolean[20][10];
				for(int i = 0; i < pos.size(); i++) {
					//Top search
					if(pos.get(i)/10 - 1 > -1) {
						if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Bottom Search
					if(pos.get(i)/10 + 1 < 21) {
						if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Left Search
					if(pos.get(i)%10 - 1 > -1) {
						if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Right Search
					if(pos.get(i)%10 + 1 < 10) {
						if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
				}

				if(state == 0) {
					state = 1;
				colorBoard[pos.get(0)/10-2][pos.get(0)%10+2] = color.get(0);
				active[pos.get(0)/10-2][pos.get(0)%10+2] = true;
				
				colorBoard[pos.get(1)/10-1][pos.get(1)%10+1] = color.get(1);
				active[pos.get(1)/10-1][pos.get(1)%10+1] = true;
			
				colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
				active[pos.get(2)/10][pos.get(2)%10] = true;
				
				colorBoard[pos.get(3)/10+1][pos.get(3)%10-1] = color.get(3);
				active[pos.get(3)/10+1][pos.get(3)%10-1] = true;

				}else if(state == 1 && repaint2 == false) {
					state = 2;
				colorBoard[pos.get(0)/10+2][pos.get(0)%10-2] = color.get(0);
				active[pos.get(0)/10+2][pos.get(0)%10-2] = true;
				
				colorBoard[pos.get(1)/10+1][pos.get(1)%10-1] = color.get(1);
				active[pos.get(1)/10+1][pos.get(1)%10-1] = true;
			
				colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
				active[pos.get(2)/10][pos.get(2)%10] = true;
				
				colorBoard[pos.get(3)/10-1][pos.get(3)%10+1] = color.get(3);
				active[pos.get(3)/10-1][pos.get(3)%10+1] = true;
					
				}else if(state == 1 && repaint2 == true) {
					if(pos.get(1)%10 == 0) {
					state = 2;
				colorBoard[pos.get(0)/10+2][pos.get(0)%10] = color.get(0);
				active[pos.get(0)/10+2][pos.get(0)%10] = true;
				
				colorBoard[pos.get(1)/10+1][pos.get(1)%10+1] = color.get(1);
				active[pos.get(1)/10+1][pos.get(1)%10+1] = true;
			
				colorBoard[pos.get(2)/10][pos.get(2)%10+2] = color.get(2);
				active[pos.get(2)/10][pos.get(2)%10+2] = true;
				
				colorBoard[pos.get(3)/10-1][pos.get(3)%10+3] = color.get(3);
				active[pos.get(3)/10-1][pos.get(3)%10+3] = true;
					}else {
					state = 2;
				colorBoard[pos.get(0)/10+2][pos.get(0)%10] = color.get(0);
				active[pos.get(0)/10+2][pos.get(0)%10] = true;
				
				colorBoard[pos.get(1)/10+1][pos.get(1)%10-1] = color.get(1);
				active[pos.get(1)/10+1][pos.get(1)%10-1] = true;
					
				colorBoard[pos.get(2)/10][pos.get(2)%10-2] = color.get(2);
				active[pos.get(2)/10][pos.get(2)%10-2] = true;
						
				colorBoard[pos.get(3)/10-1][pos.get(3)%10-3] = color.get(3);
				active[pos.get(3)/10-1][pos.get(3)%10-3] = true;			
					}
					
				}else if(state == 2) {
					state = 3;
					colorBoard[pos.get(0)/10-2][pos.get(0)%10+1] = color.get(0);
					active[pos.get(0)/10-2][pos.get(0)%10+1] = true;
					
					colorBoard[pos.get(1)/10-1][pos.get(1)%10] = color.get(1);
					active[pos.get(1)/10-1][pos.get(1)%10] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10-1] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10-1] = true;
					
					colorBoard[pos.get(3)/10+1][pos.get(3)%10-2] = color.get(3);
					active[pos.get(3)/10+1][pos.get(3)%10-2] = true;
				}else if(state == 3 && repaint2 == false) {
					state = 0;
					colorBoard[pos.get(0)/10+2][pos.get(0)%10-1] = color.get(0);
					active[pos.get(0)/10+2][pos.get(0)%10-1] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10+1] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10+1] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10+2] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10+2] = true;
				}else if(state == 3 && repaint2 == true) {
					if(pos.get(1)%10 == 0) {
					state = 0;
				colorBoard[pos.get(0)/10+2][pos.get(0)%10] = color.get(0);
				active[pos.get(0)/10+2][pos.get(0)%10] = true;
				
				colorBoard[pos.get(1)/10+1][pos.get(1)%10+1] = color.get(1);
				active[pos.get(1)/10+1][pos.get(1)%10+1] = true;
			
				colorBoard[pos.get(2)/10][pos.get(2)%10+2] = color.get(2);
				active[pos.get(2)/10][pos.get(2)%10+2] = true;
				
				colorBoard[pos.get(3)/10-1][pos.get(3)%10+3] = color.get(3);
				active[pos.get(3)/10-1][pos.get(3)%10+3] = true;
					}else {
					state = 0;
				colorBoard[pos.get(0)/10+2][pos.get(0)%10] = color.get(0);
				active[pos.get(0)/10+2][pos.get(0)%10] = true;
				
				colorBoard[pos.get(1)/10+1][pos.get(1)%10-1] = color.get(1);
				active[pos.get(1)/10+1][pos.get(1)%10-1] = true;
					
				colorBoard[pos.get(2)/10][pos.get(2)%10-2] = color.get(2);
				active[pos.get(2)/10][pos.get(2)%10-2] = true;
						
				colorBoard[pos.get(3)/10-1][pos.get(3)%10-3] = color.get(3);
				active[pos.get(3)/10-1][pos.get(3)%10-3] = true;			
					}
				}
			}
			

			
			}
			
			// T Spin (4)	

			if(color.get(0) == 4) {		
			repaint1 = true;
			repaint2 = false;
		for(int i = 0; i < pos.size();i++) {
			if(pos.get(1)%10 == 0 && state == 1 || pos.get(2)%10 == 9 && state == 3 ) {
				repaint2 = true;
			}
		}
		if(pos.get(1)/10 == 0) {
			timeCounter -= timeCounter%timeDrop;
			for(int i = 0; i < pos.size(); i++) {
				if(pos.get(i)%2 == 0) {
				colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
				}else {
				colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;	
				}
				pos.set(i,pos.get(i)+10);
				colorBoard[pos.get(i)/10][pos.get(i)%10]  = color.get(0);

			}
		}

		if(pos.get(0)/10 == 19 || pos.get(2)/10 == 19 || pos.get(3)/10 == 19 ) {
			timeCounter -= timeCounter%timeDrop;
		}
		

		if(repaint1 == true) {
			active = new boolean[20][10];
			for(int i = 0; i < pos.size(); i++) {
				//Top search
				if(pos.get(i)/10 - 1 > -1) {
					if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
				//Bottom Search
				if(pos.get(i)/10 + 1 < 20) {
					if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
				//Left Search
				if(pos.get(i)%10 - 1 > -1) {
					if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
				//Right Search
				if(pos.get(i)%10 + 1 < 10) {
					if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 0){
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
					}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 1) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}
				}
			}

			if(state == 0) {
				state = 1;
			colorBoard[pos.get(0)/10+1][pos.get(0)%10+1] = color.get(0);
			active[pos.get(0)/10+1][pos.get(0)%10+1] = true;
			
			colorBoard[pos.get(1)/10][pos.get(1)%10] = color.get(1);
			active[pos.get(1)/10][pos.get(1)%10] = true;
		
			colorBoard[pos.get(2)/10-1][pos.get(2)%10-1] = color.get(2);
			active[pos.get(2)/10-1][pos.get(2)%10-1] = true;
			
			colorBoard[pos.get(3)/10-1][pos.get(3)%10+1] = color.get(3);
			active[pos.get(3)/10-1][pos.get(3)%10+1] = true;

			}else if(state == 1 && repaint2 == false) {
				state = 2;
				colorBoard[pos.get(0)/10+1][pos.get(0)%10-1] = color.get(0);
				active[pos.get(0)/10+1][pos.get(0)%10-1] = true;
				
				colorBoard[pos.get(1)/10][pos.get(1)%10] = color.get(1);
				active[pos.get(1)/10][pos.get(1)%10] = true;
			
				colorBoard[pos.get(2)/10-1][pos.get(2)%10-1] = color.get(2);
				active[pos.get(2)/10-1][pos.get(2)%10-1] = true;
				
				colorBoard[pos.get(3)/10-1][pos.get(3)%10+1] = color.get(3);
				active[pos.get(3)/10-1][pos.get(3)%10+1] = true;
				
			}else if(state == 1 && repaint2 == true) {
				state = 2;
				colorBoard[pos.get(0)/10+1][pos.get(0)%10] = color.get(0);
				active[pos.get(0)/10+1][pos.get(0)%10] = true;
				
				colorBoard[pos.get(1)/10][pos.get(1)%10+1] = color.get(1);
				active[pos.get(1)/10][pos.get(1)%10+1] = true;
			
				colorBoard[pos.get(2)/10-1][pos.get(2)%10] = color.get(2);
				active[pos.get(2)/10-1][pos.get(2)%10] = true;
				
				colorBoard[pos.get(3)/10-1][pos.get(3)%10+2] = color.get(3);
				active[pos.get(3)/10-1][pos.get(3)%10+2] = true;
				
			}else if(state == 2) {
				state = 3;
				colorBoard[pos.get(0)/10][pos.get(0)%10] = color.get(0);
				active[pos.get(0)/10][pos.get(0)%10] = true;
				
				colorBoard[pos.get(1)/10][pos.get(1)%10] = color.get(1);
				active[pos.get(1)/10][pos.get(1)%10] = true;
			
				colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
				active[pos.get(2)/10][pos.get(2)%10] = true;
				
				colorBoard[pos.get(3)/10+1][pos.get(3)%10-1] = color.get(3);
				active[pos.get(3)/10+1][pos.get(3)%10-1] = true;
			}else if(state == 3 && repaint2 == false) {
				state = 0;
				colorBoard[pos.get(0)/10+1][pos.get(0)%10-1] = color.get(0);
				active[pos.get(0)/10+1][pos.get(0)%10-1] = true;
				
				colorBoard[pos.get(1)/10][pos.get(1)%10+1] = color.get(1);
				active[pos.get(1)/10][pos.get(1)%10+1] = true;
			
				colorBoard[pos.get(2)/10][pos.get(2)%10+1] = color.get(2);
				active[pos.get(2)/10][pos.get(2)%10+1] = true;
				
				colorBoard[pos.get(3)/10][pos.get(3)%10] = color.get(3);
				active[pos.get(3)/10][pos.get(3)%10] = true;
			}else if(state == 3 && repaint2 == true) {
				state = 0;
				colorBoard[pos.get(0)/10+1][pos.get(0)%10-2] = color.get(0);
				active[pos.get(0)/10+1][pos.get(0)%10-2] = true;
				
				colorBoard[pos.get(1)/10+1][pos.get(1)%10] = color.get(1);
				active[pos.get(1)/10+1][pos.get(1)%10] = true;
			
				colorBoard[pos.get(2)/10][pos.get(2)%10-1] = color.get(2);
				active[pos.get(2)/10][pos.get(2)%10-1] = true;
				
				colorBoard[pos.get(3)/10-1][pos.get(3)%10] = color.get(3);
				active[pos.get(3)/10-1][pos.get(3)%10] = true;
			}
		}
		
		
			
		}
			// S Red Spin
			if(color.get(0) == 5) {
				repaint1 = true;
				repaint2 = false;
/*			for(int i = 0; i < pos.size();i++) {
				if(pos.get(1)%10 == 0 && state == 1 || pos.get(2)%10 == 9 && state == 3 ) {
					repaint2 = true;
				}
			}
			*/
			if(repaint1 == true) {
				active = new boolean[20][10];
				for(int i = 0; i < pos.size(); i++) {
					//Top search
					if(pos.get(i)/10 - 1 > -1) {
						if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Bottom Search
					if(pos.get(i)/10 + 1 < 20) {
						if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Left Search
					if(pos.get(i)%10 - 1 > -1) {
						if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Right Search
					if(pos.get(i)%10 + 1 < 10) {
						if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
				}

				if(state == 0) {
					state = 1;
				colorBoard[pos.get(0)/10][pos.get(0)%10-1] = color.get(0);
				active[pos.get(0)/10][pos.get(0)%10-1] = true;
				
				colorBoard[pos.get(1)/10+1][pos.get(1)%10-2] = color.get(1);
				active[pos.get(1)/10+1][pos.get(1)%10-2] = true;
			
				colorBoard[pos.get(2)/10][pos.get(2)%10+1] = color.get(2);
				active[pos.get(2)/10][pos.get(2)%10+1] = true;
				
				colorBoard[pos.get(3)/10+1][pos.get(3)%10] = color.get(3);
				active[pos.get(3)/10+1][pos.get(3)%10] = true;

				}else if(state == 1) {
					state = 2;
					colorBoard[pos.get(0)/10+2][pos.get(0)%10] = color.get(0);
					active[pos.get(0)/10+2][pos.get(0)%10] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10+1] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10+1] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10+1] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10+1] = true;
					
				}else if(state == 2) {
					state = 3;
					colorBoard[pos.get(0)/10][pos.get(0)%10] = color.get(0);
					active[pos.get(0)/10][pos.get(0)%10] = true;
					
					colorBoard[pos.get(1)/10-1][pos.get(1)%10-2] = color.get(1);
					active[pos.get(1)/10-1][pos.get(1)%10-2] = true;
				
					colorBoard[pos.get(2)/10-1][pos.get(2)%10] = color.get(2);
					active[pos.get(2)/10-1][pos.get(2)%10] = true;
					
					colorBoard[pos.get(3)/10][pos.get(3)%10] = color.get(3);
					active[pos.get(3)/10][pos.get(3)%10] = true;
				}else if(state == 3) {
					state = 0;
					colorBoard[pos.get(0)/10][pos.get(0)%10+1] = color.get(0);
					active[pos.get(0)/10][pos.get(0)%10+1] = true;
					
					colorBoard[pos.get(1)/10-1][pos.get(1)%10+2] = color.get(1);
					active[pos.get(1)/10-1][pos.get(1)%10+2] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10-1] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10-1] = true;
				}
			}
			}
			
			//Z Green Spin
			if(color.get(0) == 6) {
				repaint1 = true;
				repaint2 = false;
			for(int i = 0; i < pos.size();i++) {
				if(pos.get(1)%10 == 0 && state == 1 || pos.get(2)%10 == 9 && state == 3 ) {
					repaint2 = true;
				}
			}
			if(pos.get(1)/10 == 0) {
				timeCounter -= timeCounter%timeDrop + 5;
				for(int i = 0; i < pos.size(); i++) {
					if(pos.get(i)%2 == 0 && pos.get(i)/10 == 0) {
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}else if(pos.get(i)%2 == 0 && pos.get(i)/10 == 1) {
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;	
					}else if(pos.get(i)%2 == 1 && pos.get(i)/10 == 0) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;	
					}else if(pos.get(i)%2 == 1 && pos.get(i)/10 == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;	
						}
					pos.set(i,pos.get(i)+10);
					colorBoard[pos.get(i)/10][pos.get(i)%10]  = color.get(0);

				}
			}
			
			if(repaint1 == true) {
				active = new boolean[20][10];
				for(int i = 0; i < pos.size(); i++) {
					//Top search
					if(pos.get(i)/10 - 1 > -1) {
						if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Bottom Search
					if(pos.get(i)/10 + 1 < 20) {
						if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Left Search
					if(pos.get(i)%10 - 1 > -1) {
						if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Right Search
					if(pos.get(i)%10 + 1 < 10) {
						if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
				}
				if(state == 0) {
					state = 1;
				colorBoard[pos.get(0)/10+1][pos.get(0)%10+1] = color.get(0);
				active[pos.get(0)/10+1][pos.get(0)%10+1] = true;
				
				colorBoard[pos.get(1)/10][pos.get(1)%10] = color.get(1);
				active[pos.get(1)/10][pos.get(1)%10] = true;
			
				colorBoard[pos.get(2)/10-1][pos.get(2)%10+1] = color.get(2);
				active[pos.get(2)/10-1][pos.get(2)%10+1] = true;
				
				colorBoard[pos.get(3)/10-2][pos.get(3)%10] = color.get(3);
				active[pos.get(3)/10-2][pos.get(3)%10] = true;

				}else if(state == 1 && repaint2 == false) {
					state = 2;
					colorBoard[pos.get(0)/10][pos.get(0)%10-2] = color.get(0);
					active[pos.get(0)/10][pos.get(0)%10-2] = true;
					
					colorBoard[pos.get(1)/10][pos.get(1)%10] = color.get(1);
					active[pos.get(1)/10][pos.get(1)%10] = true;
				
					colorBoard[pos.get(2)/10-1][pos.get(2)%10-1] = color.get(2);
					active[pos.get(2)/10-1][pos.get(2)%10-1] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10+1] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10+1] = true;
					
				}else if(state == 1 && repaint2 == true) {
					state = 2;
					colorBoard[pos.get(0)/10][pos.get(0)%10-1] = color.get(0);
					active[pos.get(0)/10][pos.get(0)%10-1] = true;
					
					colorBoard[pos.get(1)/10][pos.get(1)%10+1] = color.get(1);
					active[pos.get(1)/10][pos.get(1)%10+1] = true;
				
					colorBoard[pos.get(2)/10-1][pos.get(2)%10] = color.get(2);
					active[pos.get(2)/10-1][pos.get(2)%10] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10+2] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10+2] = true;
					
				}else if(state == 2) {
					state = 3;
					colorBoard[pos.get(0)/10+2][pos.get(0)%10] = color.get(0);
					active[pos.get(0)/10+2][pos.get(0)%10] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10-1] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10-1] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10-1] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10-1] = true;
				}else if(state == 3 && repaint2 == false) {
					state = 0;
					colorBoard[pos.get(0)/10+1][pos.get(0)%10-1] = color.get(0);
					active[pos.get(0)/10+1][pos.get(0)%10-1] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10+1] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10+1] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10] = true;
					
					colorBoard[pos.get(3)/10][pos.get(3)%10+2] = color.get(3);
					active[pos.get(3)/10][pos.get(3)%10+2] = true;
				}else if(state == 3 && repaint2 == true) {
					state = 0;
					colorBoard[pos.get(0)/10+1][pos.get(0)%10-2] = color.get(0);
					active[pos.get(0)/10+1][pos.get(0)%10-2] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10-1] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10-1] = true;
					
					colorBoard[pos.get(3)/10][pos.get(3)%10+1] = color.get(3);
					active[pos.get(3)/10][pos.get(3)%10+1] = true;
				}
			}
			}
			
			// Blue J Spin
			if(color.get(0) == 7) {
				repaint1 = true;
				repaint2 = false;

				if(pos.get(0)%10 == 0 && state == 1 || pos.get(1)%10 == 9 && state == 3 ) {
					repaint2 = true;
				}
			
			if(pos.get(0)/10 == 0) {
				timeCounter -= timeCounter%timeDrop + 5;
				for(int i = 0; i < pos.size(); i++) {
					if(pos.get(i)%2 == 0 && pos.get(i)/10 == 0) {
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}else if(pos.get(i)%2 == 0 && pos.get(i)/10 == 1) {
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;	
					}else if(pos.get(i)%2 == 1 && pos.get(i)/10 == 0) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;	
					}else if(pos.get(i)%2 == 1 && pos.get(i)/10 == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;	
						}
					pos.set(i,pos.get(i)+10);
					colorBoard[pos.get(i)/10][pos.get(i)%10]  = color.get(0);

				}
			}
			
			
			if(repaint1 == true) {
				active = new boolean[20][10];
				for(int i = 0; i < pos.size(); i++) {
					//Top search
					if(pos.get(i)/10 - 1 > -1) {
						if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Bottom Search
					if(pos.get(i)/10 + 1 < 20) {
						if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Left Search
					if(pos.get(i)%10 - 1 > -1) {
						if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Right Search
					if(pos.get(i)%10 + 1 < 10) {
						if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
				}

				if(state == 0) {
					state = 1;
				colorBoard[pos.get(0)/10+1][pos.get(0)%10+1] = color.get(0);
				active[pos.get(0)/10+1][pos.get(0)%10+1] = true;
				
				colorBoard[pos.get(1)/10][pos.get(1)%10] = color.get(1);
				active[pos.get(1)/10][pos.get(1)%10] = true;
			
				colorBoard[pos.get(2)/10-1][pos.get(2)%10] = color.get(2);
				active[pos.get(2)/10-1][pos.get(2)%10] = true;
				
				colorBoard[pos.get(3)/10-2][pos.get(3)%10-1] = color.get(3);
				active[pos.get(3)/10-2][pos.get(3)%10-1] = true;

				}else if(state == 1 && repaint2 == false) {
					state = 2;
					colorBoard[pos.get(0)/10][pos.get(0)%10-1] = color.get(0);
					active[pos.get(0)/10][pos.get(0)%10-1] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10-2] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10-2] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10+1] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10+1] = true;
					
				}else if(state == 1 && repaint2 == true) {
					state = 2;
					colorBoard[pos.get(0)/10][pos.get(0)%10] = color.get(0);
					active[pos.get(0)/10][pos.get(0)%10] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10-1] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10-1] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10+1] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10+1] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10+2] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10+2] = true;
					
				}else if(state == 2) {
					state = 3;
					colorBoard[pos.get(0)/10+2][pos.get(0)%10] = color.get(0);
					active[pos.get(0)/10+2][pos.get(0)%10] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10+1] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10+1] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10-1] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10-1] = true;
				}else if(state == 3 && repaint2 == false) {
					state = 0;
					colorBoard[pos.get(0)/10+1][pos.get(0)%10-1] = color.get(0);
					active[pos.get(0)/10+1][pos.get(0)%10-1] = true;
					
					colorBoard[pos.get(1)/10][pos.get(1)%10] = color.get(1);
					active[pos.get(1)/10][pos.get(1)%10] = true;
				
					colorBoard[pos.get(2)/10-1][pos.get(2)%10+2] = color.get(2);
					active[pos.get(2)/10-1][pos.get(2)%10+2] = true;
					
					colorBoard[pos.get(3)/10][pos.get(3)%10+1] = color.get(3);
					active[pos.get(3)/10][pos.get(3)%10+1] = true;
				}else if(state == 3 && repaint2 == true) {
					state = 0;
					colorBoard[pos.get(0)/10+1][pos.get(0)%10-2] = color.get(0);
					active[pos.get(0)/10+1][pos.get(0)%10-2] = true;
					
					colorBoard[pos.get(1)/10][pos.get(1)%10-1] = color.get(1);
					active[pos.get(1)/10][pos.get(1)%10-1] = true;
				
					colorBoard[pos.get(2)/10-1][pos.get(2)%10+1] = color.get(2);
					active[pos.get(2)/10-1][pos.get(2)%10+1] = true;
					
					colorBoard[pos.get(3)/10][pos.get(3)%10] = color.get(3);
					active[pos.get(3)/10][pos.get(3)%10] = true;
				}
			}
			}
			
			// Orange L Spin
			if(color.get(0) == 8) {
				repaint1 = true;
				repaint2 = false;

				if(pos.get(0)%10 == 0 && state == 1 || pos.get(1)%10 == 9 && state == 3 ) {
					repaint2 = true;
				}
			
			if(pos.get(0)/10 == 0) {
				timeCounter -= timeCounter%timeDrop + 5;
				for(int i = 0; i < pos.size(); i++) {
					if(pos.get(i)%2 == 0 && pos.get(i)/10 == 0) {
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
					}else if(pos.get(i)%2 == 0 && pos.get(i)/10 == 1) {
					colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;	
					}else if(pos.get(i)%2 == 1 && pos.get(i)/10 == 0) {
						colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;	
					}else if(pos.get(i)%2 == 1 && pos.get(i)/10 == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;	
						}
					pos.set(i,pos.get(i)+10);
					colorBoard[pos.get(i)/10][pos.get(i)%10]  = color.get(0);

				}
			}
			
			
			if(repaint1 == true) {
				active = new boolean[20][10];
				for(int i = 0; i < pos.size(); i++) {
					//Top search
					if(pos.get(i)/10 - 1 > -1) {
						if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10 - 1][pos.get(i)%10] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Bottom Search
					if(pos.get(i)/10 + 1 < 20) {
						if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10 + 1][pos.get(i)%10] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Left Search
					if(pos.get(i)%10 - 1 > -1) {
						if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 - 1] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
					//Right Search
					if(pos.get(i)%10 + 1 < 10) {
						if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 0){
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 1;
						}else if(colorBoard[pos.get(i)/10][pos.get(i)%10 + 1] == 1) {
							colorBoard[pos.get(i)/10][pos.get(i)%10] = 0;
						}
					}
				}

				if(state == 0) {
					state = 1;
				colorBoard[pos.get(0)/10+1][pos.get(0)%10+1] = color.get(0);
				active[pos.get(0)/10+1][pos.get(0)%10+1] = true;
				
				colorBoard[pos.get(1)/10][pos.get(1)%10] = color.get(1);
				active[pos.get(1)/10][pos.get(1)%10] = true;
			
				colorBoard[pos.get(2)/10-1][pos.get(2)%10-1] = color.get(2);
				active[pos.get(2)/10-1][pos.get(2)%10-1] = true;
				
				colorBoard[pos.get(3)/10][pos.get(3)%10+2] = color.get(3);
				active[pos.get(3)/10][pos.get(3)%10+2] = true;

				}else if(state == 1 && repaint2 == false) {
					state = 2;
					colorBoard[pos.get(0)/10+1][pos.get(0)%10-1] = color.get(0);
					active[pos.get(0)/10+1][pos.get(0)%10-1] = true;
					
					colorBoard[pos.get(1)/10][pos.get(1)%10] = color.get(1);
					active[pos.get(1)/10][pos.get(1)%10] = true;
				
					colorBoard[pos.get(2)/10-1][pos.get(2)%10+1] = color.get(2);
					active[pos.get(2)/10-1][pos.get(2)%10+1] = true;
					
					colorBoard[pos.get(3)/10-2][pos.get(3)%10] = color.get(3);
					active[pos.get(3)/10-2][pos.get(3)%10] = true;
					
				}else if(state == 1 && repaint2 == true) {
					state = 2;
					colorBoard[pos.get(0)/10+1][pos.get(0)%10] = color.get(0);
					active[pos.get(0)/10+1][pos.get(0)%10] = true;
					
					colorBoard[pos.get(1)/10][pos.get(1)%10+1] = color.get(1);
					active[pos.get(1)/10][pos.get(1)%10+1] = true;
				
					colorBoard[pos.get(2)/10-1][pos.get(2)%10+2] = color.get(2);
					active[pos.get(2)/10-1][pos.get(2)%10+2] = true;
					
					colorBoard[pos.get(3)/10-2][pos.get(3)%10+1] = color.get(3);
					active[pos.get(3)/10-2][pos.get(3)%10+1] = true;
					
				}else if(state == 2) {
					state = 3;
					colorBoard[pos.get(0)/10][pos.get(0)%10-2] = color.get(0);
					active[pos.get(0)/10][pos.get(0)%10-2] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10+1] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10+1] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10-1] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10-1] = true;
				}else if(state == 3 && repaint2 == false) {
					state = 0;
					colorBoard[pos.get(0)/10+2][pos.get(0)%10] = color.get(0);
					active[pos.get(0)/10+2][pos.get(0)%10] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10-1] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10-1] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10+1] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10+1] = true;
				}else if(state == 3 && repaint2 == true) {
					state = 0;
					colorBoard[pos.get(0)/10+2][pos.get(0)%10-1] = color.get(0);
					active[pos.get(0)/10+2][pos.get(0)%10-1] = true;
					
					colorBoard[pos.get(1)/10+1][pos.get(1)%10-2] = color.get(1);
					active[pos.get(1)/10+1][pos.get(1)%10-2] = true;
				
					colorBoard[pos.get(2)/10][pos.get(2)%10-1] = color.get(2);
					active[pos.get(2)/10][pos.get(2)%10-1] = true;
					
					colorBoard[pos.get(3)/10-1][pos.get(3)%10] = color.get(3);
					active[pos.get(3)/10-1][pos.get(3)%10] = true;
				}
			}
			}
		}
		pos.clear();
		color.clear();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		timeDrop = 100-((levels-1)*50);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("Mouse X: " + e.getX() + " Mouse Y: " + e.getY());
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
