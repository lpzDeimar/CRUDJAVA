package org.example.lpz.main;
import org.example.lpz.model.Employee;
import org.example.lpz.repository.EmployeeRepository;
import org.example.lpz.repository.Repository;
import org.example.lpz.util.DatabaseConnection;

import java.sql.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {

        try (Connection connection = DatabaseConnection.getInstance()){
            Repository<Employee> repository = new EmployeeRepository();

            repository.findAll().forEach(System.out::println);
        }
    }
}