package sample.FlappyBirdV2;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static javafx.scene.input.KeyCode.*;

public class FlappyBirdy {
    Scene scene ;
    Pane root;
    List<Pipe> pipeList ;
    Bird bird ;
    int WIDTH = 1024  ;
    int HEIGHT = 576 ;
    Font f ;
    String birdType ;
    String backgroundType ;
    ImageView background ;
    ImageView backgroundSupport ;
    ImageView sun ;
    ImageView scoreBoard ;
    ImageView coinB ;
    ImageView coinS ;
    ImageView gameOver ;
    ImageView restartIcon ;
    ImageView menuIcon ;
    AnimationTimer timerForBack ;
    AnimationTimer timerLose ;
    Text score;
    Image birdD ;
    String name ;
    DataBaseForFB db ;
    MediaPlayer menuMusic ;
    boolean restart = false ;
    boolean isBack = false ;
    boolean insert = true ;
    public FlappyBirdy (String birdType , String backgroundType ,String name ) {
        this.birdType = birdType;
        this.name = name ;
        this.backgroundType = backgroundType ;
        try {
            f = Font.loadFont(new FileInputStream(new File("src/sample/FlappyBirdV2/fonts/Orbitron.ttf")), 22);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        root = new Pane();
        scene = new Scene(root,500,576);
        pipeList = new ArrayList<>();
        bird = new Bird(birdType , scene );
        scene.setOnKeyReleased(keyEvent -> {if (keyEvent.getCode() == R ) restart = true;
        if(keyEvent.getCode() == M ) isBack = true;} );
        //setting background image
        setBackground("src/sample/FlappyBirdV2/imgs/background_" +backgroundType+".png" , "src/sample/FlappyBirdV2/imgs/sun.png");
        // pipe existence
        addPipes(1);

        root.getChildren().add(bird.birdImg);
        root.getChildren().add(bird.getReady);

        // add score
        addScore();

        menuMusic = new MediaPlayer(new Media(new File("src/sample/FlappyBirdV2/audio/mainMusic.wav").toURI().toString()));
        menuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        menuMusic.play();
    }
    public void setBackground(String img , String imgC){
        try {
            background = new ImageView(new Image(new FileInputStream(img)));
            backgroundSupport = new ImageView(new Image(new FileInputStream(img)));
            sun = new ImageView(new Image(new FileInputStream(imgC)));
            birdD = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/" +birdType+"1.png")) ;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        backgroundSupport.setX(1024);

        sun.setFitWidth(1024);
        MediaPlayer point = new MediaPlayer(new Media(new File("src/sample/FlappyBirdV2/audio/point.wav").toURI().toString()));
        MediaPlayer hit = new MediaPlayer(new Media(new File("src/sample/FlappyBirdV2/audio/hit.wav").toURI().toString()));
        timerForBack = new AnimationTimer() {
            int s = 1 ;
            int b = 2 ;
            @Override
            public void handle(long l) {
                // lose by falling

                    background.setX(background.getX()-b);
                    backgroundSupport.setX(backgroundSupport.getX()-b);
                    if (backgroundSupport.getX()<=-1024) backgroundSupport.setX(1024);
                    if (background.getX()<=-1024) background.setX(1024);
                    if(bird.start) for (int i = 6; i > 0; i--) {
                        if(pipeList.get(i-1).pipeCDown.getX()  < -62 ) {
                            pipeList.set(i-1 ,  new Pipe(root,6*350));
                        }
                        else {
                            pipeList.get(i-1).pipeUp.setX(pipeList.get(i-1).pipeUp.getX() - b);
                            pipeList.get(i-1).pipeDown.setX(pipeList.get(i-1).pipeDown.getX() - b);
                            pipeList.get(i-1).pipeCUp.setX(pipeList.get(i-1).pipeCUp.getX() - b);
                            pipeList.get(i-1).pipeCDown.setX(pipeList.get(i-1).pipeCDown.getX() - b);
                        }
                        if (lose(pipeList.get(i-1)) == -1  || bird.stop) {
                           if(insert){
                               db = new DataBaseForFB(name,s-1,true);
                               insert = false ;
                            }

                            if (bird.birdImg.getY() < 520 )downLose(bird.birdImg);
                            hit.play();
                            timerForBack.stop();
                            bird.timerBird.stop();
                            bird.t.stop();
                            bird.stop = true ;
                            scoreDisplay(("00").format("%03d",s-1) , ("00").format("%03d",db.highScore));
                            if (s-1 < db.highScore ) coinB.setVisible(true);
                            else coinS.setVisible(true);
                        }
                        else if (lose(pipeList.get(i-1)) == 2 ) {
                            point.stop();
                            point.play();
                            root.getChildren().remove(score);
                            addScore();
                            score.setText("" + s);
                            s++ ;
                        }
                    }
                }

        };
        timerForBack.start();
        root.getChildren().add(background);
        root.getChildren().add(backgroundSupport);
        if (backgroundType.equals("day")) root.getChildren().add(sun);
    }
    public void addPipes(int min) {
        for (int i = min; i <6+min ; i++) {
            Pipe pipe ;
            if (i == min )  pipe = new Pipe(root , 1024);
            else {   pipe = new Pipe(root,(i-1)*350 + 1024);}
            pipeList.add(pipe);
        }

    }
    public int lose(Pipe pipe) {
        if(bird.birdImg.getX() + bird.birdImg.getImage().getWidth() >= pipe.pipeCDown.getX() &&  bird.birdImg.getX() <= pipe.pipeCDown.getX()+62 ){

            if (bird.birdImg.getY() < pipe.pipeCUp.getY()  || bird.birdImg.getY()+bird.birdImg.getImage().getHeight()>pipe.pipeCDown.getY())
            {
                return -1 ;
            }
        }
        else if (bird.birdImg.getX() == pipe.pipeCDown.getX()+63
                )return 2 ;

        return 1 ;
    }
    public void addScore(){
        score = new Text("0");
        score.setFont(f);
        score.setStyle("-fx-font-weight: bold");
        score.setScaleX(2);
        score.setScaleY(2);
        score.setX(250);
        score.setY(150);
        score.setFill(Color.WHITE);
        root.getChildren().add(root.getChildren().size()-1 , score);
    }
    public  void snippet(){
        Rectangle snape = new Rectangle(0,0,1024,576);
        root.getChildren().add(snape);
        snape.setOpacity(0);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.3), snape);
        fadeTransition.setFromValue(0.2);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
        MediaPlayer die = new MediaPlayer(new Media(new File("src/sample/FlappyBirdV2/audio/die.wav").toURI().toString()));
        die.play();

    }
    public void downLose(ImageView birdImg) {
        final double[] down = {0};
        timerLose = new AnimationTimer() {
            @Override
            public void handle(long l) {
                birdImg.setY(birdImg.getY() + down[0]);
                if (birdImg.getRotate()<90) {birdImg.setRotate(birdImg.getRotate()+4);}
                down[0] =  (down[0] + 0.1);
                if (birdImg.getY()>520 ) timerLose.stop();
            }
        };
        timerLose.start();
    }

    public void scoreDisplay(String scoreFinal , String best ) {
        Group group = new Group();
        Text replay = new Text("Press R to replay");
        Text back = new Text("Press M to get back to main menu");
        Text scoring = new Text(scoreFinal);
        Text bestS = new Text(best);
        try {
            scoreBoard = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/scoreBoard.png")));
            coinB = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/coinB.png")));
            coinS = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/coinS.png")));
            gameOver = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/gameOver.png")));
            menuIcon = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/menu.png")));
            restartIcon = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/restart.png")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scoring.setX(300);
        scoring.setY(224);
        scoring.setFont(f);
        scoring.setScaleX(1.3);
        scoring.setScaleY(1.3);
        scoring.setFill(Color.WHITE);

        bestS.setX(300);
        bestS.setY(273);
        bestS.setFont(f);
        bestS.setScaleX(1.3);
        bestS.setScaleY(1.3);
        bestS.setFill(Color.WHITE);

        coinB.setX(180);
        coinB.setY(222);
        coinB.setVisible(false);
        coinB.setScaleX(2);
        coinB.setScaleY(2);

        coinS.setX(180);
        coinS.setY(222);
        coinS.setVisible(false);
        coinS.setScaleX(2);
        coinS.setScaleY(2);

        replay.setX(150);
        replay.setY(190);
        replay.setFont(f);
        replay.setScaleX(1.3);
        replay.setScaleY(1.3);
        replay.setFill(Color.BROWN);

        back.setX(130);
        back.setY(230);
        back.setFont(f);
        back.setScaleX(1.3);
        back.setScaleY(1.3);
        back.setFill(Color.BROWN);

        scoreBoard.setX(145);
        scoreBoard.setY(170);

        menuIcon.setX(115);
        menuIcon.setY(320);
        menuIcon.setOnMouseClicked(event -> {
            try {
                Robot m = new Robot();
                m.keyPress(KeyEvent.VK_M);
                m.keyRelease(KeyEvent.VK_M);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        });
        restartIcon.setX(265);
        restartIcon.setY(320);
        restartIcon.setOnMouseClicked(event -> {
            try {
                Robot r = new Robot();
                r.keyPress(KeyEvent.VK_R);
                r.keyRelease(KeyEvent.VK_R);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        });

        gameOver.setX(210);
        gameOver.setY(125);
        gameOver.setScaleX(2);
        gameOver.setScaleY(2);

        score.setVisible(false);
        group.getChildren().addAll(scoreBoard , scoring , bestS , coinB , coinS , menuIcon , restartIcon  , gameOver);
        snippet();
        group.setOpacity(0);
        root.getChildren().add(group);

        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(1), group );
        fadeTransition1.setFromValue(0.0);
        fadeTransition1.setToValue(1);
        fadeTransition1.play();

        menuMusic.stop();


    }

}
