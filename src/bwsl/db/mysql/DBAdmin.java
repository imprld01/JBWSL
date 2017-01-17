package bwsl.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBAdmin {
	
	private Connection con;
	private Statement stat;
	private StringBuilder url;
	
	public static final String _ENCODING_UTF_8 = "UTF-8";
	
	public static final String _DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	public DBAdmin(String addr, String dbName) throws ClassNotFoundException, SQLException {
		
		this.printFrontConnectMsg();
		this.prpareUrl(addr, dbName, true, DBAdmin._ENCODING_UTF_8);
	}
	
	public DBAdmin(String addr, String dbName, String encoding) throws ClassNotFoundException, SQLException {
		
		this.printFrontConnectMsg();
		this.prpareUrl(addr, dbName, true, encoding);
	}
	
	public DBAdmin(String addr, String dbName, String encoding, String user, String pwd) throws ClassNotFoundException, SQLException {
		
		this.printFrontConnectMsg();
		this.prpareUrl(addr, dbName, true, encoding);
		this.connect(user, pwd);
	}
	
	private void printFrontConnectMsg() {
		
		System.out.println("---------- MySQL JDBC Connection ----------");
		System.out.println("Try Connecting Database...");
	}
	
	private void printBackConnectMsg() {
		
		System.out.println("SQL Connection to Database Established!");
		System.out.println("-------------------------------------------");
	}
	
	private void printFrontCloseMsg() {
		
		System.out.println("---------- MySQL JDBC Connection ----------");
		System.out.println("Try Closing SQL Connection to Database...");
	}
	
	private void printBackCloseMsg() {
		
		System.out.println("SQL Connection to Database Closed!");
		System.out.println("-------------------------------------------");
	}
	
	private void printDriverRegMsg() {
		
		System.out.println("MySQL JDBC Driver Registered!");
	}
	
	private void prpareUrl(String addr, String dbName, boolean unicode, String encoding) {
		
		this.url = new StringBuilder();
		this.url.append("jdbc:mysql://");
		this.url.append(addr);
		this.url.append("/");
		this.url.append(dbName);
		this.url.append("?useUnicode=");
		this.url.append(unicode);
		this.url.append("&characterEncoding=");
		this.url.append(encoding);
	}
	
	public void connect(String user, String pwd) throws SQLException, ClassNotFoundException {
		
		Class.forName(DBAdmin._DRIVER_NAME);
		this.printDriverRegMsg();
		this.con = DriverManager.getConnection(this.url.toString(), user, pwd);
		this.printBackConnectMsg();
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
		
		this.printFrontCloseMsg();
		
		if(this.stat != null) {
			this.stat.close();
			this.stat = null;
		}
		
		if (this.con != null){
			this.con.close();
			this.con = null;
		}
		
		this.printBackCloseMsg();
	}
}