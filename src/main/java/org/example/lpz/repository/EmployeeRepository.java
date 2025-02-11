package org.example.lpz.repository;

import org.example.lpz.model.Employee;
import org.example.lpz.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee>{

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List <Employee> employees = new ArrayList<>();
        try(Statement myStmt = getConnection().createStatement();
            ResultSet myRs  = myStmt.executeQuery("SELECT * FROM employees")) {
            while (myRs.next()) {
                Employee e = createEmployee(myRs);
                employees.add(e);
            }
        }
        return employees;
    }

    @Override
    public Employee getById(Integer id) throws SQLException {
        Employee employee = null;
        try(PreparedStatement myStmt = getConnection().prepareStatement("SELECT * FROM employees WHERE id = ?")){
            myStmt.setInt(1, id);
            try(ResultSet myRs = myStmt.executeQuery()){
                if(myRs.next()){
                    employee = createEmployee(myRs);
                }
            }
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {
        try(PreparedStatement myStmt = getConnection().prepareStatement("INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary) VALUES (?, ?, ?, ?, ?)")){
            myStmt.setString(1, employee.getFirst_name());
            myStmt.setString(2, employee.getPa_surname());
            myStmt.setString(3, employee.getMa_surname());
            myStmt.setString(4, employee.getEmail());
            myStmt.setFloat(5, employee.getSalary());
            myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        try(PreparedStatement myStmt = getConnection().prepareStatement("UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ? WHERE id = ?")){
            myStmt.setString(1, employee.getFirst_name());
            myStmt.setString(2, employee.getPa_surname());
            myStmt.setString(3, employee.getMa_surname());
            myStmt.setString(4, employee.getEmail());
            myStmt.setFloat(5, employee.getSalary());
            myStmt.setInt(6, employee.getId());
            myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try(PreparedStatement myStmt = getConnection().prepareStatement("DELETE FROM employees WHERE id = ?")){
            myStmt.setInt(1, id);
            myStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Employee createEmployee(ResultSet myRs) throws SQLException {
        Employee e = new Employee();
        e.setId(myRs.getInt("id"));
        e.setFirst_name(myRs.getString("first_name"));
        e.setPa_surname(myRs.getString("pa_surname"));
        e.setMa_surname(myRs.getString("ma_surname"));
        e.setEmail(myRs.getString("email"));
        e.setSalary(myRs.getFloat("salary"));
        return e;
    }
}
