import java.util.ArrayList;

public class Employee{
    String employeeID;
    String name;
    String position;
    String salaryScale;
    int currentPoint;
    boolean isFullTime;
    List<Payslip> payslips = new ArrayList<Payslip>();

    public Employee(String employeeID, String name, String position, String salaryScale, int currentPoint, boolean isFullTime, List<Payslip> payslips, List<employees> employees){
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.salaryScale = salaryScale;
        this.currentPoint = currentPoint;
    }

    public String getEmployeeID(){
        return employeeID;
    }

    public String getName(){
        return name;
    }

    public String getPosition(){
        return position;
    }

    public String getSalaryScale(){
        return salaryScale;
    }

    public int getCurrentPoint(){
        return currentPoint;
    }

    public boolean isFullTime(){
        if(isFullTime == true){
            return true;
        }else{
            return false;
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public void setSalaryScale(String salaryScale){
        this.salaryScale = salaryScale;
    }

    public String toString(){
        System.out.println("Employee ID: " + employeeID + "Name" + name + "Position" + position + "SalaryScale" + salaryScale);
        return "";
    }
}
