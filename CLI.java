import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Command Line Interface
 * 
 * 
 * @author Gráinne Hartigan, Liam Finn
 */
public class CLI {
    Employee newEmployee;
    public int hours;
    private String command;
    private Scanner in;
    private Employee employee;
    private Payslip payslip = new Payslip();
    private Admin admin;
    private HumanResources humanResources;
    private final String adminpassword = "admin123";
    private final String hrpassword = "hr123";
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
    boolean running = false;

    /**
     * Constructor for CLI Object
     */
    public CLI() {
        in = new Scanner(System.in);
        admin = new Admin();
        humanResources = new HumanResources();
        payslip = new Payslip();
    } 

    /**
     * Runs the CLI
     */
    public void run() {
        running = true;
        admin.loadCSV();
        payslip.LoadPCSV();

        while (running) {
            System.out.println("E)mployee    A)dmin     H)uman Resources    Q)uit (Press Q at any point to quit)");
            command = in.nextLine().toUpperCase();
            switch (command) {
                case "E":
                    EmployeeMenu();
                    break;
                case "A":
                    AdminMenu();
                    break;
                case "H":
                    HRMenu();
                    break;
                case "Q":
                    System.out.println("Slán!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter E, A, H, or Q.");
            }
        }

    } 

    /**
     * Handles the execution of the "Employee Commands" menu.
     * This method checks if the user is logged in by calling logIn().
     * If the login is successful, it displays a menu with options for employee actions
     *
     */
    private void EmployeeMenu() {
        if (logIn()) {
            command = EmptyInputHandling("E)mployee Details V)iew Payslip S)ubmit payclaim Q)uit");
            switch (command) {
                case "E":
                    System.out.println(employee.toString());
                    break;
                /*case "V":
                    commandV();
                    break;

                 */
                case "S":
                    PayClaimSubmission();
                    break;
                case "Q":
                    quit();
                    break;
                default:
                    System.out.println("Invalid option. Please enter E, V, S, or Q.");
                    break;
            }
        }
    } 

    /**
     * Handles the process of viewing a payslip for a specified date.
     *
     * Prompts the user to enter a date in the format "dd/MM/yyyy" and attempts to
     * retrieve and display the corresponding payslip for the logged-in employee.
     */

    /*private void commandV() {
        System.out.println("Please enter payslip date (dd/MM/yyyy):");
        String dateString = in.nextLine().trim();
        if (dateString.equals("Q")) {
            running = false;
        }
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


     */

    /*public void commandV(){
        System.out.println("Please enter employee ID");
        String employeeID = in.nextLine().trim();
        /*
        for (int i = 0; i < admin.employees.size(); i ++){
            if(admin.employees.get(i).getEmployeeID().equals(employeeID)){
                this.employee = admin.employees.get(i);
            }
        }


        payslip.printPayslip(employeeID);
    }

     */

    /**
     * Handles the administration menu for managing employees.
     *
     * Allows an admin to log in, validate their password, and then either add or remove
     * employees from the system, including updating the corresponding employee data.
     */
    private void AdminMenu() {
        if (logIn()) {
            String password = null;
            while(password == null) {
                password = EmptyInputHandling("Please enter password");
                if (password.equals(adminpassword)) {
                    System.out.println("Password entered successfully.");
                    break;
                } else if (password.equals("Q")) {
                    quit();
                    return;
                } else {
                    System.out.println("Invalid password. Please try again.");
                    password = null;
                }
            }

            //admin can add or remove an employee
            command = EmptyInputHandling("A)dd employee R)emove employee Q)uit");
            switch (command) {
                case "A":
                    AddEmployees(); //add employee class
                    break;
                case "R": {
                    //remove employee
                    String employeeID = null;
                    while (employeeID == null) {
                        employeeID = EmptyInputHandling("Enter employee id");
                        if (inputEmployeeID(employeeID)) {
                            admin.removeEmployee(employeeID);
                            admin.updateCSV();
                            break;
                        } else {
                            System.out.println("Invalid employee ID. Please try again.");
                            employeeID = null;
                        }

                    }
                    break;
                }
                case "Q":
                    quit();
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    } //checked

    /**
     * Authenticates an employee based on their Employee ID.
     *
     * Prompts the user to enter a 4-digit Employee ID, validates the input, and checks
     * if the ID exists in the system. If the ID matches an employee, that employee is
     * logged in and stored in the `employee` field. Returns true if login is successful.
     * @return true if the login is successful, false otherwise.
     */
    private boolean logIn() {
        String employeeID = EmptyInputHandling("Please enter your 4-digit Employee ID to log in (or Q to quit):");


        if (employeeID.equals("Q")) {
            quit();
            return false;
        }
        try {
            int id = Integer.parseInt(employeeID);
            if (id < 1000 || id > 9999) {
                System.out.println("Invalid ID. Please enter a 4-digit Employee ID.");
                return false;
            }

            for (Employee emp : admin.getEmployees()) {
                if (emp.getEmployeeID().equals(employeeID)) {
                    employee = emp;
                    System.out.println(emp.getName() + " (" + employeeID + ") logged in successfully.");
                    return true; // Successful login
                }
            }

            // No employee found with the entered ID
            System.out.println("User not found. Please check your Employee ID.");
            return false;

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid 4-digit number.");
            return false;
        }
    } //checked


    /**
     * Handles the HR (Human Resources) menu for managing employee promotions and raises.
     *
     * Allows an HR user to log in, verify their password, and then access options of employee promotions and raises
     *
     * Includes input validation for employee IDs, positions, and pay rates, and handles various
     * error conditions to ensure valid data entry.
     */
    private void HRMenu() {
        if (logIn()) {
            // Password handling
            String password = null;
            while (password == null) {
                password = EmptyInputHandling("Please enter password");
                if (password.equals(hrpassword)) {
                    System.out.println("Password entered successfully.");
                } else if (password.equals("Q")) {
                    quit();
                    return;
                } else {
                    System.out.println("Invalid password. Please try again.");
                    password = null;
                }
            }

            // Main HR Menu
            boolean t = false;
            while (!t) {
                command = EmptyInputHandling("P)romote employee G)ive raise Q)uit").toUpperCase();
                switch (command) {
                    case "P":
                        handlePromotion();
                        break;

                    case "G": // Fix case label for "Give raise"
                        handleRaise();
                        break;

                    case "Q":
                        quit();
                        t = true; // Exit the loop after quitting
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }


    /**
     * Handle an employee promotion
     */
    private void handlePromotion() {
        String employeeID = EmptyInputHandling("Please enter employee ID");
        if(employeeID.equals("Q")) {
            quit();
            return;
        }
        if(!inputEmployeeID(employeeID)) {
            System.out.println("Invalid employee ID. Please try again.");
            return;
        }

        Employee employeeToPromote = findEmployeeById(employeeID);
        //promote employee functionality
        command = EmptyInputHandling("I)ncrement current point N)ew Position Q)uit");
        if (command.equals("I")) {
            incrementSalaryPoint(employeeToPromote);
        } else if(command.equals("Q")) {
            quit();
            return;
        } else if(command.equals("N")) {
            String newPosition = validatePosition(employeeToPromote.getIsFullTime());
            if (newPosition != null) {
                humanResources.promoteEmployee(employeeID, newPosition);
                System.out.println("Employee promoted to position: " + newPosition);
            }
        } else {
            System.out.println("Invalid option. Returning to menu.");
        }
    }

    /**
     * Handles giving an employee a raise
     */
    private void handleRaise() {
        String employeeID = EmptyInputHandling("Enter employee ID:");
        if (employeeID.equals("Q")) {
            quit();
            return;
        }

        if (!inputEmployeeID(employeeID)) {
            System.out.println("Invalid employee ID. Please try again.");
            return;
        }

        // Locate the employee
        Employee employeeToRaise = findEmployeeById(employeeID);
        if (employeeToRaise == null) {
            System.out.println("Employee not found. Please enter a valid employee ID.");
            return;
        }

        if (employeeToRaise.getIsFullTime()) {
            System.out.println("Employee is full-time. Cannot give raise.");
            return;
        }

        // Process raise
        String input = EmptyInputHandling("Enter new pay rate:");
        try {
            double payRate = Double.parseDouble(input);
            humanResources.employeeRaise(employeeID, payRate);
            System.out.printf("Employee %s's pay rate updated to %.2f.\n", employeeID, payRate);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for new pay rate. Please enter a numeric value.");
        }
    }

    /**
     * Finds if employee exists and returns them if they do
     * @param employeeID
     * @return
     */
    private Employee findEmployeeById(String employeeID) {
        for (Employee emp : admin.getEmployees()) {
            if (emp.getEmployeeID().equals(employeeID)) {
                return emp;
            }
        }
        return null;
    }

    /**
     * Increments the employees salary point if not at top of point scale
     * @param employee
     */
    private void incrementSalaryPoint(Employee employee) {
        int currentIndex = validFullTimeEmployees.indexOf(employee.getPosition());
        if (employee.getCurrentPoint() < maxPoints.get(currentIndex)) {
            employee.setCurrentPoint(employee.getCurrentPoint() + 1);
            admin.updateCSV();
            System.out.printf("Employee %s promoted from %d to %d on salary scale.\n",
                    employee.getEmployeeID(), employee.getCurrentPoint() - 1, employee.getCurrentPoint());
        } else {
            System.out.printf("Employee %s is already at the maximum point on salary scale.\n", employee.getEmployeeID());
        }
    }

    /**
     * Prompts the user to enter a new position to be promoted to
     * Checks if the position is valid from the list
     * returns the position to promote to if valid
     * @param isFullTime
     * @return position
     */
    private String validatePosition(boolean isFullTime) {
        String position = null;
        String message = "Enter new position:";

        while (position == null) {
            String attempt = EmptyInputHandling(message);
            if (validFullTimeEmployees.contains(attempt)) {
                position = attempt;
            } else {
                System.out.println("Invalid position. Would you like to see a list of valid positions? Y/N");
                if (EmptyInputHandling("").equalsIgnoreCase("Y")) {
                    viewValidFullTimeEmployees();
                }
            }
        }

        return position;
    }



    /**
     * Handles the addition of a new employee to the system.
     *
     * Allows the user to choose whether the new employee is full-time or part-time. Prompts
     * for necessary details such as Employee ID, name, position, and either salary scale
     * point (for full-time) or hourly pay rate (for part-time). Validates inputs and updates
     * the employee list upon successful entry.
     */
    private void AddEmployees() {
        System.out.println("F)ull time or P)art time");
        command = in.nextLine().toUpperCase();
        if (command.equals("F")) {
            System.out.println("Enter employee ID:");
            String employeeID = in.nextLine().trim();
            if (inputEmployeeIDAddEmployee(employeeID)) {
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
                System.out.printf("Enter point on scale between 1 and %d for %s %n", maxPoint, position); //check valid points against position
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

        } else if (command.equals("P")) {
            System.out.println("Enter employee ID:");
            String employeeID = in.nextLine().trim();
            if (inputEmployeeID(employeeID)) {
                if (employeeID == "Q") {
                    running = false;
                }

                String name = EmptyInputHandling("Enter Employee Name");
                if (name == "Q") {
                    running = false;
                }
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
                        } else if (command.equals("Q")) {
                            running = false;
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
            } else {
                running = false;
            }
        }
    }

    /**
     * Prints out a list of all full time employees
     */
    private void viewValidFullTimeEmployees() {
        for (String s : validFullTimeEmployees) {
            System.out.println(s);
        }
    }

    /**
     * Prints out a list of all part time employees
     */
    private void viewValidPartTimeEmployees() {
        for (String s : validPartTimeEmployees) {
            System.out.println(s);
        }
    }

    /**
     * Handles user input to ensure it is not empty.
     *
     * Displays a given message prompt, reads user input, and checks if the input is empty.
     * If the input is empty, the method displays an error message and prompts the user again
     * until a non-empty input is provided.
     *
     * @param message the message to display as a prompt for the user
     * @return the non-empty input entered by the user
     */
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

    /**
     * Handles the submission of a pay claim for part-time employees.
     *
     * Checks whether the logged-in employee is part-time or full-time. If part-time, prompts
     * the user to enter the number of hours worked, validates the input, and submits the pay
     * claim. Displays an appropriate message if the employee is full-time or if the input
     * is invalid.
     */
    public void PayClaimSubmission() {
        try {
            if (employee == null) {
                System.out.println("Error: Employee data is missing.");
                return;
            }

            if (employee.getIsFullTime()) {
                System.out.println("Employee is full-time, pay claim cannot be submitted.");
                return;
            } else {
                System.out.println("Submit pay claim.");
                System.out.println("Please enter number of hours worked:");
                String command = in.next();

                // Attempt to parse the number of hours
                try {
                    hours = Integer.parseInt(command.trim());
                    if (hours < 0) {
                        System.out.println("Number of hours cannot be negative.");
                        return;
                    }
                    System.out.println(hours + " submitted.");
                    PayClaim.validateClaim(employee);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number of hours worked.");
                }
            }

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    /**
     * The method inputEmployeeIDAddEmployee() handles instances when the user inputs an employee ID. This method handles
     * the speecific case when users are attempting to add a new employee
     * @param employeeID    the user input
     * @return boolean
     */
    private boolean inputEmployeeIDAddEmployee(String employeeID){
        try {
            try {
                //check to see if entered employee id is 4 digits
                if (Integer.parseInt(employeeID) < 1000 || Integer.parseInt(employeeID) > 9999) {
                    System.out.println("Invalid ID.");
                } else if (employeeID.isEmpty()) {
                    System.out.println("Employee ID cannot be empty.");
                    return false;
                } else {

                    for (Employee emp : admin.getEmployees()) {
                        if (emp.getEmployeeID().equals(employeeID)) {
                            employee = emp;
                            System.out.println(emp.getName() + "(" + employeeID + ") already exists. Please enter a new" +
                                    "Employee ID.");
                            employeeID = in.nextLine().trim();
                            return true;
                        }
                    }
                    System.out.println("Valid ID.");
                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a 4 digit employee ID.");
            }


        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        return false;
    }

    /**
     * The method inputEmployeeID() handles cases where the user inputs an employee ID to the code and implements checks
     * to ensure the user enters a valid ID
     * @param employeeID    user input
     * @return boolean
     */
    private boolean inputEmployeeID(String employeeID){
        try {
            try {
                //check to see if entered employee id is 4 digits
                if (Integer.parseInt(employeeID) < 1000 || Integer.parseInt(employeeID) > 9999) {
                    System.out.println("Invalid ID.");
                } else if (employeeID.isEmpty()) {
                    System.out.println("Employee ID cannot be empty.");
                    return false;
                } else {

                    for (Employee emp : admin.getEmployees()) {
                        if (emp.getEmployeeID().equals(employeeID)) {
                            employee = emp;
                            System.out.println(emp.getName() + "(" + employeeID + ") found.");
                            return true;
                        }
                    }
                    System.out.println("User not found. Please check your Employee ID.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a 4 digit employee ID.");
            }


        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        return false;
    }

    /**
     * Quits the programme
     */
    public void quit(){
        System.out.println("Slán!");
                running = false;
    }

}
