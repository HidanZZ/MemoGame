package sample.model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SubSceneLabel extends Label {
    private final String FONT_PATH="src/sample/view/ressources/acknowtt.ttf";

    public SubSceneLabel(String text) {
        setText(text);
        setPrefHeight(50);
        prefWidth(300);
        setPadding(new Insets(10,30,10,30));
        setLabelFont();
        setLayoutX(80);
        setLayoutY(50);

    }
    private  void setLabelFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),33));
        }catch (FileNotFoundException e){
            setFont(Font.font("Verdana",23));
        }

    }
}