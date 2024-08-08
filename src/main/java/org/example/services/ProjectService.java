package org.example.services;

import org.example.daos.ProjectDao;

import java.sql.SQLException;
import java.util.List;

public class ProjectService {
    ProjectDao projectDao;

    public ProjectService(final ProjectDao projectDao) {
        this.projectDao = projectDao;
        // this.customerDao = customerDao;
    }

    public List<ProjectResponse> getAllProjects() throws SQLException {
        // throw new SQLException();
        return ProjectMapper.mapOrderListToOrderResponseList(
                projectDao.getAllProjects());
        // return orderDao.getAllOrders();
    }

    public int createProject(final Projectrequest projectrequest)
            throws FailedToCreateException, SQLException, InvalidException {

        int id = projectDao.createProject(projectrequest);


        if (id == -1) {
            throw new FailedToCreateException(Entity.PROJECT);
        }
        return id;
    }

}
