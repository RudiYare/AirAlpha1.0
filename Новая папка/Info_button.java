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

public class Info_button {
    private Button button;
    private String info_out;
    private String info_in;
    private String price;
    private String transfer;
    private String time_in;
    private String time_out;

    public Info_button()  {
        button = new Button();
        button.setPrefWidth(590);
        button.setPrefHeight(200);
        //info_out = "Откуда: ";
        //info_in = "Куда: ";
        //price = "Стоимость:";
        //transfer = "Количество пересадок: ";
        //time_in = "Время отправления: ";
        //time_out = "Время прибытия: ";

    }

    public void set_button_XY(double x, double y) {
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

    public Button get_button() {
        return button;
    }
    public void set_action(){
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
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                Text text = new Text();
                text.setFont(Font.font(15));
                text.setText(info_in);
                text.setLayoutY(25);
                text.setLayoutX(10);
                pane.getChildren().add(text);
                scrollPane.setContent(pane);
                group.getChildren().add(scrollPane);
                stage2.setScene(scene);
                stage2.show();
            }
            catch (Exception e){

            }
        });
    }
    public void set_text() {
        AnchorPane pane = new AnchorPane();
        Text text = new Text();
        text.setText("Откуда:"+info_out);
        text.setLayoutX(15);
        text.setLayoutY(25);
        text.setFont(Font.font(20));
        pane.getChildren().add(text);
        text = new Text();
        text.setFont(Font.font(20));
        text.setText("Куда: "+ info_in);
        text.setFont(Font.font(20));
        text.setLayoutX(15);
        text.setLayoutY(50);
        pane.getChildren().add(text);
        text = new Text();
        text.setFont(Font.font(20));
        text.setText("Стоимость: "+price);
        text.setLayoutX(15);
        text.setLayoutY(85);
        pane.getChildren().add(text);
        text = new Text();
        text.setFont(Font.font(20));
        text.setText("Количество пересадок: "+transfer);
        text.setLayoutX(15);
        text.setLayoutY(110);
        pane.getChildren().add(text);
        text = new Text();
        text.setFont(Font.font(20));
        text.setText("Дата и Время отправления: "+time_in);
        text.setLayoutX(15);
        text.setLayoutY(145);
        pane.getChildren().add(text);
        text = new Text();
        text.setFont(Font.font(20));
        text.setText("Дата и Время  прибытия: "+time_out);
        text.setLayoutX(15);
        text.setLayoutY(170);
        pane.getChildren().add(text);

        button.setGraphic(pane);
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