package YAROSLAV;



import javafx.scene.control.TextField;

public class PriceTextField extends TextField {

    boolean space;
    @Override
    public void replaceText(int i, int i1, String s) {
        String old = getText();
        if ( s.matches("[0-9]*")){
            super.replaceText(i,i1,s);

            space= false;
        }else
        if (s.matches(".")&&(space == false)&&(i != 0)){
            super.replaceText(i,i1,s);
            space = true;

        }

    }
}
