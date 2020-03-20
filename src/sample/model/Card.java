package sample.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class Card {
    private int Id;
    private final String IMG_PATH="file:src/sample/assets/cards/CARD";
    private int height=180;
    private int width=180;
    private  int x;
    private  int y;
    private Boolean isHidden=false;
    private ImageView imageView;
    private AnchorPane pane;
    private Boolean empty=false;
    private volatile Boolean ismoving=false;

    public Card(ImageView imageView,AnchorPane pane,int id,int x,int y) {
        this.imageView=imageView;
        this.Id=id;
        this.pane=pane;
        this.x=x;
        this.y=y;
        this.imageView.setImage(new Image(IMG_PATH+Integer.toString(this.Id)+"/1.png"));
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);



    }
    public void hideCard(){
        if (!isHidden){
        Timeline timeline=new Timeline();
        for (int i = 1; i <11 ; i++) {
            final int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i * 50), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String id=imageView.getId();
                    imageView=new ImageView(IMG_PATH+Integer.toString(Id)+"/"+Integer.toString(finalI)+".png");
                    imageView.setId(id);
                    imageView.setLayoutX(x);
                    imageView.setLayoutY(y);
                    pane.getChildren().set(Integer.parseInt(imageView.getId()),imageView);

                }
            }));
        }
        timeline.play();
        ismoving=true;
        timeline.setOnFinished(actionEvent -> ismoving=false);
        isHidden=true;}

    }
    public void showCard(){
        if (isHidden){
        if (!empty) {
            Timeline timeline = new Timeline();
            for (int i = 10; i > 0; i--) {
                int finalI = i;
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(Math.abs(i - 11) * 50), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        String id = imageView.getId();
                        imageView = new ImageView(IMG_PATH + Integer.toString(Id) + "/" + Integer.toString(finalI) + ".png");
                        imageView.setId(id);
                        imageView.setLayoutX(x);
                        imageView.setLayoutY(y);
                        pane.getChildren().set(Integer.parseInt(imageView.getId()), imageView);

                    }
                }));
            }
            timeline.play();
            ismoving=true;
            timeline.setOnFinished(actionEvent -> ismoving=false);
            isHidden = false;
        }}
    }
    public ImageView getImageView() {
        return imageView;
    }
    public Timeline getShowingTimeline(){
        Timeline timeline = new Timeline();
        for (int i = 10; i > 0; i--) {
            int finalI = i;
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(Math.abs(i - 11) * 50), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String id = imageView.getId();
                    imageView = new ImageView(IMG_PATH + Integer.toString(Id) + "/" + Integer.toString(finalI) + ".png");
                    imageView.setId(id);
                    imageView.setLayoutX(x);
                    imageView.setLayoutY(y);
                    pane.getChildren().set(Integer.parseInt(imageView.getId()), imageView);

                }
            }));
        }
        return timeline;
    }

    public void setEmpty() {
        String id=imageView.getId();
        imageView = new ImageView("file:src/sample/assets/cards/empty.png");
        imageView.setId(id);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        pane.getChildren().set(Integer.parseInt(imageView.getId()),imageView);
        empty=true;
    }

    public Boolean getIsmoving() {
        return ismoving;
    }

    public int getId() {
        return Id;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }

    @Override
    public String toString() {
        return "Card{" +
                "Id=" + Id +
                ", IMG_PATH='" + IMG_PATH + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", x=" + x +
                ", y=" + y +
                ", isHidden=" + isHidden +
                ", imageView=" + imageView +
                ", pane=" + pane +
                ", empty=" + empty +
                '}';
    }

    public Boolean getEmpty() {
        return empty;
    }
}
