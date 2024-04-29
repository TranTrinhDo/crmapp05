package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entity.Project;
import entity.Status;
import entity.Task;
import repository.ProjectRepository;
import repository.TaskRepository;
import utils.DateFormat;

public class TaskService {

	private ProjectRepository projectRepository = new ProjectRepository();
	private TaskRepository taskRepository = new TaskRepository();
	private DateFormat dateFormat = new DateFormat();

	public List<Task> getListTask() {
		List<Task> listTask = taskRepository.getListTask();
		for (Task task : listTask) {
			task.setStartDate(dateFormat.changeFormatDate(task.getStartDate(), "/"));
			task.setEndDate(dateFormat.changeFormatDate(task.getEndDate(), "/"));
		}
		return listTask;
	}

	public List<Project> getProjectName() {
		return projectRepository.getProjectName();
	}

	public boolean insertTask(String name, String startDate, String endDate, int idProject, int idUser) {
		String dataStartDate = dateFormat.changeFormatDate(startDate, "-");
		String dataEndDate = dateFormat.changeFormatDate(endDate, "-");
		int count = taskRepository.insert(name, dataStartDate, dataEndDate, idProject, idUser);
		return count > 0;
	}

	public List<Task> findTaskByUserId(int id) {
		List<Task> listTask = taskRepository.findTaskByUserId(id);
		for (Task task : listTask) {
			task.setStartDate(dateFormat.changeFormatDate(task.getStartDate(), "/"));
			task.setEndDate(dateFormat.changeFormatDate(task.getEndDate(), "/"));
		}
		return listTask;
	}

	public boolean deleteTaskById(int id) {
		int count = taskRepository.deleteTaskById(id);
		return count > 0;
	}

	public List<Status> getListStatus() {
		return taskRepository.getListStatus();
	}

	public boolean updateTask(int id, String name, String startDate, String endDate, int idUser, int idStatus) {
		String dataStartDate = dateFormat.changeFormatDate(startDate, "-");
		String dataEndDate = dateFormat.changeFormatDate(endDate, "-");
		int count = taskRepository.updateTask(id, name, dataStartDate, dataEndDate, idUser, idStatus);
		return count > 0;
	}

	public Task findTaskById(int id) {
		Task task = taskRepository.findTaskById(id);
		task.setStartDate(dateFormat.changeFormatDate(task.getStartDate(), "/"));
		task.setEndDate(dateFormat.changeFormatDate(task.getEndDate(), "/"));
		return task;
	}

	
}
