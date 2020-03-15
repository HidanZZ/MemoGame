package sample.controller;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class CardAnimation{
    private Timeline timeline;
    private AnchorPane a;
    private int cardId;
    private final String IMAGE_PATH="file:src/sample/assets/cards/CARD";

    public CardAnimation(AnchorPane anchorPane,int id) {
        this.timeline = new Timeline();
        this.a=anchorPane;
        this.cardId=id;
        for (int i = 1; i <11 ; i++) {
            int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i * 100), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    a.getChildren().setAll(new ImageView(IMAGE_PATH+Integer.toString(cardId)+"/"+Integer.toString(finalI)+".png"));

                }
            }));
        }
    }
    public void play(){
        timeline.play();
    }
}
