
/*
This class uses two methods:one to remove and one to add employees to the employees array.
addEmployee simply uses .add() to add an employee to the array. RemoveEmployee uses a for loop to
go through the array and find an ID that matches the given ID. It also has a getEmployees method so you can make sure
the add/remove worked correctly.

@author Michal Czekalski
 */



import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Admin {



    public Admin(){

    }
    private static ArrayList<Employee> employees = new ArrayList<Employee>();// Static employee list
    Deductions deductions;
    Payslip payslip;

    public  void LoadCSV() {

        String path = "\"C:\\UL Files\\Semester 3\\CS4013\\Project\\11.20\\src\\employees.csv\"";

        String line = "";
        boolean firstLine =true;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;  // Set flag to false after processing the header
                    continue;  // Skip this iteration (header row)
                }
                String[] values = line.split(",");
                if (values.length == 6) {
                    String employeeID = values[0];
                    String name = values[1];
                    String position = values[2];
                    String salaryScale = values[3];
                    int currentPoint = Integer.parseInt(values[4]);


                    Employee employee = new Employee(employeeID, name, position, salaryScale, currentPoint);
                    employees.add(employee);

                }


            }
            System.out.println("CSV Loaded");
        } catch (FileNotFoundException e) {
            System.out.println("Incorrect path name used");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to add an employee
    public void addEmployee (Employee employee){
        employees.add(employee);// Add employee to the list
        System.out.println("Employee " + employee.getName() + " added");
    }

    // Method to remove an employee by their employee ID
    public void removeEmployee (String employeeID){
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

    public static ArrayList<Employee> getEmployees () {
        return employees;
    }

    public static void setEmployees (ArrayList < Employee > employees) {
        Admin.employees = employees;

    }

    public void updateCSV () {
        String filePath = "employees.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the header row (if needed)
            try {
                writer.write("ID,Name,Position,SalaryScale,SalarayPoint,isFullTime");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writer.newLine();

            // Iterate through the list of employees and write each employee's data
            for (Employee employee : employees) {
                writer.write(employee.getEmployeeID() + "," + employee.getName() + "," + employee.getPosition() + "," + employee.getSalaryScale() + "," + employee.getCurrentPoint() + "," + employee.printFullTime());
                writer.newLine();
            }

            System.out.println("CSV file updated successfully.");

        } catch (IOException e) {
            System.out.println("Error writing to the CSV file: " + e.getMessage());
        }
    }

    private void CreateEmployeeCSV(Employee employee) {
        String filename = employee.getEmployeeID() + "information.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
          writer.write("ID , Name, Position, SalaryPoint, isFullTime ");
          writer.newLine();
          writer.write(employee.getEmployeeID() + "," + employee.getName()+ "," + employee.getPosition() + "." + employee.getCurrentPoint() + ","
                           + employee.getIsFullTime());// writes all the employees details in order of the parameters
          writer.newLine();

          writer.write("This Epmloyees Payslips");
          writer.newLine();
          writer.write("GrossPay, NetPay,  date");
          writer.newLine();


            for (Payslip payslip : employee.getPayslips()) {
                writer.write(deductions.grossMonthlyPay +","+ deductions.netPay +","+ payslip.todaysDate);
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



