package sample.SpaceInvaders;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Particle {
    Circle circle;
    double y ;
    AnimationTimer particles;
    public Particle(){
        circle = new Circle(Math.random()*600 , 15 , 1, Color.WHITE);
    }

    public Particle(double y ){
        this.y = y ;
        circle = new Circle(Math.random()*600 , y , 1, Color.WHITE);
    }
    public void particles(){
        particles= new AnimationTimer() {
            @Override
            public void handle(long l) {
                circle.setCenterY(circle.getCenterY()+0.1);
            }
        } ;
        particles.start();

    }

}
