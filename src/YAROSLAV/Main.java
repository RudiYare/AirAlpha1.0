

package YAROSLAV;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {


    public static void main(String[] args)  {

            launch(args);


    }
    private class NameTextField extends TextField{
        @Override
        public void replaceText(int i, int i1, String s) {
            String old = getText();
            if ( s.matches("[a-z]*")|| s.matches("[A-Z]*")|| s.matches("[а-я]*")|| s.matches("[А-Я]*")){
                super.replaceText(i,i1,s);

            }
        }
    }
    public void start(Stage primaryStage) throws Exception  {
        Group group1 = new Group();
        Scene scene_main = new Scene(group1,500,500);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Text");
        NameTextField tf = new NameTextField();
        tf.setMaxWidth(100);
        tf.setMinHeight(30);
        tf.setPromptText("Введите имя:");
        tf.setLayoutX(0);
        tf.setLayoutY(20);
        Text text_bg = new Text("");
        tf.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.ENTER)){
                    text_bg.setFill(Color.RED);
                text_bg.setText("КНОПКА ДЛЯ ЭТОГО ЕСТЬ, ДЕБИЛ!");
                }
            }
        });

        Button but = new Button("Готово");
        group1.getChildren().add(tf);
        but.setMinHeight(30);
        but.setLayoutX(105);
        but.setLayoutY(20);
    text_bg.setLayoutX(0);
    text_bg.setLayoutY(70);

        but.setOnAction(ActionEvent ->{
            Group group2 = new Group();
            if (tf.getText().isEmpty()){
             text_bg.setText("Ошибка");
             text_bg.setFill(Color.RED);
            }else {
                text_bg.setText("");
                Text text = new Text("Привет," + tf.getText() + "!");
                text.setLayoutX(0);
                text.setLayoutY(20);
                group2.getChildren().add(text);
                Scene scene_sec = new Scene(group2, 250, 250);
                Stage stage_sec = new Stage();
                stage_sec.initOwner(primaryStage);
                stage_sec.setResizable(false);
                stage_sec.initModality(Modality.APPLICATION_MODAL);
                stage_sec.setScene(scene_sec);
                stage_sec.show();
            }
        });
        group1.getChildren().add(but);
        group1.getChildren().add(text_bg);
        primaryStage.setScene(scene_main);
        primaryStage.show();
    }
}