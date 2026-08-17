/**
 * The Employee class creates an employee object with various attributes including employeeID, name, position, payRate,
 * currentPoint and isFullTime.
 *
 * @author Philip Roche
 */

public class Employee {
    String employeeID;
    String name;
    String position;
    double payRate = 15.00; //same for all part-time employees
    int currentPoint;
    boolean isFullTime;
    Boolean payClaimed = false; //set for individual employees
    Boolean ableToClaim = true; //set for individual employees

    /**
     * Null constructor
     */
    public Employee(){}


    /**
     * Constructor for full-time employees
     *
     * @param employeeID    employee ID
     * @param name          employee name
     * @param position      employee position
     * @param currentPoint  current point on salary scale
     */
    public Employee(String employeeID, String name, String position, int currentPoint) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.currentPoint = currentPoint;
        this.payRate = 0.00;
        this.isFullTime = true;
    }

    /**
     * Constructor for part-time employees
     *
     * @param employeeID    employee ID
     * @param name          employee name
     * @param position      employee position
     * @param payRate       hourly pay rate
     */
    public Employee(String employeeID, String name, String position, double payRate) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.currentPoint = 0;
        this.payRate = payRate;
        this.isFullTime = false;
    }

    /**
     * The method printFullTime() returns a string displaying if an employee is full-time or part-time
     *
     * @return string describing whether an employee is full-time or part-time
     */
    public String printFullTime(){
        if(isFullTime){
            return "Employee is full-time";
        }else{
            return "Employee is part-time";
        }
    }

    /**
     * The method addPayslip() adds a payslip to a list of employee payslips
     *
     * @param payslip   the payslip to be added to the list
     */
    public void addPayslip(Payslip payslip) {
        payslip.payslips.add(payslip);
    }
    
    /**
     * The toString method provides a string representation of the employee
     * @return  string
     */
    @Override
    public String toString() {
        return "Employee ID: " + employeeID +"\n" + "Name: " + name + "\n" + "Position: " + position  + "\n" + "Current Point: " + currentPoint + "\n" + printFullTime();
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
        return isFullTime;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPosition(String position) {
        this.position = position;
    }
   
    public void setCurrentPoint(int currentPoint) {
        this.currentPoint = currentPoint;
    }

    public double getPayRate(){
        return payRate;
    }
    public void setPayRate(double payRate){
        this.payRate = payRate;
    }
}
