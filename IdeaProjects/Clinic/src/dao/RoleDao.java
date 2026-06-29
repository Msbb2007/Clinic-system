package dao;

import db.DatabaseConnection;
import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RoleDao {

    public Role findByName(String name) {

        String sql = "SELECT * FROM roles WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));

                return role;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
