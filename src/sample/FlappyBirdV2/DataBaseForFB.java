package sample.FlappyBirdV2;

import  java.sql.*;
public class DataBaseForFB {
    String name;
    int score ;
    String sql = "" ;
    int highScore = 0 ;
    public DataBaseForFB(String name , int score , boolean insert ) {
        try {
            Connection c = null;
            Statement stmt = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/sample/model/playersScores.db");
            c.setAutoCommit(true);
            stmt = c.createStatement();

            this.name = name;
            this.score = score;
            if (insert){
                sql = "INSERT INTO FlappyBird (s_name,score) " + "VALUES ('" + name + "'," + score + ")";
                stmt.executeUpdate(sql);
            }
            ResultSet rsA = stmt.executeQuery("SELECT MAX (score) as highScore  FROM FlappyBird where s_name = '"+name+"';");
            highScore =  rsA.getInt("highScore");
            ResultSet rs = stmt.executeQuery("SELECT *  FROM FlappyBird;");
            while ( rs.next() ) {
                int id = rs.getInt("p_id");
                String nameA = rs.getString("s_name");
                int scoreA = rs.getInt("score");
            }
            rs.close();
            stmt.close();
            c.close();
        }catch (Exception e ){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
