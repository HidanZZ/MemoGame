package sample.model;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class Heart {
    private ImageView screen;
    private final String IMG_PATH="file:src/sample/assets/heart/heart.png";

    public Heart(AnchorPane anchorPane,int x,int y) {
        screen=new ImageView(IMG_PATH);
        screen.setLayoutX(x);
        screen.setLayoutY(y);
        anchorPane.getChildren().add(screen);
    }

    public ImageView getScreen() {
        return screen;
    }
    public ImageView getEmpty(){
       screen=new ImageView("file:src/sample/assets/heart/empty.png");
       return screen;

    }
}
