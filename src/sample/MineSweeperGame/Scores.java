package sample.MineSweeperGame;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Scores {
    Rectangle scoreBar ;
    Text timing ;
    Text nbrFlags ;
    AnimationTimer timingReal ;
    ImageView flagIcon ;
    ImageView timerIcon ;
    double tForStop ;
    int timeScore  = 0  ;
    public Scores(double x ){
        scoreBar = new Rectangle(0,0,x , 50);
        timing =  new Text(2*x/3 - 40,30,"000");
        nbrFlags = new Text(x/4,30,"20"  );
        scoreBar.setFill(Color.rgb(74, 117, 44));
        try {
            flagIcon = new ImageView(new Image(new FileInputStream("src/sample/MineSweeperGame/imgs/flag_icon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            timerIcon = new ImageView(new Image(new FileInputStream("src/sample/MineSweeperGame/imgs/clock_icon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //flag position
        flagIcon.setFitHeight(40);
        flagIcon.setFitWidth(40);
        flagIcon.setX(x/3);
        flagIcon.setY(5);
        timerIcon.setFitHeight(40);
        timerIcon.setFitWidth(40);
        timerIcon.setX(2*x/3);
        timerIcon.setY(5);
        timing.setScaleX(1.5);
        nbrFlags.setScaleX(1.5);
        timing.setScaleY(1.5);
        nbrFlags.setScaleY(1.5);
        timing.setFill(Color.WHITE);
        nbrFlags.setFill(Color.WHITE);
        tForStop = System.currentTimeMillis()*0.001 ;
    }
    public void timer(){
        timingReal = new AnimationTimer() {
            double  t = System.currentTimeMillis()*0.001 ;
            @Override
            public void handle(long l) {
                if((int)(System.currentTimeMillis()*0.001  - tForStop) > 999 ) timingReal.stop();
                timing.setText(("00").format("%03d",((int)(System.currentTimeMillis()*0.001 -  t))));
                timeScore = (int)(System.currentTimeMillis()*0.001 -  t) ;
            }
        };
        timingReal.start();
    }
}
