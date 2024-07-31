package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jbcrypt.BCrypt;
import model.User;
import persistance.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();
		User userExist = userDAO.getUserByEmail(email);
		String storedHashPassword = userExist.getPassword();
		if(userExist == null) {
			request.setAttribute("error", "Email not existed!");
			request.setAttribute("email", email);
			request.getRequestDispatcher("loginPage.jsp").forward(request, response);
		} else {
			Boolean passValid = BCrypt.checkpw(password, storedHashPassword);
			if(!passValid) {
				request.setAttribute("error", "Email or password wrong!");
				request.setAttribute("email", email);
				request.getRequestDispatcher("loginPage.jsp").forward(request, response);
			}
			HttpSession session = request.getSession();
			User user = new User();
			user.setName(userExist.getName());
			user.setEmail(userExist.getEmail());
			user.setId(userExist.getId());
			user.setPassword(password);
			user.setDescription(userExist.getDescription());
			user.setWebsite(userExist.getWebsite());
//			userExist.setPassword(password);
			session.setAttribute("user", user);
			response.sendRedirect("home");	
		}
	}

}
