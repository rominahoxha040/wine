import java.sql.*;

import javax.swing.JOptionPane;

public class Database {
	public static Connection getConnection() {
		Connection conn = null;
		final String URL = "jdbc:mysql://localhost:3306/wine";
		final String USER = "root";

		final String PASSWORD = "root";

		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection me db nuk u be me sukeses");
			e.printStackTrace();
		}

		return conn;
	}

}
