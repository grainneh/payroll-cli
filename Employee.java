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
        try {
            String employeeID = EmptyInputHandling("Enter Employee ID:"); // add check that string is numbers and of correct length
            String name = EmptyInputHandling("Enter name:");


            String position = EmptyInputHandling("Enter position:"); // add checks against valid employee array


            String salaryScale = EmptyInputHandling("Enter salary scale:"); // add checks against valid salary scales from array

            System.out.println("Enter current point on salary scale:");
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


            //System.out.println("Is employee F)ull Time or P)art Time?");
            //command = in.nextLine().toUpperCase().trim();
            //boolean isFullTime = command.equals("F");

            boolean isFullTime = false; // Initialize
            while (true) {
                System.out.println("Is the employee F)ull Time or P)art Time?");
                command = in.nextLine().toUpperCase().trim();

                if (command.equals("F")) {
                    isFullTime = true;
                    break; // Exit loop
                } else if (command.equals("P")) {
                    break; // Exit loop
                } else {
                    System.out.println("Invalid input. Please enter 'F' for Full Time or 'P' for Part Time.");
                }
            }


            newEmployee = new Employee(employeeID, name, position, salaryScale, currentPoint);
            admin.addEmployee(newEmployee);
            admin.updateCSV();
            System.out.println("Employee " + employeeID + " added.");
        } catch (NumberFormatException e) {
            System.out.println("Current point on salary scale must be a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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

