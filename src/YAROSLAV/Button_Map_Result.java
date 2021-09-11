package YAROSLAV;

import javafx.scene.control.Button;

public class Button_Map_Result {
    private Button button;
    private String style;
    private double x;
    private double y;
    Button_Map_Result(int i){
        button = new Button();

        button.setText(Integer.toString(i));
        button.setPrefWidth(15);
        button.setPrefHeight(15);
        button.setMinHeight(15);
        button.setMinWidth(15);
        button.setMaxHeight(15);
        button.setMinWidth(15);
        style = "-fx-background-radius:20; " +
                "-fx-border-radius:20;" +
                "-fx-background-color: blue;"+"-fx-cursor:hand;";
        button.setStyle(style);
    }
    public void set_XY(double x, double y){
        this.x = x;
        this.y = y;
        button.setLayoutY(y);
        button.setLayoutX(x);
    }
    public double get_x(){
        return x;
    }
    public double get_y(){
        return y;
    }

}
