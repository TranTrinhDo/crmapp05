package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Role;
import entity.User;

public class UserRepository {
	
	public int deleteUserById(int id) {
		int count = 0;
		
		try {
			String query = "DELETE FROM users u WHERE u.id = ?";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
			
			connection.close();;
		} catch (Exception e) {
			System.out.println("Lỗi xóa user " +e.getLocalizedMessage());
		}
		return count;
	}

	public List<User> getListUser() {
		List<User> listUser = new ArrayList<User>();
		try {
			String query = "SELECT u.id , u.fullname ,u.email ,r.name as role_name \n"
					+ "FROM users u \n" + "JOIN roles r ON u.id_role = r.id";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFullname(resultSet.getString("fullname"));
				Role role = new Role();
				role.setName(resultSet.getString("role_name"));
				user.setRole(role);
				listUser.add(user);
			}
		} catch (Exception e) {
			System.out.println("Lỗi lấy danh sách user" + e.getLocalizedMessage());
		}
		return listUser;
	}

	public List<User> getAll() {
		List<User> listUser = new ArrayList<User>();
		try {
			String query = "SELECT * FROM users";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setFullname(resultSet.getString("fullname"));
				user.setPhone(resultSet.getString("phone"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				Role role = new Role();
				role.setId(resultSet.getInt("id_role"));

				listUser.add(user);
			}
		} catch (Exception e) {
			System.out.println("Lỗi truy vấn user" + e.getLocalizedMessage());
		}
		return listUser;
	}

	public int save(String fullname, String email, String password, String phone, int idRole) {
		int count = 0;
		try {
			String query = "INSERT INTO users (fullname,email,password,phone,id_role) \n" + "VALUES('" + fullname
					+ "','" + email + "','" + password + "','" + phone + "'," + idRole + ");";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi insert user " + e.getLocalizedMessage());
		}
		return count;
	}
	
	public List<User> getUserByEmailAndPassWord(String email, String password){
		List<User> users = new ArrayList<User>();
		try {
			String query ="SELECT * FROM users u WHERE u.email = ? and u.password = ?";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,email);
			statement.setString(2,password);
			
			ResultSet resultSet = 	statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setFullname(resultSet.getString("fullname"));
                user.setPhone(resultSet.getString("phone"));
                Role role = new Role();
                role.setId(resultSet.getInt("id_role"));
    			user.setRole(role);
                users.add(user);
			}
			
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi tìm user bằng email và password "+e.getLocalizedMessage());
		}
		
		return users;
		
	}

	public int updateUser(int id,String fullname, String email, String password, String phone,String firstName,String lastName, int idRole) {
		int count = 0;
		try {
			String query="UPDATE users u SET u.email = ?, u.password = ?, u.id_role=?,u.first_name=?, u.last_name=?, u.fullname=?, u.phone=? "
					+ "WHERE id=?";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setInt(3, idRole);
			statement.setString(4, firstName);
			statement.setString(5, lastName);
			statement.setString(6, fullname);
			statement.setString(7, phone);
			statement.setInt(8, id);
			count = statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi sửa thông tin user "+ e.getLocalizedMessage());
		}
		return count;
	}
	
	public User findUserById(int id) {
		User user = new User();
		
		try {
			String query ="SELECT * FROM users u WHERE id= ?";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setFullname(resultSet.getString("fullname"));
                user.setPhone(resultSet.getString("phone"));
                Role role = new Role();
                role.setId(resultSet.getInt("id_role"));
    			user.setRole(role);
    			user.setPassword(resultSet.getString("password"));
			}
			
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi tìm user bằng ID "+ e.getLocalizedMessage());
			}
		return user;
	}
}
