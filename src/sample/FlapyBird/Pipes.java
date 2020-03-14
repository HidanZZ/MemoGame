package sample.FlapyBird;


import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pipes {

    double recX ;
    double rec1Y ;
    double rec2Y ;
    double recHeight;
    double recWidth;
    Rectangle rectangle1 ;
    Rectangle rectangle2;
    public Pipes(int wait){
        this.recX = 400 ;
        this.rec1Y = 0 ;
        this.recHeight = Math.random()*520  ;
        this.rec2Y = this.recHeight + 80  ;
        this.recWidth = 40;
        rectangle1 = new Rectangle(this.recX + wait,this.rec1Y,this.recWidth, this.recHeight);
        rectangle1.setFill(Color.WHITE);
        rectangle2 = new Rectangle(this.recX + wait,this.rec2Y,this.recWidth, 600 - this.rec2Y);
        rectangle2.setFill(Color.WHITE);
    }

}
