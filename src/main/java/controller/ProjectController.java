package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Project;
import service.ProjectService;

@WebServlet(name = "projectController", urlPatterns = { "/add-project", "/projects", "/project-detail",
		"/update-project" })
public class ProjectController extends HttpServlet {
	private ProjectService projectService = new ProjectService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if (servletPath.equals("/add-project")) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		} else if (servletPath.equals("/projects")) {
			List<Project> listProject = projectService.getListProject();
			req.setAttribute("listProject", listProject);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		} else if (servletPath.equals("/project-detail")) {
			int id = Integer.parseInt(req.getParameter("id"));
			req.getSession().setAttribute("projectDetail", projectService.getProjectDetailByProjectId(id));
			req.getRequestDispatcher("groupwork-detail.jsp").forward(req, resp);
		} else if (servletPath.equals("/update-project")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Project project = projectService.findProjectById(id);
			req.getSession().setAttribute("project", project);
			req.getRequestDispatcher("groupwork-update.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if (servletPath.equals("/update-project")) {
			int id = Integer.parseInt(req.getParameter("id"));
			String projectNameUpdate = req.getParameter("projectName");
			String startDateUpdate = req.getParameter("dateStart");
			String endDateUpdate = req.getParameter("dateEnd");
			Project project = new Project(id, projectNameUpdate, startDateUpdate, endDateUpdate);
			req.getSession().setAttribute("project", project);
			req.getRequestDispatcher("groupwork-update.jsp").forward(req, resp);
		} else if (servletPath.equals("/add-project")) {
			String projectName = req.getParameter("projectname");
			String startDate = req.getParameter("dateStart");
			String endDate = req.getParameter("dateEnd");
			if (projectName == null || projectName.isEmpty() || startDate == null || startDate.isEmpty()
					|| endDate == null || endDate.isEmpty()) {
				System.out.println("Vui lòng nhập thông tin đầy đủ");
				resp.sendRedirect(req.getContextPath() + "/add-project");
			} else {
				boolean iSuccess = projectService.insertProject(projectName, startDate, endDate);
				if (iSuccess == true) {
					System.out.println("Thêm dự án thành công");
					resp.sendRedirect(req.getContextPath() + "/projects");
				} else {
					System.out.println("Thêm dự án thất bại");
					resp.sendRedirect(req.getContextPath() + "/add-project");
				}

			}
		}
	}
}
