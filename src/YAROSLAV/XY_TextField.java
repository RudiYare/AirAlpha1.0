package YAROSLAV;

import javafx.scene.control.TextField;

public class XY_TextField extends TextField {
    boolean space;

    @Override
    public void replaceText(int i, int i1, String s) {
        String old = getText();
        if ( s.matches("[0-9]*")){
            super.replaceText(i,i1,s);

            space= false;
        }else
        if (s.equals(".")&&(space == false)&&(i != 0)){
            super.replaceText(i,i1,s);
            space = true;

        }else
        if (s.equals("-")&&(space == false)&&(i == 0)){
            super.replaceText(i,i1,s);
            space = true;

    }
}}
