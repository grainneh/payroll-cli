import java.util.ArrayList;

public class HumanResources {
    private Admin admin = new Admin();

    // Method to promote an employee by employeeID
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
