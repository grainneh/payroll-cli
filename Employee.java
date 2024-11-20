
import java.util.ArrayList;
public class Employee {
    String employeeID;
    String name;
    String position;
    String salaryScale;
    int currentPoint;
    boolean isFullTime; // Changed to instance variable
    public static ArrayList<Payslip> payslips = new ArrayList<Payslip>();
    // Constructor
    public Employee(String employeeID, String name, String position, String salaryScale, int currentPoint, boolean isFullTime) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.salaryScale = salaryScale;
        this.currentPoint = currentPoint;
        this.isFullTime = isFullTime; // Initialize based on constructor input
    }
    // Getter and Setter methods
    public String getEmployeeID() {
        return employeeID;
    }
    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }
    public String getSalaryScale() {
        return salaryScale;
    }
    public int getCurrentPoint() {
        return currentPoint;
    }
    public boolean getIsFullTime() {
        return isFullTime; // Return instance variable
    }
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setSalaryScale(String salaryScale) {
        this.salaryScale = salaryScale;
    }
    public void setIsFullTime(boolean isFullTime) {
        this.isFullTime = isFullTime; // Set instance variable
    }
    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }
    public String printFullTime(){
        if(isFullTime == true){
            return "Employee is full-time";
        }else{
            return "Employee is part-time";
        }
    }


    // Add payslip to an employee's payslip list
    public void addPayslip(Payslip payslip) {
        payslips.add(payslip);
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeID +"\n" + "Name: " + name + "\n" + "Position: " + position + "\n" + "Salary Scale: " + salaryScale + "\n" + "Current Point: " + currentPoint + "\n" + printFullTime();
    }


}
