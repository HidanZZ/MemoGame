package sample.FlapyBird;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Bird {

     int posX ;
     int posY ;
     public Circle bird;
     public Bird(){
        posX = 20 ;
        posY = 250 ;
        bird = new Circle();
        bird.setCenterX(posX);
        bird.setCenterY(posY);
        bird.setRadius(15);
        bird.setFill(Color.WHITE);

    }
    public void moving(){

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (bird.getCenterY()<585)
                bird.setCenterY(bird.getCenterY()+1);

            }
        };
        timer.start();
    }
    public void mooveUp(boolean b){

        AnimationTimer h = new AnimationTimer() {
            int up = 0 ;
            @Override
            public void handle(long l) {
                if(b) stop();
                if (up < 30 && bird.getCenterY()>15){
                    bird.setCenterY(bird.getCenterY() - 2);
                    up++;
            }

            }
        };
        h.start();
    }


}
