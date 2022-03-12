package core;



import org.apache.log4j.Logger;

import java.sql.*;

public class DataBaseService {
public  static Logger logger = Logger.getLogger(DataBaseService.class);

    static final String DB_URL = "jdbc:postgresql://ec2-34-247-151-118.eu-west-1.compute.amazonaws.com:5432/dbai3bjh7nnqm4";
    static final String USER = "nwutlbqescsfje";
    static final String PASSWORD = "ae5c109d422132a3cb031e93c1c5a3d4a2e28f89edfd14866273ae9ab8083964";

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
