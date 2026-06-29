package controller;

import dao.UserDao;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import util.SceneManager;
import view.AdminDashboardView;
import view.DoctorDashboardView;
import view.PatientDashboardView;

public class LoginController {

    private final TextField usernameField;
    private final PasswordField passwordField;
    private final Label messageLabel;
    private final UserDao userDao;

    public LoginController(TextField usernameField, PasswordField passwordField, Label messageLabel) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.messageLabel = messageLabel;
        this.userDao = new UserDao();
    }

    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Please fill all fields.");
            return;
        }

        User user = userDao.login(username, password);

        if (user != null) {

                switch (user.getRoleName()) {

                    case "ADMIN":
                        SceneManager.switchScene(
                                new Scene(new AdminDashboardView().getView(), 1000, 600)
                        );
                        break;

                    case "DOCTOR":
                        SceneManager.switchScene(
                                new Scene(new DoctorDashboardView().getView(), 1000, 600)
                        );
                        break;

                    case "PATIENT":
                        SceneManager.switchScene(
                                new Scene(new PatientDashboardView().getView(), 1000, 600)
                        );
                        break;
                }

            }
        else
            messageLabel.setText("Invalid username or password");
        }
    }