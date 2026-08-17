/*
Attributes:
    String scaleName
    Map<Integer, Double> points // Point to salary mapping
Methods:
    double getSalary(int point)
    boolean isTopPoint(int point)
    String getNextScale(Employee employee)
->from ChatGPT

INITIAL THOUGHTS:
There are UL Salary scales for full time and part time employees. Each of these scales has several points. In October,
full time staff are moved onto the next point in their salary scale, if not already at the top. They can be promoted
from time to time to the next SALARY SCALE (not point) within their professional category. Point to which they are
promoted to is dependent on how long they spent at the top of their previous scale.

    Need to move employee to next point on salary scale in October, unless at top
    Need functionality for admin to promote an employee to NEXT salary scale within department
        Point promoted to dependent on how long they spent at top of prev scale -> need to check out more info here

    How do we store the different salary scales for each department/employee?
        Would salary scales be defined for each role in the organisation -> attach scales to the employee classes
        Eg - if you create an employee of type Lecturer, also define the salary scale for a lecturer

PSEUDOCODE:


@author Gráinne Hartigan
@date 5.11.2024
 */
public class SalaryScale {
}
