import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Util {
	static {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		// Establish Connection
		String url = "jdbc:mysql://localhost:3306/bank";
		String username = "root";
		String password = "Rajesh4795@";
		return DriverManager.getConnection(url, username, password);
	}

	public static void getClose(Connection connect, Statement statement) throws SQLException {
		// close resources
		statement.close();
		connect.close();
	}
}
