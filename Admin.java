/*
This class uses two methods:one to remove and one to add employees to the employees array.
addEmployee simply uses .add() to add an employee to the array. RemoveEmployee uses a for loop to
go through the array and find an ID that matches the given ID. It also has a getEmployees method so you can make sure
the add/remove worked correctly.

@author Michal Czekalski
 */



import java.io.FileWriter;
import java.util.ArrayList;

public class Admin  {

    private static ArrayList<Employee> employees = new ArrayList<Employee>(); // Static employee list




    // Method to add an employee
    public void addEmployee(Employee employee) {
        employees.add(employee);// Add employee to the list
        System.out.println("Employee "+employee.getName()+" added");
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
        System.out.println("Employee not found."); // If not found
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

public static void setEmployees(ArrayList<Employee> employees) {
        Admin.employees = employees;

}

public void  updateCSV(ArrayList<Employee> employees){
    String filePath = "emplpoyees.csv";


}


}
