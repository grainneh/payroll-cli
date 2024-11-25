/*
This class is mainly used to promote an employee. The method used is almost identical to the remove employee
method in Admin as both methods search through an array to find a matching ID and the do some sort of modification
to said ID.
@author Michal Czekalski
@version 16/11/2024
 */




import java.util.ArrayList;

public class HumanResources {


    // Method to promote an employee by employeeID
    public void promoteEmployee(String employeeID, String newPosition) {
        // Loop through the employees to find the employee by their ID
        ArrayList<Employee> employees = Admin.getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID().equals(employeeID)) {
                employees.get(i).setPosition(newPosition);// Set the new position

                System.out.println("Employee " + employeeID + " promoted to " + newPosition );
                return; // Exit method once promotion is done
            }
        }
        // If no employee is found with the given ID
        System.out.println("Employee not found for promotion.");
    }
    public void employeeRaise(String employeeID, int payRate) {
        // Loop through the employees to find the employee by their ID
        ArrayList<Employee> employees = Admin.getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID().equals(employeeID)) {
                employees.get(i).setPayRate(payRate);// Set the new position

                System.out.println("Employee " + employeeID + " pay set to " + payRate );
                return; // Exit method once raise is done
            }
        }
        // If no employee is found with the given ID
        System.out.println("Employee not found for raise.");
    }

}

