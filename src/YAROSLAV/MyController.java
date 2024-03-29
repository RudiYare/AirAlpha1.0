package YAROSLAV;
import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ARTEM.Tree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MyController implements Initializable {
    public static Stage stage;
    public static Stage stage1;
    public static DatePicker date_in ;public static DatePicker date_out ;




    //!!!!!!!!!!!!!!!!!!!!!!!!!!!
    ObservableList<String> arr_country =  FXCollections.observableArrayList();
    ObservableList<String> arr_city =  FXCollections.observableArrayList();
    ObservableList<String> arr_port =  FXCollections.observableArrayList();

    //!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static  PasswordField password;
    public static  ComboBox<String> country_box;
    public static  ComboBox<String> city_box;
    public static  ComboBox<String> port_box;
    public static TextField login;
    public static long date_1;
    public static long date_2;
    public static boolean admin;
    public static ComboBox <String> cbox_country_out;
    public static ComboBox <String> cbox_country_in;
    public static ComboBox <String> cbox_city_out;
    public static ComboBox <String> cbox_city_in;
    public static ComboBox <String> cbox_port_out;
    public static ComboBox <String> cbox_port_in;
    public static ComboBox <String> day_in;
    public static ComboBox <String> day_out;
    public static Spinner<Integer> hours_in;
    public static Spinner<Integer> hours_out;
    public static PriceTextField price ;
    public static Spinner <Integer> min_in;
    public static Spinner <Integer> min_out;
    public static NameTextField name;
    public static NameTextField city;
    public static NameTextField country;
    public static XY_TextField x;
    public static XY_TextField y;
    public static TextField text_in;
    public static TextField text_out;
    public void screen(String str){
        System.out.println(str);
    }
    public void in_password() throws Exception{stage.close();
in_editor();
    }


    public void back_from_editor_to_main() {
            stage.close();
adminMenu();
        }
    public void in_editor () throws Exception{
        //Main.stage.close();
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
        stage1 = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 331, 476);
        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\add_port.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage1.centerOnScreen();
        root.setCenter(content);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.setResizable(false);
        group.getChildren().add(root);
        //////////////////////////
   name = new NameTextField();
   name.setLayoutX(125);
   name.setLayoutY(87);
   name.setPrefWidth(195);
   city = new NameTextField();
        city.setLayoutX(125);
        city.setLayoutY(150);
        city.setPrefWidth(195);
        country = new NameTextField();
        country.setLayoutX(125);
        country.setLayoutY(213);
        country.setPrefWidth(195);
        x = new XY_TextField();
        x.setLayoutX(125);
       x.setLayoutY(280);
       x.setPrefWidth(195);
        y = new XY_TextField();
        y.setLayoutX(125);
        y.setLayoutY(350);
        y.setPrefWidth(195);
        group.getChildren().add(name);
        group.getChildren().addAll(city,country,x,y);
        ///////////////////
        stage1.setScene(scene);


        stage1.show();
    }
public void ok_add_port() throws Exception{
        Stage stage2;

try {  double iX = Double.parseDouble(x.getText()), iY = Double.parseDouble(y.getText());
    if ((name.getText().length()!=0)&&(city.getText().length()!=0)&&(country.getText().length()!=0)&&(iY<=90)&&(iX<=180)&&(iX>=-90)&&(iY>=-180)&&(y.getText().length()!=0)){
        boolean isOk = Main.net.addNewAirport(name.getText(),city.getText(),country.getText(),Double.parseDouble(x.getText()),Double.parseDouble(y.getText()));

        if(isOk){
            stage2 = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group, 310, 122);
            Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\ok.fxml").toURI().toURL()));
            BorderPane root = new BorderPane();
            stage2.centerOnScreen();
            root.setCenter(content);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initOwner(stage);
            stage2.setResizable(false);
            group.getChildren().add(root);
            stage2.setScene(scene);

            stage1.close();
            stage2.show();
        }else {
            stage2 = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group, 292, 122);
            Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\error_port1.fxml").toURI().toURL()));
            BorderPane root = new BorderPane();
            stage2.centerOnScreen();
            root.setCenter(content);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initOwner(stage1);
            stage2.setResizable(false);
            group.getChildren().add(root);
            stage2.setScene(scene);
            stage2.show();
        }
    }else {
        stage2 = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 292, 122);
        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\error_data.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage2.centerOnScreen();
        root.setCenter(content);
        stage2.initModality(Modality.WINDOW_MODAL);
        stage2.initOwner(stage1);
        stage2.setResizable(false);
        group.getChildren().add(root);
        stage2.setScene(scene);
        stage2.show();
    }
}
catch (Exception e){
    stage2 = new Stage();
    Group group = new Group();
    Scene scene = new Scene(group, 292, 122);
    Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\error_data.fxml").toURI().toURL()));
    BorderPane root = new BorderPane();
    stage2.centerOnScreen();
    root.setCenter(content);
    stage2.initModality(Modality.WINDOW_MODAL);
    stage2.initOwner(stage1);
    stage2.setResizable(false);
    group.getChildren().add(root);
    stage2.setScene(scene);
    stage2.show();
}



}
    public void in_add_way() throws Exception {

        ObservableList<String> arr_country =  FXCollections.observableArrayList(Main.net.getAllCountries());



        cbox_city_in = new ComboBox<>();
        cbox_port_in = new ComboBox<>();//array from Artemius
        stage1 = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 834, 613);

        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\add_way.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        stage1.centerOnScreen();
        root.setCenter(content);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.initOwner(stage);
        stage1.setResizable(false);
        group.getChildren().add(root);

        cbox_country_in = new ComboBox<String>();//array from Artemius

        cbox_country_in.setLayoutX(89);
        cbox_country_in.setLayoutY(102);
        cbox_country_in.setVisibleRowCount(3);
        cbox_country_in.setPrefWidth(173);
cbox_country_in.getStylesheets().add(
        (new File("src/YAROSLAV/size.css").toURI().toString()) );

cbox_country_in.setEditable(true);
        cbox_country_in.setItems(arr_country);
        cbox_country_in.setPrefWidth(173);

        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_country_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_country_in);

       // cbox_city_in = new ComboBox<>(); //array from Artemius
        cbox_city_in.setLayoutX(352);
        cbox_city_in.setLayoutY(102);
        cbox_city_in.setVisibleRowCount(3);
        cbox_city_in.setEditable(true);
        cbox_city_in.setPrefWidth(173);
        cbox_port_in.setLayoutX(649);
        cbox_port_in.setLayoutY(102);
        cbox_port_in.setVisibleRowCount(3);
        cbox_port_in.setEditable(true);
        cbox_port_in.setPrefWidth(173);
        cbox_port_in.setPrefWidth(173);
        cbox_port_in.getStylesheets().add(
                (new File("src/YAROSLAV/size.css").toURI().toString()) );
        cbox_city_in.getEditor().setPrefColumnCount(3);
        cbox_city_in.setPrefWidth(173);
        cbox_city_in.getStylesheets().add(
                (new File("src/YAROSLAV/size.css").toURI().toString()) );
        cbox_country_in.setOnAction(actionEvent -> {
            if (arr_country.contains(cbox_country_in.getValue())){
                arr_city = FXCollections.observableArrayList(Main.net.getAllCities(cbox_country_in.getValue()));
                cbox_city_in.setItems(arr_city);
                AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_city_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_city_in);

            }else {
                cbox_city_in.setItems(null);

            }
            cbox_country_in.hide();
            cbox_country_in.setVisibleRowCount(3);


        });
        cbox_city_in.setOnAction(actionEvent -> {
            if(arr_city.contains(cbox_city_in.getValue())){
                ArrayList<Tree>arr_port1 = (Main.net.getAllTitles(cbox_country_in.getValue(), cbox_city_in.getValue()));
                ArrayList<String> arr = new ArrayList<>();
                for (ARTEM.Tree t: arr_port1){
                    arr.add(t.title);
                }

                arr_port=FXCollections.observableArrayList(arr);
                //arr_port.remove(cbox_port_out.getValue());
                cbox_port_in.setItems(arr_port);
                AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_port_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_port_in);
            }else{
                cbox_port_in.setItems(null);
            }
            //cbox_port_in.setValue("");
            cbox_city_in.hide();
            cbox_city_in.setVisibleRowCount(3);


        });


            cbox_port_in.setOnAction(actionEvent -> {
                cbox_port_in.hide();
                cbox_port_in.setVisibleRowCount(3);


            });


        cbox_port_out = new ComboBox<>();// array from Artemius
        cbox_city_out = new ComboBox<>(); // array from Artemius
        cbox_country_out = new ComboBox<>(arr_country); //array from Artemius
        cbox_country_out.setLayoutY(186);
        cbox_country_out.setLayoutX(89);
        cbox_country_out.setVisibleRowCount(3);
        cbox_country_out.setVisibleRowCount(3);
        cbox_country_out.setPrefWidth(173);
        cbox_country_out.setEditable(true);
        cbox_country_out.setPrefWidth(173);
        cbox_country_out.getStylesheets().add(
                (new File("src/YAROSLAV/size.css").toURI().toString()) );
        cbox_city_out.setLayoutX(352);
        cbox_city_out.setLayoutY(186);
        cbox_city_out.setVisibleRowCount(3);
        cbox_city_out.setPrefWidth(173);
        cbox_city_out.setEditable(true);
        cbox_city_out.setPrefWidth(173);
        cbox_city_out.getStylesheets().add(
                (new File("src/YAROSLAV/size.css").toURI().toString()) );
        cbox_country_out.setOnAction(actionEvent -> {
            if (arr_country.contains(cbox_country_out.getValue())){
                arr_city = FXCollections.observableArrayList(Main.net.getAllCities(cbox_country_out.getValue()));
                cbox_city_out.setItems(arr_city);
                AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_city_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_city_out);

            }else {
                cbox_city_out.setItems(null);
            }
            cbox_country_out.hide();
            cbox_country_out.setVisibleRowCount(3);

            cbox_port_out.setItems(null);
        });
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_country_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_country_out);





        cbox_city_out.setOnAction(actionEvent -> {
            if(arr_city.contains(cbox_city_out.getValue())){
                ArrayList<Tree>arr_port1 = (Main.net.getAllTitles(cbox_country_out.getValue(), cbox_city_out.getValue()));
                ArrayList<String> arr = new ArrayList<>();
                for (ARTEM.Tree t: arr_port1){
                    arr.add(t.title);
                }

                arr_port=FXCollections.observableArrayList(arr);
                if ((cbox_country_in.getValue().equals(cbox_country_out.getValue()))&&(cbox_city_in.getValue().equals(cbox_city_out.getValue()))){
                    //arr_port.remove(cbox_port_in.getValue());
                }

                cbox_port_out.setItems(arr_port);
                AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_port_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_port_out);

            }else{
                cbox_port_out.setItems(null);
            }

            cbox_port_out.setVisibleRowCount(3);
            cbox_port_out.setPrefWidth(173);
            cbox_port_out.getStylesheets().add(
                    (new File("src/YAROSLAV/size.css").toURI().toString()) );
            cbox_city_out.hide();
            cbox_city_out.setVisibleRowCount(3);

        });



        cbox_port_out.setLayoutY(186);
        cbox_port_out.setLayoutX(649);
        cbox_port_out.setVisibleRowCount(3);


        cbox_port_out.setPrefWidth(173);
        cbox_port_out.setEditable(true);
cbox_port_out.setOnAction(actionEvent -> {



    cbox_port_out.hide();
    cbox_port_out.setVisibleRowCount(3);

});
         hours_in = new Spinner<>();
        hours_in.getEditor().setPrefWidth(86);
        hours_in.setLayoutX(388);
        hours_in.setLayoutY(288);
        SpinnerValueFactory<Integer> value_hours_in = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hours_in.setValueFactory(value_hours_in);
        hours_in.setEditable(true);

         hours_out = new Spinner<>();
        hours_out.getEditor().setPrefWidth(86);
        hours_out.setLayoutX(388);
        hours_out.setLayoutY(385);
        hours_out.setEditable(true);
        SpinnerValueFactory<Integer> value_hours_out = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hours_out.setValueFactory(value_hours_out);

         min_in = new Spinner<>();
        min_in.setEditable(true);
        min_in.setLayoutX(688);
        min_in.setLayoutY(288);
        SpinnerValueFactory<Integer> value_min_in = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        min_in.getEditor().setPrefWidth(86);
        min_in.setValueFactory(value_min_in);

         min_out = new Spinner<>();
        min_out.setEditable(true);
        min_out.setLayoutX(688);
        min_out.setLayoutY(385);
        SpinnerValueFactory<Integer> value_min_out= new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        min_out.getEditor().setPrefWidth(86);
        min_out.setValueFactory(value_min_out);



        day_in = new ComboBox<>();
        day_in.setPrefWidth(173);
        day_in.setVisibleRowCount(3);
        day_in.setLayoutX(89);
        day_in.setLayoutY(288);
        day_in.setItems(FXCollections.observableArrayList("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота" , "Воскресенье"));
        day_in.setValue("Понедельник");

        day_out = new ComboBox<>();
        day_out.setPrefWidth(173);
        day_out.setVisibleRowCount(3);
        day_out.setLayoutX(89);
        day_out.setLayoutY(385);
        day_out.setItems(FXCollections.observableArrayList("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота" , "Воскресенье"));
        day_out.setValue("Понедельник");

        price = new PriceTextField();
        price.setLayoutX(142);
        price.setLayoutY(452);
        group.getChildren().add(price);
        group.getChildren().add(cbox_country_in);
        group.getChildren().add(cbox_city_in);
        group.getChildren().add(cbox_port_in);
        group.getChildren().add(cbox_country_out);
        group.getChildren().add(cbox_city_out);
        group.getChildren().add(cbox_port_out);
        group.getChildren().add(day_in);
        group.getChildren().add(day_out);
        group.getChildren().add(hours_in);
        group.getChildren().add(hours_out);
        group.getChildren().add(min_in);
        group.getChildren().add(min_out);
        stage1.setScene(scene);





        stage1.show();
    }
    public void in_del_port()throws Exception {
        ObservableList<String> arr_country =  FXCollections.observableArrayList(Main.net.getAllCountries());
         stage1 = new Stage();
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

         country_box = new ComboBox<>(); // !!!!!!!!!!!!!!!!
        country_box.setLayoutX(130);
        country_box.setLayoutY(90);
        country_box.setVisibleRowCount(3);
        country_box.setItems(arr_country);
        country_box.setEditable(true);
        country_box.setVisibleRowCount(3);
        country_box.setPrefWidth(173);
        country_box.getStylesheets().add(
                (new File("src/YAROSLAV/size.css").toURI().toString()) );
        AutoComboBox.AutoCompleteComboBoxListener<String> auto_country = new AutoComboBox.AutoCompleteComboBoxListener<>(country_box);

         city_box = new ComboBox<>(arr_city); // !!!!!!!!!!!!!!!!
        city_box.setLayoutX(130);
        city_box.setLayoutY(150);
        city_box.setVisibleRowCount(3);
        city_box.setPrefWidth(173);
        city_box.getStylesheets().add(
                (new File("src/YAROSLAV/size.css").toURI().toString()) );
        city_box.setEditable(true);


         port_box = new ComboBox<>(); // !!!!!!!!!!!!!!!!
        port_box.setLayoutX(130);
        port_box.setLayoutY(213);
        port_box.setVisibleRowCount(3);
        port_box.setEditable(true);
        port_box.setPrefWidth(173);
        port_box.getStylesheets().add(
                (new File("src/YAROSLAV/size.css").toURI().toString()) );

        country_box.setOnAction(actionEvent -> {
            if (arr_country.contains(country_box.getValue())){
                arr_city = FXCollections.observableArrayList(Main.net.getAllCities(country_box.getValue()));
                city_box.setItems(arr_city);
                AutoComboBox.AutoCompleteComboBoxListener<String> auto_city = new AutoComboBox.AutoCompleteComboBoxListener<>(city_box);
            }else {
                city_box.setItems(null);

            }
            country_box.hide();
            country_box.setVisibleRowCount(3);
        });
        city_box.setOnAction(actionEvent -> {
            if (arr_city.contains(city_box.getValue())){
                arr_port=FXCollections.observableArrayList();
                ObservableList<ARTEM.Tree> arr_port1 = FXCollections.observableArrayList(Main.net.getAllTitles(cbox_country_in.getValue(), cbox_city_in.getValue()));

                for (Tree t: arr_port1){
                    arr_port.add(t.title);
                }

                port_box.setItems(arr_port);
                AutoComboBox.AutoCompleteComboBoxListener<String> auto_port = new AutoComboBox.AutoCompleteComboBoxListener<>(port_box);
            }else{
                port_box.setItems(null);
            }
            city_box.hide();
            city_box.setVisibleRowCount(3);
        });
port_box.setOnAction(actionEvent -> {
    port_box.hide();
    port_box.setVisibleRowCount(3);
});
        group.getChildren().add(country_box);
        group.getChildren().add(city_box);
        group.getChildren().add(port_box);

        stage1.setScene(scene);
        stage1.show();
    }
    public boolean del_port(){
        try {
            if ((country_box.getValue() == null) || (!Main.net.getAllCountries().contains(country_box.getValue()))) {
                return false;
            }
            if ((city_box.getValue() == null) || (!Main.net.getAllCities(country_box.getValue()).contains(city_box.getValue()))) {
                return false;
            }
            if ((port_box.getValue() == null) || (!Main.net.getAllTitles(country_box.getValue(), city_box.getValue()).contains(port_box.getValue()))) {
                return false;
            }

        }
        catch (Exception e){
            return false;
        }
        if (!Main.net.removeAirport(port_box.getValue(),city_box.getValue(),country_box.getValue())){
            return false;
        }
        return true;
    }
    public void screen_del_way() throws  Exception{
        Stage stage2;
            if (!del_port()){
             stage2 = new Stage();
             Group group = new Group();
                Scene scene = new Scene(group, 292, 122);
                    Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\error_data.fxml").toURI().toURL()));
                BorderPane root = new BorderPane();
                 stage2.centerOnScreen();
                root.setCenter(content);
                stage2.initModality(Modality.WINDOW_MODAL);
                stage2.initOwner(stage1);
                stage2.setResizable(false);
                group.getChildren().add(root);
                stage2.setScene(scene);
                stage2.show();
}else{
                stage2 = new Stage();
                Group group = new Group();
                Scene scene = new Scene(group, 310, 122);
                Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\ok.fxml").toURI().toURL()));
                BorderPane root = new BorderPane();
                stage2.centerOnScreen();
                root.setCenter(content);
                stage2.initModality(Modality.WINDOW_MODAL);
                stage2.initOwner(stage);
                stage2.setResizable(false);
                group.getChildren().add(root);
                stage2.setScene(scene);

                stage1.close();
                stage2.show();
            }
    }
    public void in_search_way()  {
        try {
            ObservableList<String> arr_country =  FXCollections.observableArrayList(Main.net.getAllCountries());



            cbox_city_in = new ComboBox<>();
            cbox_port_in = new ComboBox<>();//array from Artemius
            stage1 = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group, 834, 613);

            Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\search_way.fxml").toURI().toURL()));
            BorderPane root = new BorderPane();
            stage1.centerOnScreen();
            root.setCenter(content);
            stage1.initModality(Modality.WINDOW_MODAL);
            stage1.initOwner(Main.stage);
            stage1.setResizable(false);
            group.getChildren().add(root);

            cbox_country_in = new ComboBox<String>();//array from Artemius
            cbox_country_in.setItems(arr_country);
            cbox_country_in.setLayoutX(89);
            cbox_country_in.setLayoutY(102);
            cbox_country_in.setVisibleRowCount(3);
            cbox_country_in.setEditable(true);
            cbox_country_in.setPrefWidth(173);
            cbox_country_in.getStylesheets().add(
                    (new File("src/YAROSLAV/size.css").toURI().toString()) );
            AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_country_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_country_in);
            // cbox_city_in = new ComboBox<>(); //array from Artemius
            cbox_city_in.setLayoutX(352);
            cbox_city_in.setLayoutY(102);
            cbox_city_in.setVisibleRowCount(3);

            cbox_city_in.setEditable(true);
            cbox_city_in.setPrefWidth(173);
            cbox_city_in.setPrefWidth(173);
            cbox_city_in.getStylesheets().add(
                    (new File("src/YAROSLAV/size.css").toURI().toString()) );
            cbox_port_in.setLayoutX(649);
            cbox_port_in.setLayoutY(102);
            cbox_port_in.setVisibleRowCount(3);
            cbox_port_in.setEditable(true);
            cbox_port_in.setPrefWidth(173);
            cbox_port_in.setPrefWidth(173);
            cbox_port_in.getStylesheets().add(
                    (new File("src/YAROSLAV/size.css").toURI().toString()) );
            cbox_city_in.getEditor().setPrefColumnCount(3);

            cbox_country_in.setOnAction(actionEvent -> {
                if (arr_country.contains(cbox_country_in.getValue())){
                    arr_city = FXCollections.observableArrayList(Main.net.getAllCities(cbox_country_in.getValue()));
                    cbox_city_in.setItems(arr_city);
                    AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_city_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_city_in);

                }else {
                    cbox_city_in.setItems(null);

                }
                cbox_country_in.hide();
                cbox_country_in.setVisibleRowCount(3);


            });
            cbox_city_in.setOnAction(actionEvent -> {
                if(arr_city.contains(cbox_city_in.getValue())){
                    ArrayList<Tree>arr_port1 = (Main.net.getAllTitles(cbox_country_in.getValue(), cbox_city_in.getValue()));
                    ArrayList<String> arr = new ArrayList<>();
                    for (ARTEM.Tree t: arr_port1){
                        arr.add(t.title);
                    }

                    arr_port=FXCollections.observableArrayList(arr);


                    //arr_port.remove(cbox_port_out.getValue());
                    cbox_port_in.setItems(arr_port);
                    AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_port_in = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_port_in);
                }else{
                    cbox_port_in.setItems(null);
                }
                //cbox_port_in.setValue("");
                cbox_city_in.hide();
                cbox_city_in.setVisibleRowCount(3);


            });

            System.out.println("RESTETEEWGG    "+arr_port.toString());
            cbox_port_in.setOnAction(actionEvent -> {
                cbox_port_in.hide();
                cbox_port_in.setVisibleRowCount(3);


            });


            cbox_port_out = new ComboBox<>();// array from Artemius
            cbox_city_out = new ComboBox<>(); // array from Artemius
            cbox_country_out = new ComboBox<>(arr_country); //array from Artemius
            cbox_country_out.setLayoutY(186);
            cbox_country_out.setLayoutX(89);
            cbox_country_out.setPrefWidth(173);
            cbox_country_out.getStylesheets().add(
                    (new File("src/YAROSLAV/size.css").toURI().toString()) );
            cbox_country_out.setVisibleRowCount(3);
            cbox_country_out.setVisibleRowCount(3);
            cbox_country_out.setPrefWidth(173);
            cbox_country_out.setEditable(true);
            cbox_city_out.setLayoutX(352);
            cbox_city_out.setLayoutY(186);
            cbox_city_out.setVisibleRowCount(3);
            cbox_city_out.setPrefWidth(173);
            cbox_city_out.setEditable(true);
            cbox_city_out.setPrefWidth(173);
            cbox_city_out.getStylesheets().add(
                    (new File("src/YAROSLAV/size.css").toURI().toString()) );
            cbox_country_out.setOnAction(actionEvent -> {
                if (arr_country.contains(cbox_country_out.getValue())){
                    arr_city = FXCollections.observableArrayList(Main.net.getAllCities(cbox_country_out.getValue()));
                    cbox_city_out.setItems(arr_city);
                    AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_city_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_city_out);

                }else {
                    cbox_city_out.setItems(null);
                }
                cbox_country_out.hide();
                cbox_country_out.setVisibleRowCount(3);

                cbox_port_out.setItems(null);
            });
            AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_country_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_country_out);





            cbox_city_out.setOnAction(actionEvent -> {
                if(arr_city.contains(cbox_city_out.getValue())){
                    ArrayList<Tree>arr_port1 = (Main.net.getAllTitles(cbox_country_out.getValue(), cbox_city_out.getValue()));
                    ArrayList<String> arr = new ArrayList<>();
                    for (ARTEM.Tree t: arr_port1){
                        arr.add(t.title);
                    }
                    arr_port=FXCollections.observableArrayList(arr);
                    if ((cbox_country_in.getValue().equals(cbox_country_out.getValue()))&&(cbox_city_in.getValue().equals(cbox_city_out.getValue()))){
                        //arr_port.remove(cbox_port_in.getValue());
                    }

                    cbox_port_out.setItems(arr_port);
                    AutoComboBox.AutoCompleteComboBoxListener<String> auto_cbox_port_out = new AutoComboBox.AutoCompleteComboBoxListener<>(cbox_port_out);

                }else{
                    cbox_port_out.setItems(null);
                }

                cbox_port_out.setVisibleRowCount(3);
                cbox_city_out.hide();
                cbox_city_out.setVisibleRowCount(3);

            });



            cbox_port_out.setLayoutY(186);
            cbox_port_out.setLayoutX(649);
            cbox_port_out.setPrefWidth(173);
            cbox_port_out.getStylesheets().add(
                    (new File("src/YAROSLAV/size.css").toURI().toString()) );
            cbox_port_out.setVisibleRowCount(3);


            cbox_port_out.setPrefWidth(173);
            cbox_port_out.setEditable(true);
            cbox_port_out.setOnAction(actionEvent -> {



                cbox_port_out.hide();
                cbox_port_out.setVisibleRowCount(3);

            });
            hours_in = new Spinner<>();
            hours_in.getEditor().setPrefWidth(86);
            hours_in.setLayoutX(388);
            hours_in.setLayoutY(288);
            SpinnerValueFactory<Integer> value_hours_in = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
            hours_in.setValueFactory(value_hours_in);
            hours_in.setEditable(true);



            min_in = new Spinner<>();
            min_in.setEditable(true);
            min_in.setLayoutX(688);
            min_in.setLayoutY(288);
            SpinnerValueFactory<Integer> value_min_in = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
            min_in.getEditor().setPrefWidth(86);
            min_in.setValueFactory(value_min_in);




            date_in = new DatePicker();
            date_in.setLayoutX(85);
            date_in.setLayoutY(288);
            date_in.setValue(LocalDate.now());



            final Callback<DatePicker, DateCell> dayCellFactory_in =
                    new Callback<DatePicker, DateCell>() {
                        @Override
                        public DateCell call(final DatePicker datePicker) {
                            return new DateCell() {
                                @Override
                                public void updateItem(LocalDate item, boolean empty) {
                                    super.updateItem(item, empty);

                                    if (item.isBefore(
                                            LocalDate.now())
                                    ) {
                                        setDisable(true);

                                    }
                                }
                            };
                        }
                    };

            date_in.setDayCellFactory(dayCellFactory_in);



            final Callback<DatePicker, DateCell> dayCellFactory_out =
                    new Callback<DatePicker, DateCell>() {
                        @Override
                        public DateCell call(final DatePicker datePicker) {
                            return new DateCell() {
                                @Override
                                public void updateItem(LocalDate item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (date_in.getValue()!=null) {
                                        if (item.isBefore(
                                                date_in.getValue())
                                        ) {
                                            setDisable(true);

                                        }
                                    }

                                }
                            };
                        }
                    };





            group.getChildren().add(cbox_country_in);
            group.getChildren().add(cbox_city_in);
            group.getChildren().add(cbox_port_in);
            group.getChildren().add(cbox_country_out);
            group.getChildren().add(cbox_city_out);
            group.getChildren().add(cbox_port_out);
            group.getChildren().add(date_in);

            group.getChildren().add(hours_in);

            group.getChildren().add(min_in);

            stage1.setScene(scene);





            stage1.show();
        }
      catch (Exception e){
            System.out.println(e);
      }
    }


    public void in_del_way() throws Exception {

    }
    public void screen_in_add_way() throws Exception{
        Stage stage2;
        if (ok_in_add_way()){


            stage2 = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group, 310, 122);
            Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\ok.fxml").toURI().toURL()));
            BorderPane root = new BorderPane();
            stage2.centerOnScreen();
            root.setCenter(content);
            stage2.initOwner(stage1);
            stage2.setResizable(false);
            group.getChildren().add(root);
            stage2.setScene(scene);

            stage1.close();

            stage2.show();
        }else{
            stage2 = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group, 292, 122);
            Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\error_data.fxml").toURI().toURL()));
            BorderPane root = new BorderPane();
            stage2.centerOnScreen();
            root.setCenter(content);
            stage2.initModality(Modality.WINDOW_MODAL);
            stage2.initOwner(stage1);
            stage2.setResizable(false);
            group.getChildren().add(root);
            stage2.setScene(scene);
            stage2.show();
        }
    }
    public boolean ok_in_add_way() throws Exception {
int day_1= 0, day_2 = 0;
        try{
            switch (day_in.getValue()){
                case "Понедельник":
                    day_1 = 0;
                    break;
                case "Вторник":
                    day_1 = 1;
                    break;
                case "Среда":
                    day_1 = 2;
                    break;
                case "Четверг":
                    day_1 = 3;
                    break;
                case "Пятница" :
                    day_1= 4;
                    break;
                case "Суббота":
                    day_1 = 5;
                    break;
                case "Воскресенье":
                    day_1 = 6;
                    break;
            }
            switch (day_out.getValue()){
                case "Понедельник":
                    day_2 = 0;
                    break;
                case "Вторник":
                    day_2 = 1;
                    break;
                case "Среда":
                    day_2 = 2;
                    break;
                case "Четверг":
                    day_2 = 3;
                    break;
                case "Пятница" :
                    day_2= 4;
                    break;
                case "Суббота":
                    day_2 = 5;
                    break;
                case "Воскресенье":
                    day_2 = 6;
                    break;
            }
System.out.println(day_1);
        if ((day_in.getValue() == null)||(day_out.getValue() == null)) {
            return false;

        }

        if ((cbox_country_in == null)){
            return  false;
        }
        if (!Main.net.getAllCountries().contains(cbox_country_in.getValue())){
            return  false;
        }
        if ((cbox_city_in==null)||(!Main.net.getAllCities(cbox_country_in.getValue()).contains(cbox_city_in.getValue()))){
            return  false;
        }
        if ((cbox_port_in == null)||(!Main.net.getAllTitles(cbox_country_in.getValue(),cbox_city_in.getValue()).contains(cbox_port_in.getValue()))){
            return  false;
        }


        if ((cbox_country_out == null)||(!Main.net.getAllCountries().contains(cbox_country_out.getValue()))){
            return  false;
        }
        if ((cbox_city_out==null)||(!Main.net.getAllCities(cbox_country_out.getValue()).contains(cbox_city_out.getValue()))){
            return  false;
        }
        if ((cbox_port_out == null)||(!Main.net.getAllTitles(cbox_country_out.getValue(),cbox_city_out.getValue()).contains(cbox_port_out.getValue()))){
            return  false;
        }


            if (Double.parseDouble(price.getText()) <= 0) {
                return false;
            }
            if ((cbox_country_in.getValue().equals(cbox_country_out.getValue()))&&(cbox_city_in.getValue().equals(cbox_city_out.getValue()))&&(cbox_port_in.getValue().equals(cbox_port_out.getValue()))){
                return false;
            }
        }

        catch (Exception e){
            return false;
        }
        long time_1 = 0, time_2 = 0;
        time_1 = (day_1*60*24+hours_in.getValue()*60+min_in.getValue())*60*1000;
        time_2 = (day_2*60*24+hours_out.getValue()*60+min_in.getValue())*60*1000;


        int id_1=Main.net.getIDByParams(cbox_port_in.getValue(),cbox_city_in.getValue(),cbox_country_in.getValue());
        int id_2 = Main.net.getIDByParams(cbox_port_out.getValue(),cbox_city_out.getValue(),cbox_country_out.getValue());
        if(!Main.net.addNewTimeline(id_1,id_2,time_1,time_2-time_1,Double.parseDouble(price.getText()))){
            return false;
        }


        return true;
    }
    public void ok_in_search_way(){

        boolean is_ok = true;
        try{

            if ((date_in.getValue() == null)){
                throw new Exception();

            }

            if ((cbox_country_in == null)){
                throw new Exception();
            }
            if (!Main.net.getAllCountries().contains(cbox_country_in.getValue())){
                throw new Exception();
            }
            if ((cbox_city_in==null)||(!Main.net.getAllCities(cbox_country_in.getValue()).contains(cbox_city_in.getValue()))){
                throw new Exception();
            }
            ArrayList<Tree> arr1 = Main.net.getAllTitles(cbox_country_in.getValue(),cbox_city_in.getValue());
            ArrayList<String> arr2 = new ArrayList<>();
            for (Tree t:arr1){
                arr2.add(t.title);

            }
            if ((cbox_port_in == null)||(!arr2.contains(cbox_port_in.getValue()))){
                throw new Exception();
            }


            if ((cbox_country_out == null)||(!Main.net.getAllCountries().contains(cbox_country_out.getValue()))){
                throw new Exception();
            }
            if ((cbox_city_out==null)||(!Main.net.getAllCities(cbox_country_out.getValue()).contains(cbox_city_out.getValue()))){
                throw new Exception();
            }
             arr1 = Main.net.getAllTitles(cbox_country_out.getValue(),cbox_city_out.getValue());
             arr2 = new ArrayList<>();
            for (Tree t:arr1){
                arr2.add(t.title);

            }
            if ((cbox_port_out == null)||(!arr2.contains(cbox_port_out.getValue()))){
                throw new Exception();
            }


            if ((cbox_country_in.getValue().equals(cbox_country_out.getValue()))&&(cbox_city_in.getValue().equals(cbox_city_out.getValue()))&&(cbox_port_in.getValue().equals(cbox_port_out.getValue()))){
                throw new Exception();
            }
            is_ok = true;
        }

        catch (Exception e){
            is_ok = false;
        }
        if (is_ok){
            int id_1 = 0, id_2 = 0;
            id_1 = Main.net.getIDByParams(cbox_port_in.getValue(),cbox_city_in.getValue(),cbox_country_in.getValue());
            id_2 = Main.net.getIDByParams(cbox_port_out.getValue(),cbox_city_out.getValue(),cbox_country_out.getValue());
            Timestamp temp_time = new Timestamp(date_in.getValue().getYear()-1900, date_in.getValue().getMonth().getValue()-1,date_in.getValue().getDayOfMonth(), hours_in.getValue(), min_in.getValue(),0,0);
            long time = temp_time.getTime();
            in_result(id_1,id_2,time);
        }else{
            try {
                Stage stage2;
                stage2 = new Stage();
                Group group = new Group();
                Scene scene = new Scene(group, 292, 122);
                Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\error_data.fxml").toURI().toURL()));
                BorderPane root = new BorderPane();
                stage2.centerOnScreen();
                root.setCenter(content);
                stage2.initModality(Modality.WINDOW_MODAL);
                stage2.initOwner(stage1);
                stage2.setResizable(false);
                group.getChildren().add(root);
                stage2.setScene(scene);
                stage2.show();
            }
            catch (Exception e){

            }
        }
    }
    public void in_result(int id_1,int id_2, long time) {
        try {
            ArrayList<String> info =Main.net.search(id_1,id_2,time);
            System.out.println(info);
            System.out.println(id_1);
            System.out.println(id_2);
            System.out.println(time);
            ArrayList<String> temp_info ;

            stage1 = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group, 834, 600);

            Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\del_way.fxml").toURI().toURL()));
            BorderPane root = new BorderPane();
            stage1.centerOnScreen();
            root.setCenter(content);
            stage1.initModality(Modality.WINDOW_MODAL);
            stage1.initOwner(Main.stage);
            stage1.setResizable(false);
            group.getChildren().add(root);
            stage1.setScene(scene);
            int iPlus = 0;
            //TODO: По айПласу размещать клолонки
            if ((info!=null)&&(info.size()!=0)) {
                int sizeOpt = Integer.parseInt(info.get(0)), sizeSpeed = Integer.parseInt(info.get(1)), sizePrice = Integer.parseInt(info.get(2));

                Text text_optimal = new Text("Самый оптимальный маршрут:\n");
                text_optimal.setStyle("-fx-font-size:25;" +
                        " ");

                text_optimal.setFill(Color.RED);
                text_optimal.setX(100);
                text_optimal.setY(40);

                AnchorPane pane = new AnchorPane();
                pane.getChildren().add(text_optimal);


                Info_button button = new Info_button();
                button.set_button_XY(100, 75);
                temp_info = new ArrayList();
                temp_info = info.get(2);

                button.set_info_out(temp_info.get(0));
                button.set_info_in(temp_info.get(1));
                button.set_time_fly(temp_info.get(2));
                button.set_price(temp_info.get(3));
                button.set_time_in(temp_info.get(4));
                button.set_time_out(temp_info.get(5));
                button.set_transfer(temp_info.get(6));
                button.set_action(temp_info);
                button.set_style(" -fx-border-color: red;", " -fx-border-color: red; -fx-effect: dropshadow( three-pass-box, red , 10.0, 0.0,0.0,0.0);");
                button.set_text();
                pane.getChildren().add(button.get_button());
                text_optimal = new Text("Самый быстрый маршрут:\n");
                text_optimal.setStyle("-fx-font-size:25;" +
                        " ");

                text_optimal.setX(100);
                text_optimal.setY(button.get_height());


                text_optimal.setFill(Color.BLUE);
                pane.getChildren().add(text_optimal);
                button = new Info_button();
                button.set_button_XY(100, text_optimal.getY() + 25);

                temp_info = new ArrayList();
                temp_info = info.get(0);

                button.set_info_out(temp_info.get(0));
                button.set_info_in(temp_info.get(1));
                button.set_time_fly(temp_info.get(2));
                button.set_price(temp_info.get(3));
                button.set_time_in(temp_info.get(4));
                button.set_time_out(temp_info.get(5));
                button.set_transfer(temp_info.get(6));
                button.set_action(temp_info);

                button.set_text();

                button.set_style(" -fx-border-color: blue;", " -fx-border-color: blue; -fx-effect: dropshadow( three-pass-box, blue , 10.0, 0.0,0.0,0.0);");

                pane.getChildren().add(button.get_button());
                text_optimal = new Text("Самый дешевый маршрут: ");
                text_optimal.setStyle("-fx-font-size:25;" +
                        " ");

                text_optimal.setX(100);
                text_optimal.setY(button.get_height());
                text_optimal.setFill(Color.web("#32CD32"));
                pane.getChildren().add(text_optimal);
                button = new Info_button();
                button.set_button_XY(100, text_optimal.getY() + 25);
                temp_info = new ArrayList();

                temp_info = info.get(1);

                button.set_info_out(temp_info.get(0));
                button.set_info_in(temp_info.get(1));
                button.set_time_fly(temp_info.get(2));
                button.set_price(temp_info.get(3));
                button.set_time_in(temp_info.get(4));
                button.set_time_out(temp_info.get(5));
                button.set_transfer(temp_info.get(6));
                button.set_action(temp_info);


                button.set_text();

                button.set_style(" -fx-border-color: #32CD32;", " -fx-border-color: #32CD32; -fx-effect: dropshadow( three-pass-box, #00FF00  , 10.0, 0.0,0.0,0.0);");

                pane.getChildren().add(button.get_button());
                if (info.size() > 3) {
                    text_optimal = new Text("Другие: ");
                    text_optimal.setStyle("-fx-font-size:25;" +
                            " ");

                    text_optimal.setX(100);
                    text_optimal.setY(button.get_height());
                    pane.getChildren().add(text_optimal);
                    GridPane grid = new GridPane();
                    grid.setPadding(new Insets(5));
                    grid.setVgap(10);
                    grid.setLayoutX(100);
                    grid.setLayoutY(text_optimal.getY() + 25);
                    for (int i = 0; i < info.size() - 3; i++) {
                        button = new Info_button();
                        button.set_style("-fx-border-color: black;", "-fx-border-color: black;-fx-effect: dropshadow( three-pass-box, black , 10.0, 0.0,0.0,0.0);");
                        temp_info = new ArrayList();

                        temp_info = info.get(i + 3);

                        button.set_info_out(temp_info.get(0));
                        button.set_info_in(temp_info.get(1));
                        button.set_time_fly(temp_info.get(2));
                        button.set_price(temp_info.get(3));
                        button.set_time_in(temp_info.get(4));
                        button.set_time_out(temp_info.get(5));
                        button.set_transfer(temp_info.get(6));
                        button.set_action(temp_info);


                        button.set_text();
                        grid.add(button.get_button(), 0, i);



                    }
                    pane.getChildren().add(grid);

                }
                javafx.scene.control.ScrollPane scrollPane = new ScrollPane(pane);
                scrollPane.setPrefWidth(834);
                scrollPane.setStyle("-fx-background:white;");
                scrollPane.setPrefHeight(540);
                scrollPane.setLayoutX(0);
                scrollPane.setLayoutY(60);
                scrollPane.fitToWidthProperty();
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                group.getChildren().add(scrollPane);
            }else{
                Text text_optimal = new Text("Авиарейсы отсутствуют.\n");
                text_optimal.setStyle("-fx-font-size:40;" +
                        " ");


                text_optimal.setX(100);
                text_optimal.setY(300);
                group.getChildren().add(text_optimal);
            }

            stage1.show();
        }
        catch (Exception e){
System.out.println(e);
System.out.println(e.getLocalizedMessage());
        }
    }
    public void save() throws Exception{
        try {
            Main.net.saveData();
        }
       catch (Exception e){
           Stage  stage2 = new Stage();
           Group group = new Group();
           Scene scene = new Scene(group, 292, 122);
           Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\error_data.fxml").toURI().toURL()));
           BorderPane root = new BorderPane();
           stage2.centerOnScreen();
           root.setCenter(content);
           stage2.initModality(Modality.WINDOW_MODAL);
           stage2.initOwner(stage1);
           stage2.setResizable(false);
           group.getChildren().add(root);
           stage2.setScene(scene);
           stage2.show();
       }
    }
    public void del_all(){
        Main.net.removeAllData();
    }
    public void random(){

try {
    Main.net.removeAllData();
    Main.net.loadRandomData();
}
catch (Exception e){

}
    }
public void map_search(){
    stage = new Stage();

    stage.centerOnScreen();
    Group group = new Group();
    Scene scene= new Scene(group,1280, 800);
    stage.setResizable(false);
    try{
    Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\map_search.fxml" ).toURI().toURL()));
    BorderPane root= new BorderPane();
    root.getChildren().add(content);

    AnchorPane pane = new AnchorPane();
    AnchorPane pane_up = new AnchorPane();
    pane_up.setPrefHeight(87);
    pane_up.setLayoutY(0);
    pane_up.setLayoutX(0);
    pane_up.setPrefWidth(873);

    pane.setPrefWidth(1280);
    pane.setLayoutX(0);
    pane.setLayoutY(92);
    pane.setPrefHeight(710);
   text_in = new TextField("");
    text_in.setPrefWidth(335);
    text_in.setPrefHeight(25);
    text_in.setLayoutY(14);

    text_in.setLayoutX(104);
    text_in.setEditable(false);


     text_out = new TextField("");
    text_out .setPrefWidth(335);
    text_out .setPrefHeight(25);
    text_out .setLayoutY(52);

    text_out .setLayoutX(104);
    text_out .setEditable(false);


    pane_up.getChildren().add(text_in);
    pane_up.getChildren().add(text_out);

date_in = new DatePicker();


        date_in.setLayoutX(685);
        date_in.setLayoutY(14);
        date_in.setValue(LocalDate.now());



        final Callback<DatePicker, DateCell> dayCellFactory_in =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);

                                if (item.isBefore(
                                        LocalDate.now())
                                ) {
                                    setDisable(true);

                                }
                            }
                        };
                    }
                };

        date_in.setDayCellFactory(dayCellFactory_in);

pane_up.getChildren().add(date_in);

        hours_in = new Spinner<>();
        hours_in.getEditor().setPrefWidth(86);
        hours_in.setLayoutX(512);
        hours_in.setLayoutY(50);
        SpinnerValueFactory<Integer> value_hours_in = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
        hours_in.setValueFactory(value_hours_in);
        hours_in.setEditable(true);



        min_in = new Spinner<>();
        min_in.setEditable(true);
        min_in.setLayoutX(730);
        min_in.setLayoutY(50);
        SpinnerValueFactory<Integer> value_min_in = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        min_in.getEditor().setPrefWidth(86);
        min_in.setValueFactory(value_min_in);

pane_up.getChildren().add(hours_in);
pane_up.getChildren().add(min_in);
ArrayList<ArrayList<Double>> list = Main.net.tempPoints();
for (var el : list ){
    Map_but_in but = new Map_but_in(text_in,text_out);
    but.set_XY(el.get(0),el.get(1)+400);
    but.set_button_info("ERerewr");
    pane.getChildren().add(but.get_button());


}
        Map_but_in but = new Map_but_in(text_in,text_out);
        but.set_button_info("ДНР");
        but.set_XY(600,200);
        pane.getChildren().add(but.get_button());
    group.getChildren().add(root);


    group.getChildren().add(pane_up);
    group.getChildren().add(pane);
    stage.setScene(scene);
    stage.show();
    }
    catch (Exception e){

    }
}
public void map_result() {
    stage = new Stage();

    stage.centerOnScreen();
    Group group = new Group();
    Scene scene = new Scene(group, 1280, 800);
    stage.setResizable(false);
    try {
        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\map_search.fxml").toURI().toURL()));
        BorderPane root= new BorderPane();
        root.getChildren().add(content);

        AnchorPane pane = new AnchorPane();



        pane.setPrefWidth(1280);
        pane.setLayoutX(0);
        pane.setLayoutY(92);
        pane.setPrefHeight(710);
        Button_Map_Result one = new Button_Map_Result(1);
        one.set_XY(100,100);
        Button_Map_Result two = new Button_Map_Result(2);
        two.set_XY(500,300);
        Line line = new Line();
        Path path = new Path();

     one.set_line(pane,two.get_x(),two.get_y());
pane.getChildren().add(one.get_button());
pane.getChildren().add(two.get_button());
pane.getChildren().add(line);
        group.getChildren().add(root);



        group.getChildren().add(pane);
        stage.setScene(scene);
        stage.show();
    }    catch (Exception e){

    }
}
public void inLogIn(){

    if((password.getText().equals("admin"))&&(login.getText().equals("admin"))){
stage.close(); admin = true;
        adminMenu();
    }else{
        try {
            stage.close();
            admin = false;
            in_search_way();

        }catch (Exception e){

        }
    }
}
public void adminMenu(){
    stage = new Stage();
    Stage primaryStage = stage;


    primaryStage.centerOnScreen();
    Group group = new Group();
    Scene scene= new Scene(group,1280, 800);
    primaryStage.setResizable(false);
    try {
        Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\main1.fxml").toURI().toURL()));
        BorderPane root = new BorderPane();
        root.getChildren().add(content);

        group.getChildren().add(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    catch (Exception e){

    }






}
@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

}
