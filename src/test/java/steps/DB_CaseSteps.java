package steps;

import core.DataBaseService;
import dbEntries.CaseTable;
import dbEntries.ProjectTable;
import models.CaseBuilder;
import models.ProjectBuilder;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_CaseSteps {
    private CaseTable caseTable;
    public CaseBuilder newCase;

    public static Logger logger = Logger.getLogger(DB_CaseSteps.class);

    public void createCaseTable(DataBaseService dataBaseService) {
        CaseTable caseTable = new CaseTable(dataBaseService);
        caseTable.dropTable();
        caseTable.createTable();
        caseTable.addCase("Authorization", " http://example.com/login");
    }

    public CaseBuilder createAddCase(DataBaseService dataBaseService, int idCase) {
        caseTable = new CaseTable(dataBaseService);

        ResultSet rs1 = caseTable.getCaseByID(idCase);

        try {
            while (rs1.next()) {
                String title = rs1.getString("title");
                String description = rs1.getString("description");

                logger.info("name: " + title);
                logger.info("description: " + description);

                newCase = new CaseBuilder.Builder()
                        .withTitle(title)
                        .withDescription(description)
                        .build();
            }

            } catch(SQLException e){
                e.printStackTrace();
            }
            return newCase;
    }
}


