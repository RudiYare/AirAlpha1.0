package YAROSLAV;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AIR ALFA 1.0");
        primaryStage.setResizable(false);
        Group group = new Group();
        Scene scene = new Scene(group, 1024 , 720);
        //primaryStage.setOpacity(25);
        Text text = new Text  (100, 100, "ПОЕХАЛИ");
        text.setFont(Font.font(50));
        //text.setFill(Color.BLANCHEDALMOND);
        group.getChildren().add(text);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
