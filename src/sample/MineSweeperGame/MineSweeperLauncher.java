package sample.MineSweeperGame;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class MineSweeperLauncher   {

    public  MineSweeperLauncher(Stage primaryStage) {
        Difficulties difficulty = new Difficulties();
        primaryStage.setScene(difficulty.scene);
        primaryStage.show();
        difficulty.choseAccept.setOnMouseClicked(mouseEvent -> {
            { if (difficulty.clicked) {
                difficulty.clicked = false ;
                primaryStage.close();
                final MineSweeper[] mineSweeper = {new MineSweeper(difficulty.width , difficulty.cellNbr , difficulty.nbrBombs , difficulty.rand , difficulty.name.getText())};
                primaryStage.setTitle("Mine Sweeper");
                primaryStage.setScene(mineSweeper[0].scene);
                primaryStage.show();
                AnimationTimer timer = new AnimationTimer() {
                    int j = 0;
                    @Override
                    public void handle(long l) {
                        if (mineSweeper[0].scoreDisplayed) {
                            if (mineSweeper[0].restart) {
                                j = 0;
                                primaryStage.close();
                                mineSweeper[0] = new MineSweeper(difficulty.width , difficulty.cellNbr, difficulty.nbrBombs , difficulty.rand, difficulty.name.getText());
                                primaryStage.setTitle("Mine Sweeper");
                                primaryStage.setScene(mineSweeper[0].scene);
                                primaryStage.show();
                            }
                            if (mineSweeper[0].backToDif){
                                mineSweeper[0].backToDif = false ;
                                primaryStage.close();
                                primaryStage.setScene(difficulty.scene);
                                primaryStage.show();
                            }
                            j++;
                        }
                    }
                };
                timer.start();
            }
            }
        });
    }

}
