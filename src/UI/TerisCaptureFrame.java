package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import GameConnect.TerisCapture;

public class TerisCaptureFrame extends JFrame{
	private JButton cancel;
	private JButton sure;
	private JTextField ip;
	private JTextField port;
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private TerisCaptureFrame terisFrame=this;
	public TerisCaptureFrame(MenuFrame frame) {
		// TODO Auto-generated constructor stub
		if(frame.gamemode==frame.MODE_DOUBLE_CON_SERVER){
			serverLayout();
		}else{
			clientLayout();
		}
		this.setVisible(true);
		this.setLocation(frame.getLocation().x+frame.getWidth()/2-100, frame.getLocation().y+frame.getHeight()/3);
		this.setSize(220, 170);
		this.setTitle("提示");
		this.setResizable(false);
		this.requestFocus();
	}
	
	public void serverLayout(){
		this.setLayout(null);
		l1=new JLabel("您的IP地址是：");
		l2=new JLabel(""+TerisCapture.serverSocket.getInetAddress());
		l3=new JLabel("请告诉您的客户端并输入以连接");
		l4=new JLabel("等待连接中……");
		l1.setBounds(5, 5, 200, 20);
		l2.setBounds(5, 30, 200, 20);
		l3.setBounds(5, 55, 200, 20);
		l4.setBounds(5, 80, 200, 20);
		this.add(l1);this.add(l2);this.add(l3);this.add(l4);
		cancel=new JButton("取消");
		cancel.setBounds(110, 105, 80, 30);
		this.add(cancel);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				terisFrame.dispose();
			}
		});
	}
	public void clientLayout(){
		this.setLayout(null);
		l1=new JLabel("请输入服务器IP地址：");
		l1.setBounds(5, 35, 200, 20);
		this.add(l1);
		ip=new JTextField();
		ip.setBounds(5, 60, 120, 30);
		port=new JTextField();
		port.setBounds(130, 60, 40, 30);
		this.add(ip);this.add(port);
		cancel=new JButton("取消");
		cancel.setBounds(60, 105, 60, 30);
		sure=new JButton("确定");
		sure.setBounds(130, 105, 60, 30);
		this.add(cancel);this.add(sure);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				terisFrame.dispose();
			}
		});
		sure.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TerisCapture terisCapture2=new TerisCapture(ip.getText(),Integer.parseInt(port.getText()));
			}
		});
	}
}

