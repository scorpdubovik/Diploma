package steps;

import core.DataBaseService;
import dbEntries.ProjectTable;
import models.ProjectBuilder;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_ProjectSteps {
    private ProjectTable projectTable;
    public ProjectBuilder newProject;

    public static Logger logger = Logger.getLogger(DB_ProjectSteps.class);

    public void createProjectTable(DataBaseService dataBaseService) {
        ProjectTable projectTable = new ProjectTable(dataBaseService);
        projectTable.dropTable();
        projectTable.createTable();
        projectTable.addProject("Kanye", "West", "123");
    }

    public ProjectBuilder createAddProject(DataBaseService dataBaseService, int idProject) {
        projectTable = new ProjectTable(dataBaseService);

        ResultSet rs = projectTable.getProjectByID(idProject);

        try {
            while (rs.next()) {
                String nameName = rs.getString("name");
                String code = rs.getString("code");
                String description = rs.getString("description");

                logger.info("name: " + nameName);
                logger.info("code: " + code);
                logger.info("description: " + description);

                newProject = new ProjectBuilder.Builder()
                        .withName(nameName)
                        .withCode(code)
                        .withDescription(description)
                        .build();
            }

            } catch(SQLException e){
                e.printStackTrace();
            }
            return newProject;
    }
}


