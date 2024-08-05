package persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import helper.DBHelper;
import model.Cheatsheet;
import model.Section;
import model.Subsection;
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
					cheatsheet.setUser(user);
				}
				Timestamp timestamp = set.getTimestamp("updated_at");
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDateTime updatedAt = timestamp.toLocalDateTime();
				cheatsheet.setUpdatedAtFormatted(updatedAt.format(formatter));
				cheatsheets.add(cheatsheet);
			}
		} catch (SQLException e) {
			System.out.println("select error: " + e);
		}
		
		return cheatsheets;
	}
	
	public Cheatsheet getCheatsheetById(int id) {
		Cheatsheet cheatsheet = null;
		String query = "SELECT * FROM cheatsheet WHERE id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet set = stmt.executeQuery();
			while(set.next()) {
				cheatsheet = new Cheatsheet();
				cheatsheet.setId(set.getInt("id"));
				cheatsheet.setName(set.getString("name"));
				cheatsheet.setDescription(set.getString("description"));
				cheatsheet.setColor(set.getString("color"));
				cheatsheet.setContent(set.getString("content"));
				cheatsheet.setStyle(set.getString("style"));
				cheatsheet.setType(set.getString("type"));
				cheatsheet.setLanguage(set.getString("language"));
				SectionDAO sectionDAO = new SectionDAO();
				SubsectionDAO subsectionDAO = new SubsectionDAO();
				Section section = sectionDAO.getSectionById(set.getInt("section_id"));
				Subsection subsection = subsectionDAO.getSubsectionById(set.getInt("subsection_id"));
				if(section != null && subsection != null) {
					cheatsheet.setSection(section);
					cheatsheet.setSubsection(subsection);
				}
				UserDAO userDAO = new UserDAO();
				User user = userDAO.getUserById(set.getInt("user_id"));
				if (user != null) {
					cheatsheet.setUser(user);
				}
				Timestamp timestamp = set.getTimestamp("updated_at");
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDateTime updatedAt = timestamp.toLocalDateTime();
				cheatsheet.setUpdatedAtFormatted(updatedAt.format(formatter));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cheatsheet;
	}
	
	public int addCheatSheet(String name, String description, String color, String language, String content, String style, String type, int userId, int sectionId, int subsectionId) {
		int result = 0;
		String query = "INSERT INTO cheatsheet(name, description, color, language, content, style, type, user_id, section_id, subsection_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setString(3, color);
			stmt.setString(4, language);
			stmt.setString(5, content);
			stmt.setString(6, style);
			stmt.setString(7, type);
			stmt.setInt(8, userId);
			stmt.setInt(9, sectionId);
			stmt.setInt(10, subsectionId);
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateCheatSheet(String name, String description, String color, String language, String content, String style, String type, int userId, int sectionId, int subsectionId, int cheatsheetId) {
		int result = 0;
		String query = "UPDATE cheatsheet SET name = ?, description = ?, color = ?, language = ?, content = ?, style = ?, type = ?, user_id = ?, section_id = ?, subsection_id = ? WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setString(3, color);
			stmt.setString(4, language);
			stmt.setString(5, content);
			stmt.setString(6, style);
			stmt.setString(7, type);
			stmt.setInt(8, userId);
			stmt.setInt(9, sectionId);
			stmt.setInt(10, subsectionId);
			stmt.setInt(11, cheatsheetId);
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
