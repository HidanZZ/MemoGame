package sample.FlappyBirdV2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pipe {
    ImageView pipeUp;
    ImageView pipeDown;
    ImageView pipeCUp;
    ImageView pipeCDown;

    public Pipe(Pane root , int x ) {
        makePipe("src/sample/FlappyBirdV2/imgs/pipe.png", "src/sample/FlappyBirdV2/imgs/pipeC.png", x );
        root.getChildren().add(this.pipeDown);
        root.getChildren().add(this.pipeUp);
        root.getChildren().add(this.pipeCUp);
        root.getChildren().add(this.pipeCDown);
    }

    public void makePipe(String pipeImg , String pipeCImg , int posX){
        double randHeight = Math.random()*426;
        try {
            pipeUp = new ImageView(new Image(new FileInputStream(pipeImg)));
            pipeDown = new ImageView(new Image(new FileInputStream(pipeImg)));
            pipeCUp = new ImageView(new Image(new FileInputStream(pipeCImg)));
            pipeCDown = new ImageView(new Image(new FileInputStream(pipeCImg)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pipeUp.setFitWidth(60);
        pipeDown.setFitWidth(60);
        pipeCUp.setFitWidth(62);
        pipeCDown.setFitWidth(62);
        pipeCUp.setFitHeight(10);
        pipeCDown.setFitHeight(10);

        pipeUp.setFitHeight(randHeight);
        pipeDown.setFitHeight(576 - (100+randHeight));

        pipeUp.setY(0);
        pipeCUp.setY(randHeight);
        pipeDown.setY(150+randHeight);
        pipeCDown.setY(140+randHeight);

        pipeUp.setX(posX);
        pipeDown.setX(posX);
        pipeCUp.setX(posX-1);
        pipeCDown.setX(posX-1);

    }


}
