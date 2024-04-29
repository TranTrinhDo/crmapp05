package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Role;

//Quản lý tất cả các câu truy vấn liên quan tới bản role
public class RoleRepository {

	public List<Role> getListRole() {
		List<Role> listRole = new ArrayList<Role>();
		try {
			String query = "SELECT r.id, r.name, r.description  FROM roles r";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Role role = new Role();
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));

				listRole.add(role);
			}
		} catch (Exception e) {
			System.out.println("Lỗi lấy danh sách role" + e.getLocalizedMessage());
		}
		return listRole;
	}

	public int insert(String name, String description) {
		int count = 0;
		try {
			String query = "INSERT INTO roles (name,description) \n" + "VALUES('" + name + "','" + description + "')";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi insert role " + e.getLocalizedMessage());
		}
		return count;
	}

	public int deleteRoleById(int id) {
		int count = 0;
		try {
			String query = "DELETE FROM roles r WHERE r.id = " + id + ";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi xóa role" + e.getLocalizedMessage());
		}
		return count;
	}

	public int updateRole(int id, String name, String description) {
		int count = 0;
		try {
			String query = "UPDATE roles r SET r.name ='"+name+"' , r.description ='"+description+"' WHERE r.id="+id+";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi sửa quyền " + e.getLocalizedMessage());
		}
		return count;
	}

	public Role findRoleById(int id) {
		Role role = new Role();
		try {
			String query = "SELECT * FROM roles r  WHERE id= "+id+";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				role.setId(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi tìm role bằng ID " + e.getLocalizedMessage());
		}
		return role;
	}
}
