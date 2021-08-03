package YAROSLAV;

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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_AddWay implements Initializable {
    @FXML
    Spinner<Integer> spinner_hours;
    @FXML
    Spinner<Integer> spinner_min;
    @FXML
    ComboBox <String> cbox_country_out;
    @FXML
    ComboBox <String> box_country_in;
    @FXML
    ComboBox <String> cbox_city_out;
    @FXML
    ComboBox <String> cbox_city_in;
    @FXML
    ComboBox <String> cbox_port_out;
    @FXML
    ComboBox <String> cbox_port_in;
    ObservableList<String> arr_country =  FXCollections.observableArrayList("Россия","США","Испания");
    public void in_add_way() throws Exception {
//TODO: Написать ручками все
        box_country_in.getItems().setAll("rw","rwr");
        //box_country_in.setItems(arr_country);
        AutoComboBox.AutoCompleteComboBoxListener<String> autobox_country_in = new AutoComboBox.AutoCompleteComboBoxListener<String>(box_country_in);
        Stage stage1 = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 690, 475);

        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\add_way.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage1.centerOnScreen();




        root.setCenter(content);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(Main.stage);
        stage1.setResizable(false);
        group.getChildren().add(root);
        stage1.setScene(scene);

        // SpinnerValueFactory<Integer> value_hours = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24);
        // SpinnerValueFactory<Integer> value_min = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60);


        stage1.show();
    }
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resourceBundle) {


    }
}
