package YAROSLAV;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

public class Button_Map_Result {
    private Button button;
    private String style;
    private double x;
    private double y;
    Button_Map_Result(int i){
        button = new Button();

        //button.setText(Integer.toString(i));
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
    public Button get_button() {
        return button;
    }
    public void set_line(AnchorPane pane, double x,double y){
        //x+=7.5;
        //y+= 7.5;

            if ((Double.compare(get_x(),x) == 1)&&(Double.compare(get_y(),y) == 0)){
                y += 7.5;
                x+=15;
            }else
            if ((Double.compare(get_x(),x) == 1)&&(Double.compare(get_y(),y) == -1)){
                x+=15;

            }else
            if ((Double.compare(get_x(),x) == 1)&&(Double.compare(get_y(),y) == 1)){
                x+=15;
                y+=15;

            }else
            if ((Double.compare(get_x(),x) == -1)&&(Double.compare(get_y(),y) == -1)) {


            }
            else
                if ((Double.compare(get_x(),x) == -1)&&(Double.compare(get_y(),y) == 0)) {

                    y+=7.5;
                }
                else
                if ((Double.compare(get_x(),x) == -1)&&(Double.compare(get_y(),y) == 1)) {
                    y+=15;
                }
                else
                if ((Double.compare(get_x(),x) == 0)&&(Double.compare(get_y(),y) == -1)) { // точка
                    x+=7.5;
                }    else
                if ((Double.compare(get_x(),x) == 0)&&(Double.compare(get_y(),y) == 1)) { // точка
                    x+=7.5;
                    y+=15;
                }

        Line line = new Line();
        line.setStartX(get_x()+7.5);
        line.setStartY(this.get_y()+7.5);
        line.setEndX(x);
        line.setEndY(y);
        line.setStrokeWidth(5);
        line.setSmooth(true);
        double  beta = Math.atan2(get_y()-y,x-get_x());
        double alfa = Math.PI/15;// {угол между основной осью стрелки и рисочки в конце}
        int r1 = 20;

        int x2 =(int) Math.round(x - r1*Math.cos(beta + alfa));
        int y2 =(int)Math.round(y + r1*Math.sin(beta + alfa));
        Line line1 = new Line();
        line1.setStartX(x);
        line1.setStartY(y);
        line1.setEndX(x2);
        line1.setEndY(y2);
        line1.setStrokeWidth(5);
        line1.setSmooth(true);
        pane.getChildren().add(line);
        pane.getChildren().add(line1);
        x2 =(int) Math.round(x - r1*Math.cos(beta - alfa));
        y2 =(int)Math.round(y + r1*Math.sin(beta - alfa));
        Line line2 = new Line();
        line2.setStartX(x);
        line2.setStartY(y);
        line2.setEndX(x2);
        line2.setEndY(y2);
        line2.setStrokeWidth(5);
        line2.setSmooth(true);
        pane.getChildren().add(line2);
    }
}
