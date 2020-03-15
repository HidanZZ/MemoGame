package sample.MineSweeper;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class Cell {
    private  int x ;
    Text text ;
    private  int y ;
    Circle c1 ;
    private  int w ;
    Rectangle r ;
    boolean revealed = false ;
    int neighborCount ;

    protected boolean isItBomb = false ;
    public Cell(int x, int y, int w) {
        this.x = x;
        this.y = y;
        this.w = w;

    }
    public  Rectangle addRectangle(){
        r = new Rectangle();
        r.setX(this.x*20);
        r.setY(this.y*20);
        r.setWidth(this.w);
        r.setHeight(this.w);
        r.setArcWidth(1);
        r.setArcHeight(1);
        r.setStroke(Color.BLACK);
        r.setFill(Color.GRAY);
        r.setStrokeWidth(1);



        return r ;
    }
    public Circle drawBomb (){
            c1= new Circle(this.x*20+0.5*this.w, this.y*20+0.5*this.w, 5);
            c1.setVisible(false);
            c1.setStrokeWidth(1);

        return  c1;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", r=" + r +
                '}';
    }

    public int countBombs(Cell[][] grid) {
        if (this.isItBomb) {
            this.neighborCount = -1;
            return -1 ;
        }
        int total = 0;
        for (int xoff = -1; xoff <= 1; xoff++) {
            int celli = this.x + xoff;
            if (celli < 0 || celli >= 20) continue;

            for (int yoff = -1; yoff <= 1; yoff++) {
                int cellj = this.y + yoff;
                if (cellj < 0 || cellj >= 20) continue;

                Cell neighbor = grid[celli][cellj];
                if (neighbor.isItBomb) {
                    total++;
                }
            }
        }
        this.neighborCount = total;
        return  total  ;


    }

    public Text drawNumberBomb(int countBombs) {
       text = new Text(""+countBombs);
        text.setX(this.x*20+0.3*this.w);
        text.setY(this.y*20+0.7*this.w);
        text.setVisible(false);
        text.setStrokeWidth(1);

        return text;
    }

    public void floodFill(Cell[][] grid) {
        for (int xoff = -1; xoff <= 1; xoff++) {
            int celli = x + xoff;
            if (celli < 0 || celli >= 20) continue;

            for (int yoff = -1; yoff <= 1; yoff++) {
                int cellj = y + yoff;
                if (cellj < 0 || cellj >= 20) continue;

                Cell neighbor = grid[celli][cellj];
                // Note the neighbor.bee check was not required.
                // See issue #184
                if (!neighbor.revealed && neighbor.text==null) {
                    neighbor.r.setFill(Color.LIGHTGRAY);
                    neighbor.reveal(grid);

                }
            }

        }
    }
    void reveal(Cell[][] grid) {
        revealed = true;
        if (neighborCount == 0) {
            // flood fill time
            floodFill(grid);
        }
    }

}
