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
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.ScrollPane;
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


    //!!!!!!!!!!!!!!!!!!!!!!!!!!!
    ObservableList<String> arr_country =  FXCollections.observableArrayList("Россия","США","Испания");
    ObservableList<String> arr_city =  FXCollections.observableArrayList("Москва","Донецк","Барселона","Киев", "Рим", "Лондон");
    //!!!!!!!!!!!!!!!!!!!!!!!!!!



    public void in_password() throws Exception{
        stage = new Stage();
        Group group = new Group();
        Scene scene= new Scene(group,292, 122);
        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\pasw.fxml" ).toURI().toURL()));
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
            Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\error-pasw.fxml").toURI().toURL()));
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
        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\editor.fxml").toURI().toURL()));
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
        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\add_port.fxml").toURI().toURL()));
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



        ComboBox <String> cbox_country_out;
        ComboBox <String> cbox_country_in;
        ComboBox <String> cbox_city_out;
        ComboBox <String> cbox_city_in;
        ComboBox <String> cbox_port_out;
        ComboBox <String> cbox_port_in;



        Stage stage1 = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 834, 600);

        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\add_way.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage1.centerOnScreen();
        root.setCenter(content);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.setResizable(false);
        group.getChildren().add(root);

        cbox_country_in = new ComboBox<String>();//array from Artemius
        cbox_country_in.setItems(arr_country);
        cbox_country_in.setLayoutX(89);
        cbox_country_in.setLayoutY(102);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_country_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_country_in);

        cbox_city_in = new ComboBox<>(); //array from Artemius
        cbox_city_in.setLayoutX(352);
        cbox_city_in.setLayoutY(102);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_city_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_city_in);
        cbox_city_in.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
        cbox_city_in.setVisibleRowCount(3);
            if (cbox_country_in.getValue().equals("Россия")){
                cbox_city_in.setItems(arr_city);
            }else{
                cbox_city_in.setItems(FXCollections.observableArrayList());
            }
        });

        cbox_port_in = new ComboBox<>();//array from Artemius
        cbox_port_in.setLayoutX(649);
        cbox_port_in.setLayoutY(102);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_port_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_port_in);

        cbox_country_out = new ComboBox<>(); //array from Artemius
        cbox_country_out.setLayoutY(186);
        cbox_country_out.setLayoutX(89);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_country_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_country_out);

        cbox_city_out = new ComboBox<>(); // array from Artemius
        cbox_city_out.setLayoutX(352);
        cbox_city_out.setLayoutY(186);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_city_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_city_out);

        cbox_port_out = new ComboBox<>();// array from Artemius
        cbox_port_out.setLayoutY(186);
        cbox_port_out.setLayoutX(649);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_port_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_port_out);

        DatePicker date_in = new DatePicker();
        date_in.setLayoutX(85);
        date_in.setLayoutY(288);


        DatePicker date_out = new DatePicker();
        date_out.setLayoutX(85);
        date_out.setLayoutY(385);


        Spinner<Integer> hours_in = new Spinner<>();
        hours_in.getEditor().setPrefWidth(86);
        hours_in.setLayoutX(388);
        hours_in.setLayoutY(288);
        SpinnerValueFactory<Integer> value_hours_in = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hours_in.setValueFactory(value_hours_in);
        hours_in.setEditable(true);

        Spinner<Integer> hours_out = new Spinner<>();
        hours_out.getEditor().setPrefWidth(86);
        hours_out.setLayoutX(388);
        hours_out.setLayoutY(385);
        hours_out.setEditable(true);
        SpinnerValueFactory<Integer> value_hours_out = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hours_out.setValueFactory(value_hours_out);

        Spinner <Integer> min_in = new Spinner<>();
        min_in.setEditable(true);
        min_in.setLayoutX(688);
        min_in.setLayoutY(288);
        SpinnerValueFactory<Integer> value_min_in = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        min_in.getEditor().setPrefWidth(86);
        min_in.setValueFactory(value_min_in);

        Spinner<Integer> min_out = new Spinner<>();
        min_out.setEditable(true);
        min_out.setLayoutX(688);
        min_out.setLayoutY(385);
        SpinnerValueFactory<Integer> value_min_out= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        min_out.getEditor().setPrefWidth(86);
        min_out.setValueFactory(value_min_out);


        group.getChildren().add(cbox_country_in);
        group.getChildren().add(cbox_city_in);
        group.getChildren().add(cbox_port_in);
        group.getChildren().add(cbox_country_out);
        group.getChildren().add(cbox_city_out);
        group.getChildren().add(cbox_port_out);
        group.getChildren().add(date_in);
        group.getChildren().add(date_out);
        group.getChildren().add(hours_in);
        group.getChildren().add(hours_out);
        group.getChildren().add(min_in);
        group.getChildren().add(min_out);
        stage1.setScene(scene);





        stage1.show();
    }
    public void in_del_port()throws Exception {
        Stage stage1 = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 331, 345);
        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\del_port.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage1.centerOnScreen();
        root.setCenter(content);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.setResizable(false);
        group.getChildren().add(root);

        ComboBox<String> country = new ComboBox<>(arr_country); // !!!!!!!!!!!!!!!!
        country.setLayoutX(130);
        country.setLayoutY(90);
        country.setVisibleRowCount(3);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_country = new AutoComboBox.AutoCompleteComboBoxListener<>(country);

        ComboBox<String> city = new ComboBox<>(arr_city); // !!!!!!!!!!!!!!!!
        city.setLayoutX(130);
        city.setLayoutY(150);
        city.setVisibleRowCount(3);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_city = new AutoComboBox.AutoCompleteComboBoxListener<>(city);

        ComboBox<String> port = new ComboBox<>(); // !!!!!!!!!!!!!!!!
        port.setLayoutX(130);
        port.setLayoutY(213);
        port.setVisibleRowCount(3);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_port = new AutoComboBox.AutoCompleteComboBoxListener<>(port);

        group.getChildren().add(country);
        group.getChildren().add(city);
        group.getChildren().add(port);

        stage1.setScene(scene);
        stage1.show();
    }
    public void in_search_way() throws Exception {
        ComboBox <String> cbox_country_out;
        ComboBox <String> cbox_country_in;
        ComboBox <String> cbox_city_out;
        ComboBox <String> cbox_city_in;
        ComboBox <String> cbox_port_out;
        ComboBox <String> cbox_port_in;

        Stage stage1 = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 834, 600);
        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\search_way.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage1.centerOnScreen();
        root.setCenter(content);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.setResizable(false);
        group.getChildren().add(root);
        cbox_country_in = new ComboBox<String>();//array from Artemius
        cbox_country_in.setItems(arr_country);
        cbox_country_in.setLayoutX(89);
        cbox_country_in.setLayoutY(102);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_country_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_country_in);

        cbox_city_in = new ComboBox<>(); //array from Artemius
        cbox_city_in.setLayoutX(352);
        cbox_city_in.setLayoutY(102);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_city_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_city_in);
        cbox_city_in.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
            cbox_city_in.setVisibleRowCount(3);
            if (cbox_country_in.getValue().equals("Россия")){
                cbox_city_in.setItems(arr_city);
            }else{
                cbox_city_in.setItems(FXCollections.observableArrayList());
            }
        });

        cbox_port_in = new ComboBox<>();//array from Artemius
        cbox_port_in.setLayoutX(649);
        cbox_port_in.setLayoutY(102);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_port_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_port_in);

        cbox_country_out = new ComboBox<>(); //array from Artemius
        cbox_country_out.setLayoutY(186);
        cbox_country_out.setLayoutX(89);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_country_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_country_out);

        cbox_city_out = new ComboBox<>(); // array from Artemius
        cbox_city_out.setLayoutX(352);
        cbox_city_out.setLayoutY(186);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_city_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_city_out);

        cbox_port_out = new ComboBox<>();// array from Artemius
        cbox_port_out.setLayoutY(186);
        cbox_port_out.setLayoutX(649);
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_port_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_port_out);

        DatePicker date_in = new DatePicker();
        date_in.setLayoutX(85);
        date_in.setLayoutY(288);


        DatePicker date_out = new DatePicker();
        date_out.setLayoutX(85);
        date_out.setLayoutY(385);


        Spinner<Integer> hours_in = new Spinner<>();
        hours_in.getEditor().setPrefWidth(86);
        hours_in.setLayoutX(388);
        hours_in.setLayoutY(288);
        SpinnerValueFactory<Integer> value_hours_in = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hours_in.setValueFactory(value_hours_in);
        hours_in.setEditable(true);

        Spinner<Integer> hours_out = new Spinner<>();
        hours_out.getEditor().setPrefWidth(86);
        hours_out.setLayoutX(388);
        hours_out.setLayoutY(385);
        hours_out.setEditable(true);
        SpinnerValueFactory<Integer> value_hours_out = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hours_out.setValueFactory(value_hours_out);

        Spinner <Integer> min_in = new Spinner<>();
        min_in.setEditable(true);
        min_in.setLayoutX(688);
        min_in.setLayoutY(288);
        SpinnerValueFactory<Integer> value_min_in = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        min_in.getEditor().setPrefWidth(86);
        min_in.setValueFactory(value_min_in);

        Spinner<Integer> min_out = new Spinner<>();
        min_out.setEditable(true);
        min_out.setLayoutX(688);
        min_out.setLayoutY(385);
        SpinnerValueFactory<Integer> value_min_out= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        min_out.getEditor().setPrefWidth(86);
        min_out.setValueFactory(value_min_out);


        group.getChildren().add(cbox_country_in);
        group.getChildren().add(cbox_city_in);
        group.getChildren().add(cbox_port_in);
        group.getChildren().add(cbox_country_out);
        group.getChildren().add(cbox_city_out);
        group.getChildren().add(cbox_port_out);
        group.getChildren().add(date_in);
        group.getChildren().add(date_out);
        group.getChildren().add(hours_in);
        group.getChildren().add(hours_out);
        group.getChildren().add(min_in);
        group.getChildren().add(min_out);
        stage1.setScene(scene);
        stage1.show();
    }
    public void in_del_way() throws Exception {
        Stage stage1 = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 834, 600);
        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\del_way.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage1.centerOnScreen();
        root.setCenter(content);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.setResizable(false);
        group.getChildren().add(root);
        stage1.setScene(scene);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLocation(0,60);
        FlowPane container = new FlowPane();

        stage1.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

}
