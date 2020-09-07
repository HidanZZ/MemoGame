package sample.view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import sample.FlappyBirdV2.FlippyBirdLauncher;
import sample.MineSweeperGame.MineSweeperLauncher;
import sample.SpaceInvaders.SpaceInvaders;
import sample.model.Grid;

import java.io.File;

public class ViewManager {
    private final static int HEIGHT=600,WIDTH=1000;
    private Stage mainStage;
    private Scene mainScene;
    private MediaPlayer menuMusic ;
    private MenuPage menuPage;
    public ViewManager() {
        mainStage=new Stage();
        menuPage=new MenuPage();
        mainScene=new Scene(menuPage,WIDTH,HEIGHT);
        mainStage.setScene(mainScene);
        turnMenuMusic();
        setExit();
        test();
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
    private void setExit(){
        menuPage.getMenuButtons().get(5).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
            }
        });
    }
    private void test(){
        menuPage.getGridButtons().get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                menuMusic.stop();
                GamePage gamePage=new GamePage(Grid.small,menuPage.getChoosenDifficulty());
                gamePage.show();



            }
        });
        menuPage.getGridButtons().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                menuMusic.stop();
                GamePage gamePage=new GamePage(Grid.average,menuPage.getChoosenDifficulty());
                gamePage.show();



            }
        });
        menuPage.getGridButtons().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                menuMusic.stop();
                GamePage gamePage=new GamePage(Grid.big,menuPage.getChoosenDifficulty());
                gamePage.show();


            }
        });

        menuPage.getMenuButtons().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                menuMusic.stop();
                Stage mainStage1 = new Stage();
                MineSweeperLauncher mineSweeperLauncher = new MineSweeperLauncher(mainStage1);
                mainStage1.getScene().setOnKeyReleased(keyEvent -> {
                    if (keyEvent.getCode() == KeyCode.ESCAPE){
                        mainStage1.close();
                        mineSweeperLauncher.menuMusic.stop();
                        mainStage.show();
                        turnMenuMusic();
                    }
                });
            }
        });
        menuPage.getMenuButtons().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                menuMusic.stop();
                Stage  mainStage1 = new Stage();
                FlippyBirdLauncher birdLauncher = new FlippyBirdLauncher(mainStage1);
                mainStage1.getScene().setOnKeyReleased(keyEvent -> {
                    if (keyEvent.getCode() == KeyCode.ESCAPE){
                    birdLauncher.menuMusic.stop();
                    mainStage1.close();
                    mainStage.show();
                    turnMenuMusic();

                    }
                });
            }
        });

        menuPage.getMenuButtons().get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                menuMusic.stop();
                SpaceInvaders spaceInvaders = new SpaceInvaders();
                spaceInvaders.show();
            }
        });
    }

    private void turnMenuMusic(){
         menuMusic = new MediaPlayer(new Media(new File("src/sample/view/ressources/menuMusic.wav").toURI().toString()));
         menuMusic.setCycleCount(MediaPlayer.INDEFINITE);
         menuMusic.play();
     }
}
