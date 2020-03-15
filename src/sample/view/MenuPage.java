package sample.view;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import sample.MineSweeper.MineSweepper;
import sample.model.*;

import java.util.ArrayList;
import java.util.List;

public class MenuPage extends AnchorPane {
    private List<MenuButton> menuButtons=new ArrayList<>();
    private List<MenuButton> gridButtons=new ArrayList<>();
    private MenuSubScene playSubScene;
    private MenuSubScene scoreSubScene;
    private Boolean isHidden=true;
    private Difficulty choosenDifficulty;
    private Grid choosenGrid;


    public MenuPage() {
        String BG_STYLE = "-fx-background-image:url('/sample/view/ressources/bg.png')";
        setStyle(BG_STYLE);
        createButtons();
        createplaySubScene();
        getChildren().add(playSubScene);
        initMouseListener();


    }

    public Grid getChoosenGrid() {
        return choosenGrid;
    }

    public List<MenuButton> getMenuButtons() {
        return menuButtons;
    }

    private void createButtons(){
        createPlayButton();
        createMineSweeper();
        createFlapyBird();
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
    public  void createMineSweeper(){
        MenuButton play=new MenuButton("MineSweeper");
        menuButtons.add(play);
        getChildren().add(play);
        play.setLayoutX(370);
        play.setLayoutY(100+menuButtons.size()*70);

    }
    public  void createFlapyBird(){
        MenuButton play=new MenuButton("FlapyBird");
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
    private  void createscoreSubScene(){

    }
    private  void createplaySubScene(){
        playSubScene=new MenuSubScene();
        createDiffChooser();
    }

    private void createDiffChooser(){
        List<MenuButton> diffButtons=new ArrayList<>();
        playSubScene.getPane().getChildren().clear();
        MenuButton easyButton=new MenuButton("easy");
        MenuButton normalButton=new MenuButton("normal");
        MenuButton hardButton=new MenuButton("hard");
        MenuButton smallGridButton=new MenuButton("5x5");
        MenuButton averageGridButton=new MenuButton("6x6");
        MenuButton bigGridButton=new MenuButton("7x7");
        gridButtons.add(smallGridButton);
        gridButtons.add(averageGridButton);
        gridButtons.add(bigGridButton);
        diffButtons.add(easyButton);
        diffButtons.add(normalButton);
        diffButtons.add(hardButton);
        easyButton.setLayoutX(125);
        easyButton.setLayoutY(130);
        normalButton.setLayoutX(125);
        hardButton.setLayoutX(125);
        normalButton.setLayoutY(200);
        hardButton.setLayoutY(270);
        playSubScene.getPane().getChildren().add(easyButton);
        playSubScene.getPane().getChildren().add(normalButton);
        playSubScene.getPane().getChildren().add(hardButton);
        SubSceneLabel subSceneLabel=new SubSceneLabel("select Difficulty");
        MenuButton returnButton=new MenuButton("Return",50,250);
        returnButton.setLayoutX(200);
        returnButton.setLayoutY(415);
        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                playSubScene.hideSubScene();
            }
        });
        playSubScene.getPane().getChildren().add(subSceneLabel);
        playSubScene.getPane().getChildren().add(returnButton);
        easyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                choosenDifficulty= Difficulty.EASY;
                createGridChooser();
            }
        });
        normalButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                choosenDifficulty= Difficulty.normal;
                createGridChooser();
            }
        });
        hardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                choosenDifficulty= Difficulty.hard;
                createGridChooser();
            }
        });
        for (MenuButton mb:diffButtons
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
    private void createGridChooser(){
        playSubScene.getPane().getChildren().clear();
        SubSceneLabel subSceneLabel=new SubSceneLabel("select Grid");
        MenuButton returnButton=new MenuButton("Return",50,250);
        returnButton.setLayoutX(200);
        returnButton.setLayoutY(415);
        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                playSubScene.hideSubScene();
            }
        });

        gridButtons.get(0).setLayoutX(125);
        gridButtons.get(0).setLayoutY(130);
        gridButtons.get(1).setLayoutX(125);
        gridButtons.get(2).setLayoutX(125);
        gridButtons.get(1).setLayoutY(200);
        gridButtons.get(2).setLayoutY(270);
        playSubScene.getPane().getChildren().add(gridButtons.get(0));
        playSubScene.getPane().getChildren().add(gridButtons.get(1));
        playSubScene.getPane().getChildren().add(gridButtons.get(2));
        playSubScene.getPane().getChildren().add(subSceneLabel);
        playSubScene.getPane().getChildren().add(returnButton);

        for (MenuButton mb:gridButtons
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
                createDiffChooser();
                playSubScene.showSubScene();
            }
        });
        menuButtons.get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
              //  scoreSubScene.showSubScene();
            }
        });

    }

    public Difficulty getChoosenDifficulty() {
        return choosenDifficulty;
    }

    public List<MenuButton> getGridButtons() {
        return gridButtons;
    }
}
