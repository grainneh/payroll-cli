
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Class to handle user interaction with the code. Loosely based off the CLI
 * interface provided in the Appointment class from one of our labs
 * <p>
 * Main difference is that I wrote a separate method for the commands to
 * improve code readability as there are a lot of options here
 *
 * @author Gráinne Hartigan
 * @date 13/11/2024
 */
public class CLI {
    Employee newEmployee;
    public int hours;
    private String command;
    private Scanner in;
    private Employee employee;
    private Payslip payslip;
    private Admin admin;
    private HumanResources humanResources;
    private ArrayList<String> validPartTimeEmployees = new ArrayList<>(
            Arrays.asList("Library staff",
                    "Security",
                    "Spar staff",
                    "Barista",
                    "Bartender")
    );
    private ArrayList<String> validFullTimeEmployees = new ArrayList<>(
            Arrays.asList("President",
                    "Vice President",
                    "Full Professor",
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
                    "Co-Op Students")
    );
    private ArrayList<Integer> maxPoints = new ArrayList<>(
            Arrays.asList(0,
                    0,
                    6,
                    6,
                    9,
                    9,
                    10,
                    2,
                    6,
                    9,
                    12,
                    11,
                    11,
                    12,
                    18,
                    6,
                    8,
                    8,
                    8,
                    15,
                    11,
                    13,
                    10,
                    14,
                    10,
                    10,
                    10,
                    8,
                    9,
                    9,
                    6,
                    10,
                    6,
                    5,
                    9,
                    13,
                    15,
                    13,
                    15,
                    13,
                    15,
                    15,
                    15,
                    2,
                    6,
                    9,
                    10,
                    10,
                    9,
                    6,
                    7,
                    1,
                    1,
                    2,
                    2,
                    2)
    );
    public CLI() {
        in = new Scanner(System.in);
        admin = new Admin();
        humanResources = new HumanResources(); //intialize HR
    }
    public void run() {
        boolean running = true;
        admin.loadCSV();//Michal added in once admin class was complete,this loads the csv file into an array so that it can be used.

        while (running) {
            System.out.println("E)mployee    A)dmin     H)uman Resources    Q)uit (Press Q at any point to quit)");
            command = in.nextLine().toUpperCase();
            if (command.equals("Q")) {
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
                default:
                    System.out.println("Invalid option. Please enter E, A, H, or Q.");
            }
        }

    }
    private void commandE() {
        if (logIn()) {
            System.out.println("E)mployee Details V)iew Payslip S)ubmit payclaim");
            command = in.nextLine().toUpperCase();
            if (command.equals("E")) {
                System.out.println(employee.toString());
            } else if (command.equals("V")) {
                commandV();
            } else if (command.equals("S")) {
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
                    System.out.println(payslip.generatePayslip(employee));
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
    private void commandA() {
        if (logIn()) {
            //admin can add or remove an employee
            System.out.println("A)dd employee     R)emove employee");
            command = in.nextLine().toUpperCase();
            if (command.equals("A")) {
                commandAa();
            } else if (command.equals("R")) {
                //remove employee
                System.out.println("Enter Employee ID");
                String employeeID = in.nextLine();
                //check to see if entered employee id is 4 digits
                if(Integer.parseInt(employeeID) <1000 || Integer.parseInt(employeeID)>9999){
                    System.out.println("Please enter a 4 digit employee ID.");

                }
                admin.removeEmployee(employeeID);
                admin.updateCSV();
            }
        }
    }
    private boolean logIn() {
        System.out.println("Please enter employee ID to log in:");
        String employeeID = in.nextLine().trim();

        //check to see if entered employee id is 4 digits
        if(Integer.parseInt(employeeID) <1000 || Integer.parseInt(employeeID)>9999){
            System.out.println("Invalid ID.");
        }else if (employeeID.isEmpty()) {
            System.out.println("Employee ID cannot be empty.");
            return false;
        }else {

            for (Employee emp : Admin.getEmployees()) {
                if (emp.getEmployeeID().equals(employeeID)) {
                    employee = emp;
                    System.out.println(emp.getName() + "(" + employeeID + ") logged in.");
                    return true;
                }
            }
            System.out.println("User not found. Please check your Employee ID.");
        }
            return false;

    }
    private void commandH() {
        if (logIn()) {
            //implement promotion functionality
            boolean t = false;
            while (!t) {
                System.out.println("P)romote employee G)ive raise ");
                command = in.nextLine().toUpperCase();
                if (command.equals("P")) {
                    System.out.println("Enter employee ID:");
                    String employeeID = in.nextLine();
                    //check to see if entered employee id is 4 digits
                    if(Integer.parseInt(employeeID) <1000 || Integer.parseInt(employeeID)>9999){
                        System.out.println("Please enter a 4 digit employee ID.");
                        break;
                    }

                    // Find the employee to promote
                    Employee employeeToPromote = null;
                    for (Employee emp : Admin.getEmployees()) {
                        if (emp.getEmployeeID().equals(employeeID)) {
                            employeeToPromote = emp;
                            break;
                        }
                    }

                    if (employeeToPromote == null) {
                        System.out.println("Employee not found. Please enter a valid employee ID.");
                        break;
                    }

                    String position = null;
                    System.out.println("I)ncrement current salary scale point    N)ew Position ");
                    command = in.nextLine().toUpperCase();

                    int i = validFullTimeEmployees.indexOf(employeeToPromote.getPosition());

                    if(command.equals("I")){
                        if(employeeToPromote.getCurrentPoint() < maxPoints.get(i)) {
                            employeeToPromote.setCurrentPoint(employeeToPromote.getCurrentPoint() + 1);// increase current point on scale
                            admin.updateCSV();
                            System.out.printf("%s promoted from %d to %d on salary scale.\n", employeeToPromote.getEmployeeID(), employeeToPromote.getCurrentPoint()-1, employeeToPromote.getCurrentPoint());

                            break;
                        }else
                        //if employee already at top of scale, ask user to input their new position
                        System.out.printf("%s is already at the maximum point on salary scale. Please ", employee.getEmployeeID()); //string is continued in commandN
                        command = "N";
                    }
                    //check if employee is full time or part time, then check if the user entered a valid position

                    if(command.equals("N")){
                    if(employeeToPromote.isFullTime){
                        while (position == null) {
                            String attempt = EmptyInputHandling("Enter new position"); //make sure valid position and get valid current points
                            for (String s : validFullTimeEmployees) {
                                if (s.equals(attempt)) {
                                    position = attempt;
                                    break;
                                }
                            }
                            if (position == null) {
                                System.out.println("Invalid position. Would you like to see a list of valid Employees? Y/N");
                                command = in.nextLine().toUpperCase();
                                if (command.equals("Y")) {
                                    viewValidFullTimeEmployees();
                                    System.out.println("Please enter a position from this list.");
                                    position = in.nextLine();
                                } else {
                                    // If input is anything else, go back to the start of the loop
                                    System.out.println("Returning to input prompt.");
                                }
                            }
                        }
                    }else {
                        while (position == null) {
                            String attempt = EmptyInputHandling("Enter new position"); //make sure valid position and get valid current points
                            for (String s : validPartTimeEmployees) {
                                if (s.equals(attempt)) {
                                    position = attempt;
                                    break;
                                }
                            }
                            if (position == null) {
                                System.out.println("Invalid position. Would you like to see a list of valid Employees? Y/N");
                                command = in.nextLine().toUpperCase();
                                if (command.equals("Y")) {
                                    viewValidFullTimeEmployees();
                                    System.out.println("Please enter a position from this list.");
                                    position = in.nextLine();
                                } else {
                                    // If input is anything else, go back to the start of the loop
                                    System.out.println("Returning to input prompt.");
                                }
                            }
                        }
                    }

                    }
                    humanResources.promoteEmployee(employeeID, position);
                    t = true;
                }
                if (command.equals("G")) {
                    System.out.println("Enter employee ID:");
                    String employeeID = in.nextLine();
                
                    //check to see if entered employee id is 4 digits
                    if(Integer.parseInt(employeeID) <1000 || Integer.parseInt(employeeID)>9999){
                        System.out.println("Please enter a 4 digit employee ID.");
                    }
                    if (employee.getIsFullTime()){
                        System.out.println("Employee is full-time. Cannot give raise.");
                    }else {
                        System.out.println("Enter new pay rate:");
                        try {
                            double payRate = Double.parseDouble(in.nextLine().trim());
                            humanResources.employeeRaise(employeeID, payRate);
                            t = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input for new pay rate");
                        }
                        // change employees pay rate
                    }

                }
            }


        }
    }
    private void commandAa() {
        System.out.println("F)ull time or P)art time");
        command = in.nextLine().toUpperCase();
        if (command.equals("F")) {

            String employeeID = EmptyInputHandling("Enter Employee ID");
            boolean exists;

                //check to see if entered employee id is 4 digits
                if (Integer.parseInt(employeeID) < 1000 || Integer.parseInt(employeeID) > 9999) {
                    System.out.println("Please enter a 4 digit employee ID.");
                    employeeID = EmptyInputHandling("Enter Employee ID");

                }
                    for (int i = 0; i < admin.employees.size(); i++) {
                        if (admin.employees.get(i).getEmployeeID().equals(employee.getEmployeeID())) {
                            System.out.println("Employee already exists. Please enter a valid employee ID.");

                            break;
                        }
                    }


            String name = EmptyInputHandling("Enter Employee Name");
            String position = null;
            while (position == null) {
                String attempt = EmptyInputHandling("Enter Employee Position"); //make sure valid position and get valid current points
                for (String s : validFullTimeEmployees) {
                    if (s.equals(attempt)) {
                        position = attempt;
                        break;
                    }
                }
                if (position == null) {
                    System.out.println("Invalid position. Would you like to see a list of valid Employees? Y/N");
                    command = in.nextLine().toUpperCase();
                    if (command.equals("Y")) {
                        viewValidFullTimeEmployees();
                        System.out.println("Please enter a position from this list.");
                    } else {
                        // If input is anything else, go back to the start of the loop
                        System.out.println("Returning to input prompt.");
                    }
                }

            }

            // get array element from position entered
            int i = validFullTimeEmployees.indexOf(position);
            //set max currentPoint to element from points array
            int maxPoint = maxPoints.get(i);
            System.out.printf("Enter point on scale between 1 and %d for %s", maxPoint, position); //check valid points against position
            int currentPoint = -1;
            while (currentPoint < 0 || currentPoint > maxPoint) { // add a max depending on valid current point scale from array
                try {
                    currentPoint = Integer.parseInt(in.nextLine().trim());
                    if (currentPoint < 0) {
                        System.out.println("Salary scale must be a positive number. Please re-enter.");
                    }
                    if (currentPoint > maxPoint) {
                        System.out.println("Salary scale must be within the valid range. Please re-enter.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }


            newEmployee = new Employee(employeeID, name, position, currentPoint);

            admin.addEmployee(newEmployee);

        }
        if (command.equals("P")) {
            String employeeID = EmptyInputHandling("Enter Employee ID");
            //check to see if entered employee id is 4 digits
            if(Integer.parseInt(employeeID) <1000 || Integer.parseInt(employeeID)>9999){
                System.out.println("Please enter a 4 digit employee ID.");
            }

            for (int i = 0; i < admin.employees.size(); i++) {
                if (admin.employees.get(i).getEmployeeID().equals(employee.getEmployeeID())) {
                    System.out.println("Employee already exists. Please enter a valid employee ID.");
                    //employeeID = EmptyInputHandling("Enter Employee ID");
                    break;
                }
            }
            String name = EmptyInputHandling("Enter Employee Name");
            String position = null;
            while (position == null) {
                String attempt = EmptyInputHandling("Enter Employee Position"); //make sure valid position and get valid current points
                for (String s : validPartTimeEmployees) {
                    if (s.equals(attempt)) {
                        position = attempt;
                        break;
                    }
                }
                if (position == null) {
                    System.out.println("Invalid position. Would you like to see a list of valid Employees? Y/N");
                    command = in.nextLine().toUpperCase();
                    if (command.equals("Y")) {
                        viewValidPartTimeEmployees();
                    } else {
                        // If input is anything else, go back to the start of the loop
                        System.out.println("Returning to input prompt.");
                    }
                }
            }
            double payRate = -1;
            while (payRate < 0) {
                try {
                    payRate = Double.parseDouble(EmptyInputHandling("Enter Employee Hourly Pay Rate"));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            newEmployee = new Employee(employeeID, name, position, payRate); //exchange with addPartTime when written
        }
    }
    private void viewValidFullTimeEmployees() {
        for (String s : validFullTimeEmployees) {
            System.out.println(s);
        }
    }
    private void viewValidPartTimeEmployees() {
        for (String s : validPartTimeEmployees) {
            System.out.println(s);
        }
    }
    private String EmptyInputHandling(String message) {
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
    public void commandP() {
        try {
            if (employee.getIsFullTime()) {
                System.out.println("Employee is full-time, pay claim cannot be submitted.");
            } else {
                System.out.println("Submit pay claim.");
                System.out.println("Please enter number of hours worked:");
                hours = Integer.parseInt(in.nextLine().trim());
                System.out.println(hours + " submitted.");
                PayClaim.validateClaim(employee);
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter the number of hours worked.");
        }
    }
}
