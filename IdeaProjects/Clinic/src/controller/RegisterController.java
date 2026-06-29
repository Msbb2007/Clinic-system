package controller;

import dao.UserDao;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    private TextField usernameField;
    private PasswordField passwordField;
    private ComboBox<String> roleBox;
    private Label message;

    private UserDao userDao = new UserDao();

    public RegisterController(TextField usernameField,
                              PasswordField passwordField,
                              ComboBox<String> roleBox,
                              Label message) {

        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.roleBox = roleBox;
        this.message = message;
    }

    public void handleRegister() {

        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleBox.getValue();

        if (username.isEmpty() || password.isEmpty() || role == null) {
            message.setText("Please fill all fields.");
            return;
        }

        boolean success = userDao.register(username, password, role);

        if (success) {
            message.setText("User registered successfully!");
        } else {
            message.setText("Registration failed.");
        }
    }
}
