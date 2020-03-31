package sample.FlappyBirdV2;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Difficulty {
    Pane root;
    Scene scene ;
    String birdType  ;
    String backgroundType  ;
    String namePlayer  ;
    Image birdTypeA1 ;
    Image birdTypeA2 ;
    ImageView background;
    ImageView backgroundSupport;
    ImageView back1;
    ImageView back2;
    ImageView sun;
    ImageView logo;
    ImageView bird;
    ImageView bird1;
    ImageView bird2;
    ImageView bird3;
    Image birdYA1;
    Image birdYA2;
    Image birdA1;
    Image buirdA1;
    Image birdA2;
    Image buirdA2;
    AnimationTimer timerForBack ;
    RadioButton  chosenBird1 ;
    RadioButton  chosenBird2 ;
    RadioButton  chosenBird3 ;
    RadioButton  background1 ;
    RadioButton  background2 ;
    ToggleGroup birds;
    ToggleGroup backgrounds;
    Font f ;
    Font d ;
    TextField name ;
    boolean start = false ;
    Text pressEnter ;
    public  Difficulty(){
        namePlayer = "ghassane";
        try {
            f= Font.loadFont(new FileInputStream(new File("src/sample/FlappyBirdV2/fonts/Changa.ttf")), 22);
            d= Font.loadFont(new FileInputStream(new File("src/sample/FlappyBirdV2/fonts/Orbitron.ttf")), 22);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        root = new Pane();
        birdType = "birdY";
        backgroundType = "day";
        putBackground();
        decChamp();
        scene = new Scene(root , 1024 ,576 ) ;
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                start = true;
                timerForBack.stop(); }
        });
        backgroundChose();
    }
    public  void putBackground(){
        try {
            background = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/background_day.png")));
            backgroundSupport = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/background_day.png")));
            sun = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/sun.png")));
            logo = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/logo.png")));
            bird = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/birdYA1.png")));
            birdTypeA1 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/birdYA1.png")) ;
            birdTypeA2 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/birdYA2.png")) ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        logo.setFitWidth(331);
        logo.setFitHeight(101);
        logo.setY(120);
        logo.setX(341);
        bird.setFitHeight(55);

        bird.setFitWidth(70);

        bird.setX(487);
        bird.setY(240);

        backgroundSupport.setX(1024);
        sun.setFitWidth(1024);
        root.getChildren().add(background);
        root.getChildren().add(backgroundSupport);
        root.getChildren().add(sun);
        root.getChildren().add(logo);
        root.getChildren().add(bird);
        pressEnter();
        timerForBack = new AnimationTimer() {
            double j = 0 ;
            int i=0  ;
            int n = 0 ;
            @Override
            public void handle(long l) {
                if (j%19 == 0 ){
                    if(n<4 ) pressEnter.setOpacity(pressEnter.getOpacity()-0.15);
                    else if( n>3  ) pressEnter.setOpacity(pressEnter.getOpacity()+0.15);
                    if (n== 7 ) n = -1 ;
                    n++ ;
                }
                if(j%6== 0 ){
                    if ( i<8 && i>2) {
                        if (i == 3) {
                            bird.setImage(birdTypeA1);
                        }
                        bird.setY(bird.getY() + 1);
                    }
                    else {
                        if (i == 8) bird.setImage(birdTypeA2);
                        bird.setY(bird.getY() - 1);
                    }
                    if (i == 9) i = -1 ;
                    i++ ;
                }
                if(j%2 == 0 ){
                    background.setX(background.getX()-1);
                    backgroundSupport.setX(backgroundSupport.getX()-1);
                    if (backgroundSupport.getX()<=-1024) backgroundSupport.setX(1024);
                    if (background.getX()<=-1024) background.setX(1024);
                }
                j++ ;
            }
        };
        timerForBack.start();
    }
    public  void decChamp(){
        try {
            birdYA1 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/birdYA1.png"));
            birdA1 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/birdA1.png"));
            buirdA1 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/buirdA1.png"));

            birdYA2 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/birdYA2.png"));
            birdA2 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/birdA2.png"));
            buirdA2 = new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/buirdA2.png"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bird1 = new ImageView(birdYA1);
        bird2 = new ImageView(birdA1);
        bird3 = new ImageView(buirdA1);

        birds = new ToggleGroup();
        chosenBird1 = new RadioButton();
        chosenBird2 = new RadioButton();
        chosenBird3 = new RadioButton();

        bird1.setFitHeight(40);
        bird2.setFitHeight(45);
        bird3.setFitHeight(45);

        bird1.setFitWidth(50);
        bird2.setFitWidth(50);
        bird3.setFitWidth(50);

        bird1.setX(340);
        bird2.setX(480);
        bird3.setX(620);

        bird1.setY(340);
        bird2.setY(340);
        bird3.setY(340);

        root.getChildren().add(bird1);
        root.getChildren().add(bird2);
        root.getChildren().add(bird3);

        chosenBird1.setToggleGroup(birds);
        chosenBird2.setToggleGroup(birds);
        chosenBird3.setToggleGroup(birds);

        chosenBird1.setSelected(true);

        chosenBird1.setLayoutX(355);
        chosenBird2.setLayoutX(495);
        chosenBird3.setLayoutX(638);

        chosenBird1.setLayoutY(410);
        chosenBird2.setLayoutY(410);
        chosenBird3.setLayoutY(410);

        root.getChildren().add(chosenBird1);
        root.getChildren().add(chosenBird2);
        root.getChildren().add(chosenBird3);

        bird1.setOnMouseClicked(mouseEvent -> {
            chosenBird1.setSelected(true);
            chosenBird2.setSelected(false);
            chosenBird3.setSelected(false);
            birdTypeA1 = birdYA1;
            birdTypeA2 = birdYA2;
            birdType = "birdY";
        });
        bird2.setOnMouseClicked(mouseEvent -> {
            chosenBird1.setSelected(false);
            chosenBird2.setSelected(true);
            chosenBird3.setSelected(false);
            birdTypeA1 = birdA1;
            birdTypeA2 = birdA2;
            birdType = "bird";
        });
        bird3.setOnMouseClicked(mouseEvent -> {
            chosenBird1.setSelected(false);
            chosenBird2.setSelected(false);
            chosenBird3.setSelected(true);
            birdTypeA1 = buirdA1;
            birdTypeA2 = buirdA2;
            birdType = "buird";
        });
        chosenBird1.setOnAction(actionEvent -> {
            birdTypeA1 = birdYA1;
            birdTypeA2 = birdYA2;
            birdType = "birdY";
        });
        chosenBird2.setOnAction(actionEvent -> {
            birdTypeA1 = birdA1;
            birdTypeA2 = birdA2;
            birdType = "bird";
        });
        chosenBird3.setOnAction(actionEvent -> {
            birdTypeA1 = buirdA1;
            birdTypeA2 = buirdA2;
            birdType = "buird";
        });
    }
    public void backgroundChose(){
        name = new TextField();
        backgrounds = new ToggleGroup();
        background1 = new RadioButton();
        background2 = new RadioButton();
        ImageView rectangle = new ImageView();
        Text back1T = new Text("Countryside");
        Text back2T = new Text("City");
        background1.setToggleGroup(backgrounds);
        background2.setToggleGroup(backgrounds);

        background1.setLayoutX(800);
        background2.setLayoutX(800);
        background1.setLayoutY(30);
        background2.setLayoutY(85);

        try {
            back1 = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/background_day.png")));
            back2 = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/background_day2.png")));
            rectangle = new ImageView(new Image(new FileInputStream("src/sample/FlappyBirdV2/imgs/board.png")));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        back1T.setFont(d);
        back2T.setFont(d);
        rectangle.setFitWidth(200);
        rectangle.setFitHeight(138);
        rectangle.setX(780);

        name.setMinWidth(198);
        name.setMinHeight(30);
        name.setLayoutX(785);
        name.setLayoutY(150);
        name.setText("ghassane");
        name.setOnKeyTyped(actionEvent -> {if(!name.getText().equals("") && name.getText()!= null) namePlayer = name.getText();});
        //name.setOnAction();
        background1.setGraphic(back1T);
        background2.setGraphic(back2T);
        background1.setSelected(true);
        background1.setOnAction(actionEvent -> {
            background.setImage(back1.getImage());
            backgroundSupport.setImage(back1.getImage());
            backgroundType = "day";
            sun.setVisible(true);
        });
        background2.setOnAction(actionEvent -> {
            background.setImage(back2.getImage());
            backgroundSupport.setImage(back2.getImage());
            backgroundType = "day2";
            sun.setVisible(false);
        });

        root.getChildren().add(rectangle);
        root.getChildren().add(name);
        root.getChildren().add(background1);
        root.getChildren().add(background2);


    }

    public void pressEnter(){
        pressEnter = new Text("Press Enter To Start");
        pressEnter.setX(410);
        pressEnter.setY(500);
        pressEnter.setFill(Color.WHITE);
        pressEnter.setScaleX(2);
        pressEnter.setScaleY(2);
        root.getChildren().add(pressEnter);
        pressEnter.setFont(f);

    }
}
