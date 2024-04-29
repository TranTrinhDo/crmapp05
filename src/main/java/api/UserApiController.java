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
import entity.User;
import reponse.BaseReponse;
import service.UserService;

@WebServlet(name = "userApiController", urlPatterns = { "/api/user/delete", "/api/user/update" })
public class UserApiController extends HttpServlet {

	/*
	 * { statusCode : 200, message: "", data: Object }
	 */

	private Gson gson = new Gson();
	private UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));

		boolean iSuccess = userService.deleteUserById(id);

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
		BaseReponse baseResponse = new BaseReponse();
		String servletPath = req.getServletPath();
		if (servletPath.equals("/api/user/update")) {
				int id = Integer.parseInt(req.getParameter("id"));
				String fullname = req.getParameter("fullname");
				String email = req.getParameter("email");
				String password = req.getParameter("password");
				String phone = req.getParameter("phone");
				String firstName = req.getParameter("firstname");
				String lastName = req.getParameter("lastname");
				int idRole = Integer.parseInt(req.getParameter("idRole"));

				boolean isSuccessUpdate = userService.updateUser(id, fullname, email, password, phone, firstName,
						lastName, idRole);

				baseResponse.setStatusCode(200);
				baseResponse.setMessage(isSuccessUpdate ? "Cập nhật thành công" : "Cập nhật thất bại");
				baseResponse.setData(isSuccessUpdate);
		}

		String json = gson.toJson(baseResponse);
		resp.setHeader("Content-Type", "application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = resp.getWriter();
		printWriter.write(json);
		printWriter.close();
	}
}
