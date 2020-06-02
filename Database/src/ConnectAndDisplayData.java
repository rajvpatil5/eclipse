import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectAndDisplayData {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "1");
		String sql = "select * from nobelwin";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(5));
		}
		conn.close();
	}

}
