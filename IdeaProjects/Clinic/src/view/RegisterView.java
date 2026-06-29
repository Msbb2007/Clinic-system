package view;

import controller.RegisterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import util.SceneManager;

public class RegisterView {

    private StackPane root;

    public RegisterView() {

        root = new StackPane();

        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #4facfe, #00f2fe);"
        );

        HBox card = new HBox();
        card.setMaxWidth(700);
        card.setMaxHeight(420);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 30, 0, 0, 10);"
        );

        // LEFT PANEL
        VBox leftPanel = new VBox(20);
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setPrefWidth(300);

        leftPanel.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #3A7BD5, #00D2FF);" +
                        "-fx-background-radius: 12 0 0 12;"
        );

        Label welcome = new Label("Join Our Clinic!");
        welcome.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;"
        );

        Label subtitle = new Label("Create your account");
        subtitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;"
        );

        leftPanel.getChildren().addAll(welcome, subtitle);

        // RIGHT PANEL
        VBox formPanel = new VBox(15);
        formPanel.setAlignment(Pos.CENTER);
        formPanel.setPadding(new Insets(40));

        Label title = new Label("Register");
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

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("admin", "doctor", "patient");
        roleBox.setPromptText("Select Role");
        roleBox.setPrefHeight(35);

        Button registerButton = new Button("Register");
        registerButton.setPrefWidth(200);
        registerButton.setPrefHeight(35);

        registerButton.setStyle(
                "-fx-background-color: #2F80ED;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 6;"
        );

        registerButton.setOnMouseEntered(e ->
                registerButton.setStyle(
                        "-fx-background-color: #1C6ED5;" +
                                "-fx-text-fill: white;" +
                                "-fx-background-radius: 6;"
                )
        );

        registerButton.setOnMouseExited(e ->
                registerButton.setStyle(
                        "-fx-background-color: #2F80ED;" +
                                "-fx-text-fill: white;" +
                                "-fx-background-radius: 6;"
                )
        );

        Hyperlink loginLink = new Hyperlink("Already have an account? Login");
        loginLink.setOnAction(e -> {

            LoginView loginView = new LoginView();
            Scene scene = new Scene(loginView.getView(), 900, 500);

            SceneManager.switchScene(scene);

        });

        Label message = new Label();

        RegisterController controller =
                new RegisterController(usernameField, passwordField, roleBox, message);

        registerButton.setOnAction(e -> {
            controller.handleRegister();
        });

        formPanel.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                roleBox,
                registerButton,
                loginLink,
                message
        );

        card.getChildren().addAll(leftPanel, formPanel);
        root.getChildren().add(card);
    }

    public Parent getView() {
        return root;
    }
}
