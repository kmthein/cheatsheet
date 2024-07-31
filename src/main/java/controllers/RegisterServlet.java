package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jbcrypt.BCrypt;
import model.Role;
import model.User;
import persistance.dao.UserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String website = request.getParameter("website");
		String description = request.getParameter("description");

		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		UserDAO userDAO = new UserDAO();
		User userExist = userDAO.getUserByEmail(email);
		User enterUser = new User();
		enterUser.setEmail(email);
		enterUser.setPassword(password);
		enterUser.setName(name);
		enterUser.setWebsite(website);
		enterUser.setDescription(description);
		if (userExist != null) {
			request.setAttribute("error", "Email already taken");
			request.setAttribute("user", enterUser);
			request.getRequestDispatcher("registerPage.jsp").forward(request, response);
		}
		User user = new User();
		user.setEmail(email);
		user.setPassword(hashedPassword);
		user.setName(name);
		user.setWebsite(website);
		user.setDescription(description);
		user.setRole(Role.USER);
		int result = userDAO.registerUser(user);
		if (result > 0) {
			response.getWriter().println("User registered successfully!");
//			HttpSession session = request.getSession();
//			session.setAttribute("user", user);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("user", enterUser);
			request.getRequestDispatcher("registerPage.jsp").forward(request, response);
		}
	}

}
