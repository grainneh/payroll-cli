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

    public Admin(){

    }
    private static ArrayList<Employee> employees = new ArrayList<Employee>();// Static employee list


    public  void LoadCSV() {
        String path =  "/Users/micha/Desktop/employees.csv";
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 6) {
                    String employeeID = values[0];
                    String name = values[1];
                    String position = values[2];
                    String salaryScale = values[3];
                    int currentPoint = Integer.parseInt(values[4]);
                    boolean isFullTime = values[5].equalsIgnoreCase("Yes");

                    Employee employee = new Employee(employeeID, name, position, salaryScale, currentPoint, isFullTime);
                    employees.add(employee);

                }
                System.out.println("CSV loaded");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

}



