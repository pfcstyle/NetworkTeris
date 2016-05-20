package UI;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GameConnect.TerisCapture;
import GameInit.GameInit;
import Sql.UserSql;

/**
 * 菜单窗体
 * @author PfC
 *
 */
public class MenuFrame extends JFrame implements MouseListener{
	public static final int MODE_SINGLE=1;//单人模式
	public static final int MODE_DOUBLE_AI=2;//双人电脑对战模式
	public static final int MODE_DOUBLE_CON_SERVER=3;//双人联机模式(作为服务器)
	public static final int MODE_DOUBLE_CON_CLIENT=4;//双人联机模式(作为客户端)
	public static int gamemode=MODE_DOUBLE_CON_CLIENT;
	
	public static SingleGameFrame sgf;
	public static DoubleGameFrame dgf;
	
	public static TerisCaptureFrame terisCaptureFrame1;
	public static TerisCaptureFrame terisCaptureFrame2;
	
	public static TerisCapture terisCapture1;
	
	public ArrayList<Object> jilu;
	
	public static void main(String[] args){
		new GameInit();
		MenuFrame menuFrame=new MenuFrame();
//		UserSql userSql=new UserSql();
//		userSql.executeQuery("select * from Player");
//		try {
//			while(userSql.rs.next())
//			{
//			    StringBuilder builder = new StringBuilder(userSql.rs.getString(1));
//			    builder.append("t");
//			    builder.append(userSql.rs.getInt(2));
//			    System.out.println(builder.toString());
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		userSql.close();
	}
	public MenuFrame(){
		//Layout();
		switch(gamemode){
		case MODE_SINGLE:
			sgf=new SingleGameFrame();
			break;
		case MODE_DOUBLE_AI:
			break;
		case MODE_DOUBLE_CON_SERVER:
			terisCapture1=new TerisCapture();
//		    terisCaptureFrame1=new TerisCaptureFrame(this);
			break;
		case MODE_DOUBLE_CON_CLIENT:
			terisCaptureFrame2=new TerisCaptureFrame(this);
//			TerisCapture terisCapture2=new TerisCapture(ip);
			break;
		}
//		this.setBackground(Color.WHITE);
//		this.setVisible(true);
//		this.setLocation(100, 100);
//		this.setSize(800, 625);
//		this.setTitle("俄罗斯方块");
//		this.setResizable(false);
//		this.requestFocus();
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void Layout(){
		
	}
	public void getData(){
		UserSql sqlcon=new UserSql();
		String sqlStr=new String("select *from Player where rank<11 order by rank DESC");
		sqlcon.executeQuery(sqlStr);
		int rank=1;
		try {
			while(sqlcon.rs.next()){
				System.out.println(sqlcon.rs.getInt(2));
				ArrayList<Object> hang=new ArrayList<Object>();
				hang.add(sqlcon.rs.getString(1));
				hang.add(sqlcon.rs.getInt(2));
				hang.add(sqlcon.rs.getInt(3));
				//System.out.println(sqlcon.rs.getString(2));
				jilu.add(hang);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
