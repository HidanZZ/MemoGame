package sample.MineSweeperGame;

import javafx.animation.AnimationTimer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class MineSweeperLauncher   {
    public MediaPlayer menuMusic ;
    public  MineSweeperLauncher(Stage primaryStage) {

        Difficulties difficulty = new Difficulties();
        primaryStage.setScene(difficulty.scene);
        primaryStage.show();
        menuMusic = new MediaPlayer(new Media(new File("src/sample/MineSweeperGame/audio/musicMain.wav").toURI().toString()));
        menuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        menuMusic.play();
        difficulty.choseAccept.setOnMouseClicked(mouseEvent -> {
            { if (difficulty.clicked) {
                difficulty.clicked = false ;
                primaryStage.close();
                menuMusic.stop();
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
                                menuMusic.stop();
                                mineSweeper[0] = new MineSweeper(difficulty.width , difficulty.cellNbr, difficulty.nbrBombs , difficulty.rand, difficulty.name.getText());
                                primaryStage.setTitle("Mine Sweeper");
                                primaryStage.setScene(mineSweeper[0].scene);
                                primaryStage.show();
                            }
                            if (mineSweeper[0].backToDif){
                                mineSweeper[0].backToDif = false ;
                                primaryStage.close();
                                menuMusic.play();
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
