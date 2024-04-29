package entity;

import java.util.List;

public class ProjectDetail {

	private int idUser;
	private String userName;
	List<Task> task;
	
	public ProjectDetail() {}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}

	public ProjectDetail(int idUser, String userName, List<Task> task) {
		this.idUser = idUser;
		this.userName = userName;
		this.task = task;
	}
	
}
