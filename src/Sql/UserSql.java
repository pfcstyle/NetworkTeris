package Sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * 用户分数数据库类
 * @author PfC
 *
 */

public class UserSql{
	public ResultSet rs=null;
    public Statement stmt=null;
    public Connection conn = null;
	public UserSql(){
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			//System.out.println("Load the embedded driver");
			Properties props = new Properties();
			props.put("user", "user1");
			props.put("password", "user1");
			//创建数据库
			conn=DriverManager.getConnection("jdbc:derby:UserData;create=false", props);
			//System.out.println("create and connect to UserData");
			conn.setAutoCommit(true);
			//创建表
			stmt = conn.createStatement();
			//stmt.execute("create table Player(name varchar(40), scores int, rank int)");
			//System.out.println("Created table player");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 执行查询操作
	 */
	public ResultSet executeQuery(String sql) {
		  try {
		    rs = stmt.executeQuery(sql);
		  }
		  catch (SQLException ex) {
		    System.err.println(ex.getMessage());
		  }
		  return rs;
		}
	/*
	 *功能:执行添加、修改或者删除操作
	 */
	public void executeUpdate(String sql) {
	  try {
	    stmt.execute(sql);
	  }
	  catch (SQLException ex) {
	  }
	  try {
	    stmt.close();
	  }
	  catch (SQLException ex1) {
	  }
	}

	    /*
	     *功能:关闭数据库的连接
	     */
	    public void close() {
	      try {
	        if (rs != null) {
	          rs.close();
	        }
	      }
	      catch (Exception e) {
	        e.printStackTrace(System.err);
	      }
	      try {
	        if (stmt != null) {
	          stmt.close();
	        }
	      }
	      catch (Exception e) {
	        e.printStackTrace(System.err);
	      }
	      try {
	        if (conn != null) {
	        	conn.close();
	        }
	      }
	      catch (Exception e) {
	        e.printStackTrace(System.err);
	      }
	  }
}
