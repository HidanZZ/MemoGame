package sample.model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameOverLabel extends Label {
    private final String FONT_PATH="src/sample/view/ressources/acknowtt.ttf";
    public GameOverLabel(String text,int screenWidth,int screenHeight,Grid grid,Color color) {
        setText(text);
        setPrefHeight(Math.floorDiv(screenHeight,6));
        setPrefWidth(4*Math.floorDiv(screenWidth,6));
        setLayoutX(Math.floorDiv(screenWidth,6));
        setLayoutY(Math.floorDiv(screenHeight,6));
        setTextFill(color);

        switch (grid){
            case small:
                setLabelFont(75);
                break;
            case average:
                setLabelFont(100);
                break;
            case big:
                setLabelFont(145);
                break;
        }




    }
    private  void setLabelFont(int i){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),i));
        }catch (FileNotFoundException e){
            setFont(Font.font("Verdana",i));
        }

    }
}
