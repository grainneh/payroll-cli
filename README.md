# CS4013 Software Development Project

This project is a payroll system for University Of Limerick (UL) staff. This payroll system can handle tasks such as payslip generation and employee management. The payroll system has three user types - Employee, Admin and Human Resources (HR), all with different functionality.

# User Interaction with the Payroll System
Interaction between the users and the payroll system is facilitated via a command line interface (CLI). The user inputs basic, single character commands in the initial interaction with the interface. For example, the user is initially prompted to log in as either an Employee, Admin staff, or HR Staff.

The user then inputs either E, A, H, or Q to interact with the payroll system. Errors in input are handled gracefully and the user is prompted to try again.

Once the user has logged in as an Employee, Admin staff or HR staff, they may enter strings or numbers to the command line interface. The payroll system handles errors in user input by providing instructions on the expected formatting and prompting command re-entry.

# User Types
# Employee
Employees are prompted to login with their employee ID and can either view their employee details, view their payslip for a specific date or submit a pay claim.

# Admin
Admin staff are prompted to log in with their employee ID and enter a password that allows them admin privileges. Admin staff can add or remove employees from the payroll system. Information about these employees are stored in two CSV files “EmployeesFullTime.csv” and “EmployeesPartTime.csv” for full-time and part-time employees respectively.

# Human Resources 
Human Resources staff are prompted to log in with their employee ID and enter a password that allows them HR privileges. HR staff can promote employees, or give them a pay raise.

# Payroll System
# Payslips
The UL payroll system generates a payslip for each employee on the 25th of the month. These payslips are stored in a CSV file named “Payslips.csv”. Part-time employees must submit a payclaim before the second Friday of each month to get paid. The payroll system implements checks to ensure this is done so.

Employees can log onto the UL payroll system and view their payslips. 

# Salary Scales
Each full-time position has an associated salary scale which determines the employee’s yearly salary. This data is stored in a CSV file named “Salary.csv” and the payroll system accesses these values when calculating an employee’s pay and generating their payslip each month.

Full-time employees are promoted to the next point on their salary scale each October. This is taken into account by the payroll system and occurs automatically each October. However, HR staff also have the ability to promote an employee to the next point on their salary scale at any given time.

