package bwsl.db.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBAdminTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DBAdmin dba = new DBAdmin(DBAdmin._ADDR_LOCALHOST_PORT_3306, "test");
		dba.connect("root", "5868");
		
		ResultSet rs = dba.selectAll("test");
		while(rs.next()) System.out.println(rs.getString("P") + " " + rs.getString("A") + " " + rs.getString("B"));
		
		dba.close();
	}
}