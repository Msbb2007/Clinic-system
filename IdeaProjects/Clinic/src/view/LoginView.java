package view;

import controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import util.SceneManager;

public class LoginView {

    private StackPane root;

    public LoginView() {

        root = new StackPane();

        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #4facfe, #00f2fe);"
        );

        HBox card = new HBox();
        card.setMaxWidth(700);
        card.setMaxHeight(400);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 30, 0, 0, 10);"
        );

        // LEFT PANEL
        VBox leftPanel = new VBox(30);
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setPrefWidth(300);

        leftPanel.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #3A7BD5, #00D2FF);" +
                        "-fx-background-radius: 12 0 0 12;"
        );

        Label welcome = new Label("Welcome Back!");
        welcome.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;"
        );

        Label subtitle = new Label("Clinic Management System");
        subtitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;"
        );

        leftPanel.getChildren().addAll(welcome, subtitle);

        // RIGHT PANEL
        VBox formPanel = new VBox(15);
        formPanel.setAlignment(Pos.CENTER);
        formPanel.setPadding(new Insets(40));

        Label title = new Label("Login");
        title.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;"
        );

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setPrefHeight(35);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setPrefHeight(35);

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(200);
        loginButton.setPrefHeight(35);

        loginButton.setStyle(
                "-fx-background-color: #2F80ED;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 6;"
        );

        // hover effect
        loginButton.setOnMouseEntered(e ->
                loginButton.setStyle(
                        "-fx-background-color: #1C6ED5;" +
                                "-fx-text-fill: white;" +
                                "-fx-background-radius: 6;"
                )
        );

        loginButton.setOnMouseExited(e ->
                loginButton.setStyle(
                        "-fx-background-color: #2F80ED;" +
                                "-fx-text-fill: white;" +
                                "-fx-background-radius: 6;"
                )
        );

        Hyperlink registerLink = new Hyperlink("Create account");
        registerLink.setOnAction(e -> {

            RegisterView registerView = new RegisterView();
            Scene scene = new Scene(registerView.getView(), 900, 500);

            SceneManager.switchScene(scene);
        });

        Label message = new Label();

        LoginController controller =
                new LoginController(usernameField, passwordField, message);

        loginButton.setOnAction(e -> controller.handleLogin());

        formPanel.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                loginButton,
                registerLink,
                message
        );

        card.getChildren().addAll(leftPanel, formPanel);
        root.getChildren().add(card);
    }

    public Parent getView() {
        return root;
    }
}
