package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import entity.Role;
import reponse.BaseReponse;
import service.RoleService;

@WebServlet(name = "roleApiController", urlPatterns = { "/api/role/delete", "/api/role/update" })
public class RoleApiController extends HttpServlet {
	private Gson gson = new Gson();
	private RoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		boolean iSuccess = roleService.deleteRoleById(id);
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
		if (servletPath.equals("/api/role/update")) {
			int id = Integer.parseInt(req.getParameter("id"));

			String name = req.getParameter("roleName");
			String description = req.getParameter("description");
			boolean isSuccess = roleService.updateRole(id, name, description);
			baseReponse.setStatusCode(200);
			baseReponse.setMessage(isSuccess ? "Sửa thành công " : "Sửa thất bại");
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