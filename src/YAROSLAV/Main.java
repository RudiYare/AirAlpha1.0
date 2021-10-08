package YAROSLAV;

import ARTEM.Network;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
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
import javafx.util.StringConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application {
    public static Stage stage;
    public static Stage stage_back;
    public static Network net ;

    public static void main(String[] args)  {
        net = new Network();

        launch(args);


    }




    public void start(Stage s) throws Exception {

        try {
         MyController.stage = new Stage();
            Main.net.loadData();
            MyController.stage.centerOnScreen();
            Group group = new Group();
            Scene scene = new Scene(group, 330, 289);
            MyController.stage.setResizable(false);

                Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\login.fxml").toURI().toURL()));
                BorderPane root = new BorderPane();
                root.getChildren().add(content);

            AnchorPane pane = new AnchorPane();
            pane.setPrefWidth(330);
            pane.setPrefHeight(170);
            pane.setLayoutX(0);
            pane.setLayoutY(0);

            MyController.login = new TextField();
            MyController.login.setLayoutX(110);
            MyController.login.setLayoutY(90);
            MyController.login.setPrefWidth(206);
            MyController.login.setPrefHeight(27);
            pane.getChildren().add(MyController.login);
            MyController.password = new PasswordField();
            MyController.password.setLayoutX(110);
            MyController.password.setLayoutY(160);
            MyController.password.setPrefWidth(206);
            MyController.password.setPrefHeight(27);
            pane.getChildren().add(MyController.password);
            group.getChildren().add(root);
            group.getChildren().add(pane);
            MyController.stage.setScene(scene);
            MyController.stage.show();

        }
        catch(Exception e){
            Stage  stage2 = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group, 292, 122);
            Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\error_data.fxml").toURI().toURL()));
            BorderPane root = new BorderPane();
            stage2.centerOnScreen();
            root.setCenter(content);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initOwner(stage);
            stage2.setResizable(false);
            group.getChildren().add(root);
            stage2.setScene(scene);
            stage2.show();
        }

    }

}
