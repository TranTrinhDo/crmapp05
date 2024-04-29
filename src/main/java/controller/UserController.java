package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.MySQLConfig;
import entity.Role;
import entity.User;
import service.RoleService;
import service.TaskService;
import service.UserService;

@WebServlet(name = "userController", urlPatterns = { "/add-user", "/users", "/update-user","/user-details" })
public class UserController extends HttpServlet {

	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	private TaskService taskSerivce = new TaskService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if (servletPath.equals("/add-user")) {
			req.setAttribute("listRole", roleService.getListRole());
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		} else if (servletPath.equals("/users")) {
			List<User> list = userService.getListUser();
			req.setAttribute("listUser", list);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		} else if (servletPath.equals("/update-user")) {
			int id = Integer.parseInt(req.getParameter("id"));
			User user = userService.findUserById(id);
			req.setAttribute("user", user);
			req.setAttribute("listRole", roleService.getListRole());
			req.getRequestDispatcher("user-update.jsp").forward(req, resp);
		} else if(servletPath.equals("/user-details")) {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("user", userService.findUserById(id));
			req.setAttribute("tasks", taskSerivce.findTaskByUserId(id));
			req.getRequestDispatcher("user-details.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
        switch (servletPath) {
            case "/update-user":
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("roles", roleService.getListRole());
                req.getSession().setAttribute("user", userService.findUserById(id));
                req.getRequestDispatcher("/user-update.jsp").forward(req,resp);
                break;
        }

		String fullnameAdd = req.getParameter("fullname");
		String emailAdd = req.getParameter("email");
		String passwordAdd = req.getParameter("password");
		String phoneAdd = req.getParameter("phone");
		int idRoleAdd = Integer.parseInt(req.getParameter("idRole"));
		if (fullnameAdd == null || fullnameAdd.isEmpty() || emailAdd == null || emailAdd.isEmpty()
				|| passwordAdd == null || passwordAdd.isEmpty() || phoneAdd == null || phoneAdd.isEmpty()) {
			System.out.println("Vui lòng nhập đầy đủ thông tin");
			resp.sendRedirect(req.getContextPath() + "/add-user");
		} else {
			boolean iSuccess = userService.insertUser(fullnameAdd, emailAdd, passwordAdd, phoneAdd, idRoleAdd);
			if (iSuccess == true) {
				resp.sendRedirect(req.getContextPath() + "/users");
			} else {
				resp.sendRedirect(req.getContextPath() + "/add-user");
			}
		}
		

	}
}
