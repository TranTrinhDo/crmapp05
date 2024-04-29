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
import service.RoleService;
import service.TaskService;

@WebServlet(name = "taskApiController", urlPatterns = { "/api/task/delete", "/api/task/update" })
public class TaskApiController extends HttpServlet {
	private Gson gson = new Gson();
	private TaskService taskService = new TaskService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean iSuccess = taskService.deleteTaskById(id);
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
		if (servletPath.equals("/api/task/update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			String nameUpdate = req.getParameter("taskName");
			String startDateUpdate = req.getParameter("startDate");
			String endDateUpdate = req.getParameter("endDate");
			int idUserUpdate = Integer.parseInt(req.getParameter("idUser"));
			int idStatusUpdate = Integer.parseInt(req.getParameter("idStatus"));
			boolean isSuccess = taskService.updateTask(id, nameUpdate, startDateUpdate, endDateUpdate, idUserUpdate,
					idStatusUpdate);

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
