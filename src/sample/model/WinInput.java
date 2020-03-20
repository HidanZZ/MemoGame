package sample.model;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WinInput extends TextField {
    private final String FONT_PATH="src/sample/view/ressources/acknowtt.ttf";
    private final String INPUT_STYLE="-fx-background-color:transparent;-fx-background-image: url('/sample/assets/Button/Input.png');";
    private Text text;

    public WinInput() {
        setInputFont();
        setStyle(INPUT_STYLE);
        setPrefHeight(69);
        setPrefWidth(301);



    }
    private void setInputFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),33));
        }catch (FileNotFoundException e){
            setFont(Font.font("Verdana",23));
        }

    }

}
