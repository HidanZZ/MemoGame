package sample.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDatabase {
    private Connection connection;
    public PlayerDatabase() {
    }
    public void connect(){
        try {
            // db parameters
            String url = "jdbc:sqlite:src/sample/model/playersScores.db";
            // create a connection to the database
            connection = DriverManager.getConnection(url);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void disconnect(){
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public List<ResultSet> getRowList(){
        List<ResultSet> resultSets=new ArrayList<>();
        String query ="select * from players ";
        try {
            connect();
            Statement stmt  = connection.createStatement();
            ResultSet rs    = stmt.executeQuery(query);

            // loop through the result set
            while (rs.next()) {
                resultSets.add(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();
        return resultSets;
    }
    public void addRow(String playerName,String difficulty,String grid,String time){
        String sql = "INSERT INTO players(name,difficulty,grid,time) VALUES(?,?,?,?)";

        try{
            connect();
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,playerName);
            pstmt.setString(2,difficulty);
            pstmt.setString(3,grid);
            pstmt.setString(4,time);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        disconnect();
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        PlayerDatabase playerDatabase=new PlayerDatabase();
        playerDatabase.addRow("test","hard","5x5","00:00");
    }


        }



