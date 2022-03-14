package core;



import org.apache.log4j.Logger;

import java.sql.*;

public class DataBaseService {
public  static Logger logger = Logger.getLogger(DataBaseService.class);

    static final String DB_URL = "jdbc:postgresql://ec2-34-255-225-151.eu-west-1.compute.amazonaws.com:5432/da0p1ses73mop";
    static final String USER = "titxhrvjgtgwwe";
    static final String PASSWORD = "caff8a0253e69a00a82cbd7b6058b75d4f1da8a22998f1496e4eea2674426b2a";

    Connection connection = null;
    Statement statement = null;

    public DataBaseService() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.fatal(e.toString());
            return;
        }

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error(e.toString());
            return;
        }

        if (connection != null) {
            logger.info("Мы подключились к БД...");
        }
    }

    public Statement getStatement() {
        try {
            if (statement == null) {
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return statement;
    }

    public void executeSQL(String sql) {
        try {
            logger.info("Результат выполнения запроса: " + getStatement().execute(sql));
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            return getStatement().executeQuery(sql);
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return null;
    }

    public void closeStatement() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.info(e.toString());
            return;
        }
    }
}
