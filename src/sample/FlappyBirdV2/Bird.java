package sample.FlappyBirdV2;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bird {
    Image birdd ;
    ImageView birdImg ;
    String birdType ;
    boolean start = false ;
    boolean wait = false ;
    boolean stop = false ;
    AnimationTimer timerBird ;
    AnimationTimer timerFlying ;
    Scene scene ;
    Image birdA1 ;
    Image birdA2 ;
    public  Bird(String birdType, Scene scene){
        this.birdType = birdType ;
        this.scene = scene ;
        createBird();
        birdJumping();
        birdFlying();
    }
    public  void createBird(){
        try {
            birdImg = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/"+birdType+"A1.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        birdImg.setX(412);
        birdImg.setY(270);
        birdImg.setFitWidth(50);
        birdImg.setFitHeight(50);
    }
    public void birdJumping(){
        try {
            birdd = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/"+birdType+"D.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        timerBird = new AnimationTimer() {
            int j = 0;
            int up = 12;
            double down = 0;
            @Override
            public void handle(long l) {
                if (j%2 == 0 ) {
                    scene.setOnKeyPressed(keyEvent -> {
                        if (keyEvent.getCode() == KeyCode.SPACE  && !stop  ) {
                            start = true;
                            wait = true;
                            up = 10 ;
                            birdImg.setRotate(-20);
                        }
                    });
                    if (wait  && up > -1 ) {
                        birdImg.setY(birdImg.getY() - up/1.5  );

                        up--;
                        if ( up <  0) {
                            up = 10;
                            down = 0 ;
                            wait = false;
                        }
                    }
                    if (start && !wait  ) {
                        if (birdImg.getY() < 520){
                            birdImg.setY(birdImg.getY() + down);
                            if (birdImg.getRotate()<90) {birdImg.setRotate(birdImg.getRotate()+3);}
                            down = down + 0.6  ;
                        }
                        else {
                            stop = true ;
                            timerFlying.stop();
                            birdImg.setImage(birdd);
                            timerBird.stop();
                        }
                    }
                }
                j++;
            }
        };
        timerBird.start();
    }
    public void birdFlying() {
        try {
            birdA1 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/"+birdType+"A1.png"));
            birdA2 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/"+birdType+"A2.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        timerFlying = new AnimationTimer() {
            int j = 0 ;
            int i = 0 ;
            boolean b = false ;
            @Override
            public void handle(long l) {
                if(j%6== 0 ){
                    if ( i<8 && i>2) {
                        if (i == 3) {
                            birdImg.setImage(birdA1);
                        }

                        birdImg.setY(birdImg.getY() + 1);
                    }
                    else {
                        if (i == 8) birdImg.setImage(birdA2);
                        birdImg.setY(birdImg.getY() - 1);
                    }
                    if (i == 9) i = -1 ;
                    i++ ;
                }
                j++;
            }
        };
        timerFlying.start();
    }
}
