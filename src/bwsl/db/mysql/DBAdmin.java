package bwsl.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBAdmin {
	
	private Connection con;
	private StringBuilder url;
	private PreparedStatement stat;
	
	public static final String _ENCODING_UTF_8 = "UTF-8";
	
	public static final String _ADDR_LOCALHOST = "localhost";
	public static final String _ADDR_LOCALHOST_PORT_3306 = "localhost:3306";
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
		
		System.out.println("---------- MySQL JDBC Disconnection ----------");
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
		
		stat = con.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		
		return rs;
	}
	
	public void create(String table, ArrayList<CloumnParameter> columns) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE " + table + " (");
		for(int index = 0; index < columns.size(); ++index) {
			if(index > 0) sql.append(", ");
			sql.append("?");
		}
		sql.append(");");
		
		stat = con.prepareStatement(sql.toString());
		
		for(int index = 1; index <= columns.size(); ++index)
			stat.setString(index, columns.get(index).toString());
		
		stat.executeUpdate();
	}

	public void insert(String table, ArrayList<String> values) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO " + table + " VALUES (");
		for(int index = 0; index < values.size(); ++index) {
			if(index > 0) sql.append(", ");
			sql.append("?");
		}
		sql.append(");");
		
		stat = con.prepareStatement(sql.toString());
		
		for(int index = 1; index <= values.size(); ++index)
			stat.setString(index, values.get(index));
		
		stat.executeUpdate();
	}

	public void drop(String table) throws SQLException { 
		
		stat = con.prepareStatement("DROP TABLE " + table);
		stat.executeUpdate();
	}
	
	public ResultSet selectAll(String table) throws SQLException {
		
		stat = con.prepareStatement("SELECT * FROM " + table);
		ResultSet rs = stat.executeQuery();
		
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