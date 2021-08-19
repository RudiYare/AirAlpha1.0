package YAROSLAV;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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

    @FXML
    TextField text_password;
    @FXML
    NameTextField name ;
    @FXML
    NameTextField city;
    @FXML
    NameTextField country;


    //!!!!!!!!!!!!!!!!!!!!!!!!!!!
    ObservableList<String> arr_country =  FXCollections.observableArrayList();
    ObservableList<String> arr_city =  FXCollections.observableArrayList();
    ObservableList<String> arr_port =  FXCollections.observableArrayList();

    //!!!!!!!!!!!!!!!!!!!!!!!!!!

    public static long date_1;
    public static long date_2;
    public static ComboBox <String> cbox_country_out;
    public static ComboBox <String> cbox_country_in;
    public static ComboBox <String> cbox_city_out;
    public static ComboBox <String> cbox_city_in;
    public static ComboBox <String> cbox_port_out;
    public static ComboBox <String> cbox_port_in;
    public static Spinner<Integer> hours_in;
    public static Spinner<Integer> hours_out;
    public static PriceTextField price ;
    public static Spinner <Integer> min_in;
    public static Spinner <Integer> min_out;
    public void screen(String str){
        System.out.println(str);
    }
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
        stage1 = new Stage();
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
    public void ok_in_add_port() throws Exception {
        Stage stage2;
        String  name_port ;
        String city_port ;
        String country_port ;

        if ((name.getText().length()!=0)&&(name.getText().charAt(name.getText().length()-1) == ' ')){
            name_port = name.getText().substring(0,name.getText().length()-1 );
        }else{
            name_port = name.getText();
        }
        if ((city.getText().length()!=0)&&(city.getText().charAt(city.getText().length()-1)==' ' )){
            city_port = city.getText().substring(0,city.getText().length()-1);
        }else{
            city_port = city.getText();
        }
        if ((country.getText().length()!=0)&&(country.getText().charAt(country.getText().length()-1)==' ' )){
            country_port = country.getText().substring(0,country.getText().length()-1);
        }else{
            country_port = country.getText();
        }
        if (name_port.equals("") || (city_port.equals("")) || (country_port.equals(""))){
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
        } else
            if (!Main.net.addNewAirport(name_port,city_port,country_port.toString())){
                System.out.println("5");
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
            }else {
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
        cbox_country_in.setItems(arr_country);
        cbox_country_in.setLayoutX(89);
        cbox_country_in.setLayoutY(102);
        cbox_country_in.setVisibleRowCount(3);
cbox_country_in.setEditable(true);

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
                arr_port=FXCollections.observableArrayList(Main.net.getAllTitles(cbox_country_in.getValue(), cbox_city_in.getValue()));
                arr_port.remove(cbox_port_out.getValue());
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
        cbox_city_out.setLayoutX(352);
        cbox_city_out.setLayoutY(186);
        cbox_city_out.setVisibleRowCount(3);
        cbox_city_out.setPrefWidth(173);
        cbox_city_out.setEditable(true);

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
                arr_port=FXCollections.observableArrayList(Main.net.getAllTitles(cbox_country_out.getValue(), cbox_city_out.getValue()));
                if ((cbox_country_in.getValue().equals(cbox_country_out.getValue()))&&(cbox_city_in.getValue().equals(cbox_city_out.getValue()))){
                    arr_port.remove(cbox_port_in.getValue());
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


        date_in = new DatePicker();
        date_in.setLayoutX(85);
        date_in.setLayoutY(288);
        date_in.setValue(LocalDate.now());

        date_out = new DatePicker();
        date_in.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
            date_out.setValue(null);
        });
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

        date_out.setLayoutX(85);
        date_out.setLayoutY(385);

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
            date_out.setDayCellFactory(dayCellFactory_out);


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

        javafx.scene.control.ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(834);

        scrollPane.setPrefHeight(540);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(60);
        scrollPane.fitToWidthProperty();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        group.getChildren().add(scrollPane);

        scrollPane.setStyle("-fx-background:white;");


        stage1.setScene(scene);

        stage1.show();
    }
    public void screen_in_add_way() throws Exception{
        if (ok_in_add_way()){
            Stage stage2;

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
            screen("rerer" );
        }
    }
    public boolean ok_in_add_way() throws Exception {
        date_1 = 0;
        date_2 = 0;
        try{
        if ((date_in.getValue() == null)||(date_out.getValue() == null))
        {
            return  false;

        }else{
            Timestamp date1 = new Timestamp(date_in.getValue().getYear() - 1900, date_in.getValue().getMonth().getValue() - 1, date_in.getValue().getDayOfMonth(), hours_in.getValue(), min_in.getValue(), 0, 0);
            Timestamp date2 = new Timestamp(date_out.getValue().getYear() - 1900, date_out.getValue().getMonth().getValue() - 1, date_out.getValue().getDayOfMonth(), hours_out.getValue(), min_out.getValue(), 0, 0);
            date_1 = date1.getTime();
            date_2 = date2.getTime();
        }
        screen(price.getText());


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
        if (date_1 >= date_2){
            return  false;
        }

            if (Double.parseDouble(price.getText()) <= 0) {
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
        int id_1=Main.net.getIDByParams(cbox_country_in.getValue(),cbox_city_in.getValue(),cbox_port_in.getValue());
        int id_2 = Main.net.getIDByParams(cbox_country_out.getValue(),cbox_city_out.getValue(),cbox_port_out.getValue());
        Main.net.addNewTimeline(id_1,id_2,date_1,date_2-date_1, Double.parseDouble(price.getText()) );


                //TODO: ТУТ ЧЕРЕЗ АЙДИШНИК!!!
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

}
