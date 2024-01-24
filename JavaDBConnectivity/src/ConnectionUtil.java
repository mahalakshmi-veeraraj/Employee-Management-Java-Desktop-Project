import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private Connection connection;

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ems", "root", "maha");
		return connection;
	}

	public void closeConnection() throws SQLException {
		if (null != connection && !connection.isClosed()) {
			connection.close();
		}
	}
}
