package sample.MineSweeperGame;

import java.sql.*;

public class dataBaseCreator {
    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:MineSweeperDB.db");
            System.out.println("Database Opened...\n");
            stmt = c.createStatement();
            String sql = "CREATE TABLE Score " +

                    "(p_id INTEGER PRIMARY KEY AUTOINCREMENT," +

                    " s_name TEXT NOT NULL, " +

                    " score REAL NOT NULL, " +

                    " nbrBombs REAL NOT NULL); " ;
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

}
