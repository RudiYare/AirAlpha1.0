package YAROSLAV;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
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
        Group group = new Group();
        primaryStage.setResizable(false);
        Scene scene = new Scene(group, 500,500);
        BorderPane border = new BorderPane();
        border.setBottom(new Button("НАЖМИ"));

        border.setMinWidth(500);
        border.setMinHeight(500);
        border.setCenter(new Button("1"));

        group.getChildren().addAll(border);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void drawShapess(GraphicsContext gc){


    }
    private void drawShapess1(GraphicsContext gc){
        gc.setFill(Color.GREENYELLOW);
        gc.setStroke(Color.BLACK);
        gc.fillOval(12.5,12.5,25,25);
        gc.strokeOval(0,0,50,50);
        //gc.fillArc(50,50,50,25,90,100, ArcType.ROUND);
        //gc.fillRect(0,0,50,35);
    }

}
