package sample.model;

import javafx.scene.control.Button;

public class CloseButton extends Button {
    private final String BUTTON_STYLE="-fx-background-color:transparent;-fx-background-image: url('/sample/assets/Button/close.png');";

    public CloseButton() {
        setStyle(BUTTON_STYLE);
        setPrefHeight(27);
        setPrefWidth(25);
    }
}
