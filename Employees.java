import java.util.ArrayList;

public class Employees{
    String employeeID;
    String name;
    String position;
    String salaryScale;
    int currentPoint;
    boolean isFullTime;
    List<Payslip> payslips = new ArrayList<Payslip>();
    List<employees> employees = new ArrayList<employees>();

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

    public void setEmployeeID(String employeeID){
        this.employeeID = employeeID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public void setSalaryScale(int salaryScale){
        this.salaryScale = salaryScale;
    }
}
