package sample.model;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MenuButton extends Button {
    private final String FONT_PATH="src/sample/view/ressources/acknowtt.ttf";
    private final String BUTTON_STYLE="-fx-background-color:transparent;-fx-background-image: url('/sample/assets/Button/button_small.png');";
    public MenuButton(String text) {
        setText(text);
        setButtonFont();

        setStyle(BUTTON_STYLE);
        setPrefHeight(60);
        setPrefWidth(252);

    }
    public MenuButton(String text,int height,int width){
        setText(text);
        setButtonFont();

        setStyle(BUTTON_STYLE);
        setPrefHeight(height);
        setPrefWidth(width);
    }
    private void setButtonFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),30));
        }catch (FileNotFoundException e){
            setFont(Font.font("Verdana",23));
        }

    }
    public void setButtonFont(int i){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),i));
        }catch (FileNotFoundException e){
            setFont(Font.font("Verdana",i));
        }

    }


}