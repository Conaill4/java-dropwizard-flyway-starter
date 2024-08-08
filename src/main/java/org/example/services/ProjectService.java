package org.example.services;

import org.example.daos.ProjectDao;
import org.example.models.Project;
import org.example.models.ProjectRequest;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    ProjectDao projectDao;

    public ProjectService(final ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public List<Project> getAllProjects() throws SQLException {
        // throw new SQLException();
        //return ProjectMapper.mapOrderListToOrderResponseList(
                //projectDao.getAllProjects();
        // return orderDao.getAllOrders();
        return projectDao.getAllProjects();
    }

    public int createProject(final ProjectRequest projectrequest)
            throws  SQLException {

        int id = projectDao.createProject(projectrequest);

        if (id == -1) {
            throw new SQLException();
        }
        return id;
    }

    public void updateProduct(final int id, final ProjectRequest projectRequest)
            throws  SQLException {
        Project projectToUpdate = projectDao.getProjectById(id);
        if (projectToUpdate == null) {
            throw new SQLException();
        }
        projectDao.updateProject(id, projectRequest);

    }

}
