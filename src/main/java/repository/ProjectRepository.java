package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import config.MySQLConfig;
import entity.Project;
import entity.Status;
import entity.Task;
import entity.User;

public class ProjectRepository {

	public List<Project> getListProject() {
		List<Project> listProject = new ArrayList<Project>();
		try {
			String query = "SELECT * FROM project";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Project project = new Project();
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("start_date"));
				project.setEndDate(resultSet.getString("end_date"));

				listProject.add(project);
			}
		} catch (Exception e) {
			System.out.println("Lỗi truy vấn danh sách project" + e.getLocalizedMessage());
		}
		return listProject;
	}

	public int insert(String name, String startDate, String endDate) {
		int count = 0;
		try {
			String query = "INSERT INTO project (name,start_date,end_date) \n" + "VALUES('" + name + "','" + startDate
					+ "','" + endDate + "')";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi add project " + e.getLocalizedMessage());
		}
		return count;
	}

	public List<Project> getProjectName() {
		List<Project> listProject = new ArrayList<Project>();
		try {
			String query = "SELECT * FROM project";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Project project = new Project();
				project.setName(resultSet.getString("name"));
				project.setId(resultSet.getInt("id"));
				listProject.add(project);
			}
		} catch (Exception e) {
			System.out.println("Lỗi truy vấn tên project" + e.getLocalizedMessage());
		}
		return listProject;
	}

	public int deleteProjectById(int id) {
		int count = 0;
		try {
			String query = "DELETE FROM project p WHERE p.id= " + id + ";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi xóa dự án bằng ID " + e.getLocalizedMessage());
		}
		return count;
	}

	public int updateProject(int id, String name, String dateStart, String dateEnd) {
		int count = 0;
		try {
			String query = "UPDATE project p SET p.name = '" + name + "', p.start_date ='" + dateStart
					+ "',p.end_date='" + dateEnd + "' WHERE p.id = " + id + ";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			count = statement.executeUpdate();
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi cập nhật dự án " + e.getLocalizedMessage());
		}
		return count;
	}

	public Project findProjectById(int id) {
		Project project = new Project();
		try {
			String query = "SELECT * FROM project p WHERE p.id = " + id + ";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				project.setId(resultSet.getInt("id"));
				project.setName(resultSet.getString("name"));
				project.setStartDate(resultSet.getString("start_date"));
				project.setEndDate(resultSet.getString("end_date"));
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi tìm dự án bằng ID " + e.getLocalizedMessage());
		}
		return project;
	}

	public List<User> getUserByProjectId(int id) {
		List<User> users = new ArrayList<>();
		try {
			String query = "SELECT DISTINCT u.id AS user_id , u.fullname \n"
					+ "FROM users u\n" + "JOIN assigntask a ON u.id = a.id_user\n" + "JOIN task t ON a.id_task = t.id\n"
					+ "WHERE t.id_project =" + id + ";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("user_id"));
				user.setFullname(resultSet.getString("fullname"));
				users.add(user);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi lấy user bằng project id" + e.getLocalizedMessage());
		}
		return users;
	}

	public List<Task> getTaskByProjectIdAndUserId(int id, int userId) {
		List<Task> tasks = new ArrayList<>();
		try {
			String query = "SELECT DISTINCT t.*\n" + "FROM task t\n" + "JOIN assigntask a ON t.id = a.id_task\n"
					+ "WHERE a.id_user = " + userId + " AND t.id_project = " + id + ";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Task task = new Task();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(resultSet.getString("start_date"));
				task.setEndDate(resultSet.getString("end_date"));
				Project project = new Project();
				project.setId(resultSet.getInt("id_project"));
				task.setProject(project);
				Status status = new Status();
				status.setId(resultSet.getInt("id_status"));
				task.setStatus(status);
				tasks.add(task);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi lấy task bằng project id và user id" + e.getLocalizedMessage());
		}
		return tasks;
	}
	
}
