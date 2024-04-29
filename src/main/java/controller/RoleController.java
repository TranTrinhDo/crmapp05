package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Role;
import service.RoleService;

@WebServlet(name = "roleController", urlPatterns = { "/add-role", "/roles", "/update-role" })
public class RoleController extends HttpServlet {
	private RoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if (servletPath.equals("/add-role")) {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		} else if (servletPath.equals("/roles")) {
			List<Role> listRole = roleService.getListRole();
			req.setAttribute("listRole", listRole);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		} else if (servletPath.equals("/update-role")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Role role = roleService.findRoleById(id);
			req.getSession().setAttribute("role", role);
			req.getRequestDispatcher("role-update.jsp").forward(req, resp);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletPath = req.getServletPath();
		switch (servletPath) {
		case "/update-role":
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("role-name");
			String description1 = req.getParameter("description");
			Role role = new Role(id, name, description1);
			req.getSession().setAttribute("role", role);
			req.getRequestDispatcher("/role-update.jsp").forward(req, resp);
			break;
		case "/add-role":
			String roleName = req.getParameter("rolename");
			String description = req.getParameter("description");
			if (roleName == null || roleName.isEmpty() || description == null || description.isEmpty()) {
				System.out.println("Vui lòng nhập đầy đủ thông tin");
				resp.sendRedirect(req.getContextPath() + "/add-role");
			} else {
				boolean isSuccess = roleService.insertRole(roleName, description);
				if (isSuccess == true) {
					resp.sendRedirect(req.getContextPath() + "/roles");
				} else {
					resp.sendRedirect(req.getContextPath() + "/add-role");
				}
			}
			break;
		}

	}
}
