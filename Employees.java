import java.util.ArrayList;

public class Employee{
    String employeeID;
    String name;
    String position;
    String salaryScale;
    int currentPoint;
    boolean isFullTime;
    ArrayList<Payslip> payslips = new ArrayList<Payslip>();

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
        }else {
            return false;
        }
    }
    
    public String printFullTime(){
        if(isFullTime == true){
            System.out.println("Employee is Full Time");
        }else{
            System.out.println("Employee is Part Time");
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

    @Override
    public String toString(){
        return "Employee ID: " + employeeID +"\n" + "Name: " + name + "\n" + "Position: " + position + "\n" + "Salary Scale: " + salaryScale + "\n" + "Current Point: " + currentPoint + "\n" + "Full Time: " + printFullTime() + "\n" + "Part Time: " + printFullTime() + "\n";
    }
}
