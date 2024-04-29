package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Project;
import entity.ProjectDetail;
import entity.Task;
import entity.User;
import repository.ProjectRepository;
import utils.DateFormat;

public class ProjectService {

	private ProjectRepository projectRepository = new ProjectRepository();
	private DateFormat dateFormat = new DateFormat();


	public List<Project> getListProject() {
		List<Project> listProject = projectRepository.getListProject();
		for(Project project : listProject) {
			project.setStartDate(dateFormat.changeFormatDate(project.getStartDate(), "/"));
			project.setEndDate(dateFormat.changeFormatDate(project.getEndDate(), "/"));
		}
		return listProject;
	}

	public boolean insertProject(String name, String startDate, String endDate) {
		String dataStartDate = dateFormat.changeFormatDate(startDate, "-");
		String dataEndDate = dateFormat.changeFormatDate(endDate, "-");
		int count = projectRepository.insert(name, dataStartDate, dataEndDate);
		return count > 0;
	}

	public boolean deleteProjectById(int id) {
		int count = projectRepository.deleteProjectById(id);
		return count > 0;
	}
	
	public boolean updateProject(int id,String name,String startDate,String endDate) {
		String dataStartDate = dateFormat.changeFormatDate(startDate, "-");
		String dataEndDate = dateFormat.changeFormatDate(endDate, "-");
		int count = projectRepository.updateProject(id, name, dataStartDate, dataEndDate);
		return count >0;
	}
	
	public Project findProjectById(int id) {
		Project project = projectRepository.findProjectById(id);
		project.setStartDate(dateFormat.changeFormatDate(project.getStartDate(), "/"));
		project.setEndDate(dateFormat.changeFormatDate(project.getEndDate(), "/"));
		return project;
	}
	
	public List<ProjectDetail> getProjectDetailByProjectId(int id){
		List<ProjectDetail> projectDetails = new ArrayList<>();
		List<User> users = projectRepository.getUserByProjectId(id);
		for(User user : users) {
			ProjectDetail projectDetail = new ProjectDetail();
			projectDetail.setIdUser(user.getId());
			projectDetail.setUserName(user.getFullname());
			List<Task> tasks = projectRepository.getTaskByProjectIdAndUserId(id, user.getId());
			projectDetail.setTask(tasks);
			projectDetails.add(projectDetail);
		}
		return projectDetails;
	}
}
