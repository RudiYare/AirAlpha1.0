

package YAROSLAV;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
    public Main() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Group group = new Group();
        Scene scene1 = new Scene(group, 500.0D, 500.0D);
        Button but1 = new Button("Rammstein");
        but1.setLayoutX(0.0D);
        but1.setLayoutY(0.0D);
        Button but2 = new Button("Rammstain");
        but2.setLayoutX(50.0D);
        but2.setLayoutY(50.0D);
        group.getChildren().addAll(new Node[]{but1, but2});
        but1.setOnAction((actionEvent) -> {
            Text text = new Text(0.0D, 20.0D, "EEEEE");
            Scene scene2 = new Scene(new Group(new Node[]{text}), 200.0D, 200.0D);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initOwner(primaryStage);
            stage2.show();
        });
        but2.setOnAction((actionEvent) -> {
            Text text = new Text(0.0D, 20.0D, "ДЕБИЛ, В АД НАУЙ!");
            Scene scene2 = new Scene(new Group(new Node[]{text}), 200.0D, 200.0D);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initOwner(primaryStage);
            stage2.show();
        });
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}