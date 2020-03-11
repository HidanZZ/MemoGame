package sample.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.MenuButton;

public class ViewManager {
    private final static int HEIGHT=600,WIDTH=1000;
    private Stage mainStage;
    private Scene mainScene;
    private MenuPage menuPage;
    public ViewManager() {
        mainStage=new Stage();
        menuPage=new MenuPage();
        mainScene=new Scene(menuPage,WIDTH,HEIGHT);
        mainStage.setScene(mainScene);
        setExit();


    }

    public Stage getMainStage() {
        return mainStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public MenuPage getMenuPage() {
        return menuPage;
    }
    public void setExit(){
        menuPage.getMenuButtons().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
            }
        });
    }


}
