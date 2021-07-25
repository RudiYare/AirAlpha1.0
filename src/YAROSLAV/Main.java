package YAROSLAV;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        Group group = new Group();
        Scene scene1 = new Scene(group,500,500);


        Button but1 = new Button("Rammstein");
        but1.setLayoutX(0);
        but1.setLayoutY(0);
        Button but2 = new Button("Rammstain");
        but2.setLayoutX(50);
        but2.setLayoutY(50);
        group.getChildren().addAll(but1,but2);
        but1.setOnAction(actionEvent -> {
            Text text= new Text(0,20,"EEEEE");
            Scene scene2 = new Scene(new Group(text), 200,200);
            Stage stage2 = new Stage();
            stage2.setScene(scene2);
            stage2.setResizable(false);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initOwner(primaryStage);
            stage2.show();
        });
        but2.setOnAction(actionEvent -> {
            Text text= new Text(0,20,"ДЕБИЛ, В АД НАУЙ!");
            Scene scene2 = new Scene(new Group(text), 200,200);
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
