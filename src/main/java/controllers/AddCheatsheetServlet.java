package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String description = request.getParameter("description");
		String title = request.getParameter("title");
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
		System.out.println(content);
		doGet(request, response);
	}

}
