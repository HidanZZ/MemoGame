package sample.model;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InputLabel extends Label {

    private final String FONT_PATH= "sample/view/ressources/acknowtt.ttf";
    public InputLabel(String text) {
        super(text);
        setLabelFont();
        setPrefWidth(150);
        setPrefHeight(69);
    }
    private  void setLabelFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),25));
        }catch (FileNotFoundException e){
            setFont(Font.font("Verdana",22));
        }

    }
}
