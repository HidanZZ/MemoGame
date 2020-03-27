package sample.MineSweeperGame;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MineSweeper extends Stage {
    Pane root;
    Cells cell;
    Scene scene;
    List<Cells> cellList;
    List<Cells> bombList;
    Cells[][] cells;
    Difficulties difficulty;
    Scores score;
    Rectangle finalScore;
    boolean start = false;
    boolean click = true;
    boolean restart = false;
    boolean win = false;
    boolean scoreDisplayed = false;
    boolean backToDif = false ;
    double nbrBombs ;
    double nbrBombsWanted ;
    double WIDTH ;
    double cellNbr ;
    double HEIGHT ;
    double rand;
    int flagNbr;
    AnimationTimer timerFlagNbr;
    AnimationTimer timerForShowNeighbor;
    DataBaseForMSs scoreRecord ;
    String name ;
    public MineSweeper(double width , double cellNbrs , double nbrBomb , double rand , String name) {
        WIDTH = width ;
        nbrBombs = nbrBomb ;
        this.rand = rand ;
        this.name = name ;
        nbrBombsWanted = nbrBomb ;
        HEIGHT = WIDTH + 50 ;
        cellNbr =  cellNbrs ;
        root = new Pane();
        difficulty = new Difficulties();
        scene = new Scene(root, width, width + 50);
        cellList = new ArrayList<>();
        bombList = new ArrayList<>();
        score = new Scores(WIDTH);
        root.getChildren().add(score.scoreBar);
        root.getChildren().add(score.flagIcon);
        root.getChildren().add(score.timing);
        root.getChildren().add(score.timerIcon);
        root.getChildren().add(score.nbrFlags);
        createCells(cellNbr);
        onHover();
        neighborCount();
        cellClick();
        timerForNeighbor();
    }

    public void createCells(double d) {
        boolean bi = false;
        for (int i = 0; i < this.WIDTH; i += d) {
            for (int j = 50; j < this.HEIGHT; j += d) {
                double bomb = Math.random();
                if (!bi) {
                    cell = new Cells(Color.rgb(170, 215, 81), bomb < rand && nbrBombs != 0, i, j, d, bi);
                    bi = true;
                    cellList.add(cell);
                } else {
                    cell = new Cells(Color.rgb(125, 161, 57), bomb < rand && nbrBombs != 0, i, j, d, bi);
                    bi = false;
                    cellList.add(cell);
                }
                root.getChildren().add(cell.cell);
                root.getChildren().add(cell.flagIcon);
                root.getChildren().add(cell.neighborBomb);
                if (bomb < rand && nbrBombs != 0) {
                    root.getChildren().add(cell.bomb);
                    bombList.add(cell);
                    nbrBombs--;
                }
            }
            if (bi) bi = false;
            else if (!bi) bi = true;
        }
        cells = new Cells[(int) Math.sqrt(cellList.size())][(int) Math.sqrt(cellList.size())];
        int c = 0;
        for (int i = 0; i < Math.sqrt(cellList.size()); i++) {
            for (int j = 0; j < Math.sqrt(cellList.size()); j++) {
                cells[i][j] = cellList.get(c);
                c++;
            }
        }
    }

    public void onHover() {
        for (Cells cell : cellList) {
            cell.cell.setOnMouseEntered(mouseEvent -> {

                if (!cell.isExposed) {
                    cell.cell.setFill(Color.rgb(139, 201, 99));
                }
            });
            cell.cell.setOnMouseExited(mouseEvent -> {
                if (!cell.isExposed) {
                    cell.cell.setFill(cell.color);
                }
            });
        }
    }

    public void colorsBombs(boolean lose) {
        timerFlagNbr = new AnimationTimer() {
            double j = 0;
            int i = 0 ;
            @Override
            public void handle(long l) {
                if (j%15==0)if (lose) clickedBomb();
                if (j % 8 == 0) {
                    if (i == cellList.size()){
                        if (!cellList.get(i-1).bombIt)cellList.get(i-1).cell.setFill(cellList.get(i-1).color);
                        i = 0 ;
                    }
                    if(!lose ){
                        if (!cellList.get(i).bombIt){
                            cellList.get(i).cell.setFill(Color.color(Math.random(),Math.random(),Math.random() , 0.7));
                        }
                        if (i-1>-1) if (!cellList.get(i-1).bombIt)cellList.get(i-1).cell.setFill(cellList.get(i-1).color);
                    }
                    i++;
                }

                j++;
            }
        };
        timerFlagNbr.start();
    }

    public void cellClick() {
        flagNbr = (int) nbrBombsWanted - (int) nbrBombs;
        score.nbrFlags.setText(flagNbr + "");
        for (Cells[] value : cells) {
            for (int j = 0; j < cells.length; j++) {
                Cells cell = value[j];
                cell.cell.setOnMouseClicked(mouseEvent -> {
                    if (click) {
                        if (mouseEvent.getButton() == MouseButton.SECONDARY && start) {
                            if (!cell.isExposed) {
                                if (!cell.flagged && !cell.bombIsVisible) {
                                    cell.flagIcon.setVisible(true);
                                    cell.flagged = true;
                                    flagNbr--;
                                    score.nbrFlags.setText(flagNbr + "");
                                } else if (cell.flagged && !cell.bombIsVisible) {
                                    cell.flagIcon.setVisible(false);
                                    cell.flagged = false;
                                    flagNbr++;
                                    score.nbrFlags.setText(flagNbr + "");
                                }
                            }
                        }
                        if (mouseEvent.getButton() == MouseButton.PRIMARY && !cell.flagged) {
                            if (!start) {
                                score.timer();
                                start = true;
                            }
                            if (cell.bombIt) {
                                click = false;
                                clickedBomb();
                                colorsBombs(true);
                                try {
                                    displayScore(false, "src/sample/MineSweeperGame/imgs/lose_screen.png");
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                cell.isExposed = true;
                                neighborCount();

                                cell.neighborBomb.setVisible(true);
                                if (!cell.bolColor) {
                                    cell.cell.setFill(Color.rgb(229, 194, 159));
                                    cell.color = Color.rgb(229, 194, 159);
                                } else if (cell.bolColor) {
                                    cell.cell.setFill(Color.rgb(215, 184, 153));
                                    cell.color = Color.rgb(215, 184, 153);
                                }
                                win();
                            }
                        }
                    }
                });
            }
        }
    }

    public void clickedBomb() {
        for (Cells cell : bombList) {
            if (!cell.flagged) {
                Color col = Color.color(Math.random(), Math.random(), Math.random(), 0.7);
                cell.bomb.setVisible(true);
                cell.bomb.setFill(col.darker());
                cell.cell.setFill(col);
                cell.color = col;
                cell.bombIsVisible = true;
            }
        }
    }

    public void neighborCount() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (cells[i][j].bombIt) continue;
                int size = 0;
                for (int l = -1; l < 2; l++) {
                    for (int m = -1; m < 2; m++) {
                        if (i + m > -1 && i + m < cells.length && j + l < cells.length && j + l > -1)
                            if (cells[i + m][j + l].bombIt) size = size + 1;
                    }
                    if (size != 0) {
                        cells[i][j].neighborBomb.setText("" + size);
                        if (size == 1) cells[i][j].neighborBomb.setFill(Color.BLUE);
                        if (size == 2) cells[i][j].neighborBomb.setFill(Color.GREEN);
                        if (size == 3) cells[i][j].neighborBomb.setFill(Color.RED);
                        if (size == 4) cells[i][j].neighborBomb.setFill(Color.GRAY);
                        if (size == 5) cells[i][j].neighborBomb.setFill(Color.BROWN);
                    }
                }
            }
        }
    }

    public void timerForNeighbor() {
        timerForShowNeighbor = new AnimationTimer() {
            @Override
            public void handle(long k) {
                for (int i = 0; i < cells.length; i++) {
                    for (int j = 0; j < cells.length; j++) {
                        if (cells[i][j].isExposed && cells[i][j].neighborBomb.getText().equals("")) {
                            for (int l = -1; l < 2; l++) {
                                for (int m = -1; m < 2; m++) {
                                    if (i + m > -1 && i + m < cells.length && j + l < cells.length && j + l > -1) {
                                        if (!cells[i + m][j + l].flagged) {
                                            if (true){
                                                if (!cells[i + m][j + l].bolColor){
                                                    cells[i + m][j + l].cell.setFill(Color.rgb(229, 194, 159));
                                                    cells[i + m][j + l].color = (Color.rgb(229, 194, 159));
                                                }
                                                else if (cells[i + m][j + l].bolColor) {
                                                    cells[i + m][j + l].cell.setFill(Color.rgb(215, 184, 153));
                                                    cells[i + m][j + l].color = (Color.rgb(215, 184, 153));
                                                }cells[i + m][j + l].isExposed = true;
                                                cells[i + m][j + l].flagIcon.setVisible(false);
                                                cells[i + m][j + l].neighborBomb.setVisible(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        };
        timerForShowNeighbor.start();
    }

    public void displayScore(boolean situation , String img) throws FileNotFoundException {
        scoreDisplayed = true ;
        score.timingReal.stop();
        timerForShowNeighbor.stop();
        // make instants
        Text scoreText;
        if (situation) {
            scoreText = new Text(("00".format("%03d" , score.timeScore)));
            scoreRecord = new DataBaseForMSs(name , score.timeScore  , true ,(int) (nbrBombsWanted-nbrBombs) );
        }else {
            scoreText = new Text("000");
            scoreRecord = new DataBaseForMSs(name , 0 , false ,(int) (nbrBombsWanted-nbrBombs)  );
        }
        Text bestScoreText = new Text(("00".format("%03d" , scoreRecord.highScore)));
        ImageView imageView = new ImageView(new Image(new FileInputStream(img)));
        ImageView time = new ImageView(new Image(new FileInputStream("src/sample/MineSweeperGame/imgs/clock_icon.png")));
        ImageView bestTime = new ImageView(new Image(new FileInputStream("src/sample/MineSweeperGame/imgs/trophy_icon.png")));
        ImageView restartIcon = new ImageView(new Image(new FileInputStream("src/sample/MineSweeperGame/imgs/refresh_white_24dp.png")));
        Rectangle restartBRec = new Rectangle((WIDTH-300)/2,(WIDTH / 4.5) + 210 , 300 , 60);
        Rectangle backToDifB = new Rectangle((WIDTH-300)/2,(WIDTH / 4.5) + 280 , 300 , 60);
        Text restartText = new Text("Restart");
        Text backToDifT = new Text("Choose difficulty");
        // ImageView
        imageView.setFitHeight(120);
        imageView.setFitWidth(300);
        imageView.setX((WIDTH-300)/2);
        imageView.setY((WIDTH / 4.5) + 80);
        // Timing icon
        time.setFitWidth(60);
        time.setFitHeight(60);
        time.setX((WIDTH-300)/2 + 60);
        time.setY((WIDTH / 4.5) + 20);
        // time score text
        scoreText.setX((WIDTH-300)/2 + 75);
        scoreText.setY((WIDTH / 4.5) + 110);
        scoreText.setScaleX(2);
        scoreText.setScaleY(2);
        scoreText.setFill(Color.WHITE);
        // best time icon
        bestTime.setFitWidth(60);
        bestTime.setFitHeight(60);
        bestTime.setX((WIDTH-300)/2 + 180);
        bestTime.setY((WIDTH / 4.5) + 20);
        //best time text
        bestScoreText.setX((WIDTH-300)/2 + 197);
        bestScoreText.setY((WIDTH / 4.5) + 110);
        bestScoreText.setScaleX(2);
        bestScoreText.setScaleY(2);
        bestScoreText.setFill(Color.WHITE);
        // more blue for image
        finalScore = new Rectangle((WIDTH-300)/2, WIDTH / 4.5, 300, 150);
        finalScore.setFill(Color.rgb(74, 192, 253));
        finalScore.setArcHeight(40);
        finalScore.setArcWidth(40);
        //dark layer in background
        Rectangle layerBlack = new Rectangle(0, 0, WIDTH, WIDTH + 50);
        layerBlack.setFill(Color.BLACK);
        layerBlack.setOpacity(0.2);
        //add restart button
        restartBRec.setOnMouseClicked(mouseEvent -> restart = true);
        restartIcon.setOnMouseClicked(mouseEvent -> restart = true);
        restartText.setOnMouseClicked(mouseEvent -> restart = true);
        restartBRec.setArcWidth(20);
        restartBRec.setArcHeight(20);
        restartBRec.setFill(Color.rgb(74, 117, 44));
        restartIcon.setFitWidth(30);
        restartIcon.setFitHeight(30);
        restartIcon.setX((WIDTH-300)/2 + 85);
        restartIcon.setY((WIDTH / 4.5) + 225);
        restartText.setX((WIDTH-300)/2 + 145);
        restartText.setY((WIDTH / 4.5) + 245);
        restartText.setScaleX(1.7);
        restartText.setScaleY(1.7);
        restartText.setFill(Color.WHITE);
        //add back to difficulty
        backToDifB.setArcWidth(20);
        backToDifB.setArcHeight(20);
        backToDifB.setFill(Color.rgb(74, 117, 44));
        backToDifT.setX((WIDTH-300)/2 + 100);
        backToDifT.setY((WIDTH / 4.5) + 313);
        backToDifT.setScaleX(1.7);
        backToDifT.setScaleY(1.7);
        backToDifT.setFill(Color.WHITE);
        backToDifB.setOnMouseClicked(mouseEvent -> backToDif = true);
        backToDifT.setOnMouseClicked(mouseEvent -> backToDif = true);
        //in root to display
        root.getChildren().add(layerBlack);
        root.getChildren().add(finalScore);
        root.getChildren().add(imageView);
        root.getChildren().add(time);
        root.getChildren().add(bestTime);
        root.getChildren().add(scoreText);
        root.getChildren().add(bestScoreText);
        root.getChildren().add(restartBRec);
        root.getChildren().add(restartIcon);
        root.getChildren().add(restartText);
        root.getChildren().add(backToDifB);
        root.getChildren().add(backToDifT);

    }

    public void win() {
        win = true ;
        for (Cells[] value : cells) {
            for (int j = 0; j < cells.length; j++) {
                if (!value[j].bombIt) {
                    if (!value[j].isExposed) win = false;
                }
            }
        }
        if (win) {
            try {
                displayScore(true , "src/sample/MineSweeperGame/imgs/win_screen.png");
                colorsBombs(false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}



