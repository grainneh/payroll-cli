
import java.util.ArrayList;
public class Employee {
    String employeeID;
    String name;
    String position;
    double payRate = 15.00; //same for all part time employees
    int currentPoint;
    boolean isFullTime; // Changed to instance variable
    public static ArrayList<Payslip> payslips = new ArrayList<Payslip>();
    Boolean payClaimed = false; //set for individual employees
    Boolean ableToClaim = true; //set for individual employees

    public Employee(){}
    // Constructor for full time
    public Employee(String employeeID, String name, String position, int currentPoint) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.currentPoint = currentPoint;
        this.payRate = 0.00;
    }
    public Employee(String employeeID, String name, String position) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.currentPoint = 0;
        this.isFullTime = false;
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
    public void setIsFullTime(boolean isFullTime) {
        this.isFullTime = isFullTime; // Set instance variable
    }
    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }
    public String printFullTime(){
        if(isFullTime){
            return "Employee is full-time";
        }else{
            return "Employee is part-time";
        }
    }
    public double getPayRate(){
        return payRate;
    }
    public void setPayRate(double payRate){
        this.payRate = payRate;
    }



    // Add payslip to an employee's payslip list
    public void addPayslip(Payslip payslip) {
        payslips.add(payslip);
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeID +"\n" + "Name: " + name + "\n" + "Position: " + position  + "\n" + "Current Point: " + currentPoint + "\n" + printFullTime();
    }


}
