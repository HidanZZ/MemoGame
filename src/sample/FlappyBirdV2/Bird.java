package sample.FlappyBirdV2;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bird {
    Image birdd ;
    ImageView birdImg ;
    ImageView getReady ;
    String birdType ;
    boolean start = false ;
    boolean wait = false ;
    boolean stop = false ;
    AnimationTimer timerBird ;
    Timeline t ;
    Scene scene ;
    Image birdP1;
    Image birdP2;
    int UP_GLOBALE = 15;

    public  Bird(String birdType , Scene scene){
        this.birdType = birdType ;
        this.scene = scene ;
        try {
           this.birdImg = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/" +birdType+"1.png")));
           this.birdd  = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/" +birdType+"0.png"));
           this.birdP1 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/" +birdType+"1.png"));
           this.birdP2 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/" +birdType+"2.png"));
           this.getReady = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/getReady.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.birdImg.setX(200);
        this.birdImg.setY(270);
        this.birdImg.setScaleX(1.5);
        this.birdImg.setScaleY(1.5);
        t = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(birdImg.imageProperty() , this.birdP2) /*, new KeyValue(birdImg.yProperty() , birdImg.getY() + 1 )*/),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(birdImg.imageProperty() , this.birdP1) /*, new KeyValue(birdImg.yProperty() , birdImg.getY() - 1 )*/),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(birdImg.imageProperty() , this.birdP2) /*, new KeyValue(birdImg.yProperty() , birdImg.getY() - 1 )*/),
                new KeyFrame(Duration.seconds(0.6), new KeyValue(birdImg.imageProperty() , this.birdP1) /*, new KeyValue(birdImg.yProperty() , birdImg.getY() + 1 )*/)

        );
        t.setAutoReverse(true);
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        getReady.setX(200);
        getReady.setY(125);
        getReady.setScaleX(2);
        getReady.setScaleY(2);
        birdJumping();
    }

    public void birdJumping(){
        try {
            birdd = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/" +birdType+"1.png"));
            birdP1 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/" +birdType+"1.png"));
            birdP2 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/" +birdType+"2.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Media sound = new Media(new File("src/sample/FlappyBirdV2/audio/wing.wav").toURI().toString());
        MediaPlayer wing = new MediaPlayer(sound);
        timerBird = new AnimationTimer() {
            int i = 0;
            int up = UP_GLOBALE;
            double down = 0;
            @Override
            public void handle(long l) {
                scene.setOnKeyPressed(keyEvent -> {
                    if (keyEvent.getCode() == KeyCode.SPACE  && !stop  ) {
                        wing.stop();
                        wing.play();
                        start = true;
                        wait = true;
                        up = UP_GLOBALE ;
                        birdImg.setRotate(-20);
                        getReady.setVisible(false);
                    }
                });
                scene.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY  && !stop  ) {
                        start = true;
                        wait = true;
                        up = UP_GLOBALE ;
                        birdImg.setRotate(-20);
                        getReady.setVisible(false);

                    }
                });
                    if (wait  && up > -1 ) {
                        birdImg.setY(birdImg.getY() - up/2  );
                        up--;
                        if ( up <  0) {
                            up = UP_GLOBALE;
                            down = 0 ;
                            wait = false;
                        }
                    }
                    if (start && !wait  ) {
                        if (birdImg.getY() < 560){
                            birdImg.setY(birdImg.getY() + down);
                            if (birdImg.getRotate()<90) {birdImg.setRotate(birdImg.getRotate()+3);}
                            down = down + 0.5 ;
                        }
                        else {
                            stop = true ;
                            birdImg.setImage(birdd);
                            t.stop();
                            timerBird.stop();
                        }
                    }
                }
        };
        timerBird.start();
    }

}
