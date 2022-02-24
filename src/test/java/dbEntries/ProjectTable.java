package dbEntries;

import core.DataBaseService;
import org.testng.log4testng.Logger;

import java.sql.ResultSet;


public class ProjectTable {
    public static Logger logger = Logger.getLogger(ProjectTable.class);

    DataBaseService dataBaseService;

    public ProjectTable(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void createTable() {
        logger.info("Создаем таблицу  Project");

        String createTableSQL = "CREATE TABLE project (" +
                "id SERIAL PRIMARY KEY, " +
                "name CHARACTER VARYING(30), " +
                "code CHARACTER VARYING(30), " +
                "description CHARACTER VARYING(30) " +
                ");";

        dataBaseService.executeSQL(createTableSQL);
    }

    public void dropTable() {
        logger.info("Удаляем таблицу project");

        String dropTableProjectSQL = "DROP TABLE project;";

        dataBaseService.executeSQL(dropTableProjectSQL);
    }

    public ResultSet getProjectByID(int id) {
        String sql = "SELECT * FROM project WHERE id = " + id + ";";

        return dataBaseService.executeQuery(sql);
    }

    public void addProject(String name, String code, String description) {
        logger.info("Добавляем запись в таблицу project");

        String insertTableSQL = "INSERT INTO public.project(" +
                "name, code, description)" +
                "VALUES ('" + name + "', '" + code+"', '" + description+"');";

        dataBaseService.executeSQL(insertTableSQL);
    }
}
