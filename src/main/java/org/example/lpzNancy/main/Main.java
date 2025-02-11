package org.example.lpzNancy.main;
import org.example.lpzNancy.model.Employee;
import org.example.lpzNancy.repository.EmployeeRepository;
import org.example.lpzNancy.repository.Repository;
import org.example.lpzNancy.util.DatabaseConnection;

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