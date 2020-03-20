package sample.MineSweeper;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MineSweepper extends Stage {
    private int cols ;
    private int Height = 400,width = 400;
    private int rows ;
    private Cell[][] grid ;
    private int nbrBomb =60  ;
    int w = 20 ;
    boolean click = true ;
    private AnchorPane gamePane;
    public Scene gameScene;

    public  MineSweepper(){
        cols = 400/w ;
        gamePane=new AnchorPane();
        gameScene=new Scene(gamePane,this.width,this.Height);
        this.setScene(gameScene);
        rows = 400/w ;
        grid = new Cell[cols][rows] ;
        for (int i = 0; i < cols ; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new Cell(i,j,w);
                gamePane.getChildren().add(grid[i][j].addRectangle());
            }
        }
        for (int i = 0; i < cols ; i++) {
            for (int j = 0; j < rows; j++) {
                int finalI = i;
                int finalJ = j;
                grid[i][j].r.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(click)
                        onClick(finalI, finalJ);
                    }
                });
            }
        }
        while (nbrBomb !=0){
            for (int i = 0; i < cols ; i++) {
                for (int j = 0; j < rows; j++) {
                    if (Math.random()<0.08 && nbrBomb != 0  ) grid[i][j].isItBomb = true;
                    if (grid[i][j].isItBomb && nbrBomb != 0  ){
                        gamePane.getChildren().add(grid[i][j].drawBomb());
                        nbrBomb -- ;
                    }
                }
            }
        }
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if(grid[i][j].countBombs(grid) > 0 ){
                    gamePane.getChildren().add(grid[i][j].drawNumberBomb(grid[i][j].countBombs(grid)));
                }

            }
        }
    }

    public void onClick(int i , int j ){
        int nbrCellNotBomb = 0 ;
        if (grid[i][j].text != null) {
            grid[i][j].text.setVisible(true);
            grid[i][j].r.setFill(Color.LIGHTGRAY);
        }
        if (grid[i][j].c1 != null) {
            grid[i][j].c1.setVisible(true);
            grid[i][j].r.setFill(Color.LIGHTGRAY);
        }
        if(grid[i][j].countBombs(grid) == 0 ){
            grid[i][j].r.setFill(Color.LIGHTGRAY);
        }
        if (grid[i][j].isItBomb){
            gameOver();
        }
        grid[i][j].reveal(grid);
        grid[i][j].revealed = true ;

        for ( i = 0; i < cols; i++) {
            for ( j = 0; j < rows; j++) {
                if(grid[i][j].revealed && !grid[i][j].isItBomb) nbrCellNotBomb++ ;
            }
        }
        if(nbrCellNotBomb == 340) gameWin();
    }
    public void gameOver(){
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if ( grid[i][j].text != null)
                    grid[i][j].text.setVisible(true);
                if( grid[i][j].c1!=null)
                    grid[i][j].c1.setVisible(true);
                grid[i][j].r.setFill(Color.RED);
            }
            click = false ;
        }

    }
    public void gameWin(){
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if ( grid[i][j].text != null)
                    grid[i][j].text.setVisible(true);
                if( grid[i][j].c1!=null)
                    grid[i][j].c1.setVisible(true);
                grid[i][j].r.setFill(Color.GREEN);
            }

        }
click = false ;
    }
}
