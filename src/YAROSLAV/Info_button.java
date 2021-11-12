package YAROSLAV;

import ARTEM.Flight;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Info_button {

    private Button button;
    private String info_out;
    private String info_in;
    private String price;
    private String transfer;
    private String time_in;
    private String time_out;
    public Button mapButton;
    private String time_fly;
    private static Stage stage2;
    private double  y;
    public Info_button()  {
        button = new Button();
        button.setMaxWidth(590);
        //button.setPrefHeight(200);
y = 0;
        //info_out = ;
        //info_in = "Куда: ";
        //price = "Стоимость:";
        //transfer = "Количество пересадок: ";
        //time_in = "Время отправления: ";
        //time_out = "Время прибытия: ";
        //time_fly = "Время полета "
    }
    public double get_height(){
return  y;
    }
    public void set_button_XY(double x, double y) {

        this.y = y;
        button.setLayoutX(x);
        button.setLayoutY(y);
    }

    public void set_info_out(String s) {
        info_out = s;
    }

    public void set_info_in(String s) {
        info_in = s;
    }

    public void set_price(String s) {
        price = s;
    }

    public void set_transfer(String s) {
        transfer = s;
    }

    public void set_time_in(String s) {
        time_in = s;
    }

    public void set_time_out(String s) {
        time_out = s;
    }
    public void set_time_fly(String s){
        time_fly = s;
    }
    public Button get_button() {
        return button;
    }
    public void setMap(ArrayList<Flight> flights){
        try {

            Stage stage3 = new Stage();
            Group group = new Group();
            Scene scene = new Scene(group, 1280, 800);

            AnchorPane pane = new AnchorPane();
            ScrollPane scrollPane = new ScrollPane(pane);




            scrollPane.setPrefWidth(1200);
            scrollPane.setLayoutX(0);
            scrollPane.setLayoutY(5);
            scrollPane.setPrefHeight(710);
            ImageView imageMap = new ImageView(new File("src/YAROSLAV/map.jpg").toURI().toURL().toString());
            imageMap.setFitWidth(2560);
            imageMap.setFitHeight(1440);
            pane.getChildren().add(imageMap);


            stage3.initModality(Modality.WINDOW_MODAL);
            double finishX = -500, finishY = -500 ;

            for (Flight flight : flights){
                Button_Map_Result but = new Button_Map_Result(0);
                but.get_button().setLayoutX(but.get_x());
                but.get_button().setLayoutY(but.get_y());
            but.set_XY(flight.from_x,flight.from_y);
                but.set_line(pane,flight.where_x, flight.where_y);
pane.getChildren().add(but.get_button());
finishX = flight.where_x;
finishY=flight.where_y;
            }
            if (Double.compare(finishX,-500)!=0 && Double.compare(finishY,-500)!=0 ){
                Button_Map_Result button_map_result = new Button_Map_Result(0);
                button_map_result.get_button().setLayoutX( finishX);
                button_map_result.get_button().setLayoutY(finishY);
                pane.getChildren().add(button_map_result.get_button());
            }
            if (Integer.parseInt(transfer)!=1){

                stage3.initOwner(stage2);
            }else{

                stage3.initOwner(MyController.stage1);
            }

            group.getChildren().add(scrollPane);
            stage3.setScene(scene);
            stage3.setResizable(false);
            stage3.show();
        }
       catch (Exception e){
            System.out.println(e);
       }

    }
    public void set_action(ArrayList<Flight> flights) {

            button.setOnAction(actionEvent -> {

                try {
                    mapButton = new Button("Карта");
                    mapButton.setLayoutX(50);
                    mapButton.setLayoutY(5);
                    mapButton.setStyle("    \n" +
                            "    -fx-background-insets: 0,1,2,3;\n" +
                            "    -fx-background-radius: 30;\n" +
                            "   \n" +
                            "\n" +
                            "    -fx-transition:0.5s;\n" +
                            "-fx-background-color: \n" +
                            "#4569d6;");
                    mapButton.setOnMouseEntered(actionEvent5->{
                        mapButton.setStyle("    \n" +
                                "    -fx-background-insets: 0,1,2,3;\n" +
                                "    -fx-background-radius: 30;\n" +
                                "   \n" +
                                "\n" +
                                "    -fx-transition:0.5s;\n" +
                                "-fx-background-color: \n" +
                                "#4569d6;-fx-effect: dropshadow( three-pass-box, black , 10.0, 0.0,0.0,0.0);");
                    });
                    mapButton.setOnMouseExited(actionEvent7->{
                        mapButton.setStyle("    \n" +
                                "    -fx-background-insets: 0,1,2,3;\n" +
                                "    -fx-background-radius: 30;\n" +
                                "   \n" +
                                "\n" +
                                "    -fx-transition:0.5s;\n" +
                                "-fx-background-color: \n" +
                                "#4569d6;");
                    });
                    stage2 = new Stage();
                    Group group = new Group();

                    Scene scene = new Scene(group, 500, 274);
                    Parent content = FXMLLoader.load((new File("src\\YAROSLAV\\info.fxml").toURI().toURL()));
                    BorderPane root = new BorderPane();
                    stage2.centerOnScreen();
                    root.setCenter(content);

                    stage2.initModality(Modality.WINDOW_MODAL);
                    stage2.initOwner(MyController.stage1);
                    stage2.setResizable(false);
                    group.getChildren().add(root);
                    ScrollPane scrollPane = new ScrollPane();
                    scrollPane.setLayoutY(62);
                    scrollPane.setPrefWidth(500);
                    scrollPane.setPrefHeight(212);
                    scrollPane.setStyle("-fx-background:white;");
                    AnchorPane pane = new AnchorPane();
                    pane.setMaxWidth(500);
                    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    Text text = new Text();
                    text.setFont(Font.font(15));
                    String t = "";
                    int j = 7;
                    int i = 1;

                    for (Flight flight : flights){
                        t += String.valueOf(i ) + ") ";
                        t += "Откуда: " + flight.from + "\n";
                        t += "Куда: " + flight.where + "\n";
                        t += "Дата отправления: " + flight.time_from+ "\n";
                        t += "Дата прибытия: " + flight.time_where + "\n";
                        t += "Стоимость: " + flight.price + "\n";  t += "\n";
                        i++;
                    }
                    mapButton.setOnAction(actionEvent1 -> {
                        setMap(flights);
                    });
                    text.setText(t);
                    text.setLayoutY(25);
                    text.setLayoutX(10);
                    text.setFont(Font.font(20));
                    text.setWrappingWidth(470);
                    pane.getChildren().add(text);
                    scrollPane.setContent(pane);
                    group.getChildren().add(scrollPane);group.getChildren().add(mapButton);
                    stage2.setScene(scene);
                    stage2.show();
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        }
    public void setMapAction(ArrayList<Flight> flights){
        button.setOnAction(actionEvent -> {
            setMap(flights);
        });
    }
    public void set_text() {
        AnchorPane pane = new AnchorPane();
        pane.setMaxWidth(550);
        Text text = new Text();
        String t = "";


        text.setLayoutX(5);
        text.setLayoutY(23);
        text.setFont(Font.font(20));
        text.setWrappingWidth(590);


       // text = new Text();
        //text.setFont(Font.font(20));
        t+="Откуда: "+ info_out+"\n";
        t+="Куда: "+ info_in+"\n";
        t+="Время полета: "+ time_fly   +"\n";
        //text.setLayoutX(5);
        //text.setLayoutY(48);
        //pane.getChildren().add(text);
        //text = new Text();
        //text.setFont(Font.font(20));

        //text.setFont(Font.font(20));
        //text.setLayoutX(5);
        //text.setLayoutY(73);
        //pane.getChildren().add(text);
        //text = new Text();
        //text.setFont(Font.font(20));
        t+="Стоимость: "+price+" р\n";
        //text.setLayoutX(5);
        //text.setLayoutY(98);
        //pane.getChildren().add(text);
        //text = new Text();
        //text.setFont(Font.font(20));
        t+="Количество пересадок: "+transfer+"\n";
        //text.setLayoutX(5);
        //text.setLayoutY(123);
        //pane.getChildren().add(text);
        //text = new Text();
        //text.setFont(Font.font(20));
        t+="Дата и время отправления: "+time_in+"\n";
        //text.setLayoutX(5);
        //text.setLayoutY(148);
        //pane.getChildren().add(text);
        //text = new Text();
        //text.setFont(Font.font(20));
        t+="Дата и время прибытия: "+time_out+"\n";
        //text.setLayoutX(5);
        //text.setLayoutY(173);
        //pane.getChildren().add(text);
        text.setText(t);
        pane.getChildren().add(text);
        button.setGraphic(pane);
        y  += text.getLayoutBounds().getHeight() +75;
        //button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    public void set_style(String s, String effect) {
        button.setStyle("-fx-border-radius: 25; -fx-background-radius: 30;-fx-background-color: white; ;-fx-border-width:5;"+s);
        button.setOnMouseEntered(a ->
                button.setStyle("-fx-border-radius: 25; -fx-background-radius: 30;-fx-background-color: white; ;-fx-border-width:5;"+effect));

        button.setOnMouseExited(a->
                button.setStyle("-fx-border-radius: 25; -fx-background-radius: 30;-fx-background-color: white; ;-fx-border-width:5;"+s));
    }

}