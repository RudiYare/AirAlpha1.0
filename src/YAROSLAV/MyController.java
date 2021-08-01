package YAROSLAV;
import YAROSLAV.NameTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MyController implements Initializable {
    public static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    TextField text_password;
    @FXML
    NameTextField name ;
    @FXML
    NameTextField city;
    @FXML
    NameTextField country;
    public void in_password() throws Exception{
        stage = new Stage();
        Group group = new Group();
        Scene scene= new Scene(group,292, 122);
        Parent content = FXMLLoader.load((new File("D:\\JAVA PROJECT\\AIR ALPHA 1.0\\src\\YAROSLAV\\pasw.fxml" ).toURI().toURL()));
        BorderPane root= new BorderPane();
        stage.centerOnScreen();
        root.setCenter(content);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.stage);
        stage.setResizable(false);
        group.getChildren().add(root);
        stage.setScene(scene);
        stage.show();
    }

    public void check_password() throws Exception {
        if (text_password.getText().equals("abacaba")) {
            in_editor();
        } else {
            Stage stage1 = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group, 292, 122);
            Parent content = FXMLLoader.load((new File("D:\\JAVA PROJECT\\AIR ALPHA 1.0\\src\\YAROSLAV\\error-pasw.fxml").toURI().toURL()));
            BorderPane root = new BorderPane();
            stage1.centerOnScreen();
            root.setCenter(content);
            stage1.initModality(Modality.WINDOW_MODAL);
            stage1.initOwner(stage);
            stage1.setResizable(false);
            group.getChildren().add(root);
            stage1.setScene(scene);
            stage1.show();
        }
    }
    public void back_from_editor_to_main() {
            stage.close();
            Main.stage.show();
        }
    public void in_editor () throws Exception{
        Main.stage.close();
        Group group = new Group();
        Scene scene = new Scene(group, 1280, 800);
        Parent content = FXMLLoader.load((new File("D:\\JAVA PROJECT\\AIR ALPHA 1.0\\src\\YAROSLAV\\editor.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage.centerOnScreen();
        root.setCenter(content);
        stage.setResizable(false);
        group.getChildren().add(root);
        stage.setScene(scene);
        stage.show();
    }
    public void in_add_port() throws Exception{
        Stage stage1 = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 340, 320);
        Parent content = FXMLLoader.load((new File("D:\\JAVA PROJECT\\AIR ALPHA 1.0\\src\\YAROSLAV\\add_port.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage1.centerOnScreen();
        root.setCenter(content);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.setResizable(false);
        group.getChildren().add(root);
        stage1.setScene(scene);


        stage1.show();
    }
    public void ok_in_add_port() {

        // TODO:ARTEMIUS will create object Airport and check null str

        System.out.println(name.getText());
        System.out.println(city.getText());
        System.out.println(country.getText());
    }
}
