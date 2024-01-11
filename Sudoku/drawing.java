package Sudoku;

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
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

public class drawing extends mainClass implements MouseListener,KeyListener,ActionListener{
	Timer t = new Timer(5,this);
	public int[][] board = boardCreator();
	public int[][] playerBoard = visibleBoard(board);
	public int colorNumber;
	public boolean[][] state = state(playerBoard);
	public int currentPos;
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		Rectangle2D.Double rect = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
		g2.fill(rect);
		g2.setColor(Color.BLACK);
		for(int i = 1; i < 9;i++) {
			g2.setStroke(new BasicStroke(2));
			if(i%3 == 0) {
				g2.setStroke(new BasicStroke(10));
			}			
			Line2D.Double l = new Line2D.Double((getWidth()/9)*i, 0, (getWidth()/9)*i, getHeight());
			g2.draw(l);		
		}
		for(int i = 1; i < 9;i++) {
			g2.setStroke(new BasicStroke(2));
			if(i%3 == 0) {
				g2.setStroke(new BasicStroke(10));
			}	
			Line2D.Double l = new Line2D.Double(0, (getHeight()/9)*i, getWidth(),(getHeight()/9)*i);
			g2.draw(l);		
		}
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(playerBoard[j][i] > 0) {
					if(colorNumber == 0) {
						g2.setColor(Color.BLACK);
					}
					if(state[j][i] == true) {
						g2.setColor(Color.blue);
					}else {
						g2.setColor(Color.BLACK);
					}
					if(playerBoard[j][i] == colorNumber) {
						g2.setColor(Color.YELLOW);
					}
					Font stringFont = new Font( "SansSerif", Font.PLAIN, getWidth()/18 );
					g2.setFont(stringFont);
					g2.drawString("" + playerBoard[j][i], (i*(getWidth()/9)) + (getWidth()/(9*4)), (j*(getHeight()/9))+stringFont.getSize() + getHeight()/(9*4));
				}
			}
		}
		
		/*System.out.println("Board");
		for(int i = 0 ; i < board[0].length;i++) {
			for(int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}	
		
		System.out.println("playerBoard");
		for(int i = 0 ; i < playerBoard[0].length;i++) {
			for(int j = 0; j < board.length; j++) {
				System.out.print(playerBoard[i][j] + " ");
			}
			System.out.println();
		}	*/
			
			
	}
	
	public drawing(){
		t.start();
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);		
	}
	public void actionPerformed(ActionEvent e) {
		boolean win = true;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(playerBoard[j][i] != board[j][i]) {
					win = false;
				}
			}
		}
		if(win == true) {
			System.out.println("Congratulations, you won!!");
			t.stop();
		}
		repaint();
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		System.out.println(KeyEvent.VK_1 + " COMPARE TO " + code);
		if(currentPos <= 81) {
		if(code == KeyEvent.VK_1 && state[currentPos/9][currentPos%9] == true)
			playerBoard[currentPos/9][currentPos%9] = 1;
		if(code == KeyEvent.VK_2 && state[currentPos/9][currentPos%9] == true)
			playerBoard[currentPos/9][currentPos%9] = 2;
		if(code == KeyEvent.VK_3 && state[currentPos/9][currentPos%9] == true)
			playerBoard[currentPos/9][currentPos%9] = 3;
		if(code == KeyEvent.VK_4 && state[currentPos/9][currentPos%9] == true)
			playerBoard[currentPos/9][currentPos%9] = 4;
		if(code == KeyEvent.VK_5 && state[currentPos/9][currentPos%9] == true)
			playerBoard[currentPos/9][currentPos%9] = 5;
		if(code == KeyEvent.VK_6 && state[currentPos/9][currentPos%9] == true)
			playerBoard[currentPos/9][currentPos%9] = 6;
		if(code == KeyEvent.VK_7 && state[currentPos/9][currentPos%9] == true)
			playerBoard[currentPos/9][currentPos%9] = 7;
		if(code == KeyEvent.VK_8 && state[currentPos/9][currentPos%9] == true)
			playerBoard[currentPos/9][currentPos%9] = 8;
		if(code == KeyEvent.VK_9 && state[currentPos/9][currentPos%9] == true)
			playerBoard[currentPos/9][currentPos%9] = 9;
		if(code == KeyEvent.VK_DELETE && state[currentPos/9][currentPos%9] == true)
			playerBoard[currentPos/9][currentPos%9] = 0;
		if(code == KeyEvent.VK_H) {
			System.out.println(playerBoard[currentPos/9][currentPos%9] + "   " + board[currentPos/9][currentPos%9]);
			playerBoard[currentPos/9][currentPos%9] = board[currentPos/9][currentPos%9];
		}
		
		}

		if(code == KeyEvent.VK_SPACE)
			currentPos = 82;
		if(currentPos == 82) {
			if(code == KeyEvent.VK_1)
				colorNumber = 1;
			if(code == KeyEvent.VK_2)
				colorNumber = 2;
			if(code == KeyEvent.VK_3)
				colorNumber = 3;
			if(code == KeyEvent.VK_4)
				colorNumber = 4;
			if(code == KeyEvent.VK_5)
				colorNumber = 5;
			if(code == KeyEvent.VK_6)
				colorNumber = 6;
			if(code == KeyEvent.VK_7)
				colorNumber = 7;
			if(code == KeyEvent.VK_8)
				colorNumber = 8;
			if(code == KeyEvent.VK_9)
				colorNumber = 9;
			if(code == KeyEvent.VK_DELETE)
				colorNumber = 0;

		}

		
		for(int i = 0; i < playerBoard.length;i++) {
			for(int k = 0; k < playerBoard[0].length; k++) {
				System.out.print(playerBoard[i][k] + " ");
			}
			System.out.println();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		currentPos = ((e.getY()/(getHeight()/9))*9 + (e.getX()/(getWidth()/9)));
		System.out.println(currentPos);
		
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
