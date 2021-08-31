package YAROSLAV;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
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
    private String time_fly;
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
    public void set_action(ArrayList<String> info) {
        if (info.size() > 12) {
            button.setOnAction(actionEvent -> {

                try {


                    Stage stage2 = new Stage();
                    Group group = new Group();
                    Scene scene = new Scene(group, 346, 274);
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
                    scrollPane.setPrefWidth(346);
                    scrollPane.setPrefHeight(212);
                    scrollPane.setStyle("-fx-background:white;");
                    AnchorPane pane = new AnchorPane();
                    pane.setMaxWidth(330);
                    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                    Text text = new Text();
                    text.setFont(Font.font(15));
                    String t = "";
                    int j = 7;
                    for (int i = 0; i < Integer.parseInt(info.get(6)); i++) {
                        t += String.valueOf(i + 1) + ") ";
                        t += "Откуда: " + info.get(j) + "\n";
                        j++;
                        t += "Куда " + info.get(j) + "\n";
                        j++;
                        t += "Дата отправления: " + info.get(j) + "\n";
                        j++;
                        t += "Дата прибытия: " + info.get(j) + "\n";
                        j++;
                        t += "Стоимость: " + info.get(j) + "\n";
                        j++;
                        t += "\n";
                    }

                    text.setLayoutY(25);
                    text.setLayoutX(10);
                    text.setFont(Font.font(20));
                    text.setWrappingWidth(330);
                    pane.getChildren().add(text);
                    scrollPane.setContent(pane);
                    group.getChildren().add(scrollPane);
                    stage2.setScene(scene);
                    stage2.show();
                } catch (Exception e) {

                }
            });
        }
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
        t+="Куда: "+ info_in+"\n";
        t+="Время полета: "+ time_fly   +"\n";
        //text.setLayoutX(5);
        //text.setLayoutY(48);
        //pane.getChildren().add(text);
        //text = new Text();
        //text.setFont(Font.font(20));
        t+="Время полета: "+ time_fly+"\n";
        //text.setFont(Font.font(20));
        //text.setLayoutX(5);
        //text.setLayoutY(73);
        //pane.getChildren().add(text);
        //text = new Text();
        //text.setFont(Font.font(20));
        t+="Стоимость: "+price+"\n";
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
        t+="Дата и Время отправления: "+time_in+"\n";
        //text.setLayoutX(5);
        //text.setLayoutY(148);
        //pane.getChildren().add(text);
        //text = new Text();
        //text.setFont(Font.font(20));
        t+="Дата и Время  прибытия: "+time_out+"\n";
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