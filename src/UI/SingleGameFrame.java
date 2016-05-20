package UI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import Teris.*;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
/**
 * 单人游戏窗体
 * @author PfC
 *
 */

public class SingleGameFrame extends JFrame{
	//声明面板
	private Player player;//游戏主界面
	private JPanel jp1;//道具面板
	private JPanel jp2;//数据显示面板
	//道具栏主窗口
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	//下一个方块窗口
	private JLabel l4;
	//分数
	private JLabel l5;
	//暂停按钮
	JButton pauseButton;
	
	public SingleGameFrame(){
		Layout();
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setLocation(100, 100);
		this.setSize(400, 625);
		this.setTitle("俄罗斯方块");
		this.setResizable(false);
		this.requestFocus();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void Layout(){
		this.setLayout(null);
		//主游戏界面布局
		player=new Player(false);
		player.setLayout(null);
		player.setLocation(0, 100);
		player.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.add(player);
		
		/* 键盘监听 */
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				player.onKeyDown(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	}


}
