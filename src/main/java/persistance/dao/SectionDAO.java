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
import model.Section;
import model.Subsection;

public class SectionDAO {
	public static Connection con = null;
	
	static {
		con = DBHelper.getConnection();
	}
	
	public Section checkSectionExistByName(String name) {
		Section section = null;
		String query = "SELECT * FROM section WHERE name = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			ResultSet set = stmt.executeQuery();
			while(set.next()) {
				section = new Section();
				section.setId(set.getInt("id"));
				section.setName(set.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return section;
	}
	
	public Section getSectionById(int id) {
		Section section = null;
		String query = "SELECT * FROM section WHERE id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet set= stmt.executeQuery();
			while(set.next()) {
				section = new Section();
				section.setId(set.getInt("id"));
				section.setName(set.getString("name"));
				Timestamp timestamp = set.getTimestamp("updated_at");
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDateTime updatedAt = timestamp.toLocalDateTime();
				section.setUpdatedAtFormatted(updatedAt.format(formatter));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return section;
	}
	
	public List<Section> getAllSections() {
		List<Section> sections = new ArrayList<>();
		String query = "SELECT * FROM section";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			while(set.next()) {
				Section section = new Section();
				section.setId(set.getInt("id"));
				section.setName(set.getString("name"));
				Timestamp timestamp = set.getTimestamp("updated_at");
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDateTime updatedAt = timestamp.toLocalDateTime();
				section.setUpdatedAtFormatted(updatedAt.format(formatter));
				sections.add(section);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return sections;
	}
	
	public int addNewSection(String name) {
		int result = 0;
		String query = "INSERT INTO section(name) VALUES(?)";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
