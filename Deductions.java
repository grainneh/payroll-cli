import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
New class deduction to calculate pay deductions, allowing Payslip to simply generate a payslip without extraneous methods
The class deductions determines the salary by checking if the employee is full/part time (using method from Employee)
Calculates tax deductions, and uses set rates for union and health insurance fees
Calculates net pay

There is a problem with the salary and hours worked. Not sure how to get salary using getPosition(), getSalaryScale()
and getCurrentPoint(). For part time employees, I think the getHoursWorked() should come from when they submit their
pay claim in the CLI, and their hourlyRate needs to be defined somewhere. Possibly in the employee class.

@author Gráinne Hartigan
@version 9.11.24
 */
public class Deductions {
    private CLI cli;
    double salary;
    double grossMonthlyPay; //all deductions are calculated based off of monthly pay
    Employee employee; //employee whose pay is to be calculated
    double netPay;
    ArrayList<String> columnPositions = new ArrayList<> ();
    ArrayList<Integer> columnPoints = new ArrayList<>();
    ArrayList<Double> columnSalaries = new ArrayList<>();


    public Deductions(Employee employee){
        this.employee = employee; //this is the employee referenced throughout the class
    }

    /**
     * The method grossMonthlyPay() checks if an employee is full time or part time.
     * Full time employees are salaried, part-time employees are paid an hourly rate
     *
     * @return grossMonthlyPay  the employee's gross monthly pay
     */
    public void calculateGrossMonthlyPay() {
        if (employee.getIsFullTime()) {
            grossMonthlyPay = getSalary() / 12; //assume salary is annual -> divide by 12 for monthly pay
            //issue remains with determining the employees salary, needs to come from csv i think
        }else {
            grossMonthlyPay = cli.hours * employee.getPayRate();
            //employee submits their hours worked in the CLI
            // getPayRate method needs to exist in the Employee class for part-time employees, should be in constructor
        }

    }
    /**
     * The method grossMonthlyPay() returns the gross monthly pay calculated in calculateGrossMonthlyPay() to avoid
     * having to call this method and calculate the gross monthly pay every time it is needed
     *
     * @return  the gross monthly pay
     */
    public double grossMonthlyPay(){
        return grossMonthlyPay;
    }


    /**
     * The method PRSI calculates the employee's monthly PRSI contribution. Information on how to calculate PRSI was
     * taken from CitizensInformation.ie and are as follows:
     *
     * Steps to calculate PRSI
     * - calculate 1/6 of earnings
     * - subtract from €12 to get credit
     * - calculate 4.1% of weekly earnings
     * - 4.1% - credit = weekly PRSI
     *
     * @return PRSI     the employee's monthly PRSI contribution
     */
    public double PRSI(){
        double PRSI;
        double weeklyEarnings = grossMonthlyPay()/4; //PRSI is based off of weekly pay, so we must multiply by 4 at the end
        if(weeklyEarnings > 352.01){
            double PRSICredit = 12 - (weeklyEarnings / 6);
            double weeklyPRSI = weeklyEarnings * (.041);
            PRSI = weeklyPRSI - PRSICredit;
            PRSI *= 4; //ensures we stick with monthly pay
        }else PRSI = 0;
        return PRSI;
    }

    /**
     * The method USC calcuates the employee's monthly USC contribution. Information on how to calculate USC was taken
     * from Revenue.ie as follows:
     *
     * USC
     * - charged if your income is over €13k a year
     * - first €12,012 @ 0.5%
     * - next €13,748 @ 2%
     * - next €44,284 @ 4%
     * - balance @ 8%
     *
     * USC is calculated on a yearly basis, so the method returns USC/12;
     *
     * @return USC      the employee's monthly USC contribution
     */
    public double USC(){
        double grossYearlyPay = grossMonthlyPay()*12; //USC is calculated based on annual pay
        double USC = 0.0;
        if(grossYearlyPay > 13000){
            if(grossYearlyPay <= 12012){
                USC = grossYearlyPay * 0.005;
            }else if (grossYearlyPay <= 25760){
                USC = 60.06 + (grossYearlyPay - 12012) * 0.02;
            }else if(grossYearlyPay <= 70044){
                USC = 335.02 + (grossYearlyPay - 25760) * 0.04;
            }else {
                USC = 2106.38 + (grossYearlyPay- 70044)*0.08;
            }
        }
        USC /= 12; //divide by twelve to maintain monthly pay
        return USC;
    }

    /**
     * The method incomeTax calculates the employee's monthly PAYE contribution. This method assumes the employee is
     * single for simplicity.  Tax bands taken from revenue.ie as follows: €42k @ 20%, balance @ 40%
     *
     * @return incomeTax    the employee's monthly PAYE contribution
     */
    public double incomeTax(){
        double incomeTax;
        if (grossMonthlyPay() < 42000){
            incomeTax = grossMonthlyPay()*.2;
        }else{
            incomeTax = (42000*.2)+ (grossMonthlyPay() - 42000)*.4;
        }
        incomeTax = incomeTax/12;
        return incomeTax;
    }

    /**
     * The method netPay calculates the employee's net pay after all deductions have been made.
     *
     * @return netPay   the employee's net pay
     */
    public double netPay(){
        double unionFees = 12.35; //assume all employees are union members (figure from Unite.ie)
        double healthInsuranceFees = 143.94;//assume all employees are VHI members (figure from VHI.ie)
        return netPay = grossMonthlyPay() - PRSI() - USC() - incomeTax() - unionFees - healthInsuranceFees;
    }

    public  void LoadCSV() {
        String path =  "Salary.csv";


        String line = "";
        boolean firstLine =true;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;  // Set flag to false after processing the header
                    continue;  // Skip this iteration (header row)
                }
                String[] columns = line.split(",");
                if (columns.length == 3) {
                    String position = columns[0];
                    int currentPoint = Integer.parseInt(columns[1]);
                    double salary = Double.parseDouble(columns[2]);
                    columnPositions.add(position);
                    columnPoints.add(currentPoint);
                    columnSalaries.add(salary);

                }
            }
            System.out.println("CSV Loaded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getSalary() {
        String employeePosition = employee.getPosition();
        int employeeCurrentPoint = employee.getCurrentPoint();
        for(int i = 0; i < columnPositions.size(); i++){
            if(employeePosition == columnPositions.get(i) && employeeCurrentPoint == columnPoints.get(i)){
                return columnSalaries.get(i);
            }
        }
        return salary;
    }

}
