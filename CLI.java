
/**
 *
 *
 * Class to handle user interaction with the code. Loosely based off the CLI
 * interface provided in the Appointment class from one of our labs
 *
 * Main difference is that I wrote a separate method for the commands to
 * improve code readability as there are a lot of options here
 *
 * @author Gráinne Hartigan
 * @date 13/11/2024
 */
import java.time.LocalDate;
import java.util.Scanner;
public class CLI{
    private String command;
    private Scanner in;
    private Employee employee;
    private Payslip payslip;
    private Admin admin;
    private HumanResources humanResources;
    Employee newEmployee;

    /**
     * Constructs a CLI object
     */
    public CLI(){
        in = new Scanner(System.in);
        admin = new Admin();
        humanResources = new HumanResources(); //intialize HR
    }

    /**
     * Runs the system
     */
    public void run(){
        boolean running = true;
        admin.LoadCSV();//Michal added in once admin class was complete,this loads the csv file into an array so that it can be used.

        while (running){//the code will run while "running" is true
            //prompt the user to choose if they will log in as an employee, hr staff, or admin
            System.out.println("E)mployee    A)dmin     H)uman Resources    Q)uit"); //application has 3 user types
            command = in.nextLine().toUpperCase();
            if(command.equals("E")){
                //the user is an employee
                commandE();
            }else if (command.equals("A")){
                //the user is admin
                commandA();
            }else if (command.equals("H")){
                //the user is HR
                commandH();
            }else if (command.equals("Q")){
                //the user has chosen to quit the application
                running = false;
            }
        }
    }

    private void commandE(){
        if (logIn()){
            System.out.println("S)how Employee Details V)iew Payslip");
            command = in.nextLine().toUpperCase();
            if (command.equals("S")) {
                System.out.println(employee.toString());
            }else if (command.equals("V")){
                commandV();
            }
        }
    }

    private void commandV(){
        //employee needs to enter date of the payslip they want to see
        System.out.println("Please enter payslip date (dd/MM/yyyy):");
        String dateString = in.nextLine();

        //create an array to store the parts of the date
        String[] dateParts = dateString.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        LocalDate date = LocalDate.of(year, month, day); //this is the date the employee has entered

        boolean payslipFound = false;
        //go through array list of payslips and print out payslip for the date given
        for (int i = 0; i<Employee.payslips.size(); i++){ //made payslips public in employee so that it could be accessed here
            if(Employee.payslips.get(i).getDate().equals(date)){ //try using for loop
                System.out.println(Employee.payslips.get(i).generatePayslip());
                payslipFound = true;
            }
        }
        if(!payslipFound){
            System.out.println("There is no payslip found for this date.");
        }
    }

    private void commandA(){
        if(logIn()) {
            //admin can add or remove an employee
            System.out.println("A)dd employee     R)emove employee");
            command = in.nextLine().toUpperCase();
            if (command.equals("A")) {
                commandAa();
            } else if (command.equals("R")) {
                //remove employee
                System.out.println("Enter Employee ID");
                String employeeID = in.nextLine();
                admin.removeEmployee(employeeID);
                admin.updateCSV();
            }
        }
    }

    private void commandAa(){
        //add employee
        System.out.println("Please enter new employee details");
        System.out.println("Enter Employee ID:");
        String employeeID = in.nextLine();
        System.out.println("Enter name:");
        String name = in.nextLine();
        System.out.println("Enter position:");
        String position = in.nextLine();
        System.out.println("Enter salary scale");
        String salaryScale = in.nextLine();
        System.out.println("Enter current point on salary scale:");
        int currentPoint = Integer.parseInt(in.nextLine());
        System.out.println("Is employee F)ull Time or P)art Time?");
        command = in.nextLine().toUpperCase();
        if (command.equals("F")) {
            newEmployee = new Employee(employeeID, name, position, salaryScale, currentPoint, true);
        }else if (command.equals("P")) {
            newEmployee = new Employee(employeeID, name, position, salaryScale, currentPoint, false);
        }

        admin.addEmployee(newEmployee);
        admin.updateCSV();
        System.out.println("Employee " + employeeID + " added.");
    }

    private void commandH(){
        if(logIn()) {
            //implement promotion functionality
            System.out.println("P)romote employee");
            command = in.nextLine().toUpperCase();
            if(command.equals("P")) {
                System.out.println("Enter employee ID:");
                String employeeID = in.nextLine();
                System.out.println("Enter new position:");
                String position = in.nextLine();

                humanResources.promoteEmployee(employeeID, position);
            }
        }
    }

    /**
     * This method allows an employee to enter their id and log in
     */
    private boolean logIn(){
        System.out.println("Please enter employee ID to log in");
        String employeeID = in.nextLine();
        //check if employee exists
        boolean found = false;
        for(int i =0; i < Admin.getEmployees().size(); i++){
            if(Admin.getEmployees().get(i).getEmployeeID().equals(employeeID)){
                employee = Admin.getEmployees().get(i); // this employee is now logged in and will be used throughout
                System.out.println(employee.getName() + "(" + employeeID + ") logged in.");
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("User not found.");
        }
        return found;
    }
}

