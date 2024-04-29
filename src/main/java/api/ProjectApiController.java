package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import reponse.BaseReponse;
import service.ProjectService;

@WebServlet(name = "projectApiController", urlPatterns = { "/api/project/delete", "/api/project/update" })
public class ProjectApiController extends HttpServlet {
	private Gson gson = new Gson();
	private ProjectService projectService = new ProjectService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		boolean iSuccess = projectService.deleteProjectById(id);
		BaseReponse baseReponse = new BaseReponse();
		baseReponse.setStatusCode(200);
		baseReponse.setMessage(iSuccess ? "Xóa thành công " : "Xóa thất bại");
		baseReponse.setData(iSuccess);

		String json = gson.toJson(baseReponse);
		resp.setHeader("Content-Type", "application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = resp.getWriter();
		printWriter.write(json);
		printWriter.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BaseReponse baseReponse = new BaseReponse();
		String servletPath = req.getServletPath();
		if (servletPath.equals("/api/project/update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("projectName");
			String startDate = req.getParameter("dateStart");
			String endDate = req.getParameter("dateEnd");
			boolean isSuccess = projectService.updateProject(id, name, startDate, endDate);

			baseReponse.setStatusCode(200);
			baseReponse.setMessage(isSuccess ? "Cập nhật thành công " : "Cập nhật thất bại");
			baseReponse.setData(isSuccess);
		}
		String json = gson.toJson(baseReponse);
		resp.setHeader("Content-Type", "application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = resp.getWriter();
		printWriter.write(json);
		printWriter.close();
	}
}
