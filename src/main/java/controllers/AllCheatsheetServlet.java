package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cheatsheet;
import persistance.dao.CheatsheetDAO;

/**
 * Servlet implementation class AllCheatsheetServlet
 */
@WebServlet("/cheatsheets")
public class AllCheatsheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllCheatsheetServlet() {
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
		CheatsheetDAO cheatsheetDAO = new CheatsheetDAO();
		List<Cheatsheet> cheatsheets = new ArrayList<>();
		cheatsheets = cheatsheetDAO.getAllCheatsheets();
		request.setAttribute("cheatsheets", cheatsheets);
		request.getRequestDispatcher("allCheatsheets.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
