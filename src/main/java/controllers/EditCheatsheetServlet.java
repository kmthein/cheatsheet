package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.Cheatsheet;
import model.Section;
import model.Subsection;
import model.User;
import persistance.dao.CheatsheetDAO;
import persistance.dao.SectionDAO;
import persistance.dao.SubsectionDAO;

/**
 * Servlet implementation class EditCheatsheetServlet
 */
@WebServlet("/edit-cheatsheet")
public class EditCheatsheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCheatsheetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		SectionDAO sectionDAO = new SectionDAO();
		List<Section> sections = sectionDAO.getAllSections();
		request.setAttribute("sections", sections);
		CheatsheetDAO cheatsheetDAO = new CheatsheetDAO();
		Cheatsheet cheatsheet = cheatsheetDAO.getCheatsheetById(id);
		if(cheatsheet != null) {
			int sectionId = cheatsheet.getSection().getId();
			SubsectionDAO subsectionDAO = new SubsectionDAO();
			List<Subsection> subsections = subsectionDAO.getSubsectionBySectionId(sectionId);
			request.setAttribute("subsections", subsections);
			request.setAttribute("cs", cheatsheet);
			String title = parseCheatsheetTitle(cheatsheet.getContent());
			List<String[]> columns = parseCheatsheetContent(cheatsheet.getContent());
			request.setAttribute("title", title);
	        request.setAttribute("columns", columns);
			request.getRequestDispatcher("editCheatsheet.jsp").forward(request, response);
		}
	}
	
	private String parseCheatsheetTitle(String content) {
	    Document doc = Jsoup.parse(content);
	    Element th = doc.selectFirst("th");
	    return th != null ? th.text() : "";
	}

	private List<String[]> parseCheatsheetContent(String content) {
	    List<String[]> columns = new ArrayList<>();
	    Document doc = Jsoup.parse(content);
	    Elements rows = doc.select("tr");

	    for (Element row : rows) {
	        Elements cells = row.select("td");
	        if (cells.size() == 2) {
	            columns.add(new String[]{cells.get(0).text(), cells.get(1).text()});
	        }
	    }
	    return columns;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int cheatsheetId = Integer.parseInt(request.getParameter("id"));
	    String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    String title = request.getParameter("title");
	    String color = request.getParameter("color");
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
	    contentBuilder.append("<tr><th>").append(title).append("</th><th></th></tr>");
	    
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
	    CheatsheetDAO cheatsheetDAO = new CheatsheetDAO();
	    int result = cheatsheetDAO.updateCheatSheet(name, description, color, language, content, style, type, userId, section, subsection, cheatsheetId);
	    if (result == 1) {
	        request.getRequestDispatcher("cheatsheets").forward(request, response);
	    } else {
	        request.getRequestDispatcher("editCheatsheet.jsp").forward(request, response);
	    }
	}

}
