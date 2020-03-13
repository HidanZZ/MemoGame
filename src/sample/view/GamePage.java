package sample.view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import sample.model.Card;
import sample.model.Difficulty;
import sample.model.Grid;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePage extends Stage{
    private AnchorPane gamePane;
    private Scene gameScene;
    private Grid choosenGrid;
    private Difficulty choosenDiff;
    private int Height,width;
    private List<Card> cards;
    private Boolean choosing=false;
    private Card choosenCard1;
    private Card choosenCard2;
    private List<Integer> foundCardsIds=new ArrayList<>();
    private boolean isGameStarted=false;
    private int health=5;
    private int score=0;

    public GamePage(Grid choosenGrid){
        this.choosenGrid=choosenGrid;
        this.Height=4*185;
        this.width=choosenGrid.getGrid()*185;
        gamePane=new AnchorPane();
        gameScene=new Scene(gamePane,this.width,this.Height);
        this.setScene(gameScene);
        creategameGrid();
        new AnimationTimer(){

            @Override
            public void handle(long l) {




                if (!isGameStarted){
                    executAfterSeconds();
                    isGameStarted=true;
                }
                initmouseListener();
                updateGamePane1();
                updateMouseListener();


            }

        }.start();


    }
    public void setBackGround(){

    }
    private void updateGamePane1(){
        for (Card c:cards
             ) {
            if (c.getEmpty())c.setEmpty();
        }
    }
    private void updateGamePane(){
        for (Integer i:foundCardsIds
             ) {
            for (Card c:cards
                 ) {
                if (c.getId()==i){c.setEmpty();}
            }
        }
    }
    private  void updateImageView(int id){
        for (Card c:cards
             ) {
            if (c.getId()==id){c.setEmpty();}
        }
        }
    private void executAfterSeconds(){

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        for (int i = 0; i <cards.size() ; i++) {
                            Card c=cards.get(i);
                            c.hideCard();
                        }
                    }
                },
                5000
        );

    }
    private int numberOfOccurences(int id){
        int i=0;
        for (Card c:cards
             ) {
            if (c.getId()==id){
                i++;
            }
        }
        return i;
    }
    private int getRandomNumber(int i){
        Random r=new Random();
        int randomNumber=r.nextInt(i)+1;
        if (numberOfOccurences(randomNumber)<2){
            return randomNumber;
        }else return getRandomNumber(i);
    }
    public void creategameGrid(){
        cards=new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j < choosenGrid.getGrid(); j++) {

                Card card = new Card(new ImageView(), gamePane, getRandomNumber(6), j * 180, i * 180);

                gamePane.getChildren().add(card.getImageView());
                cards.add(card);
                card.getImageView().setId(Integer.toString(cards.size()-1));

            }
        }

    }
    private void updateMouseListener(){
        for (Card c:cards
             ) {
            if (!c.getHidden()){
                c.getImageView().setOnMouseClicked(mouseEvent -> {});
            }
        }
    }
    private void initmouseListener(){
        for (int i = 0; i <cards.size() ; i++) {
            Card c=cards.get(i);

            c.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!c.getIsmoving())
                    {if (choosing){
                        choosenCard2=c;
                        choosenCard2.showCard();
                        choosing=false;
                    gameLogic();}
                    else {
                        choosenCard1=c;
                        choosenCard1.showCard();
                        choosing=true;
                    }
                    }
                }
            });
        }
    }
    public void gameLogic(){

            if (choosenCard1.getId()==choosenCard2.getId()){
                score++;
                foundCardsIds.add(choosenCard1.getId());
                updateImageView(choosenCard1.getId());

                System.out.println(choosenCard2.toString());
                System.out.println(choosenCard1.toString());
                choosenCard1=null;
                choosenCard2=null;

            }else {
                health--;
                choosenCard1.hideCard();
                choosenCard2.hideCard();
                System.out.println(choosenCard2.toString());
                System.out.println(choosenCard1.toString());
                choosenCard1=null;
                choosenCard2=null;
            }




    }
    public AnchorPane getGamePane() {
        return gamePane;
    }
}
