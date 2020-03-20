package sample.model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MenuSubScene extends SubScene {
    private final String BG_STYLE="-fx-background-color:transparent;-fx-background-image:url('/sample/view/ressources/tan_inlay.png')";
    public MenuSubScene() {
        super(new AnchorPane(), 500, 500);
        prefHeight(500);
        prefWidth(500);
        getRoot().setStyle(BG_STYLE);
        setLayoutX(250);
        setLayoutY(650);
    }

    public MenuSubScene(Parent root) {
        super(root, 500, 500);
        prefHeight(500);
        prefWidth(500);
        getRoot().setStyle(BG_STYLE);
        setLayoutX(250);
        setLayoutY(650);
    }

    public void showSubScene(){
        TranslateTransition transition=new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        transition.setToY(-600);
        transition.play();
    }
    public void hideSubScene(){
        TranslateTransition transition=new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        transition.setToY(600);
        transition.play();
    }
    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
    public VBox getPane2(){
        return (VBox) this.getRoot();
    }
}