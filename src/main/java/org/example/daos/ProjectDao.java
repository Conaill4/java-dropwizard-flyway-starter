package org.example.daos;

import org.checkerframework.checker.units.qual.C;
import org.example.models.Client;
import org.example.models.DeliveryEmployee;
import org.example.models.Employee;
import org.example.models.Project;
import org.example.models.ProjectRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

    public int createProject(final ProjectRequest project) throws SQLException {
        Connection c = DatabaseConnector.getConnection();


        String insertStatement =
                "INSERT INTO Project(Name, IsCompleted, ClientId, `Value`,TechLeadId) VALUES (?,?,?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement,
                Statement.RETURN_GENERATED_KEYS);

        st.setString(1, project.getProjectName());
        st.setBoolean(2, project.getIsCompleted());
        st.setInt(1, project.getClientId());
        st.setInt(1, project.getValue());
        st.setInt(1, project.getTechLeadId());


        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);

        }
        return -1;

    }

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT ProjectId, DeliveryID, Name,TechLeadID,isCompleted "
                            +
                            "FROM Project JOIN WorksOn"
                            +
                            " Using (ProjectID)  "
                            +
                            "where DeliveryID = ?"
                            +
                            "AND IsCompleted = 'false'");


            while (resultSet.next()) {
                Project project = new Project(
                        resultSet.getInt("ProjectId"),
                        resultSet.getString("Name"),
                        new DeliveryEmployee(resultSet.getInt("DeliveryEmployeeID"), new Employee()),
                        resultSet.getString("Name");

                projects.add(project);
            }
        }
        return projects;
    }


    //update
    public void updateProject(final int id, final ProjectRequest project)
            throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String updateStatement =
                "UPDATE project SET isCompleted = ?"
                        +
                        "WHERE ProductID = ?";
        PreparedStatement st = c.prepareStatement(updateStatement);
        st.setInt(1, project.getProjectId());
        st.setBoolean(2, project.getIsCompleted());

        st.executeUpdate();

    }

    public Project getProjectById(final int id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query =
                    //join for client info
                    "SELECT ProjectId, Name, IsCompleted,ClientID,'Value', TechLeadID, "
                            +
                            "FROM Project JOIN `Client`"
                            +
                            "USING (ClientID) where ProjectID = ?;";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Project(
                        resultSet.getInt("ProjectId"),
                        resultSet.getString("Name"),
                        resultSet.getInt("Value"),
                        new Client(resultSet.getInt("ClientID"),
                        resultSet.getBoolean("IsCompleted"),
                        new DeliveryEmployee(resultSet.getInt("DeliveryEmployeeId"),
                                new Employee());


            }
            return null;
        }
    }
}





