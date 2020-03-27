package sample.MineSweeperGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Cells {
    double d ;
    double x ;
    double y ;
    Color color ;
    Rectangle cell ;
    boolean flagged = false ;
    boolean bombIsVisible = false ;
    boolean bombIt ;
    boolean isExposed = false  ;
    boolean bolColor ;
    ImageView flagIcon;
    Circle bomb;
    Text neighborBomb;
    public Cells(Color color , boolean bombIt , double posX , double posY , double d , boolean bi) {
        this.d = d ;
        this.bolColor = bi ;
        this.color = color ;
        this.x = posX;
        this.y = posY;
        this.bombIt = bombIt ;
        cell = new Rectangle( (int) posX , (int) posY ,(int) d ,(int) d );
        try {
            flagIcon = new ImageView(new Image(new FileInputStream("src/sample/MineSweeperGame/imgs/flag_icon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        neighborBomb = new Text("");
        makeBomb(bombIt);
        cell.setFill(color);
        //flag position
        flagIcon.setFitHeight(8*d/10);
        flagIcon.setFitWidth(8*d/10);
        flagIcon.setX(posX + d/10);
        flagIcon.setY(posY + d/10);
        neighborBomb.setX(posX + d/2.3);
        neighborBomb.setY(posY + d/1.7);
        neighborBomb.setScaleX(2);
        neighborBomb.setScaleY(2);
        neighborBomb.setVisible(false);
        flagIcon.setVisible(false);

    }
    public void makeBomb(boolean bombIt){
        if (bombIt) {
            bomb = new Circle(this.x + (this.d / 2), this.y + (this.d / 2), this.d / 4);
            bomb.setVisible(false);
        }
    }


}
