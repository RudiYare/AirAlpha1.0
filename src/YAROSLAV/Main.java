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

        Button but1 = new Button("НАЖМИ НА ГАЗ");
        group.getChildren().add(but1);
        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("НАЖАЛОСЬ!");
            }
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
