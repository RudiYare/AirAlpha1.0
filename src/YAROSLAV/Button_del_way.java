package YAROSLAV;

import javafx.scene.control.Button;

public class Button_del_way {
    private double x = 0,y=0;

    Button_del_way(Button button,  String country_in, String city_in, String port_in,String country_out, String city_out, String port_out, double price, String date_in, String date_out  ){

        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(590);
        button.setPrefHeight(200);
        button.setStyle("-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"+

                "-fx-background-color: white;" +
                "-fx-border-color: black;");
        x+=5;
        y += 5;
    }
    public void delete(){

    }
}
