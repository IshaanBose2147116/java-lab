package lab;

abstract public class Employee {
    private String fname, lname;
    public int employeeID;

    public void setName(String name) {
        this.fname = name.split(" ")[0];
        this.lname = name.split(" ")[1];
    }

    public String getName() {
        return this.fname + " " + this.lname;
    }

    abstract public String getEmployeeDetails();
}
