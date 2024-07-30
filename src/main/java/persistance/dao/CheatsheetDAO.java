package persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import helper.DBHelper;
import model.Cheatsheet;
import model.User;

public class CheatsheetDAO {
	public static Connection con = null;
	
	static {
		con = DBHelper.getConnection();
	}
	
	public List<Cheatsheet> getAllCheatsheets() {
		List<Cheatsheet> cheatsheets = new ArrayList<>();
		
		String query = "SELECT * FROM cheatsheet";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			while(set.next()) {
				Cheatsheet cheatsheet = new Cheatsheet();
				cheatsheet.setId(set.getInt("id"));
				cheatsheet.setName(set.getString("name"));
				cheatsheet.setDescription(set.getString("description"));
				cheatsheet.setColor(set.getString("color"));
				cheatsheet.setContent(set.getString("content"));
				cheatsheet.setStyle(set.getString("style"));
				cheatsheet.setType(set.getString("type"));
				UserDAO userDAO = new UserDAO();
				User user = userDAO.getUserById(set.getInt("user_id"));
				if (user != null) {
					System.out.println(user.getEmail());
					cheatsheet.setUser(user);
				}
				cheatsheets.add(cheatsheet);
			}
		} catch (SQLException e) {
			System.out.println("select error: " + e);
		}
		
		return cheatsheets;
	}
}
