package org.example.daos;

import org.example.models.Employee;
import org.example.models.EmployeeRequest;
import org.example.models.SalesEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class EmployeeDao {
    private final int three = 3;
    private final int four = 4;

    //get all employees
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT EmployeeID, Name, Salary,"
                            + "BankAccountNumber, NationalInsuranceNumber"
                            + " FROM Employee ");

            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("EmployeeID"),
                        resultSet.getString("Name"),
                        resultSet.getBigDecimal("salary"),
                        resultSet.getInt("BankAccountNumber"),
                        resultSet.getInt("NationalInsuranceNumber"));


                employees.add(employee);
            }
        }

        return employees;
    }



    //create employee
    public int createEmployee(final EmployeeRequest employee)
            throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement =
                "INSERT INTO Employee (Name, Salary, "
                        +
                        "BankAccountNumber,"
                        +
                        "NationalInsuranceNumber) VALUES (?,?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement,
                Statement.RETURN_GENERATED_KEYS);

        st.setString(1, employee.getEmployeeName());
        st.setBigDecimal(2, employee.getSalary());
        st.setInt(three, employee.getBankAccountNumber());
        st.setInt(four, employee.getNationalInsuranceNumber());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);

        }
        return -1;

    }


    //create delivery emp
    public int createDeliveryEmployee(final int id) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement =
                "INSERT INTO DeliveryEmployee ( EmployeeID ) VALUES (?)";
        PreparedStatement st = c.prepareStatement(insertStatement,
                Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, id);


        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);

        }
        return -1;

    }


    //create sales employee
    public int createSalesEmployee(final int id) throws SQLException {
        Connection c = DatabaseConnector.getConnection();

        String insertStatement =
                "INSERT INTO SalesEmployee ( EmployeeID ) VALUES (?)";
        PreparedStatement st = c.prepareStatement(insertStatement,
                Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, id);


        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);

        }
        return -1;
    }





    //get all sales employees
    public List<SalesEmployee> getAllSalesEmployee() throws SQLException {
        List<SalesEmployee> salesEmployees = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT EmployeeID, SalesEmployee.SalesID, Commission"
                            +
                            " FROM Employee "
                            +
                            "join SalesEmployee using (EmployeeID);");

            while (resultSet.next()) {
                SalesEmployee salesEmployee = new SalesEmployee(
                        resultSet.getInt("EmployeeID"),
                        resultSet.getInt("SalesID"),
                        resultSet.getBigDecimal("Commission"));


                salesEmployees.add(salesEmployee);
            }
        }

        return salesEmployees;
    }


    public Employee getEmployeeById(final int id) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT EmployeeID, Name, Salary, "
                    + "BankAccountNumber, NationalInsuranceNumber "
                    + "FROM Employee WHERE EmployeeID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Employee(
                        resultSet.getInt("EmployeeID"),
                        resultSet.getString("Name"),
                        resultSet.getBigDecimal("Salary"),
                        resultSet.getInt("BankAccountNumber"),
                        resultSet.getInt("NationalInsuranceNumber")
                );
            }
        }
        return null;
    }

    public void updateEmployee(final int id,
                               final EmployeeRequest employeeRequest)
            throws SQLException {
        Connection connection = DatabaseConnector.getConnection();

        String updateStatement = "UPDATE Employee SET Name = ?"
                + ",Salary = ?, BankAccountNumber = ?, "
                + "NationalInsuranceNumber = ? "
                + "WHERE EmployeeID = ?";
        PreparedStatement st = connection.prepareStatement(updateStatement);

        st.setString(1, employeeRequest.getEmployeeName());
        st.setBigDecimal(2, employeeRequest.getSalary());
        st.setInt(three, employeeRequest.getBankAccountNumber());
        st.setInt(four, employeeRequest.getNationalInsuranceNumber());
        st.setInt(5, id);

        st.executeUpdate();
    }
}
