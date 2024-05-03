package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import config.MySQLConfig;
import entity.Project;
import entity.Status;
import entity.Task;
import entity.User;

public class TaskRepository {

	public List<Task> getListTask() {
		List<Task> listTask = new ArrayList<Task>();

		try {
			String query = "SELECT t.id, t.name, t.start_date, t.end_date, p.name AS project_name, s.name AS status_name, u.fullname AS user_name\n"
					+ "FROM task t\n" + "JOIN project p ON t.id_project = p.id\n"
					+ "JOIN status s ON t.id_status = s.id\n" + "JOIN assigntask at ON at.id_task = t.id\n"
					+ "JOIN users u ON u.id = at.id_user;";
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
				project.setName(resultSet.getString("project_name"));
				task.setProject(project);
				Status status = new Status();
				status.setName(resultSet.getString("status_name"));
				task.setStatus(status);
				User user = new User();
				user.setFullname(resultSet.getString("user_name"));
				task.setUser(user);
				listTask.add(task);
			}
		} catch (Exception e) {
			System.out.println("Lỗi lấy danh sách task" + e.getLocalizedMessage());
		}
		return listTask;
	}

	public int insert(String name, String startDate, String endDate, int idProject, int idUser) {
		int count = 0;
		try {
			String taskQuery = "INSERT INTO task (name, start_date, end_date, id_project, id_status) "
					+ "VALUES (?, ?, ?, ?, 3)";

			String assigntaskQuery = "INSERT INTO assigntask (id_user, id_task, id_status) "
					+ "SELECT ?, (SELECT id FROM task WHERE name = ?), 3 " + "FROM users " + "WHERE id = ?";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement taskStatement = connection.prepareStatement(taskQuery);
			taskStatement.setString(1, name);
			taskStatement.setString(2, startDate);
			taskStatement.setString(3, endDate);
			taskStatement.setInt(4, idProject);
			count = taskStatement.executeUpdate();
			taskStatement.close();

			PreparedStatement assigntaskStatement = connection.prepareStatement(assigntaskQuery);
			assigntaskStatement.setInt(1, idUser);
			assigntaskStatement.setString(2, name);
			assigntaskStatement.setInt(3, idUser);
			count += assigntaskStatement.executeUpdate();
			assigntaskStatement.close();

			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi insert task " + e.getLocalizedMessage());
		}
		return count;
	}

	public List<Task> findTaskByUserId(int id) {
		List<Task> listTask = new ArrayList<Task>();
		try {
			String query = "SELECT t.id, t.name, t.start_date, t.end_date,p.id as id_project ,s.id as id_status  , u.fullname AS user_name\n"
					+ "FROM task t\n" + "JOIN project p ON t.id_project = p.id\n"
					+ "JOIN status s ON t.id_status = s.id\n" + "JOIN assigntask at ON at.id_task = t.id\n"
					+ "JOIN users u ON u.id = at.id_user WHERE at.id_user = " + id + ";";
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
				User user = new User();
				user.setFullname(resultSet.getString("user_name"));
				task.setUser(user);
				listTask.add(task);
			}
		} catch (Exception e) {
			System.out.println("Lỗi tìm task bằng user id" + e.getLocalizedMessage());
		}
		return listTask;
	}

	public int deleteTaskById(int id) {
		int count = 0;
		try {
			String assigntaskQuery = "DELETE FROM assigntask WHERE id_task = " + id + "";

			String taskQuery = "DELETE FROM task t WHERE t.id = " + id + ";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement assigntaskStatement = connection.prepareStatement(assigntaskQuery);
			count = assigntaskStatement.executeUpdate();
			assigntaskStatement.close();

			PreparedStatement taskStatement = connection.prepareStatement(taskQuery);
			count += taskStatement.executeUpdate();
			taskStatement.close();

			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi xóa task " + e.getLocalizedMessage());
		}
		return count;
	}

	public int updateTask(int id, String name, String startDate, String endDate, int idUser, int idStatus,int idUserOld) {
		int count = 0;
		try {
			String taskQuery = "UPDATE task t SET name = ?, start_date = ?, end_date=? , id_status=? "
					+ "WHERE t.id = ?; ";
			String assigntaskQuery = "UPDATE assigntask a SET a.id_user = ?,a.id_status = ? WHERE a.id_user =?;";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement taskStatement = connection.prepareStatement(taskQuery);
			taskStatement.setString(1, name);
			taskStatement.setString(2, startDate);
			taskStatement.setString(3, endDate);
			taskStatement.setInt(4, idStatus);
			taskStatement.setInt(5, id);
			count = taskStatement.executeUpdate();
			taskStatement.close();

			PreparedStatement assigntaskStatement = connection.prepareStatement(assigntaskQuery);
			assigntaskStatement.setInt(1, idUser);
			assigntaskStatement.setInt(2, idStatus);
			assigntaskStatement.setInt(3, idUserOld);
			count += assigntaskStatement.executeUpdate();
			assigntaskStatement.close();

			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi cập nhật công việc " + e.getLocalizedMessage());
		}
		return count;
	};

	public List<Status> getListStatus() {
		List<Status> listStatus = new ArrayList<>();
		try {
			String query = "SELECT * FROM status";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Status status = new Status();
				status.setId(resultSet.getInt("id"));
				status.setName(resultSet.getString("name"));
				listStatus.add(status);
			}
			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi lấy list status " + e.getLocalizedMessage());
		}
		return listStatus;
	}

	public Task findTaskById(int id) {
		Task task = new Task();
		try {
			String query = "SELECT t.id, t.name, t.start_date, t.end_date,t.id_project ,t.id_status , u.id AS id_user \n"
					+ "FROM task t JOIN assigntask a ON a.id_task  = t.id \n"
					+ "JOIN users u ON u.id = a.id_user  WHERE t.id ="+id+";";
			Connection connection = MySQLConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
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
				User user = new User();
				user.setId(resultSet.getInt("id_user"));
				task.setUser(user);
			}

			connection.close();
		} catch (Exception e) {
			System.out.println("Lỗi tìm công việc bằng ID " + e.getLocalizedMessage());
		}
		return task;
	}
}
