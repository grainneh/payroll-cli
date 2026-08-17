/**
 * The class Deductions calculates the various deductions from an employees gross pay. These include health insurance,
 * union contributions, PRSI, USC and income tax.
 *
 * @authors Gráinne Hartigan
 * @version 29/11/2024
 * */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Deductions {
    private CLI cli; //instance of CLI
    double salary; //the employee's yearly salary (for full-time employees)
    double grossMonthlyPay; //all deductions are calculated based off of monthly pay
    Employee employee; //employee whose pay is to be calculated
    double netPay;
    ArrayList<String> columnPositions = new ArrayList<> (); // arrayList of positions
    ArrayList<Integer> columnPoints = new ArrayList<>(); //arrayList of salary scale points
    ArrayList<Double> columnSalaries = new ArrayList<>(); //arrayList of salaries


    /**
     * Constructor for the deductions which accepts an employee as a parameter.
     *
     * @param employee  the employee whose deductions are to be calculated
     */
    public Deductions(Employee employee){
        this.employee = employee; //this is the employee referenced throughout the class
    }

    /**
     * The method grossMonthlyPay() checks if an employee is full-time or part-time.
     * Full time employees are salaried, part-time employees are paid an hourly rate.
     */
    public void calculateGrossMonthlyPay() {
        if (employee.getIsFullTime()) {
            grossMonthlyPay = getSalary() / 12; //assume salary is annual -> divide by 12 for monthly pay
        }else {
            grossMonthlyPay = cli.hours * employee.getPayRate(); //employee submits their hours worked in the CLI
        }

    }
    /**
     * The method grossMonthlyPay() returns the gross monthly pay calculated in calculateGrossMonthlyPay() to avoid
     * having to call this method and calculate the gross monthly pay every time it is needed.
     *
     * @return grossMonthlyPay   the gross monthly pay
     */
    public double grossMonthlyPay(){
        calculateGrossMonthlyPay();
        return grossMonthlyPay;
    }


    /**
     * The method PRSI calculates the employee's monthly PRSI contribution. Information on how to calculate PRSI was
     * taken from CitizensInformation.ie and are as follows:
     *
     * Steps to calculate PRSI
     * - calculate 1/6 of earnings
     * - subtract €12 to get credit
     * - calculate 4.1% of weekly earnings
     * - 4.1% - credit = weekly PRSI
     *
     * @return PRSI     the employee's monthly PRSI contribution
     */
    public double PRSI(){
        double PRSI;
        double credit = 12;
        double weeklyEarnings = grossMonthlyPay()/4;//PRSI is based off of weekly pay, so we must multiply by 4 at the end

        if (weeklyEarnings < 352){
            return 0.0;
        } else if (weeklyEarnings ==352.01){
            PRSI =  (weeklyEarnings * 0.041)- credit;
        } else if (weeklyEarnings <= 424) {
            double AfterCutOff = weeklyEarnings - 352.01;
            double sixthOfEarnings=AfterCutOff/6;
            double taxCredit = Math.max(0,12 - sixthOfEarnings);//make sure we don't get negatives
            PRSI = (weeklyEarnings * 0.041)- taxCredit;
        }
        else{
            PRSI = weeklyEarnings*0.041;
        }
        return(PRSI*52)/12;//return back to monthly values
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
     * USC is calculated on a yearly basis, so the method returns USC/12
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

    /**
     * The method LoadCSV() loads the csv "Salary.csv" which contains the information about positions, points on the
     * UL salary scale and their associated salary figures.
     */
    public void LoadCSV() {
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
            System.out.println("CSV Loaded.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method getSalary() gets the salary associated with a specific position and point by iterating through the
     * position and points arrayLists.
     *
     * @return salary   the employee's yearly salary
     */
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
