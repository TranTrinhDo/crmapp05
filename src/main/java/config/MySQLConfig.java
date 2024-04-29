package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConfig {

	// tạo hàm mở kết nối với csdl
	public static Connection getConnection() {
		Connection connection = null;
		try {
			String url = "jdbc:mysql://localhost:3307/crmapp";
			String username = "root";
			String password = "Tothemoon0710";
			// Khai báo Driver sẽ sử dụng là MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			// khai báo thông tin đường dẫn MYSQL Server sẽ kết nối tới
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("Lỗi kết nối CSDL " + e.getMessage());
		}
		return connection;

	}

}
