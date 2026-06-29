package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class AdminDashboardView {

    private BorderPane root;

    public AdminDashboardView() {

        root = new BorderPane();

        // Sidebar
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(200);

        sidebar.setStyle("-fx-background-color: #2F80ED;");

        Label title = new Label("ADMIN");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Button usersBtn = new Button("Manage Users");
        Button doctorsBtn = new Button("Manage Doctors");
        Button reportsBtn = new Button("Reports");

        usersBtn.setMaxWidth(Double.MAX_VALUE);
        doctorsBtn.setMaxWidth(Double.MAX_VALUE);
        reportsBtn.setMaxWidth(Double.MAX_VALUE);

        sidebar.getChildren().addAll(title, usersBtn, doctorsBtn, reportsBtn);

        // Center
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);

        Label welcome = new Label("Welcome Admin 👑");
        welcome.setStyle("-fx-font-size: 24px;");

        center.getChildren().add(welcome);

        root.setLeft(sidebar);
        root.setCenter(center);
    }

    public Parent getView() {
        return root;
    }
}
