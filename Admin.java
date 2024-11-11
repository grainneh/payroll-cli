import java.util.ArrayList;

public class Admin extends Employees {

    private static ArrayList<Employees> employees = new ArrayList<Employees>(); // Static employee list

    // Admin constructor - Calls Employees constructor
    public Admin(String employeeID, String name, String position, String salaryScale ,int currentPoint, boolean isFullTime) {
        super(employeeID, name, position, salaryScale, currentPoint, isFullTime); // Calls Employees to get parameters
    }

    // Method to add an employee
    public void addEmployee(Employees employee) {
        employees.add(employee); // Add employee to the list
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

    public static ArrayList<Employees> getEmployees() {
        return employees;
    }




}
