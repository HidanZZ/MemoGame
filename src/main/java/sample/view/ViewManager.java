package sample.view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.FlapyBird.FlapyBird;
import sample.MineSweeper.MineSweepper;
import sample.SpaceInvaders.SpaceInvaders;
import sample.model.Grid;

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
                GamePage gamePage=new GamePage(Grid.small,menuPage.getChoosenDifficulty());
                gamePage.show();



            }
        });
        menuPage.getGridButtons().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                GamePage gamePage=new GamePage(Grid.average,menuPage.getChoosenDifficulty());
                gamePage.show();



            }
        });
        menuPage.getGridButtons().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                GamePage gamePage=new GamePage(Grid.big,menuPage.getChoosenDifficulty());
                gamePage.show();


            }
        });

        menuPage.getMenuButtons().get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                MineSweepper mineSweepper = new MineSweepper();
                mineSweepper.show();
            }
        });
        menuPage.getMenuButtons().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                FlapyBird flapyBird = new FlapyBird();
                flapyBird.show();
            }
        });

        menuPage.getMenuButtons().get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainStage.close();
                SpaceInvaders spaceInvaders = new SpaceInvaders();
                spaceInvaders.show();
            }
        });
    }


}
