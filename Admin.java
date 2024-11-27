/*
This class uses two methods:one to remove and one to add employees to the employees array.
addEmployee simply uses .add() to add an employee to the array. RemoveEmployee uses a for loop to
go through the array and find an ID that matches the given ID. It also has a getEmployees method so you can make sure
the add/remove worked correctly.

@author Michal Czekalski
 */



import java.io.*;
import java.util.ArrayList;
public class Admin {
    public Admin() {
    }

    public static ArrayList<Employee> employees = new ArrayList<Employee>();// Static employee list
    Deductions deductions;
    Payslip payslip;
    Employee employee;
    String[] values;

    public void LoadCSV() {
        //String path = "employees.csv";
        String pathFT = "EmployeesPartTime.csv"; // path for full time employees csv
        String pathPT = "EmployeesFullTime.csv"; // path for part time employees csv

        String line = "";
        boolean firstLine = true;

        try {
            //loading the csv file with sample full time employees
            BufferedReader br1 = new BufferedReader(new FileReader(pathFT)); //full time csv
            while ((line = br1.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;  // Set flag to false after processing the header
                    continue;  // Skip this iteration (header row)
                }
                values = line.split(",");
                if (values.length == 4) {
                    String employeeID = values[0];
                    String name = values[1];
                    String position = values[2];
                    int currentPoint = Integer.parseInt(values[3]);
                    employee = new Employee(employeeID, name, position, currentPoint);
                    employees.add(employee);
                }

                //loading the csv file with sample part time employees
                BufferedReader br2 = new BufferedReader(new FileReader(pathPT)); //part time csv
                while ((line = br1.readLine()) != null) {
                    if (firstLine) {
                        firstLine = false;  // Set flag to false after processing the header
                        continue;  // Skip this iteration (header row)
                    }
                    values = line.split(",");
                    if (values.length == 3) {
                        String employeeID = values[0];
                        String name = values[1];
                        String position = values[2];
                        employee = new Employee(employeeID, name, position);
                        employees.add(employee);
                    }
                    System.out.println("CSV loaded.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect path name used");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to add an employee
    public void addEmployee(Employee employee) {
        employees.add(employee);// Add employee to the list
        System.out.println("Employee " + employee.getName() + " added");
        CreateEmployeeCSV(employee);// creates a employees personal csv
    }

    // Method to remove an employee by their employee ID
    public void removeEmployee(String employeeID) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID().equals(employeeID)) {
                employees.remove(i);
                // Remove employee by ID
                System.out.println("Employee " + employeeID + " removed.");
                i--;
                return;
            }
        }
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static void setEmployees(ArrayList<Employee> employees) {
        Admin.employees = employees;
    }

    public void updateCSV() {
        String filePathFT = "EmployeesFullTime.csv";
        String filePathPT = "EmployeesPartTime.csv";

        if (employee.getIsFullTime()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathFT))) {
                // Write the header row (if needed)
                try {
                    writer.write("EmployeeID,Name,Position, CurrentPoint");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                writer.newLine();

                // Iterate through the list of employees and write each employee's data
                for (Employee employee : employees) {
                    writer.write(employee.getEmployeeID() + "," + employee.getName() + "," + employee.getPosition() + "," + employee.getCurrentPoint());
                    writer.newLine();
                }

                System.out.println("CSV file updated successfully.");

            } catch (IOException e) {
                System.out.println("Error writing to the CSV file: " + e.getMessage());
            }
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePathPT))) {
                // Write the header row (if needed)
                try {
                    writer.write("EmployeeID,Name,Position");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                writer.newLine();

                // Iterate through the list of employees and write each employee's data
                for (Employee employee : employees) {
                    writer.write(employee.getEmployeeID() + "," + employee.getName() + "," + employee.getPosition());
                    writer.newLine();
                }

                System.out.println("CSV file updated successfully.");

            } catch (IOException e) {
                System.out.println("Error writing to the CSV file: " + e.getMessage());
            }
        }
    }


    public void CreateEmployeeCSV(Employee employee) {
        String filename = employee.getEmployeeID() + "information.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ID , Name, Position, SalaryPoint, isFullTime ");
            writer.newLine();
            writer.write(employee.getEmployeeID() + "," + employee.getName() + "," + employee.getPosition() + "." + employee.getCurrentPoint() + ","
                    + employee.getIsFullTime());// writes all the employees details in order of the parameters
            writer.newLine();

            writer.write("This Epmloyees Payslips");
            writer.newLine();
            writer.write("GrossPay, NetPay,  date");
            writer.newLine();


            for (Payslip payslip : employee.getPayslips()) {
                writer.write(deductions.grossMonthlyPay + "," + deductions.netPay + "," + payslip.todaysDate);
                writer.newLine();
            }
            System.out.println("CSV created for Employee: " + employee.getName());
        } catch (IOException e) {
            System.out.println("Error creating CSV for Employee " + employee.getName() + ": " + e.getMessage());
        }
    }

    public void addPayslipToEmployee(String employeeID, Payslip payslip) {
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equals(employeeID)) {
                employee.addPayslip(payslip);
                CreateEmployeeCSV(employee); // Update CSV with the new payslip
                System.out.println("Payslip added for Employee: " + employee.getName());
                return;
            }
        }
        System.out.println("Employee with ID " + employeeID + " not found.");
    }
}




