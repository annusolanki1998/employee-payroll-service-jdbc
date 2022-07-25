package com.bridgelabz;

import java.sql.*;

public class EmployeePayrollService {
    public static void main(String[] args) throws SQLException {
        System.out.println("welcome to employee payroll data base connection ");

        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service";
        String userName = "root";
        String password = "12345";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("Connection done...");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SUM(basic_pay), AVG(basic_pay), MAX(basic_pay), MIN(basic_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender ");
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery("SELECT  COUNT(gender) from employee_payroll WHERE gender = 'F' GROUP BY gender");

            while (resultSet.next()) {
                System.out.println("Sum of all basic salary are: " + resultSet.getDouble("SUM(basic_pay)") + "\n" + "Average of all basic salary is: " + resultSet.getDouble("AVG(basic_pay)") + "\n" + "Minimum in between basic salary is: " + resultSet.getDouble("MIN(basic_pay)") + "\n" + "Maximum in between basic salary is: " + resultSet.getDouble("MAX(basic_pay)"));
            }
            while (resultSet1.next()) {
                System.out.println("NO of female are: " + resultSet1.getInt("COUNT(gender)"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
