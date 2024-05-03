package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TaskService;
import service.UserService;

@WebServlet(name = "profileController", urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
	private UserService userService = new UserService();
	private TaskService taskSerivce = new TaskService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if (servletPath.equals("/profile")) {
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
			;
		}
	}
}
