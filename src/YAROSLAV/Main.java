package YAROSLAV;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application {
    public static Stage stage;
    public static Stage stage_back;

    public static void main(String[] args)  {

        launch(args);


    }




    public void start(Stage primaryStage) throws Exception {


        stage = new Stage();
        stage= primaryStage;
        primaryStage.centerOnScreen();
        Group group = new Group();
        Scene scene= new Scene(group,1280, 800);
        primaryStage.setResizable(false);
        Parent content = FXMLLoader.load((new File("D:\\JAVA PROJECT\\AIR ALPHA 1.0\\src\\YAROSLAV\\main1.fxml" ).toURI().toURL()));
        BorderPane root= new BorderPane();




        root.setCenter(content);
        group.getChildren().add(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}