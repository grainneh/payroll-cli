/*
Payslip Class

Made major changes to the payslip class
Moved the calculation of the employee's pay to a separate method called Deductions to keep the code tidy
The Payslip class includes a constructor that accepts an employee as a parameter
The generatePayslip() method generates a (String) payslip for the employee, taking values from the Deductions class
    It checks if it is the 25th of the month. If yes, generates payslip. If not, returns a String informing user that
    it is not the 25th of the month

@author Gráinne Hartigan
@date 9.11.2024
 */

import java.time.LocalDate;

public class Payslip {
    Employee employee; //employee whose pay is to be calculated
    String name; //employee name
    String employeeID; //employee ID
    LocalDate todaysDate; //today's date
    int payDay; //employees need to be paid on the 25th of each month

    public Payslip(Employee employee) { //issue remains with determining the employees salary, needs to come from csv i think
        name = employee.getName();
        employeeID = employee.getEmployeeID();
        todaysDate = LocalDate.now(); //should be the 25th, so we implement a check
        payDay = todaysDate.getDayOfMonth();
        this.employee = employee; //this is the employee referenced throughout the class
    }

    /**
     * The method generatePayslip() generates a payslip if it is the 25th of the month
     *
     * @return a string representation of the employee's payslip
     */
    public String generatePayslip(Employee employee) {
        if (payDay == 25) { //if it is the 25th of the month generate a payslip
            Deductions deductions = new Deductions(employee); //calculate the deductions for the employee in question
            return "Payslip " + todaysDate +
                    "\n" + employee.getName() + " (" + employee.getEmployeeID() + ")" +
                    "\nGross pay: €" + deductions.grossMonthlyPay() +
                    "\n ---------------------------------------" +
                    "\nDEDUCTIONS:" +
                    "\nPRSI: €" + deductions.PRSI() +
                    "\nUSC: €" + deductions.USC() +
                    "\nIncome tax: €" + deductions.incomeTax() +
                    "\nHealth insurance: €" + 143.94 +
                    "\nUnion Contribution: €" + 12.35 +
                    "\n ---------------------------------------" +
                    "\nNET PAY: €" + deductions.netPay();
        }else{
            return "Payslip cannot be generated today. It is not the 25th of the month.";
        }
    }

    public LocalDate getDate() {
        return todaysDate;
    }
}
