package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Subsection;
import persistance.dao.SubsectionDAO;

/**
 * Servlet implementation class GetSubsectionsServlet
 */
@WebServlet("/getSubsections")
public class GetSubsectionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSubsectionsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sectionId = Integer.parseInt(request.getParameter("sectionId"));
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		JSONArray subsections = new JSONArray();
		SubsectionDAO subsectionDAO = new SubsectionDAO();
		List<Subsection> subs = subsectionDAO.getSubsectionBySectionId(sectionId); 
		
		for (Subsection sub : subs) {
			JSONObject subsection = new JSONObject();
			subsection.put("id", sub.getId());
			subsection.put("name", sub.getName());
			subsections.put(subsection);
		}
		PrintWriter out = response.getWriter();
        out.print(subsections);
        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
