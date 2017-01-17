package bwsl.db.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBAdminTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DBAdmin dba = new DBAdmin(DBAdmin._ADDR_LOCALHOST_PORT_3306, "test");
		dba.connect("root", "5868");
		
		ResultSet rs = dba.selectAll("test");
		while(rs.next()) System.out.println(rs.getString("P") + " " + rs.getString("A") + " " + rs.getString("B"));
		
		ArrayList<CloumnParameter> paras = new ArrayList<CloumnParameter>();
		paras.add(new CloumnParameter("A", "int"));
		paras.add(new CloumnParameter("B", "int"));
		dba.create("todrop", paras);
		
		ArrayList<String> values = new ArrayList<String>();
		values.add("3");
		values.add("5");
		dba.insert("todrop", values);
		values = new ArrayList<String>();
		values.add("100");
		values.add("500");
		dba.insert("todrop", values);
		
		rs = dba.selectAll("todrop");
		while(rs.next()) System.out.println(rs.getString("A") + " " + rs.getString("B"));
		
		dba.drop("todrop");
		
		dba.close();
	}
}