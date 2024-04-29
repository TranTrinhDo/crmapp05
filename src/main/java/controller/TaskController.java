package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Project;
import entity.Status;
import entity.Task;
import entity.User;
import service.ProjectService;
import service.TaskService;
import service.UserService;

@WebServlet(name = "taskController", urlPatterns = { "/add-task", "/tasks", "/update-task" })
public class TaskController extends HttpServlet {
	private TaskService taskService = new TaskService();
	private UserService userService = new UserService();
	private ProjectService projectService = new ProjectService();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if (servletPath.equals("/add-task")) {
			req.setAttribute("listUser", userService.getAllUser());
			req.setAttribute("listProject", taskService.getProjectName());
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		} else if (servletPath.equals("/tasks")) {
			List<Task> listTask = taskService.getListTask();
			req.setAttribute("listTask", listTask);
			req.getRequestDispatcher("task.jsp").forward(req, resp);
		} else if (servletPath.equals("/update-task")) {
			List<Status> listStatus = taskService.getListStatus();
			req.setAttribute("listStatus", listStatus);
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("task", taskService.findTaskById(id));
			req.setAttribute("listProject", taskService.getProjectName());
			req.setAttribute("listUser", userService.getAllUser());
			req.getRequestDispatcher("task-update.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletPath = req.getServletPath();
		switch (servletPath) {
		case "/update-task":
            int id = Integer.parseInt(req.getParameter("id"));
            req.getSession().setAttribute("project", projectService.getListProject());
            req.getSession().setAttribute("user", userService.getAllUser());
            req.getSession().setAttribute("status", taskService.getListStatus());
            req.getSession().setAttribute("task", taskService.findTaskByUserId(id));
			req.getRequestDispatcher("task-update.jsp").forward(req, resp);
			break;
		case "/add-task":
			String taskName = req.getParameter("taskName");
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			int idProject = Integer.parseInt(req.getParameter("idProject"));
			int idUser = Integer.parseInt(req.getParameter("idUser"));
			if (taskName == null || taskName.isEmpty() || startDate == null || startDate.isEmpty() || endDate == null
					|| endDate.isEmpty()) {
				System.out.println("Vui lòng nhập đầy đủ thông tin");
				resp.sendRedirect(req.getContextPath() + "/add-task");
			} else {
				boolean iSuccess = taskService.insertTask(taskName, startDate, endDate, idProject, idUser);
				if (iSuccess == true) {
					System.out.println("Thêm công việc thành công");
					resp.sendRedirect(req.getContextPath() + "/tasks");
				} else {
					System.out.println("Thêm công việc thất bại");
					resp.sendRedirect(req.getContextPath() + "/add-task");
				}
			}
			break;
		}
	}
}
