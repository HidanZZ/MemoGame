package sample.MineSweeperGame;

import  java.sql.*;
public class DataBaseForMSs {
    String name;
    int score ;
    String sql = "" ;
    int highScore = 0 ;
    public DataBaseForMSs(String name , int score , boolean insert , int nbrBombs) {
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
                sql = "INSERT INTO Score (s_name,score,nbrBombs) " + "VALUES ('" + name + "'," + score + ","+ nbrBombs + ")";
                stmt.executeUpdate(sql);
            }
            ResultSet rsA = stmt.executeQuery("SELECT MIN (score) as highScore  FROM Score where s_name = '"+name+"';");
            highScore =  rsA.getInt("highScore");
            ResultSet rs = stmt.executeQuery("SELECT *  FROM Score;");
            while ( rs.next() ) {
                int id = rs.getInt("p_id");
                String nameA = rs.getString("s_name");
                int scoreA = rs.getInt("score");
                int nbrBombsA = rs.getInt("nbrBombs");
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
