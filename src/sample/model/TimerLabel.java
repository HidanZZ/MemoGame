package sample.model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TimerLabel extends Label {
    private final String FONT_PATH="src/sample/model/buttonStyle/acknowtt.ttf";

    public TimerLabel(int width) {
        setLabelFont();
        setLayoutX(width-150);
        setLayoutY(0);
        setPadding(new Insets(10,10,40,10));

    }
    private  void setLabelFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),55));
        }catch (FileNotFoundException e){
            setFont(Font.font("Verdana",23));
        }

    }

}
