package sample.MineSweeperGame;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Difficulties {
    double width = 400;
    double cellNbr = 40  ;
    boolean clicked = false ;
    ComboBox  difficulty ;
    Pane root;
    Scene scene;
    ImageView backgroundImage ;
    String[] difficulties = {"EASY","NORMAL","HARD"};
    String chosenDifficulty;
    TextField name ;
    double rand ;
    double nbrBombs ;
    Button choseAccept;
    public Difficulties(){
        try {
            backgroundImage = new ImageView(new Image(new FileInputStream("src/sample/MineSweeperGame/imgs/cta.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        choseAccept = new Button("Accept");
        root = new Pane();
        name = new TextField();
        scene = new Scene(root,688 , 230);
        difficulty = new ComboBox<>(FXCollections.observableArrayList(difficulties));
        difficulty.setLayoutX(345);
        difficulty.setValue("Select Difficulty");
        difficulty.setLayoutY(30);
        difficulty.setMinSize(200 , 40);
        name.setLayoutX(143);
        name.setLayoutY(30);
        name.setMinSize(200,40);
        name.setText("ghassane");
        choseAccept.setLayoutX(296);
        choseAccept.setLayoutY(150);
        choseAccept.setMinWidth(100);
        choseAccept.setMinHeight(30);
        choseAccept.setStyle("-fx-background-color: #1A73E8 ");
        difficulty.setOnAction(actionEvent -> {
            if(difficulty.getValue() .equals("EASY") ){
                width = 400 ; cellNbr = 50;
                nbrBombs = 7 ;
                rand = 0.1;
                chosenDifficulty = "EASY";
                clicked = true ;
            }
            if(difficulty.getValue() .equals("NORMAL") ) {
                width = 480 ; cellNbr = 40;
                nbrBombs = 14 ;
                rand = 0.1;
                chosenDifficulty = "NORMAL";
                clicked = true ;
            }
            if(difficulty.getValue() .equals("HARD") ){
                width = 640 ; cellNbr = 40;
                nbrBombs = 20 ;
                rand = 0.1;
                chosenDifficulty = "HARD";
                clicked = true ;
            }
        });
        choseAccept.setPadding(Insets.EMPTY);
        root.getChildren().add(backgroundImage);
        root.getChildren().add(difficulty);
        root.getChildren().add(choseAccept);
        root.getChildren().add(name);
    }
}
