package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Section;
import model.Subsection;
import persistance.dao.SectionDAO;
import persistance.dao.SubsectionDAO;

/**
 * Servlet implementation class AddSectionServlet
 */
@WebServlet("/subsections")
public class AllSubsectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllSubsectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SubsectionDAO subsectionDAO = new SubsectionDAO();
		List<Subsection> subsections = subsectionDAO.getAllSubsections();
		request.setAttribute("subsections", subsections);
		request.getRequestDispatcher("allSubsection.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		SectionDAO sectionDAO = new SectionDAO();
		Section sectionExist =  sectionDAO.checkSectionExistByName(name);
		if(sectionExist != null) {
			request.setAttribute("error", "Section name already existed!");
			request.getRequestDispatcher("addSection.jsp").forward(request, response);
		} else {
			int result = sectionDAO.addNewSection(name);
			if(result == 1) {
				response.sendRedirect("sections");
			}			
		}
	}

}
