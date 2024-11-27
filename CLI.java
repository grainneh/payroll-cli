/**
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
    private ArrayList<String> partTimeEmployees = {
            "Library staff",
            "Security",
            "Spar staff",
            "Barista",
            "Bartender"
    }
    private ArrayList<String> fullTimeEmployees = {
            "President",
"Vice President",
"Full Time Professor",
"Professor",
"Associate Professor A",
"Associate Professor B",
"Assistant Professor",
"Teaching Assistant",
"Senior Administrative Officer III",
"Senior Administrative Officer II",
"Senior Administrative Officer I",
"Senior Executive Administrator",
"Executive Administrator",
"Senior Administrator",
"Administrator",
"EPS Portfolio Manager",
"EPS Category Manager",
"EPS Category Specialist",
"EPS Category Specialist Higher",
"Sub Librarian",
"Assistant Librarian",
"Senior Library Assistant",
"Library Assistant",
"Library Attendant",
"Analyst Programmer",
"Senior Computer Operator",
"Computer Operator",
"Print Operator",
"Computer Lab Attendant",
"Temporary Computer Assistant",
"Chief Technical Officer",
"Technical Officer",
"Senior Techical Officer",
"Senior Lab Attendant",
"Laboratory Attendant",
"Sen Porter/Attendant",
"Porter/Attendant",
"Grounds Supervisor",
"Groundsworkperson",
"Senior Aide",
"Machine Attendant",
"Service Staff",
"Service Staff Shift",
"Plant Maintenance Aide",
"Grounds Foreperson",
"Teaching Fellow",
"University Teacher",
"Associate Teacher",
"Regional Placement Facilitator",
"Clinical Tutor",
"Clinical Fellow",
"Assistant Senior Instructor",
"Lead Instructor",
"Multi Activity Instructor",
"Assistant Instructor", 
"Co-Op Students",

    }
    private ArrayList<int> maxPoints = {}
        
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

        while (running) {
            System.out.println("E)mployee    A)dmin     H)uman Resources    Q)uit (Press Q at any point to quit)");
            command = in.nextLine().toUpperCase();
            if(command.equals("Q")){
                running = false;
            }
            switch (command) {
                case "E":
                    commandE();
                    break;
                case "A":
                    commandA();
                    break;
                case "H":
                    commandH();
                    break;
                case "Q":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter E, A, H, or Q.");
            }
        }

    }

    private void commandE(){
        if (logIn()){
            System.out.println("E)mployee Details V)iew Payslip S)ubmit payclaim");

            if (command.equals("E")) {
                System.out.println(employee.toString());
            }else if (command.equals("V")){
                commandV();
            } else if (command.equals("S")){
                commandP();
            }
        }
    }

    private void commandV() {
        System.out.println("Please enter payslip date (dd/MM/yyyy):");
        String dateString = in.nextLine().trim();
        try {
            String[] dateParts = dateString.split("/");
            if (dateParts.length != 3) throw new IllegalArgumentException("Invalid date format.");
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            LocalDate date = LocalDate.of(year, month, day);

            boolean payslipFound = false;
            for (Payslip slip : Employee.payslips) {
                if (slip.getDate().equals(date)) {
                    System.out.println(Payslip.generatePayslip(employee));
                    payslipFound = true;
                    break;
                }
            }
            if (!payslipFound) {
                System.out.println("No payslip found for the given date.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Date components must be numeric.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format or invalid date. Please use dd/MM/yyyy.");
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

    private void commandAa() {
        System.out.println("F)ull time or P)art time");
        command = in.nextLine().toUpperCase();
        if (command.equals("F")) {

                String employeeID = EmptyInputHandling("Enter Employee ID");
                String name = EmptyInputHandling("Enter Employee Name");
                String position = EmptyInputHandling("Enter Employee Position"); //make sure valid position and get valid current points
                System.out.println("Enter point on scale"); //check valid points against position
                int currentPoint = -1;
                while (currentPoint < 0) { // add a max depending on valid current point scale from array
                    try {
                        currentPoint = Integer.parseInt(in.nextLine().trim());
                        if (currentPoint < 0) {
                            System.out.println("Salary scale must be a positive number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                }
                newEmployee = new Employee(employeeID,name,position,currentPoint);
        }else if (command.equals("P")){
            String employeeID = EmptyInputHandling("Enter Employee ID");
            String name = EmptyInputHandling("Enter Employee Name");
            String position = EmptyInputHandling("Enter Employee Position"); //make sure valid position and get valid current points
            int payRate = -1;
            while(payRate < 0 ) {
                try {
                    payRate = Integer.parseInt(EmptyInputHandling("Enter Employee Pay Rate"));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            newEmployee = new Employee(employeeID,name,position,payRate);
        }
    }


    private void commandH(){
        if(logIn()) {
            //implement promotion functionality
            boolean t = false;
            while( !t) {
                System.out.println("P)romote employee G)ive raise ");
                command = in.nextLine().toUpperCase();
                if (command.equals("P")) {
                    System.out.println("Enter employee ID:");
                    String employeeID = in.nextLine();
                    System.out.println("Enter new position:");
                    String position = in.nextLine();

                    humanResources.promoteEmployee(employeeID, position);
                    t = true;
                }
                if (command.equals("G")) {
                    System.out.println("Enter employee ID:");
                    String employeeID = in.nextLine();
                    System.out.println("Enter new pay rate:");
                    try {
                        int payRate = Integer.parseInt(in.nextLine().trim());
                        humanResources.employeeRaise(employeeID,payRate);
                        t = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for new pay rate");
                    }
                    // change employees pay rate

                }
            }


        }
    }

    /**
     * This method allows an employee to enter their id and log in
     */
    private boolean logIn() {
        System.out.println("Please enter employee ID to log in:");
        String employeeID = in.nextLine().trim();
        if (employeeID.isEmpty()) {
            System.out.println("Employee ID cannot be empty.");
            return false;
        }

        for (Employee emp : Admin.getEmployees()) {
            if (emp.getEmployeeID().equals(employeeID)) {
                employee = emp;
                System.out.println(emp.getName() + "(" + employeeID + ") logged in.");
                return true;
            }
        }
        System.out.println("User not found. Please check your Employee ID.");
        return false;
    }

    private String EmptyInputHandling (String message) {
        String input;
        do {
            System.out.println(message);
            input = in.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty.");
            }
        } while (input.isEmpty());
        return input;
    }

    public void commandP(){
        try {
            if (employee.getIsFullTime()) {
                System.out.println("Employee is full-time, pay claim cannot be submitted.");
            } else {
                System.out.println("Submit pay claim.");
                System.out.println("Please enter number of hours worked:");
                int hours = Integer.parseInt(in.nextLine().trim());
                System.out.println(hours + " submitted.");
                PayClaim.validateClaim(employee);
            }

        }catch(NumberFormatException e){
            System.out.println("Please enter the number of hours worked.");
        }
    }
}

