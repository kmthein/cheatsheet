package persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helper.DBHelper;
import model.User;

public class UserDAO {
public static Connection con = null;
	
	static {
		con = DBHelper.getConnection();
	}
	
	public User getUserById(int id) {
		User user = null;
		String query = "SELECT * FROM user WHERE id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet set = stmt.executeQuery();
			while(set.next()) {
				user = new User();
				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setName(set.getString("name"));
				user.setWebsite(set.getString("website"));
				user.setDescription(set.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
