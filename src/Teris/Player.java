package Teris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import GameConnect.TerisCapture;
import UI.DoubleGameFrame;
import UI.MenuFrame;


/**
 * 玩家类
 * @author PfC
 *
 */
public class Player extends JPanel implements Runnable{
	java.awt.Toolkit toolkit=java.awt.Toolkit.getDefaultToolkit();
	public Teris teris;
	public Tools tools;
	private Graphics g;
	public static final int panel_W=400;
	public static final int panel_H=500;
	public static Player enemy;
	private Image offScreenImage;
	public Player(boolean isEnemy){
		teris=new Teris(isEnemy);
		tools=new Tools();
		this.setSize(panel_W, panel_H);
		Thread tr=new Thread(this);
		tr.start();
	}
	public Player(Player enemy,boolean isEnemy){
		teris=new Teris(isEnemy);
		tools=new Tools();
		this.enemy=enemy;
		this.setSize(panel_W, panel_H);
		Thread tr=new Thread(this);
		tr.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			long start=System.currentTimeMillis();
			myDraw();
			logic();
			long end=System.currentTimeMillis();
			if(end-start<50){
				try {
					Thread.sleep(50-(end-start));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void logic(){
		teris.logic();
		tools.singleLogic(this);
		tools.doubleLogic(enemy);
//		if(MenuFrame.gamemode!=MenuFrame.MODE_SINGLE){
//			enemy.teris.action(TerisCapture.ein[0]);
//			TerisCapture.ein[0]='.';
//		}
	}
	public void myDraw(){
		if (g == null) {  
			if (offScreenImage == null) {  //建立虚拟图片解决f方块移动卡顿
				offScreenImage = this.createImage(panel_W,panel_H);
			}
			if (offScreenImage != null)
				g = offScreenImage.getGraphics();
		}
		if (g != null) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Teris.SCREEN_W, Teris.SCREEN_H);
			teris.draw(g);
			tools.drawDouble(g, enemy);
			tools.drawSingle(g, this);
			this.paint(this.getGraphics());//调用重画方法
		}
	}
	public void paint(Graphics g) {
		g.drawImage(offScreenImage, 0, 0, this);
	}
	public void onKeyDown(KeyEvent e){
		if(teris.isReverseControl){
			teris.onReverseKeyDown(e);
		}else{
			teris.onKeyDown(e);
		}
		tools.onKeyDown(e);
	}
}
