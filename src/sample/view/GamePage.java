package sample.view;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.controller.PlayerDatabase;
import sample.model.*;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.security.PrivateKey;
import java.util.*;

public class GamePage extends Stage{
    private AnchorPane gamePane;
    private AnchorPane bottomPane;
    private BorderPane borderPane;
    private Scene gameScene;
    private Grid choosenGrid;
    private Difficulty choosenDiff;
    private int Height,width;
    private List<Card> cards;
    private Boolean choosing=false;
    private Card choosenCard1;
    private Card choosenCard2;
    private List<Integer> foundCardsIds=new ArrayList<>();
    private int health;
    private int columns,rows;
    private KTimer kTimer;
    private TimerLabel label;
    private List<Heart> hearts;
    private Boolean gameOver=false;
    private Boolean win=false;
    private AnimationTimer animationTimer;


    public GamePage(Grid choosenGrid,Difficulty choosenDiff){
        this.choosenGrid=choosenGrid;
        this.choosenDiff=choosenDiff;
        switch (choosenGrid){
            case small:
                rows=4;
                columns=3;
                break;
            case average:
                rows=5;
                columns=4;
                break;
            case big:
                rows=5;
                columns=6;
        }
        health=choosenDiff.getDiff();
        this.Height=rows*180+100;
        this.width=columns*185;
        gamePane=new AnchorPane();
        bottomPane=new AnchorPane();
        bottomPane.setMaxWidth(rows*185);
        bottomPane.setMaxHeight(100);
        gamePane.setMaxHeight(rows*180);
        gamePane.setMaxWidth(rows*185);
        borderPane=new BorderPane();
        borderPane.setCenter(gamePane);
        label=new TimerLabel(this.width);

        bottomPane.getChildren().add(label);
        borderPane.setBottom(bottomPane);

        gameScene=new Scene(borderPane,this.width,this.Height);
        this.setScene(gameScene);
        creategameGrid();
        createHealth();
        Timeline timeline=new Timeline();
        setBackGround();
        animationTimer=new AnimationTimer() {
            @Override
            public void handle(long l) {
                updateLabel();
                initmouseListener();
                updateGamePane1();
                updateMouseListener();
                updateHealth();
                updateGameStatus();
            }
        };
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.millis(1000),actionEvent -> label.setText("5")),
                new KeyFrame(Duration.millis(2000),actionEvent -> label.setText("4")),
                            new KeyFrame(Duration.millis(3000),actionEvent -> label.setText("3")),
                            new KeyFrame(Duration.millis(4000),actionEvent -> label.setText("2")),
                            new KeyFrame(Duration.millis(5000),actionEvent -> label.setText("1"))
                            );
        timeline.play();
                    timeline.setOnFinished(actionEvent -> {
                        for (Card c : cards) {
                            c.hideCard();
                            kTimer=new KTimer();
                            kTimer.startTimer(-20);
                            animationTimer.start();
                        }});






    }
    public void setBackGround(){
        borderPane.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    private void createHealth(){
        hearts=new ArrayList<>();
        for (int i = 0; i <health ; i++) {
            Heart heart=new Heart(bottomPane,20+(i*65),10);
            hearts.add(heart);

        }
    }
    private void updateHealth(){

        if (hearts.size()>health){
            if (health!=0){
            bottomPane.getChildren().set(hearts.size(),hearts.get(hearts.size()-1).getEmpty());
            hearts=hearts.subList(0,hearts.size()-1);
        }}
    }
    private void updateGamePane1(){
        for (Card c:cards
             ) {
            if (c.getEmpty())c.setEmpty();
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
    private  void updateLabel(){

        label=new TimerLabel(this.width);
        label.setText(kTimer.getSspTime().getValue());
        bottomPane.getChildren().set(0,label);
        for (int i = 0; i <bottomPane.getChildren().size() ; i++) {
            if (bottomPane.getChildren().get(i) instanceof ImageView){

            }
        }
    }
    public void creategameGrid(){
        cards=new ArrayList<>();
        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j < columns; j++) {

                Card card = new Card(new ImageView(), gamePane, getRandomNumber((rows*columns)/2), j * 180, i * 180);

                gamePane.getChildren().add(card.getImageView());
                cards.add(card);
                card.getImageView().setId(Integer.toString(cards.size()-1));

            }
        }


    }
    private void updateMouseListener(){
        for (Card c:cards
             ) {
            if (!c.getHidden() || c.getEmpty()){
                c.getImageView().setOnMouseClicked(mouseEvent -> {});
            }
        }
    }
    private void initmouseListener(){
        for (Card c : cards) {
            c.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!c.getIsmoving()) {
                        if (choosing) {
                            choosenCard2 = c;
                            Timeline show = choosenCard2.getShowingTimeline();
                            if (choosenCard2.getHidden()) {
                                show.play();
                                show.setOnFinished(actionEvent -> {
                                    c.setHidden(false);
                                    gameLogic();
                                });
                            }
                        } else {
                            choosenCard1 = c;
                            choosenCard1.showCard();
                            choosing = true;
                        }
                    }
                }
            });
        }
    }
    public void gameLogic(){

            if (choosenCard1.getId()==choosenCard2.getId()){
                foundCardsIds.add(choosenCard1.getId());
                updateImageView(choosenCard1.getId());
                choosing=false;

            }else {
                health--;
                choosenCard1.hideCard();
                choosenCard2.hideCard();
                choosing=false;
            }
            if (health<=0){
                gameOver=true;
            }
            if (isWin()){
                win=true;
            }


    }
    private Scene createGameOverPane(){
        AnchorPane gameoverPane=new AnchorPane();
        Scene scene=new Scene(gameoverPane,width,Height);
        GameOverLabel gameOverLabel=new GameOverLabel("Game Over",width,Height,choosenGrid,Color.RED);
        gameoverPane.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY)));
        gameoverPane.getChildren().add(gameOverLabel);
        MenuButton returnButton=new MenuButton("Return");
        returnButton.setLayoutX(Math.floorDiv(width,3)-30);
        returnButton.setLayoutY(Height-300);
        gameoverPane.getChildren().add(returnButton);
        returnButton.setOnMouseClicked(mouseEvent -> {
            new ViewManager().getMainStage().show();
            this.close();

        });
        return scene;
    }
    private Scene createWinPane(){
        AnchorPane gameoverPane=new AnchorPane();
        Scene scene=new Scene(gameoverPane,width,Height);

        GameOverLabel gameOverLabel=new GameOverLabel("You Win",width,Height,choosenGrid,Color.BLACK);
        gameOverLabel.setLayoutX(gameOverLabel.getLayoutX()+50);
        gameoverPane.setBackground(new Background(new BackgroundFill(Color.PALEGREEN,CornerRadii.EMPTY,Insets.EMPTY)));

        InputLabel inputLabel=new InputLabel("Your name :");
        inputLabel.setLayoutX(Math.floorDiv(width,2)-230);
        inputLabel.setLayoutY(Height-500);

        WinInput input=new WinInput();
        input.setLayoutX(inputLabel.getLayoutX()+160);
        input.setLayoutY(Height-500);

        MenuButton submitButton=new MenuButton("Submit");
        submitButton.setLayoutX(Math.floorDiv(width,2)-126);
        submitButton.setLayoutY(Height-300);

        submitButton.setOnMouseClicked(mouseEvent -> {
            if (input.getText()!=null && !input.getText().trim().isEmpty()){
                PlayerDatabase database=new PlayerDatabase();
                switch (choosenDiff){
                    case EASY:
                        database.addRow(input.getText(),"easy",columns+"x"+rows,kTimer.getSspTime().getValue());
                        break;
                    case normal:
                        database.addRow(input.getText(),"normal",columns+"x"+rows,kTimer.getSspTime().getValue());
                        break;
                    case hard:
                        database.addRow(input.getText(),"hard",columns+"x"+rows,kTimer.getSspTime().getValue());
                        break;
                }
                new ViewManager().getMainStage().show();
                this.close();

            }

        });
        gameoverPane.getChildren().add(gameOverLabel);
        gameoverPane.getChildren().add(input);
        gameoverPane.getChildren().add(inputLabel);
        gameoverPane.getChildren().add(submitButton);
        return scene;
    }

    private void updateGameStatus(){
        if (gameOver){
            animationTimer.stop();
            this.setScene(createGameOverPane());

        }
        if (win){
            animationTimer.stop();
            this.setScene(createWinPane());

        }
    }
    private Boolean isWin(){
        boolean tmp=true;
        for (Card c:cards
             ) {
            if (!c.getEmpty()) {
                tmp = false;
                break;
            }
        }
        return tmp;
    }
    public AnchorPane getGamePane() {
        return gamePane;
    }

}
