package doob.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {


    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306";
    public static Connection connection;
    public static Statement statement;

    public Connection getConnection() throws SQLException {
        return connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

    public Statement getStatement() throws SQLException {
        return statement =  getConnection().createStatement();
    }

}

