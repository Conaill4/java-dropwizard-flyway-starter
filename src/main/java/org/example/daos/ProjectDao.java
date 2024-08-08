package org.example.daos;


import org.example.models.ProjectRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectDao {

    public int createProject(final ProjectRequest project) throws SQLException {
        Connection c = DatabaseConnector.getConnection();


        String insertStatement =
                "INSERT INTO Project(Name, IsCompleted, "
                        +
                        "ClientId, `Value`,"
                        +
                        "TechLeadId) VALUES (?,?,?,?,?)";

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
}
