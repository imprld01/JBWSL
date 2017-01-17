package bwsl.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBAdmin {
	
	private Connection con = null;
	private Statement stat = null;
	
	public DBAdmin(String addr, String dbName, boolean unicode, String encoding, String user, String pwd) throws ClassNotFoundException, SQLException {
		
		System.out.println("Try Connecting Database...");
		StringBuilder url = new StringBuilder();
		url.append("jdbc:mysql://");
		url.append(addr);
		url.append("/");
		url.append(dbName);
		url.append("?useUnicode=");
		url.append(unicode);
		url.append("&characterEncoding=");
		url.append(encoding);
		
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url.toString(), user, pwd);
		System.out.println("Connecting Database Succeed!");
	}
	
	public ResultSet execute(String sql) throws SQLException {
		
		stat = con.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		
		this.close();
		return rs;
	}
	
	public void create(String table, ArrayList<String> columns) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE ");
		sql.append(table);
		sql.append("(");
		for(int index = 0; index < columns.size(); ++index) {
			if(index > 0) sql.append(", ");
			sql.append(columns.get(index));
		}
		sql.append(");");
		
		stat = con.createStatement();
		stat.executeUpdate(sql.toString());
		
		this.close();
	}

	public void insert(String table, String parameters, ArrayList<String> values) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO ");
		sql.append(table);
		sql.append("(");
		sql.append(parameters);
		sql.append(")");
		sql.append("VALUES (");
		for(int index = 0; index < values.size(); ++index) {
			if(index > 0) sql.append(", ");
			sql.append(values.get(index));
		}
		sql.append(");");
		
		stat = con.createStatement();
		stat.executeUpdate(sql.toString());
		
		this.close();
	}

	public void drop(String table) throws SQLException { 
		
		stat = con.createStatement(); 
		stat.executeUpdate("DROP TABLE " + table + ";"); 
		
		this.close();
	}
	
	public ResultSet selectAll(String table) throws SQLException {
		
		stat = con.createStatement();
		ResultSet rs = stat.executeQuery("SELECT * FROM " + table + ";");
		
		return rs;
	}
	
	public void close() throws SQLException {
		
		if(stat != null) {
			stat.close();
			stat = null;
		}
	}
}