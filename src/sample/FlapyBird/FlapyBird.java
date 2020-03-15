package sample.FlapyBird;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FlapyBird extends Stage {
    Pipes  pipe;
    Scene scene ;
    boolean  stop = false ;
    Pane root;
    Bird bird;

    public FlapyBird() {
        root= new Pane();
        ArrayList<Pipes> list = new ArrayList<>();
        root.setStyle("-fx-background-color: black");
        bird= new Bird();
        root.getChildren().add(bird.bird);
        for (int i = 1; i <100 ; i++) {
            pipe = new Pipes(i * 500);
            root.getChildren().add(pipe.rectangle1);
            root.getChildren().add(pipe.rectangle2);
            list.add(pipe);
            moving(pipe);
        }
        scene = new Scene(root, 400, 600);
        this.setScene(scene);
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE)
                    bird.mooveUp(stop);

            }
        });
        bird.moving();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Pipes pipes:list) {
                    if(pipes.rectangle2.getX() == 35  &&(bird.bird.getCenterY()< pipes.recHeight || bird.bird.getCenterY()>pipes.rectangle2.getY())) {
                        gameOver();
                        this.stop();
                    }
                }
                if((list.get(list.size()-2).rectangle2.getX() == -40  &&bird.bird.getCenterY()> list.get(list.size()-2).recHeight && bird.bird.getCenterY()<list.get(list.size()-2).rectangle2.getY())) {
                    gameWin();
                    this.stop();
                }
            }
        };
        timer.start();
    }

    private void gameWin() {

        stop=true ;
        Text text = new Text("YOU WON");
        text.setVisible(true);
        text.setFill(Color.GREEN);
        text.setX(200);
        text.setY(300);
        bird.bird.setFill(Color.GREEN);
        root.getChildren().add(text);
    }

    public void moving(Pipes pipe){

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(stop) stop();
                pipe.rectangle1.setX(pipe.rectangle1.getX()-1);
                pipe.rectangle2.setX(pipe.rectangle2.getX()-1);

            }
        };
        timer.start();
    }
    private void gameOver() {
        stop=true ;
        Text text = new Text("YOU LOST");
        text.setVisible(true);
        text.setFill(Color.RED);
        text.setX(200);
        text.setY(300);
        bird.bird.setFill(Color.RED);
        root.getChildren().add(text);
    }

}
