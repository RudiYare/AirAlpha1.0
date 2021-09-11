package YAROSLAV;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class Map_but_in {
    private TextField text_in;
    private TextField text_out;
    private Button button;
    private String style;
    private String info;
    private double x;
    private double y;
    private  boolean is_click;
    Map_but_in(TextField in, TextField out){
        text_in = in;
        text_out = out;
        button = new Button();
        button.setText("");
        button.setPrefWidth(8);
        button.setPrefHeight(8);
        button.setMinHeight(8);
        button.setMinWidth(8);
        button.setMaxHeight(8);
        button.setMinWidth(8);
        style = "-fx-background-radius:20; " +
                "-fx-border-radius:20;" +
                "-fx-background-color: blue;"+"-fx-cursor:hand;";
   button.setStyle(style);


is_click = false;
    }
    public void set_button_info(String info){
        this.info=info;
        Tooltip tip = new Tooltip(info);
        tip.setShowDelay(Duration.seconds(0.25));
        tip.setStyle("-fx-font-size:15");
        button.setTooltip(tip);

        button.setOnAction(action->{
            if (!is_click) {
                if (text_in.getText().equals("")) {
                    text_in.setText(info);
                    is_click = true;
                    button.setStyle(style + "-fx-effect: dropshadow( three-pass-box, red , 10.0, 0.0,0.0,0.0);");
                    button.setPrefWidth(12);
                    button.setPrefHeight(12);
                    button.setMinHeight(12);
                    button.setMinWidth(12);
                    button.setMaxHeight(12);
                    button.setMinWidth(12);
                    button.setLayoutX(x - 2);
                    button.setLayoutY(y - 2);
                } else if (text_out.getText().equals("")) {
                    text_out.setText(info);
                    is_click = true;
                    button.setStyle(style + "-fx-effect: dropshadow( three-pass-box, red , 10.0, 0.0,0.0,0.0);");
                    button.setPrefWidth(12);
                    button.setPrefHeight(12);
                    button.setMinHeight(12);
                    button.setMinWidth(12);
                    button.setMaxHeight(12);
                    button.setMinWidth(12);
                    button.setLayoutX(x - 2);
                    button.setLayoutY(y - 2);
                }

                } else if (is_click) {
                    if (text_in.getText().equals(info)) {
                        button.setStyle(style);
                        button.setPrefWidth(8);
                        button.setPrefHeight(8);
                        button.setMinHeight(8);
                        button.setMinWidth(8);
                        button.setMaxHeight(8);
                        button.setMinWidth(8);
                        button.setLayoutX(x);
                        button.setLayoutY(y);
                        is_click = false;
                        text_in.setText("");
                    }else
                    if (text_out.getText().equals(info)) {
                        button.setStyle(style);
                        button.setPrefWidth(8);
                        button.setPrefHeight(8);
                        button.setMinHeight(8);
                        button.setMinWidth(8);
                        button.setMaxHeight(8);
                        button.setMinWidth(8);
                        button.setLayoutX(x);
                        button.setLayoutY(y);
                        is_click = false;
                        text_out.setText("");
                    }
                }

        });
button.setOnMouseEntered(action->{
    if (!is_click) {
        button.setStyle(style + "-fx-effect: dropshadow( three-pass-box, blue , 10.0, 0.0,0.0,0.0);" + "-fx-cursor:hand");

    }

});
button.setOnMouseExited(action->{
    if (!is_click){
        button.setStyle(style);
    }
});
    }
public void set_XY(double x, double y){
        this.x = x;
        this.y = y;
        button.setLayoutY(y);
        button.setLayoutX(x);
}
public Button get_button() {
    return button;
}

}
