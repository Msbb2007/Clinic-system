package dao;

import db.DatabaseConnection;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    RoleDao roleDao = new RoleDao();

    // چک وجود username
    public boolean usernameExists(String username) {

        String sql = "SELECT id FROM users WHERE username=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ثبت کاربر
    public boolean register(String username, String password, String roleName) {

        if (usernameExists(username)) {
            return false;
        }

        Role role = roleDao.findByName(roleName);

        String sql = "INSERT INTO users(username,password,role_id,role) VALUES(?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, role.getId());
            ps.setString(4,role.getName());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // login
    public User login(String username, String password) {

        String sql = """
               SELECT u.id,u.username,u.password,r.name as role
               FROM users u
               JOIN roles r ON u.role_id = r.id
               WHERE u.username=? AND u.password=?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRoleName(rs.getString("role"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
