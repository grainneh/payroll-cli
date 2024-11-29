/**
 * The Admin class can add and remove employees to the employees arrayList.
 *
 * @author Michał Czekalski, Gráinne Hartigan
 */


import java.io.*;
import java.util.ArrayList;
import java.time.*;
public class Admin {
    public static ArrayList<Employee> employees = new ArrayList<Employee>();// Static employee list
    Payslip payslip; //instance of payslip class
    Employee employee; //instance of employee class
    String[] values; //string array used for reading csv values
    Deductions deductions = new Deductions(employee); //instance of deductions class
    boolean octoberCheck = false; //boolean used to check month

    /**
     * Null constructor
     */
    public Admin() {
    }

    /**
     * The method loadCSV calls the methods loadFullTime() and loadPartTime() in order to load the csv files containing
     * information about the full-time and part-time employees respectively.
     */
    public void loadCSV(){
        String pathPT = "EmployeesPartTime.csv"; // path for part-time employees csv
        String pathFT = "EmployeesFullTime.csv"; // path for full-time employees csv

        loadFullTime(pathFT);
        loadPartTime(pathPT);
    }

    /**
     * The method loadFullTime() loads the csv file with information about the full-time employees.
     * @param pathFT    the file path for the EmployeesFullTime.csv
     */
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

    /**
     * The method loadPartTime() loads the csv file with information about the part-time employees.
     * @param pathPT    the file path for the EmployeesPartTime.csv
     */
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


    /**
     * The method addEmployee() adds an employee to the arrayList of employees.
     * @param employee      the employee being added to the arrayList
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);// Add employee to the list
        System.out.println("Employee " + employee.getName() + " added");

        updateCSV();
    }

    /**
     * The method removeEmployee() removes an employee from the arrayList of employees given the employee ID.
     * @param employeeID    the ID of the employee to be removed
     */
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

    /**
     * The method getEmployees() returns an arrayList of employees.
     * @return employees    the arrayList of employees
     */
    public static ArrayList<Employee> getEmployees() {
        return employees;
    }


    /**
     * The method setEmployees() sets the arrayList of employees.
     * @param employees
     */
    public static void setEmployees(ArrayList<Employee> employees) {
        Admin.employees = employees;
    }

    /**
     * The method updateCSV() updates the csv files with employee details.
     */
    public void updateCSV() {
        String filePathFT = "EmployeesFullTime.csv";
        String filePathPT = "EmployeesPartTime.csv";

        try {
            // Full-time employees CSV
            BufferedWriter writerFT = new BufferedWriter(new FileWriter(filePathFT));
            writerFT.write("EmployeeID,Name,Position,CurrentPoint");
            writerFT.newLine();
            for (Employee emp : employees) {
                if (emp.getIsFullTime() && (LocalDate.now().getMonthValue() != 10 || octoberCheck)) {
                    writerFT.write(emp.getEmployeeID() + "," + emp.getName() + "," + emp.getPosition() + "," + emp.getCurrentPoint());
                    writerFT.newLine();
                }
                if (emp.getIsFullTime() && LocalDate.now().getMonthValue() == 10 && !octoberCheck) {
                    writerFT.write(emp.getEmployeeID() + "," + emp.getName() + "," + emp.getPosition() + "," + (emp.getCurrentPoint()+1));
                    writerFT.newLine();
                    octoberCheck = true;
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


