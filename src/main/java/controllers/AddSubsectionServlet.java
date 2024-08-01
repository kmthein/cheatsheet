package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Section;
import persistance.dao.SectionDAO;
import persistance.dao.SubsectionDAO;

/**
 * Servlet implementation class AddSubsectionServlet
 */
@WebServlet("/add-subsection")
public class AddSubsectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSubsectionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SectionDAO sectionDAO = new SectionDAO();
		List<Section> sections = sectionDAO.getAllSections();
		request.setAttribute("sections", sections);
		request.getRequestDispatcher("addSubsection.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		int sectionId = Integer.parseInt(request.getParameter("section"));
		SubsectionDAO subsectionDAO = new SubsectionDAO();
		int result = subsectionDAO.addNewSubSection(name, type, sectionId);
		if (result == 1) {
			response.sendRedirect("subsections");
		}
	}

}
