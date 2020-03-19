package sample.SpaceInvaders;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.List;

public class Invaders {

    static  final  double[] ran= {40,100,160,220,280,340,400,460,520,580};
    double x = ran[(int)(Math.random()*10)] ;
    double y = 40 ;
    int r=20 ;
    double speed ;
    boolean stop = false ;
    Circle circle ;
     AnimationTimer timer ;
    public  Invaders( double j , double speed ){
         //this.y = j ;
        this.speed = speed ;
        circle = new Circle(x , y - j , r, Color.GRAY);
    }
    public void move(Invaders invaders , List<Invaders> invadersList){
       timer = new AnimationTimer() {
           @Override
           public void handle(long l) {
               circle.setCenterY(circle.getCenterY() + speed);
               if(circle.getCenterY()+20 > 265)
               {
                   stop = true ;
                   stop();
               }
           }
       };
       timer.start();
    }
}
