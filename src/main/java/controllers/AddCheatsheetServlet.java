package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Section;
import model.User;
import persistance.dao.CheatsheetDAO;
import persistance.dao.SectionDAO;

/**
 * Servlet implementation class AddCheatsheetServlet
 */
@WebServlet("/add-cheatsheet")
public class AddCheatsheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCheatsheetServlet() {
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
		SectionDAO sectionDAO = new SectionDAO();
		List<Section> sections = sectionDAO.getAllSections();
		request.setAttribute("sections", sections);
		request.getRequestDispatcher("createCheatsheet.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String title = request.getParameter("title");
		String color = request.getParameter("color");
		System.out.println(color);
		String language = request.getParameter("language");
		String style = request.getParameter("style");
		String type = request.getParameter("type");
		int section = Integer.parseInt(request.getParameter("section"));
		int subsection = Integer.parseInt(request.getParameter("subsection"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		String layout = request.getParameter("layout");
		StringBuilder contentBuilder = new StringBuilder();
		contentBuilder.append("<table>");
		contentBuilder.append("<tr><th>").append(title).append("</th></tr>");
		int columnNumber = 1;
		boolean isNewRow = true;
		while (request.getParameter("column" + columnNumber) != null) {
			if (isNewRow) {
				contentBuilder.append("<tr>");
				isNewRow = false;
			}
			String columnValue = request.getParameter("column" + columnNumber);
			contentBuilder.append("<td>").append(columnValue).append("</td>");

			if (columnNumber % 2 == 0) {
				contentBuilder.append("</tr>");
				isNewRow = true;
			}
			columnNumber++;
		}
		if (!isNewRow) {
			contentBuilder.append("</tr>");
		}
		contentBuilder.append("</table>");
		String content = contentBuilder.toString();
		CheatsheetDAO cheatsheetDAO = new CheatsheetDAO();
		int result = cheatsheetDAO.addCheatSheet(name, description, color, language, content, style, type, userId, section, subsection);
		if(result == 1) {
			request.getRequestDispatcher("cheatsheets").forward(request, response);
		} else {
			request.getRequestDispatcher("createCheatsheet.jsp").forward(request, response);
		}
	}

}
