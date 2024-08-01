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

public class SubsectionDAO {
	public static Connection con = null;

	static {
		con = DBHelper.getConnection();
	}

	public List<Subsection> getAllSubsections() {
		List<Subsection> subsections = new ArrayList<>();
		String query = "SELECT * FROM subsection";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				Subsection subsection = new Subsection();
				subsection.setId(set.getInt("id"));
				subsection.setName(set.getString("name"));
				subsection.setType(set.getString("type"));
				int sectionId = set.getInt("section_id");
				SectionDAO sectionDAO = new SectionDAO();
				Section section = sectionDAO.getSectionById(sectionId);
				if (section != null) {
					subsection.setSection(section);
				}
				Timestamp timestamp = set.getTimestamp("updated_at");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDateTime updatedAt = timestamp.toLocalDateTime();
				subsection.setUpdatedAtFormatted(updatedAt.format(formatter));
				subsections.add(subsection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subsections;
	}

	public int addNewSubSection(String name, String type, int sectionId) {
		int result = 0;
		String query = "INSERT INTO subsection(name, type, section_id) VALUES(?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setString(2, type);
			stmt.setInt(3, sectionId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
