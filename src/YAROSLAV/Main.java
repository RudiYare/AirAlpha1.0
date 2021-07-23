package YAROSLAV;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
        Canvas cnvs = new Canvas(100,100);
        GraphicsContext gc = cnvs.getGraphicsContext2D();

        drawShapess(gc);

        group.getChildren().add(cnvs);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void drawShapess(GraphicsContext gc){
        gc.setFill(Color.GREENYELLOW);
        gc.setStroke(Color.AZURE);
        gc.fillArc(50,50,50,25,90,100, ArcType.ROUND);
        gc.fillRect(0,0,50,35);
    }

}
