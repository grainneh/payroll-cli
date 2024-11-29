/**
 * The HumanResources class has functionality to promote employees and give part-time employees a pay raise
 *
 * @author Michał Czekalski
 */

import java.util.ArrayList;

public class HumanResources {
    private Admin admin = new Admin(); //instance of the admin class

    /**
     * The method promoteEmployee() promotes an employee to a new position given an employee ID
     * @param employeeID    the ID of the employee to be promoted
     * @param newPosition   the new position
     */
    public void promoteEmployee(String employeeID, String newPosition) {
        // Loop through the employees to find the employee by their ID
        ArrayList<Employee> employees = Admin.getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID().equals(employeeID)) {
                employees.get(i).setPosition(newPosition); // Set the new position
                employees.get(i).setCurrentPoint(1);
                System.out.println("Employee " + employeeID + " promoted to " + newPosition);
                admin.updateCSV();
                return; // Exit method once promotion is done
            }
        }
        // If no employee is found with the given ID
        System.out.println("Employee not found for promotion.");
    }

    /**
     * The method employeeRaise() implements functionality to give a part-time employee a pay raise, by changing their 
     * payRate
     * 
     * @param employeeID    the ID of the employee to get a raise
     * @param payRate       the new payRate
     */
    public void employeeRaise(String employeeID, double payRate) {
        // Loop through the employees to find the employee by their ID
        ArrayList<Employee> employees = Admin.getEmployees();
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID().equals(employeeID)) {
                employees.get(i).setPayRate(payRate);// Set the new position
                admin.updateCSV();
                System.out.println("Employee " + employeeID + " pay set to " + payRate );
                return; // Exit method once raise is done
            }
        }
        // If no employee is found with the given ID
        System.out.println("Employee not found for raise.");
    }
}
