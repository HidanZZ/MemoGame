package sample.SpaceInvaders;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvaders extends Stage {
    Pane root;
    int k = 400 ;
    double tic = 0 ;
    double speed = 10 ;
    Scene scene ;
    Ship ship ;
    int total = 1 ;
    Invaders invaders ;
    AnimationTimer tick;

    List<Invaders> invadersList;
    List<Particle> circleList ;
    public  SpaceInvaders(){
        root= new Pane();
        circleList = new ArrayList<>();
        invadersList = new ArrayList<>();
        ship = new Ship() ;
        root.getChildren().add(ship.circle);
        root.setStyle("-fx-background-color: black");
        scene = new Scene(root, 600, 320);
        this.setScene(scene);
        ship.control(scene ,root);
        Text score = new Text("Score : 0");
        score.setX(20);
        score.setY(20);
        score.setScaleX(1.5);
        score.setScaleY(1.5);
        score.setFill(Color.WHITE);
        root.getChildren().add(score);
        Text sban = new Text("Sponsor : Fuck Lhoussain");
        sban.setX(420);
        sban.setY(20);
        sban.setScaleX(1);
        sban.setScaleY(1);
        sban.setFill(Color.WHITE);
        root.getChildren().add(sban);
        for (int i = 0; i < 100; i++) {
            Particle particle = new Particle(Math.random()*300);
            particle.particles();
            circleList.add(particle);
            root.getChildren().add(particle.circle);
        }
        tick = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(tic%30 == 0 ){
                    Particle particle = new Particle();
                    particle.particles();
                    circleList.add(particle);
                    root.getChildren().add(particle.circle);
                }
                if(tic %k == 0 ) {
                    invaders = new Invaders(tic * 0.001 , speed*0.01  );
                    root.getChildren().add(invaders.circle);
                    invaders.move(invaders , invadersList);
                    invadersList.add(invaders);
                    //System.out.println(k);
                    k = k -1;
                    speed = speed +0.1;

                }
                if(k <= 0 && invadersList.size()==0 ) win();
                tic++;
                for (int i = invadersList.size()-1 ; i>=0 ; i--) {
                    if (invadersList.get(i).stop)lost();
                    for (int j = ship.rectangles.size()-1 ; j>=0 ; j--) {
                        if( ship.rectangles.get(j).getY() < invadersList.get(i).circle.getCenterY()+20 &&ship.rectangles.get(j).getY() > invadersList.get(i).circle.getCenterY()-20  && invadersList.get(i).circle.getCenterX()+20>ship.rectangles.get(j).getX() && invadersList.get(i).circle.getCenterX()-20<ship.rectangles.get(j).getX()+15 )
                        {
                            root.getChildren().remove(invadersList.get(i).circle);
                            root.getChildren().remove(ship.rectangles.get(j));
                            ship.rectangles.remove(j);
                            invadersList.remove(i);
                            score.setText("Score : " + total );
                            total++ ;
                            break;
                        }

                    }

                }
            }
        };
        tick.start();
    }

    private void win() {
        tick.stop();
        ship.stop = true;
        if(ship.timer != null) ship.timer.stop();
        if (ship.timer1!=null)ship.timer1.stop();
        Text textArea = new Text("YOU WON");
        textArea.setX(280);
        textArea.setY(150);
        textArea.setScaleX(3);
        textArea.setScaleY(3);textArea.setFill(Color.GREEN);
        root.getChildren().add(textArea);
    }

    private void lost() {
        stopInvaders();
        tick.stop();
        stopParticles();
        ship.stop = true;
        if(ship.timer != null) ship.timer.stop();
        if (ship.timer1!=null)ship.timer1.stop();

        Text textArea = new Text("YOU LOST");
        textArea.setX(280);
        textArea.setY(150);
        textArea.setScaleX(3);
        textArea.setScaleY(3);
        textArea.setFill(Color.RED);
        root.getChildren().add(textArea);

    }
    public void stopInvaders(){
        for (Invaders invaders:invadersList) {
            invaders.timer.stop();
        }
    }
    public void stopParticles(){
        for (Particle circle:circleList) {
            circle.particles.stop();
        }
    }
}
