package sample.SpaceInvaders;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    List<Rectangle> rectangles ;
    AnimationTimer timer;
    AnimationTimer timer1;
    int x = 300;
    int y = 280;
    int r = 15;
    Circle circle;
    int dir= 0  ;
    boolean hold = false ;
    boolean hold1 = false ;
    boolean stop = false ;

    public Ship() {
        rectangles = new ArrayList<>();
        circle = new Circle(x, y, r, Color.PURPLE);
    }

    public void move(int dir) {
        this.circle.setCenterX(this.circle.getCenterX() + dir);
    }

    public void moving(int dir) {
if (dir<0  && !stop){
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if ( circle.getCenterX() > 15)
                    circle.setCenterX(circle.getCenterX() + dir);
            }
        };
        timer.start();
    }
        if (dir>0 && !stop) {
            timer1 = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    if (( circle.getCenterX() < 585))
                        circle.setCenterX(circle.getCenterX() + dir);
                }
            };
            timer1.start();

        }
    }

    public void control(Scene scene , Pane root){

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(hold != true){
                    if(keyEvent.getCode() == KeyCode.LEFT) {
                        dir = -2 ;
                        moving(dir);
                        hold=true ;
                    }}
                if (hold1 !=true){
                    if(keyEvent.getCode() == KeyCode.RIGHT) {
                        dir = 2 ;
                        moving(dir);
                        hold1=true ;
                    }

                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.RIGHT  ) {
                    if(timer1!= null) timer1.stop();
                    hold1=false;
                }
                if(keyEvent.getCode() == KeyCode.LEFT ) {
                    if(timer!= null) timer.stop();
                    hold=false;
                }
                if (keyEvent.getCode() == KeyCode.SPACE) {
                    Rectangle rectangle = new Rectangle(circle.getCenterX(), circle.getCenterY(), 5, 20);
                    rectangle.setFill(Color.RED);
                    rectangles.add(rectangle);
                    root.getChildren().add(rectangle);
                    AnimationTimer goRec = new AnimationTimer() {
                        @Override
                        public void handle(long l) {
                            rectangle.setY(rectangle.getY()-1);
                            if (rectangle.getY()== 40) rectangles.remove(rectangle);
                        }
                    };
                    goRec.start();
                }

            }
        });

    }
}
