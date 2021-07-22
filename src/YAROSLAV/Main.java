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
        Ellipse line = new Ellipse(350,350,100,100);
        Rectangle rec = new Rectangle(0,0,50,50);
        Text text = new Text(420,360,"ЗДАРОВА АРТЕМ!!!");
        text.setStroke(Color.RED);
        text.setFill(Color.GOLD);
        text.setFont(Font.font(50));
        line.setStroke(Color.RED);
        rec.setStroke(Color.RED);
        rec.setFill(Color.DEEPSKYBLUE);
        line.setFill(Color.DEEPSKYBLUE);
        Group group = new Group();
        Scene scene = new Scene(group, 1280, 720);
        group.getChildren().add(text);
       // group.getChildren().add(line);
        //group.getChildren().add(rec);
        primaryStage.setTitle("YARE MASTER");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
