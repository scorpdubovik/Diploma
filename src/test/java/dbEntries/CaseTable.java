package dbEntries;

import core.DataBaseService;
import org.testng.log4testng.Logger;

import java.sql.ResultSet;


public class CaseTable {
    public static Logger logger = Logger.getLogger(CaseTable.class);

    DataBaseService dataBaseService;

    public CaseTable(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void createTable() {
        logger.info("Создаем таблицу case");

        String createTableSQL = "CREATE TABLE cases (" +
                "id SERIAL PRIMARY KEY, " +
                "title CHARACTER VARYING(30), " +
                "description CHARACTER VARYING(30) " +
                ");";

        dataBaseService.executeSQL(createTableSQL);
    }

    public void dropTable() {
        logger.info("Удаляем таблицу case");

        String dropTableCaseSQL = "DROP TABLE cases;";

        dataBaseService.executeSQL(dropTableCaseSQL);
    }

    public ResultSet getCaseByID(int id) {
        String sql = "SELECT * FROM cases WHERE id = " + id + ";";

        return dataBaseService.executeQuery(sql);
    }

    public void addCase(String title, String description) {
        logger.info("Добавляем запись в таблицу case");

        String insertTableSQL = "INSERT INTO public.cases(" +
                "title, description)" +
                "VALUES ('" + title + "', '" + description +"');";

        dataBaseService.executeSQL(insertTableSQL);
    }
}
