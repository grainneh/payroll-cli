public class Admin Extends Employee {
    public  Admin(String EmployeeID,String name,String position){
    super (String EmployeeID, String name, String position);//pulls parameters from employees
    }

    //add employee adds an employee to the Employess class,it doesnt use public
    //as it only can be used by admin.

    private void addEmployee(Employee employee, String position) {
           double Salary = newSalary Salary.setSalary(position);//gets the salary for the given position

           employee.setPosition(position);//sets position,salary and if fulltime or not
           Salary.setSalary(newSalary);
           employee.isFulltime(true);

           employess.add(employee);//adds to employee array
        }

        //same as addemployee except does the oppisite
    private void removeEmployee(String  EmployeeID){
        for (int i =0; i< employess.size(); i++) {
            if (employees.get(i).getID().equals(EmployeeID)) {//gets index (i) and checks if this ID is equal to the ID of the given ID
                employees.remove(i);//removes the ID at array index (i)
                i--; // if an item was removed,array shrinks so this would make sure we dont skip a item in the array
                return; //This stops the loop if an employee was removed
            }
        }
        System.out.println("Employee not found");//If the loop stops with no id found,prints this message
    }

    //this promotes a employee by getting their ID while the position parameter is what position you want to promote
    // them to be. It then gets a new salary inline with the new position then sets both of them.

    private void Promotion(String EmployeeID,String position){
        Employee employee = getEmployeeID(EmployeeID);
        double newSalary = Salary.getSalary(position);
        double Salary = Salary.setSalary(newSalary);
        String postition = Employee.setPosition(position);

    }

}
