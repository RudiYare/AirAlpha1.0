package YAROSLAV;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.IndexRange;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

// ObservableList <String> arr = FXCollections.observableArrayList("Java", "JavaScript", "C#", "Python");
//        arr.add(new String("sdsd"));
//        ComboBox <String> cb = new ComboBox<String>(arr);
//        group.getChildren().add(cb);
//        AutoComboBox.AutoCompleteComboBoxListener<String> avt = new AutoComboBox.AutoCompleteComboBoxListener<String>(cb);


public class AutoComboBox {

    public static class AutoCompleteComboBoxListener<T> implements EventHandler<KeyEvent> {

        private ComboBox comboBox;
        private StringBuilder sb;
        private ObservableList<T> data;
        private boolean moveCaretToPos = false;
        private int caretPos;

        public AutoCompleteComboBoxListener(final ComboBox comboBox) {
            this.comboBox = comboBox;
            sb = new StringBuilder();
            data = comboBox.getItems();
           this.comboBox.setEditable(true);
this.comboBox.setVisibleRowCount(3);
            this.comboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    comboBox.hide();
                }
            });
            this.comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);
        }

        @Override
        public void handle(KeyEvent event) {

            if (event.getCode() == KeyCode.UP) {
                caretPos = -1;
                moveCaret(comboBox.getEditor().getText().length());
                this.comboBox.setVisibleRowCount(3);
                comboBox.setVisibleRowCount(3);
                return;
            } else if (event.getCode() == KeyCode.DOWN) {
                if (!comboBox.isShowing()) {
                    comboBox.show();
                    this.comboBox.setVisibleRowCount(3);
                    comboBox.setVisibleRowCount(3);
                }
                caretPos = -1;
                moveCaret(comboBox.getEditor().getText().length());
                this.comboBox.setVisibleRowCount(3);
                comboBox.setVisibleRowCount(3);
                return;
            } else if (event.getCode() == KeyCode.BACK_SPACE) {
                moveCaretToPos = true;
                caretPos = comboBox.getEditor().getCaretPosition();
                comboBox.setVisibleRowCount(3);
                this.comboBox.setVisibleRowCount(3);
            } else if (event.getCode() == KeyCode.DELETE) {
                moveCaretToPos = true;
                caretPos = comboBox.getEditor().getCaretPosition();
                comboBox.setVisibleRowCount(3);
                this.comboBox.setVisibleRowCount(3);
            }

            if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                    || event.isControlDown() || event.getCode() == KeyCode.HOME
                    || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
                comboBox.setVisibleRowCount(3);
                this.comboBox.setVisibleRowCount(3);
                return;
            }

            ObservableList list = FXCollections.observableArrayList();
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).toString().toLowerCase().startsWith(
                        AutoCompleteComboBoxListener.this.comboBox
                                .getEditor().getText().toLowerCase())) {
                    list.add(data.get(i));
                    comboBox.setVisibleRowCount(3);
                    this.comboBox.setVisibleRowCount(3);
                }
            }
            String t = comboBox.getEditor().getText();

            comboBox.setItems(list);
            comboBox.setVisibleRowCount(3);
            this.comboBox.setVisibleRowCount(3);
            comboBox.getEditor().setText(t);
            if (!moveCaretToPos) {
                caretPos = -1;
                comboBox.setVisibleRowCount(3);
                this.comboBox.setVisibleRowCount(3);
            }
            moveCaret(t.length());
            if (!list.isEmpty()) {
                comboBox.show();
                comboBox.setVisibleRowCount(3);
                this.comboBox.setVisibleRowCount(3);
            }
        }

        private void moveCaret(int textLength) {
            comboBox.setVisibleRowCount(3);
            this.comboBox.setVisibleRowCount(3);
            if (caretPos == -1) {
                comboBox.getEditor().positionCaret(textLength);
            } else {
                comboBox.getEditor().positionCaret(caretPos);
            }
            moveCaretToPos = false;
        }

    }
}