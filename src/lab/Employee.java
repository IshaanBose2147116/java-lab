package lab;

abstract public class Employee {
    private String fname, lname;
    public int employeeID;

    public String getName() {
        return this.fname + " " + this.lname;
    }

    abstract public String getEmployeeDetails();
}
