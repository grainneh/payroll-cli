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
    Payslip payslip;
    Employee employee;
    String[] values;
    Deductions deductions = new Deductions(employee);

    public void loadCSV(){
        String pathPT = "EmployeesPartTime.csv"; // path for part-time employees csv
        String pathFT = "EmployeesFullTime.csv"; // path for full-time employees csv

        loadFullTime(pathFT);
        loadPartTime(pathPT);
    }

    public void loadFullTime(String pathFT) {
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
            }

        } catch (FileNotFoundException e) {
            System.out.println("Incorrect path name used");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void loadPartTime(String pathPT) {
            String line = "";
            boolean firstLine = true;

            try {
                //loading the csv file with sample part-time employees
                BufferedReader br2 = new BufferedReader(new FileReader(pathPT)); //part-time csv
                while ((line = br2.readLine()) != null) {
                    if (firstLine) {
                        firstLine = false;  // Set flag to false after processing the header
                        continue;  // Skip this iteration (header row)
                    }
                    values = line.split(",");
                    if (values.length == 4) {
                        String employeeID = values[0];
                        String name = values[1];
                        String position = values[2];
                        double payRate = Double.parseDouble(values[3]);
                        employee = new Employee(employeeID, name, position, payRate);
                        employees.add(employee);
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

        updateCSV();
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
        updateCSV();
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

        try {
            // Full-time employees CSV
            BufferedWriter writerFT = new BufferedWriter(new FileWriter(filePathFT));
            writerFT.write("EmployeeID,Name,Position,CurrentPoint");
            writerFT.newLine();
            for (Employee emp : employees) {
                if (emp.getIsFullTime()) {
                    writerFT.write(emp.getEmployeeID() + "," + emp.getName() + "," + emp.getPosition() + "," + emp.getCurrentPoint());
                    writerFT.newLine();
                }
            }
            writerFT.close();

            // Part-time employees CSV
            BufferedWriter writerPT = new BufferedWriter(new FileWriter(filePathPT));
            writerPT.write("EmployeeID,Name,Position,PayRate");
            writerPT.newLine();
            for (Employee emp : employees) {
                if (!emp.getIsFullTime()) {
                    writerPT.write(emp.getEmployeeID() + "," + emp.getName() + "," + emp.getPosition() + "," + emp.getPayRate());
                    writerPT.newLine();
                }
            }
            writerPT.close();
            System.out.println("CSV files updated successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }
}




