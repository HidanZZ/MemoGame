package sample.FlappyBirdV2;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    AnimationTimer timerForBack ;
    AnimationTimer timer ;
    AnimationTimer timerLose ;
    Text score;
    Image birdD ;
    String name ;
    DataBaseForFB db ;
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
        scene = new Scene(root,1024,576);
        pipeList = new ArrayList<>();
        bird = new Bird(birdType , scene );
        scene.setOnKeyReleased(keyEvent -> {if (keyEvent.getCode() == KeyCode.R ) restart = true;
        if(keyEvent.getCode() == KeyCode.M ) isBack = true;} );
        //setting background image
        setBackground("src/sample/FlappyBirdV2/imgs/background_"+backgroundType+".png" , "src/sample/FlappyBirdV2/imgs/sun.png");
        // pipe existence
        addPipes(1);
        root.getChildren().add(bird.birdImg);
        // add score
        addScore();
    }
    public void setBackground(String img , String imgC){
        try {
            background = new ImageView(new Image(new FileInputStream(img)));
            backgroundSupport = new ImageView(new Image(new FileInputStream(img)));
            sun = new ImageView(new Image(new FileInputStream(imgC)));
            birdD = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/"+birdType+"D.png")) ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        backgroundSupport.setX(1024);
        sun.setFitWidth(1024);

        timerForBack = new AnimationTimer() {
            double j = 0 ;
            int s = 1 ;
            @Override
            public void handle(long l) {
                // lose by falling
                if(j%2 == 0 ){
                    background.setX(background.getX()-1);
                    backgroundSupport.setX(backgroundSupport.getX()-1);
                    if (backgroundSupport.getX()<=-1024) backgroundSupport.setX(1024);
                    if (background.getX()<=-1024) background.setX(1024);
                    if(bird.start) for (int i = 6; i > 0; i--) {
                        if(pipeList.get(i-1).pipeCDown.getX()  < -62 ) {
                            pipeList.set(i-1 ,  new Pipe(root,6*350));
                        }
                        else {
                            pipeList.get(i-1).pipeUp.setX(pipeList.get(i-1).pipeUp.getX() - 1);
                            pipeList.get(i-1).pipeDown.setX(pipeList.get(i-1).pipeDown.getX() - 1);
                            pipeList.get(i-1).pipeCUp.setX(pipeList.get(i-1).pipeCUp.getX() - 1);
                            pipeList.get(i-1).pipeCDown.setX(pipeList.get(i-1).pipeCDown.getX() - 1);
                        }
                        if (lose(pipeList.get(i-1)) == -1  || bird.stop) {
                            snippet();
                           if(insert){
                               db = new DataBaseForFB(name,s-1,true);
                                insert = false ; 
                            }

                            if (bird.birdImg.getY() < 500 )downLose(bird.birdImg);
                            timerForBack.stop();
                            bird.timerBird.stop();
                            bird.timerFlying.stop();
                            bird.stop = true ;
                            scoreDisplay(("00").format("%03d",s-1) , ("00").format("%03d",db.highScore));
                        }
                        else if (lose(pipeList.get(i-1)) == 2 ) {
                            root.getChildren().remove(score);
                            addScore();
                            score.setText("" + s);
                            s++ ;
                        }
                    }
                }
                j++ ;
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
        if(bird.birdImg.getX() == pipe.pipeCDown.getX()){
            if (bird.birdImg.getY() < pipe.pipeCUp.getY()+20) return  -1 ;
            else if (bird.birdImg.getY()+50>pipe.pipeCDown.getY()) return -1 ;
        }
        else  if (pipe.pipeCDown.getX()< bird.birdImg.getX()+50 && bird.birdImg.getX()<pipe.pipeCDown.getX()+62){
            if (bird.birdImg.getY()<pipe.pipeCUp.getY() || bird.birdImg.getY()+50>pipe.pipeCDown.getY())
                return -1 ;
        }
        else if ( bird.birdImg.getX()  ==  pipe.pipeCDown.getX()+63){
            return  2 ;
        }
        return 1 ;
    }
    public void addScore(){
        score = new Text("0");
        score.setFont(f);
        score.setStyle("-fx-font-weight: bold");
        score.setScaleX(3);
        score.setScaleY(3);
        score.setX(500);
        score.setY(250);
        score.setFill(Color.WHITE);
        root.getChildren().add(root.getChildren().size()-1 , score);
    }
    public  void snippet(){
        Rectangle snape = new Rectangle(0,0,1024,576);
        root.getChildren().add(snape);
        snape.setVisible(true);
        timer = new AnimationTimer() {
            int j = 1 ;
            @Override
            public void handle(long l) {
                if (j == 0)snape.setFill(Color.BLACK);
                if (j == 1)snape.setOpacity(0.2);
                else if ( j== 3 )snape.setOpacity(0.4);
                else  if (j == 5) snape.setOpacity(0.6);
                else if (j==7)snape.setOpacity(0.4);
                else if (j==9 )snape.setOpacity(0.2);
                else  if (j == 11 ){
                    bird.birdImg.setImage(birdD);
                    snape.setVisible(false);
                    timer.stop();
                }
                j++ ;
            }
        };
        timer.start();
    }
    public void downLose(ImageView birdImg) {
        final double[] down = {0};
        timerLose = new AnimationTimer() {
            @Override
            public void handle(long l) {
                birdImg.setY(birdImg.getY() + down[0]);
                if (birdImg.getRotate()<90) {birdImg.setRotate(birdImg.getRotate()+4);}
                down[0] =  (down[0] + 0.1);
                if (birdImg.getY()>540 ) timerLose.stop();
            }
        };
        timerLose.start();
    }

    public void scoreDisplay(String score , String best ) {
        Group group = new Group();
        Text replay = new Text("Press R to replay");
        Text back = new Text("Press M to get back to main menu");
        Text scoring = new Text(score);
        Text bestS = new Text(best);
        try {
            scoreBoard = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/scoreBoard.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scoring.setX(640);
        scoring.setY(75);
        scoring.setFont(f);
        scoring.setScaleX(1.3);
        scoring.setScaleY(1.3);
        scoring.setFill(Color.WHITE);

        bestS.setX(640);
        bestS.setY(145);
        bestS.setFont(f);
        bestS.setScaleX(1.3);
        bestS.setScaleY(1.3);
        bestS.setFill(Color.WHITE);

        replay.setX(450);
        replay.setY(190);
        replay.setFont(f);
        replay.setScaleX(1.3);
        replay.setScaleY(1.3);
        replay.setFill(Color.BROWN);

        back.setX(330);
        back.setY(230);
        back.setFont(f);
        back.setScaleX(1.3);
        back.setScaleY(1.3);
        back.setFill(Color.BROWN);

        scoreBoard.setX(401);
        scoreBoard.setFitHeight(164);
        scoreBoard.setFitWidth(326);
        TranslateTransition animation = new TranslateTransition(
                Duration.seconds(1), group
        );
        animation.setFromY(567);
        animation.setToY(170);
        animation.play();
        group.getChildren().add(scoreBoard);
        group.getChildren().add(scoring);
        group.getChildren().add(bestS);
        group.getChildren().add(replay);
        group.getChildren().add(back);

        root.getChildren().add(group);




    }

}
