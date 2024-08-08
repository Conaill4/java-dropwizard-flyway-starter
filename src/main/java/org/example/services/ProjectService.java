package org.example.services;

import org.example.daos.ProjectDao;
import org.example.models.ProjectRequest;

import java.sql.SQLException;


public class ProjectService {
    ProjectDao projectDao;

    public ProjectService(final ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public int createProject(final ProjectRequest projectrequest)
            throws  SQLException {

        int id = projectDao.createProject(projectrequest);

        if (id == -1) {
            throw new SQLException();
        }
        return id;
    }
}
