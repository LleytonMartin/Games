package BrickBreaker;
import java.awt.MouseInfo;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

import javax.swing.*;

public class drawing extends mainClass implements ActionListener, KeyListener, MouseListener {
private boolean lock = false, runCheck = false;
private int numOfRect = 36;


private int playerWidth = 250,playerX = width/2-playerWidth/2,playerY = (height*3)/4,playerHeight = 20,playerVelX = 0;
private int eWidth = 40, eHeight = 40;
private double eVelX = 0,eVelY = 0,ex = width/2-eWidth/2,ey = playerY-eHeight;
private int rHeight =50, rWidth = 100;
private int r1x = 100;
private int r1y = 200;
private ArrayList<Boolean> rState = new ArrayList<Boolean>();
private ArrayList<Integer> rXPos = new ArrayList<Integer>();
private ArrayList<Integer> rYPos = new ArrayList<Integer>();

Timer t = new Timer(5,this);
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.black);
		Graphics2D g2 = (Graphics2D) g;
/*		g2.setStroke(new BasicStroke(5.0f));
		g2.setColor(Color.black);
		g2.drawRect(200,200,rWidth,rHeight);*/
		if(runCheck  == false) {
		for(int j = 0; j < numOfRect; j++) {
			int divisor = numOfRect/3;

			if(j < divisor) {
			rXPos.add(r1x + j*rWidth);
			rYPos.add(r1y);
			}
			if(j >= divisor && j < divisor*2 ) {
			rXPos.add(r1x + (j-divisor)*rWidth);
			rYPos.add(r1y + rHeight);
			}
			if(j >= divisor*2) {
			rXPos.add(r1x + (j-divisor*2)*rWidth);
			rYPos.add(r1y + 2*rHeight);					
			}
			rState.add(true);

		}
			
		}
		runCheck = true;
	if(runCheck == true) {	
		for(int j = 0; j < rXPos.size(); j++) {
			int divisor = numOfRect/3;
			if(j < divisor) {
			rXPos.set(j,r1x + j*rWidth);
			rYPos.set(j,r1y);
			}
			if(j >= divisor && j < divisor*2 ) {
			rXPos.set(j,r1x + (j-divisor)*rWidth);
			rYPos.set(j,r1y + rHeight);
			}
			if(j >= divisor*2) {
			rXPos.set(j,r1x + (j-divisor*2)*rWidth);
			rYPos.set(j,r1y + 2*rHeight);					
			}
		}
	}
		
	for (int i = 0; i < numOfRect; i++) {
		if(rState.get(i) == true) {
		g2.setColor(Color.white);
		Rectangle rect1 = new Rectangle(rXPos.get(i),rYPos.get(i),rWidth,rHeight);
		g2.fill(rect1);
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(2));
		g2.drawRect(rXPos.get(i), rYPos.get(i), rWidth, rHeight);
		}
	}
		g2.setColor(Color.white);
				
		Rectangle rectPlayer = new Rectangle(playerX,playerY,playerWidth,playerHeight);
		g2.draw(rectPlayer);
		g2.fill(rectPlayer);


		
		Ellipse2D.Double ellipse = new Ellipse2D.Double(ex,ey,eWidth,eHeight);
		g2.draw(ellipse);
		g2.fill(ellipse);

	
	
		
		

		t.start();
	}

	public drawing() {
		t.start();
		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < rXPos.size(); i++) {
			if(i < rXPos.size() && eVelY < 0 && ey <= rYPos.get(i) + rHeight && ey  >= rYPos.get(i) && ex + eWidth >= rXPos.get(i) && ex + eWidth <= rXPos.get(i) + rWidth && rState.get(i) == true
					||i < rXPos.size() && eVelY < 0 && ey <= rYPos.get(i) + rHeight && ey  >= rYPos.get(i) && ex  >= rXPos.get(i) && ex <= rXPos.get(i) + rWidth && rState.get(i) == true ) {
				eVelY = - eVelY;
				rState.set(i, false);
				break;

			}
			if(eVelY > 0 && rState.get(i) == true && i < rXPos.size() && ey + eHeight<= rYPos.get(i) + rHeight && ey + eHeight >= rYPos.get(i) && ex + eWidth >= rXPos.get(i) && ex + eWidth <= rXPos.get(i) + rWidth && rState.get(i) == true
					||eVelY > 0 && i < rXPos.size() && i < rXPos.size() && ey +eHeight<= rYPos.get(i) + rHeight && ey + eHeight  >= rYPos.get(i) && ex  >= rXPos.get(i) && ex <= rXPos.get(i) + rWidth && rState.get(i) == true ) {
				eVelY = - eVelY;
				rState.set(i, false);
				break;
			}

			if(i < rXPos.size() && rState.get(i) == true && ex + eWidth >= rXPos.get(i) && ex + eWidth <= rXPos.get(i) + rWidth &&  ey <= rYPos.get(i) + rHeight && ey >= rYPos.get(i)
			||rState.get(i) == true && i < rXPos.size() &&	ex + eWidth >= rXPos.get(i) && ex + eWidth <= rXPos.get(i) + rWidth &&  ey +eHeight <= rYPos.get(i) + rHeight && ey + eHeight >= rYPos.get(i)) {
				eVelX = -eVelX;
				eVelY = - eVelY;
				rState.set(i,false);
				break;
			}
			if(i < rXPos.size() && rState.get(i) == true && ex >= rXPos.get(i) && ex <= rXPos.get(i) + rWidth &&  ey <= rYPos.get(i) + rHeight && ey >= rYPos.get(i)
			|| rState.get(i) == true && i < rXPos.size() &&	ex >= rXPos.get(i) && ex <= rXPos.get(i) + rWidth &&  ey +eHeight <= rYPos.get(i) + rHeight && ey + eHeight >= rYPos.get(i)) {
				eVelX = -eVelX;
				eVelY = - eVelY;
				rState.set(i, false);
				break;
			}
		}
		if(ex + eWidth <= playerX + playerWidth && ex + eWidth >= playerX && ey +eHeight >= playerY && ey + eHeight <= playerY + playerHeight 
				|| ex <= playerX + playerWidth && ex >= playerX && ey +eWidth >= playerY && ey + eHeight <= playerY + playerHeight ) {
			if(ex+eWidth/2 < playerX+playerWidth/2) {
				double angle =90-(-(2*(playerX + playerWidth/2 - (ex +eWidth/2)))/playerWidth)*60;
				eVelX = -6*Math.abs(Math.cos((Math.toRadians(angle))));
				eVelY = -6*Math.sin((Math.toRadians(angle)));
				
			}else if(ex+eWidth/2 > playerX+playerWidth/2){
				double angle = 90+((2*(playerX + playerWidth/2 - (ex +eWidth/2)))/playerWidth)*60;				
				eVelX =  6*Math.abs(Math.cos((Math.toRadians(angle))));
				eVelY =  -6*Math.sin((Math.toRadians(angle)));				
			}
		}
		
		if(ey > height)	{
			playerX = width/2-playerWidth/2;
			playerY = (height*3)/4;
			ex = width/2-eWidth/2;
			ey = playerY-eHeight;
			eVelX =0;
			eVelY = 0;
			lock = false;
		}
	
	if(playerX + playerWidth > width)	{
		playerX = width - playerWidth;
	}else if(playerX < 0) {
		playerX = 0;
	}
	
	
	if(ey < 0) {
		eVelY = -eVelY;
	}
	if(ex < 0 || ex > width-eWidth-40) {
		eVelX = -eVelX;
	}
		playerX += playerVelX;
		ey += eVelY;
		ex += eVelX;
		repaint();
		
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if(lock == true) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_LEFT) {
			playerVelX = -5;
		}
		if(code == KeyEvent.VK_RIGHT) {
			playerVelX = 5;
		}
		}
	}

	public void keyReleased(KeyEvent e) {
		playerVelX = 0;
		
	}


	public void mouseClicked(MouseEvent e) {
		int code = e.getButton();
		double x = ex-e.getX()+eWidth/2;
		double y =	ey-e.getY()+eHeight/2;
		double angle = Math.toDegrees(Math.atan(y/x));

		if(code == MouseEvent.BUTTON1 && Math.toDegrees(angle) >= 0 && e.getY() < ey+eHeight && lock == false ) {
			eVelX = - 6*(double)Math.cos(Math.toRadians(angle));
			eVelY = - 6*(double)Math.sin(Math.toRadians(angle));
			lock = true;
		}else if(code == MouseEvent.BUTTON1 && Math.toDegrees(angle) <= 0 && e.getY() < ey+eHeight && lock == false){
			eVelX = 6*(double)Math.cos(Math.toRadians(angle));
			eVelY =  6*(double)Math.sin(Math.toRadians(angle));
			lock = true;
		}
		
	}


	public void mousePressed(MouseEvent e) {

		
	}


	public void mouseReleased(MouseEvent e) {

		
	}


	public void mouseEntered(MouseEvent e) {}


	public void mouseExited(MouseEvent e) {}
}
