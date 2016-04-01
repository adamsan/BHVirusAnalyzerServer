package hu.adamsan.bionica.competition.server.dao.sqlite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDAOManager {

    private static final String DB_URL = "jdbc:sqlite:results.db";
    private static boolean runInit = false;

    public static Connection getConnection() {
        if (!runInit) {
            initSQLiteDB();
        }
        try {
                Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Can't get connection to sqlite database");
    }

    private static void initSQLiteDB() {
        runInit = true;
        try (InputStream is = SQLiteDAOManager.class.getResourceAsStream("/create_sqlite.sql");
                InputStreamReader ir = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(ir);) {
            String sql = "";
            String line = "";
            while ((line = br.readLine()) != null) {
                sql += line;
                if (line.contains(";")) {
                    runStateMent(sql);
                    sql = "";
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runStateMent(String sql) {
        System.out.println("Running sql:\n" + sql);
        try (Connection con = getConnection();
                Statement stmt = con.createStatement();) {
            stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
