package org.example.daos;

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

        st.setString(1, project.getName());
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
                    "SELECT ProjectId, DeliveryID, Name,TechLead,isCompleted "
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
                        resultSet.getInt("ProductID"),
                        new DeliveryEmployee(
                                resultSet.getInt("DeliveryEmployeeId"),
                                resultSet.getString("Name"),
                                resultSet.getString("Description"),
                                resultSet.getDouble("Price")));

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
        st.setInt(1, project.getProjectID());
        st.setBoolean(2, project.getIsCompleted());

        st.executeUpdate();

    }

}

    /*

    public Project getProjectById(final int id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query =
                    "SELECT ProjectId, DeliveryID, Name,TechLead, "
                            +
                            "FROM Project JOIN WorksOn"
                            +
                            " Using (ProjectID)  where DeliveryID = ?;";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Project(
                        resultSet.getInt("ProductID"),
                        new DeliveryEmployee(resultSet.getInt("DeliveryEmployeeID"),
                        resultSet.getString("Name"),
                        resultSet.getInt("TechLead")));

            }
            return null;
        }
    }

     */




