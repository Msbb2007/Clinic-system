package view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DoctorDashboardView {

    private BorderPane root;

    public DoctorDashboardView() {

        root = new BorderPane();

        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);

        Label label = new Label("Doctor Dashboard 🩺");
        label.setStyle("-fx-font-size: 22px;");

        center.getChildren().add(label);

        root.setCenter(center);
    }

    public Parent getView() {
        return root;
    }

}
