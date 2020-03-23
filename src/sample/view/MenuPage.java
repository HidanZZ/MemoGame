package sample.view;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import sample.MineSweeper.MineSweepper;
import sample.controller.PlayerDatabase;
import sample.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MenuPage extends AnchorPane {
    private List<MenuButton> menuButtons=new ArrayList<>();
    private List<MenuButton> gridButtons=new ArrayList<>();
    private MenuSubScene playSubScene;
    private MenuSubScene scoreSubScene;
    private Difficulty choosenDifficulty;
    private Grid choosenGrid;


    public MenuPage() {
        String BG_STYLE = "-fx-background-image:url('/sample/view/ressources/bg.png')";
        setStyle(BG_STYLE);
        createButtons();
        createscoreSubScene();
        createplaySubScene();
        getChildren().add(scoreSubScene);
        getChildren().add(playSubScene);
        initMouseListener();


    }

    public Grid getChoosenGrid() {
        return choosenGrid;
    }

    public List<MenuButton> getMenuButtons() {
        return menuButtons;
    }

    private void createButtons(){
        createPlayButton();
        createMineSweeper();
        createFlapyBird();
        createSpaceInvaders();
        createScoreButton();
        createExitButton();
        setButtonEffectEntered();

    }
    private void createPlayButton(){
        MenuButton play=new MenuButton("Memo Game");
        menuButtons.add(play);
        getChildren().add(play);
        play.setLayoutX(370);
        play.setLayoutY(40+menuButtons.size()*70);

    }
    public  void createMineSweeper(){
        MenuButton play=new MenuButton("MineSweeper");
        menuButtons.add(play);
        getChildren().add(play);
        play.setLayoutX(370);
        play.setLayoutY(40+menuButtons.size()*70);

    }
    public  void createFlapyBird(){
        MenuButton play=new MenuButton("FlappyBird");
        menuButtons.add(play);
        getChildren().add(play);
        play.setLayoutX(370);
        play.setLayoutY(40+menuButtons.size()*70);

    }
    public  void createSpaceInvaders(){
        MenuButton play=new MenuButton("SpaceInvaders");
        menuButtons.add(play);
        getChildren().add(play);
        play.setLayoutX(370);
        play.setLayoutY(40+menuButtons.size()*70);

    }
    private void createScoreButton(){
        MenuButton score=new MenuButton("Scores");
        menuButtons.add(score);
        getChildren().add(score);
        score.setLayoutX(370);
        score.setLayoutY(40+menuButtons.size()*70);
    }
    private void createExitButton(){
        MenuButton exit=new MenuButton("Exit");
        menuButtons.add(exit);
        getChildren().add(exit);
        exit.setLayoutX(370);
        exit.setLayoutY(40+menuButtons.size()*70);
    }
    private  void createscoreSubScene(){
        scoreSubScene=new MenuSubScene();
        CloseButton closeButton=new CloseButton();
        closeButton.setLayoutX(470);
        closeButton.setLayoutY(5);
        closeButton.setOnMouseClicked(mouseEvent -> scoreSubScene.hideSubScene());
        scoreSubScene.getPane().getChildren().add(closeButton);
        createScoresTableView();

    }
    private void createScoresTableView(){
        TableView tableView=new TableView();

        tableView.getStylesheets().add(getClass().getResource("ressources/style.css").toExternalForm());




        TableColumn<Player,String> name=new TableColumn<>("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setCellFactory(new Callback<TableColumn<Player, String>, TableCell<Player, String>>() {
            @Override
            public TableCell<Player, String> call(TableColumn<Player, String> playerStringTableColumn) {
                return new TableCell<>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(isEmpty())
                        {
                            setText("");
                        }
                        else
                        {

                            try {
                                setFont(Font.loadFont(new FileInputStream("src/view/ressources/acknowtt.ttf"),18));
                            }catch (FileNotFoundException e){
                                setFont(Font.font("Verdana",16));
                            }
                            setText(item);
                        }

                    }
                };
            }
        });



        TableColumn<Player,String> diff=new TableColumn<>("DIFFICULTY");
        diff.setCellFactory(new Callback<TableColumn<Player, String>, TableCell<Player, String>>() {
            @Override
            public TableCell<Player, String> call(TableColumn<Player, String> playerStringTableColumn) {
                return new TableCell<>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(isEmpty())
                        {
                            setText("");
                        }
                        else
                        {

                            try {
                                setFont(Font.loadFont(new FileInputStream("src/view/ressources/acknowtt.ttf"),18));
                            }catch (FileNotFoundException e){
                                setFont(Font.font("Verdana",16));
                            }
                            setText(item);
                        }

                    }
                };
            }
        });
        diff.setCellValueFactory(new PropertyValueFactory<>("diff"));



        TableColumn<Player,String> grid=new TableColumn<>("GRID");
        grid.setCellValueFactory(new PropertyValueFactory<>("grid"));
        grid.setCellFactory(new Callback<TableColumn<Player, String>, TableCell<Player, String>>() {
            @Override
            public TableCell<Player, String> call(TableColumn<Player, String> playerStringTableColumn) {
                return new TableCell<>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(isEmpty())
                        {
                            setText("");
                        }
                        else
                        {

                            try {
                                setFont(Font.loadFont(new FileInputStream("src/view/ressources/acknowtt.ttf"),18));
                            }catch (FileNotFoundException e){
                                setFont(Font.font("Verdana",16));
                            }
                            setText(item);
                        }

                    }
                };
            }
        });



        TableColumn<Player,String> time=new TableColumn<>("TIME");
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        time.setCellFactory(new Callback<TableColumn<Player, String>, TableCell<Player, String>>() {
            @Override
            public TableCell<Player, String> call(TableColumn<Player, String> playerStringTableColumn) {
                return new TableCell<>(){
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(isEmpty())
                        {
                            setText("");
                        }
                        else
                        {

                            try {
                                setFont(Font.loadFont(new FileInputStream("src/view/ressources/acknowtt.ttf"),18));
                            }catch (FileNotFoundException e){
                                setFont(Font.font("Verdana",16));
                            }
                            setText(item);
                        }

                    }
                };
            }
        });



        tableView.getColumns().addAll(name,diff,grid,time);
        tableView.setPrefWidth(480);
        tableView.setLayoutX(10);
        tableView.setLayoutY(40);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        scoreSubScene.getPane().getChildren().add(tableView);


        PlayerDatabase database=new PlayerDatabase();

        try {
            database.connect();
            Statement stmt=database.getConnection().createStatement();
            ResultSet rs=stmt.executeQuery("select name,difficulty,grid,time from players");
            while (rs.next()) {
                Player p=new Player(rs.getString("name"),rs.getString("difficulty"),rs.getString("grid"),rs.getString("time")) ;
                tableView.getItems().add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private  void createplaySubScene(){
        playSubScene=new MenuSubScene();
        createDiffChooser();

    }

    private void createDiffChooser(){
        List<MenuButton> diffButtons=new ArrayList<>();
        playSubScene.getPane().getChildren().clear();
        MenuButton easyButton=new MenuButton("easy");
        MenuButton normalButton=new MenuButton("normal");
        MenuButton hardButton=new MenuButton("hard");
        MenuButton smallGridButton=new MenuButton("3x4");
        MenuButton averageGridButton=new MenuButton("4x5");
        MenuButton bigGridButton=new MenuButton("6x5");
        gridButtons.add(smallGridButton);
        gridButtons.add(averageGridButton);
        gridButtons.add(bigGridButton);
        diffButtons.add(easyButton);
        diffButtons.add(normalButton);
        diffButtons.add(hardButton);
        easyButton.setLayoutX(125);
        easyButton.setLayoutY(130);
        normalButton.setLayoutX(125);
        hardButton.setLayoutX(125);
        normalButton.setLayoutY(200);
        hardButton.setLayoutY(270);
        playSubScene.getPane().getChildren().add(easyButton);
        playSubScene.getPane().getChildren().add(normalButton);
        playSubScene.getPane().getChildren().add(hardButton);
        SubSceneLabel subSceneLabel=new SubSceneLabel("select Difficulty");
        MenuButton returnButton=new MenuButton("Return",50,250);
        returnButton.setLayoutX(200);
        returnButton.setLayoutY(415);
        returnButton.setButtonFont(33);
        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                playSubScene.hideSubScene();
            }
        });
        playSubScene.getPane().getChildren().add(subSceneLabel);
        playSubScene.getPane().getChildren().add(returnButton);
        easyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                choosenDifficulty= Difficulty.EASY;
                createGridChooser();
            }
        });
        normalButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                choosenDifficulty= Difficulty.normal;
                createGridChooser();
            }
        });
        hardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                choosenDifficulty= Difficulty.hard;
                createGridChooser();
            }
        });
        for (MenuButton mb:diffButtons
             ) {
            mb.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Glow glow=new Glow(1);
                    mb.setEffect(glow);
                }
            });
            mb.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mb.setEffect(null);
                }
            });
        }
    }
    private void createGridChooser(){
        playSubScene.getPane().getChildren().clear();
        SubSceneLabel subSceneLabel=new SubSceneLabel("select Grid");
        MenuButton returnButton=new MenuButton("Return",50,250);
        returnButton.setLayoutX(200);
        returnButton.setLayoutY(415);
        returnButton.setButtonFont(33);
        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                playSubScene.hideSubScene();
            }
        });

        gridButtons.get(0).setLayoutX(125);
        gridButtons.get(0).setLayoutY(130);
        gridButtons.get(1).setLayoutX(125);
        gridButtons.get(2).setLayoutX(125);
        gridButtons.get(1).setLayoutY(200);
        gridButtons.get(2).setLayoutY(270);
        playSubScene.getPane().getChildren().add(gridButtons.get(0));
        playSubScene.getPane().getChildren().add(gridButtons.get(1));
        playSubScene.getPane().getChildren().add(gridButtons.get(2));
        playSubScene.getPane().getChildren().add(subSceneLabel);
        playSubScene.getPane().getChildren().add(returnButton);

        for (MenuButton mb:gridButtons
             ) {
            mb.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Glow glow=new Glow(1);
                    mb.setEffect(glow);
                }
            });
            mb.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mb.setEffect(null);
                }
            });
        }


    }
    private void setButtonEffectEntered(){
        for (MenuButton mb:menuButtons
        ) {
            mb.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Glow glow=new Glow(1);
                    mb.setEffect(glow);
                }
            });
            mb.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mb.setEffect(null);
                }
            });
        }
    }

    private void initMouseListener(){
        menuButtons.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createDiffChooser();
                playSubScene.showSubScene();
            }
        });
        menuButtons.get(4).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
              scoreSubScene.showSubScene();
            }
        });

    }

    public Difficulty getChoosenDifficulty() {
        return choosenDifficulty;
    }

    public List<MenuButton> getGridButtons() {
        return gridButtons;
    }
}
