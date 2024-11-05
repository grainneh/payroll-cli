/*
Attributes:
    String employeeId
    String payPeriod
    double grossPay
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
    String employeeID;
    String name;
    double grossPay;
    double taxDeductions;
    double voluntaryDeductions;
    double totalDeductions;
    double netPay;
    Employee employee; //call the employee

    public Payslip(Employee employee) {

    }

    //full time is true, part time is false using if statement, need to look at philips code

    //check if employee is full time or part time then use corresponding method to calculate gross pay
    public double grossPayFullTime(){
        //for salaried workers pay is a monthly set amount
        return grossPay = salary / 12; //?is that how salaries work
    }
    public double grossPayPartTime(){
        //for hourly workers gross pay is hours * rate
        //hourlyRate and hoursWorked are in PayClaim
        return grossPay hoursWorked * hourlyRate;
    }

    public double taxDeductions(double salary){
        //get salary

        //figure out what tax brackets they are in

        //compute PRSI
        //once weekly earnings exceed €352.01 employee is charged
        //calculate 1/6 of earnings
        //subtract from €12 to get credit
        //calculate 4.1% of weekly earnings
        //4.1% - credit = weekly PRSI
        //from CitizensInformation.ie


        //compute USC
        //standard rates from revenue.ie
        //first €12,012 @ 0.5%
        //next €13,748 @ 2%
        //next €44,284 @ 4%
        //balance @ 8%

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
    payslips.add(payslip);
}
