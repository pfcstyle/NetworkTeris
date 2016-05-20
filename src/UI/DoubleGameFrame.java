package UI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.EtchedBorder;

import GameConnect.TerisCapture;
import Teris.Player;
/**
 * 双人游戏窗体
 * @author PfC
 *
 */

public class DoubleGameFrame extends JFrame implements KeyListener{
	public static TerisCapture tCapture;
	public static Player enemy=new Player(true);//敌人
	public static Player self=new Player(enemy,false);//自己
	private JButton b1;
	public DoubleGameFrame(){
		Layout();
		tCapture=new TerisCapture();
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setLocation(100, 100);
		this.setSize(800, 625);
		this.setTitle("俄罗斯方块");
		this.setResizable(false);
		this.requestFocus();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(this);
	}
	public void Layout(){
		this.setLayout(null);
		self.setLayout(null);
		self.setLocation(0, 100);
		self.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		enemy.setLayout(null);
		enemy.setLocation(400,100);
		self.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.add(self);this.add(enemy);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		self.onKeyDown(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
