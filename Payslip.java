/*
Attributes:
    String employeeId
    String payPeriod
    double grossMonthlyPay
    double deductions
    double netPay
Methods:
    void calculateDeductions()
    void displayPayslip()
-> from ChatGPT

INITIAL THOUGHTS:
The payroll system should generate payslips for all full time staff and hourly staff (w/ current pay claims) on the 25th
day of every month. When calculating net pay for a payslip, deductions must be made. These include deductions for health
insurance, union fees, and various taxes (USC, PRSI, Income Tax etc (check Revenue)).

    Ensure this occurs on 25th of month
    Ensure part time employees w/ no pay claim do not get paid
    Do all employees have the same number of deductions?
        Health insurance, union fees, taxes
        Could we define a deducttions class to calcuate this for a given employee
        Would have to include checks to see if they pay insurance, union member?
        Define this information in the Employee class
        Checks for tax brackets could be in the Deductions class
    What information should be included in the final payslip?
        EMPLOYEE NAME
        Date
        PPS no.
        PRSI Class
        Weekly Tax Credit
        Rate - for part time employees, would assume its different for salaried employees not sure
        Hours - same with hours
        Basic Pay
        Total Pay

        DEDUCTIONS
        Total Pay
        - pension
        - insurance
        - union fees
        // here we minused the "voluntary contributions" to get gross pay
        Total Gross
        - PAYE
        - USC
        - PAYE ee
        //then we minused the taxes in order to get net pay
        NET PAY
    Takes employee, adds payslip to an arraylist
    Write this payslip to a csv file


PSEUDOCODE:
- takes in employee
- checks salary scale and uses this to pay the employee
- taxDeductions() method calculates taxes
- voluntaryDeductions() method calculates union fees, health insurance
- netPay() method subtracts the tax and voluntary deductions
- add to Philips arrayList
- write to csv file?

@author Gráinne Hartigan
@date 5.11.2024
 */

public class Payslip {
    double salary;
    double grossMonthlyPay;
    double taxDeductions;
    double voluntaryDeductions;
    double totalDeductions;
    double netPay;

    Employee employee = new Employee(); // needed to create an instance of Employee to use the

    //need to get the salary scale and point for specific postion to determine salary
    //salary scales etc need to be stored somewhere for each employee
    //i can use these to determine the salary but currently have no info
    //need an actual figure for salary to work with

   // String salaryScale = Employee.getSalaryScale();
   // int salaryPoint = Employee.getCurrentPoint();
    //String salaryPosition = Employee.getPosition();


    //check if employee is full time or part time then calculate gross pay
    if(employee.isFullTime()){
        grossMonthlyPay = salary / 12; //I think this is how it works for salaries
    }else{
        grossMonthlyPay = PayClaim.getHoursWorked() * PayClaim.getHourlyRate();
        //employee submits their hours worked in PayClaim, hourlyRate should also be in PayClaim
    }


    //get grossMonthlyPay & figure out what tax brackets they are in
    public double taxDeductions(double grossPay){
        /* PRSI
            - once weekly earnings exceed €352.01 employee is charged, else no PRSI
            - we are using a monthly pay system, so we divide gross pay by 4

            Steps to calculate PRSI taken from CitizensInformation.ie
             - calculate 1/6 of earnings
             - subtract from €12 to get credit
             - calculate 4.1% of weekly earnings
             - 4.1% - credit = weekly PRSI
         */
        double PRSI; //defined here so in scope
        double weeklyEarnings = grossPay/4;
        if(weeklyEarnings > 352.01){
            double PRSICredit = 12 - (weeklyEarnings / 6);
            double weeklyPRSI = weeklyEarnings * (.041);
            PRSI = weeklyPRSI - PRSICredit;
        }else {PRSI = 0;

        /*
            USC -> WORK IN PROGRESS AS OF 5/11/24
            - charged if your income is over €13k a year
            - standard rates from revenue.ie
            - first €12,012 @ 0.5%
            - next €13,748 @ 2%
            - next €44,284 @ 4%
            - balance @ 8%
            - need to find yearly pay here, then divide the USC by 12 for the monthly deduction
         */
            double USC = 0; //defined here to keep in scope, set as zero as USC is not charged below 13k
            double grossYearlyPay = grossMonthlyPay*12;
            if(grossYearlyPay > 13000){
                USC = 12012 * .005;
                if(grossYearlyPay <= 13748){
                    USC += balance * .02;

                }
                if(grossYearlyPay <= 44284){
                    USC += balance * .04;
                }
                if(grossYearlyPay > 44284){
                    USC += balance * .08;
                }
            }



        //compute Income Tax
        //assume employee is single
        //€42k @ 20%, balance @ 40%, from revenue.ie

        //add together
        //return total
        return taxDeductions;
    }

    public double voluntaryDeductions(){
        //determine if part of union and compute union fees
        //compute health insurance
        //compute pension
        //add these contributions together
        return voluntaryDeductions;
    }

    public double totalDeductions(double taxDeductions, double voluntaryDeductions){
        //add tax and voluntary deductions together
        this.taxDeductions = taxDeductions;
        this.voluntaryDeductions = voluntaryDeductions;
        totalDeductions = taxDeductions + voluntaryDeductions;
        return totalDeductions;
    }

    public double netPay(double grossPay, double totalDeductions){
       //net pay is the gross pay - the deductions
        return netPay = grossPay - totalDeductions;
    }

    //add completed payslip to philip's arrayList
    //payslips.add(payslip);
}
