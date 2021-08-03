package YAROSLAV;
import YAROSLAV.NameTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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

    @FXML
    TextField text_password;
    @FXML
    NameTextField name ;
    @FXML
    NameTextField city;
    @FXML
    NameTextField country;
    @FXML
    Spinner<Integer> spinner_hours;
    @FXML
    Spinner<Integer> spinner_min;
    @FXML
    ComboBox <String> cbox_country_out;
    @FXML
    ComboBox <String> cbox_country_in;
    @FXML
    ComboBox <String> cbox_city_out;
    @FXML
    ComboBox <String> cbox_city_in;
    @FXML
    ComboBox <String> cbox_port_out;
    @FXML
    ComboBox <String> cbox_port_in;



    ObservableList<String> arr_country =  FXCollections.observableArrayList("Россия","США","Испания");



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
        if (text_password.getText().equals("")) {
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
    public void in_add_way() throws Exception {

        Stage stage1 = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 690, 475);

        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\add_way.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage1.centerOnScreen();



        AutoComboBox.AutoCompleteComboBoxListener<String> autobox_country_in = new AutoComboBox.AutoCompleteComboBoxListener<String>(cbox_country_in);
        root.setCenter(content);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.setResizable(false);
        group.getChildren().add(root);
        stage1.setScene(scene);

        // SpinnerValueFactory<Integer> value_hours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24);
       // SpinnerValueFactory<Integer> value_min = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60);


        stage1.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbox_country_in=new ComboBox<>(arr_country);

    }

}
