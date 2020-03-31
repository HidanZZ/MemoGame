package sample.FlappyBirdV2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class FlippyBirdLauncher {
    public  FlippyBirdLauncher(Stage primaryStage){

        AnimationTimer timerForStart ;
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
                            flappyBirdy = new FlappyBirdy(difficulty[0].birdType , difficulty[0].backgroundType , difficulty[0].namePlayer);
                            primaryStage.setScene(flappyBirdy.scene);
                            primaryStage.show();
                        }
                        else  if(flappyBirdy.restart) {
                            primaryStage.close();
                            flappyBirdy = new FlappyBirdy(difficulty[0].birdType , difficulty[0].backgroundType , difficulty[0].namePlayer);
                            primaryStage.setScene(flappyBirdy.scene);
                            primaryStage.show();
                        }
                        else if (flappyBirdy.isBack){
                            first = true ;
                            difficulty[0].timerForBack.start();
                            difficulty[0].start = false ;
                            primaryStage.close();
                            primaryStage.setScene(difficulty[0].scene);
                            primaryStage.show();
                        }
                    }
                }
            };
            timerForStart.start();
        }
    }
