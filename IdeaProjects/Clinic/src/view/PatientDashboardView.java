package view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PatientDashboardView {

    private BorderPane root;

    public PatientDashboardView() {

        root = new BorderPane();

        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);

        Label label = new Label("Patient Dashboard 🏥");
        label.setStyle("-fx-font-size: 22px;");

        center.getChildren().add(label);

        root.setCenter(center);
    }

    public Parent getView() {
        return root;
    }
}
