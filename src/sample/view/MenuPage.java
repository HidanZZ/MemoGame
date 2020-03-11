package sample.view;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import sample.model.MenuButton;
import sample.model.MenuSubScene;

import java.util.ArrayList;
import java.util.List;

public class MenuPage extends AnchorPane {
    private List<MenuButton> menuButtons=new ArrayList<>();
    private MenuSubScene playSubScene;
    private MenuSubScene scoreSubScene;
    private Boolean isHidden=true;
    private final String BG_STYLE="-fx-background-image:url('/sample/view/ressources/bg.png')";




    public MenuPage() {
        setStyle(BG_STYLE);
        createButtons();
        playSubScene=new MenuSubScene();
        getChildren().add(playSubScene);
        initMouseListener();


    }

    public List<MenuButton> getMenuButtons() {
        return menuButtons;
    }

    private void createButtons(){
        createPlayButton();
        createScoreButton();
        createExitButton();
        setButtonEffectEntered();

    }
    private void createPlayButton(){
        MenuButton play=new MenuButton("Play");
        menuButtons.add(play);
        getChildren().add(play);
        play.setLayoutX(370);
        play.setLayoutY(100+menuButtons.size()*70);

    }
    private void createScoreButton(){
        MenuButton score=new MenuButton("Scores");
        menuButtons.add(score);
        getChildren().add(score);
        score.setLayoutX(370);
        score.setLayoutY(100+menuButtons.size()*70);
    }
    private void createExitButton(){
        MenuButton exit=new MenuButton("Exit");
        menuButtons.add(exit);
        getChildren().add(exit);
        exit.setLayoutX(370);
        exit.setLayoutY(100+menuButtons.size()*70);
    }
    private void setButtonEffectEntered(){
        for (MenuButton mb:menuButtons
        ) {
            mb.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Glow glow=new Glow(1);
                    mb.setEffect(glow);
                }
            });
            mb.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mb.setEffect(null);
                }
            });
        }
    }

    private void initMouseListener(){
        menuButtons.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (isHidden){
                    playSubScene.showSubScene();
                    isHidden=false;
                }else{
                    playSubScene.hideSubScene();
                }
            }
        });
        menuButtons.get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (isHidden){
                    playSubScene.showSubScene();
                    isHidden=false;
                }else{
                    playSubScene.hideSubScene();
                }
            }
        });

    }
}
