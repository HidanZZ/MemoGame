package sample.FlappyBirdV2;

import javafx.animation.AnimationTimer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class FlippyBirdLauncher {
    AnimationTimer timerForStart ;
    public MediaPlayer menuMusic ;
    public  FlippyBirdLauncher(Stage primaryStage){
        menuMusic = new MediaPlayer(new Media(new File("src/sample/FlappyBirdV2/audio/mainMusic.wav").toURI().toString()));
        menuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        menuMusic.play();
            final Difficulty[] difficulty = {new Difficulty()};
            primaryStage.setTitle("Flappy Bird");
            primaryStage.setScene(difficulty[0].scene);
            primaryStage.show();
            timerForStart = new AnimationTimer() {
                FlappyBirdy flappyBirdy ;
                boolean first = true ;
                @Override
                public void handle(long l) {
                    if (difficulty[0].start){
                        if (first){
                            first = false ;
                            primaryStage.close();
                            menuMusic.stop();
                            flappyBirdy = new FlappyBirdy(difficulty[0].birdType , difficulty[0].backgroundType , difficulty[0].namePlayer);
                            primaryStage.setScene(flappyBirdy.scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();

                        }
                        else  if(flappyBirdy.restart) {
                            primaryStage.close();
                            flappyBirdy.menuMusic.stop();
                            flappyBirdy = new FlappyBirdy(difficulty[0].birdType , difficulty[0].backgroundType , difficulty[0].namePlayer);
                            primaryStage.setScene(flappyBirdy.scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();
                        }
                        else if (flappyBirdy.isBack){
                            first = true ;
                            difficulty[0].timerForBack.start();
                            difficulty[0].start = false ;
                            flappyBirdy.menuMusic.stop();
                            primaryStage.close();
                            menuMusic.play();
                            primaryStage.setScene(difficulty[0].scene);
                            primaryStage.setResizable(false);
                            primaryStage.show();
                        }
                    }
                }
            };
            timerForStart.start();
        }
    }
