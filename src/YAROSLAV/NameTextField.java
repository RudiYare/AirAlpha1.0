package YAROSLAV;

import javafx.scene.control.TextField;

public class NameTextField extends TextField {
    @Override
    public void replaceText(int i, int i1, String s) {
        String old = getText();
        if ( s.matches("[a-z]*")|| s.matches("[A-Z]*")|| s.matches("[а-я]*")|| s.matches("[А-Я]*")){
            super.replaceText(i,i1,s);

        }
    }
}
